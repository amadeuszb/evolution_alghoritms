package javaFxApp;

import javafx.scene.chart.XYChart;
import model.Individual;
import model.SolutionScore;
import solution.SolutionModel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.util.List;


public class PlotController {
    public Label groupNameLabel;
    public Button backButton;

    public LineChart<String, Double> chart;

    public TextField numberOfSessionsField;

    private Stage myOwnStage;
    private SolutionModel solutionModel;
    private Scene prevScene;
    private SolutionScore solutionScore;
    private Stage primaryStage;

    public void initStagePlot(Stage stage, SolutionModel solutionModel, int amountOfEras) {
        numberOfSessionsField.setTextFormatter(new TextFormatter<Number>(new NumberStringConverter()));
        primaryStage = stage;
        prevScene = stage.getScene();
        myOwnStage = stage;
        this.solutionModel = solutionModel;
        solutionScore = solutionModel.learn(amountOfEras);
        groupNameLabel.setText("Time Of Calculations: " + solutionScore.getTimeOfExecution() + "ms");
        setChartData();
    }

    public void setChartData() {
        int eras = solutionScore.getEpochs().get(0).size(); //TODO: mocked
        XYChart.Series<String, Double> seriesOfScores = new XYChart.Series<>();
        seriesOfScores.setName("Score");
        List<Individual> scores = solutionScore.getEpochs().get(0);
        long actual = 0;
        for(Individual individual: scores){
            actual++;
            seriesOfScores.getData().add(new XYChart.Data<String, Double>(String.valueOf(actual), individual.getY()));
        }
        chart.getData().clear();
        chart.getData().add(seriesOfScores);
    }

    public void handleOnButtonBack(ActionEvent actionEvent) {
        primaryStage.setScene(prevScene);
    }
}
