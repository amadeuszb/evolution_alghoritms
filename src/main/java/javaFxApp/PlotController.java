package javaFxApp;

import Solution.SolutionModel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


import java.util.ArrayList;
import java.util.List;

import static javafx.scene.chart.XYChart.Data;
import static javafx.scene.chart.XYChart.Series;


public class PlotController {
    public Label groupNameLabel;
    public Button backButton;

    public LineChart<String, Long> chart;

    public TextField numberOfSessionsField;

    private Stage myOwnStage;
    SolutionModel solutionModel;
    Scene prevScene;

    private Stage primaryStage;

    public void initStagePlot(Stage stage, SolutionModel solutionModel) {
        numberOfSessionsField.setTextFormatter(new TextFormatter<Number>(new NumberStringConverter()));
        primaryStage = stage;
        prevScene = stage.getScene();
        myOwnStage = stage;
        this.solutionModel = solutionModel;
        groupNameLabel.setText("Selected group: ");
        numberOfSessionsField.textProperty().addListener((ov, oldValue, newValue) -> {
            try {
                setChartData(Integer.parseInt(newValue));
            } catch (Exception e) {
               // setChartData(solutionModel.getSesiones().size());
            }
        });
        //setChartData(solutionModel.getSesiones().size());
    }

    public void setChartData(int amountOfLastSessiones) {

    }

    public void handleOnButtonBack(ActionEvent actionEvent) {
        primaryStage.setScene(prevScene);
    }
}
