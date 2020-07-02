package Controller;

import Model.Category.Category;
import Model.Product;
import Model.Score;

import java.util.ArrayList;
import Model.*;
import Model.Users.Buyer;
import Model.Users.Seller;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
import View.ProductPage.ProductsPage;
import com.sun.tools.javac.Main;
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

public class ProductController implements Initializable {

    public static ArrayList<Product> getAllProducts() {
        return Product.getAllProducts();
    }

    public static ArrayList<Category> getAllCategories() {
        return Category.getAllCategory();
    }

    public Pane imageBox;
    public static Product product;
    public Buyer buyer;

    @FXML
    private Label name = new Label();

    @FXML
    private Label description = new Label();

    @FXML
    private Label category = new Label();

    @FXML
    private Label price = new Label();

    @FXML
    private Label averageScore = new Label();

    public ProductController(Product product) {
        ProductController.product = product;
        if (LoginMenu.currentPerson != null && LoginMenu.currentPerson instanceof Buyer){
            this.buyer = (Buyer)LoginMenu.currentPerson;
        }
    }

    public void goToProductPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProductMenu.fxml"));
        Menu.stage.setTitle(product.getName());
        Menu.stage.setScene(new Scene(root, 600, 600));
        Menu.stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(product.getName());
        description.setText(product.getDescription());
        category.setText(product.getCategory().getName());
        price.setText((product.getMoney())+" $");
        averageScore.setText(Double.toString(ProductController.calculateAverageScore(product)));
        loadPhoto();
    }

    private void loadPhoto() {
        ImageView imageView = product.getImageView();
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setLayoutX(10);
        imageView.setLayoutY(25);
        imageBox.getChildren().add(imageView);
    }

    public void score(int point) {
        if (buyer != null && product.isBuyerBoughtThisProduct(buyer)) {
            if (!Score.isPersonScoredBefore(buyer, product)) {
                Score score = new Score(buyer, point, product);
                product.addScore(score);
                averageScore.setText(Double.toString(ProductController.calculateAverageScore(product)));
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

    public void addToCart(MouseEvent mouseEvent) {
//        if (LoginMenu.currentPerson == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("You have to login first");
//            alert.showAndWait();
//        } else {
//            buyer.getUserCart().setProductInCart(product);
//        }
    }

    public void addComment(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CommentsMenu.fxml"));
        Stage commentStage = new Stage();
        commentStage.setTitle("Comments");
        commentStage.setScene(new Scene(root, 600, 600));
        commentStage.showAndWait();
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

    public void back(MouseEvent mouseEvent) {
        ProductsPage.show();
    }
}

