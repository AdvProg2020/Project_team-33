import Controller.PersonController;
import View.Menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends Application {
    public static Stage stage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (!PersonController.isManagerAccountCreate) {
            Pane pane = new Pane();
            Label label = new Label();
            label.setText("Hello and Welcome!\nYou have to create manager account at first");
            label.setFont(new Font("Arial", 20));
            label.setTextFill(Color.WHITE);
            pane.setStyle("-fx-background-color: black");
            pane.getChildren().add(label);
            Scene scene = new Scene(pane, 500, 400);
            Button button = new Button("Go On!");
            button.setLayoutX(200);
            button.setOnMouseClicked(e -> {
                try {
                    URL url = new File("src/main/java/view/createManagerAccount.fxml").toURI().toURL();
                    Parent root = FXMLLoader.load(url);
                    Scene scene1 = new Scene(root, 1280, 660);
                    Main.stage.setScene(scene1);
                    Main.stage.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            pane.getChildren().add(button);
            stage.setScene(scene);
        } else {
            URL url = new File("src/main/java/main.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stage.setTitle("Create Manger Account");
            Scene scene = new Scene(root, 500, 400);
            stage.setScene(scene);
        }
        stage.show();
    }
}
