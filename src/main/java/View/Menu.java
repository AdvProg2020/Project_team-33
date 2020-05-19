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
    private static ManagerMenu managerMenu = new ManagerMenu();
    private static SellerMenu sellerMenu = new SellerMenu();
    private static BuyerMenu buyerMenu = new BuyerMenu();
    private static ProductsMenu productsMenu = new ProductsMenu();
    private static OffMenu offMenu = new OffMenu();

    public Menu(String name) {
        this.name = name;
    }

    public static void show() {
        if (LoginMenu.currentPerson == null) {
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Products Page");
            System.out.println("4.Auctions");
            System.out.println("5.Exit");
            System.err.println("Attention:\nPlease Write Your Command Number");
            executeMainMenuForNullPerson();
        } else {
            System.out.println("1.User Area");
            System.out.println("2.Product Page");
            System.out.println("3.Auctions");
            System.out.println("4.Logout");
            System.out.println("5.Exit");
            System.err.println("Attention:\nPlease Write Your Command Number");
            if (LoginMenu.currentPerson instanceof Seller) {
                executeMainMenuForSeller();
            } else if (LoginMenu.currentPerson instanceof Buyer) {
                executeMainMenuForBuyer();
            } else if (LoginMenu.currentPerson instanceof Manager) {
                executeMainMenuForManager();
            }
        }

    }

    public static void executeMainMenuForNullPerson() {
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    registerMenu.help();
                    break;
                case "2":
                    loginMenu.help();
                    break;
                case "3":
                    productsMenu.help();
                    break;
                case "4":
                    offMenu.help();
                    break;
                case "5":
                    System.exit(1);
                default:
                    System.out.println("invalid command");
                    break;
            }
        }
    }

    public static void executeMainMenuForSeller() {
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    sellerMenu.help();
                    break;
                case "2":
                    productsMenu.help();
                    break;
                case "3":
                    offMenu.help();
                    break;
                case "4":
                    System.out.println("you log outed successfully");
                    LoginMenu.currentPerson = null;
                    show();
                    break;
                case "5":
                    System.exit(1);
                default:
                    System.out.println("invalid command");
                    break;
            }
        }
    }

    public static void executeMainMenuForBuyer() {
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    buyerMenu.help();
                    break;
                case "2":
                    productsMenu.help();
                    break;
                case "3":
                    offMenu.help();
                    break;
                case "4":
                    System.out.println("you log outed successfully");
                    LoginMenu.currentPerson = null;
                    show();
                    break;
                case "5":
                    System.exit(1);
                default:
                    System.out.println("invalid command");
                    break;
            }
        }
    }

    public static void executeMainMenuForManager() {
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    managerMenu.help();
                    break;
                case "2":
                    productsMenu.help();
                    break;
                case "3":
                    offMenu.help();
                    break;
                case "4":
                    System.out.println("you log outed successfully");
                    LoginMenu.currentPerson = null;
                    show();
                    break;
                case "5":
                    System.exit(1);
                default:
                    System.out.println("invalid command");
                    break;
            }
        }
    }

    public abstract void commandProcess();

    public abstract void help();

}
