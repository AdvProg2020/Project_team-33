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

    private static void createCategoryChoiceBox(Pane pane) {
        ChoiceBox category = new ChoiceBox();
        category.setLayoutX(805);
        category.setLayoutY(50);
        category.getItems().add("All categories");
        category.getItems().add("choice1");
        category.getItems().add("choice2");
        category.getItems().add("choice3");
        category.getItems().add("choice4");
        category.getItems().add("choice5");
        category.getItems().add("choice6");
        category.getItems().add("choice7");
        category.getItems().add("choice8");
        category.setValue("All categories");
        pane.getChildren().add(category);
    }

    private static void makeCategoryView(Pane parent) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #bababa");
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(120);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefSize(265, 500);

        ListView listView = new ListView();
        listView.getItems().add("choice1");
        listView.getItems().add("choice2");
        listView.getItems().add("choice3");
        listView.getItems().add("choice4");
        listView.getItems().add("choice5");
        listView.getItems().add("choice6");
        listView.getItems().add("choice7");
        listView.getItems().add("choice8");
        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().add("mobile");
        listView.getItems().add(choiceBox);
        scrollPane.setContent(listView);
        listView.setPrefHeight(500);
        listView.setCursor(Cursor.HAND);


        parent.getChildren().add(scrollPane);
    }

    private static void updateCategoryList(ListView listView) {
        ChoiceBox choiceBox = new ChoiceBox();
        listView.getItems().add(choiceBox);
        listView.getItems().add("choice2");
        listView.getItems().add("choice3");
        listView.getItems().add("choice4");
        listView.getItems().add("choice5");
        listView.getItems().add("choice6");
        listView.getItems().add("choice7");
        listView.getItems().add("choice8");
    }

    private static void showProductsWithCategoryFilter() {

    }

    private static void setProductsInPage(Pane parent) {
        Pane pane;
//        pane = new Pane();
//        pane.setStyle("-fx-background-color: #bababa");
//        pane.setPrefHeight(200);
//        pane.setPrefWidth(700);
//        pane.setLayoutX(300);
//        pane.setLayoutY(180);
//        parent.getChildren().add(pane);

//        Image image = new Image(Paths.get("src/main/java/view/images/pussy1.jpg").toUri().toString());
//        ImageView imageView = new ImageView(image);
//        imageView.setFitWidth(150);
//        imageView.setFitHeight(150);
//        imageView.setLayoutX(10);
//        imageView.setLayoutY(20);
//        Label label = new Label("Plastic pussy");
//        label.setFont(new Font(30));
//        label.setLayoutX(180);
//        pane.getChildren().add(label);
//        Label label1 = new Label("Price: 100$");
//        label1.setFont(new Font(30));
//        label1.setLayoutX(180);
//        label1.setLayoutY(60);
//        pane.getChildren().add(label1);
//        pane.getChildren().add(imageView);
        for (int i = 0; i < 5; i++) {
            pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefHeight(200);
            pane.setPrefWidth(700);
            pane.setLayoutX(300);
            pane.setLayoutY((220 * i) + 180);
            parent.getChildren().add(pane);
        }

    }

    private static void updateProducts() {

    }

    private static void createSortPanel(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: white");
        pane.setLayoutX(300);
        pane.setLayoutY(120);
        pane.setPrefWidth(700);
        pane.setPrefHeight(50);

        Label sortLabel = new Label("Sort by:");
        sortLabel.setFont(new Font(20));
        sortLabel.setTextFill(Color.BLACK);
        sortLabel.setLayoutX(10);
        sortLabel.setLayoutY(10);
        pane.getChildren().add(sortLabel);

        Button button1 = new Button("Highest price");
        button1.setLayoutX(100);
        button1.setLayoutY(15);
        button1.setStyle("-fx-background-color: #bababa");
        button1.setCursor(Cursor.HAND);
        pane.getChildren().add(button1);

        Button button2 = new Button("Lowest price");
        button2.setLayoutX(200);
        button2.setLayoutY(15);
        button2.setStyle("-fx-background-color: #bababa");
        button2.setCursor(Cursor.HAND);
        pane.getChildren().add(button2);

        Button button3 = new Button("Newest");
        button3.setLayoutX(300);
        button3.setLayoutY(15);
        button3.setStyle("-fx-background-color: #bababa");
        button3.setCursor(Cursor.HAND);
        pane.getChildren().add(button3);

        Button button4 = new Button("Oldest");
        button4.setLayoutX(370);
        button4.setLayoutY(15);
        button4.setStyle("-fx-background-color: #bababa");
        button4.setCursor(Cursor.HAND);
        pane.getChildren().add(button4);

        Button button5 = new Button("Name[A-Z]");
        button5.setLayoutX(430);
        button5.setLayoutY(15);
        button5.setStyle("-fx-background-color: #bababa");
        button5.setCursor(Cursor.HAND);
        pane.getChildren().add(button5);


        parent.getChildren().add(pane);
    }




}
