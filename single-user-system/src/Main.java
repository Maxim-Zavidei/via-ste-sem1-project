package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("MMMMMM");
    primaryStage.setScene(new Scene(root, 1200, 675));
    primaryStage.show();
    primaryStage.setMaxWidth(primaryStage.getWidth());
    primaryStage.setMinWidth(primaryStage.getWidth());
    primaryStage.setMaxHeight(primaryStage.getHeight());
    primaryStage.setMinHeight(primaryStage.getHeight());
  }






  public static void main(String[] args) {
    launch(args);
  }
}
