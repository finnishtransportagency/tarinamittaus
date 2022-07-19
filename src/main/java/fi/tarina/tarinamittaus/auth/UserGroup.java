package fi.tarina.tarinamittaus.auth;

import java.util.List;

public enum UserGroup {
    ADMIN(Right.getAllRights()),
    SUPER_USER(Right.getModifyAllRights()),
    MODIFY_USER(Right.getModifyUnsecuredRights());

    private List<Right> rights;

    UserGroup(List<Right> rights) {
        this.rights = rights;
    }

    public List<Right> getRights() {
        return rights;
    }

    public Boolean hasRights(List<Right> rights) {
        return this.rights.containsAll(rights);
    }

    private static UserGroup getUserGroup(String oamGroup) {
        switch (oamGroup) {
            case "tk_tietoturva":
                return SUPER_USER;
            case "tk_muokkaus":
                return MODIFY_USER;
            default:
                return null;
        }
    }

}
