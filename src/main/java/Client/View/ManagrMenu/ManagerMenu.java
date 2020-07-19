package Client.View.ManagrMenu;

import Client.Controller.RegisterAndLogin.PersonController;
import Client.Model.Users.*;
import Server.Model.Category.Category;
import Server.Model.Discount;
import Server.Model.Product;
import Server.Model.Requests.Request;
import Client.View.LoginAndRegister.LoginMenu;
import Client.View.Menu;
import Client.View.SupporterMenu.CreateSupporter;
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

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ManagerMenu extends Menu {
    private static Manager logInManager;
    private static Manager mainManager;

    public void show() throws IOException, ClassNotFoundException {
        showPersonalArea();
    }

    public void showPersonalArea() throws IOException, ClassNotFoundException {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label label = new Label("Your Account");
        label.setFont(new Font(30));
        label.setLayoutX(90);
        label.setLayoutY(120);
        parent.getChildren().add(label);
        createPersonalInfoPanel(parent);
        createAllMembersPanel(parent);
        createGiftCardsPanel(parent);
        createCategoryPanel(parent);
        createRequestPagePanel(parent);
        createProductsPagePanel(parent);
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
                ManagerPersonalInfoAbilities.editPersonalInfo();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

    }

    private void createAllMembersPanel(Pane parent) {
        Pane allMembers = new Pane();
        allMembers.setStyle("-fx-background-color: #bababa");
        allMembers.setPrefWidth(240);
        allMembers.setPrefHeight(70);
        allMembers.setLayoutX(490);
        allMembers.setLayoutY(200);
        allMembers.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/member.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        allMembers.getChildren().add(imageView);

        Label membersLabel = new Label("Members");
        membersLabel.setFont(new Font(20));
        membersLabel.setLayoutX(60);
        membersLabel.setLayoutY(10);
        allMembers.getChildren().add(membersLabel);

        Label membersSecondLabel = new Label("Edit and Remove members");
        membersSecondLabel.setFont(new Font(12));
        membersSecondLabel.setLayoutX(60);
        membersSecondLabel.setLayoutY(40);
        allMembers.getChildren().add(membersSecondLabel);

        allMembers.setOnMouseClicked(e -> {
            try {
                ManagerAllMembersAbilities.showPage();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(allMembers);
    }

    private void createGiftCardsPanel(Pane parent) {
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

        Label giftCardLabel = new Label("Gift Card List");
        giftCardLabel.setFont(new Font(20));
        giftCardLabel.setLayoutX(60);
        giftCardLabel.setLayoutY(10);
        giftCard.getChildren().add(giftCardLabel);

        Label giftCardSecondLabel = new Label("List of all gift cards");
        giftCardSecondLabel.setFont(new Font(12));
        giftCardSecondLabel.setLayoutX(60);
        giftCardSecondLabel.setLayoutY(40);
        giftCard.getChildren().add(giftCardSecondLabel);
        giftCard.setOnMouseClicked(e -> {
            try {
                ManagerAllGiftCodes.showPage();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(giftCard);
    }

    private void createCategoryPanel(Pane parent) {
        Pane categories = new Pane();
        categories.setStyle("-fx-background-color: #bababa");
        categories.setPrefWidth(210);
        categories.setPrefHeight(70);
        categories.setLayoutX(90);
        categories.setLayoutY(350);
        categories.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/category.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        categories.getChildren().add(imageView);


        Label balanceLabel = new Label("Categories");
        balanceLabel.setFont(new Font(20));
        balanceLabel.setLayoutX(60);
        balanceLabel.setLayoutY(10);
        categories.getChildren().add(balanceLabel);

        Label balanceSecondLabel = new Label("Edit and Add category");
        balanceSecondLabel.setFont(new Font(12));
        balanceSecondLabel.setLayoutX(60);
        balanceSecondLabel.setLayoutY(40);
        categories.getChildren().add(balanceSecondLabel);

        categories.setOnMouseClicked(e -> {
            try {
                ManagerCategories.showPage();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(categories);
    }

    private void createRequestPagePanel(Pane parent) {
        Pane requests = new Pane();
        requests.setStyle("-fx-background-color: #bababa");
        requests.setPrefWidth(240);
        requests.setPrefHeight(70);
        requests.setLayoutX(490);
        requests.setLayoutY(350);
        requests.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/request.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        requests.getChildren().add(imageView);


        Label balanceLabel = new Label("Requests");
        balanceLabel.setFont(new Font(20));
        balanceLabel.setLayoutX(60);
        balanceLabel.setLayoutY(10);
        requests.getChildren().add(balanceLabel);

        Label balanceSecondLabel = new Label("Answer Requests");
        balanceSecondLabel.setFont(new Font(12));
        balanceSecondLabel.setLayoutX(60);
        balanceSecondLabel.setLayoutY(40);
        requests.getChildren().add(balanceSecondLabel);

        requests.setOnMouseClicked(e -> {
            try {
                ManagerRequests.showPage();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        parent.getChildren().add(requests);
    }

    private void createProductsPagePanel(Pane parent) {
        Pane productsPanel = new Pane();
        productsPanel.setStyle("-fx-background-color: #bababa");
        productsPanel.setPrefWidth(240);
        productsPanel.setPrefHeight(70);
        productsPanel.setLayoutX(890);
        productsPanel.setLayoutY(350);
        productsPanel.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/Client/view/images/request.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        productsPanel.getChildren().add(imageView);

        Label balanceLabel = new Label("Products");
        balanceLabel.setFont(new Font(20));
        balanceLabel.setLayoutX(60);
        balanceLabel.setLayoutY(10);
        productsPanel.getChildren().add(balanceLabel);

        Label balanceSecondLabel = new Label("Manage products");
        balanceSecondLabel.setFont(new Font(12));
        balanceSecondLabel.setLayoutX(60);
        balanceSecondLabel.setLayoutY(40);
        productsPanel.getChildren().add(balanceSecondLabel);

        productsPanel.setOnMouseClicked(e -> {
            try {
                ManagerProducts.showPage();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        parent.getChildren().add(productsPanel);
    }

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
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        Manager manager = gson.fromJson(json, Manager.class);
        logInManager = manager;

//        ImageView personImage = manager.getImageView();
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
                    dataOutputStream.writeUTF("unknown");
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
                    dataOutputStream.writeUTF("man");
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
                    dataOutputStream.writeUTF("woman");
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

        Label role = new Label("Manager");
        role.setFont(new Font("Ink Free", 30));
        role.setLayoutX(640);
        role.setLayoutY(30);
        role.setTextFill(Color.WHITE);
        topMenu.getChildren().add(role);

        parent.getChildren().add(topMenu);
    }

    static class ManagerPersonalInfoAbilities {
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

//            ImageView personImage = (logInManager).getImageView();
//            personImage.setFitWidth(70);
//            personImage.setFitHeight(70);
//            personImage.setLayoutX(320);
//            personImage.setLayoutY(10);
//            topMenu.getChildren().add(personImage);

            Label role = new Label("Manager");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);

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
                ManagerMenu managerMenu = new ManagerMenu();
                try {
                    managerMenu.showPersonalArea();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }

        private static void name(Pane personalInfo) throws IOException, ClassNotFoundException {
            Label name = new Label("Name:" + "\n" + (logInManager).getName());
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
                        dataOutputStream.writeUTF("editPersonalInfo,name," + textField.getText());
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
            Label family = new Label("Family:" + "\n" + (logInManager).getFamily());
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
                        dataOutputStream.writeUTF("editPersonalInfo,family," + textField.getText());
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
            Label email = new Label("Email:" + "\n" + (logInManager).getEmail());
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
                    if (!PersonController.emailTypeErr(textField.getText())) {
                        label.setText("exmaple@example.con");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editPersonalInfo,email," + textField.getText());
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
            Label phone = new Label("Phone:" + "\n" + (logInManager).getPhone());
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
                    if (!PersonController.phoneTypeErr(textField.getText())) {
                        label.setText(":||||||");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editPersonalInfo,phone," + textField.getText());
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
            Label password = new Label("Password:" + "\n" + (logInManager).getPassword());
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
                            dataOutputStream.writeUTF("editPersonalInfo,password," + textField.getText());
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

    static class ManagerAllMembersAbilities {
        public static void showPage() throws IOException, ClassNotFoundException {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("ALl Members");
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
                    new ManagerMenu().showPersonalArea();
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
                    showFields(parent);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(updateList);

            Button createManagerAccount = new Button("Create manager account");
            createManagerAccount.setLayoutX(500);
            createManagerAccount.setLayoutY(110);
            createManagerAccount.setStyle("-fx-background-color: #bababa");
            createManagerAccount.setCursor(Cursor.HAND);
            createManagerAccount.setOnMouseClicked(e -> {
                new CreateManager().show();
            });
            parent.getChildren().add(createManagerAccount);

            Button createSupporterAccount = new Button("Create supporter account");
            createSupporterAccount.setLayoutX(685);
            createSupporterAccount.setLayoutY(110);
            createSupporterAccount.setStyle("-fx-background-color: #bababa");
            createSupporterAccount.setCursor(Cursor.HAND);
            createSupporterAccount.setOnMouseClicked(e -> {
                new CreateSupporter().show();
            });
            parent.getChildren().add(createSupporterAccount);

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

            ImageView personImage = (logInManager).getImageView();
            personImage.setFitWidth(70);
            personImage.setFitHeight(70);
            personImage.setLayoutX(320);
            personImage.setLayoutY(10);
            topMenu.getChildren().add(personImage);

            Label role = new Label("Manager");
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

            Label username = new Label("Username");
            username.setFont(new Font(20));
            username.setLayoutX(10);
            username.setLayoutY(5);
            pane.getChildren().add(username);
            Label name = new Label("Name");
            name.setFont(new Font(20));
            name.setLayoutX(210);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label family = new Label("Family");
            family.setFont(new Font(20));
            family.setLayoutX(410);
            family.setLayoutY(5);
            pane.getChildren().add(family);

            Label phone = new Label("Phone");
            phone.setFont(new Font(20));
            phone.setLayoutX(610);
            phone.setLayoutY(5);
            pane.getChildren().add(phone);

            Label email = new Label("Email");
            email.setFont(new Font(20));
            email.setLayoutX(810);
            email.setLayoutY(5);
            pane.getChildren().add(email);

            Label online = new Label("Online");
            online.setFont(new Font(20));
            online.setLayoutX(1010);
            online.setLayoutY(5);
            pane.getChildren().add(online);

            Label delete = new Label("Delete");
            delete.setFont(new Font(20));
            delete.setLayoutX(1150);
            delete.setLayoutY(5);
            pane.getChildren().add(delete);
            updateList(pane);
            parent.getChildren().add(pane);
        }

        private static void updateList(Pane parent) throws IOException, ClassNotFoundException {
            int i = 1;

            Person person = logInManager;
            dataOutputStream.writeUTF("getAllMembers");
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<Person> allMembers = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Gson gson = new Gson();
                Person person1 = gson.fromJson(dataInputStream.readUTF(), Person.class);
                allMembers.add(person1);
            }

            for (Person member : allMembers) {
                if (person == member) {
                    continue;
                }
                Label username = new Label(member.getUsername());
                username.setFont(new Font(20));
                username.setTextFill(Color.BLACK);
                username.setLayoutX(10);
                username.setLayoutY(50 * i);
                username.setCursor(Cursor.HAND);
                username.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);

                    if (member instanceof Seller) {
                        label.setText("Seller");
                    } else if (member instanceof Buyer) {
                        label.setText("Buyer");
                    } else if (member instanceof Supporter) {
                        label.setText("Supporter");
                    } else {
                        label.setText("Manager");
                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(username);

                Label name = new Label(member.getName());
                name.setFont(new Font(20));
                name.setTextFill(Color.BLACK);

                name.setLayoutX(210);
                name.setLayoutY(50 * i);
                name.setCursor(Cursor.HAND);
                name.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);

                    if (member instanceof Seller) {
                        label.setText("Seller");
                    } else if (member instanceof Buyer) {
                        label.setText("Buyer");
                    } else if (member instanceof Supporter) {
                        label.setText("Supporter");
                    } else {
                        label.setText("Manager");
                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(name);

                Label family = new Label(member.getFamily());
                family.setFont(new Font(20));
                family.setTextFill(Color.BLACK);

                family.setLayoutX(410);
                family.setLayoutY(50 * i);
                family.setCursor(Cursor.HAND);
                family.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);
                    if (member instanceof Seller) {
                        label.setText("Seller");
                    } else if (member instanceof Buyer) {
                        label.setText("Buyer");
                    } else if (member instanceof Supporter) {
                        label.setText("Supporter");
                    } else {
                        label.setText("Manager");
                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(family);

                Label phone = new Label(member.getPhone());
                phone.setFont(new Font(20));
                phone.setTextFill(Color.BLACK);

                phone.setLayoutX(610);
                phone.setLayoutY(50 * i);
                phone.setCursor(Cursor.HAND);

                phone.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);
                    if (member instanceof Seller) {
                        label.setText("Seller");
                    } else if (member instanceof Buyer) {
                        label.setText("Buyer");
                    } else if (member instanceof Supporter) {
                        label.setText("Supporter");
                    } else {
                        label.setText("Manager");
                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(phone);

                Label email = new Label(member.getEmail());
                email.setFont(new Font(20));
                email.setTextFill(Color.BLACK);

                email.setLayoutX(810);
                email.setLayoutY(50 * i);
                email.setCursor(Cursor.HAND);
                email.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);

                    if (member instanceof Seller) {
                        label.setText("Seller");
                    } else if (member instanceof Buyer) {
                        label.setText("Buyer");
                    } else if (member instanceof Supporter) {
                        label.setText("Supporter");
                    } else {
                        label.setText("Manager");
                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(email);


                Button circle = new Button();
                if (member instanceof Seller && ((Seller) member).isOnline()) {
                    circle.setStyle("-fx-background-color: Aqua");
                } else if (member instanceof Buyer && ((Buyer) member).isOnline()) {
                    circle.setStyle("-fx-background-color: Aqua");
                } else if (member instanceof Supporter && ((Supporter) member).isOnline()) {
                    circle.setStyle("-fx-background-color: Aqua");
                } else if (member instanceof Manager && ((Manager) member).isOnline()) {
                    circle.setStyle("-fx-background-color: Aqua");
                } else {
                    circle.setStyle("-fx-background-color: White");
                }

                circle.setLayoutX(1033);
                circle.setLayoutY(50 * i);
                circle.setCursor(Cursor.HAND);
                circle.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);
                    if (member instanceof Seller) {
                        label.setText("Seller");
                    } else if (member instanceof Buyer) {
                        label.setText("Buyer");
                    } else if (member instanceof Supporter) {
                        label.setText("Supporter");
                    } else {
                        label.setText("Manager");
                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(circle);

                dataOutputStream.writeUTF("getMainManager");
                dataOutputStream.flush();
                Gson gson = new Gson();
                String json = dataInputStream.readUTF();
                mainManager = gson.fromJson(json, Manager.class);

                if (person == mainManager) {
                    Button button = new Button("Delete");
                    button.setStyle("-fx-background-color: #858585");
                    button.setCursor(Cursor.HAND);
                    button.setLayoutX(1150);
                    button.setLayoutY(50 * i);
                    button.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.writeUTF("deleteUser," + member.getUsername());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    parent.getChildren().add(button);
                }
                i++;
            }
        }

    }

    static class ManagerAllGiftCodes {

        public static void showPage() throws IOException, ClassNotFoundException {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
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
                    new ManagerMenu().showPersonalArea();
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
                    showFields(parent);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(updateList);

            Button add = new Button("Add");
            add.setLayoutX(600);
            add.setLayoutY(110);
            add.setStyle("-fx-background-color: #bababa");
            add.setCursor(Cursor.HAND);
            add.setOnMouseClicked(e -> {
                addDiscountCode();
            });
            parent.getChildren().add(add);

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

            ImageView personImage = (logInManager).getImageView();
            personImage.setFitWidth(70);
            personImage.setFitHeight(70);
            personImage.setLayoutX(320);
            personImage.setLayoutY(10);
            topMenu.getChildren().add(personImage);

            Label role = new Label("Manager");
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

            Label code = new Label("Serial (6 digits)");
            code.setFont(new Font(20));
            code.setLayoutX(10);
            code.setLayoutY(5);
            pane.getChildren().add(code);

            Label start = new Label("Time of start (hh:mm)");
            start.setFont(new Font(20));
            start.setLayoutX(200);
            start.setLayoutY(5);
            pane.getChildren().add(start);

            Label end = new Label("Time of end (hh:mm)");
            end.setFont(new Font(20));
            end.setLayoutX(450);
            end.setLayoutY(5);
            pane.getChildren().add(end);

            Label percent = new Label("Percent");
            percent.setFont(new Font(20));
            percent.setLayoutX(700);
            percent.setLayoutY(5);
            pane.getChildren().add(percent);

            Label edit = new Label("Edit");
            edit.setFont(new Font(20));
            edit.setLayoutX(900);
            edit.setLayoutY(5);
            pane.getChildren().add(edit);

            Label add = new Label("Add");
            add.setFont(new Font(20));
            add.setLayoutX(1000);
            add.setLayoutY(5);
            pane.getChildren().add(add);

            Label delete = new Label("Delete");
            delete.setFont(new Font(20));
            delete.setLayoutX(1100);
            delete.setLayoutY(5);
            pane.getChildren().add(delete);
            updateList(pane);
        }

        private static void addDiscountCode() {
            Pane pane = new Pane();
            Label code = new Label("Code (6 digits)");
            code.setFont(new Font("Ink Free", 25));
            code.setLayoutX(300);
            code.setLayoutY(50);
            pane.getChildren().add(code);

            TextField codeField = new TextField();
            codeField.setLayoutX(300);
            codeField.setLayoutY(100);
            pane.getChildren().add(codeField);

            Label discount = new Label("Discount");
            discount.setFont(new Font("Ink Free", 25));
            discount.setLayoutX(300);
            discount.setLayoutY(150);
            pane.getChildren().add(discount);

            TextField discountField = new TextField();
            discountField.setLayoutX(300);
            discountField.setLayoutY(200);
            pane.getChildren().add(discountField);

            Label max = new Label("Max");
            max.setFont(new Font("Ink Free", 25));
            max.setLayoutX(300);
            max.setLayoutY(250);
            pane.getChildren().add(max);

            TextField maxField = new TextField();
            maxField.setLayoutX(300);
            maxField.setLayoutY(300);
            pane.getChildren().add(maxField);

            Label start = new Label("Start (hh:mm)");
            start.setFont(new Font("Ink Free", 25));
            start.setLayoutX(300);
            start.setLayoutY(350);
            pane.getChildren().add(start);

            TextField startField = new TextField();
            startField.setLayoutX(300);
            startField.setLayoutY(400);
            pane.getChildren().add(startField);

            Label end = new Label("End (hh:mm)");
            end.setFont(new Font("Ink Free", 25));
            end.setLayoutX(300);
            end.setLayoutY(450);
            pane.getChildren().add(end);

            TextField endField = new TextField();
            endField.setLayoutX(300);
            endField.setLayoutY(500);
            pane.getChildren().add(endField);

            Button button = new Button("Add");
            button.setCursor(Cursor.HAND);
            button.setLayoutX(300);
            button.setLayoutY(530);
            button.setOnMouseClicked(e -> {
                try {
                    dataOutputStream.writeUTF("createDiscount," + codeField.getText() + "," + discountField.getText() + ","
                            + maxField.getText() + "," + startField.getText() + "," + endField.getText());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                String[] splitInput = new String[6];
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
                        label.setLayoutY(125);
                        pane.getChildren().add(label);
                        break;
                    case "2":
                        label = new Label("At least 6 digit");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(125);
                        pane.getChildren().add(label);
                        break;
                    case "3":
                        label = new Label("Already exist");
                        label.setTextFill(Color.RED);
                        label.setLayoutX(300);
                        label.setLayoutY(125);
                        pane.getChildren().add(label);
                        break;
                }

                if (splitInput[1].equals("1")) {
                    label = new Label("Complete");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(300);
                    label.setLayoutY(220);
                    pane.getChildren().add(label);
                }

                if (splitInput[2].equals("1")) {
                    label = new Label("Complete");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(300);
                    label.setLayoutY(320);
                    pane.getChildren().add(label);
                }

                if (splitInput[3].equals("1")) {
                    label = new Label("Complete");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(300);
                    label.setLayoutY(420);
                    pane.getChildren().add(label);
                }

                if (splitInput[4].equals("1")) {
                    label = new Label("Complete");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(300);
                    label.setLayoutY(520);
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

        private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
            int i = 1;
            dataOutputStream.writeUTF("getAllDiscounts");
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<Discount> discounts = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Gson gson = new Gson();
                Discount discount = gson.fromJson(dataInputStream.readUTF(), Discount.class);
                discounts.add(discount);
            }

            for (Discount allDiscount : discounts) {
                Label code = new Label(allDiscount.getCode());
                code.setFont(new Font(20));
                code.setLayoutX(10);
                code.setLayoutY(50 * i);
                pane.getChildren().add(code);

                Label start = new Label(allDiscount.getStartTime().toString());
                start.setFont(new Font(20));
                start.setLayoutX(200);
                start.setLayoutY(50 * i);
                pane.getChildren().add(start);

                Label end = new Label(allDiscount.getEndTime().toString());
                end.setFont(new Font(20));
                end.setLayoutX(450);
                end.setLayoutY(50 * i);
                pane.getChildren().add(end);

                Label percent = new Label();
                String string = String.valueOf(allDiscount.getDiscountPercent());
                percent.setText(string);
                percent.setFont(new Font(20));
                percent.setLayoutX(700);
                percent.setLayoutY(50 * i);
                pane.getChildren().add(percent);

                Button edit = new Button("Edit");
                edit.setLayoutX(900);
                edit.setLayoutY(50 * i);
                edit.setCursor(Cursor.HAND);
                edit.setStyle("-fx-background-color: #858585");
                edit.setOnMouseClicked(e -> {
                    DiscountEdit.editDiscountInfo(allDiscount);
                });
                pane.getChildren().add(edit);

                Button add = new Button("Add");
                add.setLayoutX(1000);
                add.setLayoutY(50 * i);
                add.setCursor(Cursor.HAND);
                add.setStyle("-fx-background-color: #858585");
                add.setOnMouseClicked(e -> {
                    Pane pane1 = new Pane();

                    TextField textField = new TextField();
                    textField.setLayoutX(50);
                    textField.setLayoutY(100);
                    pane1.getChildren().add(textField);

                    Button button = new Button("Add");
                    button.setLayoutX(100);
                    button.setLayoutY(150);
                    button.setOnMouseClicked(e1 -> {
                        try {
                            dataOutputStream.writeUTF("addDiscountToBuyer," + textField.getText() + "," + allDiscount.getCode());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("pass")) {
                                Label label = new Label("Done");
                                label.setTextFill(Color.GREEN);
                                label.setLayoutX(50);
                                label.setLayoutY(150);
                                pane1.getChildren().add(label);
                            } else {
                                Label label = new Label("Not exist");
                                label.setTextFill(Color.RED);
                                label.setLayoutX(50);
                                label.setLayoutY(150);
                                pane1.getChildren().add(label);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    pane1.getChildren().add(button);
                    Stage stage = new Stage();
                    Scene scene1 = new Scene(pane1, 300, 300);
                    stage.setScene(scene1);
                    stage.show();

                });
                pane.getChildren().add(add);

                Button delete = new Button("Delete");
                delete.setLayoutX(1100);
                delete.setLayoutY(50 * i);
                delete.setCursor(Cursor.HAND);
                delete.setStyle("-fx-background-color: #858585");
                delete.setOnMouseClicked(e -> {
                    try {
                        dataOutputStream.writeUTF("deleteDiscount," + allDiscount.getCode());
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                pane.getChildren().add(delete);
                i++;
            }
        }

        static class DiscountEdit {
            public static void editDiscountInfo(Discount discount) {
                Pane parent = new Pane();
                parent.setStyle("-fx-background-color: #858585");
                Label label = new Label("Edit discount");
                label.setFont(new Font(30));
                label.setLayoutX(400);
                label.setLayoutY(100);
                parent.getChildren().add(label);
                makeTopMenu(parent);
                showFields(parent, discount);
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

                Label role = new Label("Manager");
                role.setFont(new Font(30));
                role.setLayoutX(640);
                role.setLayoutY(30);
                role.setTextFill(Color.WHITE);
                topMenu.getChildren().add(role);


                parent.getChildren().add(topMenu);
            }

            private static void showFields(Pane parent, Discount discount) {
                Pane editDiscount = new Pane();
                editDiscount.setStyle("-fx-background-color: #bababa");
                editDiscount.setPrefWidth(400);
                editDiscount.setPrefHeight(400);
                editDiscount.setLayoutX(400);
                editDiscount.setLayoutY(150);
                parent.getChildren().add(editDiscount);
                code(editDiscount, discount);
                startTime(editDiscount, discount);
                endTime(editDiscount, discount);
                percent(editDiscount, discount);
                maxDiscount(editDiscount, discount);

                Button button = new Button("Save and back");
                button.setPrefWidth(100);
                button.setLayoutX(150);
                button.setLayoutY(300);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);
                button.setOnMouseClicked(e -> {
                    ManagerMenu managerMenu = new ManagerMenu();
                    try {
                        managerMenu.showPersonalArea();
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
            }

            private static void code(Pane editDiscount, Discount discount) {
                Label code = new Label("Code:" + "\n" + discount.getCode());
                code.setFont(new Font(15));
                code.setLayoutX(20);
                editDiscount.getChildren().add(code);

                Line line = new Line();
                line.setStartX(0);
                line.setEndX(400);
                line.setStartY(50);
                line.setEndY(50);
                editDiscount.getChildren().add(line);

                Button button = new Button("Edit");
                button.setLayoutX(350);
                button.setLayoutY(10);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);

                TextField textField = new TextField();
                textField.setPromptText("New code");
                textField.setLayoutX(200);
                textField.setLayoutY(10);
                editDiscount.getChildren().add(textField);
                button.setOnMouseClicked(e -> {
                    Label label = new Label();
                    label.setFont(new Font(10));
                    label.setLayoutX(200);
                    label.setLayoutY(35);
                    editDiscount.getChildren().add(label);
                    if (textField.getText().isEmpty()) {
                        label.setText("Complete for edit");
                        label.setTextFill(Color.RED);
                    } else if (textField.getText().length() != 6) {
                        label.setText("At least 6 digit");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editDiscount," + discount.getCode() + "," + "code," + textField.getText());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                code.setText("Code:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            private static void startTime(Pane editDiscount, Discount discount) {
                Label startTime = new Label("Start:" + "\n" + discount.getStartTime().toString());
                startTime.setFont(new Font(15));
                startTime.setLayoutX(20);
                startTime.setLayoutY(50);
                editDiscount.getChildren().add(startTime);

                Line line = new Line();
                line.setStartX(0);
                line.setEndX(400);
                line.setStartY(100);
                line.setEndY(100);
                editDiscount.getChildren().add(line);

                Button button = new Button("Edit");
                button.setLayoutX(350);
                button.setLayoutY(60);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);

                TextField textField = new TextField();
                textField.setPromptText("New start time");
                textField.setLayoutX(200);
                textField.setLayoutY(60);
                editDiscount.getChildren().add(textField);
                button.setOnMouseClicked(e -> {
                    Label label = new Label();
                    label.setFont(new Font(10));
                    label.setLayoutX(200);
                    label.setLayoutY(85);
                    editDiscount.getChildren().add(label);
                    if (textField.getText().isEmpty()) {
                        label.setText("Complete for edit");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editDiscount," + discount.getCode() + "," + "start time," + textField.getText());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                startTime.setText("Start:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            private static void endTime(Pane editDiscount, Discount discount) {
                Label endTime = new Label("End:" + "\n" + discount.getEndTime().toString());
                endTime.setFont(new Font(15));
                endTime.setLayoutX(20);
                endTime.setLayoutY(100);
                editDiscount.getChildren().add(endTime);

                Line line = new Line();
                line.setStartX(0);
                line.setEndX(400);
                line.setStartY(150);
                line.setEndY(150);
                editDiscount.getChildren().add(line);

                Button button = new Button("Edit");
                button.setLayoutX(350);
                button.setLayoutY(110);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);

                TextField textField = new TextField();
                textField.setPromptText("New end time");
                textField.setLayoutX(200);
                textField.setLayoutY(110);
                editDiscount.getChildren().add(textField);
                button.setOnMouseClicked(e -> {
                    Label label = new Label();
                    label.setFont(new Font(10));
                    label.setLayoutX(200);
                    label.setLayoutY(135);
                    editDiscount.getChildren().add(label);
                    if (textField.getText().isEmpty()) {
                        label.setText("Complete for edit");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editDiscount," + discount.getCode() + "," + "end time," + textField.getText());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                endTime.setText("Edit:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            private static void percent(Pane editDiscount, Discount discount) {
                Label percent = new Label("Percent:" + "\n" + discount.getDiscountPercent() + "%");
                percent.setFont(new Font(15));
                percent.setLayoutX(20);
                percent.setLayoutY(150);
                editDiscount.getChildren().add(percent);

                Line line = new Line();
                line.setStartX(0);
                line.setEndX(400);
                line.setStartY(200);
                line.setEndY(200);
                editDiscount.getChildren().add(line);

                Button button = new Button("Edit");
                button.setLayoutX(350);
                button.setLayoutY(160);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);

                TextField textField = new TextField();
                textField.setPromptText("New percent");
                textField.setLayoutX(200);
                textField.setLayoutY(160);
                editDiscount.getChildren().add(textField);
                button.setOnMouseClicked(e -> {
                    Label label = new Label();
                    label.setFont(new Font(10));
                    label.setLayoutX(200);
                    label.setLayoutY(185);
                    editDiscount.getChildren().add(label);
                    if (textField.getText().isEmpty()) {
                        label.setText("Complete for edit");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editDiscount," + discount.getCode() + "," + "percent," + textField.getText());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                percent.setText("Percent:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            private static void maxDiscount(Pane editDiscount, Discount discount) {
                Label max = new Label("Max discount:" + "\n" + discount.getMaxDiscount());
                max.setFont(new Font(15));
                max.setLayoutX(20);
                max.setLayoutY(200);
                editDiscount.getChildren().add(max);

                Line line = new Line();
                line.setStartX(0);
                line.setEndX(400);
                line.setStartY(250);
                line.setEndY(250);
                editDiscount.getChildren().add(line);

                Button button = new Button("Edit");
                button.setLayoutX(350);
                button.setLayoutY(210);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);

                TextField textField = new TextField();
                textField.setPromptText("New max");
                textField.setLayoutX(200);
                textField.setLayoutY(210);
                editDiscount.getChildren().add(textField);
                button.setOnMouseClicked(e -> {
                    Label label = new Label();
                    label.setFont(new Font(10));
                    label.setLayoutX(200);
                    label.setLayoutY(235);
                    editDiscount.getChildren().add(label);
                    if (textField.getText().isEmpty()) {
                        label.setText("Complete for edit");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editDiscount," + discount.getCode() + "," + "max discount," + textField.getText());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                max.setText("Max discount:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    static class ManagerCategories {
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
                    new ManagerMenu().showPersonalArea();
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
                    showFields(parent);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(updateList);

            Button add = new Button("Add");
            add.setLayoutY(110);
            add.setLayoutX(500);
            add.setCursor(Cursor.HAND);
            add.setOnMouseClicked(e -> {
                addCategory();
            });
            add.setStyle("-fx-background-color: #bababa");
            parent.getChildren().add(add);
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

            Image image = new Image(Paths.get("src/main/Client/view/images/mainMenu.png").toUri().toString());
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

            ImageView personImage = logInManager.getImageView();
            personImage.setFitWidth(70);
            personImage.setFitHeight(70);
            personImage.setLayoutX(320);
            personImage.setLayoutY(10);
            topMenu.getChildren().add(personImage);

            Label role = new Label("Manager");
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

            Label edit = new Label("Edit");
            edit.setFont(new Font(20));
            edit.setLayoutX(500);
            edit.setLayoutY(5);
            pane.getChildren().add(edit);

            Label delete = new Label("Delete");
            delete.setFont(new Font(20));
            delete.setLayoutX(700);
            delete.setLayoutY(5);
            pane.getChildren().add(delete);

            updateList(pane);
        }

        private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
            int i = 1;
            dataOutputStream.writeUTF("getAllCategories");
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<Category> categories = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Gson gson = new Gson();
                Category category = gson.fromJson(dataInputStream.readUTF(), Category.class);
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

                Button edit = new Button("Edit");
                edit.setLayoutX(500);
                edit.setLayoutY(50 * i);
                edit.setCursor(Cursor.HAND);
                edit.setStyle("-fx-background-color: #858585");
                edit.setOnMouseClicked(e -> {
                    try {
                        editCategory(allCategory);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
                pane.getChildren().add(edit);

                Button delete = new Button("Delete");
                delete.setLayoutX(700);
                delete.setLayoutY(50 * i);
                delete.setCursor(Cursor.HAND);
                delete.setStyle("-fx-background-color: #858585");
                delete.setOnMouseClicked(e -> {
                    try {
                        dataOutputStream.writeUTF("deleteCategory," + allCategory.getName());
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                pane.getChildren().add(delete);
                i++;
            }
        }

        public static void addCategory() {
            Pane pane = new Pane();
            Label name = new Label("Name");
            name.setFont(new Font("Ink Free", 25));
            name.setLayoutX(300);
            name.setLayoutY(50);
            pane.getChildren().add(name);

            TextField nameField = new TextField();
            nameField.setLayoutX(300);
            nameField.setLayoutY(100);
            pane.getChildren().add(nameField);

            Label detail1 = new Label("Detail 1");
            detail1.setFont(new Font("Ink Free", 25));
            detail1.setLayoutX(300);
            detail1.setLayoutY(150);
            pane.getChildren().add(detail1);

            TextField detail1Field = new TextField();
            detail1Field.setLayoutX(300);
            detail1Field.setLayoutY(200);
            pane.getChildren().add(detail1Field);

            Label detail2 = new Label("Detail 2");
            detail2.setFont(new Font("Ink Free", 25));
            detail2.setLayoutX(300);
            detail2.setLayoutY(250);
            pane.getChildren().add(detail2);

            TextField detail2Field = new TextField();
            detail2Field.setLayoutX(300);
            detail2Field.setLayoutY(300);
            pane.getChildren().add(detail2Field);

            Label detail3 = new Label("Detail 3");
            detail3.setFont(new Font("Ink Free", 25));
            detail3.setLayoutX(300);
            detail3.setLayoutY(350);
            pane.getChildren().add(detail3);

            TextField detail3Field = new TextField();
            detail3Field.setLayoutX(300);
            detail3Field.setLayoutY(400);
            pane.getChildren().add(detail3Field);

            Button button = new Button("Add");
            button.setCursor(Cursor.HAND);
            button.setLayoutX(300);
            button.setLayoutY(430);
            button.setOnMouseClicked(e -> {
                try {
                    dataOutputStream.writeUTF("addCategory," + nameField.getText() + "," + detail1.getText() + "," + detail2.getText() + "," +
                            detail3.getText());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                String[] splitInput = new String[5];
                try {
                    splitInput = dataInputStream.readUTF().split("-");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                Label label = new Label();
                if (splitInput[0].equals("1")) {
                    label.setText("Complete");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(300);
                    label.setLayoutY(125);
                    pane.getChildren().add(label);
                } else if (splitInput[0].equals("2")) {
                    label.setText("Already exist");
                    label.setTextFill(Color.RED);
                    label.setLayoutX(300);
                    label.setLayoutY(125);
                    pane.getChildren().add(label);
                }

                if (splitInput[1].equals("1")) {
                    Label label1 = new Label("Complete");
                    label1.setTextFill(Color.RED);
                    label1.setLayoutX(300);
                    label1.setLayoutY(220);
                    pane.getChildren().add(label1);
                }

                if (splitInput[2].equals("1")) {
                    Label label2 = new Label("Complete");
                    label2.setTextFill(Color.RED);
                    label2.setLayoutX(300);
                    label2.setLayoutY(320);
                    pane.getChildren().add(label2);
                }

                if (splitInput[3].equals("1")) {
                    Label label3 = new Label("Complete");
                    label3.setTextFill(Color.RED);
                    label3.setLayoutX(300);
                    label3.setLayoutY(420);
                    pane.getChildren().add(label3);
                }

                if (splitInput[4].equals("pass")) {
                    Label label4 = new Label("Done");
                    label4.setLayoutX(400);
                    label4.setLayoutY(430);
                    label4.setTextFill(Color.GREEN);
                    pane.getChildren().add(label4);
                }

            });
            pane.getChildren().add(button);

            Scene scene = new Scene(pane, 800, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }

        public static void editCategory(Category category) throws IOException, ClassNotFoundException {
            CategoryEdit.editCategoryInfo(category);
        }

        static class CategoryEdit {
            public static void editCategoryInfo(Category category) throws IOException, ClassNotFoundException {
                Pane parent = new Pane();
                parent.setStyle("-fx-background-color: #858585");
                Label label = new Label("Edit category");
                label.setFont(new Font(30));
                label.setLayoutX(400);
                label.setLayoutY(100);
                parent.getChildren().add(label);
                makeTopMenu(parent);
                showFields(parent, category);
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
                ImageView personImage = ((Manager) objectInputStream.readObject()).getImageView();
                personImage.setFitWidth(70);
                personImage.setFitHeight(70);
                personImage.setLayoutX(320);
                personImage.setLayoutY(10);
                topMenu.getChildren().add(personImage);

                Label role = new Label("Manager");
                role.setFont(new Font(30));
                role.setLayoutX(640);
                role.setLayoutY(30);
                role.setTextFill(Color.WHITE);
                topMenu.getChildren().add(role);

                parent.getChildren().add(topMenu);
            }

            private static void showFields(Pane parent, Category category) {
                Pane editDiscount = new Pane();
                editDiscount.setStyle("-fx-background-color: #bababa");
                editDiscount.setPrefWidth(400);
                editDiscount.setPrefHeight(400);
                editDiscount.setLayoutX(400);
                editDiscount.setLayoutY(150);
                parent.getChildren().add(editDiscount);
                name(editDiscount, category);
                detail1(editDiscount, category);
                detail2(editDiscount, category);
                detail3(editDiscount, category);

                Button button = new Button("Save and back");
                button.setPrefWidth(100);
                button.setLayoutX(150);
                button.setLayoutY(300);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);
                button.setOnMouseClicked(e -> {
                    ManagerMenu managerMenu = new ManagerMenu();
                    try {
                        managerMenu.showPersonalArea();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                });
            }

            private static void name(Pane editDiscount, Category category) {
                Label name = new Label("Name:" + "\n" + category.getName());
                name.setFont(new Font(15));
                name.setLayoutX(20);
                editDiscount.getChildren().add(name);

                Line line = new Line();
                line.setStartX(0);
                line.setEndX(400);
                line.setStartY(50);
                line.setEndY(50);
                editDiscount.getChildren().add(line);

                Button button = new Button("Edit");
                button.setLayoutX(350);
                button.setLayoutY(10);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);

                TextField textField = new TextField();
                textField.setPromptText("New name");
                textField.setLayoutX(200);
                textField.setLayoutY(10);
                editDiscount.getChildren().add(textField);
                button.setOnMouseClicked(e -> {
                    Label label = new Label();
                    label.setFont(new Font(10));
                    label.setLayoutX(200);
                    label.setLayoutY(35);
                    editDiscount.getChildren().add(label);
                    if (textField.getText().isEmpty()) {
                        label.setText("Complete for edit");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editCategory," + category.getName() + "," + "name," + textField.getText());
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

            private static void detail1(Pane editDiscount, Category category) {
                Label detail2 = new Label("Detail1:" + "\n" + category.getDetail1());
                detail2.setFont(new Font(15));
                detail2.setLayoutX(20);
                detail2.setLayoutY(50);
                editDiscount.getChildren().add(detail2);

                Line line = new Line();
                line.setStartX(0);
                line.setEndX(400);
                line.setStartY(100);
                line.setEndY(100);
                editDiscount.getChildren().add(line);

                Button button = new Button("Edit");
                button.setLayoutX(350);
                button.setLayoutY(60);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);

                TextField textField = new TextField();
                textField.setPromptText("New detail");
                textField.setLayoutX(200);
                textField.setLayoutY(60);
                editDiscount.getChildren().add(textField);
                button.setOnMouseClicked(e -> {
                    Label label = new Label();
                    label.setFont(new Font(10));
                    label.setLayoutX(200);
                    label.setLayoutY(85);
                    editDiscount.getChildren().add(label);
                    if (textField.getText().isEmpty()) {
                        label.setText("Complete for edit");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editCategory," + category.getName() + "," + "detail1," + textField.getText());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                detail2.setText("Detail1:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            private static void detail2(Pane editDiscount, Category category) {
                Label detail2 = new Label("Detail2:" + "\n" + category.getDetail2());
                detail2.setFont(new Font(15));
                detail2.setLayoutX(20);
                detail2.setLayoutY(100);
                editDiscount.getChildren().add(detail2);

                Line line = new Line();
                line.setStartX(0);
                line.setEndX(400);
                line.setStartY(150);
                line.setEndY(150);
                editDiscount.getChildren().add(line);

                Button button = new Button("Edit");
                button.setLayoutX(350);
                button.setLayoutY(110);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);

                TextField textField = new TextField();
                textField.setPromptText("New detail");
                textField.setLayoutX(200);
                textField.setLayoutY(110);
                editDiscount.getChildren().add(textField);
                button.setOnMouseClicked(e -> {
                    Label label = new Label();
                    label.setFont(new Font(10));
                    label.setLayoutX(200);
                    label.setLayoutY(135);
                    editDiscount.getChildren().add(label);
                    if (textField.getText().isEmpty()) {
                        label.setText("Complete for edit");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editCategory," + category.getName() + "," + "detail2," + textField.getText());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                detail2.setText("Detail2:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            private static void detail3(Pane editDiscount, Category category) {
                Label detail3 = new Label("Detail3:" + "\n" + category.getDetail3());
                detail3.setFont(new Font(15));
                detail3.setLayoutX(20);
                detail3.setLayoutY(150);
                editDiscount.getChildren().add(detail3);

                Line line = new Line();
                line.setStartX(0);
                line.setEndX(400);
                line.setStartY(200);
                line.setEndY(200);
                editDiscount.getChildren().add(line);

                Button button = new Button("Edit");
                button.setLayoutX(350);
                button.setLayoutY(160);
                button.setCursor(Cursor.HAND);
                editDiscount.getChildren().add(button);

                TextField textField = new TextField();
                textField.setPromptText("New detail");
                textField.setLayoutX(200);
                textField.setLayoutY(160);
                editDiscount.getChildren().add(textField);
                button.setOnMouseClicked(e -> {
                    Label label = new Label();
                    label.setFont(new Font(10));
                    label.setLayoutX(200);
                    label.setLayoutY(185);
                    editDiscount.getChildren().add(label);
                    if (textField.getText().isEmpty()) {
                        label.setText("Complete for edit");
                        label.setTextFill(Color.RED);
                    } else {
                        try {
                            dataOutputStream.writeUTF("editCategory," + category.getName() + "," + "detail3," + textField.getText());
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            if (dataInputStream.readUTF().equals("done")) {
                                label.setText("Done");
                                label.setTextFill(Color.GREEN);
                                detail3.setText("Detail3:" + "\n" + textField.getText());
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

        }

    }

    static class ManagerRequests {
        public static void showPage() throws IOException, ClassNotFoundException {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Requests");
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
                    new ManagerMenu().showPersonalArea();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
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
                    showFields(parent);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(updateList);
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
            ImageView personImage = ((Manager) objectInputStream.readObject()).getImageView();
            personImage.setFitWidth(70);
            personImage.setFitHeight(70);
            personImage.setLayoutX(320);
            personImage.setLayoutY(10);
            topMenu.getChildren().add(personImage);

            Label role = new Label("Manager");
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

            Label sender = new Label("Sender");
            sender.setFont(new Font(20));
            sender.setLayoutX(500);
            sender.setLayoutY(5);
            pane.getChildren().add(sender);

            Label delete = new Label("Delete");
            delete.setFont(new Font(20));
            delete.setLayoutX(700);
            delete.setLayoutY(5);
            pane.getChildren().add(delete);

            Label setCondition = new Label("Set Condition");
            setCondition.setFont(new Font(20));
            setCondition.setLayoutX(900);
            setCondition.setLayoutY(5);

            updateList(pane);

            pane.getChildren().add(setCondition);
        }

        private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
            int i = 1;
            dataOutputStream.writeUTF("getRequests");
            dataOutputStream.flush();
            ArrayList<Request> requests = (ArrayList<Request>) objectInputStream.readObject();
            for (Request allRequest : requests) {

                Label type = new Label(allRequest.getType());
                type.setFont(new Font(20));
                type.setLayoutX(10);
                type.setLayoutY(50 * i);
                pane.getChildren().add(type);

                Label condition = new Label(allRequest.getCondition());
                condition.setFont(new Font(20));
                condition.setLayoutX(300);
                condition.setLayoutY(50 * i);
                condition.setCursor(Cursor.HAND);
                pane.getChildren().add(condition);

                Label sender = new Label(allRequest.getSender().getUsername());
                sender.setFont(new Font(20));
                sender.setLayoutX(500);
                sender.setLayoutY(50 * i);
                pane.getChildren().add(sender);

                if (!allRequest.getCondition().equals("Unknown")) {
                    Button delete = new Button("Delete");
                    delete.setLayoutX(700);
                    delete.setLayoutY(50 * i);
                    delete.setCursor(Cursor.HAND);
                    delete.setStyle("-fx-background-color: #858585");
                    delete.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.flush();
                            dataOutputStream.writeUTF("deleteRequest");
                            objectOutputStream.writeObject(allRequest);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    pane.getChildren().add(delete);
                }

                if (allRequest.getCondition().equals("Unknown")) {
                    Image declineImage = new Image(Paths.get("src/main/java/Client.View/images/minus.png").toUri().toString());
                    ImageView decline = new ImageView(declineImage);
                    decline.setFitHeight(10);
                    decline.setFitWidth(50);
                    decline.setLayoutX(900);
                    decline.setLayoutY(55 * i);
                    decline.setCursor(Cursor.HAND);
                    decline.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.writeUTF("setRequestCondition,Decline");
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            objectOutputStream.writeObject(allRequest);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    pane.getChildren().add(decline);

                    Image acceptImage = new Image(Paths.get("src/main/java/Client.View/images/plus.jpg").toUri().toString());
                    ImageView accept = new ImageView(acceptImage);
                    accept.setFitHeight(30);
                    accept.setFitWidth(30);
                    accept.setLayoutX(970);
                    accept.setLayoutY(50 * i);
                    accept.setCursor(Cursor.HAND);
                    accept.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.writeUTF("setRequestCondition,Accept");
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            objectOutputStream.writeObject(allRequest);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    pane.getChildren().add(accept);
                }

                i++;
            }
        }
    }

    static class ManagerProducts {
        public static void showPage() throws IOException, ClassNotFoundException {
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            Pane parent = new Pane();
            parent.setStyle("-fx-background-color: #858585");
            Label label = new Label("Requests");
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
                    new ManagerMenu().showPersonalArea();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
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
                    showFields(parent);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            parent.getChildren().add(updateList);
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

//            ImageView personImage = ((Manager) LoginMenu.currentPerson).getImageView();
//            personImage.setFitWidth(70);
//            personImage.setFitHeight(70);
//            personImage.setLayoutX(320);
//            personImage.setLayoutY(10);
//            topMenu.getChildren().add(personImage);

            Label role = new Label("Manager");
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
            id.setFont(new Font(25));
            id.setLayoutX(10);
            id.setLayoutY(5);
            pane.getChildren().add(id);

            Label name = new Label("Name");
            name.setFont(new Font(25));
            name.setLayoutX(300);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label seller = new Label("Seller");
            seller.setFont(new Font(25));
            seller.setLayoutX(500);
            seller.setLayoutY(5);
            pane.getChildren().add(seller);

            Label category = new Label("Category");
            category.setFont(new Font(25));
            category.setLayoutX(700);
            category.setLayoutY(5);
            pane.getChildren().add(category);

            Label score = new Label("Score");
            score.setFont(new Font(25));
            score.setLayoutX(900);
            score.setLayoutY(5);
            pane.getChildren().add(score);

            Label delete = new Label("Delete");
            delete.setFont(new Font(25));
            delete.setLayoutX(1000);
            delete.setLayoutY(5);
            pane.getChildren().add(delete);

            updateList(pane);
        }

        private static void updateList(Pane pane) throws IOException, ClassNotFoundException {
            int i = 1;
            dataOutputStream.writeUTF("getProducts");
            dataOutputStream.flush();
            ArrayList<Product> allProducts = (ArrayList<Product>) objectInputStream.readObject();
            for (Product allProduct : allProducts) {
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

                Label seller = new Label(allProduct.getSeller().getUsername());
                seller.setFont(new Font(20));
                seller.setLayoutX(500);
                seller.setLayoutY(50 * i);
                pane.getChildren().add(seller);

                Label category = new Label(allProduct.getCategory().getName());
                category.setFont(new Font(20));
                category.setLayoutX(700);
                category.setLayoutY(50 * i);
                pane.getChildren().add(category);

                Label score = new Label(String.valueOf(allProduct.getScore()));
                score.setFont(new Font(20));
                score.setLayoutX(900);
                score.setLayoutY(50 * i);
                pane.getChildren().add(score);

                Button delete = new Button("Delete");
                delete.setStyle("-fx-background-color: #858585");
                delete.setCursor(Cursor.HAND);
                delete.setLayoutX(1000);
                delete.setLayoutY(50 * i);
                delete.setOnMouseClicked(e -> {
                    try {
                        dataOutputStream.writeUTF("deleteProduct," + allProduct.getProductID());
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                pane.getChildren().add(delete);

                i++;
            }
        }
    }
}