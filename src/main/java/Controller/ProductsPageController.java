package Controller;

import Model.Product;
import Model.Users.Buyer;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductsPageController {

    public static void goToProductPage(Product product) throws IOException {
        Parent root = FXMLLoader.load(ProductsPageController.class.getResource("ProductMenu.fxml"));
        ProductMenuController.product = product;
        if (LoginMenu.currentPerson != null) {
            ProductMenuController.buyer = (Buyer) LoginMenu.currentPerson;
        }
        Menu.stage.setTitle(product.getName());
        Menu.stage.setScene(new Scene(root, 600, 600));
        Menu.stage.showAndWait();
    }

}
