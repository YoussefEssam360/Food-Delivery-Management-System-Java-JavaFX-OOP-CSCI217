import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DishForm {
    private Stage formStage;
    private VBox container;
    private dishes existingDish;
    private String mode; // "Add" or "Edit"
    
    public DishForm(String mode, VBox container, dishes dish) {
        this.mode = mode;
        this.container = container;
        this.existingDish = dish;
    }

    public void show() {
        formStage = new Stage();
        formStage.setTitle(mode + " Dish");
        formStage.initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #E8E8FC; -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 2);");
        root.setPadding(new Insets(30));

        Text validationMessage = UIFactory.createBoldText("", 14, "#CC0000");  // red error text

        Text title = UIFactory.createBoldText(mode + " Dish", 24, "#333333");
        TextField nameField = UIFactory.createTextField("Name", existingDish != null ? existingDish.getName() : "");
        TextField priceField = UIFactory.createTextField("Price", existingDish != null ? String.valueOf(existingDish.getPrice()) : "");
        TextField descField = UIFactory.createTextField("Description", existingDish != null ? existingDish.getDescription() : "");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.setStyle(StyleUtil.comboBox());

        typeBox.getItems().addAll("Appetizer", "Main Course", "Dessert", "Beverage");
        typeBox.setPromptText("Select Dish Type");
        if (existingDish != null) typeBox.setValue(DishManager.getDishType(existingDish));

        Button save = UIFactory.createButton("Save");
        Button cancel = UIFactory.createButton("Cancel");
        UIFactory.applyHoverZoomEffect(save);
        UIFactory.applyHoverZoomEffect(cancel);

        save.setOnAction(e -> {
            String name = nameField.getText();
            String price = priceField.getText();
            String desc = descField.getText();
            String type = typeBox.getValue();

        if (!DishManager.validate(name, price, desc, type)) { // validate inputs
            validationMessage.setText("Please fill all fields correctly.");
            return;
        }

        validationMessage.setText(""); // clear error if any

        if (mode.equals("Add")) {
            DishManager.addDish(name, price, desc, type, container);
        } else {
            DishManager.updateDish(existingDish, name, price, desc, type);
        }
        DishManager.saveDishesToFile(); // save to file after adding/updating
        DishUIFactory.refreshDishUI(container); // refresh UI to menu changes
        formStage.close(); // close the form
        });

        cancel.setOnAction(e -> formStage.close()); // close the form

        root.getChildren().addAll(title, validationMessage, nameField, priceField, descField, typeBox, new HBox(10, save, cancel));

        Scene scene = new Scene(root, 650, 500);

        formStage.setScene(scene);
        formStage.setResizable(false);
        formStage.show();
    }
}
