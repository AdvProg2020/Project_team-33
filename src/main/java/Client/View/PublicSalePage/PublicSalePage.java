package Client.View.PublicSalePage;

import Client.Model.Comment;
import Client.Model.Product;
import Client.Model.PublicSale;
import Client.View.Menu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PublicSalePage {
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;

    private static Stage publicPage = new Stage();

    public static void show(PublicSale publicSale) throws IOException {
        URL url = new File("src/main/java/Client/View/PublicSalePage/PublicSalePage.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        publicPage.setTitle("Public Page");
        Scene scene = new Scene(root, 1280, 660);
        publicPage.setScene(scene);
    }

}
