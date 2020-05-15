package View;

import Model.Buyer;
import Model.Manager;
import Model.Seller;

import java.util.Scanner;

public abstract class Menu {
    protected String name;
    public static Scanner scanner = new Scanner(System.in);
    private static RegisterMenu registerMenu = new RegisterMenu();
    private static LoginMenu loginMenu = new LoginMenu();
    private static BuyerMenu buyerMenu = new BuyerMenu();

    public Menu(String name) {
        this.name = name;
    }

    public static void show() {
        if (LoginMenu.currentPerson == null) {
            System.out.println("1.Register Menu");
            System.out.println("2.Login Menu");
            System.out.println("3.Exit");
            System.err.println("Attention:\nPlease Write Your Command Not Just Number");
            executeForMainMenu();
        } else if (LoginMenu.currentPerson instanceof Seller) {
            System.out.println("1.Logout");
            System.out.println("2.Exit");
            System.err.println("Attention:\nPlease Write Your Command Not Just Number");
        } else if (LoginMenu.currentPerson instanceof Buyer) {
            buyerMenu.help();
        } else if (LoginMenu.currentPerson instanceof Manager) {

        }
    }

    public static void executeForMainMenu() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("register menu")) {
                registerMenu.help();
            } else if (input.equalsIgnoreCase("login menu")) {
                loginMenu.help();
            } else if (input.equalsIgnoreCase("Exit")) {
                System.exit(1);
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public abstract void commandProcess();

    public abstract void help();
}
