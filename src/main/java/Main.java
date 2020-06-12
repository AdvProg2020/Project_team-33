import Controller.PersonController;
import View.Menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (!PersonController.isManagerAccountCreate) {
            Pane pane = new Pane();
            Label label = new Label();
            label.setText("If you see this it means application run for first time");
            label.setFont(new Font("Arial", 20));
            label.setTextFill(Color.WHITE);
            pane.setStyle("-fx-background-color: black");
            pane.getChildren().add(label);
            Scene scene = new Scene(pane, 500, 400);
            stage.setScene(scene);
            stage.show();

        } else {
            URL url = new File("src/main/java/main.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stage.setTitle("Create Manger Account");
            Scene scene = new Scene(root, 500, 400);
            stage.setScene(scene);
            stage.show();
        }
    }
}
