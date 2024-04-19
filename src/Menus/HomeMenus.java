import java.util.ArrayList;
import interfaces.MenuInterfaces;

public class HomeMenus extends Menus implements MenuInterfaces {

    public void onSelectOption(Number o) {

        if (o.equals(1)) {
            this.changeCurrentMenu(LoginMenus.class.getSimpleName());
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
