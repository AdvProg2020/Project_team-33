package Model.Requests;

import Model.Users.Person;

import java.util.ArrayList;

public class RequestAddSeller extends Request {

    private static ArrayList<RequestAddSeller> allAddSellerRequests = new ArrayList<>();


    public RequestAddSeller(String type, String condition, Person sender) {
        super(type, condition, sender);
        allAddSellerRequests.add(this);
    }

}
