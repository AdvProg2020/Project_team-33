package View;

import Model.Buyer;
import Model.Manager;
import Model.Seller;
//import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyerMenu extends Menu {
    Buyer buyer;

    public BuyerMenu() {
        super("Buyer Menu");
    }

    public void showPersonalInfo() {

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

    public void editPersonalInfo() {

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

    }

    public void viewOrdersProcess() {

    }

    public void showOrder() {

    }

    public void showRateOfProductProcess() {

    }

    public void viewBalance() {

    }

    public void showDiscountCodes() {

    }

    public void help() {

    }

    public void commandProcess() {
        Matcher matcher;
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
            }
            els
        }
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

}
