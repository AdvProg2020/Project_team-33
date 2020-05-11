import View.*;

public class Main {
    public static void main(String[] args) {
        RegisterMenu newMenu = new RegisterMenu();
        ManagerMenu newMenu1 = new ManagerMenu();
        CurrentMenu.setMenu(newMenu);
        System.out.println(CurrentMenu.getCurrent().getName());
        CurrentMenu.getCurrent().commandProcess();
    }
}
