package Controller.ManagerController;

import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Person;
import Model.Users.Seller;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ManagerAbilitiesControllerTest {
    ArrayList<Person> people = new ArrayList<>();

    @Test
    public void editPersonalInfoForManager() {
        Manager manager = new Manager("amk_amir", "AmirMahdi", "Kousheshi", "09912310335",
                "amk_amir82@yahoo.com", "Appleid1234321");
        ManagerAbilitiesController.editPersonalInfo(manager, "password", "123");
        ManagerAbilitiesController.editPersonalInfo(manager, "email", "ali.vanaki100@gmail.com");
        ManagerAbilitiesController.editPersonalInfo(manager, "name", "ali");
        ManagerAbilitiesController.editPersonalInfo(manager, "family", "vanaki");
        ManagerAbilitiesController.editPersonalInfo(manager, "phone", "09107270737");
        assertEquals("ali", manager.getName());
        assertEquals("123", manager.getPassword());
        assertEquals("ali.vanaki100@gmail.com", manager.getEmail());
        assertEquals("vanaki", manager.getFamily());
        assertEquals("09107270737", manager.getPhone());
        people.add(manager);
    }

    @Test
    public void getPeopleFromManger() {
        Person.people.clear();
        people.add(new Buyer("mohammad", "mohammad", "daviran", "1234", "mr@gmail.com", " 123"));
        people.add(new Seller("mohammad", "mohammad", "daviran", "1234", "mr@gmail.com", " 123", "Apple"));
        assertEquals(people, ManagerAbilitiesController.getAllMembers());
    }

    @Test
    public void getProductsForManager() {
        assertEquals(null, ManagerAbilitiesController.getAllMembers());
    }

    @Test
    public void removePerson(){
        ArrayList<Person> people = new ArrayList<>();
        Buyer man = new Buyer("ali" , "ali" , "vanaki" , "09107270737", "ali.vanaki100@gmail.com" , " 123");
        people.add(man);
        people.add(new Seller("ali" , "ali" , "vanaki" , "09107270737", "ali.vanaki100@gmail.com" , " 123" , "Apple" ));
        people.remove(man);
        ManagerAbilitiesController.deleteUser(man);
        assertEquals(people , ManagerAbilitiesController.getAllMembers());
    }

}
