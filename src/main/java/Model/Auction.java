package Model;

import Model.Users.Seller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Auction {
    private Seller seller;
    private String id;
    private ArrayList<Product> products;
    private String state;
    private LocalTime start;
    private LocalTime end;
    private int discountPercent;
    public static ArrayList<Auction> allAuctions = new ArrayList<>();


    public Auction(Seller seller, String id, ArrayList<Product> products, LocalTime start, LocalTime end, int discountPercent) {
        this.seller = seller;
        this.id = id;
        this.products = products;
        this.state = "Creating";
        this.start = start;
        this.end = end;
        this.discountPercent = discountPercent;
        allAuctions.add(this);
        seller.addAuctionForSeller(this);
    }

    public static boolean isIdExist(String id) {
        for (Auction allAuction : allAuctions) {
            if (id.equals(allAuction.getId())) {
                return true;
            }
        }
        return false;
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
}

enum AuctionStatue {
    BUILDING,
    EDITING,
    CONFIRMED
}
