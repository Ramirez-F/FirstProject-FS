package MainPackage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        VBox parent = new VBox();
        parent.getChildren().add(new Label("FIND REVISIONS"));

        HBox urlArea = new HBox(new Label("Wiki Article: "));
        TextField textField = new TextField();
        urlArea.getChildren().add(textField);
        parent.getChildren().add(urlArea);

        Button button = new Button("Parse Data");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("This is everything parsed");
                revisionReader.fetchRecentEdits(articleTitle);
            }
        });
        parent.getChildren().add(button);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

}
