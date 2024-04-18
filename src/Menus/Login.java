import java.util.ArrayList;

import types.Menu;

public class Login extends Menus implements Menu {

    @Override
    public void onSelectOption(Number o) {
        if (o.equals(0)) {
            changeCurrentMenu(Home.class.getSimpleName());
        }
    }

    @Override
    public String getTitle() {
        return "Menu de login";
    }

    @Override
    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();

        options.add("1. Fazer login");
        options.add("2. Criar conta");
        options.add("0. Voltar");

        return options;
    }

    @Override
    public String getMenuId() {
        return this.getClass().getSimpleName();
    }

}
