package View;

public class CurrentMenu {
    public static Menu currentMenu;

    public static Menu getCurrent() {
        return currentMenu;
    }

    public static void setMenu(Menu menu) {
        currentMenu = menu;
    }
}
