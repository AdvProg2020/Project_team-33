package Client.View.LoginAndRegister;

import Server.Model.Users.Person;
import Client.View.Menu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LoginMenu extends Menu {
    public static Person currentPerson;

    public void loginProcess() throws IOException {
        URL url = new File("src/main/java/Client/view/LoginAndRegister/userAreaForUnknownNullPerson.fxml").toURI().toURL();
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
