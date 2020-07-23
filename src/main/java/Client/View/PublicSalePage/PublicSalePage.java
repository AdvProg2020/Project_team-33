package Client.View.PublicSalePage;

import Client.Model.Chat;
import Client.Model.Comment;
import Client.Model.Product;
import Client.Model.PublicSale;
import Client.Model.Users.Buyer;
import Client.Model.Users.Person;
import Client.View.Menu;
import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class PublicSalePage {
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;

    private static Stage publicPage = new Stage();
    private static PublicSale publicSale;
    public static Person person;
    public TextField inputMoney;
    public TextArea message;
    public ScrollPane scrollPane;

    public static void show(PublicSale publicSale) throws IOException {
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

    public void setMoney(MouseEvent mouseEvent) throws IOException {
        if (!inputMoney.getText().isEmpty()) {
            dataOutputStream.writeUTF("inputMoneyInPublicSale," + publicSale.getId() + "," + inputMoney.getText());
            dataOutputStream.flush();
            if (dataInputStream.readUTF().equals("pass")) {
                inputMoney.setStyle("-fx-border-color: ForestGreen");
                inputMoney.setText("");
            } else {
                inputMoney.setStyle("-fx-border-color: RED");
            }
        }
    }

    public void sendMessage(MouseEvent mouseEvent) throws IOException {
        if (!message.getText().isEmpty()) {
            String chat = message.getText();
            dataOutputStream.writeUTF("sendMessageInPublicSale,," + publicSale.getId() + ",," + chat);
            dataOutputStream.flush();
        }
        updateMessages();
    }

    public void updateMessages() throws IOException {
        dataOutputStream.writeUTF("getPublicSaleChat," + publicSale.getId());
        dataOutputStream.flush();
        int size = Integer.parseInt(dataInputStream.readUTF());
        ArrayList<Chat> allChats = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            Gson gson = new Gson();
            Chat chat = gson.fromJson(dataInputStream.readUTF(), Chat.class);
            allChats.add(chat);
        }

        for (Chat chat : allChats) {
            HBox hBox = new HBox();
            Label name = new Label();
            Label message = new Label();
            name.setFont(new Font(10));
            message.setFont(new Font(18));

            name.setText(chat.getPerson().getName());
            message.setText(chat.getMessage());

            if (chat.getPerson().getUsername().equals(person.getUsername())) {
                hBox.setStyle("-fx-background-color: DodgerBlue");
            } else {
                hBox.setStyle("-fx-background-color: AliceBlue");
            }

            hBox.getChildren().addAll(name, message);

            scrollPane.getChildrenUnmodifiable().add(hBox);
        }

    }

    public void update(MouseEvent mouseEvent) throws IOException {
        updateMessages();
    }
}
