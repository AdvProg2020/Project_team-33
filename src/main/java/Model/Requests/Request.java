package Model.Requests;

import Model.Auction;
import Model.Product;
import Model.Users.Person;
import Model.Users.Seller;

import java.util.ArrayList;

public class Request {
    String type;
    String condition;
    Person sender;
    public static ArrayList<Request> allRequests = new ArrayList<>();

    public Request(String type, String condition, Person sender) {
        this.type = type;
        this.condition = condition;
        this.sender = sender;
        allRequests.add(this);
    }

    public void setCondition(String condition) {
        this.condition = condition;
        if (condition.equals("Accept")) {
            Seller seller = (Seller) this.getSender();
            seller.setCondition("Accept");
        } else if (condition.equals("Decline")) {
            Seller seller = (Seller) this.getSender();
            seller.setCondition("Decline");
        }
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }

    public String getType() {
        return type;
    }

    public String getCondition() {
        return condition;
    }

    public Person getSender() {
        return sender;
    }

    public static void deleteRequest(Request request) {
        allRequests.remove(request);
    }


}
