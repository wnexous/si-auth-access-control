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

        if (o.equals("1"))
            listFiles();
        if (o.equals("2"))
            createFile();
        if (o.equals("4"))
            deleteFile();

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

        return options;
    }

    protected void listFiles() {
        System.out.println("Arquivos existentes: ");
        File[] files = FileController.getFolderFiles();

        if (files.length == 0) {
            System.out.println("* Nenhum arquivo encontrado *");
        }
        for (int i = 0; i < files.length; i++) {
            Integer fileIndex = i + 1;
            System.out.printf("\n%d) %s", fileIndex, files[i].getName());
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

        while (true) {
            System.out.println("\nInsira o número do arquivo:");
            fileIndex = input.nextInt();

            if (fileIndex > 0 && fileIndex <= files.length)
                break;

            System.out.printf("Número de arquivo invalido. Escolha algo entre 1 a %d", files.length);
        }

        File fileToDelete = files[fileIndex - 1];

        PermissionController permission = permissionsData.getPermissionFromFileByUser(
                fileToDelete,
                Auth.getCurrentUser());

        if (permission == null || (!permission.canDelete())) {
            System.out.println("permission>>" + permission);
            System.out.println("Você não tem permissão para deletar este arquivo!");
            return;
        }

        FileController.deleteFile(fileToDelete);
        this.permissionsData.deletePermission(permission);
        System.out.printf("\nArquivo %s deletado!", fileToDelete.getName());
    }

}
