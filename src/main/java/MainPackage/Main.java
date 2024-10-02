package MainPackage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        WikipediaRevisionParser revisionParser = new WikipediaRevisionParser();

        //Creates base scene
        VBox parent = new VBox();
        parent.getChildren().add(new Label("FIND REVISIONS"));

        //User puts Article name
        HBox articleName = new HBox(new Label("Wiki Article: "));
        TextField textField = new TextField();

        articleName.getChildren().add(textField);
        parent.getChildren().add(articleName);

        //Button Activates Parse
        Button button = new Button("PARSE DATA");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent articleTitle) {
                String titleArticle = textField.getText();
                System.out.println("====================================================");
                try{
                    revisionReader.fetchRecentEdits(titleArticle);
                    String parsedData = revisionParser.parseEdits(InputStream.nullInputStream());
                    textField.setText(parsedData);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                primaryStage.setTitle("Wikipedia Revision Tracker");
                primaryStage.show();

            }

        });
        parent.getChildren().add(button);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }
    private void showResults(String data){
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Parsed Wikipedia Revisions");
    }

}
