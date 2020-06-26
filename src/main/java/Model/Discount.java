package Model;

import Model.Users.Buyer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Discount {
    public static ArrayList<Discount> allDiscounts = new ArrayList();
    private String code;
    private int discountPercent;
    private int maxDiscount;
    private LocalDateTime start;
    private LocalDateTime end;

    private HashMap<Buyer, Integer> buyerHasThisDiscount = new HashMap<>();
    private int numberBuyerCanUseCode;

    public Discount(int discount, int maxDiscount, String code, LocalDateTime start, LocalDateTime end) {
        this.discountPercent = discount;
        this.maxDiscount = maxDiscount;
        this.code = code;
        this.start = start;
        this.end = end;
        allDiscounts.add(this);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

//    public boolean isDiscountActive(String code) {
//
//    }
//
//    public boolean isThisCodeExist(String code) {
//
//    }

    public String getCode() {
        return code;
    }
}
