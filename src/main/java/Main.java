import Controller.PersonController;
import Database.SaveData;
import Model.Category.Category;
import Model.Discount;
import Model.Product;
import Model.Requests.RequestAddSeller;
import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Person;
import Model.Users.Seller;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Manager mainManager = new Manager("amk_amir", "Amir Mahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "Appleid1234321");
        PersonController.isManagerAccountCreate = true;
        PersonController.mainManager = mainManager;
        new Buyer("saba_sk", "saba", "keshavarz", "09912310335", "saba@yahoo.com", "sabasasa");
        LoginMenu.currentPerson = mainManager;
        Seller seller = new Seller("amirsalar", "amirsalar", "ansari", "09131789201", "a@a.com", "09131789201", "yes");
        Seller seller1 = new Seller("arya", "Arya", "Jalali", "09123456789", "a@a.com", "areare", "Apple");
        LocalTime localTime = LocalTime.of(21, 30);
        LocalTime localTime1 = LocalTime.of(22, 30);
        new Discount("981710", localTime, localTime1, (long) 50_000, 50);
        new Discount("981711", localTime, localTime1, (long) 50_000, 50);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Samsung");
        strings.add("Apple");
        strings.add("LG");
        Category category = new Category("Mobiles", null, strings);
        new Product("981710", "galaxy S6", "Samsung", 2000000, seller, category, "A good phone", "Unknown");
        new Product("981711", "Iphone x", "Apple", 2000000, seller1, category, "A good phone", "Unknown");
        new Product("981712", "Iphone xs", "Apple", 2000000, seller1, category, "A good phone", "Unknown");
        new Product("981713", "Iphone xs max", "Apple", 2000000, seller1, category, "A good phone", "Unknown");
        ArrayList<String> strings1 = new ArrayList<>();
        strings.add("Asus");
        strings.add("Apple");
        strings.add("Lenovo");
        Category category1 = new Category("Laptops", null, strings1);
        new Product("981714", "Zenbook", "Asus", 2000000, seller1, category1, "A good laptop", "Unknown");
        new Product("981715", "macbook pro", "Apple", 2000000, seller1, category1, "A good laptop", "Unknown");
        Menu menu = new Menu();
        Menu.currentMenu = menu;
        Menu.previousMenu = menu;
        Menu.executeMainMenu();
    }
}
