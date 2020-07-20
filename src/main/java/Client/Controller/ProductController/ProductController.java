package Client.Controller.ProductController;

import Server.Model.Category.Category;
import Server.Model.Logs.BuyLog;
import Server.Model.Product;
import Server.Model.Score;

import java.util.ArrayList;

import Server.Model.Users.Buyer;
import Client.View.CommentsPage;
import Client.View.LoginAndRegister.LoginMenu;
import Client.View.ProductPage.ProductsPage;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ProductController {

    public static ArrayList<Product> getAllProducts() {
        return Product.getAllProducts();
    }

    public static ArrayList<Category> getAllCategories() {
        return Category.getAllCategory();
    }

    public static ArrayList<Product> getAllCategoryProducts(Category category) {
        return category.getAllProduct();
    }

    public static Product product;
    public Buyer buyer;

    public ProductController(Product product) throws IOException {
        ProductController.product = product;
        if (LoginMenu.currentPerson instanceof Buyer) {
            this.buyer = (Buyer) LoginMenu.currentPerson;
        }
    }

    public void score(int point) {
        if (buyer != null && product.isBuyerBoughtThisProduct(buyer)) {
            if (!Score.isPersonScoredBefore(buyer, product)) {
                Score score = new Score(buyer, point, product);
                product.addScore(score);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You have to buy it first");
            alert.showAndWait();
        }
    }

    public static boolean isBuyerBuyThisProduct(Buyer buyer, Product product) {
        ArrayList<Product> products = new ArrayList<>();
        for (BuyLog buyLog : buyer.getLog()) {
            for (Product product1 : buyer.getProductsInLog(buyLog)) {
                products.add(product1);
            }
        }

        for (Product product1 : products) {
            if (product == product1) {
                return true;
            }
        }
        return false;
    }

    public static void scoreController(int point, Product product, Buyer buyer) {
        Score score = new Score(buyer, point, product);
        product.addScore(score);
    }

    public void pointOne(MouseEvent mouseEvent) {
        score(1);
    }

    public void pointTwo(MouseEvent mouseEvent) {
        score(2);
    }

    public void pointThree(MouseEvent mouseEvent) {
        score(3);
    }

    public void pointFour(MouseEvent mouseEvent) {
        score(4);
    }

    public void pointFive(MouseEvent mouseEvent) {
        score(5);
    }

    public static void back() throws IOException, ClassNotFoundException {
        ProductsPage.show();
    }
}
