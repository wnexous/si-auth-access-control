package auth;

import controllers.UserController;

public class Auth {

    private UserController user;

    public Auth(UserController user) {
        this.user = user;
    }

    public ValidatorI getUserIsValid() {

        return new ValidatorI() {

            @Override
            public boolean isValid() {
                return false;
            }

            @Override
            public String message() {
                return "usuario invalido";
            }
        };
    }

    public static interface ValidatorI {
        public boolean isValid();
        public String message();
    }

}
