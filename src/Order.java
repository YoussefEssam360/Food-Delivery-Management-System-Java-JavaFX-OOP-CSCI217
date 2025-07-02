import java.util.List;

public class Order {
    private int id;
    private List<dishes> items;
    private double total;
    private String status;

    public Order(int id, List<dishes> items, double total) {
        this.id = id;
        this.items = items;
        this.total = total;
        this.status = "Order Received";
    }

    public int getId() { return id; }
    public List<dishes> getItems() { return items; }
    public double getTotal() { return total; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
