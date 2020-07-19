package Client.Model;

import Client.Model.Users.Buyer;

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

    public Discount(String code, LocalTime startDate, LocalTime endDate, Long maxDiscount, int discountPercent) {
        this.code = code;
        this.startTime = startDate;
        this.endTime = endDate;
        this.maxDiscount = maxDiscount;
        this.discountPercent = discountPercent;
        this.buyerInclude = new HashMap<>();
    }

    public Discount() {

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
}

