package types;

import java.util.ArrayList;

public class Menu {

    // Menu encapsulado
    private String menuName;
    private String menuTitle;
    private static ArrayList<MenuOption> menuOpt = new ArrayList<MenuOption>();

    public void registerMenuOption(MenuOption m) {
        m.setOptionIndex(menuOpt.size());
        menuOpt.add(m);
    }

    public Menu(String menuName, String menuTitle) {
        this.menuName = menuName;
        this.menuTitle = menuTitle;
    }

    // accessors
    // public void setMenuText(String text) {
    // this.menuText = text;
    // }

    // public void setMenuName(String name) {
    // this.menuName = name;
    // }

    public String getMenuText() {
        return this.menuTitle;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void onSelectOption(Number opt) {
        System.out.println("Opcao selecionada.");
    }

}
