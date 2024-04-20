package auth;

import java.util.ArrayList;
import java.util.List;

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

    public static void signIn(UserController u) {

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
        PageController.setCurrentPage(HomePages.class.getSimpleName());
    }

    public static void signUp(UserController u) {

        isAuth = false;
        user = null;
        reasonMessage = null;

        String[] invalidChars = new String[] { ";", "\\", " " };

        UserController userController = userData.getUserByUsername(u.getUsername());

        // Verifica se usuario existe na tabela
        if (userController != null) {
            reasonMessage = String.format("usuário %s já existe!", u.getUsername());
            return;
        }

        // verifica se a senha confere
        if (u.getPassword().length() < 6) {
            reasonMessage = "senha muito pequena! mínimo de 6 caracteres.";
            return;
        }

        // verifica se a senha possui um minimo de caracteres
        if (u.getPassword().length() < 6) {
            reasonMessage = "senha muito pequena! mínimo de 6 caracteres.";
            return;
        }

        // verifica se a senha confere
        if (u.getUsername().length() < 3) {
            reasonMessage = "nome de usuário muito pequeno! mínimo de 3 caracteres.";
            return;
        }

        for (String passChars : u.getPassword().split("")) {
            for (String invalidCharsList : invalidChars) {
                if (passChars.equals(invalidCharsList)) {
                    reasonMessage = String.format("O caracter %s é não pode ser usado em sua senha.", invalidCharsList);
                    return;
                }
            }
        }
        for (String userChars : u.getUsername().split("")) {
            for (String invalidCharsList : invalidChars) {
                if (userChars.equals(invalidCharsList)) {
                    reasonMessage = String.format("O caracter '%s' é não pode ser usado em seu nome de usuário.",
                            invalidCharsList);
                    return;
                }
            }
        }

        System.out.println("usuario criado com sucesso");
        userData.createUser(u);

        user = u;
        isAuth = true;
        PageController.setCurrentPage(HomePages.class.getSimpleName());
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
