package Controller.SellerController;

import Model.Requests.Request;
import Model.Users.Person;
import Model.Users.Seller;

import java.util.ArrayList;

public class SellerAbilitiesController {

    public static void editPersonalInfo(Person person, String field, String newChange) {
        if (field.equalsIgnoreCase("password")) {
            person.srtPassword(newChange);
        } else if (field.equalsIgnoreCase("name")) {
            person.setName(newChange);
        } else if (field.equalsIgnoreCase("family")) {
            person.setFamily(newChange);
        } else if (field.equalsIgnoreCase("phone")) {
            person.setPhone(newChange);
        } else if (field.equalsIgnoreCase("email")) {
            person.setEmail(newChange);
        } else if (field.equals("company")) {
            Seller seller = (Seller) person;
            seller.setCompany(newChange);
        }
    }

    public static ArrayList<Request> getAllSellerRequests(Seller seller) {
        return seller.getSellerRequests();
    }

    public static void deleteRequest(Seller seller, Request request) {
        seller.deleteRequest(request);
    }
}
