package View.ProductPage;

import Model.Product;
import Model.Users.Seller;
import javafx.scene.layout.VBox;

import java.awt.*;

public class ProductMenuG {
    private Product product;
    Seller selectedSeller = null;

    public ProductMenuG(Product product) {
        this.product = product;
    }

    public void showMenu(){
        VBox vBox = new VBox();
        Label nameLabel = new Label("name: " + product.getName());

    }
}