package View;

import Controller.CartAndPurchase.CartController;
import Model.Cart;
import Model.Product;
import Model.Users.Buyer;
import Model.Users.Manager;
import Model.Users.Seller;
import View.BuyerMenu.BuyerMenu;
import View.LoginAndRegister.LoginMenu;
import View.LoginAndRegister.RegisterMenu;
import View.ManagrMenu.ManagerMenu;
import View.ProductPage.ProductsPage;
import View.SellerMenu.SellerMenu;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.nio.file.Paths;

public class CartPage {
    public static Cart staticCart;
    private static Image plus = new Image(Paths.get("src/main/java/view/images/plus.jpg").toUri().toString());
    private static Image minus = new Image(Paths.get("src/main/java/view/images/minus.png").toUri().toString());

    public static void show(Cart cart) {
        staticCart = cart;
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");

        Label label = new Label("Cart");
        label.setFont(new Font(30));
        label.setLayoutX(5);
        label.setLayoutY(100);
        parent.getChildren().add(label);

        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #bababa");
        back.setLayoutX(130);
        back.setLayoutY(110);
        back.setCursor(Cursor.HAND);
        back.setOnMouseClicked(e -> {
            ProductsPage.show();
        });
        parent.getChildren().add(back);

        Button update = new Button("Update list");
        update.setStyle("-fx-background-color: #bababa");
        update.setLayoutX(200);
        update.setLayoutY(110);
        update.setCursor(Cursor.HAND);
        update.setOnMouseClicked(e -> {
            showFields(parent);
        });
        parent.getChildren().add(update);

        Button purchase = new Button("Purchase");
        purchase.setStyle("-fx-background-color: #bababa");
        purchase.setLayoutX(350);
        purchase.setLayoutY(110);
        purchase.setCursor(Cursor.HAND);
        purchase.setOnMouseClicked(e -> {
            if (LoginMenu.currentPerson instanceof Buyer) {
                PurchaseMenu.show();
            } else {
                try {
                    new LoginMenu().loginProcess();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        parent.getChildren().add(purchase);

        makeTopOfPage(parent);
        showFields(parent);

        scrollPane.setContent(parent);
        Scene scene = new Scene(scrollPane, 1280, 660);

        Menu.stage.setScene(scene);

        Menu.stage.show();
    }

    public static void makeTopOfPage(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #232f3e");
        pane.setPrefWidth(1280);
        pane.setPrefHeight(100);
        createImages(pane);
//        setProductsInPage(parent);

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

    public static void showFields(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #bababa");
        pane.setLayoutX(5);
        pane.setPrefWidth(1260);
        pane.setPrefHeight(550);
        pane.setLayoutY(150);

        Label productName = new Label("Product");
        productName.setLayoutX(100);
        productName.setLayoutY(10);
        productName.setFont(new Font(25));
        pane.getChildren().add(productName);

        Label numberOfProduct = new Label("Number");
        numberOfProduct.setLayoutX(550);
        numberOfProduct.setLayoutY(10);
        numberOfProduct.setFont(new Font(25));
        pane.getChildren().add(numberOfProduct);

        Label productPrice = new Label("Price");
        productPrice.setLayoutX(350);
        productPrice.setLayoutY(10);
        productPrice.setFont(new Font(25));
        pane.getChildren().add(productPrice);

        Label finalPrice = new Label("Final Price");
        finalPrice.setLayoutX(750);
        finalPrice.setLayoutY(10);
        finalPrice.setFont(new Font(25));
        pane.getChildren().add(finalPrice);

        Label increaseOrDecrease = new Label("Increase/Decrease");
        increaseOrDecrease.setLayoutX(950);
        increaseOrDecrease.setLayoutY(10);
        increaseOrDecrease.setFont(new Font(25));
        pane.getChildren().add(increaseOrDecrease);

        updateList(pane);

        parent.getChildren().add(pane);
    }

    public static void updateList(Pane pane) {
        int i = 1;
        for (Product product : CartController.getAllProductsInCart(staticCart)) {

            ImageView productImage = product.getImageView();
            productImage.setLayoutY(40 * i);
            productImage.setFitWidth(50);
            productImage.setFitHeight(50);
            pane.getChildren().add(productImage);

            Label productName = new Label(product.getName());
            productName.setLayoutX(100);
            productName.setLayoutY(50 * i);
            productName.setFont(new Font(20));
            pane.getChildren().add(productName);

            Label numberOfProduct = new Label(String.valueOf(CartController.getNumberOfProduct(staticCart, product)));
            numberOfProduct.setLayoutX(550);
            numberOfProduct.setLayoutY(50 * i);
            numberOfProduct.setFont(new Font(20));
            pane.getChildren().add(numberOfProduct);

            Label productPrice = new Label(String.valueOf(product.getMoney()));
            productPrice.setLayoutX(350);
            productPrice.setLayoutY(50 * i);
            productPrice.setFont(new Font(20));
            pane.getChildren().add(productPrice);

            Label finalPrice = new Label(String.valueOf(Integer.parseInt(numberOfProduct.getText()) * product.getMoney()));
            finalPrice.setLayoutX(750);
            finalPrice.setLayoutY(50 * i);
            finalPrice.setFont(new Font(20));
            pane.getChildren().add(finalPrice);

            ImageView increase = new ImageView(plus);
            increase.setLayoutX(1000);
            increase.setLayoutY(50 * i);
            increase.setFitWidth(30);
            increase.setFitHeight(30);
            increase.setCursor(Cursor.HAND);
            increase.setOnMouseClicked(e -> {
                if (product.getNumberOfProducts() >= CartController.getNumberOfProduct(staticCart, product) + 1) {
                    CartController.changeNumberOfProductsInHashMap(staticCart, product, CartController.getNumberOfProduct(staticCart, product) + 1);
                }
            });
            pane.getChildren().add(increase);

            ImageView decrease = new ImageView(minus);
            decrease.setLayoutX(950);
            decrease.setLayoutY(50 * i);
            decrease.setFitHeight(10);
            decrease.setFitWidth(40);
            decrease.setCursor(Cursor.HAND);
            decrease.setOnMouseClicked(e -> {
                CartController.changeNumberOfProductsInHashMap(staticCart, product, CartController.getNumberOfProduct(staticCart, product) - 1);

            });
            pane.getChildren().add(decrease);

            i++;
        }
    }

}
