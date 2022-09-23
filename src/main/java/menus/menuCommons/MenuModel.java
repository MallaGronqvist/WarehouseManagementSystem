package menus.menuCommons;


import java.util.List;

public abstract class MenuModel {
    protected List<String> menuOptions;

    public List<String> getMenuOptions() { return menuOptions; }

    protected abstract void processOption(int selectedOption) throws IndexOutOfBoundsException;
}
