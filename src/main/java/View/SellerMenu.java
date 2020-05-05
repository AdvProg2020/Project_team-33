package View;

import Model.Product;
import Model.Seller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerMenu extends Menu {
    Seller seller;
    Product product;

    public SellerMenu(Menu previousMenu) {
        super("Seller Menu", previousMenu);
    }


    public void viewPersonalInfo() {

    }

    public void editPersonalInfoProcess() {

    }

    public void viewCompanyInformation() {

    }

    public void viewSalesHistory() {

    }

    public void manageProducts() {

    }

    public void viewProductProcess() {

    }

    public void viewBuyerProcess() {

    }

    public void editProductProcess() {

    }

    public void addProductProcess() {

    }

    public void removeProductProcess(int productId) {

    }

    public void showCategories() {

    }

    public void viewOffs() {

    }

    public void viewOff() {

    }

    public void editOffProcess() {

    }

    public void addOffProcess() {

    }

    public void viewBalance() {

    }

    public void help() {

    }

    public void commandProcess() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("view personal info")) {
                viewPersonalInfo();
            } else if (input.equalsIgnoreCase("view company information")) {
                viewCompanyInformation();
            } else if (input.equalsIgnoreCase("view sales history")) {
                viewSalesHistory();
            } else if (input.equalsIgnoreCase("manage products")) {
                manageProducts();
            } else if (input.equalsIgnoreCase("add product")) {
                addProductProcess();
            } else if ((matcher = getMatcher(input,"(remove product (\\d+))")).find()) {
                removeProductProcess(Integer.parseInt(matcher.group(1)));
            } else if (input.equalsIgnoreCase("show categories")) {
                showCategories();
            } else if (input.equalsIgnoreCase("view offs")) {
                viewOffs();
            } else if (input.equalsIgnoreCase("help")) {
                help();
            }
        }
    }

    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
