import java.util.ArrayList;
import java.util.List;

public class OrderTracker {
    private static List<Order> orders = new ArrayList<>();
    private static int orderCounter = 1;

    public static void addOrder(Order order) {
        orders.add(order);
        orderCounter++;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static int getNextOrderId() {
        return orderCounter;
    }
}

























//import javafx.application.Platform;
//import javafx.util.Duration;
//import javafx.animation.PauseTransition;

    // updateStatus(order);


    // private static void updateStatus(Order order) {
    //     PauseTransition stage1 = new PauseTransition(Duration.seconds(5)); // was 5min
    //     PauseTransition stage2 = new PauseTransition(Duration.seconds(10));
    //     PauseTransition stage3 = new PauseTransition(Duration.seconds(15));

    //     stage1.setOnFinished(e -> order.setStatus("Being Prepared"));
    //     stage2.setOnFinished(e -> order.setStatus("On the Way"));
    //     stage3.setOnFinished(e -> order.setStatus("Delivered"));

    //     stage1.play();
    //     stage2.playFromStart();
    //     stage3.playFromStart();
    // }