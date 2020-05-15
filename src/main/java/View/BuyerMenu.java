package View;

import Model.BuyLog;
import Model.Buyer;
import Model.Product;
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

    public void viewCartProcess() {
        System.out.println("1.show products");
        System.out.println("2.view [productId]");
        System.out.println("3.increase [productId]");
        System.out.println("4.decrease [productId]");
        System.out.println("5.show total price");
        System.out.println("6.purchase");
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
        if (buyer.getCart().getCart().size() < 1){
            System.out.println("There are not any products in your cart");
        }else {
            for (Product product : buyer.getCart().getCart().keySet()) {
                System.out.println(product.getName());
            }
        }
    }

    public void viewProductInBasketProcess(int productId) {

    }

    public void increaseNumberOfProductsProcess(int productId) {

    }

    public void decreaseNumberOfProductsProcess(int productId) {

    }

    public void showTotalPriceOfProductsInBasket() {
        int totalPrice = 0;
        for (Product product : buyer.getCart().getCart().keySet()) {
            totalPrice += product.getPrice();
        }
        System.out.println("The total price is: " + totalPrice);
    }

    public void purchaseProcess() {

    }

    public void viewOrders() {
        //TODO show logs
        int logsNo = 1;
        for (BuyLog buyLog : buyer.getLog()) {
            System.out.println("buyLog number " + logsNo);
            for (Product product : buyLog.getProducts().keySet()) {
                product.getName();
            }
            logsNo++;
        }
        viewOrdersCommandProcessor();
    }

    public void viewOrdersCommandProcessor() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if ((matcher = getMatcher(input, "(show order (\\d+))")).find()) {
                showOrder(Integer.parseInt(matcher.group(2)));
            } else if ((matcher = getMatcher(input, "(rate (\\d+) (\\d+))")).find()) {
                rateProductProcess(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            } else if (input.equalsIgnoreCase("back")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void showOrder(int orderId) {

    }

    public void rateProductProcess(int productId, int rate) {

    }

    public void viewBalance() {
        //TODO balance
    }

    public void viewDiscountCodes() {
        for (String discountCode : buyer.getDiscountCode()) {
            System.out.println(discountCode);
        }
    }

    public void help() {
        System.out.println("Enter your command:");
        System.out.println("1.view personal info");
        System.out.println("\tedit [field]");
        System.out.println("2.view cart");
        System.out.println("\tshow products\n\t" +
                "view [productId]\n\t" + "increase [productId]\n\t" +
                "decrease [productId]\n\t" + "show total price\n\t" +
                "purchase");
        System.out.println("3.purchase");
        System.out.println("4.view orders");
        System.out.println("\tshow order [orderId]\n\t" +
                "rate [productId] [1-5]");
        System.out.println("5.view balance");
        System.out.println("6.view discount codes");
        System.out.println("7.logout");
        System.out.println("8.help");
        System.out.println("9.back");
        System.out.println("10.exit");
        commandProcess();
    }

    public void commandProcess() {
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("view personal info")) {
                showPersonalInfo();
            } else if (input.equalsIgnoreCase("view cart")) {
                viewCartProcess();
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
            } else if (input.equalsIgnoreCase("back")) {
                Menu.show();
            } else if (input.equalsIgnoreCase("exit")) {
                System.exit(1);
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
