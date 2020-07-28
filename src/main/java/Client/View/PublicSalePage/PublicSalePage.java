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
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

public class PublicSalePage {
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;
    private static String token = Menu.token;

    private static Stage publicPage = new Stage();
    private static String publicSale;
    public static Person person;
    public TextField inputMoney;
    public TextArea message;
    public VBox chatBox;
    public ImageView imageView;
    public ImageView moneyImageView;

    public static void show(String publicSale) throws IOException {
        dataOutputStream.writeUTF("getPerson," + token);
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        person = gson.fromJson(json.substring(6), Buyer.class);

        PublicSalePage.publicSale = publicSale;
        URL url = new File("src/main/java/Client/View/PublicSalePage/PublicSaleMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        publicPage.setTitle("Public Page");
        Scene scene = new Scene(root, 600, 400);
        publicPage.setScene(scene);
        publicPage.show();
    }

    public void setMoney(MouseEvent mouseEvent) throws IOException {
        if (!inputMoney.getText().isEmpty()) {
            dataOutputStream.writeUTF("inputMoneyInPublicSale," + publicSale + "," + inputMoney.getText() + "," + token);
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
            message.setText("");
            dataOutputStream.writeUTF("sendMessageInPublicSale," + publicSale + "," + chat + "," + token);
            dataOutputStream.flush();
        }
        updateMessages();
    }

    public void updateMessages() throws IOException {
        dataOutputStream.writeUTF("getPublicSaleEndTime," + publicSale + "," + token);
        dataOutputStream.flush();
        String[] splitInput1 = dataInputStream.readUTF().split("-");
        LocalTime endTime = LocalTime.of(Integer.parseInt(splitInput1[0]), Integer.parseInt(splitInput1[1]));

        if (LocalTime.now().compareTo(endTime) < 0) {
            dataOutputStream.writeUTF("getPublicSaleChat," + publicSale + "," + token);
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<String> allChats = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String chat = dataInputStream.readUTF();
                allChats.add(chat);
            }

            chatBox.getChildren().clear();
            for (String chat : allChats) {
                Pane pane = new Pane();
                HBox hBox = new HBox();
                Label name = new Label();
                Label message = new Label();
                name.setFont(new Font(8));
                message.setFont(new Font(15));

                String[] splitInput = chat.split("--");

                name.setText(splitInput[1]);
                message.setText(splitInput[0]);

                hBox.getChildren().addAll(name, message);

                if (splitInput[1].equals(person.getUsername())) {
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
        } else {
            dataOutputStream.writeUTF("expirePublicSale," + publicSale + "," + token);
            dataOutputStream.flush();
            String[] splitInput = dataInputStream.readUTF().split("-");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("public sale expired!!");
            alert.setContentText("The winner is: " + splitInput[0]);
            alert.showAndWait();
            //Todo
            publicPage.close();
        }

    }

    public void update(MouseEvent mouseEvent) throws IOException {
        updateMessages();
        Image image = new Image(Paths.get("src/main/java/Client/view/images/blue-plus-icon.png").toUri().toString());
        imageView.setImage(image);
        Image image2 = new Image(Paths.get("src/main/java/Client/View/images/true.jpg").toUri().toString());
        moneyImageView.setImage(image2);
    }
}
