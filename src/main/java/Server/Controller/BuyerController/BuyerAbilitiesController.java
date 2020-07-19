package Server.Controller.BuyerController;

import Server.Model.Users.Person;
import Server.Model.Users.Seller;

public class BuyerAbilitiesController {
    public static void editPersonalInfo(Person person, String field, String newChange) {
        if (field.equalsIgnoreCase("password")) {
            person.setPassword(newChange);
        } else if (field.equalsIgnoreCase("name")) {
            person.setName(newChange);
        } else if (field.equalsIgnoreCase("family")) {
            person.setFamily(newChange);
        } else if (field.equalsIgnoreCase("phone")) {
            person.setPhone(newChange);
        } else if (field.equalsIgnoreCase("email")) {
            person.setEmail(newChange);
        } else if (field.equalsIgnoreCase("company")){
            ((Seller)person).setCompany(newChange);
        }
    }
}
