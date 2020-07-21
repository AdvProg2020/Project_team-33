package Client.Model.Requests;

import Client.Model.Product;
import Client.Model.Users.Person;
import Client.Model.Users.Seller;

import java.util.ArrayList;

public class Request {
    protected int id;
    protected String type;
    protected String condition;
    protected String sender;

    public Request(int id, String type, String condition, String sender) {
        this.id = id;
        this.type = type;
        this.condition = condition;
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public String getCondition() {
        return condition;
    }

    public String getSender() {
        return sender;
    }

    public int getId() {
        return id;
    }
}
