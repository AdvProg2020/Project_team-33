package Client.View.PublicSalePage;

import Client.Model.Users.Buyer;
import Client.Model.Users.Person;
import Client.View.BuyerMenu.BuyerMenu;
import Client.View.CartPage;
import Client.View.LoginAndRegister.RegisterMenu;
import Client.View.ManagerMenu.ManagerMenu;
import Client.View.Menu;
import Client.View.SellerMenu.SellerMenu;
import Client.View.SupporterMenu.SupporterMenu;
import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public void sendMessage(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        if (!message.getText().isEmpty()) {
            String chat = message.getText();
            message.setText("");
            dataOutputStream.writeUTF("sendMessageInPublicSale," + publicSale + "," + chat + "," + token);
            dataOutputStream.flush();
        }
        updateMessages();
    }

    public void updateMessages() throws IOException, ClassNotFoundException {
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
            publicPage.close();
            if (person.getUsername().equals(splitInput[1])){
                PurchaseForPublicSale.show();
            }
        }

    }

    public void update(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        updateMessages();
        Image image = new Image(Paths.get("src/main/java/Client/view/images/blue-plus-icon.png").toUri().toString());
        imageView.setImage(image);
        Image image2 = new Image(Paths.get("src/main/java/Client/View/images/true.jpg").toUri().toString());
        moneyImageView.setImage(image2);
    }

    static class PurchaseForPublicSale{
        public static void show() throws IOException, ClassNotFoundException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");

            makeTopOfMenu(parent);
            purchasePage(parent);

            Scene scene = new Scene(parent, 1280, 660);

            Menu.stage.setScene(scene);

            Menu.stage.show();
        }

        private static void makeTopOfMenu(Pane parent) {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #232f3e");
            pane.setPrefWidth(1280);
            pane.setPrefHeight(100);
            createImages(pane);

            parent.getChildren().add(pane);
        }

        //Done
        private static void createImages(Pane pane) {
            Image mainMenuImage = new Image(Paths.get("src/main/java/Client/view/images/mainMenu.png").toUri().toString());
            ImageView mainMenu = new ImageView(mainMenuImage);
            mainMenu.setFitWidth(70);
            mainMenu.setFitHeight(70);
            mainMenu.setLayoutY(10);
            mainMenu.setCursor(Cursor.HAND);
            mainMenu.setOnMouseClicked(e -> {
                try {
                    Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            pane.getChildren().add(mainMenu);

            Image userArea = new Image(Paths.get("src/main/java/Client/view/images/userArea.jpg").toUri().toString());
            ImageView userAreaImage = new ImageView(userArea);
            userAreaImage.setFitWidth(70);
            userAreaImage.setFitHeight(70);
            userAreaImage.setLayoutX(90);
            userAreaImage.setLayoutY(10);
            userAreaImage.setCursor(Cursor.HAND);
            userAreaImage.setOnMouseClicked(e -> {
                try {
                    dataOutputStream.writeUTF("getPerson," + token);
                    dataOutputStream.flush();
                    Gson gson = new Gson();
                    String json = dataInputStream.readUTF();
                    if (!json.equalsIgnoreCase("null")) {
                        if (json.substring(0, json.indexOf("-")).equalsIgnoreCase("buyer")) {
                            try {
                                new BuyerMenu().show();
                            } catch (IOException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        } else if (json.substring(0, json.indexOf("-")).equalsIgnoreCase("seller")) {
                            try {
                                new SellerMenu().show();
                            } catch (IOException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        } else if (json.substring(0, json.indexOf("-")).equalsIgnoreCase("manager")) {
                            try {
                                new ManagerMenu().show();
                            } catch (IOException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        } else if (json.substring(0, json.indexOf("-")).equalsIgnoreCase("supporter")) {
                            try {
                                new SupporterMenu().show();
                            } catch (IOException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        }
                    } else {
                        new RegisterMenu().show();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            });
            pane.getChildren().add(userAreaImage);
        }

        //Done
        public static void purchasePage(Pane parent) throws IOException, ClassNotFoundException {
            dataOutputStream.writeUTF("getPerson," + token);
            dataOutputStream.flush();
            Gson gson = new Gson();
            String json = dataInputStream.readUTF();
            Person person = gson.fromJson(json.substring(json.indexOf("-") + 1), Person.class);
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefWidth(400);
            pane.setPrefHeight(500);
            pane.setLayoutX(400);
            pane.setLayoutY(120);

            Label username = new Label("Username:\n" + person.getUsername());
            username.setFont(new Font(17));
            username.setLayoutX(10);
            pane.getChildren().add(username);

            addFields(pane, person);


            parent.getChildren().add(pane);
        }

        //Done
        private static void addFields(Pane pane, Person person) throws IOException {

            dataOutputStream.writeUTF("getPurchaseMoney," + token);
            dataOutputStream.flush();

            Label price = new Label("Total:\n" + dataInputStream.readUTF());
            price.setFont(new Font(17));
            price.setLayoutX(300);
            pane.getChildren().add(price);

            Label name = new Label("Name:");
            name.setLayoutX(20);
            name.setLayoutY(50);
            pane.getChildren().add(name);

            TextField nameField = new TextField();
            nameField.setLayoutX(20);
            nameField.setLayoutY(70);
            pane.getChildren().add(nameField);

            Label family = new Label("Family:");
            family.setLayoutX(20);
            family.setLayoutY(100);
            pane.getChildren().add(family);

            TextField familyField = new TextField();
            familyField.setLayoutX(20);
            familyField.setLayoutY(120);
            pane.getChildren().add(familyField);

            Label phone = new Label("Phone:");
            phone.setLayoutX(20);
            phone.setLayoutY(150);
            pane.getChildren().add(phone);

            TextField phoneField = new TextField();
            phoneField.setLayoutX(20);
            phoneField.setLayoutY(170);
            pane.getChildren().add(phoneField);

            Label email = new Label("Email:");
            email.setLayoutX(20);
            email.setLayoutY(200);
            pane.getChildren().add(email);

            TextField emailField = new TextField();
            emailField.setLayoutX(20);
            emailField.setLayoutY(220);
            pane.getChildren().add(emailField);

            Label address = new Label("Address:");
            address.setLayoutX(20);
            address.setLayoutY(250);
            pane.getChildren().add(address);

            TextArea addressField = new TextArea();
            addressField.setLayoutX(20);
            addressField.setLayoutY(270);
            addressField.setPrefWidth(300);
            addressField.setPrefHeight(100);
            pane.getChildren().add(addressField);

            Button purchaseButton = new Button("Purchase");
            purchaseButton.setStyle("-fx-background-color: #858585");
            purchaseButton.setLayoutX(150);
            purchaseButton.setLayoutY(450);
            pane.getChildren().add(purchaseButton);
            purchaseButton.setCursor(Cursor.HAND);
            purchaseButton.setOnMouseClicked(e -> {
                AtomicBoolean purchase = new AtomicBoolean(true);
                if (nameField.getText().isEmpty()) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Complete");
                    label.setLayoutX(170);
                    label.setLayoutY(70);
                    pane.getChildren().add(label);
                    purchase.set(false);
                }

                if (familyField.getText().isEmpty()) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Complete");
                    label.setLayoutX(170);
                    label.setLayoutY(120);
                    pane.getChildren().add(label);
                    purchase.set(false);
                }

                if (phoneField.getText().isEmpty()) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Complete");
                    label.setLayoutX(330);
                    label.setLayoutY(170);
                    pane.getChildren().add(label);
                    purchase.set(false);
                } else if (!phoneField.getText().matches("09\\d{9}")) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Incorrect");
                    label.setLayoutX(170);
                    label.setLayoutY(170);
                    pane.getChildren().add(label);
                    purchase.set(false);
                }

                if (emailField.getText().isEmpty()) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Complete");
                    label.setLayoutX(170);
                    label.setLayoutY(220);
                    pane.getChildren().add(label);
                    purchase.set(false);
                } else if (!emailField.getText().matches("\\S+@\\S+.com")) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Incorrect");
                    label.setLayoutX(170);
                    label.setLayoutY(220);
                    pane.getChildren().add(label);
                    purchase.set(false);
                }

                if (addressField.getText().isEmpty()) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Incorrect");
                    label.setLayoutX(170);
                    label.setLayoutY(270);
                    pane.getChildren().add(label);
                    purchase.set(false);
                }

                if (purchase.get()) {
                    try {
                        dataOutputStream.writeUTF("doPurchaseForPublicSale-" + "0" + "-" + address + "-" + publicSale + "," + token);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    showIfCreateSuccessful();
                }
            });
        }

        //Done
        public static void showIfCreateSuccessful() {
            Pane gridPane = new Pane();
            Image image = new Image(Paths.get("src/main/java/Client/view/images/blue-plus-icon.png").toUri().toString());
            ImageView imageView = new ImageView(image);
            gridPane.getChildren().add(imageView);
            imageView.setLayoutX(100);
            imageView.setLayoutY(100);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            Button button = new Button("Click to continue");

            Label label = new Label();
            label.setText("Purchase Successful");
            label.setTextFill(Color.GREEN);
            gridPane.getChildren().add(label);
            label.setLayoutX(100);
            label.setLayoutY(170);

            gridPane.getChildren().add(button);
            button.setLayoutX(100);
            button.setLayoutY(200);
            button.setOnMouseClicked(e -> {
                try {
                    new BuyerMenu().show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            Stage stage = new Stage();
            stage.setScene(new Scene(gridPane, 300, 300));
            stage.show();
        }
    }

}
