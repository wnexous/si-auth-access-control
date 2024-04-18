package types;

import java.util.ArrayList;

public interface Menu {

    public String getMenuId();
    public void onSelectOption(Number o);
    public String getTitle();
    public ArrayList<String> getOptions();

}
