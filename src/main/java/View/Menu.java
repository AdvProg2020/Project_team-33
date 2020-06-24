package View;

import Controller.PersonController;
import Model.Users.Buyer;
import Model.Users.Seller;
import View.BuyerMenu.BuyerPersonalArea;
import View.LoginAndRegister.LoginMenu;
import View.LoginAndRegister.RegisterMenu;
import View.ManagrMenu.ManagerMenu;
import View.SellerMenu.SellerMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Menu {
    public static Menu currentMenu;
    public static Menu previousMenu;
    public static Stage stage = new Stage();
    public static Scene scene;

    public static void executeMainMenu() throws IOException {
        URL url = new File("src/main/java/View/mainMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Create Manger Account");
        scene = new Scene(root, 914, 514);
        stage.setScene(scene);
        stage.show();
    }

    private void checkMainManager() {
        if (!PersonController.isManagerAccountCreate) {
            Pane pane = new Pane();
            Label label = new Label();
            label.setText("Hello and Welcome!\nYou have to create manager account at first");
            label.setFont(new Font("Arial", 20));
            label.setTextFill(Color.WHITE);
            pane.setStyle("-fx-background-color: black");
            pane.getChildren().add(label);
            scene = new Scene(pane, 1280, 660);
            Button button = new Button("Go On!");
            button.setLayoutX(200);
            button.setOnMouseClicked(e -> {
                RegisterMenu registerMenu = new RegisterMenu();
                currentMenu = registerMenu;
                registerMenu.show();
            });
            pane.getChildren().add(button);
        }
    }

    public void userArea(MouseEvent mouseEvent) throws IOException {
        if (LoginMenu.currentPerson == null) {
            LoginMenu loginMenu = new LoginMenu();
            currentMenu = loginMenu;
            loginMenu.loginProcess();

        } else {
            if (LoginMenu.currentPerson instanceof Seller) {
                SellerMenu sellerMenu = new SellerMenu();
                sellerMenu.showPersonalArea();
            } else if (LoginMenu.currentPerson instanceof Buyer) {
                BuyerPersonalArea.showPersonalArea();
            } else {
                ManagerMenu managerMenu = new ManagerMenu();
//                currentMenu = managerMenu;
                managerMenu.showPersonalArea();
            }
        }
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public void productMenu(MouseEvent mouseEvent) throws IOException {

    }

    public void auctions(MouseEvent mouseEvent) {
    }

    public void executeUserArea() {

    }

    public void executeProductMenu() {

    }

    public void executeAuctions() {

    }

    public void show() {

    }

}

