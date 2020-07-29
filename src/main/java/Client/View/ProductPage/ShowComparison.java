package Client.View.ProductPage;

import Client.View.Menu;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ShowComparison {
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;
    private static String token = Menu.token;

    public void show(String id1, String id2) throws IOException {

        Stage comparison = new Stage();

        Pane parent = new Pane();

        parent.setPrefHeight(400);
        parent.setPrefWidth(600);

        Label label = new Label("         First Product                    Second Product");
        label.setStyle("-fx-background-color: #232f3e");
        label.setTextFill(Color.GREY);
        label.setPrefHeight(56);
        label.setPrefWidth(600);
        label.setFont(new Font(25));
        parent.getChildren().add(label);

        updatePanes(parent, id1, id2);
        comparison.setTitle("Comparison Page");
        Scene scene = new Scene(parent, 600, 400);
        comparison.setScene(scene);
        comparison.showAndWait();
    }

    private void updatePanes(Pane parent, String product1, String product2) throws IOException {
        dataOutputStream.writeUTF("getProductById," + product1 + "," + token);
        dataOutputStream.flush();
        String[] first = dataInputStream.readUTF().split(",");

        Pane pane1 = new Pane();
        pane1.setLayoutY(56);
        pane1.setStyle("-fx-background-color: #bababa");

        Label name = new Label("Name:   " + first[0]);
        name.setFont(new Font(20));
        name.setLayoutX(6);
        name.setLayoutY(10);
        name.setTextFill(Color.BLACK);
        System.out.println(name.getText());

        Label category = new Label("Category:   " + first[1]);
        category.setFont(new Font(20));
        category.setLayoutX(6);
        category.setLayoutY(60);
        category.setTextFill(Color.BLACK);

        Label score = new Label("Average Score: " + first[2]);
        score.setFont(new Font(20));
        score.setLayoutX(6);
        score.setLayoutY(110);
        score.setTextFill(Color.BLACK);

        Label price = new Label("Price:   " + first[3]);
        price.setFont(new Font(20));
        price.setLayoutX(6);
        price.setLayoutY(160);
        price.setTextFill(Color.BLACK);

        Label description = new Label("Description:   " + first[4]);
        description.setFont(new Font(20));
        description.setLayoutX(6);
        description.setLayoutY(210);
        description.setTextFill(Color.BLACK);

        Label number = new Label("Number:   " + first[5]);
        number.setFont(new Font(20));
        number.setLayoutX(6);
        number.setLayoutY(260);
        number.setTextFill(Color.BLACK);

        Line line = new Line();
        line.setLayoutX(15);
        line.setLayoutY(-100);
        line.setStartX(285);
        line.setStartY(444);
        line.setEndX(285);

        pane1.getChildren().addAll(name, category, score, price, description, number, line);

        parent.getChildren().add(pane1);

        dataOutputStream.writeUTF("getProductById," + product2 + "," + token);
        dataOutputStream.flush();
        String[] second = dataInputStream.readUTF().split(",");

        Pane pane2 = new Pane();
        pane2.setLayoutY(56);
        pane2.setLayoutX(300);
        pane2.setPrefHeight(344);
        pane2.setPrefWidth(300);
        pane2.setStyle("-fx-background-color: #bababa");

        Label name1 = new Label("Name:   " + second[0]);
        name1.setFont(new Font(20));
        name1.setLayoutX(6);
        name1.setLayoutY(10);
        name1.setTextFill(Color.BLACK);

        Label category1 = new Label("Category:   " + second[1]);
        category1.setFont(new Font(20));
        category1.setLayoutX(6);
        category1.setLayoutY(60);
        category1.setTextFill(Color.BLACK);

        Label score1 = new Label("Average Score: " + second[2]);
        score1.setFont(new Font(20));
        score1.setLayoutX(6);
        score1.setLayoutY(110);
        score1.setTextFill(Color.BLACK);

        Label price1 = new Label("Price:   " + second[3]);
        price1.setFont(new Font(20));
        price1.setLayoutX(6);
        price1.setLayoutY(160);
        price1.setTextFill(Color.BLACK);

        Label description1 = new Label("Description:   " + second[4]);
        description1.setFont(new Font(20));
        description1.setLayoutX(6);
        description1.setLayoutY(210);
        description1.setTextFill(Color.BLACK);

        Label number1 = new Label("Number:   " + second[5]);
        number1.setFont(new Font(20));
        number1.setLayoutX(6);
        number1.setLayoutY(260);
        number1.setTextFill(Color.BLACK);

        pane2.getChildren().addAll(name1, category1, score1, price1, description1, number1);

        parent.getChildren().add(pane2);
    }

}
