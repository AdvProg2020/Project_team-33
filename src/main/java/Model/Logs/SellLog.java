package Model.Logs;

import Model.Product;
import Model.Users.Buyer;
import Model.Users.Seller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class SellLog {
    private Seller seller;
    private String logId;
    private LocalDateTime localTime;
    private double moneyThatPaid;
    private double discount;
    private Product product;
    private Buyer buyer;
    private String productReceived;

    public static ArrayList<SellLog> allSellLogs = new ArrayList<>();

    public SellLog(Seller seller, String logId, LocalDateTime localTime, double moneyThatPaid, double discount, Product product, Buyer buyer, String productReceived) {
        this.seller = seller;
        this.logId = logId;
        this.localTime = localTime;
        this.moneyThatPaid = moneyThatPaid;
        this.discount = discount;
        this.product = product;
        this.buyer = buyer;
        this.productReceived = productReceived;
        seller.setBalance((long) (moneyThatPaid + seller.getBalance()));
    }

    public String getLogId() {
        return logId;
    }

    public double getMoneyThatPaid() {
        return moneyThatPaid;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public double getDiscount() {
        return discount;
    }

    public Product getProduct() {
        return product;
    }

    public String getProductReceived() {
        return productReceived;
    }
}
