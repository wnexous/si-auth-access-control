package types;

public class UserTypes {

    private String username, password;

    public UserTypes(String user, String pwd) {
        this.password = pwd;
        this.username = user;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
