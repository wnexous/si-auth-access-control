import java.util.Date;
import java.util.Scanner;
import controllers.DataController;
import types.MenuTypes;

public class Main {

    public static Scanner keyboard;

    public static void main(String[] args) {
        DataController dc = new DataController("UsersData.txt");

        String[] newUser = new String[] { "andre3", new String().format("%d", new Date().getTime()) };
        // dc.appendRow(newUser);
        // dc.removeRowByIndex(0);
        // System.out.println(dc.findIndexFromItemByColumn("andre2", "nome"));

        keyboard = new Scanner(System.in);

        // Registra os menus
        new RegistrationMenu();

        // obtem-se o gerenciador de menus e o menu atual
        Menus menus = new Menus();

        while (true) {
            MenuTypes menu = menus.getCurrentMenu();

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