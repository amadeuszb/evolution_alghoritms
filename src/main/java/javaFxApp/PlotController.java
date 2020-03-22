package javaFxApp;

import javafx.scene.chart.XYChart;
import model.EvaluatedIndividual;
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

import java.util.LinkedList;
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
        numberOfSessionsField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
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
        long actual = 0;
        for (Double mean : getMeanScore(solutionScore)) {
            actual++;
            seriesOfScores.getData().add(new XYChart.Data<>(String.valueOf(actual), mean));
        }
        chart.getData().clear();
        chart.getData().add(seriesOfScores);
    }

    private LinkedList<Double> getMeanScore(SolutionScore solutionScore) { //TODO: Move to solution score
        LinkedList<Double> meanScores = new LinkedList<>();
        for (List<EvaluatedIndividual> individuals : solutionScore.getEpochs()) {
            double sumOfScores = 0;
            int counter = 0;
            for (EvaluatedIndividual individual : individuals) {
                sumOfScores += individual.getScore();
                counter++;
            }
            meanScores.add(sumOfScores / counter);
        }
        return meanScores;
    }

    public void handleOnButtonBack(ActionEvent actionEvent) {
        primaryStage.setScene(prevScene);
    }
}
