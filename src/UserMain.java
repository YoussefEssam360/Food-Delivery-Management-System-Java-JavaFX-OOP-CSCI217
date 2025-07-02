import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;

public class UserMain {
    private VBox dishContainer = new VBox(20);

    public Scene createScene(Stage stage) {
        VBox root = new VBox();
        root.setStyle("-fx-background-color: white;");
        // ---------------------------- HEADER ----------------------------
        HBox header = new HBox(20);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color:rgb(29, 0, 0); -fx-border-width: 5; -fx-border-color: black;");
        header.setAlignment(Pos.CENTER_LEFT);

        Image logo = new Image(getClass().getResource("images/logo.png").toExternalForm());
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(60);
        logoView.setFitHeight(60);

        Button titleBtn = new Button("Foodie User"); // Button to switch to admin page
        titleBtn.setFont(Font.font("Palatino", FontWeight.BOLD, FontPosture.ITALIC, 40));
        titleBtn.setTextFill(Color.WHITE);
        titleBtn.setStyle(
            "-fx-background-color: black;" +
            "-fx-background-radius: 8px;" +
            "-fx-padding: 4px 12px;" +
            "-fx-border-radius: 8px;" +
            "-fx-cursor: hand;"
        );
        titleBtn.setFocusTraversable(false);  // no focus glow
        titleBtn.setBorder(Border.EMPTY);     // remove border

        UIFactory.applyHoverZoomEffect(titleBtn);  // animation

        titleBtn.setOnAction(e -> {
            adminPage adminScreen = new adminPage();
            DishUIFactory.refreshDishUI(adminScreen.getDishContainer());
            stage.setScene(adminScreen.createScene(stage));
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Button to view Order Status
        Button statusButton = UIFactory.createButton("Orders", StyleUtil.DashboardButton(), StyleUtil.hover());
        UIFactory.applyHoverZoomEffect(statusButton);
        statusButton.setOnAction(e -> OrderStatusModal.show(stage));

        // Button to view Cart
        Button cartButton = UIFactory.createButton("Cart", StyleUtil.DashboardButton(), StyleUtil.hover());
        UIFactory.applyHoverZoomEffect(cartButton);
        cartButton.setOnAction(e -> ViewCartModal.showCart(stage));

        header.getChildren().addAll(logoView, titleBtn, spacer, statusButton, cartButton); // add/organize header buttons to the header

        // ---------------------------- MAIN ----------------------------
        HBox currentContainerHeader = new HBox(20); // current dishes container header
        currentContainerHeader.setMaxWidth(Double.MAX_VALUE);
        currentContainerHeader.setStyle(
            "-fx-background-color: rgb(29, 0, 0);" +  /* Match sidebar (light purple) */ 
            "-fx-background-radius: 12px;" +   /* Rounded corners */
            "-fx-border-radius: 12px;" +       /* Rounded border corners if borders are used */
            "-fx-padding: 10px 20px;"          /* Internal spacing */
        );

        // Header Texts and their spacers
        Text Name = UIFactory.createBoldText("Name", 30, "#b2883d");
        Name.setFont(Font.font("Garamond", FontWeight.BOLD,FontPosture.ITALIC,30));
        Region nameSpacer = new Region();
        HBox.setHgrow(nameSpacer, Priority.ALWAYS);
        Text Price = UIFactory.createBoldText("Price", 30, "#b2883d");
        Price.setFont(Font.font("Garamond", FontWeight.BOLD,FontPosture.ITALIC,30));
        Region priceSpacer = new Region();
        HBox.setHgrow(priceSpacer, Priority.ALWAYS);
        Text Type = UIFactory.createBoldText("Type", 30, "#b2883d");
        Type.setFont(Font.font("Garamond", FontWeight.BOLD,FontPosture.ITALIC,30));
        Region typeSpacer = new Region();
        HBox.setHgrow(typeSpacer, Priority.ALWAYS);
        Text Actions = UIFactory.createBoldText("Actions", 30, "#b2883d");
        Actions.setFont(Font.font("Garamond", FontWeight.BOLD,FontPosture.ITALIC,30));

        //Adding children to the current container header
        currentContainerHeader.getChildren().addAll(Name, nameSpacer, Price, priceSpacer, Type, typeSpacer, Actions); // add the header texts to the current container header

        HBox main = new HBox();
        main.setStyle("-fx-background-color: #FFFFFF;");

        VBox currentContainer = new VBox(20);
        currentContainer.setStyle("-fx-background-color: black; -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 2);");
        HBox.setHgrow(currentContainer, Priority.ALWAYS);

        dishContainer.setStyle(
            "-fx-background-color: black;" +
            "-fx-border-color: black;" +
            "-fx-padding: 10px 20px;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0.2, 0, 1);"
        );

        ScrollPane scrollPane = new ScrollPane(dishContainer);
        scrollPane.setStyle("-fx-border-color: #b2883d; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS); 
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
        scrollPane.setFocusTraversable(false);
        scrollPane.setMaxWidth(Double.MAX_VALUE);
        

        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        currentContainer.getChildren().addAll(currentContainerHeader,scrollPane);

        VBox sidebar = new VBox(15);
        sidebar.setPrefWidth(320);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(20));
        sidebar.getChildren().add(UIFactory.createBoldText("Available Dishes", 20, "#FFFFFF"));

        Region backgroundImage = new Region();
        backgroundImage.setStyle("-fx-background-image: url('/images/elegant_table.png'); -fx-background-repeat: no-repeat; -fx-background-size: cover; -fx-background-position: center center;");
        backgroundImage.setPrefSize(320, 929);
        backgroundImage.setMouseTransparent(true);

        Region fadeOverlay = new Region();
        fadeOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.25);");
        fadeOverlay.setPrefSize(320, 929);
        fadeOverlay.setMouseTransparent(true);

        StackPane sidebarContainer = new StackPane();
        sidebarContainer.setPrefSize(320, 929);
        sidebarContainer.getChildren().addAll(backgroundImage, fadeOverlay, sidebar);

        main.getChildren().addAll(sidebarContainer, currentContainer);
        VBox.setVgrow(main, Priority.ALWAYS);

        root.getChildren().addAll(header, main);

        populateDishes();
        return new Scene(root, 1280, 720);
    }

    private void populateDishes() {
        dishContainer.getChildren().clear();
        for (String type : List.of("Appetizer", "Main Course", "Dessert", "Beverage")) {
            for (dishes d : DishManager.getList(type)) {
                HBox dishBox = new HBox(20);
                dishBox.setStyle(
                    "-fx-background-color: rgb(29, 0, 0);" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-color: rgb(54, 0, 0);" +
                    "-fx-border-radius: 10;" +
                    "-fx-padding: 12px;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0.2, 0, 1);"
                );
                Text name = UIFactory.createBoldText(d.getName(), 25, "#b2883d");
                name.setFont(Font.font("Garamond", FontWeight.BOLD, FontPosture.ITALIC,25));
                Text price = UIFactory.createBoldText("$" + d.getPrice(), 25, "#b2883d");
                price.setFont(Font.font("Garamond", FontWeight.BOLD, FontPosture.ITALIC,25));
                Text category = UIFactory.createBoldText(type, 25, "#b2883d");
                category.setFont(Font.font("Garamond", FontWeight.BOLD, FontPosture.ITALIC,25));

                name.setWrappingWidth(200);   // aligns with Name column
                price.setWrappingWidth(150);  // aligns with Price column
                category.setWrappingWidth(150);    // aligns with Type column

                Region nameSpacer = new Region();
                Region priceSpacer = new Region();
                Region typeSpacer = new Region();

                HBox.setHgrow(nameSpacer, Priority.ALWAYS);
                HBox.setHgrow(priceSpacer, Priority.ALWAYS);
                HBox.setHgrow(typeSpacer, Priority.ALWAYS);
            
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button addToCart = UIFactory.createButton("Add to Cart", StyleUtil.button(), StyleUtil.hover());
                UIFactory.applyHoverZoomEffect(addToCart);
                addToCart.setOnAction(e -> CartManager.addToCart(d));

                dishBox.getChildren().addAll(name, nameSpacer, price, priceSpacer, category, typeSpacer, addToCart);
                dishContainer.getChildren().add(dishBox);
            }
        }
    }


} 
