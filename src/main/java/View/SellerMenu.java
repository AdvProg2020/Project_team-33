package View;

import Model.BuyLog;
import Model.Product;
import Model.SellLog;
import Model.Seller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerMenu extends Menu {
    Seller seller;
    Product product;

    public SellerMenu() {
        super("Seller Menu");
    }


    public void viewPersonalInfo() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if ((matcher = getMatcher(input, "(edit (\\S+))")).find()) {
                editPersonalInfoProcess(matcher.group(2));
            } else if (input.equalsIgnoreCase("back")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void editPersonalInfoProcess(String field) {
        if (field.equalsIgnoreCase("email")){
            seller.changeEmail(scanner.nextLine());
        } else if (field.equalsIgnoreCase("password")){
            seller.changePassword(scanner.nextLine());
        } else if (field.equalsIgnoreCase("name")){
            seller.changeName(scanner.nextLine());
        } else if (field.equalsIgnoreCase("phone")){
            seller.changePhone(scanner.nextLine());
        } else if (field.equalsIgnoreCase("family")){
            seller.changeFamily(scanner.nextLine());
        } else if (field.equalsIgnoreCase("username")){
            System.out.println("you can not change your username");
        } else {
            System.out.println("invalid field");
        }
    }

    public void viewCompanyInformation() {
        System.out.println(seller.getDescription());
    }

    public void viewSalesHistory() {
        //TODO
        int logsNo = 1;
        for (SellLog log : seller.getLogs()) {
            System.out.println("sellLog number " + logsNo + ":");
            for (Product product : log.getProducts()) {
                System.out.println("\t" + product.getName());
            }
            logsNo++;
        }
    }

    public void manageProducts() {
        for (Product sellerProduct : seller.getProducts()) {
            System.out.println(sellerProduct.getName());
        }
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if ((matcher = getMatcher(input, "(view (\\d+))")).find()){
                viewProductProcess(Integer.parseInt(matcher.group(2)));
            } else if ((matcher = getMatcher(input, "(view buyers (\\d+))")).find()){
                viewBuyersProcess(Integer.parseInt(matcher.group(2)));
            } else if ((matcher = getMatcher(input, "(edit (\\d+))")).find()){
                editProductProcess(Integer.parseInt(matcher.group(2)));
            } else if (input.equalsIgnoreCase("back")) {
                return;
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void viewProductProcess(int productId) {
        //TODO
    }

    public void viewBuyersProcess(int productId) {
        //TODO
    }

    public void editProductProcess(int productId) {
        //TODO
    }

    public void addProductProcess() {
        System.out.println("please enter the list below:");
        System.out.println("name of the product: ");
        String productName = scanner.nextLine();
        System.out.println("description: ");
        String description = scanner.nextLine();
        System.out.println("price: ");
        double price = scanner.nextDouble();
        System.out.println("state :");
        String state = scanner.nextLine();
        System.out.println("category: ");
        String categoryName = scanner.nextLine();

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
            } else if ((matcher = getMatcher(input, "(remove product (\\d+))")).find()) {
                removeProductProcess(Integer.parseInt(matcher.group(1)));
            } else if (input.equalsIgnoreCase("show categories")) {
                showCategories();
            } else if (input.equalsIgnoreCase("view offs")) {
                viewOffs();
            } else if (input.equalsIgnoreCase("help")) {
                help();
            } else if (input.equalsIgnoreCase("back")) {
                Menu.show();
            } else if (input.equalsIgnoreCase("Exit")) {
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
