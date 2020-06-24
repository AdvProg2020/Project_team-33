package View.LoginAndRegister;

import Model.Users.Person;
import View.Menu;
import View.Menus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LoginMenu extends Menu implements Menus {
    public static Person currentPerson;

    public void loginProcess() throws IOException {
        URL url = new File("src/main/java/view/LoginAndRegister/userAreaForUnknownNullPerson.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    @Override
    public void show() {
        try {
            loginProcess();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
