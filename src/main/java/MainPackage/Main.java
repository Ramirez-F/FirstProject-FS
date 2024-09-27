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
        //connects WikipediaRevisionReader to GUI

        VBox parent = new VBox();
        parent.getChildren().add(new Label("FIND REVISIONS"));
        //Creates base scene

        HBox articleName = new HBox(new Label("Wiki Article: "));
        TextField textField = new TextField();
        articleName.getChildren().add(textField);
        parent.getChildren().add(articleName);

        Button button = new Button("PARSE DATA");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent articleTitle) {
                String titleArticle = textField.getText();
                System.out.println("====================================================");
                try{
                    revisionReader.fetchRecentEdits(titleArticle);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        parent.getChildren().add(button);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }

}
