package utils;

import java.util.Scanner;

public abstract class InputUtils {

    static protected Scanner input = new Scanner(System.in);

    static public Integer getNumber(Integer min, Integer max, String text, String errorMessage) {
        while (true) {
            System.out.println(text);

            Integer intInput = input.nextInt();

            if (intInput <= max)
                return intInput;

            System.out.println(errorMessage);
        }
    }

    static public Integer getNumber(Integer min, Integer max, String text) {
        while (true) {
            System.out.println(text);

            Integer intInput = input.nextInt();

            if (intInput <= max)
                return intInput;

            System.out.println(String.format("Entrada inválida. Por favor escolha um número entre %d a %d", min, max));
        }
    }

}
