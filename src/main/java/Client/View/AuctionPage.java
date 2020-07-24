package Client.View;

import Controller.AuctionController.AuctionController;
import Controller.ProductController.ProductController;
import Model.Cart;
import Model.Category.Category;
import Model.Product;
import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Seller;
import View.BuyerMenu.BuyerMenu;
import View.LoginAndRegister.LoginMenu;
import View.LoginAndRegister.RegisterMenu;
import View.ManagrMenu.ManagerMenu;
import View.ProductPage.ProductPage;
import View.SellerMenu.SellerMenu;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AuctionPage {
    public static ArrayList<Product> products = new ArrayList<>(AuctionController.getAllProducts());
    private static Cart staticCart = new Cart();

    public static void show() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        makeTopOfPage(parent);
        createCategoryPanel(parent);
        createSortPanel(parent);
        setProductsInPage(parent);
        createSortPanel(parent);
        scrollPane.setContent(parent);
        showAuctionAnimation(parent);

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
                staticCart.clear();
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
            if (LoginMenu.currentPerson instanceof Buyer) {
                CartPage.show(((Buyer) LoginMenu.currentPerson).getCart());
            } else if (LoginMenu.currentPerson == null) {
                CartPage.show(staticCart);
            }
        });
        pane.getChildren().add(cartImage);

        Image userArea = new Image(Paths.get("src/main/java/view/images/userArea.jpg").toUri().toString());
        ImageView userAreaImage = new ImageView(userArea);
        userAreaImage.setFitWidth(70);
        userAreaImage.setFitHeight(70);
        userAreaImage.setLayoutX(90);
        userAreaImage.setLayoutY(10);
        userAreaImage.setCursor(Cursor.HAND);
        userAreaImage.setOnMouseClicked(e -> {
            if (LoginMenu.currentPerson instanceof Buyer) {
                new BuyerMenu().show();
            } else if (LoginMenu.currentPerson instanceof Seller) {
                new SellerMenu().show();
            } else if (LoginMenu.currentPerson instanceof Manager) {
                new ManagerMenu().show();
            } else {
                new RegisterMenu().show();
            }


        });
        pane.getChildren().add(userAreaImage);
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

    private static void createSortPanel(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: white");
        pane.setLayoutX(300);
        pane.setLayoutY(350);
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
        button1.setOnMouseClicked(e -> {
            Collections.sort(products, new HighestPriceSort());
            show();
        });
        pane.getChildren().add(button1);

        Button button2 = new Button("Lowest price");
        button2.setLayoutX(200);
        button2.setLayoutY(15);
        button2.setStyle("-fx-background-color: #bababa");
        button2.setCursor(Cursor.HAND);
        button2.setOnMouseClicked(e -> {
            Collections.sort(products, new LowestPriceSort());
            show();
        });
        pane.getChildren().add(button2);

        Button button3 = new Button("Newest");
        button3.setLayoutX(300);
        button3.setLayoutY(15);
        button3.setStyle("-fx-background-color: #bababa");
        button3.setCursor(Cursor.HAND);
        button3.setOnMouseClicked(e -> {
            Collections.sort(products, new NewestSort());
            show();
        });
        pane.getChildren().add(button3);

        Button button4 = new Button("Oldest");
        button4.setLayoutX(370);
        button4.setLayoutY(15);
        button4.setStyle("-fx-background-color: #bababa");
        button4.setCursor(Cursor.HAND);
        button4.setOnMouseClicked(e -> {
            Collections.sort(products, new OldestSort());
            show();
        });
        pane.getChildren().add(button4);

        Button button5 = new Button("Name[A-Z]");
        button5.setLayoutX(430);
        button5.setLayoutY(15);
        button5.setStyle("-fx-background-color: #bababa");
        button5.setCursor(Cursor.HAND);
        button5.setOnMouseClicked(e -> {
            Collections.sort(products, new NameSort());
            show();
        });
        pane.getChildren().add(button5);


        parent.getChildren().add(pane);
    }

    private static void createCategoryPanel(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: white");
        pane.setLayoutX(10);
        pane.setLayoutY(350);
        pane.setPrefWidth(250);
        pane.setPrefHeight(50);

        Label label = new Label("Categories");
        label.setTextFill(Color.BLACK);
        label.setFont(new Font(25));
        label.setLayoutX(50);
        label.setLayoutY(10);
        pane.getChildren().add(label);

        createListOfCategories(parent);

        parent.getChildren().add(pane);
    }

    private static void createListOfCategories(Pane pane) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #bababa");
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(410);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefSize(250, 500);

        ListView listView = new ListView();
        listView.getItems().add("All");
        for (Category allCategory : ProductController.getAllCategories()) {
            listView.getItems().add(allCategory.getName() + "(" + allCategory.getDetail1() + allCategory.getDetail2() + "...)");
        }

        listView.setOnMouseClicked(e -> {
            String name = listView.getSelectionModel().getSelectedItems().toString();
            if (name.equals("[All]")) {
                products.clear();
                products.addAll(AuctionController.getAllProducts());
                show();
            } else {
                showProductsWithCategoryFilter(Category.getCategoryByName(name.substring(1, name.indexOf("("))));

            }
        });
        listView.setCursor(Cursor.HAND);
        scrollPane.setContent(listView);
        listView.setPrefHeight(500);

        pane.getChildren().add(scrollPane);
    }

    private static void createFilterPanel(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: white");
        pane.setLayoutX(1010);
        pane.setLayoutY(350);
        pane.setPrefWidth(250);
        pane.setPrefHeight(50);

        Label label = new Label("Filter");
        label.setTextFill(Color.BLACK);
        label.setFont(new Font(25));
        label.setLayoutX(110);
        label.setLayoutY(10);
        pane.getChildren().add(label);

        creteListOfFilters(parent);

        parent.getChildren().add(pane);
    }

    private static void creteListOfFilters(Pane pane) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #bababa");
        scrollPane.setLayoutX(1010);
        scrollPane.setLayoutY(410);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefSize(250, 500);

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


        pane.getChildren().add(scrollPane);

    }

    private static void setProductsInPage(Pane parent) {
        int i = 0;
        for (Product product : products) {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefHeight(200);
            pane.setPrefWidth(700);
            pane.setLayoutX(300);
            pane.setLayoutY((220 * i) + 410);
            pane.setCursor(Cursor.HAND);

            ImageView imageView = product.getImageView();
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            imageView.setLayoutX(10);
            imageView.setLayoutY(25);
            pane.getChildren().add(imageView);

            Label name = new Label("Name: " + product.getName());
            name.setTextFill(Color.BLACK);
            name.setFont(new Font(30));
            name.setLayoutX(180);
            pane.getChildren().add(name);

            Label description = new Label("Description: " + product.getDescription());
            description.setTextFill(Color.BLUE);
            description.setTextFill(Color.BLACK);
            description.setFont(new Font(25));
            description.setLayoutX(180);
            description.setLayoutY(50);
            pane.getChildren().add(description);

            Label score = new Label("Score: " + product.getScore());
            score.setTextFill(Color.YELLOW);
            score.setTextFill(Color.BLACK);
            score.setFont(new Font(25));
            score.setLayoutX(180);
            score.setLayoutY(100);
            pane.getChildren().add(score);

            Label discount = new Label("Discount: " + product.getDiscount() + "%");
            discount.setTextFill(Color.GREEN);
            discount.setFont(new Font(20));
            discount.setLayoutX(180);
            discount.setLayoutY(140);
            pane.getChildren().add(discount);

            Label money = new Label("Price: " + product.getMoney());
            money.setTextFill(Color.GREEN);
            money.setFont(new Font(20));
            money.setLayoutX(180);
            money.setLayoutY(170);
            pane.getChildren().add(money);

    }

    private static void showAuctionAnimation(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: red");
        pane.setPrefHeight(200);
        pane.setPrefWidth(1280);
        pane.setLayoutY(120);
        parent.getChildren().add(pane);

    }

    private static void showProductsWithCategoryFilter(Category category) {
        products.clear();
        products.addAll(AuctionController.getAllCategoryProducts(category));
        show();
    }

}

class NewestSort implements Comparator<Product> {
    @Override
    public int compare(Product product2, Product product1) {
        int time = product1.getLocalTime().compareTo(product2.getLocalTime());
        return time;
    }
}

class OldestSort implements Comparator<Product> {
    @Override
    public int compare(Product product2, Product product1) {
        int time = product2.getLocalTime().compareTo(product1.getLocalTime());
        return time;
    }
}

}
