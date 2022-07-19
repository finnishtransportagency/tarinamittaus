package fi.tarina.tarinamittaus.auth;

import java.util.List;

public class UserInfo {
    private final String userName;
    private final List<UserGroup> userGroups;

    public UserInfo(String userName, List<UserGroup> userGroups) {
        this.userName = userName;
        this.userGroups = userGroups;
    }

    public String getUserName() {
        return userName;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }
}

