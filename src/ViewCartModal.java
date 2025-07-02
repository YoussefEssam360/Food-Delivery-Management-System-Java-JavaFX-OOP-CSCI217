import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewCartModal {
    public static void showCart(Stage owner) {
        Stage cartStage = new Stage();
        cartStage.initModality(Modality.APPLICATION_MODAL);
        cartStage.initOwner(owner);
        cartStage.setTitle("Your Cart");
        cartStage.setResizable(false);

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: rgb(29, 0, 0);");

        VBox cartItemsBox = new VBox(10);
        cartItemsBox.setStyle("-fx-background-color: #000000;-fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 2);");
        Text errorMsg = UIFactory.createBoldText("", 14, "red");

        ScrollPane scrollPane = new ScrollPane(cartItemsBox);
        scrollPane.setStyle(
        "-fx-border-color: #b2883d;" +   // subtle dark border
        "-fx-border-width: 1;" +         // 1px thin border
        "-fx-border-radius: 5;"+          // optional rounded corners
        "-fx-background-color: transparent;" // background color
        );

        VBox.setVgrow(scrollPane, Priority.ALWAYS); 
        scrollPane.setFitToWidth(true); // make the scroll pane fill the available space
        scrollPane.setFitToHeight(true); // make the scroll pane fill the available space
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Vertical scrollbar when needed
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    // Disable horizontal scrollbar
        scrollPane.setPannable(true); 
        scrollPane.setFocusTraversable(false); // Disable focus on the scroll pane
        
        root.getChildren().add(scrollPane); // Add the scroll pane to the root VBox

        for (dishes d : CartManager.getCart()) {
            HBox row = new HBox(20);
            row.setStyle(
            "-fx-background-color: rgb(29, 0, 0);" +
            "-fx-background-radius: 10;" +
            "-fx-border-color: rgb(54, 0, 0);" +
            "-fx-border-radius: 10;" +
            "-fx-padding: 12px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0.2, 0, 1);");
            
            Text name = UIFactory.createBoldText(d.getName(), 25, "#b2883d");
            name.setFont(Font.font("Garamond", FontWeight.BOLD, FontPosture.ITALIC,25));
            Text price = UIFactory.createBoldText("$" + d.getPrice(), 25, "#b2883d");
            price.setFont(Font.font("Garamond", FontWeight.BOLD, FontPosture.ITALIC,25));
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            Button removeBtn = UIFactory.createButton("Remove", StyleUtil.button(), StyleUtil.hover());
            UIFactory.applyHoverZoomEffect(removeBtn);
            removeBtn.setOnAction(e -> {
                CartManager.removeFromCart(d);
                cartStage.close();
                showCart(owner);
            });
            row.getChildren().addAll(name, spacer, price, removeBtn);
            cartItemsBox.getChildren().add(row);
        }

        Text total = UIFactory.createBoldText("Total: $" + CartManager.getTotal(), 16, "#000000");

        Button checkout = UIFactory.createButton("Checkout", StyleUtil.button(), StyleUtil.hover());
        UIFactory.applyHoverZoomEffect(checkout);
        
        checkout.setOnAction(e -> {
            try {
                CartManager.checkout();
                cartStage.close();
            } catch (Exception ex) {
                errorMsg.setText(ex.getMessage());
            }
        });

        Button cancel = UIFactory.createButton("Close", StyleUtil.button(), StyleUtil.hover());
        UIFactory.applyHoverZoomEffect(cancel);
        cancel.setOnAction(e -> cartStage.close());

        HBox actions = new HBox(10, checkout, cancel);
        actions.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().addAll(cartItemsBox, errorMsg, total, actions);

        Scene scene = new Scene(root, 500, 400);
        cartStage.setScene(scene);
        cartStage.show();
    }
}
