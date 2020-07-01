package Controller;

import Model.Category.Category;
import Model.Product;
import Model.Score;

import java.util.ArrayList;

public class ProductController {

    public static ArrayList<Product> getAllProducts() {
        return Product.getAllProducts();
    }

    public static ArrayList<Category> getAllCategories() {
        return Category.getAllCategory();
    }

    //    public Pane imageBox;
//    public static Product product;
//    public static Buyer buyer;
//    private SellerOfProduct selectedSeller;
//    ObservableList<String> allSellersList = FXCollections.observableArrayList();
//
//    public ProductMenuController(Product product, Buyer buyer) {
//        ProductMenuController.product = product;
//        ProductMenuController.buyer = buyer;
//    }
//
//    @FXML
//    private Label name = new Label(product.getName());
//
//    @FXML
//    private Label description = new Label(product.getDescription());
//
//    @FXML
//    private Label category = new Label(product.getCategory().getName());
//
//    @FXML
//    private Label price = new Label();
//
//    @FXML
//    private Label averageScore = new Label(Double.toString(ProductController.calculateAverageScore(product)));
//
//    @FXML
//    private ChoiceBox<String> allSellersBox;
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        loadPhoto();
//        loadData();
//        allSellersBox.setValue(allSellersList.get(0));
//        selectedSeller = product.getSellerByName(allSellersBox.getValue());
//        price.setText(Double.toString(selectedSeller.getPrice()));
//    }
//
//    private void loadData() {
//        allSellersList.removeAll();
//        for (SellerOfProduct sellerOfProduct : product.getAllSeller()) {
//            allSellersList.add(sellerOfProduct.getSeller().getName());
//        }
//        allSellersBox.getItems().addAll(allSellersList);
//    }
//
//    private void loadPhoto() {
//        ImageView imageView = new ImageView();
//        Image image = new Image(Paths.get("src/main/java/view/images/" + product.getName() + ".jpg").toUri().toString());
//        imageView.setImage(image);
//        imageView.fitHeightProperty();
//        imageView.fitWidthProperty();
//        imageView.setPreserveRatio(true);
//        imageView.setSmooth(true);
//        imageView.setCache(true);
//        imageBox.getChildren().add(imageView);
//    }
//
//    public void score(int point) {
//        if (product.isBuyerBoughtThisProduct(buyer)) {
//            Score score = new Score(buyer, point, product);
//            product.addScore(score);
//            averageScore.setText(Double.toString(ProductController.calculateAverageScore(product)));
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("You have to buy it first");
//            alert.showAndWait();
//        }
//    }
//
//    public void pointZero(MouseEvent mouseEvent) {
//        score(0);
//    }
//
//    public void pointOne(MouseEvent mouseEvent) {
//        score(1);
//    }
//
//    public void pointTwo(MouseEvent mouseEvent) {
//        score(2);
//    }
//
//    public void pointThree(MouseEvent mouseEvent) {
//        score(3);
//    }
//
//    public void pointFour(MouseEvent mouseEvent) {
//        score(4);
//    }
//
//    public void pointFive(MouseEvent mouseEvent) {
//        score(5);
//    }
//
//    public void addToCart(MouseEvent mouseEvent) {
//        if (LoginMenu.currentPerson == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("You have to login first");
//            alert.showAndWait();
//        } else {
//            buyer.getUserCart().setProductInCart(product);
//        }
//    }
//
//    public void addComment(MouseEvent mouseEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("CommentsMenu.fxml"));
//        Stage commentStage = new Stage();
//        commentStage.setTitle("Comments");
//        commentStage.setScene(new Scene(root, 600, 600));
//        commentStage.showAndWait();
//    }
//
    public static double calculateAverageScore(Product product) {
        int sum = 0;
        int number = 0;
        for (Score score : product.getAllScores()) {
            sum += score.getScore();
            number++;
        }
        return (double) sum / number;
    }
}

