package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();

        primaryStage.setTitle("Agent Information");
        primaryStage.setScene(new Scene(root, 345, 415));
        primaryStage.show();

        controller.setIDs();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
