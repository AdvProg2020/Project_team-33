package Model.Logs;

import Model.Cart;
import Model.Product;
import Model.Users.Buyer;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BuyLog {
    private Buyer buyer;
    private String logId;
    private LocalDateTime localTime;
    private double moneyThatPaid;
    private double discount;
    private String productReceived;
    private ArrayList<Product> products = new ArrayList<>();

    public static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();

    public BuyLog(Buyer buyer, String logId, LocalDateTime localTime, double money, double discount, String productReceived, Cart cart) {
        this.buyer = buyer;
        this.logId = logId;
        this.localTime = localTime;
        this.moneyThatPaid = money;
        this.discount = discount;
        this.productReceived = productReceived;
        this.products = cart.getProductsInCart();
        checkForProducts(cart);
        buyer.addLog(this);
        buyer.setMoney((long) (buyer.getMoney() - (moneyThatPaid - discount)));
    }

    private static void checkForProducts(Cart cart) {
        for (Product product : cart.getProductsInCart()) {
            product.setNumberOfProducts(product.getNumberOfProducts() - cart.getNumberOfProductsInPage(product));
        }
    }

    public String getLogId() {
        return logId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getMoneyThatPaid() {
        return moneyThatPaid;
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public String getProductReceived() {
        return productReceived;
    }
}
