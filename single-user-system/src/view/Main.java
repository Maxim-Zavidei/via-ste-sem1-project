package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("ColorIT Project Management System");
    primaryStage.setScene(new Scene(root, 1200, 675));
    primaryStage.setMaxWidth(primaryStage.getWidth());
    primaryStage.setMinWidth(primaryStage.getWidth());
    primaryStage.setMaxHeight(primaryStage.getHeight());
    primaryStage.setMinHeight(primaryStage.getHeight());
    primaryStage.show();
  }
}