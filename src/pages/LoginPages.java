package pages;

import java.util.ArrayList;
import java.util.Scanner;

import auth.Auth;
import controllers.PageController;
import interfaces.PageInterfaces;
import types.UserTypes;

public class LoginPages extends PageController implements PageInterfaces {

    Scanner input = new Scanner(System.in);

    @Override
    public void onSelectOption(Integer o) {

        // Retorar ao menu inicial
        if (o.equals(0)) {
            System.exit(0);
        }

        // Realizar login
        if (o.equals(1)) {
            System.out.println("Username: ");
            String username = input.nextLine();

            System.out.println("Password: ");
            String password = input.nextLine();

            UserTypes user = new UserTypes(username, password);

            Auth.signIn(user);
        }

        // Cadastrar conta
        if (o.equals(2)) {
            System.out.println("Username: ");
            String username = input.nextLine();

            System.out.println("Password: ");
            String password = input.nextLine();

            UserTypes user = new UserTypes(username, password);

            Auth.signUp(user);
        }
    }

    @Override
    public String getTitle() {
        return "Menu de login";
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();

        options.add("1. Fazer login");
        options.add("2. Criar conta");
        options.add("0. Encerrar");

        return options;
    }

}
