package fi.tarina.tarinamittaus.Util;

import fi.tarina.tarinamittaus.auth.Constants;
import fi.tarina.tarinamittaus.auth.JwtRequestFilter;
import fi.tarina.tarinamittaus.auth.UserInfo;
import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class RequestLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();
            UserInfo userInfo = jwtRequestFilter.getUserInfo((HttpServletRequest) servletRequest);
            ThreadContext.put("user_name", userInfo.getUserName() == null ? "unknown user" : userInfo.getUserName());
            servletRequest.setAttribute(Constants.JWT_USER_GROUPS_ATTRIBUTE, userInfo.getUserGroups());
            servletRequest.setAttribute(Constants.JWT_USER_NAME_ATTRIBUTE, userInfo.getUserName());
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            ThreadContext.clearAll();
        }
    }

    @Override
    public void destroy() {

    }
}
