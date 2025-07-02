import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
//import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusModal {
    private static List<Text> statusTexts = new ArrayList<>();

    public static void show(Stage owner) {
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.initOwner(owner);
        modal.setTitle("Order Status");
        modal.setResizable(false);

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: rgb(29, 0, 0);");

        VBox ordersBox = new VBox(15);
        ordersBox.setAlignment(Pos.CENTER);
        ordersBox.setStyle("-fx-background-color: #000000; -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 2);");

        ScrollPane scrollPane = new ScrollPane(ordersBox);
        scrollPane.setStyle(
        "-fx-border-color: #b2883d;" +   // subtle dark border
        "-fx-border-width: 1;" +         // 1px thin border
        "-fx-border-radius: 5;"+          // optional rounded corners
        "-fx-background-color: transparent;" // background color
        );
        
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
        scrollPane.setFocusTraversable(false);

        // Add the scrollPane to the root VBox
        root.getChildren().add(scrollPane);

        for (Order order : OrderTracker.getOrders()) {
            VBox orderBox = new VBox(5);
            orderBox.setStyle(
            "-fx-background-color: rgb(29, 0, 0);" +
            "-fx-background-radius: 10;" +
            "-fx-border-color: rgb(54, 0, 0);" +
            "-fx-border-radius: 10;" +
            "-fx-padding: 12px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0.2, 0, 1);"
            );

            Text header = UIFactory.createBoldText("Order #" + order.getId() + " - $" + order.getTotal(), 16, "#000000");
            Text status = UIFactory.createBoldText(order.getStatus(), 14, "#b2883d");
            statusTexts.add(status);

            orderBox.getChildren().addAll(header, status);
            ordersBox.getChildren().add(orderBox);
        }

        Button ok = UIFactory.createButton("OK", StyleUtil.button(), StyleUtil.hover());
        UIFactory.applyHoverZoomEffect(ok);
        ok.setOnAction(e -> modal.close());

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        root.getChildren().addAll(ordersBox, spacer, ok);

        Scene scene = new Scene(root, 500, 400);
        modal.setScene(scene);
        modal.show();

        // Update status every 5 seconds (for demo/testing)
        for (int i = 0; i < OrderTracker.getOrders().size(); i++) {
            final int index = i; // Capture the current index for use in the lambda expressions, must be final since we are using lambda expressions
            Order order = OrderTracker.getOrders().get(i);

            // status starts as "Order Received"
            PauseTransition t1 = new PauseTransition(Duration.seconds(5));
            t1.setOnFinished(e -> {
                order.setStatus("Being Prepared");
                statusTexts.get(index).setText(order.getStatus());
            });

            PauseTransition t2 = new PauseTransition(Duration.seconds(10));
            t2.setOnFinished(e -> {
                order.setStatus("On the Way");
                statusTexts.get(index).setText(order.getStatus());
            });

            PauseTransition t3 = new PauseTransition(Duration.seconds(15));
            t3.setOnFinished(e -> {
                order.setStatus("Delivered");
                statusTexts.get(index).setText(order.getStatus());
            });

            t1.play();
            t2.play();
            t3.play();
        }
    }
}
