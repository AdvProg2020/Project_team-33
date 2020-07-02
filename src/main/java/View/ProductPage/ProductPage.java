package View.ProductPage;

import Model.Product;
import View.Menu;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ProductPage {

    public static void show(Product product) {
        AnchorPane parent = new AnchorPane();
        parent.setStyle("-fx-background-color: #858585");
        makeImageBox(parent);
        createCategoryPanel(parent);
        setProductsInPage(parent);
        createSortPanel(parent);
        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private static void makeImageBox(Pane parent) {
        Pane pane = new Pane();
        ImageView imageView = product.getImageView();
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setLayoutX(10);
        imageView.setLayoutY(25);
        pane.getChildren().add(imageView);

        parent.getChildren().add(pane);
    }


}
