package View.SellerMenu;

import Controller.RegisterAndLogin.PersonController;
import Controller.SellerController.SellerAbilitiesController;
import Model.Category.Category;
import Model.Logs.SellLog;
import Model.Product;
import Model.Requests.Request;
import Model.Users.Buyer;
import Model.Users.Seller;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
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

import java.io.IOException;
import java.nio.file.Paths;

public class SellerMenu extends Menu {

    public void show() {
        showPersonalArea();
    }

    public void showPersonalArea() {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label label = new Label("Your Account");
        label.setFont(new Font(30));
        label.setLayoutX(90);
        label.setLayoutY(120);
        parent.getChildren().add(label);
        createPersonalInfoPanel(parent);
        createSellLogsPanel(parent);
        createSalesListPanel(parent);
        createBalancePanel(parent);
        createRequestPanel(parent);
        createAuctionsPanel(parent);
        createCategoriesPanel(parent);
        makeTopOfMenu(parent);

        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private void createPersonalInfoPanel(Pane parent) {
        Pane personalInfo = new Pane();
        personalInfo.setStyle("-fx-background-color: #bababa");
        personalInfo.setPrefWidth(210);
        personalInfo.setPrefHeight(70);
        personalInfo.setLayoutX(90);
        personalInfo.setLayoutY(200);
        personalInfo.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/personalInfo.png").toUri().toString());
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
            SellerPersonalInfoAbilities.editPersonalInfo();
        });

    }

    private void createSellLogsPanel(Pane parent) {
        Pane sellLogs = new Pane();
        sellLogs.setStyle("-fx-background-color: #bababa");
        sellLogs.setPrefWidth(240);
        sellLogs.setPrefHeight(70);
        sellLogs.setLayoutX(490);
        sellLogs.setLayoutY(200);
        sellLogs.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/basket.png").toUri().toString());
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

        sellLogs.setOnMouseClicked(e -> SellerLogs.show());
        parent.getChildren().add(sellLogs);
    }

    private void createSalesListPanel(Pane parent) {
        Pane salesLists = new Pane();
        salesLists.setStyle("-fx-background-color: #bababa");
        salesLists.setPrefWidth(240);
        salesLists.setPrefHeight(70);
        salesLists.setLayoutX(890);
        salesLists.setLayoutY(200);
        salesLists.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/giftCard.png").toUri().toString());
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
            SellerProducts.show();
        });

        parent.getChildren().add(salesLists);
    }

    private void createBalancePanel(Pane parent) {
        Pane balance = new Pane();
        balance.setStyle("-fx-background-color: #bababa");
        balance.setPrefWidth(210);
        balance.setPrefHeight(70);
        balance.setLayoutX(90);
        balance.setLayoutY(350);
        balance.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/balance.png").toUri().toString());
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

        Label balanceSecondLabel = new Label("View your balance");
        balanceSecondLabel.setFont(new Font(12));
        balanceSecondLabel.setLayoutX(60);
        balanceSecondLabel.setLayoutY(40);
        balance.getChildren().add(balanceSecondLabel);

        balance.setOnMouseClicked(e -> SellerBalance.show());
        parent.getChildren().add(balance);
    }

    public void createRequestPanel(Pane parent) {
        Pane request = new Pane();
        request.setStyle("-fx-background-color: #bababa");
        request.setPrefWidth(240);
        request.setPrefHeight(70);
        request.setLayoutX(490);
        request.setLayoutY(350);
        request.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/request.png").toUri().toString());
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

        request.setOnMouseClicked(e -> SellerRequests.show());
        parent.getChildren().add(request);
    }

    public void createAuctionsPanel(Pane parent) {
        Pane auction = new Pane();
        auction.setStyle("-fx-background-color: #bababa");
        auction.setPrefWidth(240);
        auction.setPrefHeight(70);
        auction.setLayoutX(890);
        auction.setLayoutY(350);
        auction.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/auction.png").toUri().toString());
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

    public void createCategoriesPanel(Pane parent) {
        Pane category = new Pane();
        category.setStyle("-fx-background-color: #bababa");
        category.setPrefWidth(210);
        category.setPrefHeight(70);
        category.setLayoutX(90);
        category.setLayoutY(500);
        category.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/category.png").toUri().toString());
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
            Categories.showPage();
        });
        parent.getChildren().add(category);
    }

    private void makeTopOfMenu(Pane parent) throws IOException, ClassNotFoundException {
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

        Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
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


        dataOutputStream.writeUTF("getPerson");
        dataOutputStream.flush();
        Seller seller = (Seller) objectInputStream.readObject();

        ImageView personImage = seller.getImageView();
        personImage.setFitWidth(70);
        personImage.setFitHeight(70);
        personImage.setLayoutX(320);
        personImage.setLayoutY(10);
        topMenu.getChildren().add(personImage);

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
                    dataOutputStream.writeUTF("unknown");
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                show();
            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 1) {
                try {
                    dataOutputStream.writeUTF("man");
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                show();
            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 2) {
                try {
                    dataOutputStream.writeUTF("woman");
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                show();
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

    static class SellerPersonalInfoAbilities {
        public static void editPersonalInfo() {
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

        private static void makeTopMenu(Pane parent) {
            Pane topMenu = new Pane();
            topMenu.setStyle("-fx-background-color: #232f3e");
            topMenu.setPrefWidth(1280);
            topMenu.setPrefHeight(100);
            topMenu.setLayoutX(0);
            topMenu.setLayoutY(0);

            ImageView imageView = ((Seller) LoginMenu.currentPerson).getImageView();
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

            Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
                ((Seller)LoginMenu.currentPerson).setOnline(false);
                LoginMenu.currentPerson = null;
                try {
                    Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);
            Image person = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
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

            Image cart = new Image(Paths.get("src/main/java/view/images/cart.png").toUri().toString());
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

        private static void showFields(Pane parent) {
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
                new SellerMenu().showPersonalArea();
            });
        }

        private static void name(Pane personalInfo) {
            Label name = new Label("Name:" + "\n" + LoginMenu.currentPerson.getName());
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
                    SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "name", textField.getText());
                    label.setText("Done");
                    label.setTextFill(Color.GREEN);
                    name.setText("Name:" + "\n" + textField.getText());
                }
            });
        }

        private static void family(Pane personalInfo) {
            Label family = new Label("Family:" + "\n" + LoginMenu.currentPerson.getFamily());
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
                    SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "family", textField.getText());
                    label.setText("Done");
                    label.setTextFill(Color.GREEN);
                    family.setText("Family:" + "\n" + textField.getText());
                }
            });
        }

        private static void email(Pane personalInfo) {

            Label email = new Label("Email:" + "\n" + LoginMenu.currentPerson.getEmail());
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
                        SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "email", textField.getText());
                        label.setText("Done");
                        label.setTextFill(Color.GREEN);
                        email.setText("Email:" + "\n" + textField.getText());
                    }
                }
            });
        }

        private static void phone(Pane personalInfo) {
            Label phone = new Label("Phone:" + "\n" + LoginMenu.currentPerson.getPhone());
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
                        SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "phone", textField.getText());
                        label.setText("Done");
                        label.setTextFill(Color.GREEN);
                        phone.setText("Phone:" + "\n" + textField.getText());
                    }
                }
            });
        }

        private static void password(Pane personalInfo) {
            Label password = new Label("Password:" + "\n" + LoginMenu.currentPerson.getPassword());
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
                        SellerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
                        label.setText("Done");
                        label.setTextFill(Color.GREEN);
                        password.setText("Password:" + "\n" + textField.getText());
                    }
                }
            });
        }

        private static void company(Pane personalInfo) {
            Seller seller = (Seller) LoginMenu.currentPerson;
            Label company = new Label("Company:" + "\n" + seller.getCompany());
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
//                    seller.setCompany(textField.getText());
                    SellerAbilitiesController.editPersonalInfo(seller, "company", textField.getText());
                    label.setText("Done");
                    label.setTextFill(Color.GREEN);
                    company.setText("Company:" + "\n" + textField.getText());
                }
            });
        }

    }

    static class SellerLogs {
        public static void show() {
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
            backButton.setOnMouseClicked(e -> new SellerMenu().showPersonalArea());
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

        private static void showFields(Pane parent) {
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

        private static void updateList(Pane pane) {
            int i = 1;
            for (SellLog log : ((Seller) LoginMenu.currentPerson).getLogs()) {

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

                Label finalPrice = new Label(log.getProduct().getProductID());
                finalPrice.setLayoutX(950);
                finalPrice.setLayoutY(50 * i);
                finalPrice.setFont(new Font(20));
                pane.getChildren().add(finalPrice);

                Label delivery = new Label(log.getProductReceived());
                delivery.setLayoutX(1150);
                delivery.setLayoutY(50*i);
                delivery.setFont(new Font(25));
                pane.getChildren().add(delivery);

                i++;
            }
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

            Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
                LoginMenu.currentPerson = null;
                try {
                    Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);

            ImageView personImage = ((Seller) LoginMenu.currentPerson).getImageView();
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


    }

    static class SellerProducts {
        public static void show() {
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
                new SellerMenu().show();
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

            Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
                LoginMenu.currentPerson = null;
                try {
                    Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);

            ImageView personImage = ((Seller) LoginMenu.currentPerson).getImageView();
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

        private static void showFields(Pane parent) {
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

            Label buyers = new Label("View buyers");
            buyers.setFont(new Font(20));
            buyers.setLayoutX(1100);
            buyers.setLayoutY(5);
            pane.getChildren().add(buyers);

            updateList(pane);
        }

        private static void updateList(Pane pane) {
            int i = 1;
            for (Product allProduct : SellerAbilitiesController.getAllProducts((Seller) LoginMenu.currentPerson)) {
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

                Label category = new Label(String.valueOf(allProduct.getCategory().getName()));
                category.setFont(new Font(20));
                category.setLayoutX(900);
                category.setLayoutY(50 * i);
                pane.getChildren().add(category);

                Label description = new Label(String.valueOf(allProduct.getDescription()));
                description.setFont(new Font(20));
                description.setLayoutX(650);
                description.setLayoutY(50 * i);
                pane.getChildren().add(description);

                if (!allProduct.getCondition().equals("Unknown")) {
                    Button buyers = new Button("View buyers");
                    buyers.setStyle("-fx-background-color: #858585");
                    buyers.setLayoutX(1100);
                    buyers.setLayoutY(50 * i);
                    buyers.setCursor(Cursor.HAND);
                    buyers.setOnMouseClicked(e -> {

                    });
                    pane.getChildren().add(buyers);
                }

            }
        }
    }

    static class SellerRequests {
        public static void show() {
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

            Button back = new Button("Back");
            back.setStyle("-fx-background-color: #bababa");
            back.setCursor(Cursor.HAND);
            back.setLayoutX(400);
            back.setLayoutY(140);
            back.setOnMouseClicked(e -> {
                new SellerMenu().show();
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

            Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
                LoginMenu.currentPerson = null;
                try {
                    Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);

            ImageView personImage = ((Seller) LoginMenu.currentPerson).getImageView();
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
                EditProduct.show();
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
                RemoveProduct.show();
            });
        }

        private static void addAuctionPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(120);
            requestPage.setLayoutY(400);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);


            Label addAuctionLabel = new Label("Add Auction");
            addAuctionLabel.setFont(new Font(20));
            addAuctionLabel.setLayoutX(45);
            addAuctionLabel.setLayoutY(20);
            requestPage.getChildren().add(addAuctionLabel);

            requestPage.setOnMouseClicked(e -> {
                AddAuction.show();
            });
        }

        private static void editAuctionPage(Pane parent) {
            Pane requestPage = new Pane();
            requestPage.setStyle("-fx-background-color: #bababa");
            requestPage.setPrefWidth(210);
            requestPage.setPrefHeight(70);
            requestPage.setLayoutX(520);
            requestPage.setLayoutY(400);
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
            requestPage.setLayoutY(400);
            requestPage.setCursor(Cursor.HAND);
            parent.getChildren().add(requestPage);


            Label editAuctionLabel = new Label("All requests");
            editAuctionLabel.setFont(new Font(20));
            editAuctionLabel.setLayoutX(45);
            editAuctionLabel.setLayoutY(20);
            requestPage.getChildren().add(editAuctionLabel);

            requestPage.setOnMouseClicked(e -> {
                AllRequests.show();
            });
        }

        static class AddProduct {
//            public static void show() {
//                Pane parent = new Pane();
//                parent.setStyle("-fx-background-color: #858585");
//                Label label = new Label("Add product");
//                label.setLayoutX(120);
//                label.setLayoutY(130);
//                label.setFont(new Font(30));
//                parent.getChildren().add(label);
//                makeTopOfMenu(parent);
//
//                Button back = new Button("Back");
//                back.setStyle("-fx-background-color: #bababa");
//                back.setCursor(Cursor.HAND);
//                back.setLayoutX(400);
//                back.setLayoutY(140);
//                back.setOnMouseClicked(e -> {
//                    SellerRequests.show();
//                });
//                parent.getChildren().add(back);
//
//                Scene scene = new Scene(parent, 1280, 660);
//                Menu.stage.setScene(scene);
//                Menu.stage.show();
//            }

//            private static void makeTopOfMenu(Pane parent) {
//                Pane topMenu = new Pane();
//                topMenu.setStyle("-fx-background-color: #232f3e");
//                topMenu.setPrefWidth(1280);
//                topMenu.setPrefHeight(100);
//                topMenu.setLayoutX(0);
//                topMenu.setLayoutY(0);
//
//                Image image = new Image(Paths.get("src/main/java/view/images/mainMenu.png").toUri().toString());
//                ImageView imageView = new ImageView(image);
//                imageView.setFitWidth(70);
//                imageView.setFitHeight(70);
//                imageView.setLayoutY(10);
//                imageView.setCursor(Cursor.HAND);
//                imageView.setOnMouseClicked(e -> {
//                    try {
//                        Menu.executeMainMenu();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                });
//                topMenu.getChildren().add(imageView);
//
//                Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
//                ImageView logOut = new ImageView(log);
//                logOut.setFitWidth(100);
//                logOut.setFitHeight(80);
//                logOut.setLayoutX(1170);
//                logOut.setLayoutY(10);
//                logOut.setCursor(Cursor.HAND);
//                logOut.setOnMouseClicked(e -> {
//                    LoginMenu.currentPerson = null;
//                    try {
//                        Menu.executeMainMenu();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                });
//                topMenu.getChildren().add(logOut);
//                Image person = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
//                ImageView personImage = new ImageView(person);
//                personImage.setFitWidth(70);
//                personImage.setFitHeight(70);
//                personImage.setLayoutX(320);
//                personImage.setLayoutY(10);
//                topMenu.getChildren().add(personImage);
//
//                Label role = new Label("Seller");
//                role.setFont(new Font(30));
//                role.setLayoutX(640);
//                role.setLayoutY(30);
//                role.setTextFill(Color.WHITE);
//                topMenu.getChildren().add(role);
//
//
//                parent.getChildren().add(topMenu);
//            }

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

                    } else if (Product.isProductExist(idField.getText())) {
                        label = new Label("Already exist");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(80);
                        pane.getChildren().add(label);
                        create = false;
                    }
                    if (nameField.getText().isEmpty()) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(180);
                        pane.getChildren().add(label);
                        create = false;

                    }
                    if (priceField.getText().isEmpty()) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(280);
                        pane.getChildren().add(label);
                        create = false;
                    }
                    if (categoryField.getText().isEmpty()) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(380);
                        pane.getChildren().add(label);
                        create = false;
                    } else if (!Category.isCategoryExist(categoryField.getText())) {
                        label = new Label("Not exist");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(380);
                        pane.getChildren().add(label);
                        create = false;
                    }
                    if (descriptionField.getText().isEmpty()) {
                        label = new Label("Complete");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(480);
                        pane.getChildren().add(label);
                        create = false;
                    }
                    if (create) {
                        Seller seller = (Seller) LoginMenu.currentPerson;
                        Category category1 = Category.getCategoryByName(categoryField.getText());
                        SellerAbilitiesController.sendAddProductRequestToManager(idField.getText(), nameField.getText(), Long.parseLong(priceField.getText()), seller, category1, descriptionField.getText());
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
            public static void show() {
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
                    SellerRequests.show();
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

                Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
                ImageView logOut = new ImageView(log);
                logOut.setFitWidth(100);
                logOut.setFitHeight(80);
                logOut.setLayoutX(1170);
                logOut.setLayoutY(10);
                logOut.setCursor(Cursor.HAND);
                logOut.setOnMouseClicked(e -> {
                    LoginMenu.currentPerson = null;
                    try {
                        Menu.executeMainMenu();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                topMenu.getChildren().add(logOut);
                Image person = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
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

            private static void showFields(Pane parent) {
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

                Label photo = new Label("Photo");
                photo.setFont(new Font(20));
                photo.setLayoutX(700);
                photo.setLayoutY(5);
                pane.getChildren().add(photo);


                updateList(pane);


            }

            private static void updateList(Pane pane) {
                int i = 1;
                for (Product allProduct : SellerAbilitiesController.getAllProducts((Seller) LoginMenu.currentPerson)) {
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

                    if (!allProduct.getCondition().equals("Unknown")) {
                        Button edit = new Button("Edit");
                        edit.setStyle("-fx-background-color: #858585");
                        edit.setLayoutX(500);
                        edit.setLayoutY(50 * i);
                        edit.setCursor(Cursor.HAND);
                        edit.setOnMouseClicked(e -> {
                            EditProductInfo.editInfo(allProduct);
                        });
                        pane.getChildren().add(edit);

                        ChoiceBox choiceBox = new ChoiceBox();
                        choiceBox.getItems().add("Digital");
                        choiceBox.getItems().add("Art");
                        choiceBox.getItems().add("Book");
                        choiceBox.getItems().add("Food");
                        choiceBox.setLayoutX(700);
                        choiceBox.setLayoutY(50 * i);
                        choiceBox.setOnAction(e -> {
                            System.out.println(choiceBox.getSelectionModel().getSelectedIndex());
                            if (choiceBox.getSelectionModel().getSelectedIndex() == 0) {
                                allProduct.setImageView("digital");
                                show();
                            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 1) {
                                allProduct.setImageView("art");
                                show();
                            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 2) {
                                allProduct.setImageView("book");
                                show();
                            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 3) {
                                allProduct.setImageView("food");
                                show();
                            }
                        });
                        pane.getChildren().add(choiceBox);


                        i++;
                    }

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

                    Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
                    ImageView logOut = new ImageView(log);
                    logOut.setFitWidth(100);
                    logOut.setFitHeight(80);
                    logOut.setLayoutX(1170);
                    logOut.setLayoutY(10);
                    logOut.setCursor(Cursor.HAND);
                    logOut.setOnMouseClicked(e -> {
                        LoginMenu.currentPerson = null;
                        try {
                            Menu.executeMainMenu();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    topMenu.getChildren().add(logOut);
                    Image person = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
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

                    Image cart = new Image(Paths.get("src/main/java/view/images/cart.png").toUri().toString());
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
                        new SellerMenu().showPersonalArea();
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
                            SellerAbilitiesController.sendEditProductRequest(LoginMenu.currentPerson, product, "id", textField.getText());
                            label.setText("Sent");
                            label.setTextFill(Color.GREEN);
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
                            SellerAbilitiesController.sendEditProductRequest(LoginMenu.currentPerson, product, "name", textField.getText());
                            label.setText("Sent");
                            label.setTextFill(Color.GREEN);
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
                                SellerAbilitiesController.sendEditProductRequest(LoginMenu.currentPerson, product, "money", textField.getText());
                                label.setText("Sent");
                                label.setTextFill(Color.GREEN);
                            }
                        }
                    });
                }

                private static void category(Pane productInfo, Product product) {
                    Label category = new Label("Category:" + "\n" + product.getCategory().getName());
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
                            if (!Category.isCategoryExist(textField.getText())) {
                                label.setText("Doesnt exist");
                                label.setTextFill(Color.RED);
                            } else {
                                SellerAbilitiesController.sendEditProductRequest(LoginMenu.currentPerson, product, "category", textField.getText());
                                label.setText("Sent");
                                label.setTextFill(Color.GREEN);
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
                            SellerAbilitiesController.sendEditProductRequest(LoginMenu.currentPerson, product, "category", textField.getText());
                            label.setText("Sent");
                            label.setTextFill(Color.GREEN);
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
                        Label label = new Label();
                        label.setFont(new Font(10));
                        label.setLayoutX(150);
                        label.setLayoutY(285);
                        personalInfo.getChildren().add(label);
                        product.setNumberOfProducts(product.getNumberOfProducts() + 1);
                        label.setText("Done");
                        label.setTextFill(Color.GREEN);
                        increase.setText("Number:" + "\n" + product.getNumberOfProducts());

                    });
                }


            }
        }

        static class RemoveProduct {
            public static void show() {
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
                    SellerRequests.show();
                });
                parent.getChildren().add(back);

                Button update = new Button("Update list");
                update.setStyle("-fx-background-color: #bababa");
                update.setCursor(Cursor.HAND);
                update.setLayoutX(600);
                update.setLayoutY(110);
                update.setOnMouseClicked(e -> {
                    showFields(parent);
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

                Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
                ImageView logOut = new ImageView(log);
                logOut.setFitWidth(100);
                logOut.setFitHeight(80);
                logOut.setLayoutX(1170);
                logOut.setLayoutY(10);
                logOut.setCursor(Cursor.HAND);
                logOut.setOnMouseClicked(e -> {
                    LoginMenu.currentPerson = null;
                    try {
                        Menu.executeMainMenu();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                topMenu.getChildren().add(logOut);
                Image person = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
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

            private static void showFields(Pane parent) {
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

            private static void updateList(Pane pane) {
                int i = 1;
                for (Product allProduct : SellerAbilitiesController.getAllProducts((Seller) LoginMenu.currentPerson)) {
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

                    Label category = new Label(String.valueOf(allProduct.getCategory().getName()));
                    category.setFont(new Font(20));
                    category.setLayoutX(900);
                    category.setLayoutY(50 * i);
                    pane.getChildren().add(category);

                    Label description = new Label(String.valueOf(allProduct.getDescription()));
                    description.setFont(new Font(20));
                    description.setLayoutX(650);
                    description.setLayoutY(50 * i);
                    pane.getChildren().add(description);

                    if (!allProduct.getCondition().equals("Unknown")) {
                        Button edit = new Button("Delete");
                        edit.setStyle("-fx-background-color: #858585");
                        edit.setLayoutX(1200);
                        edit.setLayoutY(50 * i);
                        edit.setCursor(Cursor.HAND);
                        edit.setOnMouseClicked(e -> {
                            SellerAbilitiesController.sendDeleteProductRequest(LoginMenu.currentPerson, allProduct);
                            Label label = new Label("Sent");
                            label.setTextFill(Color.GREEN);
                            label.setLayoutX(1200);
                            label.setLayoutY(75 * i);
                            pane.getChildren().add(label);
                        });
                        pane.getChildren().add(edit);
                    }

                }
            }

        }

        static class AddAuction {
            public static void show() {
                Pane parent = new Pane();
                parent.setStyle("-fx-background-color: #858585");
                Label label = new Label("Add auction");
                label.setLayoutX(120);
                label.setLayoutY(130);
                label.setFont(new Font(30));
                parent.getChildren().add(label);

                makeTopOfMenu(parent);

                Button back = new Button("Back");
                back.setStyle("-fx-background-color: #bababa");
                back.setCursor(Cursor.HAND);
                back.setLayoutX(400);
                back.setLayoutY(140);
                back.setOnMouseClicked(e -> {
                    SellerRequests.show();
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

                ImageView logOut = ((Seller) LoginMenu.currentPerson).getImageView();
                logOut.setFitWidth(100);
                logOut.setFitHeight(80);
                logOut.setLayoutX(1170);
                logOut.setLayoutY(10);
                logOut.setCursor(Cursor.HAND);
                logOut.setOnMouseClicked(e -> {
                    LoginMenu.currentPerson = null;
                    try {
                        Menu.executeMainMenu();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                topMenu.getChildren().add(logOut);
                Image person = new Image(Paths.get("src/main/java/view/images/unknownPerson.jpg").toUri().toString());
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
        }

        static class EditAuction {
            public static void show() {
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                Pane parent = new Pane();
                parent.setStyle("-fx-background-color: #858585");
                Label label = new Label("Edit product");
                label.setLayoutX(120);
                label.setLayoutY(130);
                label.setFont(new Font(30));
                parent.getChildren().add(label);
//                showFields(parent);
                makeTopOfMenu(parent);

                Button back = new Button("Back");
                back.setStyle("-fx-background-color: #bababa");
                back.setCursor(Cursor.HAND);
                back.setLayoutX(400);
                back.setLayoutY(140);
                back.setOnMouseClicked(e -> {
                    SellerRequests.show();
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

                Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
                ImageView logOut = new ImageView(log);
                logOut.setFitWidth(100);
                logOut.setFitHeight(80);
                logOut.setLayoutX(1170);
                logOut.setLayoutY(10);
                logOut.setCursor(Cursor.HAND);
                logOut.setOnMouseClicked(e -> {
                    LoginMenu.currentPerson = null;
                    try {
                        Menu.executeMainMenu();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                topMenu.getChildren().add(logOut);

                ImageView personImage = ((Seller) LoginMenu.currentPerson).getImageView();
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


        }

        static class AllRequests {
            public static void show() {
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
                    SellerRequests.show();
                });
                parent.getChildren().add(back);

                Button update = new Button("Update list");
                update.setStyle("-fx-background-color: #bababa");
                update.setCursor(Cursor.HAND);
                update.setLayoutX(500);
                update.setLayoutY(110);
                update.setOnMouseClicked(e -> {
                    showFields(parent);
                });
                parent.getChildren().add(update);
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

                Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
                ImageView logOut = new ImageView(log);
                logOut.setFitWidth(100);
                logOut.setFitHeight(80);
                logOut.setLayoutX(1170);
                logOut.setLayoutY(10);
                logOut.setCursor(Cursor.HAND);
                logOut.setOnMouseClicked(e -> {
                    LoginMenu.currentPerson = null;
                    try {
                        Menu.executeMainMenu();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                topMenu.getChildren().add(logOut);

                ImageView personImage = ((Seller) LoginMenu.currentPerson).getImageView();
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

            private static void showFields(Pane parent) {
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

            private static void updateList(Pane pane) {
                int i = 1;
                for (Request allSellerRequest : SellerAbilitiesController.getAllSellerRequests((Seller) LoginMenu.currentPerson)) {
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
                            SellerAbilitiesController.deleteRequest((Seller) LoginMenu.currentPerson, allSellerRequest);
                        });
                        pane.getChildren().add(delete);
                    }

                }
            }

        }
    }

    static class SellerBalance {
        public static void show() {
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label balance = new Label("Balance");
            balance.setLayoutX(600);
            balance.setLayoutY(200);
            balance.setFont(new Font(25));
            parent.getChildren().add(balance);

            Seller seller = (Seller) LoginMenu.currentPerson;
//            Label money = new Label(String.valueOf(seller.getBalance()));
//            money.setFont(new Font("Ink Free", 50));
//            money.setLayoutX(600);
//            money.setLayoutY(220);
//            parent.getChildren().add(money);

            Button back = new Button("Back");
            back.setLayoutX(610);
            back.setLayoutY(350);
            back.setCursor(Cursor.HAND);
            back.setOnMouseClicked(e -> new SellerMenu().show());
            parent.getChildren().add(back);

            Scene scene = new Scene(parent, 1280, 660);
            Menu.stage.setScene(scene);
            Menu.stage.show();
        }
    }

    static class Categories {
        public static void showPage() {
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
            backButton.setOnMouseClicked(e -> new SellerMenu().showPersonalArea());
            parent.getChildren().add(backButton);

            scrollPane.setContent(parent);

            Scene scene = new Scene(scrollPane, 1280, 660);
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

            Image log = new Image(Paths.get("src/main/java/view/images/logOut.png").toUri().toString());
            ImageView logOut = new ImageView(log);
            logOut.setFitWidth(100);
            logOut.setFitHeight(80);
            logOut.setLayoutX(1170);
            logOut.setLayoutY(10);
            logOut.setCursor(Cursor.HAND);
            logOut.setOnMouseClicked(e -> {
                LoginMenu.currentPerson = null;
                try {
                    Menu.executeMainMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            topMenu.getChildren().add(logOut);

            ImageView personImage = ((Seller) LoginMenu.currentPerson).getImageView();
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

        private static void showFields(Pane parent) {
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

        private static void updateList(Pane pane) {
            int i = 1;
            for (Category allCategory : SellerAbilitiesController.getAllCategories()) {
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