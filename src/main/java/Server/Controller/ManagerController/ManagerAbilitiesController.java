package Server.Controller.ManagerController;

import Server.Model.Category.Category;
import Server.Model.Discount;
import Server.Model.Requests.Request;
import Server.Model.Users.Person;

import java.time.LocalTime;
import java.util.ArrayList;

public class ManagerAbilitiesController {


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

    public static ArrayList<Category> getAllCategories() {
        return Category.getAllCategory();
    }

    public static void deleteCategory(Category category) {
        Category.deleteCategory(category);
    }

    public static void editCategory(Category category, String field, String newChange) {
        if (field.equalsIgnoreCase("name")) {
            category.setName(newChange);
        } else if (field.equalsIgnoreCase("detail1")) {
            category.setDetail1(newChange);
        } else if (field.equalsIgnoreCase("detail2")) {
            category.setDetail2(newChange);
        } else if (field.equalsIgnoreCase("detail3")) {
            category.setDetail3(newChange);
        }
    }

    public static ArrayList<Request> getAllRequests() {
        return Request.getAllRequests();
    }

    public static void deleteRequest(Request request) {
        Request.deleteRequest(request);
    }

    public static void setConditionForRequest(Request request, String condition) {
        request.setCondition(condition);
    }


}
