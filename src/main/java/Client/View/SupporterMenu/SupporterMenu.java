package Client.View.SupporterMenu;

import Client.Model.Chat;
import Client.Model.Users.Person;
import Client.Model.Users.Buyer;
import Client.Model.Users.Supporter;
import Client.View.Menu;
import com.google.gson.Gson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SupporterMenu extends Menu {
    private static String buyer = "-";
    private static Person loginSupporter;

    public void show() throws IOException, ClassNotFoundException {
        dataOutputStream.writeUTF("getPerson," + token);
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        loginSupporter = gson.fromJson(json.substring(10), Person.class);
        dataOutputStream.writeUTF("setOnline id-" + loginSupporter.getUsername() + "-yes," + token);
        dataOutputStream.flush();
        showPersonalArea();
    }

    public void showPersonalArea() throws IOException, ClassNotFoundException {
        Pane parent = new Pane();
        parent.setOnMouseClicked(e -> {
            try {
                createChatPanel(parent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        parent.setStyle("-fx-background-color: #858585");
        Label label = new Label("Your Account");
        label.setFont(new Font(30));
        label.setLayoutX(90);
        label.setLayoutY(120);
        Button updateList = new Button("Update list");
        updateList.setLayoutX(400);
        updateList.setLayoutY(110);
        updateList.setStyle("-fx-background-color: #bababa");
        updateList.setCursor(Cursor.HAND);
        updateList.setOnMouseClicked(e -> {
            try {
                createChooseBuyerBox(parent);
                createChatPanel(parent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        parent.getChildren().addAll(label, updateList);
        makeTopOfMenu(parent);
        createChooseBuyerBox(parent);
        createChatPanel(parent);
        createSendMessageBox(parent);

        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private void createChooseBuyerBox(Pane parent) throws IOException {
        dataOutputStream.writeUTF("getPerson," + token);
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        loginSupporter = gson.fromJson(json.substring(10), Person.class);
        dataOutputStream.writeUTF("getAllBuyersWithSupporter," + loginSupporter.getUsername() + "," + token);
        dataOutputStream.flush();
        int size = Integer.parseInt(dataInputStream.readUTF());
        ArrayList<String> userNames = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            String username = dataInputStream.readUTF();
            userNames.add(username);
        }

        Label l = new Label("choose your Buyer");
        l.setFont(new Font(20));
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(userNames));
        choiceBox.setLayoutY(50);
        choiceBox.setLayoutX(40);
        Pane pane = new Pane();
        pane.getChildren().addAll(l, choiceBox);
        pane.setLayoutX(950);
        pane.setLayoutY(160);

        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                buyer = userNames.get(new_value.intValue());
            }
        });

        parent.getChildren().add(pane);
    }


    private void createChatPanel(Pane parent) throws IOException {
        VBox vBox = new VBox();

        if (!buyer.equals("-")) {
            dataOutputStream.writeUTF("getSupporterBuyerChat," + buyer + "," + token);
            dataOutputStream.flush();
            int size = Integer.parseInt(dataInputStream.readUTF());
            ArrayList<String> allChats = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                String chat = dataInputStream.readUTF();
                allChats.add(chat);
            }

            vBox.getChildren().clear();
            vBox.setLayoutY(150);
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

                if (splitInput[1].equals(loginSupporter.getUsername())) {
                    hBox.setStyle("-fx-background-color: DodgerBlue");
                    hBox.setPrefWidth(300);
                    hBox.setLayoutX(350);
                } else {
                    hBox.setStyle("-fx-background-color: AliceBlue");
                    hBox.setPrefWidth(300);
                }

                pane.getChildren().add(hBox);

                vBox.getChildren().add(pane);
            }
        }

        parent.getChildren().add(vBox);
    }


    private void createSendMessageBox(Pane parent) {
        Pane sendMessagePanel = new Pane();
        sendMessagePanel.setLayoutY(580);
        sendMessagePanel.setStyle("-fx-background-color: #858585");

        TextArea textArea = new TextArea();
        textArea.setPromptText("write here...");
        textArea.setLayoutX(18.0);
        textArea.setPrefHeight(59);
        textArea.setPrefWidth(700);

        Button button = new Button();
        button.setLayoutX(750);
        button.setLayoutY(17);
        button.setPrefWidth(37);
        button.setPrefHeight(27);

//        Image image = new Image(Paths.get("src/main/java/Client/View/images/blue-plus-icon.png").toUri().toString());
//        ImageView imageView = new ImageView(image);
//        imageView.setFitWidth(50);
//        imageView.setFitHeight(50);
//        imageView.setLayoutY(10);

        button.setOnMouseClicked(e -> {
            if (!textArea.getText().isEmpty() && !buyer.equals("-")) {
                textArea.setStyle("-fx-border-color: 858585");
                String chat = textArea.getText();
                try {
                    dataOutputStream.writeUTF("sendMessageSupporterBuyer," + buyer + "," + chat + "," + token);
                    dataOutputStream.flush();
                    button.setStyle("-fx-border-color: 858585");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    createChatPanel(parent);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                textArea.setStyle("-fx-border-color: RED");
            }
        });

        sendMessagePanel.getChildren().addAll(textArea, button);
        parent.getChildren().add(sendMessagePanel);
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

//        ImageView personImage = ((Supporter) loginSupporter).getImageView();
//        personImage.setFitWidth(70);
//        personImage.setFitHeight(70);
//        personImage.setLayoutX(320);
//        personImage.setLayoutY(10);
//        topMenu.getChildren().add(personImage);
//
//        ChoiceBox choiceBox = new ChoiceBox();
//        choiceBox.getItems().add("Unknown");
//        choiceBox.getItems().add("Man");
//        choiceBox.getItems().add("Woman");
//        choiceBox.setLayoutX(320);
//        choiceBox.setLayoutY(85);
//        choiceBox.setOnAction(e -> {
//            System.out.println(choiceBox.getSelectionModel().getSelectedIndex());
//            if (choiceBox.getSelectionModel().getSelectedIndex() == 0) {
//                try {
//                    dataOutputStream.writeUTF("unknown," + token);
//                    dataOutputStream.flush();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//                try {
//                    show();
//                } catch (IOException | ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 1) {
//                try {
//                    dataOutputStream.writeUTF("man," + token);
//                    dataOutputStream.flush();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//                try {
//                    show();
//                } catch (IOException | ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//            } else if (choiceBox.getSelectionModel().getSelectedIndex() == 2) {
//                try {
//                    dataOutputStream.writeUTF("woman," + token);
//                    dataOutputStream.flush();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//                try {
//                    show();
//                } catch (IOException | ClassNotFoundException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//        topMenu.getChildren().add(choiceBox);

        Label role = new Label("Supporter");
        role.setFont(new Font("Ink Free", 30));
        role.setLayoutX(640);
        role.setLayoutY(30);
        role.setTextFill(Color.WHITE);
        topMenu.getChildren().add(role);

        parent.getChildren().add(topMenu);
    }

}
