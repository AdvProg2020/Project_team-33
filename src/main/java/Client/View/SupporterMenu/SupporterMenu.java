package Client.View.SupporterMenu;

import Client.Model.Chat;
import Client.Model.Users.Person;
import Server.Model.Users.Supporter;
import Client.View.Menu;
import com.google.gson.Gson;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
        makeTopOfMenu(parent);
        createChatPanel(parent);

        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private void createChatPanel(Pane parent) throws IOException {
        dataOutputStream.writeUTF("getPerson");
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        Person person = gson.fromJson(json.substring(7), Person.class);

        VBox vBox = new VBox();
        dataOutputStream.writeUTF("getBuyerSupporterChat," + person.getUsername());
        dataOutputStream.flush();
        int size = Integer.parseInt(dataInputStream.readUTF());
        ArrayList<Chat> allChats = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            Gson gson1 = new Gson();
            Chat chat = gson1.fromJson(dataInputStream.readUTF(), Chat.class);
            allChats.add(chat);
        }

        for (Chat chat : allChats) {
            HBox hBox = new HBox();
            Label name = new Label();
            Label message = new Label();
            name.setFont(new Font(10));
            message.setFont(new Font(18));

            name.setText(chat.getPerson().getName());
            message.setText(chat.getMessage());

            hBox.getChildren().addAll(name, message);

            if (chat.getPerson().getUsername().equals(person.getUsername())) {
                hBox.setStyle("-fx-background-color: DodgerBlue");
            } else {
                hBox.setStyle("-fx-background-color: AliceBlue");
            }

            vBox.getChildren().add(hBox);
        }

        parent.getChildrenUnmodifiable().add(vBox);
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
        Gson gson=new Gson();
        //ToDo
        Supporter supporter = (Supporter)gson.fromJson(dataInputStream.readUTF(), Supporter.class) ;

        ImageView personImage = supporter.getImageView();
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

        Label role = new Label("Supporter");
        role.setFont(new Font("Ink Free", 30));
        role.setLayoutX(640);
        role.setLayoutY(30);
        role.setTextFill(Color.WHITE);
        topMenu.getChildren().add(role);

        parent.getChildren().add(topMenu);
    }

}
