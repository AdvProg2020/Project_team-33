package Controller;

import Model.Buyer;
import Model.Product;
import Model.Score;
import javafx.fxml.FXML;
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
    private Label name = new Label();

    @FXML
    private Label description = new Label();

    @FXML
    private Label price = new Label();

    @FXML
    private Label averageScore = new Label();


    public void score(int point) {
        if (product.isBuyerBoughtThisProduct(buyer)){
            Score score = new Score(buyer, point, product);
            product.addScore(score);
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
