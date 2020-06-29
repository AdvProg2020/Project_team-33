package Controller.ManagerController;

import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Person;
import Model.Users.Seller;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ManagerController {

    @Test
    public void editPersonalInfoForManager() {
        Manager manager = new Manager("amk_amir", "AmirMahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "Appleid1234321");
        ManagerAbilitiesControllerTest.editPersonalInfo(manager, "phone", "09999999999");
        assertEquals("09999999999",manager.getPhone());
        ManagerAbilitiesControllerTest.editPersonalInfo(manager, "name", "Amir");
        ManagerAbilitiesControllerTest.editPersonalInfo(manager , "password" , "123");
        ManagerAbilitiesControllerTest.editPersonalInfo(manager , "email" , "ali.vanaki100@gmail.com");
        ManagerAbilitiesControllerTest.editPersonalInfo(manager , "name" , "ali");
        ManagerAbilitiesControllerTest.editPersonalInfo(manager, "family" , "vanaki");
        ManagerAbilitiesControllerTest.editPersonalInfo(manager , "phone" , "09107270737");
        assertEquals("ali", manager.getName());
        assertEquals("123" , manager.getPassword());
        assertEquals("ali.vanaki100@gmail.com" , manager.getEmail());
        assertEquals("ali" , manager.getName());
        assertEquals("vanaki" , manager.getFamily());
        assertEquals("09107270737" , manager.getPhone());
    }

    @Test
    public void getPeopleFromManger()
    {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Buyer("ali" , "ali" , "vanaki" , "09107270737", "ali.vanaki100@gmail.com" , " 123" ));
        people.add(new Seller("ali" , "ali" , "vanaki" , "09107270737", "ali.vanaki100@gmail.com" , " 123" , "Apple" ));
        assertEquals(people , ManagerAbilitiesControllerTest.getAllMembers());

    }

    @Test
    public void getProductsForManager() {
        assertEquals(null, ManagerAbilitiesControllerTest.getAllMembers());
    }

    @Test
    public void removePerson(){
        ArrayList<Person> people = new ArrayList<>();
        Buyer man = new Buyer("ali" , "ali" , "vanaki" , "09107270737", "ali.vanaki100@gmail.com" , " 123");
        people.add(man);
        people.add(new Seller("ali" , "ali" , "vanaki" , "09107270737", "ali.vanaki100@gmail.com" , " 123" , "Apple" ));
        people.remove(man);
        ManagerAbilitiesControllerTest.deleteUser(man);
        assertEquals(people , ManagerAbilitiesControllerTest.getAllMembers());
    }

}
