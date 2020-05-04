package View;

import Model.Manager;
import Model.Person;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu {
    private Manager manager;
    private Matcher matcher;
    Person person;

    public void commandProcess() {
        if (Menu.scanner.nextLine().equalsIgnoreCase("view personal info")) {
            showPersonalInfo();
        } else if ((matcher = getMatcher(Menu.scanner.nextLine(), "(edit )((password|name|family|" +
                "email|phone)")).find()) {
            editPersonalInfo(matcher);
        } else if (Menu.scanner.nextLine().equalsIgnoreCase("manage users")) {
            showPeople();
        } else if ((matcher = getMatcher(Menu.scanner.nextLine(), "(view )(\\S+)")).find()) {
            viewPerson(matcher.group(2));
        }else if((matcher = getMatcher(Menu.scanner.nextLine(), "(delete user )(\\S+)")).find()){

        }
    }

    private void showPersonalInfo() {
        manager.toString();
    }

    private void editPersonalInfo(Matcher matcher) {
        System.out.println("new " + matcher.group(2) + ":");
        String field = Menu.scanner.nextLine();
        if (matcher.group(2).equals("password")) {
            manager.changePassword(field);
        } else if (matcher.group(2).equals("name")) {
            manager.changeName(field);
        } else if (matcher.group(2).equals("family")) {
            manager.changeFamily(field);
        } else if (matcher.group(2).equals("phone")) {
            manager.changePhone(field);
        } else if (matcher.group(2).equals("email")) {
            manager.changeEmail(field);
        }
    }

    private void showPeople() {
        ArrayList<Person> people = new ArrayList<Person>();
        people.addAll(Person.getPeople());
        for (Person person : people) {
            System.out.println(person.getUsername());
        }
    }

    private void viewPerson(String username) {
        person = Person.getPersonByUsername(username);
        person.toString();
    }

    private void deleteUser(String username){
        Person.deleteUser(username);
    }







    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
