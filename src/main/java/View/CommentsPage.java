package View;

import Controller.CommentsController;
import Controller.ProductController;
import Model.Comment;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class CommentsPage {

    public static void show(Product product) {
        AnchorPane parent = new AnchorPane();
        parent.setPrefWidth(600.0);
        parent.setPrefHeight(387.0);
        TableView<Comment> commentsTableView = createTable();
        commentsTableView.setPrefHeight(327.0);
        commentsTableView.setPrefWidth(600.0);
        commentsTableView.setStyle("-fx-background-color: Grey");
        parent.getChildren().add(commentsTableView);
        commentField(parent);
        Stage commentStage = new Stage();
        Scene scene = new Scene(parent, 700, 500);
        commentStage.setScene(scene);
        commentStage.show();
    }

    private static void commentField(AnchorPane parent) {
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
        addComment.setOnMouseClicked(e -> {
            CommentsController.addComment(textField);
        });

        Image image = new Image(Paths.get("src/main/java/View/images/blue-plus-icon.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(36.0);
        imageView.setFitHeight(22.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        addComment.setGraphic(imageView);

        parent.getChildren().addAll(textField, addComment);
    }

    private static TableView<Comment> createTable() {
        TableView<Comment> table = new TableView<>();
        table.getItems().addAll(getComments());
        table.getColumns().addAll(getNameColumn(), getCommentColumn(), getPurchaseStatusColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("nothing yet!"));
        return table;
    }

    private static ObservableList<Comment> getComments() {
        ObservableList<Comment> comments = FXCollections.observableArrayList();
        comments.addAll(ProductController.product.getAllComments());
        return comments;
    }

    private static TableColumn<Comment, String> getNameColumn() {
        TableColumn<Comment, String> names = new TableColumn<>("name");
        names.setCellValueFactory(new PropertyValueFactory<>("name"));
        return names;
    }

    private static TableColumn<Comment, String> getCommentColumn() {
        TableColumn<Comment, String> commentColumn =  new TableColumn<>("comment");
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        return commentColumn;
    }

    private static TableColumn<Comment, Image> getPurchaseStatusColumn() {
        TableColumn<Comment, Image> purchaseStatusColumn =  new TableColumn<>("buy status");
        purchaseStatusColumn.setCellValueFactory(new PropertyValueFactory<>("buyCondition"));
        return purchaseStatusColumn;
    }


}
