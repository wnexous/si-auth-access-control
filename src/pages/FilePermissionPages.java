package pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controllers.FileController;
import controllers.PageController;
import controllers.PermissionController;
import data.PermissionsData;
import interfaces.PageInterfaces;
import providers.Auth;
import types.UserTypes;

public class FilePermissionPages extends PageController implements PageInterfaces {

    protected Scanner input = new Scanner(System.in);
    protected PermissionsData permissionsData = new PermissionsData();

    @Override
    public void onSelectOption(String o) {

        switch (o) {
            case "1":
                listFiles();
                break;
            case "2":
                createFile();
                break;
            case "3":
                readFile();
                break;
            case "4":
                deleteFile();
                break;
            case "5":
                executeFile();
                break;
            case "6":
                setCurrentPage(HomePages.class.getSimpleName());
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

    }

    @Override
    public String getTitle() {
        return "Página gerenciadora de aquivos";
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<String>();

        options.add("1. Listar arquivos");
        options.add("2. Criar arquivo");
        options.add("3. Ler arquivo");
        options.add("4. Excluir arquivo");
        options.add("5. Executar arquivo ");
        options.add("6. Voltar");

        return options;
    }

    protected void listFiles() {
        System.out.println("Arquivos existentes: ");
        // File[] files = FileController.getFolderFiles();

        List<PermissionController> permissionsFromUser = permissionsData.getPermissionsFromUser(Auth.getCurrentUser());

        if (permissionsFromUser.size() == 0) {
            System.out.println("* Nenhum arquivo encontrado *");
        }

        System.out.println(String.format("| %-8s | %-15s | %-25s | %-10s | %-10s | %-10s |",
                "Numero",
                "Dono",
                "Arquivo",
                "Ler",
                "Deletar",
                "Executar"));

        for (int i = 0; i < permissionsFromUser.size(); i++) {
            Integer fileIndex = i + 1;

            PermissionController permission = permissionsFromUser.get(i);
            // File currentFile = ;

            // System.out.println(String.format("\n%d) %s", fileIndex,
            // currentFile.getName()));

            if (permission != null && permission.getUser() != null){
                System.out.println(String.format("| %-8s | %-15s | %-25s | %-10s | %-10s | %-10s |",
                        fileIndex,
                        permission.getUser().getUsername(),
                        permission.getFilename(),
                        permission.canRead() ? "x" : "",
                        permission.canDelete() ? "x" : "",
                        permission.canExecute() ? "x" : ""));
            }
        }
    }

    protected void createFile() {
        System.out.println("Digite o nome do arquivo:");
        String fileName = input.next();
        try {
            FileController.createFile(fileName);

            PermissionController filePermissions = new PermissionController(
                    Auth.getCurrentUser(),
                    fileName,
                    true,
                    true,
                    true);

            permissionsData.createPermission(filePermissions);

        } catch (Exception e) {
            System.out.println("erro ao criar arquivo: " + e.getMessage());
        }
    }

    protected PermissionController getUserFile(){
        List<PermissionController> permissionsFromUser = permissionsData.getPermissionsFromUser(Auth.getCurrentUser());
        Integer fileIndex = 0;

        if (permissionsFromUser.size() == 0) {
            System.out.println("* Nenhum arquivo encontrado *");
        }

        if (permissionsFromUser.size() == 0) {
            System.out.println("nenhum arquivo encontrado");
            return null;
        }

        while (true) {
            System.out.println("\nInsira o número do arquivo:");
            fileIndex = input.nextInt();

            if (fileIndex > 0 && fileIndex <= permissionsFromUser.size())
                break;

            System.out.printf("Número de arquivo invalido. Escolha algo entre 1 a %d", permissionsFromUser.size());
        }

        return   permissionsFromUser.get(fileIndex - 1);
    }

    protected void deleteFile() {
        listFiles();

        // verifica aqui se usuario possui permissao para deletar o arquivo
        PermissionController permission = getUserFile();

        File fileToDelete = FileController.getFileByName(permission.getFilename());

        if (permission == null || (!permission.canDelete())) {
            System.out.println("Você não tem permissão para deletar este arquivo!");
            return;
        }

        FileController.deleteFile(fileToDelete);
        this.permissionsData.deletePermission(permission);
        System.out.printf("\nArquivo %s deletado!", fileToDelete.getName());
    }

    protected void executeFile() {
        listFiles();

        // verifica aqui se usuario possui permissao para deletar o arquivo
        PermissionController permission = getUserFile();

        File fileSelected = FileController.getFileByName(permission.getFilename());

        if (permission == null || (!permission.canExecute())) {
            System.out.println("Você não tem permissão para executar este arquivo!");
            return;
        }

        System.out.printf("\nArquivo %s executado!", fileSelected.getName());
    }

    protected void readFile() {
        listFiles();
        // verifica aqui se usuario possui permissao para deletar o arquivo
        PermissionController permission = getUserFile();

        File fileSelected = FileController.getFileByName(permission.getFilename());
        if (permission == null || (!permission.canRead())) {
            System.out.println("Você não tem permissão para ler este arquivo!");
            return;
        }

        List<String> fileLines = FileController.readFile(fileSelected);

        if (fileLines.size() == 0) {
            System.out.println(String.format("O arquivo %s nao possui nada escrito.", fileSelected.getName()));
            return;
        }

        System.out.println("-".repeat(20));
        for (int i = 0; i < fileLines.size(); i++) {
            System.out.println(String.format("%d | %s", i + 1, fileLines.get(i)));
        }
        System.out.println("-".repeat(20));

    }

}
