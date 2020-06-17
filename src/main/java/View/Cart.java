package View;

import Model.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Cart extends Application {
    Image plus = new Image(Paths.get("src/main/java/view/images/plus.jpg").toUri().toString());
    Image minus = new Image(Paths.get("src/main/java/view/images/minus.png").toUri().toString());
    ImageView increase = new ImageView(plus);
    ImageView decrease = new ImageView(minus);

    @Override
    public void start(Stage stage) throws Exception {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label productName = new Label("Product");
        productName.setLayoutX(50);
        productName.setLayoutY(50);
        productName.setFont(new Font(25));
        parent.getChildren().add(productName);
        Label numberOfProduct = new Label("Number");
        numberOfProduct.setLayoutX(550);
        numberOfProduct.setLayoutY(50);
        numberOfProduct.setFont(new Font(25));
        parent.getChildren().add(numberOfProduct);
        Label productPrice = new Label("Price");
        productPrice.setLayoutX(350);
        productPrice.setLayoutY(50);
        productPrice.setFont(new Font(25));
        parent.getChildren().add(productPrice);
        Label finalPrice = new Label("Final Price");
        finalPrice.setLayoutX(750);
        finalPrice.setLayoutY(50);
        finalPrice.setFont(new Font(25));
        parent.getChildren().add(finalPrice);
        Label increaseOrDecrease = new Label("Increase/Decrease");
        increaseOrDecrease.setLayoutX(950);
        increaseOrDecrease.setLayoutY(50);
        increaseOrDecrease.setFont(new Font(25));
        parent.getChildren().add(increaseOrDecrease);


        Scene scene = new Scene(parent, 1280, 660);

        stage.setScene(scene);

        stage.show();
    }
}
