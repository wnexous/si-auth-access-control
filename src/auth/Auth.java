package auth;

import controllers.PageController;
import controllers.UserController;
import data.UsersData;
import interfaces.AuthValidatorInterfaces;
import pages.*;

public class Auth extends UsersData {

    private static Boolean isAuth = false;
    private static UserController user;
    private static UsersData userData = new UsersData();
    private static String reasonMessage;

    public Auth() {
        super();
    }

    public static void setCurrentUser(UserController u) {

        isAuth = false;
        user = null;
        reasonMessage = null;

        UserController userController = userData.getUserByUsername(u.getUsername());

        // Verifica se usuario existe na tabela
        if (userController == null) {
            reasonMessage = "nome de usuário inválido.";
            return;
        }

        // verifica se a senha confere
        if (!(userController.getPassword().equals(u.getPassword()))) {
            reasonMessage = "senha inválida.";
            return;
        }

        user = u;
        isAuth = true;
        PageController.setCurrentPage(HomePages.class);
    }

    public static Boolean isAuth() {
        return isAuth;
    }

    public static AuthValidatorInterfaces getUserIsValid() {
        return new AuthValidatorInterfaces() {

            @Override
            public boolean isValid() {
                return isAuth;
            }

            @Override
            public String message() {
                return reasonMessage;
            }
        };
    }
}
