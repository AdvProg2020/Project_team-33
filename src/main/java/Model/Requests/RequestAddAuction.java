package Model.Requests;


import Model.Auction;
import Model.Users.Person;
import Model.Users.Seller;

import java.util.ArrayList;

public class RequestAddAuction extends Request {
    private Auction auction;
    private static ArrayList<RequestAddAuction> requestAddAuctions = new ArrayList<>();

    public RequestAddAuction(String type, String condition, Person sender, Auction auction) {
        super(type, condition, sender);
        this.auction = auction;
        requestAddAuctions.add(this);
    }


    public Auction getAuction() {
        return auction;
    }
}
