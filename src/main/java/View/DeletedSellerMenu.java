package View;

import Controller.PersonController;
import Model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeletedSellerMenu {
    //    Seller seller;
//
//    public SellerMenu() {
////        super("Seller Menu");
//    }
//
//    public void help() {
//        System.out.println("Enter your command:");
//        System.out.println("1.view personal info");
//        System.out.println("\tedit [field]");
//        System.out.println("2.view company information");
//        System.out.println("3.view sales history");
//        System.out.println("4.manage products");
//        System.out.println("\tview [productId]");
//        System.out.println("\tview buyers [productId]");
//        System.out.println("\tedit [productId]");
//        System.out.println("5.add product");
//        System.out.println("6.remove product");
//        System.out.println("7.show categories");
//        System.out.println("8.view offs");
//        System.out.println("\tview [offId]");
//        System.out.println("\tedit [offId]");
//        System.out.println("\tadd off");
//        System.out.println("9.view balance");
//        System.out.println("back");
//        if (LoginMenu.currentPerson != null) {
//            System.out.println("logout");
//        }
//        System.out.println("exit");
//
//        commandProcess();
//    }
//
//    public void commandProcess() {
//        Matcher matcher;
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if (input.equalsIgnoreCase("view personal info")) {
//                viewPersonalInfo();
//            } else if (input.equalsIgnoreCase("view company information")) {
//                viewCompanyInformation();
//            } else if (input.equalsIgnoreCase("view sales history")) {
//                viewSalesHistory();
//            } else if (input.equalsIgnoreCase("manage products")) {
//                manageProducts();
//            } else if (input.equalsIgnoreCase("add product")) {
//                addProductProcess();
//            } else if ((matcher = getMatcher(input, "(remove product (\\d+))")).find()) {
//                removeProductProcess(Integer.parseInt(matcher.group(1)));
//            } else if (input.equalsIgnoreCase("show categories")) {
//                showCategories();
//            } else if (input.equalsIgnoreCase("view offs")) {
//                viewOffs();
//            } else if (input.equalsIgnoreCase("view balance")) {
//                viewBalance();
//            } else if (input.equalsIgnoreCase("help")) {
//                help();
//            } else if (input.equalsIgnoreCase("back")) {
////                Menu.show();
//            } else if (input.equalsIgnoreCase("logout")) {
//                LoginMenu.currentPerson=null;
////                Menu.show();
//            } else if (input.equalsIgnoreCase("Exit")) {
//                System.exit(1);
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    public void viewPersonalInfo() {
//        BuyerMenu.showPersonalInfo();
//    }
//
//    public void viewCompanyInformation() {
//        seller = (Seller) LoginMenu.currentPerson;
//        System.out.println("your company is:");
//        System.out.print(seller.getCompany());
//    }
//
//    public void viewSalesHistory() {
//        int logsNo = 1;
//        for (SellLog log : PersonController.sellerSellLogs((seller = (Seller) LoginMenu.currentPerson))) {
//            System.out.println("sell log number #" + logsNo + ":");
//            System.out.println("\tid: " + log.getSellLogId());
//            System.out.println("products: ");
//            for (Product product : log.getProducts()) {
//                System.out.println("\tproduct id: " + product.getProductID());
//                System.out.println("\tname: " + product.getName());
//            }
//            System.out.println("\t" + log.getProducts());
//            System.out.println("\t money that paid: " + log.getMoneyThatPaid());
//            System.out.println("\t discount: " + log.getDiscount());
//        }
//    }
//
//    public void manageProducts() {
//        for (Product sellerProduct : seller.getProducts()) {
//            System.out.println("name: " + sellerProduct.getName());
//        }
//        System.err.println("if you want to continue write your command and else write back");
//        Matcher matcher;
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if ((matcher = getMatcher(input, "(view (\\d+))")).find()) {
//                viewProductProcess(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(view buyers (\\d+))")).find()) {
//                viewBuyersProcess(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(edit (\\d+))")).find()) {
//                editProductProcess(Integer.parseInt(matcher.group(2)));
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    public void viewProductProcess(int productId) {
//        if (seller.getProductById(productId) == null) {
//            System.err.println("wrong id");
//            return;
//        }
//        seller = (Seller) LoginMenu.currentPerson;
//        Product product = seller.getProductById(productId);
//        System.out.println("name: " + product.getName());
//        System.out.println("id: " + product.getID());
//        System.out.println("description: " + product.getDescription());
//    }
//
//    public void viewBuyersProcess(int productId) {
//        if (seller.getProductById(productId) == null) {
//            System.err.println("wrong id");
//            return;
//        }
//        seller = (Seller) LoginMenu.currentPerson;
//        Product product = seller.getProductById(productId);
//        for (SellerOfProduct seller1 : product.getAllSeller()) {
//            System.out.println("company: " + seller1.getSeller().getCompany());
//            System.out.println("name: " + seller1.getSeller().getName());
//            System.out.println("family: " + seller1.getSeller().getFamily());
//            System.out.println("username: " + seller1.getSeller().getUsername());
//        }
//    }
//
//    public void editProductProcess(int productId) {
//        seller = (Seller) LoginMenu.currentPerson;
//        Product product = seller.getProductById(productId);
//        System.out.println();
//    }
//
//    //TODO
//    public void addProductProcess() {
//        System.out.println("please enter the list below:");
//        System.out.println("name of the product: ");
//        String productName = scanner.nextLine();
//        System.out.println("description: ");
//        String description = scanner.nextLine();
//        System.out.println("price: ");
//        double price = scanner.nextDouble();
//        System.out.println("state :");
//        String state = scanner.nextLine();
//        System.out.println("category: ");
//        String categoryName = scanner.nextLine();
//        System.out.println("super category for the category you wrote: ");
//        String superCategory = scanner.nextLine();
//        System.out.println("is this product available?(yes/no)");
//        String availability = scanner.nextLine();
//        //TODO
//    }
//
//    public void removeProductProcess(int productId) {
//        if (seller.getProductById(productId) == null) {
//            System.err.println("wrong id");
//            return;
//        }
//        seller = (Seller) LoginMenu.currentPerson;
//        Product product = seller.getProductById(productId);
//        seller.deleteProduct(product);
//        System.out.println("deleted successfully");
//    }
//
//    public void showCategories() {
//        for (Category category : Category.allCategory) {
//            System.out.println(category.getName());
//        }
//    }
//
//    public void viewOffs() {
//        Matcher matcher;
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if ((matcher = getMatcher(input, "(view (\\d+))")).find()) {
//                viewOff(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(view (\\d+))")).find()) {
//                editOffProcess(Integer.parseInt(matcher.group(2)));
//            } else if ((input.equalsIgnoreCase("add off"))) {
//                addOffProcess();
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else if (input.equalsIgnoreCase("Exit")) {
//                System.exit(1);
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    public void viewOff(int offId) {
//
//    }
//
//    public void editOffProcess(int offId) {
//
//    }
//
//    public void addOffProcess() {
//
//    }
//
//    public void viewBalance() {
//        seller = (Seller) LoginMenu.currentPerson;
//        System.out.println(seller.getMoney());
//    }
//
//    private static Matcher getMatcher(String input, String regex) {
//        Pattern pattern = Pattern.compile(regex);
//        return pattern.matcher(input);
//    }

}
