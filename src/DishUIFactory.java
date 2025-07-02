import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Priority;

public class DishUIFactory {

    public static HBox createDishBox(dishes dish, String type, VBox container) {
        HBox dishBox = new HBox();
        dishBox.setStyle(
            "-fx-background-color: rgb(29, 0, 0);" +
            "-fx-background-radius: 10;" +
            "-fx-border-color: rgb(54, 0, 0);" +
            "-fx-border-radius: 10;" +
            "-fx-padding: 12px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0.2, 0, 1);"
        );
        
        dishBox.setMaxWidth(Double.MAX_VALUE);
        dishBox.setSpacing(10);

        Text name = UIFactory.createBoldText(dish.getName(), 25, "#b2883d");
        name.setFont(Font.font("Garamond", FontWeight.BOLD, FontPosture.ITALIC,25));
        Text price = UIFactory.createBoldText("$" + String.valueOf(dish.getPrice()), 25, "#b2883d");
        price.setFont(Font.font("Garamond", FontWeight.BOLD, FontPosture.ITALIC,25));
        Text cat = UIFactory.createBoldText(type, 25, "#b2883d");
        cat.setFont(Font.font("Garamond", FontWeight.BOLD, FontPosture.ITALIC,25));

        name.setWrappingWidth(200);   // aligns with Name column
        price.setWrappingWidth(150);  // aligns with Price column
        cat.setWrappingWidth(150);    // aligns with Type column

        Region nameSpacer = new Region();
        Region priceSpacer = new Region();
        Region typeSpacer = new Region();

        HBox.setHgrow(nameSpacer, Priority.ALWAYS);
        HBox.setHgrow(priceSpacer, Priority.ALWAYS);
        HBox.setHgrow(typeSpacer, Priority.ALWAYS);

        Button edit = UIFactory.createButton("Edit", StyleUtil.button(), StyleUtil.hover());
        Button delete = UIFactory.createButton("Delete", StyleUtil.button(), StyleUtil.hover());

        UIFactory.applyHoverZoomEffect(edit);
        UIFactory.applyHoverZoomEffect(delete);


        HBox btnSection = new HBox(10, edit, delete);

        dishBox.getChildren().addAll(name, nameSpacer, price, priceSpacer, cat, typeSpacer, btnSection);

        edit.setOnAction(e -> new DishForm("Edit", container, dish).show());

        delete.setOnAction(e -> {
            container.getChildren().remove(dishBox);
            dishes.allDishes.remove(dish);
            DishManager.saveDishesToFile();
            refreshDishUI(container);
        });

        return dishBox;
    }

    public static void refreshDishUI(VBox container) {
        container.getChildren().clear(); // Clear the container before adding new dishes

        for (String type : List.of("Appetizer", "Main Course", "Dessert", "Beverage")) {
            for (dishes d : DishManager.getList(type)) {
                container.getChildren().add(createDishBox(d, type, container));
            }
        }
    }

}
