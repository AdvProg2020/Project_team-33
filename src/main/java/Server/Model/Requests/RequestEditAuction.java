package Server.Model.Requests;

import Server.Model.Auction;
import Server.Model.Product;
import Server.Model.Users.Person;
import Server.Model.Users.Seller;

import java.time.LocalTime;
import java.util.ArrayList;

public class RequestEditAuction extends Request {
    private String field;
    private Auction auction;
    private String newChange;

    public RequestEditAuction(String type, String condition, Person sender, Auction auction, String field, String newChange) {
        super(Request.counter, type, condition, sender);
        Request.counter++;
        this.field = field;
        this.auction = auction;
        this.newChange = newChange;
    }

    public Auction getAuction() {
        return auction;
    }

    public String getField() {
        return field;
    }

    public String getNewChange() {
        return newChange;
    }

    public void setChanges(String field) {
        if (field.equals("start")) {
            LocalTime localTime = LocalTime.of(Integer.parseInt(this.getNewChange().substring(0, 2)), Integer.parseInt(this.getNewChange().substring(3, 5)));
            this.getAuction().setStart(localTime);
        } else if (field.equals("end")) {
            LocalTime localTime = LocalTime.of(Integer.parseInt(this.getNewChange().substring(0, 2)), Integer.parseInt(this.getNewChange().substring(3, 5)));
            this.getAuction().setEnd(localTime);
        }
    }
}
