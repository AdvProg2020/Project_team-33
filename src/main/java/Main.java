import Controller.PersonController;
import Database.SaveData;
import Model.Discount;
import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Person;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.currentPerson = new Manager("amk_amir", "Amir Mahdi", "Kousheshi", "09912310335", "amk_amir82@yahoo.com", "Appleid1234321");
        PersonController.isManagerAccountCreate = true;
        PersonController.mainManager = (Manager) LoginMenu.currentPerson;
        new Buyer("saba_sk", "saba", "keshavarz", "09912310335", "saba@yahoo.com", "sabasasa");
        LocalTime localTime = LocalTime.of(21, 30);
        LocalTime localTime1 = LocalTime.of(22, 30);
        new Discount("981710", localTime, localTime1, (long) 50, 50);
        new Discount("981711", localTime, localTime1, (long) 50, 50);

        Menu menu = new Menu();
        Menu.currentMenu = menu;
        Menu.previousMenu = menu;
        Menu.executeMainMenu();
    }
}
