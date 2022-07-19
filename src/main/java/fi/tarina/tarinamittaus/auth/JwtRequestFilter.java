package fi.tarina.tarinamittaus.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SecurityException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class JwtRequestFilter {
    private final Logger logger = LogManager.getLogger(JwtRequestFilter.class);

    private static String publicKey = null;
    private static PublicKey ecPublicKey = null;

    // public for mocking in tests
    public String getPublicKey(String kid, boolean isForce) throws Exception {
        if (isForce || publicKey == null) {
            String url = "https://public-keys.auth.elb.eu-west-1.amazonaws.com/" + kid;
            HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();
            httpClient.setRequestMethod("GET");
            httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = httpClient.getResponseCode();

            logger.debug(String.format("Sending request %s", url));
            logger.debug(String.format("Response code %s", responseCode));

            String key = "";
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                key = response.toString()
                        .replaceAll("-----BEGIN PUBLIC KEY-----", "")
                        .replaceAll("-----END PUBLIC KEY-----", "");

                logger.debug(String.format("Public key %s", key));
            }
            publicKey = key;
            ecPublicKey = null;
        }
        return publicKey;
    }

    private Claims decodeJWT(String jwt, String key, boolean isForce) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (isForce || ecPublicKey == null) {
            logger.debug("ecPublicKey is null");
            byte[] publicKeyBytes = Base64.decodeBase64(key);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            ecPublicKey = keyFactory.generatePublic(keySpec);
            logger.debug("generated ecPublicKey: " + ecPublicKey);
        }
        logger.debug("using ecPublicKey: " + ecPublicKey);
        JwtParser parser = Jwts.parserBuilder().setSigningKey(ecPublicKey).build();
        return parser.parseClaimsJws(jwt).getBody();
    }

    public UserInfo getUserInfo(HttpServletRequest request) {
        Set<UserGroup> userGroups = new HashSet<>();
        String userName = null;
        try {
            logger.debug(String.format("Path %s", request.getServletPath()));

            String jwt = request.getHeader("x-amzn-oidc-data");
            if (jwt == null || jwt.length() == 0) {
                jwt = request.getHeader("x-iam-data");
            }
            if (jwt != null) {
                String jwt_headers = jwt.split("\\.")[0];
                String decoded_jwt_headers = new String(Base64.decodeBase64(jwt_headers));
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(decoded_jwt_headers);

                logger.debug(String.format("JWT headers json %s", jsonNode.toString()));

                String key = getPublicKey(jsonNode.get("kid").asText(),false);
                logger.debug("getPublicKey: " + key);
                Claims claims;
                try {
                    claims = decodeJWT(jwt, key, false);
                } catch (SecurityException e) {
                    // The exception is actually the subclass io.jsonwebtoken.SignatureException which is deprecated
                    logger.debug("Invalid key, trying again");
                    key = getPublicKey(jsonNode.get("kid").asText(),true);
                    claims = decodeJWT(jwt, key, true);
                }

                if (claims != null) {
                    String userNameClaim = (String) claims.get("username");
                    String uid = (String) claims.get("custom:uid");
                    userName = (uid != null) ? uid : userNameClaim;
                    logger.debug(String.format("Username %s", userName));

                    String[] roles = ((String) claims.get("custom:rooli")).split("\\,");
                    logger.debug(String.format("Roles %s", StringUtils.join(roles, ",")));

                    // claims.forEach((k, v) -> logger.debug(String.format("Claim %s=%s", k, v)));

                    for (String role : roles) {
                        switch (role) {
                            case "tk_tietoturva":
                                userGroups.add(UserGroup.SUPER_USER);
                            case "tk_muokkaus":
                                userGroups.add(UserGroup.MODIFY_USER);
                        }
                    }
                    if (userGroups.isEmpty()) {
                        logger.warn("JWT tokenissa ei ollut yhtaan tunnettua roolia");
                    }
                }
            } else {
                logger.warn("No JWT header found");
            }
            return new UserInfo(userName, new ArrayList<>(userGroups));
        } catch (Exception ex) {
            logger.error("Autorisointi epaonnistui", ex);
            return new UserInfo(userName, new ArrayList<>(userGroups));
        }
    }


}
