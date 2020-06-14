package Controller;

import Model.Buyer;
import Model.Comment;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class CommentsMenuController {

    public TextField comment;

    @FXML
    private TableView<Comment> tableView;

    @FXML
    private TableColumn<Comment, String> nameColumn;

    @FXML
    private TableColumn<Comment, String> commentColumn;

    @FXML
    private TableColumn<Comment, Image> hasBeenBought;

    public void addComment(MouseEvent mouseEvent) {

    }
}
