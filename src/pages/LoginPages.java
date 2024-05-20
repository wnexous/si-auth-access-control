package pages;

import java.util.ArrayList;
import java.util.Scanner;

import controllers.PageController;
import interfaces.PageInterfaces;
import providers.Auth;
import types.UserTypes;

public class LoginPages extends PageController implements PageInterfaces {

    Scanner input = new Scanner(System.in);
    private Integer currentLoginAttempt = 0;
    private Integer maxLoginAttempt = 3;

    @Override
    public void onSelectOption(String o) {

        // Retorar ao menu inicial
        if (o.equals("0")) {
            System.exit(0);
        }
        if (canLoggin()) {
            // Realizar login
            if (o.equals("1")) {
                System.out.println("Username: ");
                String username = input.nextLine();

                System.out.println("Password: ");
                String password = input.nextLine();

                UserTypes user = new UserTypes(username, password);

                if (Auth.signIn(user)) {
                    resetLoginError();
                } else {
                    setLoginError();
                }
            }

            // Cadastrar conta
            if (o.equals("2")) {
                System.out.println("Username: ");
                String username = input.nextLine();

                System.out.println("Password: ");
                String password = input.nextLine();

                UserTypes user = new UserTypes(username, password);

                if (Auth.signUp(user)) {
                    resetLoginError();
                } else {
                    setLoginError();
                }
            }
        }

    }

    @Override
    public String getTitle() {
        String titleText = "Menu de login";
        if (!canLoggin()) {
            titleText += " - Máxima tentativa de login excedida";
        }
        return titleText;
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();

        if (canLoggin()) {
            options.add("1. Fazer login");
            options.add("2. Criar conta");
        }
        options.add("0. Encerrar");
        return options;
    }

    public boolean canLoggin() {
        return currentLoginAttempt <= maxLoginAttempt;
    }

    public void setLoginError() {
        currentLoginAttempt += 1;
    }

    public void resetLoginError() {
        currentLoginAttempt = 0;
    }

}
