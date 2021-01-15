package by.nyurush.music.entity;

public enum AccountRole {
    ADMIN("ADMIN"), CLIENT("CLIENT");

    private final String val;

    AccountRole(String val) {
        this.val = val;
    }

    public String getAccountRole() {
        return val;
    }
}
