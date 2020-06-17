package View.BuyerMenu;

import View.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ShowBuyLogsForBuyer extends Application {

    public static void show() {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label productName = new Label("Serial");
        productName.setLayoutX(50);
        productName.setLayoutY(50);
        productName.setFont(new Font(25));
        parent.getChildren().add(productName);

        Label productPrice = new Label("Price");
        productPrice.setLayoutX(350);
        productPrice.setLayoutY(50);
        productPrice.setFont(new Font(25));
        parent.getChildren().add(productPrice);

        Label numberOfProduct = new Label("Date");
        numberOfProduct.setLayoutX(550);
        numberOfProduct.setLayoutY(50);
        numberOfProduct.setFont(new Font(25));
        parent.getChildren().add(numberOfProduct);

        Label increaseOrDecrease = new Label("Off");
        increaseOrDecrease.setLayoutX(750);
        increaseOrDecrease.setLayoutY(50);
        increaseOrDecrease.setFont(new Font(25));
        parent.getChildren().add(increaseOrDecrease);

        Label finalPrice = new Label("Final Price");
        finalPrice.setLayoutX(950);
        finalPrice.setLayoutY(50);
        finalPrice.setFont(new Font(25));
        parent.getChildren().add(finalPrice);


        Scene scene = new Scene(parent, 1280, 660);

        Menu.stage.setScene(scene);

        Menu.stage.show();
    }


    @Override
    public void start(Stage stage) throws Exception {
        show();
    }
}

