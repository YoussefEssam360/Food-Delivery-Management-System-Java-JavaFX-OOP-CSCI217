import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) 
    {
        // Load all backend data first
        DishManager.loadDishesFromFile();

        // Role selection buttons
        Button adminBtn = UIFactory.createButton("Admin", StyleUtil.button(), StyleUtil.hover());
        Button userBtn = UIFactory.createButton("User", StyleUtil.button(), StyleUtil.hover());
        UIFactory.applyHoverZoomEffect(adminBtn);
        UIFactory.applyHoverZoomEffect(userBtn);

        HBox buttonRow = new HBox(20, adminBtn, userBtn);
        buttonRow.setStyle("-fx-alignment: center;");

        VBox root = new VBox(30, buttonRow);
        root.setStyle("-fx-background-color: rgb(29, 0, 0); -fx-padding: 40px; -fx-alignment: center;");

        adminBtn.setOnAction(e -> {
            adminPage adminScreen = new adminPage();
            DishUIFactory.refreshDishUI(adminScreen.getDishContainer());
            primaryStage.setScene(adminScreen.createScene(primaryStage));
            primaryStage.setTitle("Foodie Admin");
            primaryStage.centerOnScreen(); // Center the window
        });

        userBtn.setOnAction(e -> {
            UserMain userScreen = new UserMain();
            primaryStage.setScene(userScreen.createScene(primaryStage));
            primaryStage.setTitle("Foodie User");
            primaryStage.centerOnScreen(); // Center the window
        });

        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.setTitle("Choose Role");
        primaryStage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
