package pages;

import java.util.ArrayList;

import controllers.PageController;
import interfaces.PageInterfaces;

public class HomePages extends PageController implements PageInterfaces {

    public void onSelectOption(Number o) {

        if (o.equals(1)) {
            setCurrentPage(LoginPages.class);
        }
        if (o.equals(0)) {
            System.exit(0);
        }
    }

    @Override
    public String getTitle() {
        return "Menu inicial";
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();

        options.add("1. Realizar login");
        options.add("0. Sair");

        return options;
    }

    @Override
    public String getMenuId() {
        return this.getClass().getSimpleName();
    }

}
