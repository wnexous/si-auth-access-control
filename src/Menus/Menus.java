
import java.util.ArrayList;

import interfaces.MenuInterfaces;

class Menus {

    private static String defaultMenuId = HomeMenus.class.getSimpleName();;
    private static String currentMenu = defaultMenuId;

    public static ArrayList<MenuInterfaces> menusArray = new ArrayList<MenuInterfaces>();

    public void registerMenu(MenuInterfaces m) {
        menusArray.add(m);
    }

    public MenuInterfaces getCurrentMenu() {
        for (MenuInterfaces menu : menusArray) {
            if (menu.getClass().getSimpleName().equals(this.currentMenu)) {
                return menu;
            }
        }
        return null;
    }

    public String getStringifyOptions() {
        String opts = "";
        MenuInterfaces menu = this.getCurrentMenu();

        for (String opt : menu.getOptions()) {
            opts += opt + "\n";
        }

        return opts;
    }

    public String getMenuTitle() {
        MenuInterfaces menu = this.getCurrentMenu();
        return menu.getTitle();
    }

    public void changeCurrentMenu(String menuId) {
        this.currentMenu = menuId;
    }

}