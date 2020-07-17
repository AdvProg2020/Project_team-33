package View;

import Controller.RegisterAndLogin.PersonController;
import Model.Users.Buyer;
import Model.Users.Seller;
import Model.Users.Supporter;
import View.BuyerMenu.BuyerMenu;
import View.LoginAndRegister.LoginMenu;
import View.LoginAndRegister.RegisterMenu;
import View.ManagrMenu.ManagerMenu;
import View.ProductPage.ProductsPage;
import View.SellerMenu.SellerMenu;
import View.SupporterMenu.SupporterMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public static Menu currentMenu;
    public static Menu previousMenu;
    public static Stage stage = new Stage();
    public static Scene scene;
    public static Socket socket;
    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;
    public ObjectInputStream objectInputStream;
    public ObjectOutputStream objectOutputStream;

    public Menu(DataInputStream dataInputStream, DataOutputStream dataOutputStream, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
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
            URL url = new File("src/main/java/View/mainMenu.fxml").toURI().toURL();
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
                currentMenu = registerMenu;
                registerMenu.show();
            });
            pane.getChildren().add(button);
            parent.getChildren().add(pane);
            return false;
        }
        return true;
    }

    public void userArea(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        String path = "src/main/java/view/clickSound.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        if (LoginMenu.currentPerson == null) {
            LoginMenu loginMenu = new LoginMenu();
            currentMenu = loginMenu;
            loginMenu.loginProcess();
        } else {
            if (LoginMenu.currentPerson instanceof Seller) {
                SellerMenu sellerMenu = new SellerMenu();
                sellerMenu.showPersonalArea();
            } else if (LoginMenu.currentPerson instanceof Buyer) {
                new BuyerMenu().showPersonalArea();
            } else if (LoginMenu.currentPerson instanceof Supporter) {
                new SupporterMenu().showPersonalArea();
            } else {
                ManagerMenu managerMenu = new ManagerMenu();
//                currentMenu = managerMenu;
                managerMenu.showPersonalArea();
            }
        }
    }

    public void exit(MouseEvent mouseEvent) {
        String path = "src/main/java/view/clickSound.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        if (ConfirmBox.display("EXIT", "are you sure you want to exit?!")) {
            System.exit(1);
        }
    }

    public void productMenu(MouseEvent mouseEvent) throws IOException {
        String path = "src/main/java/view/clickSound.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        ProductsPage.show();
    }

    public void auctions(MouseEvent mouseEvent) {
        String path = "src/main/java/view/clickSound.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        AuctionPage.show();
    }

    public void executeUserArea() {

    }

    public void executeProductMenu() {

    }

    public void executeAuctions() {

    }

    public void show() throws IOException, ClassNotFoundException {

    }

}

