package pages;

import java.util.ArrayList;
import java.util.Scanner;

import auth.Auth;
import controllers.PageController;
import controllers.UserController;
import interfaces.PageInterfaces;

public class LoginPages extends PageController implements PageInterfaces {

    Scanner input = new Scanner(System.in);

    @Override
    public void onSelectOption(Number o) {

        // Retorar ao menu inicial
        if (o.equals(0)) {
            PageController.setCurrentPage(HomePages.class);
        }

        // Realizar login
        if (o.equals(1)) {
            System.out.println("Username: ");
            String username = input.next();

            System.out.println("Password: ");
            String password = input.next();

            UserController user = new UserController(username, password);

            Auth.setCurrentUser(user);
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
        options.add("0. Voltar");

        return options;
    }

    @Override
    public String getMenuId() {
        return this.getClass().getSimpleName();
    }
}
