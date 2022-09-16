package menuCommons;

import java.util.List;

public interface MenuModel {

    List<String> getMenuOptions();
    void processOption(int selectedOption) throws IndexOutOfBoundsException;
}
