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

}

enum AuctionStatue{
    BUILDING,
    EDITING,
    CONFIRMED
}
