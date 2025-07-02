import java.util.List;
import java.util.ArrayList;

public class dishes {
    private String name;
    private String description;
    private double price;
    private String type;

    public static List<dishes> allDishes = new ArrayList<>();

    public dishes(String name, double price, String description, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
