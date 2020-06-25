package Controller.ManagerController;

import Model.Users.Person;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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



}
