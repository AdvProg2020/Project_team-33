package Client.View.ProductPage;

import Client.Model.Cart;
import Client.Model.Product;
import Client.View.CommentsPage;
import Client.View.Menu;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Paths;

public class ProductPage {
    private static Cart staticCart;
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;
    private static String token = Menu.token;
//    private static ObjectInputStream objectInputStream = Menu.objectInputStream;
//    private static ObjectOutputStream objectOutputStream = Menu.objectOutputStream;

    public ProductPage() throws IOException {
    }

    public static void show(Product product) throws IOException {
        AnchorPane parent = new AnchorPane();
        parent.setPrefHeight(308.0);
        parent.setPrefWidth(308.0);
        parent.setStyle("-fx-background-color: #858585");
        makeImageBox(parent, product);
        secondPane(parent, product);
        Scene scene = new Scene(parent, 582.0, 346.0);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private static void makeImageBox(Pane parent, Product product) {
        Pane pane = new Pane();
        pane.setPrefHeight(346.0);
        pane.setPrefWidth(294.0);
        Image image = new Image(Paths.get("src/main/java/Client/view/images/digital.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setLayoutX(10);
        imageView.setLayoutY(25);
        pane.getChildren().add(imageView);
        pane.setStyle("-fx-background-color: Gainsboro");
        parent.getChildren().add(pane);
    }

    private static void secondPane(AnchorPane parent, Product product) throws IOException {
        Pane pane = new Pane();
        pane.setLayoutX(288.0);
        pane.setPrefHeight(346.0);
        pane.setPrefWidth(294.0);
        pane.setStyle("-fx-background-color: Gainsboro");

        Label name = new Label("Name: " + product.getName());
        name.setTextFill(Color.BLACK);
        name.setFont(new Font(19));
        name.setPrefHeight(41.0);
        name.setPrefWidth(294.0);
        name.setLayoutX(14.0);
        pane.getChildren().add(name);

        Label description = new Label("Description: " + product.getDescription());
        description.setTextFill(Color.BLACK);
        description.setFont(new Font(15));
        description.setPrefHeight(41.0);
        description.prefWidth(294.0);
        description.setLayoutY(41.0);
        description.setLayoutX(14.0);
        pane.getChildren().add(description);

        Label category = new Label("Category: " + product.getCategory());
        category.setTextFill(Color.BLACK);
        category.setFont(new Font(15));
        category.setLayoutX(14.0);
        category.setLayoutY(70.0);
        category.setPrefHeight(53.0);
        pane.getChildren().add(category);
//
//        dataOutputStream.writeUTF("getScoreForProduct," + product.getProductID() + "," + token);
//        dataOutputStream.flush();
//        String scoreNo = dataInputStream.readUTF();
        Label score = new Label("Average score: " + product.getScore());
        score.setTextFill(Color.YELLOW);
        score.setTextFill(Color.BLACK);
        score.setFont(new Font(15));
        score.setLayoutX(14.0);
        score.setLayoutY(123.0);
        score.prefHeight(27.0);
        score.prefWidth(105.0);
        pane.getChildren().add(score);

//        dataOutputStream.writeUTF("getNumberForProduct," + product.getProductID() + "," + token);
//        dataOutputStream.flush();
//        String numberNo = dataInputStream.readUTF();
        Label number = new Label("Number: " + product.getNumberOfProducts());
        number.setTextFill(Color.BLACK);
        number.setFont(new Font(15));
        number.setLayoutX(14.0);
        number.setLayoutY(150.0);
        number.setPrefHeight(41.0);
        number.setPrefWidth(198.0);
        pane.getChildren().add(number);

        Label money = new Label("Price: " + product.getMoney() + "$");
        money.setTextFill(Color.BLACK);
        money.setFont(new Font(15));
        money.setLayoutX(14.0);
        money.setLayoutY(191.0);
        money.setPrefWidth(198.0);
        money.setPrefHeight(41.0);
        pane.getChildren().add(money);

        createButtons(pane, product);

        parent.getChildren().add(pane);
    }

    private static void createButtons(Pane pane, Product product) {
        Button addToCartButton = new Button("Add to cart");
        addToCartButton.setLayoutX(21.0);
        addToCartButton.setLayoutY(246.0);
        addToCartButton.setPrefHeight(60.0);
        addToCartButton.setPrefWidth(126.0);
        addToCartButton.setStyle("-fx-background-color: Aqua");
        addToCartButton.setCursor(Cursor.HAND);

        addToCartButton.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("addToCart," + product.getProductID() + "," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button addComment = new Button("Add comment");
        addComment.setLayoutX(152.0);
        addComment.setLayoutY(246.0);
        addComment.setPrefHeight(60.0);
        addComment.setPrefWidth(126.0);
        addComment.setStyle("-fx-background-color: Aquamarine");

        addComment.setOnMouseClicked(e -> {
            try {
                CommentsPage.show(product);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button back = new Button("Back");
        back.setTextFill(Color.WHITE);
        back.setLayoutX(21.0);
        back.setLayoutY(312.0);
        back.setPrefHeight(27.0);
        back.setPrefWidth(257.0);
        back.setStyle("-fx-background-color: Black");

        back.setOnMouseClicked(e -> {
            try {
                ProductsPage.show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        Button setScore = new Button("Set score");
        setScore.setStyle("-fx-background-color:Green ");
        setScore.setCursor(Cursor.HAND);
        setScore.setTextFill(Color.BLACK);
        setScore.setFont(new Font(15));
        setScore.setLayoutX(180.0);
        setScore.setLayoutY(118.0);
        setScore.prefHeight(27.0);
        setScore.prefWidth(105.0);
        setScore.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("setScore," + product.getProductID() + "," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String[] splitInput = new String[2];
            try {
                splitInput = dataInputStream.readUTF().split("-");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (splitInput[0].equals("1")) {
                if (splitInput[1].equals("0")) {
                    score(product);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You have to buy it first");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You have to buy it first");
                alert.showAndWait();
            }
        });
        pane.getChildren().addAll(addToCartButton, addComment, back, setScore);
    }

    private static void score(Product product) {
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: grey");

        Button one = new Button();
        one.setStyle("-fx-background-radius: 40");
        one.setStyle("-fx-background-color: RED");
        one.setLayoutX(10);
        one.setCursor(Cursor.HAND);

        Button two = new Button();
        two.setStyle("-fx-background-radius: 40");
        two.setStyle("-fx-background-color: Orange");
        two.setLayoutX(50);
        two.setCursor(Cursor.HAND);

        Button three = new Button();
        three.setStyle("-fx-background-radius: 40");
        three.setStyle("-fx-background-color: Yellow");
        three.setLayoutX(90);
        three.setCursor(Cursor.HAND);

        Button four = new Button();
        four.setStyle("-fx-background-radius: 40");
        four.setStyle("-fx-background-color: LawnGreen");
        four.setLayoutX(130);
        four.setCursor(Cursor.HAND);

        Button five = new Button();
        five.setStyle("-fx-background-radius: 40");
        five.setStyle("-fx-background-color: SeaGreen");
        five.setLayoutX(170);
        five.setCursor(Cursor.HAND);

        one.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("scoreController,1," + product.getProductID() + "," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if (dataInputStream.readUTF().equals("done")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Done");
                    alert.showAndWait();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        two.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("scoreController,2," + product.getProductID() + "," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Done");
            alert.showAndWait();
        });

        three.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("scoreController,3," + product.getProductID() + "," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if (dataInputStream.readUTF().equals("done")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Done");
                    alert.showAndWait();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        four.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("scoreController,4," + product.getProductID() + "," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                if (dataInputStream.readUTF().equals("done")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Done");
                    alert.showAndWait();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        five.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("scoreController,5," + product.getProductID() + "," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                if (dataInputStream.readUTF().equals("done")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Done");
                    alert.showAndWait();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        parent.getChildren().addAll(one, two, three, four, five);

        Stage stage = new Stage();
        Scene scene = new Scene(parent, 200, 100);
        stage.setScene(scene);
        stage.show();
    }
}
