package View;

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

    public void start(Product product) {
        AnchorPane parent = new AnchorPane();
        parent.setPrefWidth(600.0);
        parent.setPrefHeight(400);
        addTitle(gridPane);
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

    private void commentField(AnchorPane parent) {
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

        Image image = new Image(Paths.get("src/main/java/view/images/blue-plus-icon.png").toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(36.0);
        imageView.setFitHeight(22.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        addComment.setGraphic(imageView);

        parent.getChildren().addAll(textField, addComment);
    }

    private TableView<Comment> createTable() {
        TableView<Comment> table = new TableView<>();
        table.getItems().addAll(getPlayerList());
        table.getColumns().addAll(getNameColumn(), getTotalScoreColumn(), getWinsColumn(), getLosesColumn());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("nothing yet!"));
        return table;
    }

    private ObservableList<Comment> getPlayerList() {
        return FXCollections.observableArrayList(UserController.getInstance().getSortedPlayers());
    }

    private TableColumn<Comment, String> getNameColumn() {
        TableColumn<Comment, String> names = new TableColumn<>("Username");
        PropertyValueFactory<Comment, String> nameCellValueFactory = new PropertyValueFactory<>("username");
        names.setCellValueFactory(nameCellValueFactory);
        return names;
    }

    private TableColumn<Comment, Integer> getTotalScoreColumn() {
        TableColumn<Comment, Integer> totalScore =  new TableColumn<>("Total Score");
        PropertyValueFactory<Comment, Integer> totalScoreCellValueFactory = new PropertyValueFactory<>("totalScore");
        totalScore.setCellValueFactory(totalScoreCellValueFactory);
        return totalScore;
    }

    private TableColumn<Comment, Integer> getWinsColumn() {
        TableColumn<Comment, Integer> wins =  new TableColumn<>("Wins");
        PropertyValueFactory<Comment, Integer> winsCellValueFactory = new PropertyValueFactory<>("wins");
        wins.setCellValueFactory(winsCellValueFactory);
        return wins;
    }

    private TableColumn<Comment, Integer> getLosesColumn() {
        TableColumn<Comment, Integer> loses =  new TableColumn<>("Loses");
        PropertyValueFactory<Comment, Integer> losesCellValueFactory = new PropertyValueFactory<>("loses");
        loses.setCellValueFactory(losesCellValueFactory);
        return loses;
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(60, 60, 60, 60));
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        return gridPane;
    }
}
