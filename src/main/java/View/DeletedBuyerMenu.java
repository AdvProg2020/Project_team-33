package View;

import Controller.PersonController;
import Model.BuyLog;
import Model.Buyer;
import Model.BuyingProduct;
import Model.Product;
//import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeletedBuyerMenu {
    //    Buyer buyer;
//
//
//    public void help() {
//        System.out.println("Enter your command:");
//        System.out.println("1.view personal info");
//        System.out.println("\tedit [field]");
//        System.out.println("2.view cart");
//        System.out.println("\tshow products\n\t" +
//                "view [productId]\n\t" + "increase [productId]\n\t" +
//                "decrease [productId]\n\t" + "show total price\n\t" +
//                "purchase");
//        System.out.println("3.purchase");
//        System.out.println("4.view orders");
//        System.out.println("\tshow order [orderId]\n\t" +
//                "rate [productId] [1-5]");
//        System.out.println("5.view balance");
//        System.out.println("6.view discount codes");
//        System.out.println("7.logout");
//        System.out.println("8.help");
//        System.out.println("9.back");
//        System.out.println("10.exit");
//        if (LoginMenu.currentPerson != null) {
//            System.out.println("logout");
//        } else {
//            System.out.println("register or login");
//        }
//        System.err.println("Attention:\nPlease Write Your Command Not Just Number");
//        commandProcess();
//    }
//
//    public void commandProcess() {
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if (input.equalsIgnoreCase("view personal info")) {
//                showPersonalInfo();
//            } else if (input.equalsIgnoreCase("view cart")) {
//                viewCartProcess();
//            } else if (input.equalsIgnoreCase("purchase")) {
//                purchaseProcess();
//            } else if (input.equalsIgnoreCase("view")) {
//                viewOrders();
//            } else if (input.equalsIgnoreCase("view balance")) {
//                viewBalance();
//            } else if (input.equalsIgnoreCase("view discount codes")) {
//                viewDiscountCodes();
//            } else if (input.equalsIgnoreCase("logout")) {
//                LoginMenu.currentPerson = null;
////                Menu.show();
//            } else if (input.equalsIgnoreCase("help")) {
//                help();
//            } else if (input.equalsIgnoreCase("back")) {
////                Menu.show();
//            } else if (input.equalsIgnoreCase("exit")) {
//                System.exit(1);
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    public static void showPersonalInfo() {
//        Matcher matcher;
//        LoginMenu.currentPerson.toString();
//        System.out.println("if you want do edit write ");
//        System.err.print("edit [field]");
//        System.out.println("if you dont write ");
//        System.err.print("back");
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if ((matcher = getMatcher(input, "(edit (\\S+))")).find()) {
//                editPersonalInfo(matcher.group(2));
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    public static void editPersonalInfo(String field) {
//        while (true) {
//            if (field.equalsIgnoreCase("name")) {
//                System.out.println("new name:");
//                PersonController.editPersonalInfo(LoginMenu.currentPerson, "name", Menu.scanner.nextLine());
//                System.out.println("your name changed successfully");
//            } else if (field.equalsIgnoreCase("password")) {
//                System.out.println("new password:");
//                String password = Menu.scanner.nextLine();
//                if (RegisterMenu.getPasswordOfAccount(password)) {
//                    PersonController.editPersonalInfo(LoginMenu.currentPerson, "password", Menu.scanner.nextLine());
//                    System.out.println("your password changed successfully");
//                }
//            } else if (field.equalsIgnoreCase("email")) {
//                System.out.println("new email:");
//                String email = Menu.scanner.nextLine();
//                if (RegisterMenu.getEmailOfAccount(email)) {
//                    PersonController.editPersonalInfo(LoginMenu.currentPerson, "email", Menu.scanner.nextLine());
//                    System.out.println("your email changed successfully");
//                }
//            } else if (field.equalsIgnoreCase("phone")) {
//                System.out.println("new phone:");
//                String phone = Menu.scanner.nextLine();
//                if (RegisterMenu.getPhoneOfAccount(phone)) {
//                    PersonController.editPersonalInfo(LoginMenu.currentPerson, "phone", Menu.scanner.nextLine());
//                    System.out.println("your phone changed successfully");
//                }
//            } else if (field.equalsIgnoreCase("family")) {
//                System.out.println("new family:");
//                PersonController.editPersonalInfo(LoginMenu.currentPerson, "family", Menu.scanner.nextLine());
//                System.out.println("your family changed successfully");
//            } else if (field.equalsIgnoreCase("username")) {
//                System.out.println("you can not change your username");
//            } else {
//                System.out.println("invalid field");
//                break;
//            }
//        }
//    }
//
//    public void viewCartProcess() {
//        System.out.println("1.show products");
//        System.out.println("2.view [productId]");
//        System.out.println("3.increase [productId]");
//        System.out.println("4.decrease [productId]");
//        System.out.println("5.show total price");
//        System.out.println("6.purchase");
//        Matcher matcher;
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if (input.equalsIgnoreCase("show products")) {
//                showProductsInBasket();
//            } else if ((matcher = getMatcher(input, "(view (\\d+))")).find()) {
//                viewProductInBasketProcess(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(increase (\\d+))")).find()) {
//                increaseNumberOfProductsProcess(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(decrease (\\d+))")).find()) {
//                decreaseNumberOfProductsProcess(Integer.parseInt(matcher.group(2)));
//            } else if (input.equalsIgnoreCase("show total price")) {
//                showTotalPriceOfProductsInBasket();
//            } else if (input.equalsIgnoreCase("purchase")) {
//                purchaseProcess();
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    public void showProductsInBasket() {
//        buyer = (Buyer) LoginMenu.currentPerson;
//        if (buyer.getUserCart().getProductsInCart().size() < 1) {
//            System.out.println("There are not any products in your cart");
//        } else {
//            for (BuyingProduct product : buyer.getUserCart().getProductsInCart()) {
//                System.out.println(product.getProduct().getName());
//            }
//        }
//    }
//
//    public void viewProductInBasketProcess(int productId) {
//        buyer = (Buyer) LoginMenu.currentPerson;
//        if (buyer.getUserCart().getProductInCartById(productId) != null) {
//            System.out.println(buyer.getUserCart().getProductInCartById(productId).getName());
//        } else {
//            System.out.println("your productID is incorrect");
//        }
//    }
//
//    public void increaseNumberOfProductsProcess(int productId) {
//        buyer = (Buyer) LoginMenu.currentPerson;
//        if (buyer.getUserCart().getProductInCartById(productId) != null) {
//            buyer.getUserCart().setProductInCart(buyer.getUserCart().getProductInCartById(productId));
//            System.out.println("your products increased");
//        } else {
//            System.out.println("your productID is incorrect");
//        }
//    }
//
//    public void decreaseNumberOfProductsProcess(int productId) {
//        buyer = (Buyer) LoginMenu.currentPerson;
//        if (buyer.getUserCart().getProductInCartById(productId) != null) {
//            buyer.getUserCart().removeProductFromCart(buyer.getUserCart().getProductInCartById(productId));
//            System.out.println("your products decreased");
//        } else {
//            System.out.println("your productID is incorrect");
//        }
//    }
//
//    public void showTotalPriceOfProductsInBasket() {
//        int totalPrice = 0;
//        for (BuyingProduct product : buyer.getUserCart().getProductsInCart()) {
//            totalPrice += product.getPrice();
//        }
//        System.out.println("The total price is: " + totalPrice);
//    }
//
//    public void purchaseProcess() {
//        PurchaseMenu.receiveInformation();
//    }
//
//    public void viewOrders() {
//        //TODO show logs
//        int logsNo = 1;
//        for (BuyLog buyLog : buyer.getLog()) {
//            System.out.println("buyLog number " + logsNo);
//            for (Product product : buyLog.getProducts().keySet()) {
//                product.getName();
//            }
//            logsNo++;
//        }
//        viewOrdersCommandProcessor();
//    }
//
//    public void viewOrdersCommandProcessor() {
//        Matcher matcher;
//        while (true) {
//            String input = Menu.scanner.nextLine();
//            if ((matcher = getMatcher(input, "(show order (\\d+))")).find()) {
//                showOrder(Integer.parseInt(matcher.group(2)));
//            } else if ((matcher = getMatcher(input, "(rate (\\d+) (\\d+))")).find()) {
//                rateProductProcess(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else {
//                System.out.println("invalid command");
//            }
//        }
//    }
//
//    public void showOrder(int orderId) {
//
//    }
//
//    public void rateProductProcess(int productId, int rate) {
//
//    }
//
//    public void viewBalance() {
//        Buyer buyer = (Buyer) LoginMenu.currentPerson;
//        System.out.println("Your balance: " + buyer.getMoney());
//    }
//
//    public void viewDiscountCodes() {
//        for (String discountCode : buyer.getDiscountCode()) {
//            System.out.println(discountCode);
//        }
//    }
//
//    private static Matcher getMatcher(String input, String regex) {
//        Pattern pattern = Pattern.compile(regex);
//        return pattern.matcher(input);
//    }
}


