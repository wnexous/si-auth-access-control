package interfaces;

import java.util.ArrayList;

public interface MenuInterfaces {

    public String getMenuId();
    public void onSelectOption(Number o);
    public String getTitle();
    public ArrayList<String> getOptions();

}
