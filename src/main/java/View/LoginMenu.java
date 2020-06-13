package View;

import Model.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu extends Menu {
    public static Person currentPerson;

    public static void loginProcess(Stage stage, Scene scene) throws IOException {
        if (LoginMenu.currentPerson == null) {
            URL url = new File("src/main/java/view/userAreaForUnknownNullPerson.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            scene = new Scene(root, 1280, 660);
            stage.setScene(scene);
            stage.show();
        } else {

        }
    }

}
