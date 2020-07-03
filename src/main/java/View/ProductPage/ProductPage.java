package View.ProductPage;

import Controller.ProductController.ProductController;
import Model.Cart;
import Model.Product;
import Model.Users.Buyer;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductPage {
    private static Cart staticCart;

    public static void show(Product product, Cart cart) {
        staticCart = cart;
        AnchorPane parent = new AnchorPane();
        parent.setPrefHeight(308.0);
        parent.setPrefWidth(308.0);
        parent.setStyle("-fx-background-color: #858585");
        makeImageBox(parent, product);
        secondPane(parent, product);
        Scene scene = new Scene(parent, 582.0, 346.0);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private static void makeImageBox(Pane parent, Product product) {
        Pane pane = new Pane();
        pane.setPrefHeight(346.0);
        pane.setPrefWidth(294.0);
        ImageView imageView = product.getImageView();
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setLayoutX(10);
        imageView.setLayoutY(25);
        pane.getChildren().add(imageView);
        pane.setStyle("-fx-background-color: Gainsboro");
        parent.getChildren().add(pane);
    }

    private static void secondPane(AnchorPane parent, Product product) {
        Pane pane = new Pane();
        pane.setLayoutX(288.0);
        pane.setPrefHeight(346.0);
        pane.setPrefWidth(294.0);
        pane.setStyle("-fx-background-color: Gainsboro");

        Label name = new Label("Name: " + product.getName());
        name.setTextFill(Color.BLACK);
        name.setFont(new Font(19));
        name.setPrefHeight(41.0);
        name.setPrefWidth(294.0);
        name.setLayoutX(14.0);
        pane.getChildren().add(name);

        Label description = new Label("Description: " + product.getDescription());
        description.setTextFill(Color.BLACK);
        description.setFont(new Font(15));
        description.setPrefHeight(41.0);
        description.prefWidth(294.0);
        description.setLayoutY(41.0);
        description.setLayoutX(14.0);
        pane.getChildren().add(description);

        Label category = new Label("Category: " + product.getCategory().getName());
        category.setTextFill(Color.BLACK);
        category.setFont(new Font(15));
        category.setLayoutX(14.0);
        category.setLayoutY(70.0);
        category.setPrefHeight(53.0);
        pane.getChildren().add(category);

        Label score = new Label("Average score: " + product.getScore());
        score.setTextFill(Color.YELLOW);
        score.setTextFill(Color.BLACK);
        score.setFont(new Font(15));
        score.setLayoutX(14.0);
        score.setLayoutY(123.0);
        score.prefHeight(27.0);
        score.prefWidth(105.0);
        pane.getChildren().add(score);

        Label number = new Label("Number: " + product.getNumberOfProducts());
        number.setTextFill(Color.BLACK);
        number.setFont(new Font(15));
        number.setLayoutX(14.0);
        number.setLayoutY(150.0);
        number.setPrefHeight(41.0);
        number.setPrefWidth(198.0);
        pane.getChildren().add(number);


        Label money = new Label("Price: " + product.getMoney() + "$");
        money.setTextFill(Color.BLACK);
        money.setFont(new Font(15));
        money.setLayoutX(14.0);
        money.setLayoutY(191.0);
        money.setPrefWidth(198.0);
        money.setPrefHeight(41.0);
        pane.getChildren().add(money);

        createButtons(pane, product);

        parent.getChildren().add(pane);
    }

    private static void createButtons(Pane pane, Product product) {
        Button addToCartButton = new Button("Add to cart");
        addToCartButton.setLayoutX(21.0);
        addToCartButton.setLayoutY(246.0);
        addToCartButton.setPrefHeight(60.0);
        addToCartButton.setPrefWidth(126.0);
        addToCartButton.setStyle("-fx-background-color: Aqua");
        addToCartButton.setCursor(Cursor.HAND);

        addToCartButton.setOnMouseClicked(e -> {
            if (LoginMenu.currentPerson instanceof Buyer) {
                ((Buyer) LoginMenu.currentPerson).getCart().addProductToCart(product);
            } else if (LoginMenu.currentPerson == null) {
                staticCart.addProductToCart(product);
            }
        });

        Button addComment = new Button("Add comment");
        addComment.setLayoutX(152.0);
        addComment.setLayoutY(246.0);
        addComment.setPrefHeight(60.0);
        addComment.setPrefWidth(126.0);
        addComment.setStyle("-fx-background-color: Aquamarine");

        addComment.setOnMouseClicked(e -> {
            ProductController.addComment(product);
        });

        Button back = new Button("Back");
        back.setTextFill(Color.WHITE);
        back.setLayoutX(21.0);
        back.setLayoutY(312.0);
        back.setPrefHeight(27.0);
        back.setPrefWidth(257.0);
        back.setStyle("-fx-background-color: Black");

        back.setOnMouseClicked(e -> {
            ProductController.back();
        });

        Button setScore = new Button("Set score");
        setScore.setStyle("-fx-background-color:Green ");
        setScore.setCursor(Cursor.HAND);
        setScore.setTextFill(Color.BLACK);
        setScore.setFont(new Font(15));
        setScore.setLayoutX(180.0);
        setScore.setLayoutY(118.0);
        setScore.prefHeight(27.0);
        setScore.prefWidth(105.0);
        setScore.setOnMouseClicked(e -> {
            if (LoginMenu.currentPerson instanceof Buyer) {
                Buyer buyer = (Buyer) LoginMenu.currentPerson;
                if (ProductController.isBuyerBuyThisProduct(buyer, product)) {
                    score(product, buyer);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You have to buy it first");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You have to buy it first");
                alert.showAndWait();
            }
        });

        pane.getChildren().addAll(addToCartButton, addComment, back, setScore);

    }

    private static void score(Product product, Buyer buyer) {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: grey");

        Button one = new Button();
        one.setStyle("-fx-background-radius: 40");
        one.setStyle("-fx-background-color: RED");
        one.setLayoutX(10);
        one.setCursor(Cursor.HAND);

        Button two = new Button();
        two.setStyle("-fx-background-radius: 40");
        two.setStyle("-fx-background-color: Orange");
        two.setLayoutX(50);
        two.setCursor(Cursor.HAND);

        Button three = new Button();
        three.setStyle("-fx-background-radius: 40");
        three.setStyle("-fx-background-color: Yellow");
        three.setLayoutX(90);
        three.setCursor(Cursor.HAND);

        Button four = new Button();
        four.setStyle("-fx-background-radius: 40");
        four.setStyle("-fx-background-color: LawnGreen");
        four.setLayoutX(130);
        four.setCursor(Cursor.HAND);

        Button five = new Button();
        five.setStyle("-fx-background-radius: 40");
        five.setStyle("-fx-background-color: SeaGreen");
        five.setLayoutX(170);
        five.setCursor(Cursor.HAND);

        one.setOnMouseClicked(e -> {
            ProductController.scoreController(1, product, buyer);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Done");
            alert.showAndWait();
        });

        two.setOnMouseClicked(e -> {
            ProductController.scoreController(2, product, buyer);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Done");
            alert.showAndWait();

        });

        three.setOnMouseClicked(e -> {
            ProductController.scoreController(3, product, buyer);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Done");
            alert.showAndWait();


        });

        four.setOnMouseClicked(e -> {
            ProductController.scoreController(4, product, buyer);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Done");
            alert.showAndWait();

        });

        five.setOnMouseClicked(e -> {
            ProductController.scoreController(5, product, buyer);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Done");
            alert.showAndWait();

        });

        parent.getChildren().addAll(one, two, three, four, five);

        Stage stage = new Stage();
        Scene scene = new Scene(parent, 200, 100);
        stage.setScene(scene);
        stage.show();
    }


}
