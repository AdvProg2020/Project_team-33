package Client.View;

import Client.Model.Users.*;
import Client.View.BuyerMenu.BuyerMenu;
import Client.View.LoginAndRegister.RegisterMenu;
import Client.View.ManagerMenu.ManagerMenu;
import Client.View.SellerMenu.SellerMenu;
import Client.View.SupporterMenu.SupporterMenu;
import com.google.gson.Gson;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;


public class PurchaseMenu {
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;
    private static String token = Menu.token;

    //Done
    public static void show() throws IOException, ClassNotFoundException {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");

        makeTopOfMenu(parent);
        purchasePage(parent);

        Scene scene = new Scene(parent, 1280, 660);

        Menu.stage.setScene(scene);

        Menu.stage.show();
    }

    //Done
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

    //ToDo
    private static void addFields(Pane pane, Person person) throws IOException {
        AtomicBoolean discount = new AtomicBoolean(false);

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

        Label code = new Label("Gift code:");
        code.setLayoutX(20);
        code.setLayoutY(380);
        pane.getChildren().add(code);

        TextField codeField = new TextField();
        codeField.setLayoutX(20);
        codeField.setLayoutY(400);
        pane.getChildren().add(codeField);

        Button button = new Button("Check");
        button.setStyle("-fx-background-color:#858585 ");
        button.setLayoutX(200);
        button.setLayoutY(400);
        button.setCursor(Cursor.HAND);
        button.setOnMouseClicked(e -> {
            if (codeField.getText().isEmpty()) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(400);
                pane.getChildren().add(label);
            } else if (codeField.getText().length() != 6) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("6 digits");
                label.setLayoutX(170);
                label.setLayoutY(400);
                pane.getChildren().add(label);
            } else {
                try {
                    dataOutputStream.writeUTF("checkDiscount," + codeField.getText() + "," + token);
                    dataOutputStream.flush();
                    if (dataInputStream.readUTF().equalsIgnoreCase("exist")) {
                        Label label = new Label();
                        label.setTextFill(Color.GREEN);
                        label.setText("Done");
                        label.setLayoutX(170);
                        label.setLayoutY(400);
                        pane.getChildren().add(label);
                        discount.set(true);
                    } else {
                        Label label = new Label();
                        label.setTextFill(Color.RED);
                        label.setText("Not Exist");
                        label.setLayoutX(170);
                        label.setLayoutY(400);
                        pane.getChildren().add(label);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        pane.getChildren().add(button);

        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #858585");
        back.setLayoutX(250);
        back.setLayoutY(450);
        back.setCursor(Cursor.HAND);
        back.setOnMouseClicked(e -> {
            CartPage.show();
        });
        pane.getChildren().add(back);

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
                if (discount.get()) {
                    readyForPurchase(codeField.getText(), addressField.getText());
                } else {
                    readyForPurchase("0", addressField.getText());
                }
            }
        });
    }

    public static void readyForPurchase(String code, String address) {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");

        Button wallet = new Button("Pay with wallet");
        wallet.setCursor(Cursor.HAND);
        wallet.setStyle("-fx-background-color: #bababa");
        wallet.setLayoutX(600);
        wallet.setLayoutY(250);
        wallet.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("haveEnoughMoneyForPurchaseInWallet," + token);
                dataOutputStream.flush();
                if (dataInputStream.readUTF().equalsIgnoreCase("yes")) {
                    dataOutputStream.writeUTF("doPurchaseWithWallet-" + code + "-" + address + "," + token);
                    dataOutputStream.flush();
                    showIfCreateSuccessful();
                } else {
                    Label label = new Label("Do not have enough money");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(600);
                    label.setLayoutY(270);
                    parent.getChildren().add(label);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(wallet);

        Button bankAccount = new Button("Pay with Bank");
        bankAccount.setCursor(Cursor.HAND);
        bankAccount.setStyle("-fx-background-color: #bababa");
        bankAccount.setLayoutX(600);
        bankAccount.setLayoutY(300);
        bankAccount.setOnMouseClicked(e -> {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #858585");

            TextField username = new TextField();
            username.setPromptText("Username");
            username.setLayoutX(600);
            username.setLayoutY(320);
            pane.getChildren().add(username);

            TextField password = new TextField();
            password.setPromptText("Password");
            password.setLayoutX(600);
            password.setLayoutY(360);
            pane.getChildren().add(password);

            TextField id = new TextField();
            id.setPromptText("Id");
            id.setLayoutX(600);
            id.setLayoutY(400);
            pane.getChildren().add(id);


            Button button = new Button("Purchase");
            button.setStyle("-fx-background-color: #bababa");
            button.setLayoutX(600);
            button.setLayoutY(440);
            button.setOnMouseClicked(e1 -> {
                if (username.getText().isEmpty() || password.getText().isEmpty() || id.getText().isEmpty()) {
                    Label error = new Label("Fill");
                    error.setFont(new Font(15));
                    error.setTextFill(Color.RED);
                    error.setLayoutX(600);
                    error.setLayoutY(460);
                    pane.getChildren().add(error);
                } else {
                    try {
                        dataOutputStream.writeUTF("haveEnoughMoneyForPurchaseInBank-" + username.getText() + "-" + password.getText() + "-" + id.getText() + "," + token);
                        dataOutputStream.flush();
                        String msg = dataInputStream.readUTF();
                        if (msg.equalsIgnoreCase("yes")) {
                            dataOutputStream.writeUTF("doPurchaseWithBank-" + username.getText() + "-" + password.getText() + "-" + id.getText() + "-" + code + "-" + address + "," + token);
                            dataOutputStream.flush();
                            String message = dataInputStream.readUTF();
                            if (message.equalsIgnoreCase("done successfully")) {
                                showIfCreateSuccessful();
                            } else {
                                Label error = new Label(message);
                                error.setFont(new Font(15));
                                error.setTextFill(Color.RED);
                                error.setLayoutX(600);
                                error.setLayoutY(460);
                                pane.getChildren().add(error);
                            }
                        } else {
                            Label error = new Label("Do not have enough money");
                            error.setFont(new Font(15));
                            error.setTextFill(Color.RED);
                            error.setLayoutX(100);
                            error.setLayoutY(460);
                            pane.getChildren().add(error);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            button.setCursor(Cursor.HAND);
            pane.getChildren().add(button);

            Button back = new Button("Back");
            back.setStyle("-fx-background-color: #bababa");
            back.setLayoutX(680);
            back.setLayoutY(440);
            back.setOnMouseClicked(e1 -> {
                readyForPurchase(code, address);
            });
            button.setCursor(Cursor.HAND);
            pane.getChildren().add(back);


            parent.getChildren().add(pane);
        });
        parent.getChildren().add(bankAccount);

        Button cancel = new Button("Cancel");
        cancel.setCursor(Cursor.HAND);
        cancel.setStyle("-fx-background-color: #bababa");
        cancel.setLayoutX(600);
        cancel.setLayoutY(350);
        cancel.setOnMouseClicked(e -> {
            try {
                new BuyerMenu().show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(cancel);

        Scene scene = new Scene(parent, 1280, 660);

        Menu.stage.setScene(scene);

        Menu.stage.show();
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
