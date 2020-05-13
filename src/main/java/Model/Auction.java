package Model;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
    public ArrayList<Auction> allAuctions;
    private Date beginningTime;
    private Date finishingTime;
    private ArrayList<Product> products;
    private AuctionStatue auctionStatue;
    private int discount;

    public Auction(Date beginningTime, Date finishingTime, ArrayList<Product> products, int discount) {
        this.beginningTime = beginningTime;
        this.finishingTime = finishingTime;
        this.products = products;
        this.discount = discount;
    }
}

enum AuctionStatue{
    BUILDING,
    EDITING,
    CONFIRMED
}
