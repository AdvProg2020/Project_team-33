package View;

import Controller.PersonController;
import Model.Manager;
import Model.Person;
import Model.Product;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu{
    private Manager manager;
    private Matcher matcher;
    Person person;

    public ManagerMenu(){
        super("Manager Menu",null);
    }

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
        } else if ((matcher = getMatcher(Menu.scanner.nextLine(), "(delete user )(\\S+)")).find()) {
            deleteUser(matcher.group(2));
        } else if (Menu.scanner.nextLine().equalsIgnoreCase("create manager profile")) {
            createManager();
        } else if (Menu.scanner.nextLine().equalsIgnoreCase("manage all products")) {
            showProducts();
        } else if ((matcher = getMatcher(Menu.scanner.nextLine(), "(remove )(\\d+)")).find()) {
            removeProduct(Integer.parseInt(matcher.group(2)));
        } else if (Menu.scanner.nextLine().equalsIgnoreCase("create discount code")) {
            createDiscount();
        } else if (Menu.scanner.nextLine().equalsIgnoreCase("view discount codes")) {
            showDiscounts();
        } else if ((matcher = getMatcher(Menu.scanner.nextLine(), "(view discount code )(\\d+)")).find()) {
            viewDiscount(Integer.parseInt(matcher.group(2)));
        } else if ((matcher = getMatcher(Menu.scanner.nextLine(), "(edit discount code )(\\d+)")).find()) {
            editDiscount(Integer.parseInt(matcher.group(2)));
        } else if ((matcher = getMatcher(Menu.scanner.nextLine(), "(remove discount code )(\\d+)")).find()) {
            removeDiscount(Integer.parseInt(matcher.group(2)));
        }
    }

    private void showPersonalInfo() {
        manager.toString();
    }

    private void editPersonalInfo(Matcher matcher) {
        System.out.println("new " + matcher.group(2) + ":");
        String field = Menu.scanner.nextLine();
        PersonController.editPersonalInfo(LoginMenu.currentPerson,matcher.group(2),field);
    }

    private void showPeople() {
        ArrayList<Person> people = new ArrayList<Person>();
        people.addAll(Person.people);
        for (Person person : people) {
            System.out.println(person.getUsername());
        }
    }

    private void viewPerson(String username) {
        person = Person.getPersonByUsername(username);
        person.toString();
    }

    private void deleteUser(String username) {
        Person.deleteUser(username);
    }

    private void createManager() {
        System.out.println("name: ");
        String name = Menu.scanner.nextLine();
        System.out.println("family: ");
        String family = Menu.scanner.nextLine();
        System.out.println("username: ");
        String username = Menu.scanner.nextLine();
        System.out.println("password: ");
        String password = Menu.scanner.nextLine();
        System.out.println("phone: ");
        String phone = Menu.scanner.nextLine();
        System.out.println("email: ");
        String email = Menu.scanner.nextLine();
        manager = new Manager(name, family, username, password, phone, email);
    }

    private void showProducts() {
        for (Product product : Product.allProduct) {
            System.out.println(product.getID());
        }
    }

    private void removeProduct(int ID) {

    }

    private void createDiscount() {

    }

    private void showDiscounts() {

    }

    private void viewDiscount(int id) {

    }

    private void editDiscount(int id) {

    }

    private void removeDiscount(int id) {

    }


    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
