package Controller;

import Model.Buyer;
import Model.Comment;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.util.Collections;

public class CommentsMenuController {
    public Product product;

    public TextField comment;

    @FXML
    private TableView<Comment> tableView;

    @FXML
    private TableColumn<Comment, String> nameColumn;

    @FXML
    private TableColumn<Comment, String> commentColumn;

    @FXML
    private TableColumn<Comment, Image> hasBeenBought;

    public ObservableList<Comment> getUsers(){
        ObservableList<Comment> comments = FXCollections.observableArrayList();
    }

    public void addComment(MouseEvent mouseEvent) {

    }
}
