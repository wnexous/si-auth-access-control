package controllers;

public class UserController {

    private String username, password;

    public UserController(String user, String pwd) {
        this.password = pwd;
        this.username = user;
    }

    // public UserController(String user) {
    //     this.username = user;
    // }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean verifyPassword(String pwd) {
        return this.password.equals(pwd);
    }

}
