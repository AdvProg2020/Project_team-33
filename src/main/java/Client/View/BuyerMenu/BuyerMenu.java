package Client.View.BuyerMenu;

import Client.Controller.RegisterAndLogin.PersonController;
import Client.Model.Cart;
import Client.Model.Discount;
import Client.Model.Logs.BuyLog;
import Client.Model.Product;
import Client.Model.PublicSale;
import Client.Model.Users.Buyer;
import Client.Model.Users.Person;
import Client.Model.Users.Supporter;
import Client.View.BuyerMenu.BuyerChatPage.BuyerChatMenu;
import Client.View.CartPage;
import Client.View.ManagerMenu.ManagerMenu;
import Client.View.Menu;
import Client.View.PublicSalePage.PublicSalePage;
import com.google.gson.Gson;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;


public class BuyerMenu extends Menu {
    private static Buyer loginBuyer;

    @Override
    public void show() throws IOException, ClassNotFoundException {
        showPersonalArea();
    }

    //Done
    public void showPersonalArea() throws IOException, ClassNotFoundException {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label label = new Label("Your Account");
        label.setFont(new Font(30));
        label.setLayoutX(90);
        label.setLayoutY(120);
        parent.getChildren().add(label);

        Button creatBank = new Button("Create Account In Bank");
        creatBank.setOnMouseClicked(e -> {
            try {
                createBankAccount();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        creatBank.setLayoutX(300);
        creatBank.setLayoutY(130);
        creatBank.setCursor(Cursor.HAND);
        creatBank.setStyle("-fx-background-color: #bababa");
        parent.getChildren().add(creatBank);

        makePersonalInfoPage(parent);
        makeYourOrdersPage(parent);
        makeGiftCardsPage(parent);
        balancePage(parent);
        makePublicSalePage(parent);
        talkToSupporterPage(parent);
        makeTopOfMenu(parent);

        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    //Done
    public void createBankAccount() throws IOException {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #bababa");

        Label createAccount = new Label("Create Bank Account");
        createAccount.setLayoutX(530);
        createAccount.setLayoutY(70);
        createAccount.setFont(new Font(30));
        parent.getChildren().add(createAccount);

        Label nameLabel = new Label("Name");
        nameLabel.setLayoutX(530);
        nameLabel.setLayoutY(120);
        nameLabel.setFont(new Font(15));
        parent.getChildren().add(nameLabel);

        TextField nameTextField = new TextField();
        nameTextField.setLayoutX(530);
        nameTextField.setLayoutY(140);
        nameTextField.setPrefWidth(200);
        parent.getChildren().add(nameTextField);

        Label nameErrorLabel = new Label();
        nameErrorLabel.setLayoutX(530);
        nameErrorLabel.setLayoutY(165);
        parent.getChildren().add(nameErrorLabel);

        Label familyLabel = new Label("Family");
        familyLabel.setLayoutX(530);
        familyLabel.setLayoutY(180);
        familyLabel.setFont(new Font(15));
        parent.getChildren().add(familyLabel);

        TextField familyTextField = new TextField();
        familyTextField.setLayoutX(530);
        familyTextField.setLayoutY(200);
        familyTextField.setPrefWidth(200);
        parent.getChildren().add(familyTextField);

        Label familyErrorLabel = new Label();
        familyErrorLabel.setLayoutX(530);
        familyErrorLabel.setLayoutY(255);
        parent.getChildren().add(familyErrorLabel);

        Label usernameLabel = new Label("Username");
        usernameLabel.setLayoutX(530);
        usernameLabel.setLayoutY(240);
        usernameLabel.setFont(new Font(15));
        parent.getChildren().add(usernameLabel);

        TextField usernameTextField = new TextField();
        usernameTextField.setLayoutX(530);
        usernameTextField.setLayoutY(260);
        usernameTextField.setPrefWidth(200);
        parent.getChildren().add(usernameTextField);

        Label usernameErrorLabel = new Label();
        usernameErrorLabel.setLayoutX(530);
        usernameErrorLabel.setLayoutY(285);
        parent.getChildren().add(usernameErrorLabel);

        Label passwordLabel = new Label("Password");
        passwordLabel.setLayoutX(530);
        passwordLabel.setLayoutY(310);
        passwordLabel.setFont(new Font(15));
        parent.getChildren().add(passwordLabel);

        TextField passwordTextField = new TextField();
        passwordTextField.setLayoutX(530);
        passwordTextField.setLayoutY(330);
        passwordTextField.setPrefWidth(200);
        parent.getChildren().add(passwordTextField);

        Label passwordErrorLabel = new Label();
        passwordErrorLabel.setLayoutX(530);
        passwordErrorLabel.setLayoutY(355);
        parent.getChildren().add(passwordErrorLabel);

        Label rePasswordLabel = new Label("ReEnter-Password");
        rePasswordLabel.setLayoutX(530);
        rePasswordLabel.setLayoutY(370);
        rePasswordLabel.setFont(new Font(15));
        parent.getChildren().add(rePasswordLabel);

        TextField rePasswordTextField = new TextField();
        rePasswordTextField.setLayoutX(530);
        rePasswordTextField.setLayoutY(390);
        rePasswordTextField.setPrefWidth(200);
        parent.getChildren().add(rePasswordTextField);

        Label rePasswordErrorLabel = new Label();
        rePasswordErrorLabel.setLayoutX(530);
        rePasswordErrorLabel.setLayoutY(415);
        parent.getChildren().add(rePasswordErrorLabel);

        Button button = new Button("Create account");
        button.setStyle("-fx-background-color: #858585");
        button.setLayoutX(530);
        button.setLayoutY(450);
        button.setCursor(Cursor.HAND);
        button.setOnMouseClicked(e -> {
            boolean create = true;
            if (nameTextField.getText().isEmpty()) {
                nameErrorLabel.setTextFill(Color.RED);
                nameErrorLabel.setText("please complete");
                create = false;
            }

            if (familyTextField.getText().isEmpty()) {
                familyErrorLabel.setTextFill(Color.RED);
                familyErrorLabel.setText("please complete");
                create = false;
            }

            if (usernameTextField.getText().isEmpty()) {
                usernameErrorLabel.setTextFill(Color.RED);
                usernameErrorLabel.setText("please complete");
                create = false;
            }

            if (passwordTextField.getText().isEmpty()) {
                passwordErrorLabel.setTextFill(Color.RED);
                passwordErrorLabel.setText("please complete");
                create = false;
            }

            if (rePasswordTextField.getText().isEmpty()) {
                rePasswordErrorLabel.setTextFill(Color.RED);
                rePasswordErrorLabel.setText("please complete");
                create = false;
            } else if (!passwordTextField.getText().equals(rePasswordTextField.getText())) {
                rePasswordErrorLabel.setTextFill(Color.RED);
                rePasswordErrorLabel.setText("Not Same");
                create = false;
            }
            if (create) {
                try {
                    dataOutputStream.writeUTF("createBankAccount-" + nameTextField.getText() + "-" + familyTextField.getText() + "-"
                            + usernameTextField.getText() + "-" + passwordTextField.getText() + "-" + rePasswordTextField.getText() +
                            "," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    String input = dataInputStream.readUTF();
                    if (input.equalsIgnoreCase("username is not available")) {
                        Label label = new Label(input);
                        label.setTextFill(Color.RED);
                        label.setLayoutX(530);
                        label.setLayoutY(470);
                        parent.getChildren().add(label);
                    } else {
                        new BuyerMenu().show();
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        parent.getChildren().add(button);

        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #858585");
        back.setLayoutX(660);
        back.setLayoutY(450);
        back.setOnMouseClicked(e -> {
            try {
                new BuyerMenu().show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        back.setCursor(Cursor.HAND);
        parent.getChildren().add(back);

        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    //Done
    private void makePersonalInfoPage(Pane parent) {
        Pane personalInfo = new Pane();
        personalInfo.setStyle("-fx-background-color: #bababa");
        personalInfo.setPrefWidth(210);
        personalInfo.setPrefHeight(70);
        personalInfo.setLayoutX(90);
        personalInfo.setLayoutY(200);
        personalInfo.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/personalInfo.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        personalInfo.getChildren().add(imageView);
        parent.getChildren().add(personalInfo);

        Label personalInfoLabel = new Label("Personal Info");
        personalInfoLabel.setFont(new Font(20));
        personalInfoLabel.setLayoutX(60);
        personalInfoLabel.setLayoutY(10);
        personalInfo.getChildren().add(personalInfoLabel);

        Label personalInfoSecondLabel = new Label("name,family,password,...");
        personalInfoSecondLabel.setFont(new Font(12));
        personalInfoSecondLabel.setLayoutX(60);
        personalInfoSecondLabel.setLayoutY(40);
        personalInfo.getChildren().add(personalInfoSecondLabel);
        personalInfo.setOnMouseClicked(e -> {
            try {
                BuyerPersonalInfo.editPersonalInfo();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    //Done
    private void makeYourOrdersPage(Pane parent) {
        Pane order = new Pane();
        order.setStyle("-fx-background-color: #bababa");
        order.setPrefWidth(240);
        order.setPrefHeight(70);
        order.setLayoutX(490);
        order.setLayoutY(200);
        order.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/basket.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        order.getChildren().add(imageView);

        Label orderLabel = new Label("Your Orders");
        orderLabel.setFont(new Font(20));
        orderLabel.setLayoutX(60);
        orderLabel.setLayoutY(10);
        order.getChildren().add(orderLabel);

        Label orderSecondLabel = new Label("Track, return, or buy things again");
        orderSecondLabel.setFont(new Font(12));
        orderSecondLabel.setLayoutX(60);
        orderSecondLabel.setLayoutY(40);
        order.getChildren().add(orderSecondLabel);

        order.setOnMouseClicked(e -> {
            try {
                BuyerBuyLogs.show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(order);
    }

    //Done
    private void makeGiftCardsPage(Pane parent) {
        Pane giftCard = new Pane();
        giftCard.setStyle("-fx-background-color: #bababa");
        giftCard.setPrefWidth(240);
        giftCard.setPrefHeight(70);
        giftCard.setLayoutX(890);
        giftCard.setLayoutY(200);
        giftCard.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/giftCard.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        giftCard.getChildren().add(imageView);


        Label giftCardLabel = new Label("Gift Cards");
        giftCardLabel.setFont(new Font(20));
        giftCardLabel.setLayoutX(60);
        giftCardLabel.setLayoutY(10);
        giftCard.getChildren().add(giftCardLabel);

        Label giftCardSecondLabel = new Label("Redeem a card");
        giftCardSecondLabel.setFont(new Font(12));
        giftCardSecondLabel.setLayoutX(60);
        giftCardSecondLabel.setLayoutY(40);
        giftCard.getChildren().add(giftCardSecondLabel);

        giftCard.setOnMouseClicked(e -> {
            try {
                BuyerGiftCards.showPage();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(giftCard);
    }

    //Done
    private void balancePage(Pane parent) {
        Pane balance = new Pane();
        balance.setStyle("-fx-background-color: #bababa");
        balance.setPrefWidth(210);
        balance.setPrefHeight(70);
        balance.setLayoutX(90);
        balance.setLayoutY(350);
        balance.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/balance.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        balance.getChildren().add(imageView);


        Label balanceLabel = new Label("Balance");
        balanceLabel.setFont(new Font(20));
        balanceLabel.setLayoutX(60);
        balanceLabel.setLayoutY(10);
        balance.getChildren().add(balanceLabel);

        Label balanceSecondLabel = new Label("Client.View Your Balance");
        balanceSecondLabel.setFont(new Font(12));
        balanceSecondLabel.setLayoutX(60);
        balanceSecondLabel.setLayoutY(40);
        balance.getChildren().add(balanceSecondLabel);

        balance.setOnMouseClicked(e -> {
            try {
                BuyerBalance.showPage();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(balance);
    }

    //Done
    private void makePublicSalePage(Pane parent) {
        Pane publicSale = new Pane();
        publicSale.setStyle("-fx-background-color: #bababa");
        publicSale.setPrefWidth(210);
        publicSale.setPrefHeight(70);
        publicSale.setLayoutX(490);
        publicSale.setLayoutY(350);
        publicSale.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/publicSale.jpg").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        publicSale.getChildren().add(imageView);
        parent.getChildren().add(publicSale);

        Label publicSaleLabel = new Label("Public Sale");
        publicSaleLabel.setFont(new Font(20));
        publicSaleLabel.setLayoutX(60);
        publicSaleLabel.setLayoutY(10);
        publicSale.getChildren().add(publicSaleLabel);

        Label publicSaleSecondLabel = new Label("products in public sale");
        publicSaleSecondLabel.setFont(new Font(12));
        publicSaleSecondLabel.setLayoutX(60);
        publicSaleSecondLabel.setLayoutY(40);
        publicSale.getChildren().add(publicSaleSecondLabel);

        publicSale.setOnMouseClicked(e -> {
            try {
                BuyerPublicSale.show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    //Done
    private void talkToSupporterPage(Pane parent) {
        Pane buyerChat = new Pane();
        buyerChat.setStyle("-fx-background-color: #bababa");
        buyerChat.setPrefWidth(210);
        buyerChat.setPrefHeight(70);
        buyerChat.setLayoutX(890);
        buyerChat.setLayoutY(350);
        buyerChat.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/publicSale.jpg").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        buyerChat.getChildren().add(imageView);
        parent.getChildren().add(buyerChat);

        Label publicSaleLabel = new Label("Chat");
        publicSaleLabel.setFont(new Font(20));
        publicSaleLabel.setLayoutX(60);
        publicSaleLabel.setLayoutY(10);
        buyerChat.getChildren().add(publicSaleLabel);

        Label publicSaleSecondLabel = new Label("chat with supporter");
        publicSaleSecondLabel.setFont(new Font(12));
        publicSaleSecondLabel.setLayoutX(60);
        publicSaleSecondLabel.setLayoutY(40);
        buyerChat.getChildren().add(publicSaleSecondLabel);

        buyerChat.setOnMouseClicked(e -> {
            try {
                BuyerChatWithSupporter.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    //Done
    private void makeTopOfMenu(Pane parent) throws IOException, ClassNotFoundException {
        Pane topMenu = new Pane();
        topMenu.setStyle("-fx-background-color: #232f3e");
        topMenu.setPrefWidth(1280);
        topMenu.setPrefHeight(100);
        topMenu.setLayoutX(0);
        topMenu.setLayoutY(0);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/mainMenu.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setLayoutY(10);
        imageView.setCursor(Cursor.HAND);
        imageView.setOnMouseClicked(e -> {
            try {
                Menu.executeMainMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        topMenu.getChildren().add(imageView);

        Image log = new Image(Paths.get("src/main/java/Client/view/images/logOut.png").toUri().toString());
        ImageView logOut = new ImageView(log);
        logOut.setFitWidth(100);
        logOut.setFitHeight(80);
        logOut.setLayoutX(1170);
        logOut.setLayoutY(10);
        logOut.setCursor(Cursor.HAND);
        logOut.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("logout," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if (dataInputStream.readUTF().equals("done")) {
                    try {
                        Menu.executeMainMenu();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        topMenu.getChildren().add(logOut);

        dataOutputStream.writeUTF("getPerson," + token);
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        loginBuyer = gson.fromJson(json.substring(6), Buyer.class);

//        ImageView personImage = loginBuyer.getImageView();
//        personImage.setFitWidth(70);
//        personImage.setFitHeight(70);
//        personImage.setLayoutX(320);
//        personImage.setLayoutY(10);
//        topMenu.getChildren().add(personImage);

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().add("Unknown");
        choiceBox.getItems().add("Man");
        choiceBox.getItems().add("Woman");
        choiceBox.setLayoutX(320);
        choiceBox.setLayoutY(85);
        choiceBox.setOnAction(e -> {
            System.out.println(choiceBox.getSelectionModel().getSelectedIndex());
            if (choiceBox.getSelectionModel().getSelectedIndex() == 0) {
                try {
                    dataOutputStream.writeUTF("unknown," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 1) {
                try {
                    dataOutputStream.writeUTF("man," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 2) {
                try {
                    dataOutputStream.writeUTF("woman," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        topMenu.getChildren().add(choiceBox);

        Label role = new Label("Buyer");
        role.setFont(new Font(30));
        role.setLayoutX(640);
        role.setLayoutY(30);
        role.setTextFill(Color.WHITE);
        topMenu.getChildren().add(role);

        Image cart = new Image(Paths.get("src/main/java/Client/view/images/cart.png").toUri().toString());
        ImageView cartImage = new ImageView(cart);
        cartImage.setFitWidth(70);
        cartImage.setFitHeight(70);
        cartImage.setLayoutX(940);
        cartImage.setLayoutY(10);
        cartImage.setCursor(Cursor.HAND);
        cartImage.setOnMouseClicked(e -> {
            CartPage.show();
        });
        topMenu.getChildren().add(cartImage);

        parent.getChildren().add(topMenu);
    }

    //Done
    static class BuyerPersonalInfo {
        public static void editPersonalInfo() throws IOException, ClassNotFoundException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Login & Security");
            label.setFont(new Font(30));
            label.setLayoutX(400);
            label.setLayoutY(100);
            parent.getChildren().add(label);
            makeTopMenu(parent);
            showFields(parent);
            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }

        private static void makeTopMenu(Pane parent) throws IOException, ClassNotFoundException {
            Pane topMenu = new Pane();
            topMenu.setStyle("-fx-background-color: #232f3e");
            topMenu.setPrefWidth(1280);
            topMenu.setPrefHeight(100);
            topMenu.setLayoutX(0);
            topMenu.setLayoutY(0);

//            ImageView imageView = loginBuyer.getImageView();
//            imageView.setFitWidth(70);
//            imageView.setFitHeight(70);
//            imageView.setLayoutY(10);
//            imageView.setCursor(Cursor.HAND);
//            imageView.setOnMouseClicked(e -> {
//                try {
//                    Menu.executeMainMenu();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            });
//            topMenu.getChildren().add(imageView);

            Image log = new Image(Paths.get("src/main/java/Client/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
//                loginBuyer.setOnline(false);
                try {
                    dataOutputStream.writeUTF("logout," + token);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (dataInputStream.readUTF().equals("done"))
                        Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);
            Image person = new Image(Paths.get("src/main/java/Client/view/images/unknownPerson.jpg").toUri().toString());
            ImageView personImage = new ImageView(person);
            personImage.setFitWidth(70);
            personImage.setFitHeight(70);
            personImage.setLayoutX(320);
            personImage.setLayoutY(10);
            topMenu.getChildren().add(personImage);

            Label role = new Label("Buyer");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);

            Image cart = new Image(Paths.get("src/main/java/Client/view/images/cart.png").toUri().toString());
            ImageView cartImage = new ImageView(cart);
            cartImage.setFitWidth(70);
            cartImage.setFitHeight(70);
            cartImage.setLayoutX(940);
            cartImage.setLayoutY(10);
            cartImage.setCursor(Cursor.HAND);
            cartImage.setOnMouseClicked(e -> {

            });
            topMenu.getChildren().add(cartImage);


            parent.getChildren().add(topMenu);
        }

        private static void showFields(Pane parent) throws IOException, ClassNotFoundException {
            Pane personalInfo = new Pane();
            personalInfo.setStyle("-fx-background-color: #bababa");
            personalInfo.setPrefWidth(400);
            personalInfo.setPrefHeight(400);
            personalInfo.setLayoutX(400);
            personalInfo.setLayoutY(150);
            parent.getChildren().add(personalInfo);
            name(personalInfo);
            family(personalInfo);
            email(personalInfo);
            phone(personalInfo);
            password(personalInfo);

            Button button = new Button("Save and back");
            button.setPrefWidth(100);
            button.setLayoutX(150);
            button.setLayoutY(300);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);
            button.setOnMouseClicked(e -> {
                try {
                    new BuyerMenu().showPersonalArea();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }

        private static void name(Pane personalInfo) throws IOException {
            Label name = new Label("Name:" + "\n" + (loginBuyer).getName());
            name.setFont(new Font(15));
            name.setLayoutX(20);
            personalInfo.getChildren().add(name);

            Line line = new Line();
            line.setStartX(0);
            line.setEndX(400);
            line.setStartY(50);
            line.setEndY(50);
            personalInfo.getChildren().add(line);

            Button button = new Button("Edit");
            button.setLayoutX(350);
            button.setLayoutY(10);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);

            TextField textField = new TextField();
            textField.setPromptText("New name");
            textField.setLayoutX(200);
            textField.setLayoutY(10);
            personalInfo.getChildren().add(textField);
            button.setOnMouseClicked(e -> {
                Label label = new Label();
                label.setFont(new Font(10));
                label.setLayoutX(200);
                label.setLayoutY(35);
                personalInfo.getChildren().add(label);
                if (textField.getText().isEmpty()) {
                    label.setText("Complete for edit");
                    label.setTextFill(Color.RED);
                } else {
                    try {
                        dataOutputStream.writeUTF("editPersonalInfo,name," + textField.getText() + "," + token);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        if (dataInputStream.readUTF().equals("done")) {
                            label.setText("Done");
                            label.setTextFill(Color.GREEN);
                            name.setText("Name:" + "\n" + textField.getText());
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        private static void family(Pane personalInfo) throws IOException {
            Label family = new Label("Family:" + "\n" + loginBuyer.getFamily());
            family.setFont(new Font(15));
            family.setLayoutX(20);
            family.setLayoutY(50);
            personalInfo.getChildren().add(family);

            Line line1 = new Line();
            line1.setStartX(0);
            line1.setEndX(400);
            line1.setStartY(100);
            line1.setEndY(100);
            personalInfo.getChildren().add(line1);

            Button button = new Button("Edit");
            button.setLayoutX(350);
            button.setLayoutY(60);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);

            TextField textField = new TextField();
            textField.setPromptText("New family");
            textField.setLayoutX(200);
            textField.setLayoutY(60);
            personalInfo.getChildren().add(textField);
            button.setOnMouseClicked(e -> {
                Label label = new Label();
                label.setFont(new Font(10));
                label.setLayoutX(200);
                label.setLayoutY(85);
                personalInfo.getChildren().add(label);
                if (textField.getText().isEmpty()) {
                    label.setText("Complete for edit");
                    label.setTextFill(Color.RED);
                } else {
                    try {
                        dataOutputStream.writeUTF("editPersonalInfo,family," + textField.getText() + "," + token);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        if (dataInputStream.readUTF().equals("done")) {
                            label.setText("Done");
                            label.setTextFill(Color.GREEN);
                            family.setText("Family:" + "\n" + textField.getText());
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        private static void email(Pane personalInfo) throws IOException, ClassNotFoundException {
            Label email = new Label("Email:" + "\n" + (loginBuyer).getEmail());
            email.setFont(new Font(15));
            email.setLayoutX(20);
            email.setLayoutY(100);
            personalInfo.getChildren().add(email);

            Line line2 = new Line();
            line2.setStartX(0);
            line2.setEndX(400);
            line2.setStartY(150);
            line2.setEndY(150);
            personalInfo.getChildren().add(line2);

            Button button = new Button("Edit");
            button.setLayoutX(350);
            button.setLayoutY(110);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);

            TextField textField = new TextField();
            textField.setPromptText("New email");
            textField.setLayoutX(200);
            textField.setLayoutY(110);
            personalInfo.getChildren().add(textField);
            button.setOnMouseClicked(e -> {
                Label label = new Label();
                label.setFont(new Font(10));
                label.setLayoutX(200);
                label.setLayoutY(135);
                personalInfo.getChildren().add(label);
                if (textField.getText().isEmpty()) {
                    label.setText("Complete for edit");
                    label.setTextFill(Color.RED);
                } else {
                    if (PersonController.checkLengthOfPassWord(textField.getText())) {
                        label.setText(":||||||");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editPersonalInfo,email," + textField.getText() + "," + token);
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                email.setText("Email:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }

        private static void phone(Pane personalInfo) throws IOException, ClassNotFoundException {
            Label phone = new Label("Phone:" + "\n" + (loginBuyer).getPhone());
            phone.setFont(new Font(15));
            phone.setLayoutX(20);
            phone.setLayoutY(150);
            personalInfo.getChildren().add(phone);

            Line line3 = new Line();
            line3.setStartX(0);
            line3.setEndX(400);
            line3.setStartY(200);
            line3.setEndY(200);
            personalInfo.getChildren().add(line3);

            Button button = new Button("Edit");
            button.setLayoutX(350);
            button.setLayoutY(160);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);

            TextField textField = new TextField();
            textField.setPromptText("New phone");
            textField.setLayoutX(200);
            textField.setLayoutY(160);
            personalInfo.getChildren().add(textField);
            button.setOnMouseClicked(e -> {
                Label label = new Label();
                label.setFont(new Font(10));
                label.setLayoutX(200);
                label.setLayoutY(185);
                personalInfo.getChildren().add(label);
                if (textField.getText().isEmpty()) {
                    label.setText("Complete for edit");
                    label.setTextFill(Color.RED);
                } else {
                    if (PersonController.checkLengthOfPassWord(textField.getText())) {
                        label.setText(":||||||");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editPersonalInfo,phone," + textField.getText() + "," + token);
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                phone.setText("Phone:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }

        private static void password(Pane personalInfo) throws IOException, ClassNotFoundException {
            Label password = new Label("Password:" + "\n" + loginBuyer.getPassword());
            password.setLayoutX(20);
            password.setLayoutY(200);
            password.setFont(new Font(15));
            personalInfo.getChildren().add(password);

            Line line4 = new Line();
            line4.setStartX(0);
            line4.setEndX(400);
            line4.setStartY(250);
            line4.setEndY(250);
            personalInfo.getChildren().add(line4);

            Button button = new Button("Edit");
            button.setLayoutX(350);
            button.setLayoutY(210);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);

            TextField textField = new TextField();
            textField.setPromptText("New password");
            textField.setLayoutX(200);
            textField.setLayoutY(210);
            personalInfo.getChildren().add(textField);
            button.setOnMouseClicked(e -> {
                Label label = new Label();
                label.setFont(new Font(10));
                label.setLayoutX(200);
                label.setLayoutY(235);
                personalInfo.getChildren().add(label);
                if (textField.getText().isEmpty()) {
                    label.setText("Complete for edit");
                    label.setTextFill(Color.RED);
                } else {
                    if (PersonController.checkLengthOfPassWord(textField.getText())) {
                        label.setText("At least 6 characters");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editPersonalInfo,password," + textField.getText() + "," + token);
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                password.setText("Password:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    //Done
    static class BuyerPublicSale {
        public static void show() throws IOException, ClassNotFoundException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            showFields(parent);
            makeTopOfMenu(parent);
            Label label = new Label("Public Sale");
            label.setFont(new Font(30));
            label.setLayoutX(5);
            label.setLayoutY(100);
            parent.getChildren().add(label);

            Button backButton = new Button("Back");
            backButton.setLayoutX(300);
            backButton.setLayoutY(110);
            backButton.setStyle("-fx-background-color: #bababa");
            backButton.setCursor(Cursor.HAND);
            backButton.setOnMouseClicked(e -> {
                try {
                    new BuyerMenu().showPersonalArea();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(backButton);

            Button updateList = new Button("Update list");
            updateList.setLayoutX(400);
            updateList.setLayoutY(110);
            updateList.setStyle("-fx-background-color: #bababa");
            updateList.setCursor(Cursor.HAND);
            updateList.setOnMouseClicked(e -> {
                try {
                    show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(updateList);

            Scene scene = new Scene(parent, 1280, 660);

            Menu.stage.setScene(scene);

            Menu.stage.show();
        }

        private static void makeTopOfMenu(Pane parent) throws IOException, ClassNotFoundException {
            Pane topMenu = new Pane();
            topMenu.setStyle("-fx-background-color: #232f3e");
            topMenu.setPrefWidth(1280);
            topMenu.setPrefHeight(100);
            topMenu.setLayoutX(0);
            topMenu.setLayoutY(0);

            Image image = new Image(Paths.get("src/main/java/Client/view/images/mainMenu.png").toUri().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(70);
            imageView.setFitHeight(70);
            imageView.setLayoutY(10);
            imageView.setCursor(Cursor.HAND);
            imageView.setOnMouseClicked(e -> {
                try {
                    Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(imageView);

            Image log = new Image(Paths.get("src/main/java/Client/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
                try {
                    dataOutputStream.writeUTF("logout," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (dataInputStream.readUTF().equals("done")) {
                        try {
                            Menu.executeMainMenu();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);

//            ImageView personImage = loginSeller.getImageView();
//            personImage.setFitWidth(70);
//            personImage.setFitHeight(70);
//            personImage.setLayoutX(320);
//            personImage.setLayoutY(10);
//            topMenu.getChildren().add(personImage);

            Label role = new Label("Buyer");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);

            parent.getChildren().add(topMenu);
        }

        private static void showFields(Pane parent) throws IOException {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefWidth(1270);
            pane.setPrefHeight(600);
            pane.setLayoutX(5);
            pane.setLayoutY(150);
            parent.getChildren().add(pane);

            Label id = new Label("Id");
            id.setFont(new Font(20));
            id.setLayoutX(10);
            id.setLayoutY(5);
            pane.getChildren().add(id);

            Label name = new Label("Name");
            name.setFont(new Font(20));
            name.setLayoutX(200);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label money = new Label("Money");
            money.setFont(new Font(20));
            money.setLayoutX(400);
            money.setLayoutY(5);
            pane.getChildren().add(money);

            Label category = new Label("Category");
            category.setFont(new Font(20));
            category.setLayoutX(650);
            category.setLayoutY(5);
            pane.getChildren().add(category);

            Label description = new Label("Description");
            description.setFont(new Font(20));
            description.setLayoutX(900);
            description.setLayoutY(5);
            pane.getChildren().add(description);

            Label buyers = new Label("participate in public sale");
            buyers.setFont(new Font(20));
            buyers.setLayoutX(1100);
            buyers.setLayoutY(5);
            pane.getChildren().add(buyers);

            updateList(pane);
        }

        private static void updateList(Pane pane) throws IOException {
            int i = 1;
            dataOutputStream.writeUTF("getAllProductsInPublicSale," + token);
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<String> publicSales = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String publicSale = dataInputStream.readUTF();
                publicSales.add(publicSale);
            }

            for (String item : publicSales) {
                String[] splitInput = item.split("-");
                if (Integer.parseInt(splitInput[0]) > 0 && splitInput[1].equals("false")) {

                    Label id = new Label(splitInput[2]);
                    id.setFont(new Font(20));
                    id.setLayoutX(10);
                    id.setLayoutY(50 * i);
                    pane.getChildren().add(id);

                    Label name = new Label(splitInput[3]);
                    name.setFont(new Font(20));
                    name.setLayoutX(200);
                    name.setLayoutY(50 * i);
                    pane.getChildren().add(name);

                    Label money = new Label(String.valueOf(splitInput[4]));
                    money.setFont(new Font(20));
                    money.setLayoutX(400);
                    money.setLayoutY(50 * i);
                    pane.getChildren().add(money);

                    Label category = new Label(String.valueOf(splitInput[5]));
                    category.setFont(new Font(20));
                    category.setLayoutX(900);
                    category.setLayoutY(50 * i);
                    pane.getChildren().add(category);

                    Label description = new Label(String.valueOf(splitInput[6]));
                    description.setFont(new Font(20));
                    description.setLayoutX(650);
                    description.setLayoutY(50 * i);
                    pane.getChildren().add(description);

//                    if (!product.getCondition().equals("Unknown")) {
                    Button publicSale = new Button("participate");
                    publicSale.setStyle("-fx-background-color: #858585");
                    publicSale.setLayoutX(1100);
                    publicSale.setLayoutY(50 * i);
                    publicSale.setCursor(Cursor.HAND);
                    publicSale.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.writeUTF("participateInPublicSale," + splitInput[7] + "," + token);
                            dataOutputStream.flush();
                            PublicSalePage.show(splitInput[7]);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    pane.getChildren().add(publicSale);
//                    }

                    i++;
                }
            }
        }
    }

    //Done
    static class BuyerBalance {
        public static void showPage() throws IOException, ClassNotFoundException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Bank Account");
            label.setFont(new Font(30));
            label.setLayoutX(10);
            label.setLayoutY(100);
            parent.getChildren().add(label);
            makeTopMenu(parent);
            makeButtons(parent);

            Button backButton = new Button("Back");
            backButton.setLayoutX(300);
            backButton.setLayoutY(107);
            backButton.setStyle("-fx-background-color: #bababa");
            backButton.setCursor(Cursor.HAND);
            backButton.setOnMouseClicked(e -> {
                try {
                    new BuyerMenu().show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(backButton);

            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }

        private static void makeButtons(Pane parent) {
            Label balance = new Label("Balance");
            balance.setFont(new Font(30));
            balance.setLayoutX(400);
            balance.setLayoutY(100);
            parent.getChildren().add(balance);

            Label label = new Label();
            label.setFont(new Font(25));
            label.setLayoutX(400);
            label.setLayoutY(150);
            parent.getChildren().add(label);

            Label account = new Label("Account ID");
            account.setFont(new Font(30));
            account.setLayoutX(600);
            account.setLayoutY(100);
            parent.getChildren().add(account);

            try {
                dataOutputStream.writeUTF("getAccountId," + token);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Label label1 = new Label(dataInputStream.readUTF());
                label1.setFont(new Font(25));
                label1.setLayoutX(600);
                label1.setLayoutY(150);
                parent.getChildren().add(label1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Label wallet = new Label("Wallet");
            wallet.setFont(new Font(30));
            wallet.setLayoutX(800);
            wallet.setLayoutY(100);
            parent.getChildren().add(wallet);

            Label label2 = new Label();
            label2.setFont(new Font(25));
            label2.setLayoutX(800);
            label2.setLayoutY(150);
            parent.getChildren().add(label2);

            try {
                dataOutputStream.writeUTF("getWalletMoney," + token);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                label2.setText(dataInputStream.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }


            //Increase Balance
            Label increase = new Label("Increase Balance");
            increase.setFont(new Font(30));
            increase.setLayoutX(100);
            increase.setLayoutY(260);
            parent.getChildren().add(increase);

            TextField username = new TextField();
            username.setPromptText("Username");
            username.setLayoutX(100);
            username.setLayoutY(320);
            parent.getChildren().add(username);

            TextField password = new TextField();
            password.setPromptText("Password");
            password.setLayoutX(100);
            password.setLayoutY(360);
            parent.getChildren().add(password);

            TextField id = new TextField();
            id.setPromptText("Id");
            id.setLayoutX(100);
            id.setLayoutY(400);
            parent.getChildren().add(id);

            TextField money = new TextField();
            money.setPromptText("Money");
            money.setLayoutX(100);
            money.setLayoutY(440);
            parent.getChildren().add(money);

            Button button = new Button("Increase");
            button.setStyle("-fx-background-color: #bababa");
            button.setLayoutX(100);
            button.setLayoutY(480);
            button.setOnMouseClicked(e -> {
                if (username.getText().isEmpty() || password.getText().isEmpty() || id.getText().isEmpty() || money.getText().isEmpty()) {
                    Label error = new Label("Fill");
                    error.setFont(new Font(15));
                    error.setTextFill(Color.RED);
                    error.setLayoutX(100);
                    error.setLayoutY(520);
                    parent.getChildren().add(error);
                } else {
                    try {
                        dataOutputStream.writeUTF("increaseBalance-" + username.getText() + "-" + password.getText() + "-" + id.getText() + "-" + money.getText() + "," + token);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        Label error = new Label(dataInputStream.readUTF());
                        error.setFont(new Font(15));
                        error.setLayoutX(100);
                        error.setLayoutY(520);
                        parent.getChildren().add(error);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });
            button.setCursor(Cursor.HAND);
            parent.getChildren().add(button);


            //Check Balance
            Label check = new Label("Check Balance");
            check.setFont(new Font(30));
            check.setLayoutX(350);
            check.setLayoutY(260);
            parent.getChildren().add(check);

            TextField username1 = new TextField();
            username1.setPromptText("Username");
            username1.setLayoutX(350);
            username1.setLayoutY(320);
            parent.getChildren().add(username1);

            TextField password1 = new TextField();
            password1.setPromptText("Password");
            password1.setLayoutX(350);
            password1.setLayoutY(360);
            parent.getChildren().add(password1);

            Button button1 = new Button("Check");
            button1.setStyle("-fx-background-color: #bababa");
            button1.setLayoutX(350);
            button1.setLayoutY(400);
            button1.setOnMouseClicked(e -> {
                if (username1.getText().isEmpty() || password1.getText().isEmpty()) {
                    Label error = new Label("Fill");
                    error.setFont(new Font(15));
                    error.setTextFill(Color.RED);
                    error.setLayoutX(350);
                    error.setLayoutY(410);
                    parent.getChildren().add(error);
                } else {
                    try {
                        dataOutputStream.writeUTF("getBankBalance-" + username1.getText() + "-" + password1.getText() + "," + token);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        String input = dataInputStream.readUTF();
                        if (input.equalsIgnoreCase("invalid username or password")) {
                            Label error1 = new Label("invalid username or password");
                            error1.setFont(new Font(15));
                            error1.setTextFill(Color.RED);
                            error1.setLayoutX(350);
                            error1.setLayoutY(410);
                            parent.getChildren().add(error1);
                        } else {
                            label.setText(input);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            button1.setCursor(Cursor.HAND);
            parent.getChildren().add(button1);


            //Decrease Balance
            Label decrease = new Label("Decrease Balance");
            decrease.setFont(new Font(30));
            decrease.setLayoutX(600);
            decrease.setLayoutY(260);
            parent.getChildren().add(decrease);

            TextField username2 = new TextField();
            username2.setPromptText("Username");
            username2.setLayoutX(600);
            username2.setLayoutY(320);
            parent.getChildren().add(username2);

            TextField password2 = new TextField();
            password2.setPromptText("Password");
            password2.setLayoutX(600);
            password2.setLayoutY(360);
            parent.getChildren().add(password2);

            TextField id2 = new TextField();
            id2.setPromptText("Id");
            id2.setLayoutX(600);
            id2.setLayoutY(400);
            parent.getChildren().add(id2);

            TextField money2 = new TextField();
            money2.setPromptText("Money");
            money2.setLayoutX(600);
            money2.setLayoutY(440);
            parent.getChildren().add(money2);

            Button button2 = new Button("Decrease");
            button2.setStyle("-fx-background-color: #bababa");
            button2.setLayoutX(600);
            button2.setLayoutY(480);
            button2.setOnMouseClicked(e -> {
                if (username2.getText().isEmpty() || password2.getText().isEmpty() || id2.getText().isEmpty() || money2.getText().isEmpty()) {
                    Label error = new Label("Fill");
                    error.setFont(new Font(15));
                    error.setTextFill(Color.RED);
                    error.setLayoutX(600);
                    error.setLayoutY(520);
                    parent.getChildren().add(error);
                } else {
                    try {
                        dataOutputStream.writeUTF("decreaseBalance-" + username2.getText() + "-" + password2.getText() + "-" + id2.getText() + "-" + money2.getText() + "," + token);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        Label error = new Label(dataInputStream.readUTF());
                        error.setFont(new Font(15));
                        error.setLayoutX(600);
                        error.setLayoutY(520);
                        parent.getChildren().add(error);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            button2.setCursor(Cursor.HAND);
            parent.getChildren().add(button2);


            //Charge Wallet
            Label charge = new Label("Charge Wallet");
            charge.setFont(new Font(30));
            charge.setLayoutX(850);
            charge.setLayoutY(260);
            parent.getChildren().add(charge);

            TextField username3 = new TextField();
            username3.setPromptText("Username");
            username3.setLayoutX(850);
            username3.setLayoutY(320);
            parent.getChildren().add(username3);

            TextField password3 = new TextField();
            password3.setPromptText("Password");
            password3.setLayoutX(850);
            password3.setLayoutY(360);
            parent.getChildren().add(password3);

            TextField id3 = new TextField();
            id3.setPromptText("Id");
            id3.setLayoutX(850);
            id3.setLayoutY(400);
            parent.getChildren().add(id3);

            TextField money3 = new TextField();
            money3.setPromptText("Money");
            money3.setLayoutX(850);
            money3.setLayoutY(440);
            parent.getChildren().add(money3);

            Button button3 = new Button("Charge");
            button3.setStyle("-fx-background-color: #bababa");
            button3.setLayoutX(850);
            button3.setLayoutY(480);
            button3.setOnMouseClicked(e -> {
                if (username3.getText().isEmpty() || password3.getText().isEmpty() || id3.getText().isEmpty() || money3.getText().isEmpty()) {
                    Label error = new Label("Fill");
                    error.setFont(new Font(15));
                    error.setTextFill(Color.RED);
                    error.setLayoutX(850);
                    error.setLayoutY(520);
                    parent.getChildren().add(error);
                } else {
                    try {
                        dataOutputStream.writeUTF("chargeWallet-" + username3.getText() + "-" + password3.getText() + "-" + id3.getText() + "-" + money3.getText() + "," + token);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        Label error = new Label(dataInputStream.readUTF());
                        error.setFont(new Font(15));
                        error.setLayoutX(850);
                        error.setLayoutY(520);
                        parent.getChildren().add(error);
                        if (error.getText().equalsIgnoreCase("done successfully")) {
                            dataOutputStream.writeUTF("getWalletMoney," + token);
                            dataOutputStream.flush();
                            label2.setText(dataInputStream.readUTF());
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            button3.setCursor(Cursor.HAND);
            parent.getChildren().add(button3);


        }

        private static void makeTopMenu(Pane parent) throws IOException, ClassNotFoundException {
            Pane topMenu = new Pane();
            topMenu.setStyle("-fx-background-color: #232f3e");
            topMenu.setPrefWidth(1280);
            topMenu.setPrefHeight(100);
            topMenu.setLayoutX(0);
            topMenu.setLayoutY(0);

            Image image = new Image(Paths.get("src/main/java/Client/view/images/mainMenu.png").toUri().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(70);
            imageView.setFitHeight(70);
            imageView.setLayoutY(10);
            imageView.setCursor(Cursor.HAND);
            imageView.setOnMouseClicked(e -> {
                try {
                    Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(imageView);

            Image log = new Image(Paths.get("src/main/java/Client/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
                try {
                    dataOutputStream.writeUTF("logout," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try {
                    if (dataInputStream.readUTF().equals("done")) {
                        try {
                            Menu.executeMainMenu();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);

//            ImageView personImage = (logInManager).getImageView();
//            personImage.setFitWidth(70);
//            personImage.setFitHeight(70);
//            personImage.setLayoutX(320);
//            personImage.setLayoutY(10);
//            topMenu.getChildren().add(personImage);

            Label role = new Label("Buyer");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);

            parent.getChildren().add(topMenu);
        }

    }

    //Done
    static class BuyerChatWithSupporter {
        public static void show() throws IOException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Choose supporter");
            label.setFont(new Font(30));
            label.setLayoutX(10);
            label.setLayoutY(100);
            parent.getChildren().add(label);
            makeTopMenu(parent);
            showFields(parent);

            Button backButton = new Button("Back");
            backButton.setLayoutX(300);
            backButton.setLayoutY(110);
            backButton.setStyle("-fx-background-color: #bababa");
            backButton.setCursor(Cursor.HAND);
            backButton.setOnMouseClicked(e -> {
                try {
                    new BuyerMenu().showPersonalArea();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(backButton);

            Button updateList = new Button("Update list");
            updateList.setLayoutX(400);
            updateList.setLayoutY(110);
            updateList.setStyle("-fx-background-color: #bababa");
            updateList.setCursor(Cursor.HAND);
            updateList.setOnMouseClicked(e -> {
                try {
                    show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(updateList);

            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }

        private static void makeTopMenu(Pane parent) {
            Pane topMenu = new Pane();
            topMenu.setStyle("-fx-background-color: #232f3e");
            topMenu.setPrefWidth(1280);
            topMenu.setPrefHeight(100);
            topMenu.setLayoutX(0);
            topMenu.setLayoutY(0);

//            ImageView imageView = loginBuyer.getImageView();
//            imageView.setFitWidth(70);
//            imageView.setFitHeight(70);
//            imageView.setLayoutY(10);
//            imageView.setCursor(Cursor.HAND);
//            imageView.setOnMouseClicked(e -> {
//                try {
//                    Menu.executeMainMenu();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            });
//            topMenu.getChildren().add(imageView);

            Image log = new Image(Paths.get("src/main/java/Client/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
//                loginBuyer.setOnline(false);
                try {
                    dataOutputStream.writeUTF("logout," + token);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (dataInputStream.readUTF().equals("done"))
                        Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);
            Image person = new Image(Paths.get("src/main/java/Client/view/images/unknownPerson.jpg").toUri().toString());
            ImageView personImage = new ImageView(person);
            personImage.setFitWidth(70);
            personImage.setFitHeight(70);
            personImage.setLayoutX(320);
            personImage.setLayoutY(10);
            topMenu.getChildren().add(personImage);

            Label role = new Label("Buyer");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);

            Image cart = new Image(Paths.get("src/main/java/Client/view/images/cart.png").toUri().toString());
            ImageView cartImage = new ImageView(cart);
            cartImage.setFitWidth(70);
            cartImage.setFitHeight(70);
            cartImage.setLayoutX(940);
            cartImage.setLayoutY(10);
            cartImage.setCursor(Cursor.HAND);
            cartImage.setOnMouseClicked(e -> {

            });
            topMenu.getChildren().add(cartImage);

            parent.getChildren().add(topMenu);
        }

        private static void showFields(Pane parent) throws IOException {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefWidth(1270);
            pane.setPrefHeight(600);
            pane.setLayoutX(5);
            pane.setLayoutY(150);
            parent.getChildren().add(pane);

            Label name = new Label("Name");
            name.setFont(new Font(20));
            name.setLayoutX(10);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label family = new Label("Family");
            family.setFont(new Font(20));
            family.setLayoutX(200);
            family.setLayoutY(5);
            pane.getChildren().add(family);

            Label phone = new Label("Choose");
            phone.setFont(new Font(20));
            phone.setLayoutX(400);
            phone.setLayoutY(5);
            pane.getChildren().add(phone);

            updateList(pane);
        }

        private static void updateList(Pane pane) throws IOException {
            int i = 1;
            dataOutputStream.writeUTF("getAllOnlineSupporters," + token);
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<Supporter> supporters = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String[] splitInput = dataInputStream.readUTF().split("-");
                Supporter supporter = new Supporter(splitInput[0], splitInput[1], splitInput[2], splitInput[3], splitInput[4], splitInput[5]);
                supporters.add(supporter);
            }

            for (Supporter supporter : supporters) {

                Label name = new Label(supporter.getName());
                name.setFont(new Font(20));
                name.setLayoutX(10);
                name.setLayoutY(50 * i);
                pane.getChildren().add(name);

                Label family = new Label(supporter.getFamily());
                family.setFont(new Font(20));
                family.setLayoutX(200);
                family.setLayoutY(50 * i);
                pane.getChildren().add(family);

                Button Choose = new Button("Choose");
                Choose.setStyle("-fx-background-color: #858585");
                Choose.setLayoutX(400);
                Choose.setLayoutY(50 * i);
                Choose.setCursor(Cursor.HAND);
                Choose.setOnMouseClicked(e -> {
                    try {
                        dataOutputStream.writeUTF("setSupporterForBuyer," + supporter.getUsername() + "," + token);
                        dataOutputStream.flush();
                        BuyerChatMenu.show(supporter);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                pane.getChildren().add(Choose);

            }
        }

    }

    //Done
    static class BuyerGiftCards {
        public static void showPage() throws IOException, ClassNotFoundException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Gift Codes");
            label.setFont(new Font(30));
            label.setLayoutX(10);
            label.setLayoutY(100);
            parent.getChildren().add(label);
            makeTopMenu(parent);
            showFields(parent);

            Button backButton = new Button("Back");
            backButton.setLayoutX(300);
            backButton.setLayoutY(110);
            backButton.setStyle("-fx-background-color: #bababa");
            backButton.setCursor(Cursor.HAND);
            backButton.setOnMouseClicked(e -> {
                try {
                    new BuyerMenu().showPersonalArea();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(backButton);

            Button updateList = new Button("Update list");
            updateList.setLayoutX(400);
            updateList.setLayoutY(110);
            updateList.setStyle("-fx-background-color: #bababa");
            updateList.setCursor(Cursor.HAND);
            updateList.setOnMouseClicked(e -> {
                try {
                    showPage();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(updateList);

            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }

        private static void makeTopMenu(Pane parent) throws ClassNotFoundException {
            Pane topMenu = new Pane();
            topMenu.setStyle("-fx-background-color: #232f3e");
            topMenu.setPrefWidth(1280);
            topMenu.setPrefHeight(100);
            topMenu.setLayoutX(0);
            topMenu.setLayoutY(0);

            Image image = new Image(Paths.get("src/main/java/Client/view/images/mainMenu.png").toUri().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(70);
            imageView.setFitHeight(70);
            imageView.setLayoutY(10);
            imageView.setCursor(Cursor.HAND);
            imageView.setOnMouseClicked(e -> {
                try {
                    Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(imageView);

            Image log = new Image(Paths.get("src/main/java/Client/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
                try {
                    dataOutputStream.writeUTF("logout," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (dataInputStream.readUTF().equals("done")) {
                        try {
                            Menu.executeMainMenu();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);

//            ImageView personImage = loginBuyer.getImageView();
//            personImage.setFitWidth(70);
//            personImage.setFitHeight(70);
//            personImage.setLayoutX(320);
//            personImage.setLayoutY(10);
//            topMenu.getChildren().add(personImage);

            Label role = new Label(" Buyer");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);


            parent.getChildren().add(topMenu);
        }

        private static void showFields(Pane parent) throws IOException, ClassNotFoundException {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefWidth(1270);
            pane.setPrefHeight(600);
            pane.setLayoutX(5);
            pane.setLayoutY(150);
            parent.getChildren().add(pane);

            Label serial = new Label("Serial");
            serial.setFont(new Font(20));
            serial.setLayoutX(10);
            serial.setLayoutY(5);
            pane.getChildren().add(serial);

            Label start = new Label("Date of start");
            start.setFont(new Font(20));
            start.setLayoutX(300);
            start.setLayoutY(5);
            pane.getChildren().add(start);

            Label end = new Label("Date of end");
            end.setFont(new Font(20));
            end.setLayoutX(500);
            end.setLayoutY(5);
            pane.getChildren().add(end);

            Label percent = new Label("Percent");
            percent.setFont(new Font(20));
            percent.setLayoutX(700);
            percent.setLayoutY(5);
            updateList(pane);

            pane.getChildren().add(percent);

        }

        private static void updateList(Pane pane) throws IOException {
            int i = 1;
            dataOutputStream.writeUTF("getBuyerDiscounts," + token);
            dataOutputStream.flush();

            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<Discount> discounts = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String[] input = dataInputStream.readUTF().split("-");
                Discount discount = new Discount(input[0], input[1], input[2], Long.parseLong(input[4]), Integer.parseInt(input[3]));
                discounts.add(discount);
            }

            for (Discount discount : discounts) {

                Label serial = new Label(discount.getCode());
                serial.setFont(new Font(20));
                serial.setLayoutX(10);
                serial.setLayoutY(50 * i);
                pane.getChildren().add(serial);

                Label start = new Label(discount.getStartTime().toString());
                start.setFont(new Font(20));
                start.setLayoutX(300);
                start.setLayoutY(50 * i);
                pane.getChildren().add(start);

                Label end = new Label(discount.getEndTime().toString());
                end.setFont(new Font(20));
                end.setLayoutX(500);
                end.setLayoutY(50 * i);
                pane.getChildren().add(end);

                Label percent = new Label(String.valueOf(discount.getDiscountPercent()));
                percent.setFont(new Font(20));
                percent.setLayoutX(700);
                percent.setLayoutY(50 * i);
                pane.getChildren().add(percent);

                i++;
            }
        }
    }

    //Done
    static class BuyerBuyLogs {
        public static void show() throws IOException, ClassNotFoundException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Buy logs");
            label.setFont(new Font(30));
            label.setLayoutX(10);
            label.setLayoutY(100);
            parent.getChildren().add(label);
            makeTopOfMenu(parent);
            showFields(parent);

            Button backButton = new Button("Back");
            backButton.setLayoutX(300);
            backButton.setLayoutY(110);
            backButton.setStyle("-fx-background-color: #bababa");
            backButton.setCursor(Cursor.HAND);
            backButton.setOnMouseClicked(e -> {
                try {
                    new BuyerMenu().showPersonalArea();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(backButton);


            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }

        private static void showFields(Pane parent) throws IOException, ClassNotFoundException {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefWidth(1270);
            pane.setPrefHeight(500);
            pane.setLayoutX(5);
            pane.setLayoutY(150);

            Label productName = new Label("Serial");
            productName.setLayoutX(10);
            productName.setLayoutY(10);
            productName.setFont(new Font(25));
            pane.getChildren().add(productName);

            Label seller = new Label("Products");
            seller.setLayoutX(150);
            seller.setLayoutY(10);
            seller.setFont(new Font(25));
            pane.getChildren().add(seller);

            Label productPrice = new Label("Price");
            productPrice.setLayoutX(350);
            productPrice.setLayoutY(10);
            productPrice.setFont(new Font(25));
            pane.getChildren().add(productPrice);

            Label dateOfBuy = new Label("Date");
            dateOfBuy.setLayoutX(550);
            dateOfBuy.setLayoutY(10);
            dateOfBuy.setFont(new Font(25));
            pane.getChildren().add(dateOfBuy);

            Label off = new Label("Off");
            off.setLayoutX(750);
            off.setLayoutY(10);
            off.setFont(new Font(25));
            pane.getChildren().add(off);

            Label finalPrice = new Label("Final Price");
            finalPrice.setLayoutX(950);
            finalPrice.setLayoutY(10);
            finalPrice.setFont(new Font(25));
            pane.getChildren().add(finalPrice);

            Label delivery = new Label("Delivery");
            delivery.setLayoutX(1150);
            delivery.setLayoutY(10);
            delivery.setFont(new Font(25));
            pane.getChildren().add(delivery);

            updateList(pane);

            parent.getChildren().add(pane);
        }

        //Done
        private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
            int i = 1;

            dataOutputStream.writeUTF("buyLogs" + "," + token);
            dataOutputStream.flush();

            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<BuyLog> buyLogs = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String[] input = dataInputStream.readUTF().split("-");
                BuyLog buyLog = new BuyLog(input[0], input[1], Double.parseDouble(input[2]), Double.parseDouble(input[3]), input[4]);
                buyLogs.add(buyLog);
            }

            for (BuyLog buyLog : buyLogs) {

                Label logId = new Label(buyLog.getLogId());
                logId.setLayoutX(10);
                logId.setLayoutY(50 * i);
                logId.setFont(new Font(20));
                pane.getChildren().add(logId);

                Label product = new Label("Click");
                product.setLayoutX(160);
                product.setLayoutY(50 * i);
                product.setFont(new Font(20));
                product.setCursor(Cursor.HAND);
                product.setOnMouseClicked(e -> {
                    ScrollPane scrollPane = new ScrollPane();
                    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                    Pane pane1 = new Pane();
                    int ii = 1;
                    ArrayList<Product> products = new ArrayList<>();
                    try {
                        dataOutputStream.writeUTF("buylogProducts id-" + buyLog.getLogId() + "," + token);
                        dataOutputStream.flush();
                        int size1 = dataInputStream.read();
                        for (int j = 0; j < size1; j++) {
                            String[] input = dataInputStream.readUTF().split("-");
                            Product product1 = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
                            products.add(product1);
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    for (Product product1 : products) {
                        Label label = new Label(product1.getName());
                        label.setFont(new Font(25));
                        label.setLayoutX(10);
                        label.setLayoutY(10 * ii);
                        pane1.getChildren().add(label);
                        ii++;
                    }
                    Scene scene = new Scene(pane1, 500, 500);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                pane.getChildren().add(product);

                Label productPrice = new Label(String.valueOf(buyLog.getMoneyThatPaid()));
                productPrice.setLayoutX(350);
                productPrice.setLayoutY(50 * i);
                productPrice.setFont(new Font(20));
                pane.getChildren().add(productPrice);

                Label dateOfBuy = new Label(buyLog.getLocalTime().toString());
                dateOfBuy.setLayoutX(550);
                dateOfBuy.setLayoutY(50 * i);
                dateOfBuy.setFont(new Font(20));
                pane.getChildren().add(dateOfBuy);

                Label off = new Label(String.valueOf(buyLog.getDiscount()));
                off.setLayoutX(750);
                off.setLayoutY(50 * i);
                off.setFont(new Font(20));
                pane.getChildren().add(off);

                Label finalPrice = new Label(String.valueOf(buyLog.getMoneyThatPaid() - buyLog.getDiscount()));
                finalPrice.setLayoutX(950);
                finalPrice.setLayoutY(50 * i);
                finalPrice.setFont(new Font(20));
                pane.getChildren().add(finalPrice);

                Label delivery = new Label();
                dataOutputStream.writeUTF("isFileOrNot," + buyLog.getLogId() + "," + token);
                dataOutputStream.flush();
                if (dataInputStream.readUTF().equals("yes")) {
                    delivery.setText("download");
                    delivery.setOnMouseClicked(e -> {

                        Pane parent = new Pane();
                        TextField textField = new TextField();
                        textField.setPromptText("url");
                        textField.setLayoutY(20);

                        Button download = new Button();
                        download.setText("download");
                        download.setLayoutY(60);
                        download.setOnMouseClicked(event -> {
                            if (!textField.getText().isEmpty()){
                                try {
                                    dataOutputStream.writeUTF("downloadFile," + buyLog.getLogId() + "," + token);
                                    dataOutputStream.flush();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                String path = textField.getText();
                                File deliveredFile = new File(path);
                                try {
                                    byte[] byteArray = new byte[999999999];
                                    FileOutputStream fileOutputStream = new FileOutputStream(deliveredFile, false);
                                    int bytesRead = dataInputStream.read(byteArray, 0, byteArray.length);
                                    fileOutputStream.write(byteArray, 0, bytesRead);
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        });

                        parent.getChildren().addAll(textField, download);
                        Stage stage = new Stage();
                        Scene scene = new Scene(parent, 200, 100);
                        stage.setScene(scene);
                        stage.show();
                    });
                } else {
                    delivery.setText(buyLog.getProductReceived());
                }
                delivery.setLayoutX(1150);
                delivery.setLayoutY(50 * i);
                delivery.setFont(new Font(20));
                pane.getChildren().add(delivery);

                i++;
            }

        }

        private static void makeTopOfMenu(Pane parent) throws IOException, ClassNotFoundException {
            Pane topMenu = new Pane();
            topMenu.setStyle("-fx-background-color: #232f3e");
            topMenu.setPrefWidth(1280);
            topMenu.setPrefHeight(100);
            topMenu.setLayoutX(0);
            topMenu.setLayoutY(0);

//            ImageView imageView = loginBuyer.getImageView();
//            imageView.setFitWidth(70);
//            imageView.setFitHeight(70);
//            imageView.setLayoutY(10);
//            imageView.setCursor(Cursor.HAND);
//            imageView.setOnMouseClicked(e -> {
//                try {
//                    Menu.executeMainMenu();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            });
//            topMenu.getChildren().add(imageView);

            Image log = new Image(Paths.get("src/main/java/Client/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
                try {
                    dataOutputStream.writeUTF("logout," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (dataInputStream.readUTF().equals("done")) {
                        try {
                            Menu.executeMainMenu();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);
            Image person = new Image(Paths.get("src/main/java/Client/view/images/unknownPerson.jpg").toUri().toString());
            ImageView personImage = new ImageView(person);
            personImage.setFitWidth(70);
            personImage.setFitHeight(70);
            personImage.setLayoutX(320);
            personImage.setLayoutY(10);
            topMenu.getChildren().add(personImage);

            Label role = new Label(" Buyer");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);

            parent.getChildren().add(topMenu);
        }
    }
}
