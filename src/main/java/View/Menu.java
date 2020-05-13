package View;

import Model.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu {
    private String name;
    protected Menu parentMenu;
    private HashMap<Integer, Menu> subMenus = new HashMap<Integer, Menu>();
    public static Scanner scanner = new Scanner(System.in);

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }

    public void show() {
        for (Integer menuNum : subMenus.keySet()) {
            System.out.println(menuNum + "." + subMenus.get(menuNum).getName());
        }
        if (this.parentMenu == null) {
            System.out.println((this.subMenus.size() + 1) + ". Exit");
        } else {
            System.out.println((this.subMenus.size() + 1) + ". Back");
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
        }

    }

    public String getName() {
        return this.name;
    }
}
