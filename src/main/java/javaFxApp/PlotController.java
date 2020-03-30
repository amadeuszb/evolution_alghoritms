package javaFxApp;

import javafx.scene.chart.XYChart;
import model.SolutionScore;
import solution.SolutionModel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;


public class PlotController {
    public Label groupNameLabel;
    public Button backButton;

    public LineChart<String, Double> chart;

    public Label numberOfSessionsField;
    public Label bestScore;

    private Stage myOwnStage;
    private SolutionModel solutionModel;
    private Scene prevScene;
    private SolutionScore solutionScore;
    private Stage primaryStage;

    public void initStagePlot(Stage stage, SolutionModel solutionModel, int amountOfEras) {
        primaryStage = stage;
        prevScene = stage.getScene();
        myOwnStage = stage;
        this.solutionModel = solutionModel;
        solutionScore = solutionModel.learn(amountOfEras);
        groupNameLabel.setText("Time Of Calculations: " + solutionScore.getTimeOfExecution() + "ms");
        bestScore.setText(solutionScore.bestScoresOfEpochs().get(amountOfEras - 1).toString());
        setChartData(solutionScore.mediumScoresOfEpochs());
    }

    public void setChartData(List<Double> scores) {
        XYChart.Series<String, Double> seriesOfScores = new XYChart.Series<>();
        seriesOfScores.setName("Score");
        long actual = 0;
        for(Double mean: scores){
            actual++;
            seriesOfScores.getData().add(new XYChart.Data<>(String.valueOf(actual), mean));
        }
        chart.getData().clear();
        chart.getData().add(seriesOfScores);
    }


    public void handleBestScores(ActionEvent actionEvent) {
        setChartData(solutionScore.bestScoresOfEpochs());
    }

    public void handleOnStandardDeviations(ActionEvent actionEvent) {
        setChartData(solutionScore.standardDeviationsOfEpochs());
    }

    public void handleOnMediumScores(ActionEvent actionEvent) {
        setChartData(solutionScore.mediumScoresOfEpochs());

    }
}
