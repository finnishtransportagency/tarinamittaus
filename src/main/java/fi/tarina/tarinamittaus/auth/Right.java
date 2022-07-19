package fi.tarina.tarinamittaus.auth;

import java.util.Arrays;
import java.util.List;

public enum Right {
    MODIFY_SECURED_FIELDS,
    MODIFY_UNSECURED_FIELDS;

    public static List<Right> getAllRights() {
        return Arrays.asList(MODIFY_SECURED_FIELDS, MODIFY_UNSECURED_FIELDS);
    }

    public static List<Right> getModifyAllRights() {
        return Arrays.asList(MODIFY_SECURED_FIELDS, MODIFY_UNSECURED_FIELDS);
    }

    public static List<Right> getModifyUnsecuredRights() {
        return Arrays.asList(MODIFY_UNSECURED_FIELDS);
    }
}
