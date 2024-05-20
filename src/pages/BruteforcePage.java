package pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import controllers.HashController;
import controllers.PageController;
import data.UsersData;
import interfaces.PageInterfaces;
import types.UserTypes;

public class BruteforcePage extends PageController implements PageInterfaces {

    protected UsersData usersData = new UsersData();
    protected Scanner input = new Scanner(System.in);
    public Date timer;

    public void onSelectOption(String o) {

        if (o.equals("1")) {
            UserTypes selectedUser = getSelectedUser();
            Integer passwordSize = getPasswordSize(128);
            startButeforceByRecursion(selectedUser, passwordSize);
        }
        if (o.equals("2")) {
            UserTypes selectedUser = getSelectedUser();
            Integer passwordSize = getPasswordSize(128);
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

    public void startTimer() {
        this.timer = new Date();
    }

    public void endTimer() {
        Date currentDate = new Date();

        if (this.timer != null) {
            Long diffTime = (currentDate.getTime() - this.timer.getTime());
            Double secondsTime = (diffTime.doubleValue() / 1000);
            System.out.println("Tempo corrido: " + secondsTime + " segundos");
        }
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

    public Integer getPasswordSize(Integer maxSize) {

        while (true) {
            System.out.println("Informe o tamanho da senha: ");

            Integer passSize = input.nextInt();

            if (passSize <= maxSize) {
                return passSize;
            }

            System.out.println("Tamanho de senha inválido. TAMANHO MAXIMO = " + maxSize);
        }
    }

    public String getLetterFromNumber(int n) {
        return String.valueOf((char) ((int) n + 33));
    }

    public String getRandomLetter() {
        return getLetterFromNumber((int) Math.round(Math.random() * 94));
    }

    public void startButeforceByKick(UserTypes user, Integer passSize) {
        startTimer();
        System.out.println("Iniciando bruteforce por aleatoriedade. Aguarde enquanto testamos as senhas...");
        String[] passArr = new String[passSize];

        while (true) {
            for (int i = 0; i < passArr.length; i++) {
                passArr[i] = getRandomLetter();
            }
            if (testPassword(passArr, user.getPassword())) {
                showFinddedPassword(passArr);
                endTimer();
                return;
            }
        }
    }

    public void startButeforceByRecursion(UserTypes user, Integer passSize) {
        startTimer();
        System.out.println("Iniciando bruteforce por recursividade. Aguarde enquanto testamos as senhas...");
        String[] passArr = new String[passSize];
        String[] recursivePassword = recursiveCombination(passSize - 1, 0, passArr, user.getPassword());
        showFinddedPassword(recursivePassword);
        endTimer();
    }

    public void showFinddedPassword(String[] pwd) {
        System.out.println("-".repeat(60));
        System.out.println("SENHA ENCONTRADA: " + stringArrayToString(pwd));
        System.out.println("-".repeat(60));
    }

    private String[] recursiveCombination(Integer maxLength, Integer currentLength, String[] pwd, String hashPwd) {

        for (int i = 0; i < 94; i++) {
            pwd[currentLength] = getLetterFromNumber(i);

            if (currentLength < maxLength)
                recursiveCombination(maxLength, currentLength + 1, pwd, hashPwd);

            if (testPassword(pwd, hashPwd)) {
                return pwd;
            }
        }
        return null;
    }

    public boolean testPassword(String[] pwd, String hashPwd) {
        String pwdString = stringArrayToString(pwd);
        // System.out.println("senha testada: " + pwdString);
        return HashController.verify(hashPwd, pwdString);
    }

    public String stringArrayToString(String[] strArray) {
        return String.join("", strArray);
    }

    // public Integer[] sumPass(Integer[] pwd) {
    // // for (int i = 0; i < pwd.length; i++) {
    // // pwd[i] =

    // // }

    // }
}