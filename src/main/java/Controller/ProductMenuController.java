package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductMenuController implements Initializable {
    private Product product;
    private Buyer buyer;
    private Seller selectedSeller;
    ObservableList<String> allSellersList = FXCollections.observableArrayList("hey", " hiy");

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

    @FXML
    private ChoiceBox<String> allSellersBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allSellersBox.setItems(allSellersList);

    }

    private void loadData(){
        allSellersList.removeAll();
        for (SellerOfProduct sellerOfProduct : product.getAllSeller()) {
            allSellersList.add(sellerOfProduct.getSeller().getName());
        }
    }

    public void score(int point) {
        if (product.isBuyerBoughtThisProduct(buyer)){
            Score score = new Score(buyer, point, product);
            product.addScore(score);
            averageScore.setText(Double.toString(ProductController.calculateAverageScore(product)));
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

    public void addToBuyLog(MouseEvent mouseEvent) {

    }

    public void addComment(MouseEvent mouseEvent) {

    }

}
