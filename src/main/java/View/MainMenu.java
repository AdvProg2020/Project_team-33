package View;

import java.util.HashMap;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Main Menu", null);
        HashMap<Integer, Menu> subMenus = new HashMap<Integer, Menu>();
        subMenus.put(1, new RegisterMenu(this));
        subMenus.put(2, new LoginMenu(this));
        this.setSubMenus(subMenus);
    }

    public void commandProcess() {

    }

    public void help() {

    }
}
