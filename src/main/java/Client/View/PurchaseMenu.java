package Client.View;

import Client.Model.Users.*;
import Client.View.BuyerMenu.BuyerMenu;
import Client.View.LoginAndRegister.RegisterMenu;
import Client.View.ManagrMenu.ManagerMenu;
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
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;


public class PurchaseMenu {
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;

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
            Person person = null;
            try {
                dataOutputStream.writeUTF("getPerson");
                dataOutputStream.flush();
                Gson gson = new Gson();
                String json = dataInputStream.readUTF();
                person = gson.fromJson(json, Person.class);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (person instanceof Buyer) {
                try {
                    new BuyerMenu().show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else if (person instanceof Seller) {
                try {
                    new SellerMenu().show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else if (person instanceof Manager) {
                try {
                    new ManagerMenu().show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else if (person instanceof Supporter) {
                try {
                    new SupporterMenu().show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else {
                new RegisterMenu().show();
            }
        });
        pane.getChildren().add(userAreaImage);
    }

    public static void purchasePage(Pane parent) throws IOException, ClassNotFoundException {
        dataOutputStream.writeUTF("getPerson");
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        Person person = gson.fromJson(json, Person.class);
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

    private static void addFields(Pane pane, Person person) {
        AtomicBoolean discount = new AtomicBoolean(true);

        Label price = new Label("Total:\n" + ((Buyer) person).getCart().getMoneyForPurchase());
        price.setFont(new Font(17));
        price.setLayoutX(300);
        pane.getChildren().add(price);

        Label name = new Label("Name:");
        name.setLayoutY(50);
        pane.getChildren().add(name);
        TextField nameField = new TextField();
        nameField.setLayoutY(70);
        name.setLayoutX(20);
        nameField.setLayoutX(20);
        pane.getChildren().add(nameField);

        Label family = new Label("Family:");
        family.setLayoutY(100);
        pane.getChildren().add(family);
        TextField familyField = new TextField();
        familyField.setLayoutY(120);
        family.setLayoutX(20);
        familyField.setLayoutX(20);
        pane.getChildren().add(familyField);

        Label phone = new Label("Phone:");
        phone.setLayoutY(150);
        pane.getChildren().add(phone);
        TextField phoneField = new TextField();
        phoneField.setLayoutY(170);
        phone.setLayoutX(20);
        phoneField.setLayoutX(20);
        pane.getChildren().add(phoneField);

        Label email = new Label("Email:");
        email.setLayoutY(200);
        pane.getChildren().add(email);
        TextField emailField = new TextField();
        emailField.setLayoutY(220);
        email.setLayoutX(20);
        emailField.setLayoutX(20);
        pane.getChildren().add(emailField);

        Label address = new Label("Address:");
        address.setLayoutY(250);
        pane.getChildren().add(address);

        TextArea addressField = new TextArea();
        addressField.setLayoutY(270);
        addressField.setPrefWidth(300);
        addressField.setPrefHeight(100);
        address.setLayoutX(20);
        addressField.setLayoutX(20);
        pane.getChildren().add(addressField);

        Label code = new Label("Gift code:");
        code.setLayoutY(380);
        pane.getChildren().add(code);

        TextField codeField = new TextField();
        codeField.setLayoutY(400);
        code.setLayoutX(20);
        codeField.setLayoutX(20);
        pane.getChildren().add(codeField);

        Button button = new Button("Check");
        button.setStyle("-fx-background-color:#858585 ");
        button.setLayoutX(200);
        button.setLayoutY(400);
        button.setCursor(Cursor.HAND);
        button.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("checkDiscount," + codeField.getText());
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String[] splitInput = new String[3];
            try {
                splitInput = dataInputStream.readUTF().split("-");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (splitInput[0].equals("1")) {
                if (splitInput[1].equals("1")) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("6 digits");
                    label.setLayoutX(170);
                    label.setLayoutY(400);
                    pane.getChildren().add(label);
                    discount.set(false);
                } else if (splitInput[1].equals("0")) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Doesnt Exist");
                    label.setLayoutX(170);
                    label.setLayoutY(400);
                    pane.getChildren().add(label);
                    discount.set(false);
                }
            } else {
                discount.set(false);
            }
        });
        pane.getChildren().add(button);

        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #858585");
        back.setLayoutX(250);
        back.setLayoutY(450);
        back.setCursor(Cursor.HAND);
        back.setOnMouseClicked(e -> {
            CartPage.show(((Buyer) person).getCart());
        });
        pane.getChildren().add(back);

        Button purchaseButton = new Button("Purchase");
        purchaseButton.setStyle("-fx-background-color: #858585");
        purchaseButton.setLayoutX(150);
        purchaseButton.setLayoutY(450);
        pane.getChildren().add(purchaseButton);
        purchaseButton.setCursor(Cursor.HAND);
        purchaseButton.setOnMouseClicked(e -> {
            String discountType;
            if (discount.get()){
                discountType = "true";
            }else {
                discountType = "false";
            }
            try {
                dataOutputStream.writeUTF("purchase," + nameField.getText() + "," + familyField.getText() + "," +
                        addressField.getText() + "," + phoneField.getText() + "," + emailField.getText() + "," + codeField.getText() + "," + discountType);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String[] splitInput = new String[12];
            try {
                splitInput = dataInputStream.readUTF().split("-");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (splitInput[0].equals("1")) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(70);
                pane.getChildren().add(label);
            }

            if (splitInput[1].equals("1")) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(120);
                pane.getChildren().add(label);
            }

            if (splitInput[2].equals("1")) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(330);
                label.setLayoutY(270);
                pane.getChildren().add(label);
            }

            if (splitInput[3].equals("1")) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(170);
                pane.getChildren().add(label);
            } else {
                if (splitInput[3].equals("2")) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Incorrect");
                    label.setLayoutX(170);
                    label.setLayoutY(170);
                    pane.getChildren().add(label);
                }
            }

            if (splitInput[4].equals("1")) {
                Label label = new Label();
                label.setTextFill(Color.RED);
                label.setText("Complete");
                label.setLayoutX(170);
                label.setLayoutY(220);
                pane.getChildren().add(label);
            } else {
                if (splitInput[4].equals("2")) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Incorrect");
                    label.setLayoutX(170);
                    label.setLayoutY(220);
                    pane.getChildren().add(label);
                }
            }

            if (splitInput[5].equals("1")) {
                if (splitInput[6].equals("1")) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("6 digits");
                    label.setLayoutX(170);
                    label.setLayoutY(400);
                    pane.getChildren().add(label);
                    discount.set(false);
                } else if (splitInput[6].equals("2")) {
                    Label label = new Label();
                    label.setTextFill(Color.RED);
                    label.setText("Doesnt Exist");
                    label.setLayoutX(170);
                    label.setLayoutY(400);
                    pane.getChildren().add(label);
                    discount.set(false);
                }
            } else {
                discount.set(false);
            }

            if (splitInput[7].equals("pass")) {
                if (splitInput[8].equals("1")) {
                    if (splitInput[9].equals("1")) {
                        if (splitInput[10].equals("1")) {
                            showIfCreateSuccessful();
                        } else {
                            Label label = new Label("Not enough money");
                            label.setTextFill(Color.RED);
                            label.setLayoutX(50);
                            label.setLayoutY(450);
                            pane.getChildren().add(label);
                        }
                    } else {
                        if (splitInput[10].equals("1")) {
                            showIfCreateSuccessful();
                        } else {
                            Label label = new Label("Not enough money");
                            label.setTextFill(Color.RED);
                            label.setLayoutX(50);
                            label.setLayoutY(450);
                            pane.getChildren().add(label);
                        }
                    }
                } else {
                    if (splitInput[9].equals("1")) {
                        showIfCreateSuccessful();
                    } else {
                        Label label = new Label("Not enough money");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(50);
                        label.setLayoutY(450);
                        pane.getChildren().add(label);
                    }
                }

            }
        });
    }

    public static void showIfCreateSuccessful() {
        Pane gridPane = new Pane();
        Image image = new Image(Paths.get("src/main/java/view/images/blue-plus-icon.png").toUri().toString());
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
