package Client.Model.Logs;

import Client.Model.Product;
import Client.Model.Users.Buyer;
import Client.Model.Users.Seller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class SellLog {
    private Seller seller;
    private String logId;
    private LocalTime localTime;
    private double moneyThatPaid;
    private double discount;
    private String product;
    private String buyer;
    private String productReceived;


    public SellLog(String logId, String localTime, double moneyThatPaid, double discount, String product, String buyer, String productReceived) {
        this.logId = logId;
        String[] input = localTime.split(":");
        this.localTime = LocalTime.of(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        this.moneyThatPaid = moneyThatPaid;
        this.discount = discount;
        this.product = product;
        this.buyer = buyer;
        this.productReceived = productReceived;
    }

    public String getLogId() {
        return logId;
    }

    public double getMoneyThatPaid() {
        return moneyThatPaid;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public double getDiscount() {
        return discount;
    }

    public String getProduct() {
        return product;
    }

    public String getProductReceived() {
        return productReceived;
    }
}
