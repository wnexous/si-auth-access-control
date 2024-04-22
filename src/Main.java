
/**
 * --------------------------------------
 * CODIGO DESENVOLVIDO POR:
 * - Andre Ruan Cesar Dal Negro
 * - Camila Vieira De Oliveira
 * --------------------------------------
 */

import java.util.Scanner;
import auth.Auth;
import controllers.PageController;
import interfaces.PageInterfaces;
import pages.LoginPages;
import pages.RegistrationPages;

public class Main {

    public static Scanner keyboard;

    public static void main(String[] args) {

       

        keyboard = new Scanner(System.in);

        // Registra as paginas
        new RegistrationPages();

        // obtem-se o gerenciador de menus e o menu atual
        PageController pages = new PageController();

        while (true) {
            System.out.println("\n".repeat(1));

            if (!Auth.getUserIsValid().isValid()) {

                String reasonMessage = Auth.getUserIsValid().message();
                PageController.setCurrentPage(LoginPages.class.getSimpleName());

                if (reasonMessage != null) {
                    System.out.println(">> " + reasonMessage);
                }
            }

            PageInterfaces menu = pages.getCurrentPage();

            if (menu == null)
                throw new Error("tentou acessar um menu inexistente!");

            // mostra o titulo do menu
            System.out.println(menu.getTitle());

            // mostra as opcoes do menu
            System.out.println(pages.getStringifyOptions());

            // requere que o usuario selecione uma opcao
            System.out.printf("\nSelecione uma opção: ");

            // captura a opcao selecionada
            String opt = keyboard.nextLine();

            // inputa a opcao selecionada na callback do menu
            menu.onSelectOption(opt);
        }
    }
}