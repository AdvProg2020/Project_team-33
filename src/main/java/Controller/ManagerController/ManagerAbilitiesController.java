package Controller.ManagerController;

import Model.Discount;
import Model.Users.Person;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalTime;
import java.util.ArrayList;

public class ManagerAbilitiesController {


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
        }
    }

    public static ArrayList<Person> getAllMembers() {
        return Person.getPeople();
    }

    public static void deleteUser(Person person) {
        Person.deleteUser(person);
    }

    public static ArrayList<Discount> getAllDiscounts() {
        return Discount.getAllDiscounts();
    }

    public static void deleteDiscount(Discount discount) {
        Discount.deleteDiscount(discount);
    }

    public static void editDiscount(Discount discount, String field, String newChange) {
        if (field.equalsIgnoreCase("code")) {
            discount.setCode(newChange);
        } else if (field.equalsIgnoreCase("start time")) {
            String[] input = newChange.split(":");
            LocalTime localTime = LocalTime.of(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            discount.setStartTime(localTime);
        } else if (field.equalsIgnoreCase("end time")) {
            String[] input = newChange.split(":");
            LocalTime localTime = LocalTime.of(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            discount.setEndTime(localTime);
        } else if (field.equalsIgnoreCase("percent")) {
            discount.setDiscountPercent(Integer.parseInt(newChange));
        } else if (field.equalsIgnoreCase("max discount")) {
            discount.setMaxDiscount(Long.parseLong(newChange));
        }
    }


}
