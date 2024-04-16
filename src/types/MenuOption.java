package types;

public class MenuOption {
    private String optionText;
    private Number optionIndex;

    public MenuOption(String optText) {
        this.optionText = optText;
    }

    // public void setMenuText(String m) {
    // this.optionText = m;
    // }

    public String getMenuText() {
        return this.optionText;
    }

    public void setOptionIndex(Number o) {
    this.optionIndex = o;
    }

    public Number getOptionIndex() {
        return this.optionIndex;
    }

}
