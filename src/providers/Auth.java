package providers;

import controllers.HashController;
import controllers.PageController;
import data.UsersData;
import interfaces.AuthValidatorInterfaces;
import pages.*;
import types.UserTypes;

public class Auth extends UsersData {

    private static Boolean isAuth = false;
    private static UserTypes user;
    private static UsersData userData = new UsersData();
    private static String reasonMessage;

    public Auth() {
        super();
    }

    public static UserTypes getCurrentUser() {
        return user;
    }

    public static void signIn(UserTypes u) {

        isAuth = false;
        user = null;
        reasonMessage = null;

        UserTypes userController = userData.getUserByUsername(u.getUsername());

        // Verifica se usuario existe na tabela
        if (userController == null) {
            reasonMessage = "nome de usuário inválido.";
            return;
        }

        // // verifica se a senha confere
        // if (!(userController.getPassword().equals(u.getPassword()))) {
        //     reasonMessage = "senha inválida.";
        //     return;
        // }

        // verifica se a senha confere
        if (!(HashController.verify(userController.getPassword(), u.getPassword()))) {
            reasonMessage = "senha inválida.";
            return;
        }

        user = u;
        isAuth = true;

        System.out.println(String.format("usuario '%s' autenticado com sucesso", u.getUsername()));

        PageController.setCurrentPage(HomePages.class.getSimpleName());
    }

    public static void signUp(UserTypes u) {

        isAuth = false;
        user = null;
        reasonMessage = null;

        String[] invalidChars = new String[] { ";", "\\", " " };

        UserTypes userController = userData.getUserByUsername(u.getUsername());

        // Verifica se usuario existe na tabela
        if (userController != null) {
            reasonMessage = String.format("usuário %s já existe!", u.getUsername());
            return;
        }

        // verifica se a senha possui um minimo de caracteres
        if (u.getPassword().length() < 3) {
            reasonMessage = "senha muito pequena! mínimo de 6 caracteres.";
            return;
        }

        // verifica se o username possui um minimo de caracteres
        if (u.getUsername().length() < 2) {
            reasonMessage = "nome de usuário muito pequeno! mínimo de 3 caracteres.";
            return;
        }

        for (String passChars : u.getPassword().split("")) {
            for (String invalidCharsList : invalidChars) {
                if (passChars.equals(invalidCharsList)) {
                    reasonMessage = String.format("O caracter '%s' é não pode ser usado em sua senha.",
                            invalidCharsList);
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

        userData.createUser(u);

        user = u;
        isAuth = true;

        System.out.println(String.format("usuario '%s' criado e autenticado com sucesso", u.getUsername()));

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
