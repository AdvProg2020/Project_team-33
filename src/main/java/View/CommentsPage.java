package View;

import Controller.CommentsController;
import Model.Comment;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Paths;

public class CommentsPage {
    private static DataInputStream dataInputStream = Menu.dataInputStream;
    private static DataOutputStream dataOutputStream = Menu.dataOutputStream;
    private static ObjectInputStream objectInputStream = Menu.objectInputStream;
    private static ObjectOutputStream objectOutputStream = Menu.objectOutputStream;

    private static Stage commentStage = new Stage();

    public static void show(Product product) {
        AnchorPane parent = new AnchorPane();
        parent.setPrefWidth(600.0);
        parent.setPrefHeight(399.0);
        TableView<Comment> commentsTableView = new TableView<>();
        updateTable(product, commentsTableView);
        commentsTableView.setPrefHeight(327.0);
        commentsTableView.setPrefWidth(600.0);
        commentsTableView.setStyle("-fx-background-color: Grey");
        parent.getChildren().add(commentsTableView);
        commentField(parent, product, commentsTableView);
        Scene scene = new Scene(parent, 600.0, 399.0);
        commentStage.setScene(scene);
        commentStage.show();
    }

    private static void commentField(AnchorPane parent, Product product, TableView<Comment> table) {
        TextField textField = new TextField();
        textField.setLayoutX(7.0);
        textField.setLayoutY(333.0);
        textField.setPrefHeight(61.0);
        textField.setPrefWidth(532.0);
        textField.setPromptText("write your comment...");

        Button addComment = new Button();
        addComment.setLayoutX(548.0);
        addComment.setLayoutY(350);
        addComment.setPrefHeight(26.0);
        addComment.setPrefWidth(44.0);

        Image image = new Image(Paths.get("src/main/java/View/images/blue-plus-icon.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(36.0);
        imageView.setFitHeight(22.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        addComment.setGraphic(imageView);

        addComment.setOnMouseClicked(e -> {
            CommentsController.addComment(textField , product);
            show(product);
        });

        parent.getChildren().addAll(textField, addComment);
    }

    private static void updateTable(Product product, TableView<Comment> table) {
        table.getItems().addAll(getComments(product));
        table.getColumns().addAll(getNameColumn(), getCommentColumn(), getPurchaseStatusColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("nothing yet!"));
    }

    private static ObservableList<Comment> getComments(Product product) {
        ObservableList<Comment> comments = FXCollections.observableArrayList();
        comments.addAll(product.getAllComments());
        return comments;
    }

    private static TableColumn<Comment, String> getNameColumn() {
        TableColumn<Comment, String> names = new TableColumn<>("name");
        names.setCellValueFactory(new PropertyValueFactory<>("name"));
        names.setPrefWidth(80.0);
        return names;
    }

    private static TableColumn<Comment, String> getCommentColumn() {
        TableColumn<Comment, String> commentColumn =  new TableColumn<>("comment");
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        commentColumn.setPrefWidth(350.0);
        return commentColumn;
    }

    private static TableColumn<Comment, String> getPurchaseStatusColumn() {
        TableColumn<Comment, String> purchaseStatusColumn =  new TableColumn<>("has been bought");
        purchaseStatusColumn.setCellValueFactory(new PropertyValueFactory<>("buyCondition"));
        purchaseStatusColumn.setPrefWidth(75.0);
        return purchaseStatusColumn;
    }


}
