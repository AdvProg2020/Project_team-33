package View.ProductPage;

import Controller.ProductController;
import Model.Product;
import View.Menu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ProductPage {

    public static void show(Product product) {
        AnchorPane parent = new AnchorPane();
        parent.setPrefHeight(308.0);
        parent.setPrefWidth(592.0);
        parent.setStyle("-fx-background-color: #858585");
        makeImageBox(parent, product);
        secondPane(parent, product);
        setProductsInPage(parent);
        createSortPanel(parent);
        Scene scene = new Scene(parent, 1280, 660);
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
        pane.prefHeight(346.0);
        pane.setPrefWidth(304.0);

        Label name = new Label("Name: " + product.getName());
        name.setTextFill(Color.BLACK);
        name.setFont(new Font(19));
        name.setPrefHeight(41.0);
        name.setPrefWidth(294.0);
        pane.getChildren().add(name);

        Label description = new Label("Description: " + product.getDescription());
        description.setTextFill(Color.GRAY);
        description.setFont(new Font(15));
        description.setPrefHeight(41.0);
        description.prefWidth(294.0);
        description.setLayoutY(41.0);
        pane.getChildren().add(description);

        Label category = new Label("Category: " + product.getDescription());
        category.setTextFill(Color.BLUE);
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
        number.setTextFill(Color.RED);
        number.setFont(new Font(15));
        number.setLayoutX(14.0);
        number.setLayoutY(150.0);
        number.setPrefHeight(41.0);
        number.setPrefWidth(50.0);
        pane.getChildren().add(number);


        Label money = new Label("Price: " + product.getMoney());
        money.setTextFill(Color.GREEN);
        money.setFont(new Font(15));
        money.setLayoutX(14.0);
        money.setLayoutY(191.0);
        money.setPrefWidth(50.0);
        money.setPrefHeight(41.0);
        pane.getChildren().add(money);

        parent.getChildren().add(pane);
    }


}
