package Client.View.ProductPage;

import Client.Model.Cart;
import Client.Model.Category.Category;
import Client.Model.Product;
import Client.Model.Users.*;
import Client.View.BuyerMenu.BuyerMenu;
import Client.View.CartPage;
import Client.View.LoginAndRegister.LoginMenu;
import Client.View.ManagerMenu.ManagerMenu;
import Client.View.Menu;
import Client.View.SellerMenu.SellerMenu;
import Client.View.SupporterMenu.SupporterMenu;
import com.google.gson.Gson;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class ProductsPage {
    public static ArrayList<Product> products = new ArrayList<>();
    private static Cart staticCart = new Cart();
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;
    private static String token = Menu.token;
    private static String productId1 = "-";
    private static String productId2 = "-";

    //Done
    public static void show() throws IOException, ClassNotFoundException {
        productId1 = "-";
        productId2 = "-";

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Pane parent = new Pane();
        parent.setStyle("-fx-background-color: #858585");
        makeTopOfPage(parent);
        createCategoryPanel(parent);
        setProductsInPage(parent);
        createSortPanel(parent);
        scrollPane.setContent(parent);

        Scene scene = new Scene(scrollPane, 1280, 660);
        Menu.stage.setScene(scene);
        Menu.stage.show();
    }

    private static void makeTopOfPage(Pane parent) throws IOException, ClassNotFoundException {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #232f3e");
        pane.setPrefWidth(1280);
        pane.setPrefHeight(100);
        createImages(pane);
        createSearch(pane);
        createFilterPanel(parent);
        setProductsInPage(parent);

        parent.getChildren().add(pane);
    }

    private static void createImages(Pane pane) {
        Image mainMenuImage = new Image(Paths.get("src/main/java/Client/view/images/mainMenu.png").toUri().toString());
        ImageView mainMenu = new ImageView(mainMenuImage);
        mainMenu.setFitWidth(70);
        mainMenu.setFitHeight(70);
        mainMenu.setLayoutY(10);
        mainMenu.setCursor(Cursor.HAND);
        mainMenu.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("clearCart," + token);
                dataOutputStream.flush();
                if (dataInputStream.readUTF().equals("cleared")) {
                    Menu.executeMainMenu();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pane.getChildren().add(mainMenu);

        Image cart = new Image(Paths.get("src/main/java/Client/view/images/cart.png").toUri().toString());
        ImageView cartImage = new ImageView(cart);
        cartImage.setFitWidth(70);
        cartImage.setFitHeight(70);
        cartImage.setLayoutX(1200);
        cartImage.setLayoutY(10);
        cartImage.setCursor(Cursor.HAND);
        cartImage.setOnMouseClicked(e -> {
            try {
                dataOutputStream.writeUTF("getPerson," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Gson gson = new Gson();
                String json = dataInputStream.readUTF();
                Person person = gson.fromJson(json.substring(json.indexOf("-") + 1), Person.class);
                if (!json.equalsIgnoreCase("null")) {
                    if (json.substring(0, json.indexOf("-")).equalsIgnoreCase("buyer")) {
                        CartPage.show();
                    }
                } else if (json.equalsIgnoreCase("null")) {
                    CartPage.show();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        pane.getChildren().

                add(cartImage);

        Image userArea = new Image(Paths.get("src/main/java/Client/view/images/userArea.jpg").toUri().toString());
        ImageView userAreaImage = new ImageView(userArea);
        userAreaImage.setFitWidth(70);
        userAreaImage.setFitHeight(70);
        userAreaImage.setLayoutX(90);
        userAreaImage.setLayoutY(10);
        userAreaImage.setCursor(Cursor.HAND);
        userAreaImage.setOnMouseClicked(e ->

        {
            try {
                dataOutputStream.writeUTF("getPerson," + token);
                dataOutputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Person person = null;
            try {
                Gson gson = new Gson();
                String json = dataInputStream.readUTF();
                person = gson.fromJson(json.substring(json.indexOf("-") + 1), Person.class);
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
        pane.getChildren().

                add(userAreaImage);

    }

    //Done
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

    private static void createSortPanel(Pane parent) throws IOException, ClassNotFoundException {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: white");
        pane.setLayoutX(280);
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
        button1.setOnMouseClicked(e -> {
            products.sort(new HighestPriceSort());
            try {
                show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        pane.getChildren().add(button1);

        Button button2 = new Button("Lowest price");
        button2.setLayoutX(200);
        button2.setLayoutY(15);
        button2.setStyle("-fx-background-color: #bababa");
        button2.setCursor(Cursor.HAND);
        button2.setOnMouseClicked(e -> {
            products.sort(new LowestPriceSort());
            try {
                show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        pane.getChildren().add(button2);

        Button button3 = new Button("Newest");
        button3.setLayoutX(300);
        button3.setLayoutY(15);
        button3.setStyle("-fx-background-color: #bababa");
        button3.setCursor(Cursor.HAND);
        button3.setOnMouseClicked(e -> {
            products.sort(new NewestSort());
            try {
                show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        pane.getChildren().add(button3);

        Button button4 = new Button("Oldest");
        button4.setLayoutX(370);
        button4.setLayoutY(15);
        button4.setStyle("-fx-background-color: #bababa");
        button4.setCursor(Cursor.HAND);
        button4.setOnMouseClicked(e -> {
            products.sort(new OldestSort());
            try {
                show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        pane.getChildren().add(button4);

        Button button5 = new Button("Name[A-Z]");
        button5.setLayoutX(430);
        button5.setLayoutY(15);
        button5.setStyle("-fx-background-color: #bababa");
        button5.setCursor(Cursor.HAND);
        button5.setOnMouseClicked(e -> {
            products.sort(new NameSort());
            try {
                show();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        pane.getChildren().add(button5);

        Button showComparison = new Button("show comparison");
        showComparison.setLayoutX(540);
        showComparison.setLayoutY(15);
        showComparison.setStyle("-fx-background-color: PaleTurquoise");
        showComparison.setCursor(Cursor.HAND);
        showComparison.setOnMouseClicked(e -> {
            if (!productId1.equals("-") && !productId2.equals("-")) {
                try {
                    new ShowComparison().show(productId1, productId2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                productId1 = "-";
                productId2 = "-";
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("first choose two product!!");
                alert.showAndWait();
            }
        });
        pane.getChildren().add(showComparison);

        parent.getChildren().add(pane);
    }

    //Done
    private static void createCategoryPanel(Pane parent) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: white");
        pane.setLayoutX(10);
        pane.setLayoutY(120);
        pane.setPrefWidth(250);
        pane.setPrefHeight(50);

        Label label = new Label("Categories");
        label.setTextFill(Color.BLACK);
        label.setFont(new Font(25));
        label.setLayoutX(50);
        label.setLayoutY(10);
        pane.getChildren().add(label);

        try {
            createListOfCategories(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        parent.getChildren().add(pane);
    }

    //Done
    private static void createListOfCategories(Pane pane) throws IOException {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #bababa");
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(180);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefSize(250, 500);

        ListView listView = new ListView();
        listView.getItems().add("All");

        dataOutputStream.writeUTF("getAllCategories," + token);
        dataOutputStream.flush();

        int size = Integer.parseInt(dataInputStream.readUTF());
        ArrayList<Category> categories = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            String[] input = dataInputStream.readUTF().split("-");
            ArrayList<String> details = new ArrayList<>();
            details.add(input[1]);
            details.add(input[2]);
            details.add(input[3]);
            Category category = new Category(input[0], details);
            categories.add(category);
            listView.getItems().add(category.getName() + "(" + category.getDetail1() + category.getDetail2() + "...)");
        }

        listView.setOnMouseClicked(e -> {
            String name = listView.getSelectionModel().getSelectedItems().toString();
            if (name.equals("[All]")) {
                products.clear();
                try {
                    dataOutputStream.writeUTF("getProducts," + token);
                    dataOutputStream.flush();
                    int size1 = Integer.parseInt(dataInputStream.readUTF());
                    for (int i = 0; i < size1; i++) {
                        String[] input = dataInputStream.readUTF().split("-");
                        Product product = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
                        products.add(product);
                    }
                    show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else {
                products.clear();
                try {
                    dataOutputStream.writeUTF("getCategoryProducts," + name.substring(1, name.indexOf("(")) + "," + token);
                    dataOutputStream.flush();
                    int size1 = Integer.parseInt(dataInputStream.readUTF());
                    for (int i = 0; i < size1; i++) {
                        String[] input = dataInputStream.readUTF().split("-");
                        Product product = new Product(input[0], input[1], input[2], Long.parseLong(input[3]), input[4], input[5], input[6], Integer.parseInt(input[7]));
                        products.add(product);
                    }
                    show();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
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
        pane.setLayoutY(120);
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
        scrollPane.setLayoutY(180);
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

    //Done
    private static void setProductsInPage(Pane parent) throws IOException, ClassNotFoundException {
        int i = 0;
        dataOutputStream.writeUTF("getPerson," + token);
        dataOutputStream.flush();
        Gson gson = new Gson();
        String json = dataInputStream.readUTF();
        Person person = gson.fromJson(json.substring(json.indexOf("-") + 1), Person.class);

        for (Product product : products) {
            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: #bababa");
            pane.setPrefHeight(200);
            pane.setPrefWidth(700);
            pane.setLayoutX(280);
            pane.setLayoutY((220 * i) + 180);
            pane.setCursor(Cursor.HAND);

            Image image = new Image(Paths.get("src/main/java/Client/view/images/digital.png").toUri().toString());
            ImageView imageView = new ImageView(image);
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

            dataOutputStream.writeUTF("productScore-" + product.getProductID() + "," + token);
            dataOutputStream.flush();
            product.setScore(dataInputStream.readUTF());
            Label score = new Label("Score: " + product.getScore());
            score.setTextFill(Color.YELLOW);
            score.setTextFill(Color.BLACK);
            score.setFont(new Font(25));
            score.setLayoutX(180);
            score.setLayoutY(110);
            pane.getChildren().add(score);

            Label money = new Label("Price: " + product.getMoney());
            money.setTextFill(Color.GREEN);
            money.setFont(new Font(20));
            money.setLayoutX(180);
            money.setLayoutY(170);
            pane.getChildren().add(money);

            Label number = new Label("Number: " + product.getNumberOfProducts());
            number.setTextFill(Color.RED);
            number.setFont(new Font(20));
            number.setLayoutX(380);
            number.setLayoutY(170);
            pane.getChildren().add(number);

            Button addToComparison = new Button("Add to comparison");
            addToComparison.setCursor(Cursor.HAND);
            addToComparison.setLayoutX(500);
            addToComparison.setLayoutY(10);
            addToComparison.setOnMouseClicked(e -> {
                if (productId1.equals("-")) {
                    productId1 = product.getProductID();
                    addToComparison.setStyle("-fx-background-color: SteelBlue");
                } else if (productId2.equals("-")) {
                    productId2 = product.getProductID();
                    addToComparison.setStyle("-fx-background-color: SteelBlue");
                }
            });
            pane.getChildren().add(addToComparison);

            if (product.getNumberOfProducts() > 0) {
                Button addToCartButton = new Button("Add to cart");
                addToCartButton.setCursor(Cursor.HAND);
                addToCartButton.setLayoutX(500);
                addToCartButton.setLayoutY(170);
                if (person == null || json.substring(0, json.indexOf("-")).equalsIgnoreCase("Buyer")) {
                    addToCartButton.setOnMouseClicked(e -> {
                        try {
                            dataOutputStream.writeUTF("addToCart," + product.getProductID() + "," + token);
                            dataOutputStream.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
                pane.getChildren().add(addToCartButton);
            }
            pane.setOnMouseClicked(e -> {
                try {
                    ProductPage.show(product);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            parent.getChildren().add(pane);
            i++;
        }
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

class HighestPriceSort implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        int price = (int) (product2.getMoney() - product1.getMoney());
        return price;
    }
}

class LowestPriceSort implements Comparator<Product> {

    @Override
    public int compare(Product product1, Product product2) {
        int price = (int) (product1.getMoney() - product2.getMoney());
        return price;
    }
}

class NameSort implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        int price = (product1.getName().compareTo(product2.getName()));
        return price;
    }
}