package Client.View.ManagrMenu;

public class RequestPage {
//    public static void show() {
//        Pane parent = new Pane();
//        parent.setStyle("-fx-background-color: #858585");
//        Label label = new Label("Requests Page");
//        label.setLayoutX(120);
//        label.setLayoutY(90);
//        label.setFont(new Font(30));
//        parent.getChildren().add(label);
//        showAllRequestsPage(parent);
//
//
//        Scene scene = new Scene(parent, 1280, 660);
//        Menu.stage.setScene(scene);
//        Menu.stage.show();
//    }
//
//    private static void showAllRequestsPage(Pane parent) {
//        Pane requestPage = new Pane();
//        requestPage.setStyle("-fx-background-color: #bababa");
//        requestPage.setPrefWidth(210);
//        requestPage.setPrefHeight(70);
//        requestPage.setLayoutX(120);
//        requestPage.setLayoutY(200);
//        requestPage.setCursor(Cursor.HAND);
//
//        Image image = new Image(Paths.get("src/main/java/view/images/request.png").toUri().toString());
//        ImageView imageView = new ImageView(image);
//        imageView.setFitWidth(50);
//        imageView.setFitHeight(50);
//        imageView.setLayoutY(10);
//        requestPage.getChildren().add(imageView);
//        parent.getChildren().add(requestPage);
//
//        Label personalInfoLabel = new Label("All Requests");
//        personalInfoLabel.setFont(new Font(20));
//        personalInfoLabel.setLayoutX(60);
//        personalInfoLabel.setLayoutY(10);
//        requestPage.getChildren().add(personalInfoLabel);
//
//        Label personalInfoSecondLabel = new Label("accept,decline requests");
//        personalInfoSecondLabel.setFont(new Font(12));
//        personalInfoSecondLabel.setLayoutX(60);
//        personalInfoSecondLabel.setLayoutY(40);
//        requestPage.getChildren().add(personalInfoSecondLabel);
//        requestPage.setOnMouseClicked(e -> {
//            showAllRequests();
//        });
//
//    }
//
//    private static void showAllRequests() {
//        Pane parent = new Pane();
//        parent.setStyle("-fx-background-color: #858585");
//        Label label = new Label("Type");
//        label.setLayoutX(100);
//        label.setLayoutY(90);
//        label.setFont(new Font(20));
//        parent.getChildren().add(label);
//
//        Label label1 = new Label("Condition");
//        label1.setLayoutX(300);
//        label1.setLayoutY(90);
//        label1.setFont(new Font(20));
//        parent.getChildren().add(label1);
//
//        Label label2 = new Label("Sender");
//        label2.setLayoutX(500);
//        label2.setLayoutY(90);
//        label2.setFont(new Font(20));
//        parent.getChildren().add(label2);
//
//        Label label3 = new Label("Delete");
//        label3.setLayoutX(700);
//        label3.setLayoutY(90);
//        label3.setFont(new Font(20));
//        parent.getChildren().add(label3);
//
//        Label label4 = new Label("Set Condition");
//        label4.setLayoutX(900);
//        label4.setLayoutY(90);
//        label4.setFont(new Font(20));
//        parent.getChildren().add(label4);
//
//
//        Scene scene = new Scene(parent, 1280, 660);
//        Menu.stage.setScene(scene);
//        Menu.stage.show();
//    }
//
//    private static void updateList() {
//
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        showAllRequests();
//    }
}