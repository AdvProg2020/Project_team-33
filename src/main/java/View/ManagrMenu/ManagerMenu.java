package View.ManagrMenu;

import Controller.ManagerController.ManagerAbilitiesController;
import Controller.PersonController;
import Model.Users.Buyer;
import Model.Users.Person;
import Model.Users.Seller;
import View.LoginAndRegister.LoginMenu;
import View.Menu;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

public class ManagerMenu extends Menu {

    //    @Override
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
            ManagerPersonalInfoAbilities.editPersonalInfo();
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

        Image image = new Image(Paths.get("src/main/java/view/images/member.png").toUri().toString());
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
            ManagerAllMembersAbilities.showPage();
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

        Image image = new Image(Paths.get("src/main/java/view/images/giftCard.png").toUri().toString());
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
        giftCard.setOnMouseClicked(e -> ManagerAllGiftCodes.showPage());
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

        Image image = new Image(Paths.get("src/main/java/view/images/category.png").toUri().toString());
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

        categories.setOnMouseClicked(e -> ManagerCategories.showPage());
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

        Image image = new Image(Paths.get("src/main/java/view/images/request.png").toUri().toString());
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

        requests.setOnMouseClicked(e -> ManagerRequests.showPage());
        parent.getChildren().add(requests);
    }

    private void createProductsPagePanel(Pane parent) {
        Pane requests = new Pane();
        requests.setStyle("-fx-background-color: #bababa");
        requests.setPrefWidth(240);
        requests.setPrefHeight(70);
        requests.setLayoutX(890);
        requests.setLayoutY(350);
        requests.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/request.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        requests.getChildren().add(imageView);


        Label balanceLabel = new Label("Products");
        balanceLabel.setFont(new Font(20));
        balanceLabel.setLayoutX(60);
        balanceLabel.setLayoutY(10);
        requests.getChildren().add(balanceLabel);

        Label balanceSecondLabel = new Label("Manage products");
        balanceSecondLabel.setFont(new Font(12));
        balanceSecondLabel.setLayoutX(60);
        balanceSecondLabel.setLayoutY(40);
        requests.getChildren().add(balanceSecondLabel);

//        requests.setOnMouseClicked(e -> ManagerRequests.showPage());
        parent.getChildren().add(requests);
    }

    private void makeTopOfMenu(Pane parent) {
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

        Label role = new Label("Manager");
        role.setFont(new Font(30));
        role.setLayoutX(640);
        role.setLayoutY(30);
        role.setTextFill(Color.WHITE);
        topMenu.getChildren().add(role);


        parent.getChildren().add(topMenu);
    }

    static class ManagerPersonalInfoAbilities {
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

            Label role = new Label("Manager");
            role.setFont(new Font(30));
            role.setLayoutX(640);
            role.setLayoutY(30);
            role.setTextFill(Color.WHITE);
            topMenu.getChildren().add(role);


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

            Button button = new Button("Save and back");
            button.setPrefWidth(100);
            button.setLayoutX(150);
            button.setLayoutY(300);
            button.setCursor(Cursor.HAND);
            personalInfo.getChildren().add(button);
            button.setOnMouseClicked(e -> {
                ManagerMenu managerMenu = new ManagerMenu();
                managerMenu.showPersonalArea();
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
                    ManagerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "name", textField.getText());
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
                    ManagerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "family", textField.getText());
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
                        ManagerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "email", textField.getText());
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
                        ManagerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "phone", textField.getText());
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
                        ManagerAbilitiesController.editPersonalInfo(LoginMenu.currentPerson, "password", textField.getText());
                        label.setText("Done");
                        label.setTextFill(Color.GREEN);
                        password.setText("Password:" + "\n" + textField.getText());
                    }
                }
            });
        }


    }

    static class ManagerAllMembersAbilities {
        public static void showPage() {
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
            backButton.setOnMouseClicked(e -> new ManagerMenu().showPersonalArea());
            parent.getChildren().add(backButton);

            Button updateList = new Button("Update list");
            updateList.setLayoutX(400);
            updateList.setLayoutY(110);
            updateList.setStyle("-fx-background-color: #bababa");
            updateList.setCursor(Cursor.HAND);
            updateList.setOnMouseClicked(e -> {
                showFields(parent);
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

            Label role = new Label("Manager");
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

            Label username = new Label("Username");
            username.setFont(new Font(20));
            username.setLayoutX(10);
            username.setLayoutY(5);
            pane.getChildren().add(username);
            Label name = new Label("Name");
            name.setFont(new Font(20));
            name.setLayoutX(300);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label family = new Label("Family");
            family.setFont(new Font(20));
            family.setLayoutX(500);
            family.setLayoutY(5);
            pane.getChildren().add(family);

            Label phone = new Label("Phone");
            phone.setFont(new Font(20));
            phone.setLayoutX(700);
            phone.setLayoutY(5);
            pane.getChildren().add(phone);

            Label email = new Label("Email");
            email.setFont(new Font(20));
            email.setLayoutX(900);
            email.setLayoutY(5);
            pane.getChildren().add(email);

            Label delete = new Label("Delete");
            delete.setFont(new Font(20));
            delete.setLayoutX(1150);
            delete.setLayoutY(5);
            pane.getChildren().add(delete);
            updateList(pane);
            parent.getChildren().add(pane);


        }

        private static void updateList(Pane parent) {
            int i = 1;
            for (Person allMember : ManagerAbilitiesController.getAllMembers()) {
                if (LoginMenu.currentPerson == allMember) {
                    continue;
                }
                Label username = new Label(allMember.getUsername());
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

                    if (allMember instanceof Seller) {
                        label.setText("Seller");
                    } else if (allMember instanceof Buyer) {
                        label.setText("Buyer");

                    } else {
                        label.setText("Manager");

                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(username);

                Label name = new Label(allMember.getName());
                name.setFont(new Font(20));
                name.setTextFill(Color.BLACK);

                name.setLayoutX(300);
                name.setLayoutY(50 * i);
                name.setCursor(Cursor.HAND);
                name.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);


                    if (allMember instanceof Seller) {
                        label.setText("Seller");
                    } else if (allMember instanceof Buyer) {
                        label.setText("Buyer");

                    } else {
                        label.setText("Manager");

                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(name);

                Label family = new Label(allMember.getFamily());
                family.setFont(new Font(20));
                family.setTextFill(Color.BLACK);

                family.setLayoutX(500);
                family.setLayoutY(50 * i);
                family.setCursor(Cursor.HAND);
                family.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);


                    if (allMember instanceof Seller) {
                        label.setText("Seller");
                    } else if (allMember instanceof Buyer) {
                        label.setText("Buyer");

                    } else {
                        label.setText("Manager");

                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(family);

                Label phone = new Label(allMember.getPhone());
                phone.setFont(new Font(20));
                phone.setTextFill(Color.BLACK);

                phone.setLayoutX(700);
                phone.setLayoutY(50 * i);
                phone.setCursor(Cursor.HAND);

                phone.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);


                    if (allMember instanceof Seller) {
                        label.setText("Seller");
                    } else if (allMember instanceof Buyer) {
                        label.setText("Buyer");

                    } else {
                        label.setText("Manager");

                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(phone);

                Label email = new Label(allMember.getEmail());
                email.setFont(new Font(20));
                email.setTextFill(Color.BLACK);

                email.setLayoutX(900);
                email.setLayoutY(50 * i);
                email.setCursor(Cursor.HAND);
                email.setOnMouseClicked(e -> {
                    Pane pane = new Pane();
                    Label label = new Label();
                    label.setFont(new Font(25));
                    label.setTextFill(Color.BLACK);
                    pane.getChildren().add(label);


                    if (allMember instanceof Seller) {
                        label.setText("Seller");
                    } else if (allMember instanceof Buyer) {
                        label.setText("Buyer");

                    } else {
                        label.setText("Manager");

                    }
                    Scene scene = new Scene(pane, 200, 200);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });
                parent.getChildren().add(email);

                if (LoginMenu.currentPerson == PersonController.mainManager) {
                    Button button = new Button("Delete");
                    button.setStyle("-fx-background-color: #858585");
                    button.setCursor(Cursor.HAND);
                    button.setLayoutX(1150);
                    button.setLayoutY(50 * i);
                    button.setOnMouseClicked(e -> {
                        ManagerAbilitiesController.deleteUser(allMember);
                    });
                    parent.getChildren().add(button);
                }
                i++;
            }
        }


    }

    static class ManagerAllGiftCodes {

        public static void showPage() {
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
            backButton.setOnMouseClicked(e -> new ManagerMenu().showPersonalArea());
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

            Label role = new Label("Manager");
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

            Label username = new Label("Serial");
            username.setFont(new Font(20));
            username.setLayoutX(10);
            username.setLayoutY(5);
            pane.getChildren().add(username);
            Label name = new Label("Date of start");
            name.setFont(new Font(20));
            name.setLayoutX(300);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label family = new Label("Date of end");
            family.setFont(new Font(20));
            family.setLayoutX(500);
            family.setLayoutY(5);
            pane.getChildren().add(family);

            Label phone = new Label("Percent");
            phone.setFont(new Font(20));
            phone.setLayoutX(700);
            phone.setLayoutY(5);
            pane.getChildren().add(phone);

            Label email = new Label("Edit");
            email.setFont(new Font(20));
            email.setLayoutX(900);
            email.setLayoutY(5);
            pane.getChildren().add(email);

            Label delete = new Label("Delete");
            delete.setFont(new Font(20));
            delete.setLayoutX(1100);
            delete.setLayoutY(5);
            pane.getChildren().add(delete);
        }

        private static void updateList() {

        }
    }

    static class ManagerRequests {
        public static void showPage() {
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
            backButton.setOnMouseClicked(e -> new ManagerMenu().showPersonalArea());
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

            Label role = new Label("Manager");
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

            Label username = new Label("Type");
            username.setFont(new Font(20));
            username.setLayoutX(10);
            username.setLayoutY(5);
            pane.getChildren().add(username);
            Label name = new Label("Condition");
            name.setFont(new Font(20));
            name.setLayoutX(300);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label family = new Label("Sender");
            family.setFont(new Font(20));
            family.setLayoutX(500);
            family.setLayoutY(5);
            pane.getChildren().add(family);

            Label phone = new Label("Delete");
            phone.setFont(new Font(20));
            phone.setLayoutX(700);
            phone.setLayoutY(5);
            pane.getChildren().add(phone);

            Label email = new Label("Set Condition");
            email.setFont(new Font(20));
            email.setLayoutX(900);
            email.setLayoutY(5);
            pane.getChildren().add(email);

        }

//        private static void showAllRequests(Pane parent) {
//            parent.setStyle("-fx-background-color: #858585");
//            Label label = new Label("Type");
//            label.setLayoutX(100);
//            label.setLayoutY(90);
//            label.setFont(new Font(20));
//            parent.getChildren().add(label);
//
//            Label label1 = new Label("Condition");
//            label1.setLayoutX(300);
//            label1.setLayoutY(90);
//            label1.setFont(new Font(20));
//            parent.getChildren().add(label1);
//
//            Label label2 = new Label("Sender");
//            label2.setLayoutX(500);
//            label2.setLayoutY(90);
//            label2.setFont(new Font(20));
//            parent.getChildren().add(label2);
//
//            Label label3 = new Label("Delete");
//            label3.setLayoutX(700);
//            label3.setLayoutY(90);
//            label3.setFont(new Font(20));
//            parent.getChildren().add(label3);
//
//            Label label4 = new Label("Set Condition");
//            label4.setLayoutX(900);
//            label4.setLayoutY(90);
//            label4.setFont(new Font(20));
//            parent.getChildren().add(label4);
//
//
//            Scene scene = new Scene(parent, 1280, 660);
//            Menu.stage.setScene(scene);
//            Menu.stage.show();
//
//
//        }

        private static void updateList() {

        }
    }

    static class ManagerCategories {
        public static void showPage() {
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
            backButton.setOnMouseClicked(e -> new ManagerMenu().showPersonalArea());
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

            Label role = new Label("Manager");
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

            Label username = new Label("Name");
            username.setFont(new Font(20));
            username.setLayoutX(10);
            username.setLayoutY(5);
            pane.getChildren().add(username);
            Label name = new Label("Edit");
            name.setFont(new Font(20));
            name.setLayoutX(300);
            name.setLayoutY(5);
            pane.getChildren().add(name);

            Label family = new Label("Delete");
            family.setFont(new Font(20));
            family.setLayoutX(500);
            family.setLayoutY(5);
            pane.getChildren().add(family);

        }

        private static void updateList() {

        }
    }

}