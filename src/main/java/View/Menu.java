package View;

import java.util.Scanner;

public abstract class Menu {
    protected String name;
    protected Menu parentMenu;
    public static Scanner scanner = new Scanner(System.in);
    public static Menu currentMenu;
    private static RegisterMenu registerMenu = new RegisterMenu();
    private static LoginMenu loginMenu = new LoginMenu();

    public Menu(String name) {
        this.name = name;
    }

    public static void show() {
        System.out.println("1.Register Menu");
        System.out.println("2.Login Menu");
        System.out.println("3.Exit");
        execute();
    }

    public static void execute() {
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("register menu")) {
            registerMenu.help();
        } else if (input.equalsIgnoreCase("login menu")) {
            loginMenu.help();
        } else if (input.equalsIgnoreCase("Exit")) {
            System.exit(1);
        }
    }

    public abstract void commandProcess();

    public abstract void help();
}
