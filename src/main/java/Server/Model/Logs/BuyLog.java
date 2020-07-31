package Server.Model.Logs;

import Server.Model.Cart;
import Server.Model.Product;
import Server.Model.Users.Buyer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BuyLog {
    private Buyer buyer;
    private String logId;
    private LocalDateTime localTime;
    private double moneyThatPaid;
    private double discount;
    private String productReceived;
    String address;
    private ArrayList<Product> products = new ArrayList<>();

    public static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();

    public BuyLog(Buyer buyer, String logId, LocalDateTime localTime, double money, double discount, String productReceived, Cart cart, String address) {
        this.buyer = buyer;
        this.logId = logId;
        this.localTime = localTime;
        this.moneyThatPaid = money;
        this.discount = discount;
        this.productReceived = productReceived;
        this.products.addAll(cart.getProductsInCart());
        this.address = address;
        checkForProducts(cart);
        buyer.addLog(this);
        allBuyLogs.add(this);
    }

    public BuyLog(Buyer buyer, String logId, LocalDateTime localTime, double money, double discount, String productReceived, Product product, String address) {
        this.buyer = buyer;
        this.logId = logId;
        this.localTime = localTime;
        this.moneyThatPaid = money;
        this.discount = discount;
        this.productReceived = productReceived;
        this.products.add(product);
        this.address = address;
        buyer.addLog(this);
        product.setNumberOfProducts(product.getNumberOfProducts() - 1);
        allBuyLogs.add(this);
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
        return this.products;
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

    public static BuyLog getBuyLogById(String id) {
        for (BuyLog allBuyLog : allBuyLogs) {
            if (allBuyLog.getLogId().equals(id)) {
                return allBuyLog;
            }
        }
        return null;
    }

    public static ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }

    public void setProductReceived(String productReceived) {
        this.productReceived = productReceived;
    }

    public String getAddress() {
        return address;
    }
}
