import Controller.PersonController;
import Database.SaveData;
import Model.Category.Category;
import Model.Discount;
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
        new Seller("amirsalar", "amirsalar", "ansari", "09131789201", "a@a.com", "09131789201", "yes");
        LocalTime localTime = LocalTime.of(21, 30);
        LocalTime localTime1 = LocalTime.of(22, 30);
        new Discount("981710", localTime, localTime1, (long) 50_000, 50);
        new Discount("981711", localTime, localTime1, (long) 50_000, 50);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Samsung");
        strings.add("Apple");
        strings.add("LG");
        new Category("Mobiles", null, strings);
        Menu menu = new Menu();
        Menu.currentMenu = menu;
        Menu.previousMenu = menu;
        Menu.executeMainMenu();
    }
}
