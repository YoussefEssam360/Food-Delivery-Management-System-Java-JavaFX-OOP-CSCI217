import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<dishes> cart = new ArrayList<>();

    public static void addToCart(dishes dish) {
        cart.add(dish);
    }

    public static void removeFromCart(dishes dish) {
        cart.remove(dish);
    }

    public static List<dishes> getCart() {
        return cart;
    }

    public static double getTotal() {
        double total = 0;
        for (dishes d : cart) {
            total += d.getPrice();
        }
        return total;
    }

    public static void checkout() throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty. Please add items before checking out.");
        }

        int newId = OrderTracker.getNextOrderId();
        Order newOrder = new Order(newId, new ArrayList<>(cart), getTotal());
        OrderTracker.addOrder(newOrder);
        SalesTracker.addSale(newOrder.getTotal());
        cart.clear();
    }

}

