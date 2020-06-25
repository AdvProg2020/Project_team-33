package Model;

import Model.Users.Buyer;

import java.util.ArrayList;
import java.util.HashMap;

public class Discount {
    ArrayList<Discount> allDiscounts = new ArrayList<Discount>();
    private int discount;
    private int maxDiscount;
    private HashMap<Buyer, Integer> buyerHasThisDiscount = new HashMap<Buyer, Integer>();
    private int numberBuyerCanUseCode;
    private String code;

    public Discount(int discount, int maxDiscount, String code) {
        this.discount = discount;
        this.maxDiscount = maxDiscount;
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscount() {
        return discount;
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
