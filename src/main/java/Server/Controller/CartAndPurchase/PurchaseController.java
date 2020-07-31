package Server.Controller.CartAndPurchase;

import Server.Model.Discount;
import Server.Model.Logs.BuyLog;
import Server.Model.Logs.SellLog;
import Server.Model.Product;
import Server.Model.Users.Buyer;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class PurchaseController {

    public static boolean isCodeExistForBuyer(Buyer buyer, String code) {
        return buyer.isDiscountExist(code);
    }

    public static void doPurchase(Buyer buyer, double discount, String address, double money) {
        new BuyLog(buyer, String.valueOf(Math.random()), LocalDateTime.now(), money, discount, "Unknown", buyer.getCart(), address);
        for (Product product : buyer.getCart().getProductsInCart()) {
            new SellLog(product.getSeller(), String.valueOf(Math.random()), LocalTime.now(), product.getMoney(), discount, product, buyer, "Received");
        }
    }

    public static void purchaseForPublicSale(Buyer buyer, double discount, String address, Product product){
        new BuyLog(buyer, String.valueOf(Math.random()), LocalDateTime.now(), product.getMoney(), discount, "Unknown", product, address);
        new SellLog(product.getSeller(), String.valueOf(Math.random()), LocalTime.now(), product.getMoney(), discount, product, buyer, "Received");
    }

    public static double getDiscountMax(Discount discount) {
        return discount.getMaxDiscount();
    }

    public static int getDiscountPercent(Discount discount) {
        return discount.getDiscountPercent();
    }

}
