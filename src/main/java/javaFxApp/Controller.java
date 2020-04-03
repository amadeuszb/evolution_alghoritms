package javaFxApp;

import crossover.CrossoverType;
import function.DropwaveFunction;
import function.Function;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mutation.MutationType;
import selection.*;
import solution.SolutionModel;
import solution.SolutionModelBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import static selection.SelectionMethodType.Roulette;
import static selection.SelectionMethodType.values;

public class Controller implements Initializable {

    @FXML
    private TextField eras;
    @FXML
    private TextField populationSize;
    @FXML
    private TextField crossoverProbability;
    @FXML
    private TextField mutationProbability;
    @FXML
    private TextField inversionProbability;
    @FXML
    private TextField accuracyOfChormosome;
    @FXML
    private TextField amountOfElites;
    @FXML
    private ChoiceBox<MutationType> methodMutation;
    @FXML
    private ChoiceBox<CrossoverType> methodCrossover;
    @FXML
    private ChoiceBox<SelectionMethodType> methodSelection;
    @FXML
    private TextField selectionParameter;
    @FXML
    private TextField selectionPercent;
    @FXML
    private TextField randomSeed;
    @FXML
    private Button calculateButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initStage(Stage primaryStage) {
        initializeChoiceBoxes();
    }

    private void initializeChoiceBoxes() {
        methodMutation.getItems().addAll(Arrays.asList(MutationType.values()));
        methodCrossover.getItems().addAll(Arrays.asList(CrossoverType.values()));
        methodSelection.getItems().addAll(Arrays.asList(values()));
        methodCrossover.setValue(CrossoverType.TwoPoints);
        methodSelection.setValue(Roulette);
        methodMutation.setValue(MutationType.TWO_POINTS);
    }

    @FXML
    private void onButtonCalculate(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../Plot.fxml"));
            Parent root = myLoader.load();
            myLoader.<PlotController>getController().initStagePlot(stage, buildSolution(), valueOfLabelInt(eras));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SolutionModel buildSolution() {
        Function function = new DropwaveFunction(valueOfLabelInt(accuracyOfChormosome));
        SelectionMethod selectionMethod = null;
        Random random = new Random(valueOfLabelInt(randomSeed));
        switch (methodSelection.getValue()) {
            case Roulette:
                selectionMethod = new RouletteSelection(random);
                break;
            case Best:
                selectionMethod = new BestSelection();
                break;
            case Tournament:
                selectionMethod = new TournamentSelection(random, valueOfLabelInt(selectionParameter));
                break;
        }
        SolutionModelBuilder modelBuilder = new SolutionModelBuilder();
        modelBuilder.withCrossoverType(methodCrossover.getValue())
                .withFunction(function)
                .withMutationType(methodMutation.getValue())
                .withPopulationSize(valueOfLabelInt(populationSize))
                .withSelectionMethod(selectionMethod)
                .withCrossoverProbability(valueOfLabelDouble(crossoverProbability))
                .withMutationProbability(valueOfLabelDouble(mutationProbability))
                .withInversionProbability(valueOfLabelDouble(inversionProbability))
                .withRandom(random)
                .withElitesCount(valueOfLabelInt(amountOfElites))
                .withSelectionPercentage(valueOfLabelDouble(selectionPercent));
        return modelBuilder.build();
    }

    private int valueOfLabelInt(TextField text) {
        return Integer.parseInt(text.getText());
    }

    private double valueOfLabelDouble(TextField text) {
        return Double.parseDouble(text.getText());
    }

    public void onSelectionChange(ActionEvent actionEvent) {
        switch (methodSelection.getValue()) {
            case Roulette:
            case Best:
                selectionParameter.setDisable(true);
                break;
            case Tournament:
                selectionParameter.setDisable(false);
                break;
        }
    }
}

