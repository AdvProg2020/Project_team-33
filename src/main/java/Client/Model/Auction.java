package Client.Model;

import Client.Model.Users.Seller;

import java.time.LocalTime;
import java.util.ArrayList;

public class Auction {
    private String seller;
    private String id;
    private LocalTime start;
    private LocalTime end;
    private int discountPercent;


    public Auction(String seller, String id, String start, String end, int discountPercent) {
        this.seller = seller;
        this.id = id;
        this.start = LocalTime.of(Integer.parseInt(start.substring(0,2)),Integer.parseInt(start.substring(3)));
        this.end = LocalTime.of(Integer.parseInt(end.substring(0,2)),Integer.parseInt(end.substring(3)));
        this.discountPercent = discountPercent;
    }

    public String getId() {
        return id;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getSeller() {
        return seller;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
}