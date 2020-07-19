package Server.Model.Requests;

import Server.Model.Auction;
import Server.Model.Product;
import Server.Model.Users.Seller;

import java.util.ArrayList;

public class RequestEditAuction  {
    private Seller seller;
    private Auction auction;
    public static ArrayList<RequestEditAuction> allEditAuctionRequests = new ArrayList<RequestEditAuction>();


    public RequestEditAuction(Seller seller, Product product, Auction auction) {
//        super(seller, product, auction);
        this.seller = seller;
        this.auction = auction;
        allEditAuctionRequests.add(this);
    }

    public Seller getSeller() {
        return seller;
    }

    public Auction getAuction() {
        return auction;
    }
}
