package Client.View;

import Client.Model.Users.*;
import Client.View.BuyerMenu.BuyerMenu;
import Client.View.LoginAndRegister.LoginMenu;
import Client.View.LoginAndRegister.RegisterMenu;
import Client.View.ManagerMenu.ManagerMenu;
import Client.View.ProductPage.ProductsPage;
import Client.View.SellerMenu.SellerMenu;
import Client.View.SupporterMenu.SupporterMenu;
import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;

public class Menu {
    public static Stage stage = new Stage();
    public static Scene scene;
    public static Socket socket;
    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;
    public static String token;

    public void setDataInputStream(DataInputStream dataInputStream) {
        Menu.dataInputStream = dataInputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        Menu.dataOutputStream = dataOutputStream;
    }

    public static void executeMainMenu() throws IOException {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: black");
        if (!checkMainManager(parent)) {
            Scene scene = new Scene(parent, 1280, 660);
            stage.setScene(scene);
        } else {
//            String path = "src/main/java/view/music.WAV";
//            Media media = new Media(new File(path).toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.play();
            URL url = new File("src/main/java/Client/View/mainMenu.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stage.setTitle("Create Manger Account");
            scene = new Scene(root, 1280, 660);
            stage.setScene(scene);
        }
        stage.show();
    }

    private static boolean checkMainManager(Pane parent) throws IOException {
        dataOutputStream.writeUTF("checkMainManager");
        dataOutputStream.flush();
        if (dataInputStream.readUTF().equals("no")) {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: black");
            Label label = new Label();
            label.setText("Hello and Welcome!\nYou have to create manager account at first");
            label.setFont(new Font("Arial", 20));
            label.setTextFill(Color.WHITE);
            pane.setStyle("-fx-background-color: black");
            pane.getChildren().add(label);
            Button button = new Button("Go On!");
            button.setLayoutX(200);
            button.setOnMouseClicked(e -> {
                RegisterMenu registerMenu = new RegisterMenu();
                registerMenu.show();
            });
            pane.getChildren().add(button);
            parent.getChildren().add(pane);
            return false;
        }
        return true;
    }

    public void userArea(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        String path = "src/main/java/Client/view/clickSound.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        dataOutputStream.writeUTF("getPerson," + token);
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        Person person;
        if (json.equals("null")) {
            LoginMenu loginMenu = new LoginMenu();
            loginMenu.loginProcess();
        } else {
            if (json.startsWith("seller")) {
                person = gson.fromJson(json.substring(7), Person.class);
                dataOutputStream.writeUTF("condition of seller with id-" + person.getUsername() + "," + token);
                dataOutputStream.flush();
                String message = dataInputStream.readUTF();
                if (message.equals("Accept")) {
                    new SellerMenu().show();
                } else if (message.equals("Decline")) {
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.loginProcess();
                } else {
                    Pane pane = new Pane();
                    Label label = new Label("wait to accept");
                    pane.getChildren().add(label);
                    Scene scene = new Scene(pane, 100, 100);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
            } else if (json.startsWith("manager")) {
                new ManagerMenu().show();
            } else if (json.startsWith("buyer")) {
                person = gson.fromJson(json.substring(6), Person.class);
                dataOutputStream.writeUTF("setOnline id-" + person.getUsername() + "-yes," + token);
                dataOutputStream.flush();
                new BuyerMenu().show();
            } else if (json.startsWith("supporter")) {
                person = gson.fromJson(json.substring(10), Person.class);
                dataOutputStream.writeUTF("setOnline id-" + person.getUsername() + "-yes," + token);
                dataOutputStream.flush();
                new SupporterMenu().show();
            }
        }
    }

    public void exit(MouseEvent mouseEvent) {
        String path = "src/main/java/Client/view/clickSound.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        if (ConfirmBox.display("EXIT", "are you sure you want to exit?!")) {
            System.exit(1);
        }
    }

    public void productMenu(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        String path = "src/main/java/Client/view/clickSound.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        ProductsPage.show();
    }

    public void auctions(MouseEvent mouseEvent) {
        String path = "src/main/java/Client/view/clickSound.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
//        AuctionPage.show();
    }

    public void show() throws IOException, ClassNotFoundException {

    }

}

