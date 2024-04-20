package controllers;

import java.util.ArrayList;
import interfaces.PageInterfaces;
import pages.HomePages;

public class PageController {

    private static String defaultMenuId = HomePages.class.getSimpleName();
    private static String currentPage = defaultMenuId;

    public static ArrayList<PageInterfaces> pagesArray = new ArrayList<PageInterfaces>();

    public void registerPage(PageInterfaces m) {
        pagesArray.add(m);
    }

    public static void setCurrentPage(Object menu) {
        currentPage = menu.getClass().getSimpleName();
    }

    public PageInterfaces getCurrentPage() {

        System.out.println("currentPage " + currentPage);
        for (PageInterfaces page : pagesArray) {
            if (page.getClass().getSimpleName().equals(currentPage)) {
                return page;
            }
        }
        return null;
    }

    public String getStringifyOptions() {
        String opts = "";
        PageInterfaces page = this.getCurrentPage();

        for (String opt : page.getOptions()) {
            opts += opt + "\n";
        }

        return opts;
    }

    public String getPageTitle() {
        PageInterfaces page = this.getCurrentPage();
        return page.getTitle();
    }

    // public void changeCurrentMenu(String menuId) {
    // currentMenu = menuId;
    // }

}