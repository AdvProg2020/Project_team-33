package Controller;

import Model.Category.Category;
import Model.Product;
import Model.Score;

import java.util.ArrayList;

import Model.*;
import Model.Users.Buyer;
import Model.Users.Seller;
import View.CommentsPage;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
import View.ProductPage.ProductsPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
        if (LoginMenu.currentPerson != null && LoginMenu.currentPerson instanceof Buyer) {
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

    public static void addToCart(Product product) {
//        if (LoginMenu.currentPerson == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("You have to login first");
//            alert.showAndWait();
//        } else {
//            buyer.getUserCart().setProductInCart(product);
//        }
    }

    public static void addComment(Product product) throws IOException {
//        Parent root = FXMLLoader.load(ProductController.class.getResource("src/main/java/View/CommentsMenu.fxml"));
//        Stage commentStage = new Stage();
//        commentStage.setTitle("Comments");
//        commentStage.setScene(new Scene(root, 600, 600));
//        commentStage.showAndWait();
        CommentsPage.show(product);
    }

    public static double calculateAverageScore(Product product) {
        int sum = 0;
        int number = 0;
        for (Score score : product.getAllScores()) {
            sum += score.getScore();
            number++;
        }
        return (double) sum / number;
    }

    public static void back() {
        ProductsPage.show();
    }
}

