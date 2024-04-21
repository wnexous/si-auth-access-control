package pages;

import java.util.ArrayList;

import controllers.PageController;
import interfaces.PageInterfaces;

public class HomePages extends PageController implements PageInterfaces {

    public void onSelectOption(String o) {

        if (o.equals("1")) {
            setCurrentPage(FilePermissionPages.class.getSimpleName());
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

        options.add("1. Acessar gerenciador de arquivos.");
        options.add("0. Sair");

        return options;
    }
}
