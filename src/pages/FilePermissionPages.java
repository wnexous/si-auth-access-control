package pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import auth.Auth;
import controllers.FileController;
import controllers.PageController;
import controllers.PermissionController;
import data.PermissionsData;
import interfaces.PageInterfaces;
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
        options.add("5. Executar arquivo");
        options.add("6. Voltar");

        return options;
    }

    protected void listFiles() {
        System.out.println("Arquivos existentes: ");
        File[] files = FileController.getFolderFiles();

        if (files.length == 0) {
            System.out.println("* Nenhum arquivo encontrado *");
        }

        System.out.println(String.format("| %-8s | %-15s | %-25s | %-10s | %-10s | %-10s |",
                "Numero",
                "Dono",
                "Arquivo",
                "Ler",
                "Deletar",
                "Executar"));

        for (int i = 0; i < files.length; i++) {
            Integer fileIndex = i + 1;
            File currentFile = files[i];

            PermissionController permission = permissionsData.getPermissionFromFileByUser(
                    currentFile,
                    Auth.getCurrentUser());

            // System.out.println(String.format("\n%d) %s", fileIndex,
            // currentFile.getName()));

            if (permission != null) {
                System.out.println(String.format("| %-8s | %-15s | %-25s | %-10s | %-10s | %-10s |",
                        fileIndex,
                        permission.getUser().getUsername(),
                        currentFile.getName(),
                        permission.canRead() ? "x" : "",
                        permission.canDelete() ? "x" : "",
                        permission.canExecute() ? "x" : ""));
            }
        }
    }

    protected void createFile() {
        System.out.println("Digite o nome do arquivo:");
        String fileName = input.next();
        // PermissionsData permissionsData = new PermissionsData();
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

    protected void deleteFile() {
        listFiles();

        Integer fileIndex = 0;
        File[] files = FileController.getFolderFiles();

        if (files.length == 0) {
            System.out.println("não existem arquivos para deletar");
            return;
        }

        while (true) {
            System.out.println("\nInsira o número do arquivo:");
            fileIndex = input.nextInt();

            if (fileIndex > 0 && fileIndex <= files.length)
                break;

            System.out.printf("Número de arquivo invalido. Escolha algo entre 1 a %d", files.length);
        }

        // pega o arquivo pelo index informado pelo usuario
        File fileToDelete = files[fileIndex - 1];

        // verifica aqui se usuario possui permissao para deletar o arquivo
        PermissionController permission = permissionsData.getPermissionFromFileByUser(
                fileToDelete,
                Auth.getCurrentUser());

        if (permission == null || (!permission.canDelete())) {
            System.out.println("Você não tem permissão para deletar este arquivo!");
            return;
        }

        FileController.deleteFile(fileToDelete);
        this.permissionsData.deletePermission(permission);
        System.out.printf("\nArquivo %s deletado!", fileToDelete.getName());
    }

    protected void readFile() {
        listFiles();

        Integer fileIndex = 0;
        File[] files = FileController.getFolderFiles();

        if (files.length == 0) {
            System.out.println("não existem arquivos para ler");
            return;
        }

        while (true) {
            System.out.println("\nInsira o número do arquivo:");
            fileIndex = input.nextInt();

            if (fileIndex > 0 && fileIndex <= files.length)
                break;

            System.out.printf("Número de arquivo invalido. Escolha algo entre 1 a %d", files.length);
        }

        // pega o arquivo pelo index informado pelo usuario
        File fileToRead = files[fileIndex - 1];

        // verifica aqui se usuario possui permissao para deletar o arquivo
        PermissionController permission = permissionsData.getPermissionFromFileByUser(
                fileToRead,
                Auth.getCurrentUser());

        if (permission == null || (!permission.canRead())) {
            System.out.println("Você não tem permissão para ler este arquivo!");
            return;
        }

        List<String> fileLines = FileController.readFile(fileToRead);

        if (fileLines.size() == 0) {
            System.out.println(String.format("O arquivo %s nao possui nada escrito.", fileToRead.getName()));
            return;
        }

        System.out.println("-".repeat(20));
        for (int i = 0; i < fileLines.size(); i++) {
            System.out.println(String.format("%d | %s", i + 1, fileLines.get(i)));
        }
        System.out.println("-".repeat(20));

        // FileController.deleteFile(fileToDelete);
        // this.permissionsData.deletePermission(permission);
        // System.out.printf("\nArquivo %s deletado!", fileToDelete.getName());

    }

}
