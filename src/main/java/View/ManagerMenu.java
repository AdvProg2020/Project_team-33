package View;

import Controller.PersonController;
import Model.Manager;
import Model.Person;
import Model.Product;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu {
    private Manager manager;
    private Matcher matcher;
    Person person;

    public ManagerMenu() {
        super("Manager Menu");
    }

    public void commandProcess() {
        Matcher matcher;
        while (true) {
            String input = Menu.scanner.nextLine();
            if (input.equalsIgnoreCase("view personal info")) {
                showPersonalInfo();
            } else if ((matcher = getMatcher(input, "(edit )((password||name||family||" +
                    "email||phone)")).find()) {
                editPersonalInfoProcess(matcher);
            } else if (input.equalsIgnoreCase("manage users")) {
                manageUsers();
            } else if ((matcher = getMatcher(input, "(view )(\\S+)")).find()) {
                viewPerson(matcher.group(2));
            } else if ((matcher = getMatcher(input, "(delete user )(\\S+)")).find()) {
                deleteUser(matcher.group(2));
            } else if (input.equalsIgnoreCase("create manager profile")) {
                createManager();
            } else if (input.equalsIgnoreCase("manage all products")) {
                showProducts();
            } else if ((matcher = getMatcher(input, "(remove )(\\d+)")).find()) {
                removeProduct(Integer.parseInt(matcher.group(2)));
            } else if (input.equalsIgnoreCase("create discount code")) {
                createDiscount();
            } else if (input.equalsIgnoreCase("view discount codes")) {
                showDiscounts();
            } else if ((matcher = getMatcher(input, "(view discount code )(\\d+)")).find()) {
                viewDiscount(Integer.parseInt(matcher.group(2)));
            } else if ((matcher = getMatcher(input, "(edit discount code )(\\d+)")).find()) {
                editDiscount(Integer.parseInt(matcher.group(2)));
            } else if ((matcher = getMatcher(input, "(remove discount code )(\\d+)")).find()) {
                removeDiscount(Integer.parseInt(matcher.group(2)));
            } else if (input.equalsIgnoreCase("back")) {
                Menu.show();
            } else if (input.equalsIgnoreCase("exit")) {
                System.exit(1);
            } else {
                System.out.println("invalid command");
            }
        }
    }

    public void help() {
        System.out.println("Enter your command:");
        System.out.println("1.view personal info");
        System.out.println("\tedit [field]");
        System.out.println("2.manage users");
        System.out.println("\tview [username]\n" +
                "\tchange type [username] [role]\n" +
                "\tdelete user [username]\n" +
                "\tcreate manager profile");
        System.out.println("3.manage all products");
        System.out.println("\tremove [productId]");
        System.out.println("4.create discount code");
        System.out.println("5.view discount codes");
        System.out.println("\t￼view discount code [code]\n" +
                "\tedit discount code [code]\n" +
                "\tremove discount code [code]");
        System.out.println("6.manage requests");
        System.out.println("\tdetails [requestId]\n" +
                "\taccept [requestId]\n" +
                "\tdecline [requestId]");
        System.out.println("7.back");
        System.out.println("8.exit");
    }

    public void manageUsers() {
        showPeople();
        while ()
    }

    private void showPersonalInfo() {
        manager.toString();
    }

    private void editPersonalInfoProcess(Matcher matcher) {
        System.out.println("new " + matcher.group(2) + ":");
        String field = Menu.scanner.nextLine();
        if (matcher.group(2).equalsIgnoreCase("password")) {
            if (!PersonController.checkLengthOfPassWord(field)) {
                System.out.println("password type incorrect");
                return;
            }
        } else if (matcher.group(2).equalsIgnoreCase("name")) {
            if (!PersonController.nameTypeErr(field)) {
                System.out.println("name type incorrect");
                return;
            }
        } else if (matcher.group(2).equalsIgnoreCase("phone")) {
            if (!PersonController.phoneTypeErr(field)) {
                System.out.println("phone type incorrect");
                return;
            }
        } else if (matcher.group(2).equalsIgnoreCase("email")) {
            if (!PersonController.emailTypeErr(field)) {
                System.out.println("email type incorrect");
                return;
            }
        }
        PersonController.editPersonalInfo(manager, matcher.group(2), field);
        System.out.println(matcher.group(2) + " changed successfully");
    }

    private void showPeople() {
        ArrayList<Person> people = new ArrayList<Person>();
        people.addAll(Person.people);
        for (Person person : people) {
            System.out.println(person.getUsername());
        }
    }

    private void viewPerson(String username) {
        if (!PersonController.usernameTypeErr(username)) {
            System.out.println("username type incorrect");
            return;
        } else if ((person = Person.getPersonByUsername(username)) == null) {
            System.out.println("there is no user with this username exist");
            return;
        }
        person.toString();
    }

    private void deleteUser(String username) {
        if (!PersonController.usernameTypeErr(username)) {
            System.out.println("username type incorrect");
            return;
        } else if ((person = Person.getPersonByUsername(username)) == null) {
            System.out.println("there is no user with this username exist");
            return;
        }
        Person.deleteUser(username);
        System.out.println("user deleted successfully");
    }

    private void createManager() {
        System.out.println("name: ");
        String name = Menu.scanner.nextLine();
        if (!PersonController.nameTypeErr(name)) {
            System.out.println("name type incorrect");
            return;
        }
        System.out.println("family: ");
        String family = Menu.scanner.nextLine();
        System.out.println("username: ");
        String username = Menu.scanner.nextLine();
        if (!PersonController.nameTypeErr(username)) {
            System.out.println("username type incorrect");
            return;
        }
        System.out.println("password: ");
        String password = Menu.scanner.nextLine();
        if (!PersonController.checkLengthOfPassWord(password)) {
            System.out.println("password type incorrect");
            return;
        }
        System.out.println("phone: ");
        String phone = Menu.scanner.nextLine();
        if (!PersonController.phoneTypeErr(phone)) {
            System.out.println("phone type incorrect");
            return;
        }
        System.out.println("email: ");
        String email = Menu.scanner.nextLine();
        if (!PersonController.emailTypeErr(email)) {
            System.out.println("email type incorrect");
            return;
        }
        manager = new Manager(name, family, username, password, phone, email);
        System.out.println("manager created successfully");
    }

    private void showProducts() {
        for (Product product : Product.allProducts) {
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
