package View;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    protected String name;
    protected Menu parentMenu;
    private HashMap<Integer, Menu> subMenus = new HashMap<Integer, Menu>();
    public static Scanner scanner = new Scanner(System.in);
    public static Menu currentMenu;

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public void show() {
        for (Integer menuNum : subMenus.keySet()) {
            System.out.println(menuNum + "." + subMenus.get(menuNum).getName());
        }
        if (this.parentMenu == null) {
            System.out.println((this.subMenus.size() + 1) + ".Exit");
        } else {
            System.out.println((this.subMenus.size() + 1) + ".Back");
        }
    }

    public void execute() {
        Menu nextMenu = null;
        int input = Integer.parseInt(scanner.nextLine());
        if (input == this.subMenus.size() + 1) {
            if (parentMenu == null) {
                System.exit(1);
            } else {
                nextMenu = this.parentMenu;
            }
        } else {
            nextMenu = subMenus.get(input);
        }
        nextMenu.commandProcess();
    }

    public void setSubMenus(HashMap<Integer, Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getName() {
        return this.name;
    }

    public abstract void commandProcess();

    public abstract void help();
}
