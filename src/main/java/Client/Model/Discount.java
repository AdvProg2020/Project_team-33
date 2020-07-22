package Client.Model;

import Client.Model.Users.Buyer;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Discount implements Serializable {
    private String code;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long maxDiscount;
    private int discountPercent;
    private HashMap<String, Buyer> buyerInclude;

    public Discount(String code, String startDate, String endDate, Long maxDiscount, int discountPercent) {
        this.code = code;
        String[] start = startDate.split(":");
        String[] end = endDate.split(":");
        this.startTime = LocalTime.of(Integer.parseInt(start[0]), Integer.parseInt(start[1]));
        this.endTime = LocalTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]));
        this.maxDiscount = maxDiscount;
        this.discountPercent = discountPercent;
        this.buyerInclude = new HashMap<>();
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

