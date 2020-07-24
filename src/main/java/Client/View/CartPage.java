package Client.View;

import Client.Model.Cart;
import Client.Model.Product;
import Client.Model.Users.*;
import Client.View.BuyerMenu.BuyerMenu;
import Client.View.LoginAndRegister.LoginMenu;
import Client.View.LoginAndRegister.RegisterMenu;
import Client.View.ManagerMenu.ManagerMenu;
import Client.View.ProductPage.ProductsPage;
import Client.View.SellerMenu.SellerMenu;
import Client.View.SupporterMenu.SupporterMenu;
import com.google.gson.Gson;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CartPage {
    private static Image plus = new Image(Paths.get("src/main/java/Client/view/images/plus.jpg").toUri().toString());
    private static Image minus = new Image(Paths.get("src/main/java/Client/view/images/minus.png").toUri().toString());
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;

    //ToDo
    public static void show() {
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
            try {
                ProductsPage.show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
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

        //ToDO
//        Button purchase = new Button("Purchase");
//        purchase.setStyle("-fx-background-color: #bababa");
//        purchase.setLayoutX(350);
//        purchase.setLayoutY(110);
//        purchase.setCursor(Cursor.HAND);
//        purchase.setOnMouseClicked(e -> {
//            try {
//                dataOutputStream.writeUTF("getPerson");
//                dataOutputStream.flush();
//                Gson gson = new Gson();
//                String json = dataInputStream.readUTF();
//                loginPerson = gson.fromJson(json, Person.class);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            try {
//                if (loginPerson instanceof Buyer) {
//                    PurchaseMenu.show();
//                } else {
//                    try {
//                        new LoginMenu().loginProcess();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            } catch (IOException | ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        });
//        parent.getChildren().add(purchase);

        makeTopOfPage(parent);
        showFields(parent);

        scrollPane.setContent(parent);
        Scene scene = new Scene(scrollPane, 1280, 660);

        Menu.stage.setScene(scene);

        Menu.stage.show();
    }

    //ToDo
    public static void makeTopOfPage(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #232f3e");
        pane.setPrefWidth(1280);
        pane.setPrefHeight(100);
        createImages(pane);

        parent.getChildren().add(pane);
    }

    //Done
    private static void createImages(Pane pane) {
        Image mainMenuImage = new Image(Paths.get("src/main/java/Client/view/images/mainMenu.png").toUri().toString());
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

        Image userArea = new Image(Paths.get("src/main/java/Client/view/images/userArea.jpg").toUri().toString());
        ImageView userAreaImage = new ImageView(userArea);
        userAreaImage.setFitWidth(70);
        userAreaImage.setFitHeight(70);
        userAreaImage.setLayoutX(90);
        userAreaImage.setLayoutY(10);
        userAreaImage.setCursor(Cursor.HAND);
        userAreaImage.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("getPerson");
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                String json = dataInputStream.readUTF();
                if (!json.equalsIgnoreCase("null")) {
                    if (json.substring(0, json.indexOf("-")).equalsIgnoreCase("buyer")) {
                        try {
                            new BuyerMenu().show();
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else if (json.substring(0, json.indexOf("-")).equalsIgnoreCase("seller")) {
                        try {
                            new SellerMenu().show();
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else if (json.substring(0, json.indexOf("-")).equalsIgnoreCase("manager")) {
                        try {
                            new ManagerMenu().show();
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else if (json.substring(0, json.indexOf("-")).equalsIgnoreCase("supporter")) {
                        try {
                            new SupporterMenu().show();
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    new LoginMenu().show();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        });
        pane.getChildren().add(userAreaImage);
    }

    //Done
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

        try {
            updateList(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

        parent.getChildren().add(pane);
    }

    //ToDo
    public static void updateList(Pane pane) throws IOException {
        ArrayList<Product> allCartProducts = new ArrayList<>();
        dataOutputStream.writeUTF("getCartProducts");
        dataOutputStream.flush();
        int size = Integer.parseInt(dataInputStream.readUTF());
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String[] input = dataInputStream.readUTF().split("-");
            Product product = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
            products.add(product);
        }
        int i = 1;
        for (Product product : products) {

//            Image image = new Image("src/main/java/Client/View/images/product.png");
//            ImageView productImage = new ImageView(image);
//            productImage.setLayoutY(40 * i);
//            productImage.setFitWidth(50);
//            productImage.setFitHeight(50);
//            pane.getChildren().add(productImage);

            Label productName = new Label(product.getName());
            productName.setLayoutX(100);
            productName.setLayoutY(50 * i);
            productName.setFont(new Font(20));
            pane.getChildren().add(productName);

            dataOutputStream.writeUTF("getNumberOfProductInCart id-" + product.getProductID());
            dataOutputStream.flush();

            Label numberOfProduct = new Label(dataInputStream.readUTF());
            numberOfProduct.setLayoutX(550);
            numberOfProduct.setLayoutY(50 * i);
            numberOfProduct.setFont(new Font(20));
            pane.getChildren().add(numberOfProduct);

            Label productPrice = new Label(String.valueOf(product.getMoney()));
            productPrice.setLayoutX(350);
            productPrice.setLayoutY(50 * i);
            productPrice.setFont(new Font(20));
            pane.getChildren().add(productPrice);

            //ToDo
            Label finalPrice = new Label(String.valueOf(Integer.parseInt(numberOfProduct.getText()) * product.getMoney()));
            finalPrice.setLayoutX(750);
            finalPrice.setLayoutY(50 * i);
            finalPrice.setFont(new Font(20));
            pane.getChildren().add(finalPrice);

            ImageView increase = new ImageView(plus);
            increase.setLayoutX(1100);
            increase.setLayoutY(50 * i);
            increase.setFitWidth(30);
            increase.setFitHeight(30);
            increase.setCursor(Cursor.HAND);
            increase.setOnMouseClicked(e -> {
                try {
                    dataOutputStream.writeUTF("changeNumberOfProductsInHashMap,increase," + product.getProductID());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
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
                try {
                    dataOutputStream.writeUTF("changeNumberOfProductsInHashMap,decrease," + product.getProductID());
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            pane.getChildren().add(decrease);
            i++;
        }
    }

}
