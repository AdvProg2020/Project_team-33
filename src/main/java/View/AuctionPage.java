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


}
