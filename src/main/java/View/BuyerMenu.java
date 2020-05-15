package View;

import Model.Buyer;
//import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyerMenu extends Menu {
    Buyer buyer;

    public BuyerMenu() {
        super("Buyer Menu");
    }

    public void showPersonalInfo() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if ((matcher = getMatcher(input, "(edit (\\S+))")).find()) {
                editPersonalInfo(matcher.group(2));
            } else if (input.equalsIgnoreCase("back")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }

    }

    public void viewCart() {

    }

    public void viewCartProcess() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("show products")) {
                showProductsInBasket();
            } else if ((matcher = getMatcher(input, "(view (\\d+))")).find()) {
                viewProductInBasketProcess(Integer.parseInt(matcher.group(2)));
            } else if ((matcher = getMatcher(input, "(increase (\\d+))")).find()) {
                increaseNumberOfProductsProcess(Integer.parseInt(matcher.group(2)));
            } else if ((matcher = getMatcher(input, "(decrease (\\d+))")).find()) {
                decreaseNumberOfProductsProcess(Integer.parseInt(matcher.group(2)));
            } else if (input.equalsIgnoreCase("show total price")) {
                showTotalPriceOfProductsInBasket();
            } else if (input.equalsIgnoreCase("purchase")) {
                purchaseProcess();
            } else if (input.equalsIgnoreCase("back")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void editPersonalInfo(String field) {
        if (field.equalsIgnoreCase("name")){
            buyer.changeName(scanner.nextLine());
        } else if (field.equalsIgnoreCase("password")){
            buyer.changePassword(scanner.nextLine());
        } else if (field.equalsIgnoreCase("email")){
            buyer.changeEmail(scanner.nextLine());
        } else if (field.equalsIgnoreCase("phone")){
            buyer.changePhone(scanner.nextLine());
        } else if (field.equalsIgnoreCase("family")){
            buyer.changeFamily(scanner.nextLine());
        } else if (field.equalsIgnoreCase("username")){
            System.out.println("you can not change your username");
        } else {
            System.out.println("invalid field");
        }
    }

    public void showProductsInBasket() {

    }

    public void viewProductInBasketProcess(int productId) {

    }

    public void increaseNumberOfProductsProcess(int productId) {

    }

    public void decreaseNumberOfProductsProcess(int productId) {

    }

    public void showTotalPriceOfProductsInBasket() {

    }

    public void purchaseProcess() {

    }

    public void viewOrders() {

        viewOrdersCommandProcessor();
    }

    public void viewOrdersCommandProcessor() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if ((matcher = getMatcher(input, "(show order (\\d+))")).find()) {
                showOrder(Integer.parseInt(matcher.group(2)));
            } else if ((matcher = getMatcher(input, "(rate (\\d+) (\\d+))")).find()) {
                showRateOfProductProcess(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            } else if (input.equalsIgnoreCase("help")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }

    }

    public void showOrder(int orderId) {

    }

    public void showRateOfProductProcess(int productId, int rate) {

    }

    public void viewBalance() {

    }

    public void viewDiscountCodes() {

    }

    public void help() {

    }

    public void commandProcess() {
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("view personal info")) {
                showPersonalInfo();
            } else if (input.equalsIgnoreCase("view cart")) {
                viewCart();
            } else if (input.equalsIgnoreCase("purchase")) {
                purchaseProcess();
            } else if (input.equalsIgnoreCase("view orders")) {
                viewOrders();
            } else if (input.equalsIgnoreCase("view balance")) {
                viewBalance();
            } else if (input.equalsIgnoreCase("view discount codes")) {
                viewDiscountCodes();
            } else if (input.equalsIgnoreCase("logout")) {

            } else if (input.equalsIgnoreCase("help")) {
                help();
            } else if (input.equalsIgnoreCase("Exit")) {
                System.exit(1);
            } else if (input.equalsIgnoreCase("back")) {
                Menu.show();
            } else {
                System.out.println("invalid command");
            }
        }
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

}
