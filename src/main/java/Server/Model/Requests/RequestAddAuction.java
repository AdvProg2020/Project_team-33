package Server.Model.Requests;


import Server.Model.Auction;
import Server.Model.Users.Person;

import java.util.ArrayList;

public class RequestAddAuction extends Request {
    private Auction auction;
    private static ArrayList<RequestAddAuction> requestAddAuctions = new ArrayList<>();

    public RequestAddAuction(String type, String condition, Person sender, Auction auction) {
        super(Request.counter, type, condition, sender);
        Request.counter++;
        this.auction = auction;
        requestAddAuctions.add(this);
    }


    public Auction getAuction() {
        return auction;
    }
}
