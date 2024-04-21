package controllers;

import types.UserTypes;

public class PermissionController {

    private UserTypes user;
    private String file;
    private Boolean read, delete, execute;

    public PermissionController(UserTypes user, String file, Boolean read, Boolean delete, Boolean execute) {
        this.user = user;
        this.file = file;
        this.read = read;
        this.delete = delete;
        this.execute = execute;
    }

    public UserTypes getUser() {
        return this.user;
    }

    public String getFilename() {
        return this.file;
    }

    public Boolean canRead() {
        return this.read;
    }

    public Boolean canDelete() {
        return this.delete;
    }

    public Boolean canExecute() {
        return this.execute;
    }

    public String canReadStringfy() {
        return stringfyPermission(this.read);
    }

    public String canDeleteStringfy() {
        return stringfyPermission(this.delete);
    }

    public String canExecuteStringfy() {
        return stringfyPermission(this.execute);
    }

    public static String stringfyPermission(Boolean perm) {
        return perm ? "1" : "0";
    }

    public static Boolean parsePermission(String perm) {
       return perm.equals("1");
    }
}