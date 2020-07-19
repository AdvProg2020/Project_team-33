package Server.Model;

import Server.Model.Users.Buyer;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Discount {
    private String code;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long maxDiscount;
    private int discountPercent;
    private HashMap<String, Buyer> buyerInclude;
    private static ArrayList<Discount> allDiscounts = new ArrayList<>();

    public Discount(String code, LocalTime startDate, LocalTime endDate, Long maxDiscount, int discountPercent) {
        this.code = code;
        this.startTime = startDate;
        this.endTime = endDate;
        this.maxDiscount = maxDiscount;
        this.discountPercent = discountPercent;
        this.buyerInclude = new HashMap<>();
        allDiscounts.add(this);
    }

    public Discount() {

    }

    public static ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public String getCode() {
        return code;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setMaxDiscount(Long maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Long getMaxDiscount() {
        return maxDiscount;
    }

    public static void deleteDiscount(Discount discount) {
        allDiscounts.remove(discount);
    }

    public static boolean isDiscountExist(String code) {
        for (Discount allDiscount : allDiscounts) {
            if (allDiscount.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static Discount getDiscountByCode(String code) {
        for (Discount allDiscount : allDiscounts) {
            if (allDiscount.getCode().equals(code)) {
                return allDiscount;
            }
        }
        return null;
    }
}
