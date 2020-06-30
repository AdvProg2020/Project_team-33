package Model.Requests;

import Model.Auction;
import Model.Product;
import Model.Users.Seller;

import java.util.ArrayList;

public class RequestAddAuction {
    private Seller seller;
    private Auction auction;
    public static ArrayList<RequestAddAuction> allAddAuctionRequest = new ArrayList<RequestAddAuction>();

    public RequestAddAuction(Seller seller, Product product, Auction auction) {
//        super(seller, product, auction);
        this.seller = seller;
        this.auction = auction;
        allAddAuctionRequest.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Auction getAuction() {
        return auction;
    }
}
