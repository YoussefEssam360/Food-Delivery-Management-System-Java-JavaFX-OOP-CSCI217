import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

class UIFactory {
    // Default bold text — uses black
    public static Text createBoldText(String text, int size) {
        return createBoldText(text, size, "#000000");
    }

    // Custom bold text color
    public static Text createBoldText(String text, int size, String colorHex) {
        Text t = new Text(text);
        t.setFont(Font.font("Arial", FontWeight.BOLD, size));
        t.setFill(Color.web(colorHex));
        return t;
    }

    // Default styled button with hover
    public static Button createButton(String label) {
        Button b = new Button(label);
        String style = StyleUtil.button();
        String hover = StyleUtil.hover();
        b.setStyle(style);
        b.setOnMouseEntered(e -> b.setStyle(hover));
        b.setOnMouseExited(e -> b.setStyle(style));
        return b;
    }

    // Custom style + custom hover
    public static Button createButton(String label, String style, String hoverStyle) {
        Button b = new Button(label);
        b.setStyle(style);
        b.setOnMouseEntered(e -> b.setStyle(hoverStyle != null ? hoverStyle : style));
        b.setOnMouseExited(e -> b.setStyle(style));
        return b;
    }

    // Default text field
    public static TextField createTextField(String prompt, String initial) {
        TextField tf = new TextField(initial);
        tf.setPromptText(prompt);
        tf.setStyle(StyleUtil.input());
        return tf;
    }

    // Custom styled text field
    public static TextField createTextField(String prompt, String initial, String style) {
        TextField tf = new TextField(initial);
        tf.setPromptText(prompt);
        tf.setStyle(style != null ? style : StyleUtil.input());
        return tf;
    }

    public static void applyHoverZoomEffect(Button button) {
        ScaleTransition zoomIn = new ScaleTransition(Duration.millis(150), button);
        zoomIn.setToX(1.1);
        zoomIn.setToY(1.1);

        ScaleTransition zoomOut = new ScaleTransition(Duration.millis(150), button);
        zoomOut.setToX(1.0);
        zoomOut.setToY(1.0);

        button.setOnMouseEntered(e -> zoomIn.playFromStart());
        button.setOnMouseExited(e -> zoomOut.playFromStart());
    }

}
