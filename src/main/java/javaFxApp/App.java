package javaFxApp;

import Solution.SolutionModelBuilder;
import crossover.CrossoverType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sample.fxml"));
        Parent root = loader.load();
        Controller mainController = loader.<Controller>getController();
        mainController.initStage(stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
