package View;

import Model.Manager;
import Model.Person;
import Model.Product;

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
            deleteUser(matcher.group(2));
        }else if(Menu.scanner.nextLine().equalsIgnoreCase("create manager profile")){
            createManager();
        }else if(Menu.scanner.nextLine().equalsIgnoreCase("manage all products")){
            showProducts();
        }else if((matcher=getMatcher(Menu.scanner.nextLine(),"(remove )(\\d+)")).find()){
            removeProduct(Integer.parseInt(matcher.group(2)));
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

    private void createManager(){
        System.out.println("name: ");
        String name=Menu.scanner.nextLine();
        System.out.println("family: ");
        String family=Menu.scanner.nextLine();
        System.out.println("username: ");
        String username=Menu.scanner.nextLine();
        System.out.println("password: ");
        String password=Menu.scanner.nextLine();
        System.out.println("phone: ");
        String phone=Menu.scanner.nextLine();
        System.out.println("email: ");
        String email=Menu.scanner.nextLine();
        manager=new Manager(name,family,username,password,phone,email);
    }

    private void showProducts(){
        for (Product product : Product.allProduct) {
            System.out.println(product.getID());
        }
    }

    private void removeProduct(int ID){

    }







    private static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
