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
        Label name = new Label();
        Label description = new Label();
        Label category = new Label();
        Label price = new Label();
        Label averageScore = new Label();
        name.setText(product.getName());
        description.setText(product.getDescription());
        category.setText(product.getCategory().getName());
        price.setText((product.getMoney()) + " $");
        averageScore.setText(Double.toString(product.getScore()));
    }


}
