
import java.util.ArrayList;

import types.Menu;

class Menus {

    private static String defaultMenuId = Home.class.getSimpleName();;
    private static String currentMenu = defaultMenuId;

    public static ArrayList<Menu> menusArray = new ArrayList<Menu>();

    public void registerMenu(Menu m) {
        menusArray.add(m);
    }

    public Menu getCurrentMenu() {
        for (Menu menu : menusArray) {
            if (menu.getClass().getSimpleName().equals(this.currentMenu)) {
                return menu;
            }
        }
        return null;
    }

    public String getStringifyOptions() {
        String opts = "";
        Menu menu = this.getCurrentMenu();

        for (String opt : menu.getOptions()) {
            opts += opt + "\n";
        }

        return opts;
    }

    public String getMenuTitle() {
        Menu menu = this.getCurrentMenu();
        return menu.getTitle();
    }

    public void changeCurrentMenu(String menuId) {
        this.currentMenu = menuId;
    }

}