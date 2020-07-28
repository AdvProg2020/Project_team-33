package Client.View.SellerMenu;

import Client.Controller.RegisterAndLogin.PersonController;
import Client.Model.Auction;
import Client.Model.Category.Category;
import Client.Model.Logs.BuyLog;
import Client.Model.Logs.SellLog;
import Client.Model.Product;
import Client.Model.Requests.Request;
import Client.Model.Users.Person;
import Client.Model.Users.Seller;
import Client.View.BuyerMenu.BuyerMenu;
import Client.View.ManagerMenu.ManagerMenu;
import Client.View.Menu;
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
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

public class SellerMenu extends Menu {
    private static Person loginSeller;

    public void show() throws IOException, ClassNotFoundException {
        dataOutputStream.writeUTF("getPerson," + token);
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        loginSeller = gson.fromJson(json.substring(7), Person.class);
        dataOutputStream.writeUTF("setOnline id-" + loginSeller.getUsername() + "-yes," + token);
        dataOutputStream.flush();
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

        createPersonalInfoPanel(parent);
        createSellLogsPanel(parent);
        createSalesListPanel(parent);
        createBalancePanel(parent);
        createRequestPanel(parent);
        createAuctionsPanel(parent);
        createCategoriesPanel(parent);
        createPublicSalePanel(parent);
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
                new SellerMenu().show();
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
    private void createPersonalInfoPanel(Pane parent) {
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
                SellerPersonalInfoAbilities.editPersonalInfo();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

    }

    //Done
    private void createSellLogsPanel(Pane parent) {
        Pane sellLogs = new Pane();
        sellLogs.setStyle("-fx-background-color: #bababa");
        sellLogs.setPrefWidth(240);
        sellLogs.setPrefHeight(70);
        sellLogs.setLayoutX(490);
        sellLogs.setLayoutY(200);
        sellLogs.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/basket.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        sellLogs.getChildren().add(imageView);

        Label orderLabel = new Label("Sell Logs");
        orderLabel.setFont(new Font(20));
        orderLabel.setLayoutX(60);
        orderLabel.setLayoutY(10);
        sellLogs.getChildren().add(orderLabel);

        Label orderSecondLabel = new Label("All log of products that sell");
        orderSecondLabel.setFont(new Font(12));
        orderSecondLabel.setLayoutX(60);
        orderSecondLabel.setLayoutY(40);
        sellLogs.getChildren().add(orderSecondLabel);

        sellLogs.setOnMouseClicked(e -> {
            try {
                SellerLogs.show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(sellLogs);
    }

    //Done
    private void createSalesListPanel(Pane parent) {
        Pane salesLists = new Pane();
        salesLists.setStyle("-fx-background-color: #bababa");
        salesLists.setPrefWidth(240);
        salesLists.setPrefHeight(70);
        salesLists.setLayoutX(890);
        salesLists.setLayoutY(200);
        salesLists.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/giftCard.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        salesLists.getChildren().add(imageView);

        Label giftCardLabel = new Label("Sells List");
        giftCardLabel.setFont(new Font(20));
        giftCardLabel.setLayoutX(60);
        giftCardLabel.setLayoutY(10);
        salesLists.getChildren().add(giftCardLabel);

        Label giftCardSecondLabel = new Label("List of your available products");
        giftCardSecondLabel.setFont(new Font(12));
        giftCardSecondLabel.setLayoutX(60);
        giftCardSecondLabel.setLayoutY(40);
        salesLists.getChildren().add(giftCardSecondLabel);

        salesLists.setOnMouseClicked(e -> {
            try {
                SellerProducts.show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        parent.getChildren().add(salesLists);
    }

    //Done
    private void createBalancePanel(Pane parent) {
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

        Label balanceSecondLabel = new Label("Client.View your balance");
        balanceSecondLabel.setFont(new Font(12));
        balanceSecondLabel.setLayoutX(60);
        balanceSecondLabel.setLayoutY(40);
        balance.getChildren().add(balanceSecondLabel);

        balance.setOnMouseClicked(e -> {
            try {
                SellerBalance.showPage();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(balance);
    }

    //Done
    public void createRequestPanel(Pane parent) {
        Pane request = new Pane();
        request.setStyle("-fx-background-color: #bababa");
        request.setPrefWidth(240);
        request.setPrefHeight(70);
        request.setLayoutX(490);
        request.setLayoutY(350);
        request.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/request.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        request.getChildren().add(imageView);

        Label requestLabel = new Label("Request");
        requestLabel.setFont(new Font(20));
        requestLabel.setLayoutX(60);
        requestLabel.setLayoutY(10);
        request.getChildren().add(requestLabel);

        Label requestSecondLabel = new Label("Send request to manager");
        requestSecondLabel.setFont(new Font(12));
        requestSecondLabel.setLayoutX(60);
        requestSecondLabel.setLayoutY(40);
        request.getChildren().add(requestSecondLabel);

        request.setOnMouseClicked(e -> {
            try {
                SellerRequests.show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(request);
    }

    //Done
    public void createAuctionsPanel(Pane parent) {
        Pane auction = new Pane();
        auction.setStyle("-fx-background-color: #bababa");
        auction.setPrefWidth(240);
        auction.setPrefHeight(70);
        auction.setLayoutX(890);
        auction.setLayoutY(350);
        auction.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/auction.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        auction.getChildren().add(imageView);


        Label auctionLabel = new Label("Auctions");
        auctionLabel.setFont(new Font(20));
        auctionLabel.setLayoutX(60);
        auctionLabel.setLayoutY(10);
        auction.getChildren().add(auctionLabel);

        Label auctionSecondLabel = new Label("All auctions");
        auctionSecondLabel.setFont(new Font(12));
        auctionSecondLabel.setLayoutX(60);
        auctionSecondLabel.setLayoutY(40);
        auction.getChildren().add(auctionSecondLabel);

        auction.setOnMouseClicked(e -> {

        });
        parent.getChildren().add(auction);
    }

    //Done
    public void createCategoriesPanel(Pane parent) {
        Pane category = new Pane();
        category.setStyle("-fx-background-color: #bababa");
        category.setPrefWidth(210);
        category.setPrefHeight(70);
        category.setLayoutX(90);
        category.setLayoutY(500);
        category.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/category.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        category.getChildren().add(imageView);


        Label categoryLabel = new Label("Category");
        categoryLabel.setFont(new Font(20));
        categoryLabel.setLayoutX(60);
        categoryLabel.setLayoutY(10);
        category.getChildren().add(categoryLabel);

        Label categorySecondLabel = new Label("All categories");
        categorySecondLabel.setFont(new Font(12));
        categorySecondLabel.setLayoutX(60);
        categorySecondLabel.setLayoutY(40);
        category.getChildren().add(categorySecondLabel);

        category.setOnMouseClicked(e -> {
            try {
                Categories.showPage();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(category);
    }

    //Done
    private void createPublicSalePanel(Pane parent) {
        Pane publicSale = new Pane();
        publicSale.setStyle("-fx-background-color: #bababa");
        publicSale.setPrefWidth(210);
        publicSale.setPrefHeight(70);
        publicSale.setLayoutX(490);
        publicSale.setLayoutY(500);
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

        Label publicSaleSecondLabel = new Label("products that you can sale");
        publicSaleSecondLabel.setFont(new Font(12));
        publicSaleSecondLabel.setLayoutX(60);
        publicSaleSecondLabel.setLayoutY(40);
        publicSale.getChildren().add(publicSaleSecondLabel);

        publicSale.setOnMouseClicked(e -> {
            try {
                PublicSale.show();
            } catch (IOException | ClassNotFoundException ex) {
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


//        ImageView personImage = loginSeller.getImageView();
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

        Label role = new Label("Seller");
        role.setFont(new Font(30));
        role.setLayoutX(640);
        role.setLayoutY(30);
        role.setTextFill(Color.WHITE);
        topMenu.getChildren().add(role);

        parent.getChildren().add(topMenu);
    }

    static class UploadFile {
        public static void show() {
            Pane pane = new Pane();
            Label id = new Label("Id (6 digits)");
            id.setFont(new Font("Ink Free", 25));
            id.setLayoutX(300);
            id.setLayoutY(10);
            pane.getChildren().add(id);

            TextField idField = new TextField();
            idField.setLayoutX(300);
            idField.setLayoutY(50);
            pane.getChildren().add(idField);

            Label name = new Label("Name");
            name.setFont(new Font("Ink Free", 25));
            name.setLayoutX(300);
            name.setLayoutY(90);
            pane.getChildren().add(name);

            TextField nameField = new TextField();
            nameField.setLayoutX(300);
            nameField.setLayoutY(120);
            pane.getChildren().add(nameField);

            Label price = new Label("Price");
            price.setFont(new Font("Ink Free", 25));
            price.setLayoutX(300);
            price.setLayoutY(160);
            pane.getChildren().add(price);

            TextField priceField = new TextField();
            priceField.setLayoutX(300);
            priceField.setLayoutY(190);
            pane.getChildren().add(priceField);

            Label category = new Label("Category");
            category.setFont(new Font("Ink Free", 25));
            category.setLayoutX(300);
            category.setLayoutY(230);
            pane.getChildren().add(category);

            TextField categoryField = new TextField();
            categoryField.setLayoutX(300);
            categoryField.setLayoutY(260);
            pane.getChildren().add(categoryField);

            Label description = new Label("Description");
            description.setFont(new Font("Ink Free", 25));
            description.setLayoutX(300);
            description.setLayoutY(300);
            pane.getChildren().add(description);

            TextField descriptionField = new TextField();
            descriptionField.setLayoutX(300);
            descriptionField.setLayoutY(330);
            pane.getChildren().add(descriptionField);

            Label url = new Label("File Address");
            url.setFont(new Font("Ink Free", 25));
            url.setLayoutX(300);
            url.setLayoutY(370);
            pane.getChildren().add(url);

            TextField urlField = new TextField();
            urlField.setLayoutX(300);
            urlField.setLayoutY(400);
            pane.getChildren().add(urlField);

            Button button = new Button("Add");
            button.setCursor(Cursor.HAND);
            button.setLayoutX(345);
            button.setLayoutY(440);
            button.setOnMouseClicked(e -> {
                try {
                    dataOutputStream.writeUTF("addFile," + (idField.getText().isEmpty() ? " " : idField.getText()) + "," + (nameField.getText().isEmpty() ? " " : nameField.getText()) + "," +
                            (priceField.getText().isEmpty() ? " " : priceField.getText()) + "," + (categoryField.getText().isEmpty() ? " " : categoryField.getText()) + ","
                            + (descriptionField.getText().isEmpty() ? " " : descriptionField.getText()) + "," + (urlField.getText().isEmpty() ? " " : urlField.getText()) + "," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                String[] splitInput = new String[7];
                try {
                    splitInput = dataInputStream.readUTF().split("-");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                Label idError = new Label();
                idError.setLayoutX(300);
                idError.setLayoutY(70);
                switch (splitInput[0]) {
                    case "1":
                        idError.setText("Complete");
                        idError.setTextFill(Color.RED);
                        break;
                    case "2":
                        idError = new Label("At least 6 digit");
                        idError.setTextFill(Color.RED);
                        break;
                    case "3":
                        idError.setText("Already exist");
                        idError.setTextFill(Color.RED);
                        break;
                }
                pane.getChildren().add(idError);

                Label label2;
                if (splitInput[1].equals("1")) {
                    label2 = new Label("Complete");
                    label2.setTextFill(Color.RED);
                    label2.setLayoutX(300);
                    label2.setLayoutY(140);
                    pane.getChildren().add(label2);
                }

                Label priceError = new Label();
                if (splitInput[2].equals("1")) {
                    priceError.setText("Complete");
                    priceError.setTextFill(Color.RED);
                    priceError.setLayoutX(300);
                    priceError.setLayoutY(210);
                    pane.getChildren().add(priceError);
                }

                Label label;
                if (splitInput[3].equals("1")) {
                    label = new Label("Complete");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(300);
                    label.setLayoutY(280);
                    pane.getChildren().add(label);
                } else if (splitInput[3].equals("2")) {
                    label = new Label("Not exist");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(300);
                    label.setLayoutY(280);
                    pane.getChildren().add(label);
                }

                if (splitInput[4].equals("1")) {
                    label = new Label("Complete");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(300);
                    label.setLayoutY(350);
                    pane.getChildren().add(label);
                }

                Label urlError = new Label();
                if (splitInput[5].equals("1")) {
                    urlError.setText("Complete");
                    urlError.setTextFill(Color.RED);
                    urlError.setLayoutX(300);
                    urlError.setLayoutY(420);
                    pane.getChildren().add(urlError);
                }

                if (splitInput[6].equals("pass")) {
                    if (!priceField.getText().matches("\\d+")) {
                        priceError.setText("enter correct value for price");
                    } else {
                        try {
                            String path = urlField.getText();

                            File myFile = new File(path);
                            byte[] myByteArray = new byte[(int) myFile.length()];
                            FileInputStream fis = null;

                            try {
                                fis = new FileInputStream(myFile);
                                String type = path.substring(path.lastIndexOf("."));
                                dataOutputStream.writeUTF("uploadFile," + idField.getText() + "," + nameField.getText() + "," + priceField.getText() + "," + categoryField.getText()
                                        + "," + descriptionField.getText() + "," + type + "," + token);
                                dataOutputStream.flush();

                                BufferedInputStream bis = new BufferedInputStream(fis);
                                bis.read(myByteArray, 0, myByteArray.length);
                                dataOutputStream.write(myByteArray, 0, myByteArray.length);
                                dataOutputStream.flush();
                            } catch (FileNotFoundException ex) {
                                urlError.setText("can not find the file with this url!");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    label = new Label("Done");
                    label.setLayoutX(400);
                    label.setLayoutY(490);
                    label.setTextFill(Color.GREEN);
                    pane.getChildren().add(label);
                }
            });
            pane.getChildren().add(button);

            Scene scene = new Scene(pane, 800, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }

    }

    //Done
    static class SellerPersonalInfoAbilities {
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
//
//            ImageView imageView = (loginSeller).getImageView();
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

            Label role = new Label("Seller");
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
            dataOutputStream.writeUTF("getPerson," + token);
            dataOutputStream.flush();
            Gson gson = new Gson();
            loginSeller = gson.fromJson(dataInputStream.readUTF().substring(7), Person.class);

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
            company(personalInfo);

            Button button = new Button("Save and back");
            button.setPrefWidth(100);
            button.setLayoutX(150);
            button.setLayoutY(350);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);
            button.setOnMouseClicked(e -> {
                try {
                    new SellerMenu().showPersonalArea();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }

        private static void name(Pane personalInfo) throws IOException, ClassNotFoundException {
            Label name = new Label("Name:" + "\n" + (loginSeller).getName());
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

        private static void family(Pane personalInfo) throws IOException, ClassNotFoundException {
            Label family = new Label("Family:" + "\n" + loginSeller.getFamily());
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

        private static void email(Pane personalInfo) throws IOException {
            Label email = new Label("Email:" + "\n" + loginSeller.getEmail());
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

        private static void phone(Pane personalInfo) throws IOException {
            Label phone = new Label("Phone:" + "\n" + loginSeller.getPhone());
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
            Label password = new Label("Password:" + "\n" + loginSeller.getPassword());
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

        private static void company(Pane personalInfo) throws IOException, ClassNotFoundException {
            dataOutputStream.writeUTF("name of company id-" + loginSeller.getUsername() + "," + token);
            dataOutputStream.flush();
            String message = dataInputStream.readUTF();
            Label company = new Label("Company:" + "\n" + message);
            company.setLayoutX(20);
            company.setLayoutY(250);
            company.setFont(new Font(15));
            personalInfo.getChildren().add(company);

            Line line5 = new Line();
            line5.setStartX(0);
            line5.setEndX(400);
            line5.setStartY(300);
            line5.setEndY(300);
            personalInfo.getChildren().add(line5);

            Button button = new Button("Edit");
            button.setLayoutX(350);
            button.setLayoutY(260);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);

            TextField textField = new TextField();
            textField.setPromptText("New company");
            textField.setLayoutX(200);
            textField.setLayoutY(260);
            personalInfo.getChildren().add(textField);
            button.setOnMouseClicked(e -> {
                Label label = new Label();
                label.setFont(new Font(10));
                label.setLayoutX(200);
                label.setLayoutY(285);
                personalInfo.getChildren().add(label);
                if (textField.getText().isEmpty()) {
                    label.setText("Complete for edit");
                    label.setTextFill(Color.RED);
                } else {
                    try {
                        dataOutputStream.writeUTF("editPersonalInfo,company," + textField.getText() + "," + token);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        if (dataInputStream.readUTF().equals("done")) {
                            label.setText("Done");
                            label.setTextFill(Color.GREEN);
                            company.setText("Company:" + "\n" + textField.getText());
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }

    //TODO
    static class PublicSale {

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
                    new SellerMenu().showPersonalArea();
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

            Label role = new Label("Seller");
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

            Label buyers = new Label("add to public sale");
            buyers.setFont(new Font(20));
            buyers.setLayoutX(1100);
            buyers.setLayoutY(5);
            pane.getChildren().add(buyers);

            updateList(pane);
        }

        private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
            int i = 1;
            dataOutputStream.writeUTF("getProductsForSeller," + token);
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<Product> products = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String[] input = dataInputStream.readUTF().split("-");
                Product product = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
                products.add(product);
            }

            for (Product product : products) {
                if (product.getNumberOfProducts() > 0) {
                    Label id = new Label(product.getProductID());
                    id.setFont(new Font(20));
                    id.setLayoutX(10);
                    id.setLayoutY(50 * i);
                    pane.getChildren().add(id);

                    Label name = new Label(product.getName());
                    name.setFont(new Font(20));
                    name.setLayoutX(200);
                    name.setLayoutY(50 * i);
                    pane.getChildren().add(name);

                    Label money = new Label(String.valueOf(product.getMoney()));
                    money.setFont(new Font(20));
                    money.setLayoutX(400);
                    money.setLayoutY(50 * i);
                    pane.getChildren().add(money);

                    Label category = new Label(String.valueOf(product.getCategory()));
                    category.setFont(new Font(20));
                    category.setLayoutX(900);
                    category.setLayoutY(50 * i);
                    pane.getChildren().add(category);

                    Label description = new Label(String.valueOf(product.getDescription()));
                    description.setFont(new Font(20));
                    description.setLayoutX(650);
                    description.setLayoutY(50 * i);
                    pane.getChildren().add(description);

//                    if (!product.getCondition().equals("Unknown")) {
                    Button publicSale = new Button("add to public sale");
                    publicSale.setStyle("-fx-background-color: #858585");
                    publicSale.setLayoutX(1100);
                    publicSale.setLayoutY(50 * i);
                    publicSale.setCursor(Cursor.HAND);
                    publicSale.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.writeUTF("checkPublicSale," + token);
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("yes")) {
                                writeEndTime(product);
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("wait until last auction ends");
                                alert.showAndWait();
                            }
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }

                    });
                    pane.getChildren().add(publicSale);
//                    }

                }
            }
        }

        private static void writeEndTime(Product product) throws ClassNotFoundException {
            Stage stage = new Stage();
            Pane parent = new Pane();
            Label endTime = new Label("End Time");
            endTime.setLayoutX(450);
            endTime.setLayoutY(200);
            endTime.setFont(new Font(25));
            parent.getChildren().add(endTime);

            TextField textField = new TextField();
            textField.setLayoutX(420);
            textField.setLayoutY(240);
            parent.getChildren().add(textField);

            Button button = new Button("Done");
            button.setLayoutX(475);
            button.setLayoutY(290);
            button.setStyle("-fx-background-color: lightblue");
            button.setCursor(Cursor.HAND);
            button.setOnMouseClicked(e1 -> {
                try {
                    dataOutputStream.writeUTF("addPublicSale," + product.getProductID() + "," + textField.getText() + "," + token);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("public sale has been added");
                stage.close();
            });

            parent.getChildren().add(button);

            Scene scene = new Scene(parent, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }

    }

    //ToDo
    static class SellerLogs {
        public static void show() throws IOException, ClassNotFoundException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            showFields(parent);
            makeTopOfMenu(parent);
            Label label = new Label("Sell logs");
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
                    new SellerMenu().showPersonalArea();
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
                    updateList(parent);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(updateList);

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
            pane.setLayoutY(160);

            Label productName = new Label("Serial");
            productName.setLayoutX(10);
            productName.setLayoutY(10);
            productName.setFont(new Font(25));
            pane.getChildren().add(productName);

            Label productPrice = new Label("Price");
            productPrice.setLayoutX(350);
            productPrice.setLayoutY(10);
            productPrice.setFont(new Font(25));
            pane.getChildren().add(productPrice);

            Label numberOfProduct = new Label("Date");
            numberOfProduct.setLayoutX(550);
            numberOfProduct.setLayoutY(10);
            numberOfProduct.setFont(new Font(25));
            pane.getChildren().add(numberOfProduct);

            Label increaseOrDecrease = new Label("Off");
            increaseOrDecrease.setLayoutX(750);
            increaseOrDecrease.setLayoutY(10);
            increaseOrDecrease.setFont(new Font(25));
            pane.getChildren().add(increaseOrDecrease);

            Label finalPrice = new Label("Product");
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

        private static void updateList(Pane pane) throws IOException, ClassNotFoundException {

            dataOutputStream.writeUTF("sellLogs");
            dataOutputStream.flush();

            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<SellLog> sellLogs = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String[] input = dataInputStream.readUTF().split("-");
                SellLog sellLog = new SellLog(input[0], input[1], Double.parseDouble(input[2]), Double.parseDouble(input[3]), input[4], input[5], input[6]);
                sellLogs.add(sellLog);
            }
            int i = 1;

            for (SellLog log : sellLogs) {
                Label id = new Label(log.getLogId());
                id.setLayoutX(10);
                id.setLayoutY(50 * i);
                id.setFont(new Font(20));
                pane.getChildren().add(id);

                Label price = new Label(String.valueOf(log.getMoneyThatPaid()));
                price.setLayoutX(350);
                price.setLayoutY(50 * i);
                price.setFont(new Font(20));
                pane.getChildren().add(price);

                Label date = new Label(log.getLocalTime().toString());
                date.setLayoutX(550);
                date.setLayoutY(50 * i);
                date.setFont(new Font(20));
                pane.getChildren().add(date);

                Label off = new Label(String.valueOf(log.getDiscount()));
                off.setLayoutX(750);
                off.setLayoutY(50 * i);
                off.setFont(new Font(20));
                pane.getChildren().add(off);

                Label finalPrice = new Label(String.valueOf(log.getMoneyThatPaid() - log.getDiscount()));
                finalPrice.setLayoutX(950);
                finalPrice.setLayoutY(50 * i);
                finalPrice.setFont(new Font(20));
                pane.getChildren().add(finalPrice);

                Label delivery = new Label(log.getProductReceived());
                delivery.setLayoutX(1150);
                delivery.setLayoutY(50 * i);
                delivery.setFont(new Font(25));
                pane.getChildren().add(delivery);

                i++;
            }
        }

        private static void makeTopOfMenu(Pane parent) throws IOException {
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
//
//            ImageView personImage = loginSeller.getImageView();
//            personImage.setFitWidth(70);
//            personImage.setFitHeight(70);
//            personImage.setLayoutX(320);
//            personImage.setLayoutY(10);
//            topMenu.getChildren().add(personImage);

            Label role = new Label("Seller");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);


            parent.getChildren().add(topMenu);
        }
    }

    //Done
    static class SellerProducts {
        public static void show() throws IOException, ClassNotFoundException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Sales list Page");
            label.setLayoutX(10);
            label.setLayoutY(100);
            label.setFont(new Font(30));
            parent.getChildren().add(label);
            showFields(parent);
            makeTopOfMenu(parent);

            Button back = new Button("Back");
            back.setStyle("-fx-background-color: #bababa");
            back.setCursor(Cursor.HAND);
            back.setLayoutX(400);
            back.setLayoutY(110);
            back.setOnMouseClicked(e -> {
                try {
                    new SellerMenu().show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(back);

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

            Label role = new Label("Seller");
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

            Label buyers = new Label("Buyers");
            buyers.setFont(new Font(20));
            buyers.setLayoutX(1100);
            buyers.setLayoutY(5);
            pane.getChildren().add(buyers);

            updateList(pane);
        }

        private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
            int i = 1;
            dataOutputStream.writeUTF("getProductsForSeller," + token);
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<Product> products = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String[] input = dataInputStream.readUTF().split("-");
                Product product = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
                products.add(product);
            }

            for (Product allProduct : products) {
                Label id = new Label(allProduct.getProductID());
                id.setFont(new Font(20));
                id.setLayoutX(10);
                id.setLayoutY(50 * i);
                pane.getChildren().add(id);

                Label name = new Label(allProduct.getName());
                name.setFont(new Font(20));
                name.setLayoutX(200);
                name.setLayoutY(50 * i);
                pane.getChildren().add(name);

                Label money = new Label(String.valueOf(allProduct.getMoney()));
                money.setFont(new Font(20));
                money.setLayoutX(400);
                money.setLayoutY(50 * i);
                pane.getChildren().add(money);

                Label category = new Label(allProduct.getCategory());
                category.setFont(new Font(20));
                category.setLayoutX(900);
                category.setLayoutY(50 * i);
                pane.getChildren().add(category);

                Label description = new Label(String.valueOf(allProduct.getDescription()));
                description.setFont(new Font(20));
                description.setLayoutX(650);
                description.setLayoutY(50 * i);
                pane.getChildren().add(description);

                Button buyers = new Button("Buyers");
                buyers.setStyle("-fx-background-color: #858585");
                buyers.setLayoutX(1100);
                buyers.setLayoutY(50 * i);
                buyers.setCursor(Cursor.HAND);
                buyers.setOnMouseClicked(e -> {
                    try {
                        dataOutputStream.writeUTF("getBuyersOfProduct id-" + allProduct.getProductID() + "," + token);
                        dataOutputStream.flush();
                        int size1 = Integer.parseInt(dataInputStream.readUTF());
                        int counter = 1;
                        Pane pane1 = new Pane();
                        for (int j = 0; j < size1; j++) {
                            String buyer = dataInputStream.readUTF();
                            Label label = new Label(buyer);
                            label.setLayoutY(20 * counter);
                            pane1.getChildren().add(label);
                            counter++;
                        }
                        Scene scene1 = new Scene(pane1, 200, 200);
                        Stage stage1 = new Stage();
                        stage1.setScene(scene1);
                        stage1.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                });
                pane.getChildren().add(buyers);

                i++;
            }
        }
    }

    //ToDo
    static class SellerRequests {
        public static void show() throws IOException, ClassNotFoundException {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Request Page");
            label.setLayoutX(120);
            label.setLayoutY(130);
            label.setFont(new Font(30));
            parent.getChildren().add(label);
            makeTopOfMenu(parent);
            addProductPage(parent);
            editProductPage(parent);
            removeProductPage(parent);
            addAuctionPage(parent);
            editAuctionPage(parent);
            allRequestsPage(parent);
            uploadFile(parent);

            Button back = new Button("Back");
            back.setStyle("-fx-background-color: #bababa");
            back.setCursor(Cursor.HAND);
            back.setLayoutX(400);
            back.setLayoutY(140);
            back.setOnMouseClicked(e -> {
                try {
                    new SellerMenu().show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(back);

            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }

        private static void makeTopOfMenu(Pane parent) throws IOException {
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

            Image logout = new Image(Paths.get("src/main/java/Client/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(logout);
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

            Label role = new Label("Seller");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);


            parent.getChildren().add(topMenu);
        }

        private static void addProductPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(120);
            requestPage.setLayoutY(200);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);

            Label addProductLabel = new Label("Add Product");
            addProductLabel.setFont(new Font(20));
            addProductLabel.setLayoutX(45);
            addProductLabel.setLayoutY(20);
            requestPage.getChildren().add(addProductLabel);
            requestPage.setOnMouseClicked(e -> {
                AddProduct.addProduct();
            });
        }

        private static void editProductPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(520);
            requestPage.setLayoutY(200);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);

            Label editProductLabel = new Label("Edit Product");
            editProductLabel.setFont(new Font(20));
            editProductLabel.setLayoutX(45);
            editProductLabel.setLayoutY(20);
            requestPage.getChildren().add(editProductLabel);

            requestPage.setOnMouseClicked(e -> {
                try {
                    EditProduct.show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }

        private static void removeProductPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(920);
            requestPage.setLayoutY(200);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);

            Label removeProductLabel = new Label("Remove Product");
            removeProductLabel.setFont(new Font(20));
            removeProductLabel.setLayoutX(35);
            removeProductLabel.setLayoutY(20);
            requestPage.getChildren().add(removeProductLabel);
            requestPage.setOnMouseClicked(e -> {
                try {
                    RemoveProduct.show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }

        private static void addAuctionPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(120);
            requestPage.setLayoutY(370);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);

            Label addAuctionLabel = new Label("Add Auction");
            addAuctionLabel.setFont(new Font(20));
            addAuctionLabel.setLayoutX(45);
            addAuctionLabel.setLayoutY(20);
            requestPage.getChildren().add(addAuctionLabel);

            requestPage.setOnMouseClicked(e -> {
                try {
                    AddAuction.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }

        private static void editAuctionPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(520);
            requestPage.setLayoutY(370);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);

            Label editAuctionLabel = new Label("Edit Auction");
            editAuctionLabel.setFont(new Font(20));
            editAuctionLabel.setLayoutX(45);
            editAuctionLabel.setLayoutY(20);
            requestPage.getChildren().add(editAuctionLabel);

            requestPage.setOnMouseClicked(e -> {
                EditAuction.show();
            });
        }

        private static void allRequestsPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(920);
            requestPage.setLayoutY(370);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);

            Label editAuctionLabel = new Label("All requests");
            editAuctionLabel.setFont(new Font(20));
            editAuctionLabel.setLayoutX(45);
            editAuctionLabel.setLayoutY(20);
            requestPage.getChildren().add(editAuctionLabel);

            requestPage.setOnMouseClicked(e -> {
                try {
                    AllRequests.show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }

        private static void uploadFile(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(120);
            requestPage.setLayoutY(540);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);

            Label editAuctionLabel = new Label("Add File");
            editAuctionLabel.setFont(new Font(20));
            editAuctionLabel.setLayoutX(45);
            editAuctionLabel.setLayoutY(20);
            requestPage.getChildren().add(editAuctionLabel);

            requestPage.setOnMouseClicked(e -> {
                UploadFile.show();
            });
        }

        static class AddProduct {
            private static void addProduct() {
                Pane pane = new Pane();
                Label id = new Label("Id (6 digits)");
                id.setFont(new Font("Ink Free", 25));
                id.setLayoutX(300);
                id.setLayoutY(10);
                pane.getChildren().add(id);

                TextField idField = new TextField();
                idField.setLayoutX(300);
                idField.setLayoutY(60);
                pane.getChildren().add(idField);

                Label name = new Label("Name");
                name.setFont(new Font("Ink Free", 25));
                name.setLayoutX(300);
                name.setLayoutY(110);
                pane.getChildren().add(name);

                TextField nameField = new TextField();
                nameField.setLayoutX(300);
                nameField.setLayoutY(160);
                pane.getChildren().add(nameField);

                Label price = new Label("Price");
                price.setFont(new Font("Ink Free", 25));
                price.setLayoutX(300);
                price.setLayoutY(210);
                pane.getChildren().add(price);

                TextField priceField = new TextField();
                priceField.setLayoutX(300);
                priceField.setLayoutY(260);
                pane.getChildren().add(priceField);

                Label category = new Label("Category");
                category.setFont(new Font("Ink Free", 25));
                category.setLayoutX(300);
                category.setLayoutY(310);
                pane.getChildren().add(category);

                TextField categoryField = new TextField();
                categoryField.setLayoutX(300);
                categoryField.setLayoutY(360);
                pane.getChildren().add(categoryField);

                Label description = new Label("Description");
                description.setFont(new Font("Ink Free", 25));
                description.setLayoutX(300);
                description.setLayoutY(410);
                pane.getChildren().add(description);

                TextField descriptionField = new TextField();
                descriptionField.setLayoutX(300);
                descriptionField.setLayoutY(460);
                pane.getChildren().add(descriptionField);

                Button button = new Button("Add");
                button.setCursor(Cursor.HAND);
                button.setLayoutX(300);
                button.setLayoutY(530);
                button.setOnMouseClicked(e -> {
                    try {
                        dataOutputStream.writeUTF("addProduct," + (idField.getText().isEmpty() ? " " : idField.getText()) + "," + (nameField.getText().isEmpty() ? " " : nameField.getText()) + "," +
                                (priceField.getText().isEmpty() ? " " : priceField.getText()) + "," + (categoryField.getText().isEmpty() ? " " : categoryField.getText()) + ","
                                + (descriptionField.getText().isEmpty() ? " " : descriptionField.getText()) + "," + token);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    String[] splitInput = new String[7];
                    try {
                        splitInput = dataInputStream.readUTF().split("-");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    Label label;
                    switch (splitInput[0]) {
                        case "1":
                            label = new Label("Complete");
                            label.setTextFill(Color.RED);
                            label.setLayoutX(300);
                            label.setLayoutY(80);
                            pane.getChildren().add(label);
                            break;
                        case "2":
                            label = new Label("At least 6 digit");
                            label.setTextFill(Color.RED);
                            label.setLayoutX(300);
                            label.setLayoutY(80);
                            pane.getChildren().add(label);
                            break;
                        case "3":
                            label = new Label("Already exist");
                            label.setTextFill(Color.RED);
                            label.setLayoutX(300);
                            label.setLayoutY(80);
                            pane.getChildren().add(label);
                            break;
                    }

                    if (splitInput[1].equals("1")) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(180);
                        pane.getChildren().add(label);
                    }

                    if (splitInput[2].equals("1")) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(280);
                        pane.getChildren().add(label);
                    }

                    if (splitInput[3].equals("1")) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(380);
                        pane.getChildren().add(label);
                    } else if (splitInput[3].equals("2")) {
                        label = new Label("Not exist");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(380);
                        pane.getChildren().add(label);
                    }

                    if (splitInput[4].equals("1")) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(480);
                        pane.getChildren().add(label);
                    }
                    if (splitInput[5].equals("pass")) {
                        label = new Label("Done");
                        label.setLayoutX(400);
                        label.setLayoutY(530);
                        label.setTextFill(Color.GREEN);
                        pane.getChildren().add(label);
                    }
                });
                pane.getChildren().add(button);

                Scene scene = new Scene(pane, 800, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }

        static class EditProduct {
            public static void show() throws IOException, ClassNotFoundException {
                Pane parent = new Pane();
                parent.setStyle("-fx-background-color: #858585");
                Label label = new Label("Edit product");
                label.setLayoutX(10);
                label.setLayoutY(100);
                label.setFont(new Font(30));
                parent.getChildren().add(label);
                showFields(parent);
                makeTopOfMenu(parent);

                Button back = new Button("Back");
                back.setStyle("-fx-background-color: #bababa");
                back.setCursor(Cursor.HAND);
                back.setLayoutX(400);
                back.setLayoutY(110);
                back.setOnMouseClicked(e -> {
                    try {
                        SellerRequests.show();
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
                parent.getChildren().add(back);

                Scene scene = new Scene(parent, 1280, 660);
                Menu.stage.setScene(scene);
                Menu.stage.show();
            }

            private static void makeTopOfMenu(Pane parent) {
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
                Image person = new Image(Paths.get("src/main/java/Client/view/images/unknownPerson.jpg").toUri().toString());
                ImageView personImage = new ImageView(person);
                personImage.setFitWidth(70);
                personImage.setFitHeight(70);
                personImage.setLayoutX(320);
                personImage.setLayoutY(10);
                topMenu.getChildren().add(personImage);

                Label role = new Label("Seller");
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

                Label id = new Label("Product id");
                id.setFont(new Font(20));
                id.setLayoutX(10);
                id.setLayoutY(5);
                pane.getChildren().add(id);

                Label name = new Label("Product name");
                name.setFont(new Font(20));
                name.setLayoutX(300);
                name.setLayoutY(5);
                pane.getChildren().add(name);

                Label edit = new Label("Edit");
                edit.setFont(new Font(20));
                edit.setLayoutX(500);
                edit.setLayoutY(5);
                pane.getChildren().add(edit);

//                Label photo = new Label("Photo");
//                photo.setFont(new Font(20));
//                photo.setLayoutX(700);
//                photo.setLayoutY(5);
//                pane.getChildren().add(photo);

                updateList(pane);
            }

            private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
                int i = 1;
                dataOutputStream.writeUTF("getProductsForSeller," + token);
                dataOutputStream.flush();
                int size = Integer.parseInt(dataInputStream.readUTF());
                ArrayList<Product> products = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    String[] input = dataInputStream.readUTF().split("-");
                    Product product = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
                    products.add(product);
                }

                for (Product allProduct : products) {
                    Label id = new Label(allProduct.getProductID());
                    id.setFont(new Font(20));
                    id.setLayoutX(10);
                    id.setLayoutY(50 * i);
                    pane.getChildren().add(id);

                    Label name = new Label(allProduct.getName());
                    name.setFont(new Font(20));
                    name.setLayoutX(300);
                    name.setLayoutY(50 * i);
                    pane.getChildren().add(name);

                    Button edit = new Button("Edit");
                    edit.setStyle("-fx-background-color: #858585");
                    edit.setLayoutX(500);
                    edit.setLayoutY(50 * i);
                    edit.setCursor(Cursor.HAND);
                    edit.setOnMouseClicked(e -> {
                        EditProductInfo.editInfo(allProduct);
                    });
                    pane.getChildren().add(edit);

//                        ChoiceBox choiceBox = new ChoiceBox();
//                        choiceBox.getItems().add("Digital");
//                        choiceBox.getItems().add("Art");
//                        choiceBox.getItems().add("Book");
//                        choiceBox.getItems().add("Food");
//                        choiceBox.setLayoutX(700);
//                        choiceBox.setLayoutY(50 * i);
//                        choiceBox.setOnAction(e -> {
//                            System.out.println(choiceBox.getSelectionModel().getSelectedIndex());
//                            if (choiceBox.getSelectionModel().getSelectedIndex() == 0) {
//                                try {
//                                    dataOutputStream.writeUTF("productSetImageView,digital," + allProduct.getProductID() + "," + token);
//                                    dataOutputStream.flush();
//                                } catch (IOException ex) {
//                                    ex.printStackTrace();
//                                }
//                                try {
//                                    if (dataInputStream.readUTF().equals("done")) {
//                                        show();
//                                    }
//                                } catch (IOException | ClassNotFoundException ex) {
//                                    ex.printStackTrace();
//                                }
//                            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 1) {
//                                try {
//                                    dataOutputStream.writeUTF("productSetImageView,art," + allProduct.getProductID() + "," + token);
//                                    dataOutputStream.flush();
//                                } catch (IOException ex) {
//                                    ex.printStackTrace();
//                                }
//                                try {
//                                    if (dataInputStream.readUTF().equals("done")) {
//                                        show();
//                                    }
//                                } catch (IOException | ClassNotFoundException ex) {
//                                    ex.printStackTrace();
//                                }
//                            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 2) {
//                                try {
//                                    dataOutputStream.writeUTF("productSetImageView,book," + allProduct.getProductID() + "," + token);
//                                    dataOutputStream.flush();
//                                } catch (IOException ex) {
//                                    ex.printStackTrace();
//                                }
//
//                                try {
//                                    if (dataInputStream.readUTF().equals("done")) {
//                                        show();
//                                    }
//                                } catch (IOException | ClassNotFoundException ex) {
//                                    ex.printStackTrace();
//                                }
//                            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 3) {
//                                try {
//                                    dataOutputStream.writeUTF("productSetImageView,food," + allProduct.getProductID() + "," + token);
//                                    dataOutputStream.flush();
//                                } catch (IOException ex) {
//                                    ex.printStackTrace();
//                                }
//                                try {
//                                    if (dataInputStream.readUTF().equals("done")) {
//                                        show();
//                                    }
//                                } catch (IOException | ClassNotFoundException ex) {
//                                    ex.printStackTrace();
//                                }
//                            }
//                        });
//                        pane.getChildren().add(choiceBox);

                    i++;


                }
            }

            static class EditProductInfo {
                public static void editInfo(Product product) {
                    Pane parent = new Pane();
                    parent.setStyle("-fx-background-color: #858585");
                    Label label = new Label("Edit Product");
                    label.setFont(new Font(30));
                    label.setLayoutX(400);
                    label.setLayoutY(100);
                    parent.getChildren().add(label);
                    makeTopMenu(parent);
                    showFields(parent, product);
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
                    Image person = new Image(Paths.get("src/main/java/Client/view/images/unknownPerson.jpg").toUri().toString());
                    ImageView personImage = new ImageView(person);
                    personImage.setFitWidth(70);
                    personImage.setFitHeight(70);
                    personImage.setLayoutX(320);
                    personImage.setLayoutY(10);
                    topMenu.getChildren().add(personImage);

                    Label role = new Label("Seller");
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

                private static void showFields(Pane parent, Product product) {
                    Pane productInfo = new Pane();
                    productInfo.setStyle("-fx-background-color: #bababa");
                    productInfo.setPrefWidth(400);
                    productInfo.setPrefHeight(400);
                    productInfo.setLayoutX(400);
                    productInfo.setLayoutY(150);
                    parent.getChildren().add(productInfo);
                    id(productInfo, product);
                    name(productInfo, product);
                    money(productInfo, product);
                    category(productInfo, product);
                    description(productInfo, product);
                    increase(productInfo, product);

                    Button button = new Button("Save and back");
                    button.setPrefWidth(100);
                    button.setLayoutX(150);
                    button.setLayoutY(350);
                    button.setCursor(Cursor.HAND);
                    productInfo.getChildren().add(button);
                    button.setOnMouseClicked(e -> {
                        try {
                            new SellerMenu().showPersonalArea();
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    });
                }

                private static void id(Pane productInfo, Product product) {
                    Label name = new Label("Id:" + "\n" + product.getProductID());
                    name.setFont(new Font(15));
                    name.setLayoutX(20);
                    productInfo.getChildren().add(name);

                    Line line = new Line();
                    line.setStartX(0);
                    line.setEndX(400);
                    line.setStartY(50);
                    line.setEndY(50);
                    productInfo.getChildren().add(line);

                    Button button = new Button("Edit");
                    button.setLayoutX(350);
                    button.setLayoutY(10);
                    button.setCursor(Cursor.HAND);
                    productInfo.getChildren().add(button);

                    TextField textField = new TextField();
                    textField.setPromptText("New id");
                    textField.setLayoutX(200);
                    textField.setLayoutY(10);
                    productInfo.getChildren().add(textField);
                    button.setOnMouseClicked(e -> {
                        Label label = new Label();
                        label.setFont(new Font(10));
                        label.setLayoutX(200);
                        label.setLayoutY(35);
                        productInfo.getChildren().add(label);
                        if (textField.getText().isEmpty()) {
                            label.setText("Complete for edit");
                            label.setTextFill(Color.RED);
                        } else if (textField.getText().length() != 6) {
                            label.setText("At least 6 number");
                            label.setTextFill(Color.RED);
                        } else {
                            try {
                                dataOutputStream.writeUTF("sendEditProductRequest," + "id," + textField.getText() +
                                        "," + product.getProductID() + "," + token);
                                dataOutputStream.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                if (dataInputStream.readUTF().equals("done")) {
                                    label.setText("Sent");
                                    label.setTextFill(Color.GREEN);
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }

                private static void name(Pane productInfo, Product product) {
                    Label name = new Label("Name:" + "\n" + product.getName());
                    name.setFont(new Font(15));
                    name.setLayoutX(20);
                    name.setLayoutY(50);
                    productInfo.getChildren().add(name);

                    Line line1 = new Line();
                    line1.setStartX(0);
                    line1.setEndX(400);
                    line1.setStartY(100);
                    line1.setEndY(100);
                    productInfo.getChildren().add(line1);

                    Button button = new Button("Edit");
                    button.setLayoutX(350);
                    button.setLayoutY(60);
                    button.setCursor(Cursor.HAND);
                    productInfo.getChildren().add(button);

                    TextField textField = new TextField();
                    textField.setPromptText("New name");
                    textField.setLayoutX(200);
                    textField.setLayoutY(60);
                    productInfo.getChildren().add(textField);
                    button.setOnMouseClicked(e -> {
                        Label label = new Label();
                        label.setFont(new Font(10));
                        label.setLayoutX(200);
                        label.setLayoutY(85);
                        productInfo.getChildren().add(label);
                        if (textField.getText().isEmpty()) {
                            label.setText("Complete for edit");
                            label.setTextFill(Color.RED);
                        } else {
                            try {
                                dataOutputStream.writeUTF("sendEditProductRequest," + "name," + textField.getText() + "," +
                                        product.getProductID() + "," + token);
                                dataOutputStream.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                if (dataInputStream.readUTF().equals("done")) {
                                    label.setText("Sent");
                                    label.setTextFill(Color.GREEN);
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }

                private static void money(Pane productInfo, Product product) {
                    Label money = new Label("Price:" + "\n" + product.getMoney());
                    money.setFont(new Font(15));
                    money.setLayoutX(20);
                    money.setLayoutY(100);
                    productInfo.getChildren().add(money);

                    Line line2 = new Line();
                    line2.setStartX(0);
                    line2.setEndX(400);
                    line2.setStartY(150);
                    line2.setEndY(150);
                    productInfo.getChildren().add(line2);

                    Button button = new Button("Edit");
                    button.setLayoutX(350);
                    button.setLayoutY(110);
                    button.setCursor(Cursor.HAND);
                    productInfo.getChildren().add(button);

                    TextField textField = new TextField();
                    textField.setPromptText("New price");
                    textField.setLayoutX(200);
                    textField.setLayoutY(110);
                    productInfo.getChildren().add(textField);
                    button.setOnMouseClicked(e -> {
                        Label label = new Label();
                        label.setFont(new Font(10));
                        label.setLayoutX(200);
                        label.setLayoutY(135);
                        productInfo.getChildren().add(label);
                        if (textField.getText().isEmpty()) {
                            label.setText("Complete for edit");
                            label.setTextFill(Color.RED);
                        } else {
                            if (!textField.getText().matches("\\d+")) {
                                label.setText(":||||||");
                                label.setTextFill(Color.RED);
                            } else {
                                try {
                                    dataOutputStream.writeUTF("sendEditProductRequest," + "money," + textField.getText() + "," + product.getProductID() + "," + token);
                                    dataOutputStream.flush();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                try {
                                    if (dataInputStream.readUTF().equals("done")) {
                                        label.setText("Sent");
                                        label.setTextFill(Color.GREEN);
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                }

                private static void category(Pane productInfo, Product product) {
                    Label category = new Label("Category:" + "\n" + product.getCategory());
                    category.setFont(new Font(15));
                    category.setLayoutX(20);
                    category.setLayoutY(150);
                    productInfo.getChildren().add(category);

                    Line line3 = new Line();
                    line3.setStartX(0);
                    line3.setEndX(400);
                    line3.setStartY(200);
                    line3.setEndY(200);
                    productInfo.getChildren().add(line3);

                    Button button = new Button("Edit");
                    button.setLayoutX(350);
                    button.setLayoutY(160);
                    button.setCursor(Cursor.HAND);
                    productInfo.getChildren().add(button);

                    TextField textField = new TextField();
                    textField.setPromptText("New category");
                    textField.setLayoutX(200);
                    textField.setLayoutY(160);
                    productInfo.getChildren().add(textField);
                    button.setOnMouseClicked(e -> {
                        Label label = new Label();
                        label.setFont(new Font(10));
                        label.setLayoutX(200);
                        label.setLayoutY(185);
                        productInfo.getChildren().add(label);
                        if (textField.getText().isEmpty()) {
                            label.setText("Complete for edit");
                            label.setTextFill(Color.RED);
                        } else {
                            try {
                                dataOutputStream.writeUTF("sendEditProductRequest," + "category," + textField.getText() +
                                        "," + product.getProductID() + "," + token);
                                dataOutputStream.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            try {
                                if (dataInputStream.readUTF().equals("fail")) {
                                    label.setText("Doesnt exist");
                                    label.setTextFill(Color.RED);
                                } else {
                                    label.setText("Sent");
                                    label.setTextFill(Color.GREEN);
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }

                private static void description(Pane personalInfo, Product product) {
                    Label description = new Label("Description:" + "\n" + product.getDescription());
                    description.setFont(new Font(15));
                    description.setLayoutX(20);
                    description.setLayoutY(200);
                    personalInfo.getChildren().add(description);

                    Line line3 = new Line();
                    line3.setStartX(0);
                    line3.setEndX(400);
                    line3.setStartY(250);
                    line3.setEndY(250);
                    personalInfo.getChildren().add(line3);

                    Button button = new Button("Edit");
                    button.setLayoutX(350);
                    button.setLayoutY(210);
                    button.setCursor(Cursor.HAND);
                    personalInfo.getChildren().add(button);

                    TextField textField = new TextField();
                    textField.setPromptText("New description");
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
                            try {
                                dataOutputStream.writeUTF("sendEditProductRequest," + "description," + textField.getText() + "," + product.getProductID() + "," + token);
                                dataOutputStream.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                if (dataInputStream.readUTF().equals("done")) {
                                    label.setText("Sent");
                                    label.setTextFill(Color.GREEN);
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }

                private static void increase(Pane personalInfo, Product product) {
                    Label increase = new Label("Number:" + "\n" + product.getNumberOfProducts());
                    increase.setFont(new Font(15));
                    increase.setLayoutX(20);
                    increase.setLayoutY(250);
                    personalInfo.getChildren().add(increase);

                    Line line3 = new Line();
                    line3.setStartX(0);
                    line3.setEndX(400);
                    line3.setStartY(300);
                    line3.setEndY(300);
                    personalInfo.getChildren().add(line3);

                    Button button = new Button("Increase");
                    button.setLayoutX(310);
                    button.setLayoutY(260);
                    button.setCursor(Cursor.HAND);
                    personalInfo.getChildren().add(button);

                    button.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.writeUTF("increaseProduct," + product.getProductID() + "," + token);
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        Label label = new Label();
                        label.setFont(new Font(10));
                        label.setLayoutX(150);
                        label.setLayoutY(285);
                        personalInfo.getChildren().add(label);
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                increase.setText("Number:" + "\n" + product.getNumberOfProducts());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            }
        }

        static class RemoveProduct {
            public static void show() throws IOException, ClassNotFoundException {
                Pane parent = new Pane();
                parent.setStyle("-fx-background-color: #858585");
                Label label = new Label("Remove product");
                label.setLayoutX(10);
                label.setLayoutY(100);
                label.setFont(new Font(30));
                parent.getChildren().add(label);
                showFields(parent);
                makeTopOfMenu(parent);

                Button back = new Button("Back");
                back.setStyle("-fx-background-color: #bababa");
                back.setCursor(Cursor.HAND);
                back.setLayoutX(400);
                back.setLayoutY(110);
                back.setOnMouseClicked(e -> {
                    try {
                        SellerRequests.show();
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
                parent.getChildren().add(back);

                Button update = new Button("Update list");
                update.setStyle("-fx-background-color: #bababa");
                update.setCursor(Cursor.HAND);
                update.setLayoutX(600);
                update.setLayoutY(110);
                update.setOnMouseClicked(e -> {
                    try {
                        showFields(parent);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
                parent.getChildren().add(update);

                Scene scene = new Scene(parent, 1280, 660);
                Menu.stage.setScene(scene);
                Menu.stage.show();
            }

            private static void makeTopOfMenu(Pane parent) {
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
                Image person = new Image(Paths.get("src/main/java/Client/view/images/unknownPerson.jpg").toUri().toString());
                ImageView personImage = new ImageView(person);
                personImage.setFitWidth(70);
                personImage.setFitHeight(70);
                personImage.setLayoutX(320);
                personImage.setLayoutY(10);
                topMenu.getChildren().add(personImage);

                Label role = new Label("Seller");
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

                Label delete = new Label("Delete");
                delete.setFont(new Font(20));
                delete.setLayoutX(1200);
                delete.setLayoutY(5);
                pane.getChildren().add(delete);

                updateList(pane);
            }

            private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
                dataOutputStream.writeUTF("getProductsForSeller," + token);
                dataOutputStream.flush();
                int size = Integer.parseInt(dataInputStream.readUTF());
                ArrayList<Product> products = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    String[] input = dataInputStream.readUTF().split("-");
                    Product product = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
                    products.add(product);
                }

                int i = 1;
                for (Product allProduct : products) {
                    Label id = new Label(allProduct.getProductID());
                    id.setFont(new Font(20));
                    id.setLayoutX(10);
                    id.setLayoutY(50 * i);
                    pane.getChildren().add(id);

                    Label name = new Label(allProduct.getName());
                    name.setFont(new Font(20));
                    name.setLayoutX(200);
                    name.setLayoutY(50 * i);
                    pane.getChildren().add(name);

                    Label money = new Label(String.valueOf(allProduct.getMoney()));
                    money.setFont(new Font(20));
                    money.setLayoutX(400);
                    money.setLayoutY(50 * i);
                    pane.getChildren().add(money);

                    Label category = new Label(allProduct.getCategory());
                    category.setFont(new Font(20));
                    category.setLayoutX(900);
                    category.setLayoutY(50 * i);
                    pane.getChildren().add(category);

                    Label description = new Label(String.valueOf(allProduct.getDescription()));
                    description.setFont(new Font(20));
                    description.setLayoutX(650);
                    description.setLayoutY(50 * i);
                    pane.getChildren().add(description);

                    Button edit = new Button("Delete");
                    edit.setStyle("-fx-background-color: #858585");
                    edit.setLayoutX(1200);
                    edit.setLayoutY(50 * i);
                    edit.setCursor(Cursor.HAND);
                    int finalI = i;
                    edit.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.writeUTF("sendDeleteProductRequest," + allProduct.getProductID() + "," + token);
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                Label label = new Label("Sent");
                                label.setTextFill(Color.GREEN);
                                label.setLayoutX(1200);

                                label.setLayoutY(75 * finalI);
                                pane.getChildren().add(label);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    pane.getChildren().add(edit);

                    i++;
                }
            }
        }

        //ToDo
        static class AddAuction {
            //ToDo
            public static void show() throws IOException {
                Pane parent = new Pane();
                parent.setStyle("-fx-background-color: #858585");
                Label label = new Label("Add auction");
                label.setLayoutX(10);
                label.setLayoutY(100);
                label.setFont(new Font(30));
                parent.getChildren().add(label);

                makeTopOfMenu(parent);
                showFields(parent);

                Button back = new Button("Back");
                back.setStyle("-fx-background-color: #bababa");
                back.setCursor(Cursor.HAND);
                back.setLayoutX(400);
                back.setLayoutY(110);
                back.setOnMouseClicked(e -> {
                    try {
                        SellerRequests.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
                parent.getChildren().add(back);

                Button add = new Button("Add auction");
                add.setStyle("-fx-background-color: #bababa");
                add.setCursor(Cursor.HAND);
                add.setLayoutX(600);
                add.setLayoutY(110);
                add.setOnMouseClicked(e -> {
                    addAuction();
                });
                parent.getChildren().add(add);

                Scene scene = new Scene(parent, 1280, 660);
                Menu.stage.setScene(scene);
                Menu.stage.show();
            }

            //ToDo
            private static void makeTopOfMenu(Pane parent) {
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

//                ImageView person = ((Seller) LoginMenu.currentPerson).getImageView();
//                person.setFitWidth(100);
//                person.setFitHeight(80);
//                person.setLayoutX(1170);
//                person.setLayoutY(10);
//                person.setCursor(Cursor.HAND);
//                person.setOnMouseClicked(e -> {
//                    LoginMenu.currentPerson = null;
//                    try {
//                        Menu.executeMainMenu();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                });
//                topMenu.getChildren().add(person);

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

                Label role = new Label("Seller");
                role.setFont(new Font(30));
                role.setLayoutX(640);
                role.setLayoutY(30);
                role.setTextFill(Color.WHITE);
                topMenu.getChildren().add(role);


                parent.getChildren().add(topMenu);
            }

            //ToDo
            private static void addAuction() {
                Pane pane = new Pane();

                Label id = new Label("Id (6 digits)");
                id.setFont(new Font("Ink Free", 25));
                id.setLayoutX(300);
                id.setLayoutY(10);
                pane.getChildren().add(id);

                TextField idField = new TextField();
                idField.setLayoutX(300);
                idField.setLayoutY(60);
                pane.getChildren().add(idField);

                Label start = new Label("Start (hh:mm)");
                start.setFont(new Font("Ink Free", 25));
                start.setLayoutX(300);
                start.setLayoutY(110);
                pane.getChildren().add(start);

                TextField startField = new TextField();
                startField.setLayoutX(300);
                startField.setLayoutY(160);
                pane.getChildren().add(startField);

                Label end = new Label("End (hh:mm)");
                end.setFont(new Font("Ink Free", 25));
                end.setLayoutX(300);
                end.setLayoutY(210);
                pane.getChildren().add(end);

                TextField endField = new TextField();
                endField.setLayoutX(300);
                endField.setLayoutY(260);
                pane.getChildren().add(endField);

                Label Percent = new Label("Percent");
                Percent.setFont(new Font("Ink Free", 25));
                Percent.setLayoutX(300);
                Percent.setLayoutY(310);
                pane.getChildren().add(Percent);

                TextField percentField = new TextField();
                percentField.setLayoutX(300);
                percentField.setLayoutY(360);
                pane.getChildren().add(percentField);

                Button button = new Button("Continue");
                button.setCursor(Cursor.HAND);
                button.setLayoutX(300);
                button.setLayoutY(430);
                button.setOnMouseClicked(e -> {
                    boolean create = true;
                    Label label;
                    if (idField.getText().isEmpty()) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(80);
                        pane.getChildren().add(label);
                        create = false;
                    } else if (idField.getText().length() != 6) {
                        label = new Label("At least 6 digit");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(80);
                        pane.getChildren().add(label);
                        create = false;

                    }
                    try {
                        dataOutputStream.writeUTF("isAuctionExist id-" + idField.getText());
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        if (dataInputStream.readUTF().equals("yes")) {
                            label = new Label("Already exist");
                            label.setTextFill(Color.RED);
                            label.setLayoutX(300);
                            label.setLayoutY(80);
                            pane.getChildren().add(label);
                            create = false;
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (startField.getText().isEmpty()) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(180);
                        pane.getChildren().add(label);
                        create = false;

                    }
                    if (endField.getText().isEmpty()) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(280);
                        pane.getChildren().add(label);
                        create = false;
                    }
                    if (percentField.getText().isEmpty()) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(380);
                        pane.getChildren().add(label);
                        create = false;
                    }
                    if (create) {
                        ScrollPane scrollPane = new ScrollPane();
                        Pane pane1 = new Pane();
                        pane1.setPrefHeight(660);
                        pane1.setPrefWidth(1280);
                        pane1.setStyle("-fx-background-color: #858585");
                        Pane pane2 = new Pane();
                        pane2.setPrefHeight(600);
                        pane2.setPrefWidth(1270);
                        pane2.setLayoutY(50);
                        pane2.setLayoutX(10);
                        pane2.setStyle("-fx-background-color: #bababa");
                        Button addAuction = new Button("Add");
                        addAuction.setStyle("-fx-background-color: #bababa");
                        addAuction.setLayoutX(10);
                        addAuction.setLayoutY(20);
                        addAuction.setCursor(Cursor.HAND);

                        ArrayList<Product> products = new ArrayList<>();
                        try {
                            dataOutputStream.writeUTF("getProductsForSeller," + token);
                            dataOutputStream.flush();
                            int size = Integer.parseInt(dataInputStream.readUTF());

                            for (int j = 0; j < size; j++) {
                                String[] input = dataInputStream.readUTF().split("-");
                                Product product = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
                                products.add(product);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }


                        ArrayList<Product> offProducts = new ArrayList<>();
                        int i = 1;
                        for (Product product : products) {
                            try {
                                dataOutputStream.writeUTF("isProductInAuction id-" + product.getProductID());
                                dataOutputStream.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                if (dataInputStream.readUTF().equals("no")) {
                                    Label productId = new Label(product.getProductID());
                                    productId.setFont(new Font(20));
                                    productId.setLayoutX(10);
                                    productId.setLayoutY(50 * i);
                                    pane2.getChildren().add(productId);

                                    Label productName = new Label(product.getName());
                                    productName.setFont(new Font(20));
                                    productName.setLayoutX(100);
                                    productName.setLayoutY(50 * i);
                                    pane2.getChildren().add(productName);

                                    Button add = new Button("Add to auction");
                                    add.setLayoutX(300);
                                    add.setLayoutY(50 * i);
                                    add.setCursor(Cursor.HAND);
                                    add.setStyle("-fx-background-color: #858585");
                                    add.setOnMouseClicked(e1 -> {
                                        offProducts.add(product);
                                    });
                                    pane2.getChildren().add(add);
                                    i++;
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        addAuction.setOnMouseClicked(e2 -> {
                            if (!offProducts.isEmpty()) {
                                try {
                                    dataOutputStream.writeUTF("sendAddAuctionRequest-" + idField.getText() + "-" + startField.getText() + "-" + endField.getText() + "-" + percentField.getText());
                                    dataOutputStream.flush();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                try {
                                    dataInputStream.readUTF();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                try {
                                    dataOutputStream.writeUTF(String.valueOf(offProducts.size()));
                                    for (Product offProduct : offProducts) {
                                        dataOutputStream.writeUTF(offProduct.getProductID());
                                        dataOutputStream.flush();
                                    }
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                try {
                                    new SellerMenu().show();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                } catch (ClassNotFoundException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                Label label1 = new Label("You have to select product");
                                label1.setTextFill(Color.RED);
                                label1.setLayoutX(100);
                                label1.setLayoutY(30);
                                pane1.getChildren().add(label1);

                            }
                        });
                        pane1.getChildren().add(addAuction);
                        scrollPane.setContent(pane1);
                        pane1.getChildren().add(pane2);
                        Scene scene = new Scene(scrollPane, 1280, 660);
                        Stage stage1 = new Stage();
                        stage1.setScene(scene);
                        stage1.show();
                    }
                });
                pane.getChildren().add(button);

                Scene scene = new Scene(pane, 800, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }

            //ToDo
            private static void showFields(Pane parent) throws IOException {
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: #bababa");
                pane.setPrefWidth(1270);
                pane.setPrefHeight(600);
                pane.setLayoutX(5);
                pane.setLayoutY(150);
                parent.getChildren().add(pane);

                Label id = new Label("ID");
                id.setFont(new Font(25));
                id.setLayoutX(10);
                id.setLayoutY(5);
                pane.getChildren().add(id);

                Label start = new Label("Start");
                start.setFont(new Font(25));
                start.setLayoutX(400);
                start.setLayoutY(5);
                pane.getChildren().add(start);

                Label end = new Label("End");
                end.setFont(new Font(25));
                end.setLayoutX(800);
                end.setLayoutY(5);
                pane.getChildren().add(end);

                updateList(pane);


            }

            //ToDo
            private static void updateList(Pane pane) throws IOException {
                dataOutputStream.writeUTF("auctionsOfSeller");
                dataOutputStream.flush();
                int size = Integer.parseInt(dataInputStream.readUTF());
                ArrayList<Auction> auctions = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    String[] input = dataInputStream.readUTF().split("-");
                    Auction auction = new Auction(input[0], input[1], input[2], input[3], Integer.parseInt(input[4]));
                    auctions.add(auction);
                }
                int i = 1;
                for (Auction allSellerAuction : auctions) {
                    Label id = new Label(allSellerAuction.getId());
                    id.setFont(new Font(20));
                    id.setLayoutX(10);
                    id.setLayoutY(50 * i);
                    pane.getChildren().add(id);

                    Label start = new Label(allSellerAuction.getStart().toString());
                    start.setFont(new Font(20));
                    start.setLayoutX(400);
                    start.setLayoutY(50 * i);
                    pane.getChildren().add(start);

                    Label end = new Label(allSellerAuction.getEnd().toString());
                    end.setFont(new Font(20));
                    end.setLayoutX(800);
                    end.setLayoutY(50 * i);
                    pane.getChildren().add(end);

                    i++;
                }
            }
        }

        static class EditAuction {
            public static void show() {
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                Pane parent = new Pane();
                parent.setStyle("-fx-background-color: #858585");
                Label label = new Label("Edit product");
                label.setLayoutX(120);
                label.setLayoutY(100);
                label.setFont(new Font(30));
                parent.getChildren().add(label);
                showFields(parent);
                makeTopOfMenu(parent);

                Button back = new Button("Back");
                back.setStyle("-fx-background-color: #bababa");
                back.setCursor(Cursor.HAND);
                back.setLayoutX(400);
                back.setLayoutY(110);
                back.setOnMouseClicked(e -> {
                    try {
                        SellerRequests.show();
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
                parent.getChildren().add(back);

                scrollPane.setContent(parent);

                Scene scene = new Scene(scrollPane, 1280, 660);
                Menu.stage.setScene(scene);
                Menu.stage.show();
            }

            private static void makeTopOfMenu(Pane parent) {
                Pane topMenu = new Pane();
                topMenu.setStyle("-fx-background-color: #232f3e");
                topMenu.setPrefWidth(1280);
                topMenu.setPrefHeight(100);
                topMenu.setLayoutX(0);
                topMenu.setLayoutY(0);

                Image image = new Image(Paths.get("src/main/java/view/images/mainMenu.png").toUri().toString());
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

                Label role = new Label("Seller");
                role.setFont(new Font(30));
                role.setLayoutX(640);
                role.setLayoutY(30);
                role.setTextFill(Color.WHITE);
                topMenu.getChildren().add(role);


                parent.getChildren().add(topMenu);
            }

            private static void showFields(Pane parent) {
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: #bababa");
                pane.setPrefWidth(1270);
                pane.setPrefHeight(600);
                pane.setLayoutX(5);
                pane.setLayoutY(150);
                parent.getChildren().add(pane);

                Label id = new Label("ID");
                id.setFont(new Font(25));
                id.setLayoutX(10);
                id.setLayoutY(5);
                pane.getChildren().add(id);

                Label start = new Label("Start");
                start.setFont(new Font(25));
                start.setLayoutX(400);
                start.setLayoutY(5);
                pane.getChildren().add(start);

                Label end = new Label("End");
                end.setFont(new Font(25));
                end.setLayoutX(800);
                end.setLayoutY(5);
                pane.getChildren().add(end);

                Label edit = new Label("Edit");
                edit.setFont(new Font(25));
                edit.setLayoutX(1000);
                edit.setLayoutY(5);
                pane.getChildren().add(edit);

                try {
                    updateList(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            private static void updateList(Pane pane) throws IOException {
                dataOutputStream.writeUTF("auctionsOfSeller");
                dataOutputStream.flush();
                int size = Integer.parseInt(dataInputStream.readUTF());
                ArrayList<Auction> auctions = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    String[] input = dataInputStream.readUTF().split("-");
                    Auction auction = new Auction(input[0], input[1], input[2], input[3], Integer.parseInt(input[4]));
                    auctions.add(auction);
                }

                int i = 1;
                for (Auction allSellerAuction : auctions) {
                    Label id = new Label(allSellerAuction.getId());
                    id.setFont(new Font(20));
                    id.setLayoutX(10);
                    id.setLayoutY(50 * i);
                    pane.getChildren().add(id);

                    Label start = new Label(allSellerAuction.getStart().toString());
                    start.setFont(new Font(20));
                    start.setLayoutX(400);
                    start.setLayoutY(50 * i);
                    pane.getChildren().add(start);

                    Label end = new Label(allSellerAuction.getEnd().toString());
                    end.setFont(new Font(20));
                    end.setLayoutX(800);
                    end.setLayoutY(50 * i);
                    pane.getChildren().add(end);

                    Button edit = new Button("Edit");
                    edit.setLayoutX(1000);
                    edit.setLayoutY(50 * i);
                    edit.setOnMouseClicked(e -> {
                        EditAuctionInfo.editAuctionInfo(allSellerAuction);
                    });
                    pane.getChildren().add(edit);

                    i++;
                }
            }

            static class EditAuctionInfo {
                public static void editAuctionInfo(Auction auction) {
                    Pane parent = new Pane();
                    parent.setStyle("-fx-background-color: #858585");
                    Label label = new Label("Edit Auctions");
                    label.setFont(new Font(30));
                    label.setLayoutX(400);
                    label.setLayoutY(100);
                    parent.getChildren().add(label);
                    makeTopMenu(parent);
                    showFields(parent, auction);
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

//                    ImageView imageView = ((Seller) LoginMenu.currentPerson).getImageView();
//                    imageView.setFitWidth(70);
//                    imageView.setFitHeight(70);
//                    imageView.setLayoutY(10);
//                    imageView.setCursor(Cursor.HAND);
//                    imageView.setOnMouseClicked(e -> {
//                        try {
//                            Menu.executeMainMenu();
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                        }
//                    });
//                    topMenu.getChildren().add(imageView);

                    Image log = new Image(Paths.get("src/main/java/Client/view/images/logOut.png").toUri().toString());
                    ImageView logOut = new ImageView(log);
                    logOut.setFitWidth(100);
                    logOut.setFitHeight(80);
                    logOut.setLayoutX(1170);
                    logOut.setLayoutY(10);
                    logOut.setCursor(Cursor.HAND);
                    logOut.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.writeUTF("logout");
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

                    Label role = new Label("Seller");
                    role.setFont(new Font(30));
                    role.setLayoutX(640);
                    role.setLayoutY(30);
                    role.setTextFill(Color.WHITE);
                    topMenu.getChildren().add(role);

                    parent.getChildren().add(topMenu);
                }

                private static void showFields(Pane parent, Auction auction) {
                    Pane auctionInfo = new Pane();
                    auctionInfo.setStyle("-fx-background-color: #bababa");
                    auctionInfo.setPrefWidth(400);
                    auctionInfo.setPrefHeight(400);
                    auctionInfo.setLayoutX(400);
                    auctionInfo.setLayoutY(150);
                    parent.getChildren().add(auctionInfo);
                    start(auctionInfo, auction);
                    end(auctionInfo, auction);
                    products(auctionInfo, auction);


                    Button button = new Button("Save and back");
                    button.setPrefWidth(100);
                    button.setLayoutX(150);
                    button.setLayoutY(350);
                    button.setCursor(Cursor.HAND);
                    auctionInfo.getChildren().add(button);
                    button.setOnMouseClicked(e -> {
                        try {
                            new SellerMenu().showPersonalArea();
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    });
                }

                private static void start(Pane auctionInfo, Auction auction) {
                    Label start = new Label("Start (hh:mm):" + "\n" + auction.getStart().toString());
                    start.setFont(new Font(15));
                    start.setLayoutX(20);
                    auctionInfo.getChildren().add(start);

                    Line line = new Line();
                    line.setStartX(0);
                    line.setEndX(400);
                    line.setStartY(50);
                    line.setEndY(50);
                    auctionInfo.getChildren().add(line);

                    Button button = new Button("Edit");
                    button.setLayoutX(350);
                    button.setLayoutY(10);
                    button.setCursor(Cursor.HAND);
                    auctionInfo.getChildren().add(button);

                    TextField textField = new TextField();
                    textField.setPromptText("New start");
                    textField.setLayoutX(200);
                    textField.setLayoutY(10);
                    auctionInfo.getChildren().add(textField);
                    button.setOnMouseClicked(e -> {
                        Label label = new Label();
                        label.setFont(new Font(10));
                        label.setLayoutX(200);
                        label.setLayoutY(35);
                        auctionInfo.getChildren().add(label);
                        if (textField.getText().isEmpty()) {
                            label.setText("Complete for edit");
                            label.setTextFill(Color.RED);
                        } else {
                            try {
                                dataOutputStream.writeUTF("editAuction id-" + auction.getId() + "-" + "start-" + textField.getText());
                                dataOutputStream.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            label.setText("Sent");
                            label.setTextFill(Color.GREEN);
                        }
                    });
                }

                private static void end(Pane auctionInfo, Auction auction) {
                    Label end = new Label("End (hh:mm):" + "\n" + auction.getEnd().toString());
                    end.setFont(new Font(15));
                    end.setLayoutX(20);
                    end.setLayoutY(50);
                    auctionInfo.getChildren().add(end);

                    Line line1 = new Line();
                    line1.setStartX(0);
                    line1.setEndX(400);
                    line1.setStartY(100);
                    line1.setEndY(100);
                    auctionInfo.getChildren().add(line1);

                    Button button = new Button("Edit");
                    button.setLayoutX(350);
                    button.setLayoutY(60);
                    button.setCursor(Cursor.HAND);
                    auctionInfo.getChildren().add(button);

                    TextField textField = new TextField();
                    textField.setPromptText("New end");
                    textField.setLayoutX(200);
                    textField.setLayoutY(60);
                    auctionInfo.getChildren().add(textField);
                    button.setOnMouseClicked(e -> {
                        Label label = new Label();
                        label.setFont(new Font(10));
                        label.setLayoutX(200);
                        label.setLayoutY(85);
                        auctionInfo.getChildren().add(label);
                        if (textField.getText().isEmpty()) {
                            label.setText("Complete for edit");
                            label.setTextFill(Color.RED);
                        } else {
                            try {
                                dataOutputStream.writeUTF("editAuction id-" + auction.getId() + "-" + "start-" + textField.getText());
                                dataOutputStream.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            label.setText("Done");
                            label.setTextFill(Color.GREEN);
                        }
                    });
                }

                //ToDo
                private static void products(Pane auctionInfo, Auction auction) {
                    Label end = new Label("Products:");
                    end.setFont(new Font(15));
                    end.setLayoutX(20);
                    end.setLayoutY(100);
                    auctionInfo.getChildren().add(end);

                    Line line1 = new Line();
                    line1.setStartX(0);
                    line1.setEndX(400);
                    line1.setStartY(150);
                    line1.setEndY(150);
                    auctionInfo.getChildren().add(line1);

                    Button button = new Button("Edit");
                    button.setLayoutX(350);
                    button.setLayoutY(100);
                    button.setCursor(Cursor.HAND);
                    auctionInfo.getChildren().add(button);

                    button.setOnMouseClicked(e -> {
                        ScrollPane scrollPane = new ScrollPane();
                        Pane pane = new Pane();
                        pane.setStyle("-fx-background-color: #858585");
                        pane.setPrefWidth(1280);
                        pane.setPrefHeight(660);

                        Button button1 = new Button("Save and back");
                        button1.setStyle("-fx-background-color: #bababa");
                        button1.setLayoutX(10);
                        button1.setLayoutY(10);
                        button1.setOnMouseClicked(e1 -> {
                            try {
                                new SellerMenu().show();
                            } catch (IOException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        });
                        pane.getChildren().add(button1);

                        Pane pane1 = new Pane();
                        pane1.setStyle("-fx-background-color: #bababa");
                        pane1.setPrefWidth(1270);
                        pane1.setPrefHeight(500);
                        pane1.setLayoutX(5);
                        pane1.setLayoutY(50);
                        pane.getChildren().add(pane1);

                        try {
                            dataOutputStream.writeUTF("productsOfAuction id-" + auction.getId());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        ArrayList<Product> products = new ArrayList<>();
                        try {
                            int size = Integer.parseInt(dataInputStream.readUTF());
                            for (int j = 0; j < size; j++) {
                                String[] input = dataInputStream.readUTF().split("-");
                                Product product = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
                                products.add(product);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        int i = 0;
                        for (Product product : products) {
                            Label productId = new Label(product.getProductID());
                            productId.setLayoutX(10);
                            productId.setLayoutY(50 * i);
                            pane1.getChildren().add(productId);

                            Label productName = new Label(product.getName());
                            productName.setLayoutX(100);
                            productName.setLayoutY(50 * i);
                            pane1.getChildren().add(productName);

                            Button remove = new Button("Remove");
                            remove.setStyle("-fx-background-color: #858585");
                            remove.setCursor(Cursor.HAND);
                            remove.setLayoutX(200);
                            remove.setLayoutY(50 * i);
                            pane1.getChildren().add(remove);

                            remove.setOnMouseClicked(e1 -> {
                                try {
                                    dataOutputStream.writeUTF("removeProductOfAuction id-" + auction.getId() + "-" + product.getProductID());
                                    dataOutputStream.flush();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            });
                            i++;
                        }
                        scrollPane.setContent(pane);
                        Scene scene = new Scene(scrollPane, 1280, 660);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();

                    });
                }
            }

        }

        static class AllRequests {
            public static void show() throws IOException, ClassNotFoundException {
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                Pane parent = new Pane();
                parent.setStyle("-fx-background-color: #858585");
                Label label = new Label("All requests");
                label.setLayoutX(10);
                label.setLayoutY(100);
                label.setFont(new Font(30));
                parent.getChildren().add(label);
                showFields(parent);
                makeTopOfMenu(parent);

                Button back = new Button("Back");
                back.setStyle("-fx-background-color: #bababa");
                back.setCursor(Cursor.HAND);
                back.setLayoutX(400);
                back.setLayoutY(110);
                back.setOnMouseClicked(e -> {
                    try {
                        SellerRequests.show();
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
                parent.getChildren().add(back);

                Button update = new Button("Update list");
                update.setStyle("-fx-background-color: #bababa");
                update.setCursor(Cursor.HAND);
                update.setLayoutX(500);
                update.setLayoutY(110);
                update.setOnMouseClicked(e -> {
                    try {
                        showFields(parent);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
                parent.getChildren().add(update);
                scrollPane.setContent(parent);

                Scene scene = new Scene(scrollPane, 1280, 660);
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

//                ImageView personImage = loginSeller.getImageView();
//                personImage.setFitWidth(70);
//                personImage.setFitHeight(70);
//                personImage.setLayoutX(320);
//                personImage.setLayoutY(10);
//                topMenu.getChildren().add(personImage);

                Label role = new Label("Seller");
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

                Label type = new Label("Type");
                type.setFont(new Font(20));
                type.setLayoutX(10);
                type.setLayoutY(5);
                pane.getChildren().add(type);

                Label condition = new Label("Condition");
                condition.setFont(new Font(20));
                condition.setLayoutX(300);
                condition.setLayoutY(5);
                pane.getChildren().add(condition);

                Label delete = new Label("Delete");
                delete.setFont(new Font(20));
                delete.setLayoutX(500);
                delete.setLayoutY(5);
                pane.getChildren().add(delete);

                updateList(pane);
            }

            private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
                int i = 1;
                dataOutputStream.writeUTF("getAllSellerRequests," + token);
                dataOutputStream.flush();
                int size = Integer.parseInt(dataInputStream.readUTF());
                ArrayList<Request> allSellerRequests = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    String[] message = dataInputStream.readUTF().split("-");
                    Request request = new Request(Integer.parseInt(message[0]), message[1], message[2], message[3]);
                    allSellerRequests.add(request);
                }

                for (Request allSellerRequest : allSellerRequests) {
                    Label type = new Label(allSellerRequest.getType());
                    type.setFont(new Font(20));
                    type.setLayoutX(10);
                    type.setLayoutY(50 * i);
                    pane.getChildren().add(type);

                    Label condition = new Label(allSellerRequest.getCondition());
                    condition.setFont(new Font(20));
                    condition.setLayoutX(300);
                    condition.setLayoutY(50 * i);
                    pane.getChildren().add(condition);

                    if (!allSellerRequest.getCondition().equals("Unknown")) {
                        Button delete = new Button("Delete");
                        delete.setStyle("-fx-background-color: #858585");
                        delete.setLayoutX(500);
                        delete.setLayoutY(50 * i);
                        delete.setCursor(Cursor.HAND);
                        delete.setOnMouseClicked(e -> {
                            try {
                                dataOutputStream.writeUTF("deleteSellerRequest," + token);
                                dataOutputStream.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                Gson gson = new Gson();
                                String json = gson.toJson(allSellerRequest);
                                dataOutputStream.writeUTF(json);
                                dataOutputStream.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                        pane.getChildren().add(delete);
                    }
                    i++;
                }
            }
        }
    }

    //Done
    static class SellerBalance {
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

            //Decrease Wallet
            Label deWallet = new Label("Decrease Wallet");
            deWallet.setFont(new Font(30));
            deWallet.setLayoutX(1050);
            deWallet.setLayoutY(260);
            parent.getChildren().add(deWallet);

            TextField money4 = new TextField();
            money4.setPromptText("Money");
            money4.setLayoutX(1050);
            money4.setLayoutY(320);
            parent.getChildren().add(money4);

            Button button4 = new Button("Decrease");
            button4.setStyle("-fx-background-color: #bababa");
            button4.setLayoutX(1050);
            button4.setLayoutY(360);
            button4.setOnMouseClicked(e -> {
                if (money4.getText().isEmpty()) {
                    Label error = new Label("Fill");
                    error.setFont(new Font(15));
                    error.setTextFill(Color.RED);
                    error.setLayoutX(1050);
                    error.setLayoutY(370);
                    parent.getChildren().add(error);
                } else {
                    try {
                        dataOutputStream.writeUTF("decreaseWallet-" + money4.getText() + "," + token);
                        dataOutputStream.flush();
                        Label error=new Label(dataInputStream.readUTF());
                        error.setFont(new Font(15));
                        error.setTextFill(Color.RED);
                        error.setLayoutX(1050);
                        error.setLayoutY(370);
                        parent.getChildren().add(error);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        dataOutputStream.writeUTF("getWalletMoney," + token);
                        dataOutputStream.flush();
                        label2.setText(dataInputStream.readUTF());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            button4.setCursor(Cursor.HAND);
            parent.getChildren().add(button4);
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

            Label role = new Label("Seller");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);

            parent.getChildren().add(topMenu);
        }
    }

    //Done
    static class Categories {
        public static void showPage() throws IOException, ClassNotFoundException {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Categories");
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
                    new SellerMenu().showPersonalArea();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(backButton);

            scrollPane.setContent(parent);

            Scene scene = new Scene(scrollPane, 1280, 660);
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

            Label role = new Label("Seller");
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

            Label name = new Label("Name");
            name.setFont(new Font(20));
            name.setLayoutX(10);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label details = new Label("Details");
            details.setFont(new Font(20));
            details.setLayoutX(300);
            details.setLayoutY(5);
            pane.getChildren().add(details);

            updateList(pane);
        }

        private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
            int i = 1;
            dataOutputStream.writeUTF("getAllCategories," + token);
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<Category> categories = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String[] input = dataInputStream.readUTF().split("-");
                ArrayList<String> details = new ArrayList<>();
                details.add(input[1]);
                details.add(input[2]);
                details.add(input[3]);
                Category category = new Category(input[0], details);
                categories.add(category);
            }

            for (Category allCategory : categories) {
                Label name = new Label(allCategory.getName());
                name.setFont(new Font(20));
                name.setLayoutX(10);
                name.setLayoutY(50 * i);
                pane.getChildren().add(name);

                Label details = new Label("Click to show");
                details.setFont(new Font(20));
                details.setLayoutX(300);
                details.setLayoutY(50 * i);
                details.setCursor(Cursor.HAND);
                details.setOnMouseClicked(e -> {
                    Pane pane1 = new Pane();
                    Label detail1 = new Label(allCategory.getDetail1());
                    detail1.setFont(new Font(20));
                    detail1.setLayoutX(10);
                    pane1.getChildren().add(detail1);
                    Label detail2 = new Label(allCategory.getDetail2());
                    detail2.setFont(new Font(20));
                    detail2.setLayoutX(10);
                    detail2.setLayoutY(40);
                    pane1.getChildren().add(detail2);
                    Label detail3 = new Label(allCategory.getDetail3());
                    detail3.setFont(new Font(20));
                    detail3.setLayoutX(10);
                    detail3.setLayoutY(80);
                    pane1.getChildren().add(detail3);
                    Scene scene = new Scene(pane1, 300, 300);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                pane.getChildren().add(details);

                i++;
            }
        }
    }
}
