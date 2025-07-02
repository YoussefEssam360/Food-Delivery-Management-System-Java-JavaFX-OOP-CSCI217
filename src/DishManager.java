import javafx.scene.layout.VBox;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

class DishManager {
    public static void addDish(String name, String price, String desc, String type, VBox container) {
        dishes newDish = new dishes(name, Double.parseDouble(price), desc, type);
        dishes.allDishes.add(newDish);
        container.getChildren().add(DishUIFactory.createDishBox(newDish, type, container));
    }

    public static void updateDish(dishes dish, String name, String price, String desc, String newType) {
        String oldType = dish.getType();

        dish.setName(name);
        dish.setDescription(desc);
        dish.setPrice(Double.parseDouble(price));

        if (!oldType.equals(newType)) {
            dish.setType(newType);
        }
    }

    public static boolean validate(String name, String price, String desc, String type) {
        if (name.isEmpty() || price.isEmpty() || desc.isEmpty() || type == null) return false;
        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static List<dishes> getList(String type) {
        return dishes.allDishes.stream()
            .filter(d -> d.getType().equals(type))
            .toList();
    }

    public static String getDishType(dishes dish) {
        return dish.getType();
    }

    public static void saveDishesToFile() {
        Gson gson = new Gson();
        String json = gson.toJson(dishes.allDishes);

        try {
            Files.write(
                Paths.get("dishes.json"),
                json.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );
            System.out.println("Dishes saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving dishes:" + e.getMessage());
        }
    }

    public static void loadDishesFromFile() {
        try {
            String json = Files.readString(Paths.get("dishes.json"), StandardCharsets.UTF_8); // Read the file content
            Gson gson = new Gson();
            Type dishListType = new TypeToken<List<dishes>>() {}.getType(); //returns the type of the list that java forgot in runtime
            List<dishes> loaded = gson.fromJson(json, dishListType); // Converts the JSON string to a list of real dishes objects

            dishes.allDishes.clear();
            dishes.allDishes.addAll(loaded);

            System.out.println("Dishes loaded from file.");
        } catch (IOException e) {
            System.out.println("No dishes file found or failed to load.");
        }
    }
}