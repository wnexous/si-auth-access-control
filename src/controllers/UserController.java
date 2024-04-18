package controllers;

public class UserController {

    private static String username, password;

    public UserController(String user, String pwd) {
        password = pwd;
        username = user;
    }

    public String getUsername() {
        return username;
    }

    public Boolean verifyPassword(String pwd) {
        return password.equals(pwd);
    }

}
