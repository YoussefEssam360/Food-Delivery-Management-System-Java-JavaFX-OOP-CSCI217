import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Priority;
//import java.util.List;
//import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class adminPage {
        private VBox dishContainer = new VBox(20); // current dishes container with spacing between each dish. will be inside the scroll pane. passed to the DishUIFactory
        
        public Scene createScene(Stage stage) {
/*----------------------------------------------------------------- Root -----------------------------------------------------------------*/  
        VBox root = new VBox(); // the area containing both the header and the main containers
        root.setStyle("-fx-background-color: white; -fx-padding: 0px;");

/*---------------------------------------------------------------- Main Header -----------------------------------------------------------------*/        
        HBox MainHeader = new HBox(20); // the main header container
        MainHeader.setStyle("-fx-background-color:rgb(29, 0, 0); -fx-padding: 15px; -fx-border-width: 5; -fx-border-color: black;"); // all the effects inside and control the padding, add bottom black border and rounded corners
        MainHeader.setAlignment(Pos.CENTER_LEFT);
        
        Button dashButton = UIFactory.createButton("Dashboard", StyleUtil.DashboardButton(), StyleUtil.hover()); // Dashboard button(Top right)
        UIFactory.applyHoverZoomEffect(dashButton); // Dashboard button animation
        
        HBox rightsecBox = new HBox(10); // invisible box to push the dashboard button to the right
        rightsecBox.setAlignment(Pos.CENTER_RIGHT);
        rightsecBox.getChildren().add(dashButton);
        
        Image image = new Image(getClass().getResource("images/logo.png").toExternalForm()); // logo image
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        HBox imageBox = new HBox(); // invisible box to push the logo to the left
        imageBox.getChildren().add(imageView);
        imageBox.setAlignment(Pos.CENTER_LEFT);
        
        Button titleBtn = new Button("Foodie Admin"); // Button to switch to User Page
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
        UserMain userScreen = new UserMain();
        stage.setScene(userScreen.createScene(stage));
        });

        HBox leftsecBox = new HBox(10, imageView, titleBtn); // invisible box to push the title to the left
        leftsecBox.setAlignment(Pos.CENTER_LEFT);
        
        Region spacer = new Region(); // invisible space between the two boxes
        HBox.setHgrow(spacer, Priority.ALWAYS); // this will make the spacer take all the available space between the two boxes
        
        MainHeader.getChildren().addAll(imageBox,leftsecBox, spacer, rightsecBox); // add the two boxes with the spacer in between, to the header box

/*----------------------------------------------------------------- Main Container -----------------------------------------------------------------*/  
    HBox MainContainer = new HBox(); // main container(area under the header)
    MainContainer.setStyle("-fx-background-color: #FFFFFF;");

/*----------------------------------------------------------------- Main Container and Main Header merge -----------------------------------------------------------------*/  
        VBox.setVgrow(MainContainer, Priority.ALWAYS);
        root.getChildren().addAll(MainHeader, MainContainer);
/*---------------------------------------------------------------- Sidebar -----------------------------------------------------------------*/
        Text Manage_Dishes = UIFactory.createBoldText("Manage Dishes", 20, "#FFFFFF");

        // Sidebar content container
        VBox sidebar = new VBox(15);
        sidebar.setPrefWidth(320);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(20));
        sidebar.getChildren().add(Manage_Dishes); // We'll add button later

        // Add Dish button
        Button addDishBtn = UIFactory.createButton("Add Dish", StyleUtil.button(), StyleUtil.hover());
        UIFactory.applyHoverZoomEffect(addDishBtn);
        addDishBtn.setMaxWidth(Double.MAX_VALUE); // Fill width
        sidebar.getChildren().add(addDishBtn);

        // Background image region
        Region backgroundImage = new Region();
        backgroundImage.setStyle(
        "-fx-background-image: url('/images/elegant_table.png');" +
        "-fx-background-repeat: no-repeat;" +
        "-fx-background-size: cover;" +
        "-fx-background-position: center center;"
        );
        backgroundImage.setPrefSize(320, 929);

        // Fade overlay below content
        Region fadeOverlay = new Region();
        fadeOverlay.setStyle("-fx-background-color: rgba(0,0,0,0.25);");
        fadeOverlay.setPrefSize(320, 929);

        // Make both layers ignore mouse clicks
        backgroundImage.setMouseTransparent(true);
        fadeOverlay.setMouseTransparent(true);

        // Stacking : [image -> fade -> sidebar content]
        StackPane sidebarContainer = new StackPane();
        sidebarContainer.setPrefSize(320, 929);
        sidebarContainer.getChildren().addAll(backgroundImage, fadeOverlay, sidebar);

/*--------------------------------------------------------------------------- Current Container -----------------------------------------------------------------------------*/
        VBox currentContainer = new VBox(20); // main current container including the current dishes container header and the current dishes container
        currentContainer.setStyle("-fx-background-color: #000000; -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 2);");
        currentContainer.setMaxWidth(Double.MAX_VALUE);

/*----------------------------------------------------------------- Current Container Header -----------------------------------------------------------------*/  
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
        HBox.setHgrow(currentContainer, Priority.ALWAYS); // make the current container fill the available space

/*----------------------------------------------------------- ScrollPane with dishContainer inside -----------------------------------------------------------*/  
        // Current Dishes Container (Initialization moved up to class fields to be accessible outside)
        dishContainer.setStyle("-fx-background-color: black; -fx-padding: 10 20px;");
        dishContainer.setMaxWidth(Double.MAX_VALUE); // make the current dishes container fill the available space

        ScrollPane scrollPane = new ScrollPane(dishContainer); // scroll pane to store the current dishes container
        scrollPane.setStyle("-fx-border-color: #b2883d;" +"-fx-border-width: 1;" +"-fx-border-radius: 5;"+"-fx-background-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS); 
        scrollPane.setFitToWidth(true); // make the scroll pane fill the available space
        scrollPane.setFitToHeight(true); // make the scroll pane fill the available space
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Vertical scrollbar when needed
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);    // Disable horizontal scrollbar
        scrollPane.setPannable(true); 
        scrollPane.setFocusTraversable(false); // Disable focus on the scroll pane
        currentContainer.getChildren().addAll(currentContainerHeader,scrollPane); // add the current dishes container header and the scrollpane containing the current dishes container to the current container

/*----------------------------------------------------------------- Sidebar and CurrentContainer merge -----------------------------------------------------------------*/  
        MainContainer.getChildren().addAll(sidebarContainer, currentContainer);

/*----------------------------------------------------------------- Action Buttons -----------------------------------------------------------------*/  
        dashButton.setOnAction(e -> {
        TotalSalesModal.show(stage);
        }); // Dashboard button action

        addDishBtn.setOnAction(e -> new DishForm("Add", dishContainer, null).show()); // Add Dish button action

/*----------------------------------------------------------------- Scene end -----------------------------------------------------------------*/          
        return new Scene(root, 1280, 720); 
        }

        public void deletButtonClicked(HBox dishBox, String name) {
                dishBox.setVisible(false);
        }

/*----------------------------------------------------------------- some getters -----------------------------------------------------------------*/          
        public VBox getDishContainer() {
        return dishContainer;
        }

}



/* removed line between current dishes container header and the current dishes container:
        Region lineContainer = new Region();
        lineContainer.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: black;");
        HBox.setHgrow(lineContainer, Priority.ALWAYS);
*/