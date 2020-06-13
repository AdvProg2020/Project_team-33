package Controller;

import Model.Buyer;
import Model.Product;
import Model.Score;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ProductMenuController {
    private Product product;
    private Buyer buyer;

    public ProductMenuController(Product product, Buyer buyer) {
        this.product = product;
        this.buyer = buyer;
    }

    @FXML
    private Label name = new Label(product.getName());

    @FXML
    private Label description = new Label(product.getDescription());

    @FXML
    private Label price = new Label();

    @FXML
    private Label averageScore = new Label(Double.toString(ProductController.calculateAverageScore(product)));


    public void score(int point) {
        if (product.isBuyerBoughtThisProduct(buyer)){
            Score score = new Score(buyer, point, product);
            product.addScore(score);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You have to buy it first");
            alert.showAndWait();
        }
    }

    public void pointZero(MouseEvent mouseEvent) {
        score(0);
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
}
