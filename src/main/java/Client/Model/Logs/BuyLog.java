package Client.Model.Logs;

import Client.Model.Cart;
import Client.Model.Product;
import Client.Model.Users.Buyer;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class BuyLog {
    private String logId;
    private LocalTime localTime;
    private double moneyThatPaid;
    private double discount;
    private String productReceived;
    private ArrayList<Product> products = new ArrayList<>();


    public BuyLog(String logId, String time, double money, double discount, String productReceived) {
        this.logId = logId;
        String[] input = time.split(":");
        this.localTime = LocalTime.of(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        this.moneyThatPaid = money;
        this.discount = discount;
        this.productReceived = productReceived;
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

    public LocalTime getLocalTime() {
        return localTime;
    }

    public String getProductReceived() {
        return productReceived;
    }
}
