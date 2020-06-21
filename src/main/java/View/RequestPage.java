package View;

import View.ManagrMenu.ShowAndEditPersonalInfoForManager;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class RequestPage extends Application {
    public static void show() {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label label = new Label("Requests Page");
        label.setLayoutX(120);
        label.setLayoutY(90);
        label.setFont(new Font(30));
        parent.getChildren().add(label);
        showAllRequestsPage(parent);


        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private static void showAllRequestsPage(Pane parent) {
        Pane personalInfo = new Pane();
        personalInfo.setStyle("-fx-background-color: #bababa");
        personalInfo.setPrefWidth(210);
        personalInfo.setPrefHeight(70);
        personalInfo.setLayoutX(120);
        personalInfo.setLayoutY(200);
        personalInfo.setCursor(Cursor.HAND);

        Image image = new Image(Paths.get("src/main/java/view/images/request.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(10);
        personalInfo.getChildren().add(imageView);
        parent.getChildren().add(personalInfo);

        Label personalInfoLabel = new Label("All Requests");
        personalInfoLabel.setFont(new Font(20));
        personalInfoLabel.setLayoutX(60);
        personalInfoLabel.setLayoutY(10);
        personalInfo.getChildren().add(personalInfoLabel);

        Label personalInfoSecondLabel = new Label("accept,decline requests");
        personalInfoSecondLabel.setFont(new Font(12));
        personalInfoSecondLabel.setLayoutX(60);
        personalInfoSecondLabel.setLayoutY(40);
        personalInfo.getChildren().add(personalInfoSecondLabel);
        personalInfo.setOnMouseClicked(e -> {
            showAllRequests();
        });

    }

    private static void showAllRequests() {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        Label label = new Label("Type");
        label.setLayoutX(100);
        label.setLayoutY(90);
        label.setFont(new Font(20));
        parent.getChildren().add(label);

        Label label1 = new Label("Condition");
        label1.setLayoutX(300);
        label1.setLayoutY(90);
        label1.setFont(new Font(20));
        parent.getChildren().add(label1);

        Label label2 = new Label("Sender");
        label2.setLayoutX(500);
        label2.setLayoutY(90);
        label2.setFont(new Font(20));
        parent.getChildren().add(label2);

        Label label3 = new Label("Delete");
        label3.setLayoutX(700);
        label3.setLayoutY(90);
        label3.setFont(new Font(20));
        parent.getChildren().add(label3);

        Label label4 = new Label("Set Condition");
        label4.setLayoutX(900);
        label4.setLayoutY(90);
        label4.setFont(new Font(20));
        parent.getChildren().add(label4);


        Scene scene = new Scene(parent, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private static void updateList() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        showAllRequests();
    }
}
