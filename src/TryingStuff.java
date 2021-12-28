
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

/**
 * @author Sergei Noivko
 * @version 1.0
 */

public class TryingStuff extends Application {

    private Button b1 = new Button("ok");
    /**
     * start method for the application.
     * @param primaryStage initial Stage
     */
    public void start(Stage primaryStage) {
        ObservableList<String> list = FXCollections.observableArrayList();
        ListView<String> l = new ListView<>(list);


        TextField name = new TextField();
        name.setPromptText("Enter your name");
        TextField password = new TextField();
        password.setPromptText("Enter you LastName");
        Text name1 = new Text("Name");
        Text password1 = new Text("Password");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5);
        pane.setVgap(5);
        pane.add(name1, 0, 0);
        pane.add(password1, 0, 1);
        pane.add(name, 1, 0);
        pane.add(password, 1, 1);




        Button add = new Button("ADD");
        pane.add(add, 3, 2);



        CheckBox yes = new CheckBox();
        CheckBox no = new CheckBox();

        RadioButton c1 = new RadioButton("Italian");
        RadioButton c3 = new RadioButton("German");
        RadioButton c2 = new RadioButton("Russian");

        ToggleGroup group = new ToggleGroup();
        c1.setToggleGroup(group);
        c2.setToggleGroup(group);
        c3.setToggleGroup(group);




        pane.add(yes, 3, 0);
        pane.add(no, 3, 1);

        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (yes.isSelected()) {
                    name1.setFill(Color.GREEN);
                }
                if (no.isSelected()) {
                    password1.setFill(Color.GREEN);
                }
                if (!(yes.isSelected()) ) {
                    name1.setFill(Color.BLACK);
                }
                if (!(no.isSelected())) {
                    password1.setFill(Color.BLACK);
                }
            }

        };
        yes.setOnAction(handler);
        no.setOnAction(handler);
        add.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please go fuck yourself");
            alert.setTitle("Bitch");
            alert.setHeaderText("Suka");
            alert.show();

        });
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * main mtheod
     * @param args arguments past from stage
     */
    public static void main(String[] args) {
        launch(args);
    }


}
