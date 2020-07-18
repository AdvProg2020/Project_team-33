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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentsController{

    public static void addComment(TextField comment, Product product, DataOutputStream dataOutputStream, DataInputStream dataInputStream) throws IOException {
        dataOutputStream.writeUTF("addComment," + product.getProductID() + "," + comment.getText());
        dataOutputStream.flush();
        String[] splitInput = dataInputStream.readUTF().split("-");
        if (splitInput[0].equals("1")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("write your comment first");
            alert.showAndWait();
        } else {
            if (splitInput[1].equals("1")) {
                comment.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You have to login it first");
                alert.showAndWait();
            }
        }
    }

}
