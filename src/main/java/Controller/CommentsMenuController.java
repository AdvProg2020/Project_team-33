package Controller;

import Model.Comment;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentsMenuController implements Initializable {
    public Product product;

    @FXML
    public TextField comment;

    @FXML
    private TableView<Comment> tableView;

    @FXML
    private TableColumn<Comment, String> nameColumn;

    @FXML
    private TableColumn<Comment, String> commentColumn;

    @FXML
    private TableColumn<Comment, Image> hasBeenBought;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("personWhoGiveComment"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        hasBeenBought.setCellValueFactory(new PropertyValueFactory<>("isPersonBuyProduct"));
        tableView.setItems(getComments());
    }


    public ObservableList<Comment> getComments() {
        ObservableList<Comment> comments = FXCollections.observableArrayList();
        comments.addAll(ProductMenuController.product.getAllComments());
        return comments;
    }

    public void addComment(MouseEvent mouseEvent) {
        if (comment.getText() == null || comment.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("write your comment first");
            alert.showAndWait();
        } else {
            if (ProductMenuController.buyer != null) {
                Comment comment = new Comment(ProductMenuController.buyer, ProductMenuController.product, true, this.comment.toString());
                ProductMenuController.product.addComment(comment);
            }
        }

    }
}
