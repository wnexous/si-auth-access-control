import java.util.Scanner;

import types.Menu;

public class Main {

    public static Scanner keyboard;

    public static void main(String[] args) {
        keyboard = new Scanner(System.in);

        // Registra os menus
        new RegistrationMenu();

        // obtem-se o gerenciador de menus e o menu atual
        Menus menus = new Menus();

        while (true) {

            Menu menu = menus.getCurrentMenu();

            if (menu == null)
                throw new Error("tentou acessar um menu inexistente!");

            // mostra o titulo do menu
            System.out.println(menu.getTitle());

            // mostra as opcoes do menu
            System.out.println(menus.getStringifyOptions());

            // requere que o usuario selecione uma opcao
            System.out.printf("\nSelecione uma opção: ");

            // captura a opcao selecionada
            Number opt = keyboard.nextInt();

            // inputa a opcao selecionada na callback do menu
            menu.onSelectOption(opt);

        }

    }
}