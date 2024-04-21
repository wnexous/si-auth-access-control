package pages;

import java.io.File;
import java.util.ArrayList;

import controllers.FileController;
import controllers.PageController;
import interfaces.PageInterfaces;

public class FilePermissionPages extends PageController implements PageInterfaces {

    @Override
    public void onSelectOption(Integer o) {

        if (o == 1)
            listFiles();

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

        for (int i = 0; i < files.length; i++) {
            Integer fileIndex = i + 1;
            System.out.printf("\n%d) %s", fileIndex, files[i].getName());
        }

    }
}
