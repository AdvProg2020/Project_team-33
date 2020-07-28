package Client.Controller;

import Client.Model.Product;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommentsController{

    public static void addComment(TextField comment, Product product, DataOutputStream dataOutputStream, DataInputStream dataInputStream, String token) throws IOException {
        dataOutputStream.writeUTF("addComment," + product.getProductID() + "," + comment.getText() + "," + token);
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
