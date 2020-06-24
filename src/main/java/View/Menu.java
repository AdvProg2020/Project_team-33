package View;

import View.LoginAndRegister.LoginMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Menu {
    public static Stage stage = new Stage();
    public static Scene scene;

    public static void executeMainMenu() throws IOException {
//        if (!PersonController.isManagerAccountCreate) {
//            Pane pane = new Pane();
//            Label label = new Label();
//            label.setText("Hello and Welcome!\nYou have to create manager account at first");
//            label.setFont(new Font("Arial", 20));
//            label.setTextFill(Color.WHITE);
//            pane.setStyle("-fx-background-color: black");
//            pane.getChildren().add(label);
//            scene = new Scene(pane, 1280, 660);
//            Button button = new Button("Go On!");
//            button.setLayoutX(200);
//            button.setOnMouseClicked(e -> {
//                try {
//                    RegisterMenu.createStaticAccount(stage);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            });
//            pane.getChildren().add(button);
//        } else {
        URL url = new File("src/main/java/View/mainMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Create Manger Account");
        scene = new Scene(root, 914, 514);
//        }
        stage.setScene(scene);
        stage.show();
    }

    public void userArea(MouseEvent mouseEvent) throws IOException {
        LoginMenu.loginProcess(stage, scene);
    }

    public void productMenu(MouseEvent mouseEvent) throws IOException {

    }

    public void auctions(MouseEvent mouseEvent) {
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public static void executeUserArea() {

    }

    public static void executeProductMenu() {

    }

    public static void executeAuctions() {

    }

}
