package Client.View.BuyerMenu.BuyerChatPage;

import Client.Model.PublicSale;
import Client.Model.Users.Buyer;
import Client.Model.Users.Person;
import Client.Model.Users.Supporter;
import Client.View.Menu;
import Client.View.PublicSalePage.PublicSalePage;
import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BuyerChatMenu {
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;

    private static Stage publicPage = new Stage();
    private static PublicSale publicSale;
    public static Person person;
    public TextField inputMoney;
    public TextArea message;
    public ScrollPane scrollPane;

    public static void show(Supporter supporter) throws IOException {
        dataOutputStream.writeUTF("getPerson");
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        person = gson.fromJson(json.substring(6), Buyer.class);

        PublicSalePage.publicSale = publicSale;
        URL url = new File("src/main/java/Client/View/PublicSalePage/PublicSalePage.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        publicPage.setTitle("Public Page");
        Scene scene = new Scene(root, 1280, 660);
        publicPage.setScene(scene);
    }

    public void sendMessage(MouseEvent mouseEvent) {

    }
}
