//I worked on the homework assignment alone, using only course materials.

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;

/**
 * GUI application.
 * @author Sergei Novikov
 * @version 1.0
 */
public class Starbase extends Application {
    private Label title;
    private Button clearDocking;
    private ComboBox<Starship> dropDown;
    private Button evacuate;
    private TextField shipName;
    private int occupied;
    private ObservableList<Button> dockList;


    protected VBox getTopPane() {
        VBox top = new VBox();
        title.setText("Welcome to StarBase 1331!");
        top.getChildren().add(title);

        Insets i1 = new Insets(20, 0, 0, 0);
        top.setPadding(i1);
        top.setAlignment(Pos.CENTER);
        return top;
    }
    protected StackPane getBackgroundPane() {
        StackPane pane = new StackPane();
        Image image = new Image("space.jpg", 600, 420, true, true);
        //another way use imageview methods to set length and width
        ImageView view = new ImageView(image);
        pane.getChildren().add(view);
        return pane;
    }
    protected void initDocks() {

        for (int i = 0; i < 8; i++) {
            Button b = new Button("Empty");
            b.setPrefSize(100, 100);
            b.setStyle("-fx-background-color: Green");
            dockList.add(b);
            occupied = 0;
        }
    }
    @Override
    public void start(Stage primaryStage) {
        dockList = FXCollections.observableArrayList();
        ListView<Button> dockView = new ListView<>(dockList);
        initDocks();

        title = new Label("Welcome to Starbase!");
        title.setFont(new Font(22));
        title.setTextFill(Color.WHITE);
        //lower pane
        //HBox lower = new HBox();
        shipName = new TextField();
        shipName.setPromptText("Enter Ship Name");
        dropDown = new ComboBox<>();
        dropDown.getItems().addAll(Starship.values());
        dropDown.setMinWidth(150);

        clearDocking = new Button("Request Docking Clearance");
        evacuate = new Button("Evacuate");
        evacuate.setStyle("-fx-background-color: Red");

        evacuate.minWidth(150);


        //middle pane
        GridPane middle = new GridPane();
        middle.setAlignment(Pos.CENTER);
        middle.setHgap(20);
        middle.setVgap(20);
        Insets n = new Insets(10, 10, 10, 10);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                middle.add(dockList.get(i * 2 + j), i, j);
            }
        }

        clearDocking.setOnAction(e -> {
            boolean space = false;
            Starship ship = dropDown.getSelectionModel().getSelectedItem();
            String name = shipName.getText();
            if (occupied == 8) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("NO PORTS AVAILABLE");
                alert.setHeaderText("Please Wait");
                alert.setContentText(name + " has not been granted docking clearance!");
                alert.show();

            }
            if (!(name.isBlank() || ship == null)) {
                for (Button b: dockList) {
                    if (b.getText().equals("Empty")) {
                        space = true;
                        int i = dockList.indexOf(b);
                        dockList.remove(i);
                        b.setStyle("-fx-background-color: Grey");
                        b.setText(name + "\n" + ship);
                        dockList.add(i, b);
                        occupied++;
                        break;
                    }
                }
            }
            shipName.clear();
            shipName.setPromptText("Enter Ship Name");
            dropDown.getSelectionModel().select(null);
        });

        evacuate.setOnAction(e -> {
            for (Button b: dockList) {
                if (!(b.getText().equals("Empty"))) {
                    b.setText("Empty");
                    b.setStyle("-fx-background-color: Green");
                    occupied--;
                }
            }
        });
        for (Button b: dockList) {
            b.setOnAction(e -> {
                if (!(b.getText().equals("Empty"))) {
                    b.setText("Empty");
                    b.setStyle("-fx-background-color: Green");
                    occupied--;
                }
            });
        }


        //pane for lower part
        HBox lower = new HBox();
        lower.getChildren().addAll(shipName, dropDown, clearDocking, evacuate);
        Insets i2 = new Insets(0, 0, 20, 0);
        lower.setPadding(i2);
        lower.setAlignment(Pos.CENTER);
        //setting pane for middle contents

        BorderPane onpane = new BorderPane();
        onpane.setTop(getTopPane());
        onpane.setCenter(middle);
        onpane.setBottom(lower);
        StackPane p = new StackPane(getBackgroundPane(), onpane);
        Scene scene = new Scene(p);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Starbase Command");

        primaryStage.show();

    }

    /**
     * Main method is only needed for IDE with limited
     *JavaFx support.
     * @param args passed parameters to be launched
     */
    public static void main(String[] args) {
        launch(args);
    }
}
