class StyleUtil {
    public static String button() {
        return "-fx-background-color: #000000;" +
            "-fx-text-fill: white;" +
            "-fx-padding: 6px 12px;" +
            "-fx-background-radius: 6px;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;";
    }

    public static String DashboardButton() {
        return "-fx-background-color: #000000;" +
            "-fx-text-fill: white;" +
            "-fx-padding: 6px 12px;" +
            "-fx-background-radius: 6px;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-width: 2px;";
    }

    public static String hover() {
        return "-fx-background-color: #483D8B;" +
            "-fx-text-fill: #FFFFFF;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 0, 2);";
    }

    public static String sidebarButton() {
        return "-fx-background-color: #FFFFFF;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 12px;" +
            "-fx-background-radius: 12px;" +
            "-fx-padding: 12 20 12 20;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #000000;";
    }

    public static String sidebarHover() {
        return StyleUtil.sidebarButton() +
            "-fx-background-color: #6A5ACD;" +
            "-fx-text-fill: #FFFFFF;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.5, 0, 2);";
    }



    public static String input() {
        return "-fx-background-color: white;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 15;" +
            "-fx-background-radius: 15;" +
            "-fx-padding: 10px;" +
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #333333;";
    }


    public static String comboBox() {
        return "-fx-background-color: white;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 10px;" +
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #333333;";
    }
}