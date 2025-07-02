import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TotalSalesModal {
    public static void show(Stage owner) {
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.initOwner(owner);
        modal.setTitle("Order Status");
        modal.setResizable(false);

        VBox root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: rgb(29, 0, 0);");

        Text totalSales = UIFactory.createBoldText("Total Sales: $" + SalesTracker.getTotalSales(), 26, "#000000");

        Button ok = UIFactory.createButton("OK", StyleUtil.button(), StyleUtil.hover());
        UIFactory.applyHoverZoomEffect(ok);
        ok.setOnAction(e -> modal.close());

        root.getChildren().addAll(totalSales, ok);

        Scene scene = new Scene(root, 400, 200);
        modal.setScene(scene);
        modal.show();
    }
}
