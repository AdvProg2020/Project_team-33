package Controller.ManagerController;

import Model.Category.Category;
import Model.Discount;
import Model.Requests.Request;
import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Person;
import Model.Users.Seller;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ManagerAbilitiesControllerTest {

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
    }

    @Test
    public void getPeopleFromManger() {
        Person.people.clear();
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Buyer("mohammad", "mohammad", "daviran", "1234", "mr@gmail.com", " 123"));
        people.add(new Seller("mohammad", "mohammad", "daviran", "1234", "mr@gmail.com", " 123", "Apple"));
        assertEquals(people, ManagerAbilitiesController.getAllMembers());
    }

    @Test
    public void deleteUser(){
        Person.people.clear();
        Buyer buyer = new Buyer("mohammad", "mohammad", "daviran", "1234", "mr@gmail.com", " 123");
        ManagerAbilitiesController.deleteUser(buyer);
        boolean actual = Person.people.isEmpty();
        assertTrue(actual);
    }

    @Test
    public void getAllDiscountsAndDeleteDiscount(){
        ArrayList<Discount> discounts = new ArrayList<>();
        LocalTime start = LocalTime.now();
        LocalTime end = start.plusMinutes(10);
        Discount.getAllDiscounts().clear();
        Discount discount = new Discount("123", start, end, (long)10, 15);
        discounts.add(discount);
        assertEquals(discounts, ManagerAbilitiesController.getAllDiscounts());
        ManagerAbilitiesController.deleteDiscount(discount);
        boolean actual = ManagerAbilitiesController.getAllDiscounts().isEmpty();
        assertTrue(actual);
    }

    @Test
    public void editDiscount(){
        LocalTime start = LocalTime.now();
        LocalTime end = start.plusMinutes(10);
        Discount discount = new Discount("123", start, end, (long)10, 15);
        ManagerAbilitiesController.editDiscount(discount,"code", "1234");
        ManagerAbilitiesController.editDiscount(discount,"start time", "2:34");
        ManagerAbilitiesController.editDiscount(discount,"end time", "4:40");
        ManagerAbilitiesController.editDiscount(discount,"percent", "20");
        ManagerAbilitiesController.editDiscount(discount,"max discount", "30");
        assertEquals("1234", discount.getCode());
        LocalTime startTime = LocalTime.of(2, 34);
        assertEquals(startTime, discount.getStartTime());
        LocalTime endTime = LocalTime.of(4, 40);
        assertEquals(endTime, discount.getEndTime());
        assertEquals(20, discount.getDiscountPercent());
        Long max = (long)30;
        assertEquals(max, discount.getMaxDiscount());
    }

    @Test
    public void getAllCategoriesAndDeleteCategory(){
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<String> details = new ArrayList<>();
        details.add("hich");
        Category.getAllCategory().clear();
        Category category = new Category("toy", null, details);
        categories.add(category);
        assertEquals(categories, ManagerAbilitiesController.getAllCategories());
        ManagerAbilitiesController.deleteCategory(category);
        boolean actual = ManagerAbilitiesController.getAllCategories().isEmpty();
        assertTrue(actual);
    }

    @Test
    public void editCategory(){
        ArrayList<String> details = new ArrayList<>();
        details.add("hich");
        details.add("hich");
        details.add("hich");
        Category category = new Category("toy", null, details);
        ManagerAbilitiesController.editCategory(category, "name", "dairy");
        ManagerAbilitiesController.editCategory(category, "detail1", "1");
        ManagerAbilitiesController.editCategory(category, "detail2", "2");
        ManagerAbilitiesController.editCategory(category, "detail3", "3");
        assertEquals("dairy", category.getName());
        assertEquals("1", category.getDetail1());
        assertEquals("2", category.getDetail2());
        assertEquals("3", category.getDetail3());
    }

    @Test
    public void getAllRequestsAndDeleteRequest(){
        ArrayList<Request> requests = new ArrayList<>();
        Seller seller = new Seller("mohammad", "mohammad", "daviran", "1234",
                "mr@gmail.com", " 123", "Apple");
        Request.allRequests.clear();
        Request request = new Request("hello", "decline", seller);
        requests.add(request);
        assertEquals(requests, ManagerAbilitiesController.getAllRequests());
        ManagerAbilitiesController.deleteRequest(request);
        boolean actual = ManagerAbilitiesController.getAllRequests().isEmpty();
        assertTrue(actual);
    }

    @Test
    public void setConditionForRequest(){
        Seller seller = new Seller("mohammad", "mohammad", "daviran", "1234",
                "mr@gmail.com", " 123", "Apple");
        Request request = new Request("hello", "decline", seller);
        ManagerAbilitiesController.setConditionForRequest(request, "accept");
        assertEquals("accept", request.getCondition());
    }

}
