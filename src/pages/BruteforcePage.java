package pages;

import java.util.ArrayList;
import java.util.Scanner;
import controllers.HashController;
import controllers.PageController;
import data.UsersData;
import interfaces.PageInterfaces;
import types.UserTypes;
import utils.InputUtils;
import utils.StringUtils;
import utils.TimerUtils;

public class BruteforcePage extends PageController implements PageInterfaces {

    protected UsersData usersData = new UsersData();
    protected Scanner input = new Scanner(System.in);

    public void onSelectOption(String o) {

        if (o.equals("1")) {
            UserTypes selectedUser = getSelectedUser();
            Integer passwordSize = InputUtils.getNumber(3, 128, "Insira o tamanho da senha:");
            startButeforceByRecursion(selectedUser, passwordSize);
        }
        if (o.equals("2")) {
            UserTypes selectedUser = getSelectedUser();
            Integer passwordSize = InputUtils.getNumber(3, 128, "Insira o tamanho da senha:");
            startButeforceByKick(selectedUser, passwordSize);
        }
        if (o.equals("0")) {
            System.exit(0);
        }
    }

    @Override
    public String getTitle() {
        return "Menu inicial";
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<String>();

        options.add("1. Teste por recursividade.");
        options.add("2. Teste por aleatoriedade.");
        options.add("0. Sair");

        return options;
    }

    public UserTypes getSelectedUser() {
        ArrayList<UserTypes> usersList = usersData.getAllUsers();

        while (true) {
            System.out.println("Informe qual usuário voce deseja quebrar a senha: ");

            for (int i = 0; i < usersList.size(); i++) {
                UserTypes currentUser = usersList.get(i);
                System.out.printf("%d. %s \n", i + 1, currentUser.getUsername());
            }

            try {
                Integer selectedOption = input.nextInt() - 1;
                return usersList.get(selectedOption);

            } catch (Exception e) {
                System.out.println("opcao invalida");
            }

        }
    }

    public void startButeforceByKick(UserTypes user, Integer passSize) {
        TimerUtils.startTimer();
        System.out.println("Iniciando bruteforce por aleatoriedade. Aguarde enquanto testamos as senhas...");
        String[] passArr = new String[passSize];

        while (true) {
            for (int i = 0; i < passArr.length; i++)
                passArr[i] = StringUtils.getRandomLetter();

            if (testPassword(passArr, user.getPassword())) {
                showFinddedPassword(passArr);
                TimerUtils.endTimer();
                return;
            }
        }
    }

    public void startButeforceByRecursion(UserTypes user, Integer passSize) {
        TimerUtils.startTimer();
        System.out.println("Iniciando bruteforce por recursividade. Aguarde enquanto testamos as senhas...");
        String[] passArr = new String[passSize];
        String[] recursivePassword = recursiveCombination(passSize - 1, 0, passArr, user.getPassword());
        showFinddedPassword(recursivePassword);
        TimerUtils.endTimer();
    }

    public void showFinddedPassword(String[] pwd) {
        System.out.println("-".repeat(60));
        System.out.println("SENHA ENCONTRADA: " + StringUtils.stringArrayToString(pwd));
        System.out.println("-".repeat(60));
    }

    private String[] recursiveCombination(Integer maxLength, Integer currentLength, String[] pwd, String hashPwd) {

        for (int i = 0; i < 94; i++) {
            pwd[currentLength] = StringUtils.getLetterFromNumber(i);

            if (currentLength < maxLength)
                recursiveCombination(maxLength, currentLength + 1, pwd, hashPwd);

            if (testPassword(pwd, hashPwd))
                return pwd;

        }
        return null;
    }

    public boolean testPassword(String[] pwd, String hashPwd) {
        return HashController.verify(hashPwd, StringUtils.stringArrayToString(pwd));
    }

}