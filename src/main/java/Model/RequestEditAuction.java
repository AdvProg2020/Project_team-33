package Model;

import java.util.ArrayList;

public class RequestEditAuction extends Request {
    private Seller seller;
    private Auction auction;
    public static ArrayList<RequestEditAuction> allEditAuctionRequests = new ArrayList<RequestEditAuction>();


    public RequestEditAuction(Seller seller, Product product, Auction auction) {
        super(seller, product, auction);
        this.seller=seller;
        this.auction=auction;
    }

    public Seller getSeller() {
        return seller;
    }

    public Auction getAuction() {
        return auction;
    }
}
