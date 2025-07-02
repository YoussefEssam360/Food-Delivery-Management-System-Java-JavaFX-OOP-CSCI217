public class SalesTracker {
    private static double totalSales = 0;

    public static void addSale(double amount) {
        totalSales += amount;
    }

    public static double getTotalSales() {
        return totalSales;
    }
}
