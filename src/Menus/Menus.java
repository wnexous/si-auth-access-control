
import java.util.ArrayList;

import types.MenuTypes;

class Menus {

    private static String defaultMenuId = HomeMenus.class.getSimpleName();;
    private static String currentMenu = defaultMenuId;

    public static ArrayList<MenuTypes> menusArray = new ArrayList<MenuTypes>();

    public void registerMenu(MenuTypes m) {
        menusArray.add(m);
    }

    public MenuTypes getCurrentMenu() {
        for (MenuTypes menu : menusArray) {
            if (menu.getClass().getSimpleName().equals(this.currentMenu)) {
                return menu;
            }
        }
        return null;
    }

    public String getStringifyOptions() {
        String opts = "";
        MenuTypes menu = this.getCurrentMenu();

        for (String opt : menu.getOptions()) {
            opts += opt + "\n";
        }

        return opts;
    }

    public String getMenuTitle() {
        MenuTypes menu = this.getCurrentMenu();
        return menu.getTitle();
    }

    public void changeCurrentMenu(String menuId) {
        this.currentMenu = menuId;
    }

}