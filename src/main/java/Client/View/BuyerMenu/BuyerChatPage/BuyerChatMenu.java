package Client.View.BuyerMenu.BuyerChatPage;

import Client.Model.Chat;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BuyerChatMenu {
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;
    private static String token = Menu.token;

    private static Stage buyerChatPage = new Stage();
    private static Supporter supporter;
    public static Person person;
    public TextField inputMoney;
    public TextArea message;
    public VBox chatBox;

    public static void show(Supporter supporter) throws IOException {
        BuyerChatMenu.supporter = supporter;

        dataOutputStream.writeUTF("getPerson," + token);
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        person = gson.fromJson(json.substring(6), Buyer.class);

        URL url = new File("src/main/java/Client/View/BuyerMenu/BuyerChatPage/BuyerChatMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        buyerChatPage.setTitle("Chat Page");
        Scene scene = new Scene(root, 600, 400);
        buyerChatPage.setScene(scene);
    }

    public void sendMessage(MouseEvent mouseEvent) throws IOException {
        if (!message.getText().isEmpty()) {
            String chat = message.getText();
            dataOutputStream.writeUTF("sendMessageBuyerSupporter," + supporter.getId() + "," + chat + "," + token);
            dataOutputStream.flush();
        }
        updateMessages();
    }

    public void updateMessages() throws IOException {
        dataOutputStream.writeUTF("getBuyerSupporterChat," + supporter.getUsername() + "," + token);
        dataOutputStream.flush();
        int size = Integer.parseInt(dataInputStream.readUTF());
        ArrayList<Chat> allChats = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            Gson gson = new Gson();
            Chat chat = gson.fromJson(dataInputStream.readUTF(), Chat.class);
            allChats.add(chat);
        }

        chatBox.getChildren().clear();
        for (Chat chat : allChats) {
            Pane pane = new Pane();
            HBox hBox = new HBox();
            Label name = new Label();
            Label message = new Label();
            name.setFont(new Font(8));
            message.setFont(new Font(15));

            name.setText(chat.getPerson().getName());
            message.setText(chat.getMessage());

            hBox.getChildren().addAll(name, message);

            if (chat.getPerson().getUsername().equals(supporter.getUsername())) {
                hBox.setStyle("-fx-background-color: DodgerBlue");
                hBox.setPrefWidth(300);
                hBox.setLayoutX(350);
            } else {
                hBox.setStyle("-fx-background-color: AliceBlue");
                hBox.setPrefWidth(300);
            }

            pane.getChildren().add(hBox);

            chatBox.getChildren().add(pane);
        }
    }

    public void update(MouseEvent mouseEvent) throws IOException {
        updateMessages();
    }

}
