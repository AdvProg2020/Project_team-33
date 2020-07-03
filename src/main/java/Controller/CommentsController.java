package Controller;

import Model.Comment;
import Model.Product;
import Model.Users.Buyer;
import View.LoginAndRegister.LoginMenu;
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

public class CommentsController{

    public static void addComment(TextField comment, Product product) {
        if (comment.getText() == null || comment.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("write your comment first");
            alert.showAndWait();
        } else {
            if (LoginMenu.currentPerson != null) {
                boolean isBuyerBoughtThisProduct;
                if (LoginMenu.currentPerson instanceof Buyer){
                    isBuyerBoughtThisProduct = product.isBuyerBoughtThisProduct((Buyer) LoginMenu.currentPerson);
                }else {
                    isBuyerBoughtThisProduct = false;
                }
                Comment newComment = new Comment(LoginMenu.currentPerson, product,
                        isBuyerBoughtThisProduct , comment.getText());
                product.addComment(newComment);
                comment.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You have to login it first");
                alert.showAndWait();
            }
        }
    }
}
