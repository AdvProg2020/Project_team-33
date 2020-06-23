package View;

import View.Menu;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class AuctionPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        show();
    }

    public static void show() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        makeTopOfPage(parent);
        makeCategoryView(parent);
        setProductsInPage(parent);
        createSortPanel(parent);
        scrollPane.setContent(parent);

        Scene scene = new Scene(scrollPane, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private static void makeTopOfPage(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #232f3e");
        pane.setPrefWidth(1280);
        pane.setPrefHeight(100);
        createImages(pane);
        createSearch(pane);
        createCategoryChoiceBox(pane);
        createFilterPanel(parent);

        parent.getChildren().add(pane);
    }

    private static void createImages(Pane pane) {
        Image mainMenuImage = new Image(Paths.get("src/main/java/view/images/mainMenu.png").toUri().toString());
        ImageView mainMenu = new ImageView(mainMenuImage);
        mainMenu.setFitWidth(70);
        mainMenu.setFitHeight(70);
        mainMenu.setLayoutY(10);
        mainMenu.setCursor(Cursor.HAND);
        mainMenu.setOnMouseClicked(e -> {
            try {
                Menu.executeMainMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pane.getChildren().add(mainMenu);

        Image cart = new Image(Paths.get("src/main/java/view/images/cart.png").toUri().toString());
        ImageView cartImage = new ImageView(cart);
        cartImage.setFitWidth(70);
        cartImage.setFitHeight(70);
        cartImage.setLayoutX(1200);
        cartImage.setLayoutY(10);
        cartImage.setCursor(Cursor.HAND);
        cartImage.setOnMouseClicked(e -> {

        });
        pane.getChildren().add(cartImage);
    }

    private static void createSearch(Pane pane) {
        TextField searchTextField = new TextField();
        searchTextField.setPromptText("Search by name");
        searchTextField.setLayoutX(500);
        searchTextField.setLayoutY(50);
        searchTextField.setPrefWidth(300);
        pane.getChildren().add(searchTextField);

        Button searchButton = new Button("Search");
        searchButton.setLayoutX(445);
        searchButton.setLayoutY(50);
        pane.getChildren().add(searchButton);
    }



}
