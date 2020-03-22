package model;

import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SolutionScore {

    public SolutionScore() {
        epochs = new ArrayList<>();
    }

    private long timeOfExecution;

    private List<List<EvaluatedIndividual>> epochs;

    public long getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecutionInMs(long timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }

    public List<List<EvaluatedIndividual>> getEpochs() {
        return epochs;
    }

    public void setEpochs(List<List<EvaluatedIndividual>> epochs) {
        this.epochs = epochs;
    }

    public LinkedList<Double> bestScoresOfEpochs() {
        LinkedList<Double> bestScores = new LinkedList<>();
        for (List<EvaluatedIndividual> individuals : getEpochs()) {
            double bestScore = Double.MAX_VALUE;
            for (EvaluatedIndividual individual : individuals) {
                //The best score for our method
                if (Math.abs(individual.getScore() -1)  < bestScore) {
                    bestScore = individual.getScore();
                }
            }
            bestScores.add(bestScore);
        }
        return bestScores;
    }


    public LinkedList<Double> mediumScoresOfEpochs() {
        LinkedList<Double> mediumScores = new LinkedList<>();
        for (List<EvaluatedIndividual> individuals : getEpochs()) {
            double sumOfScores = 0;
            int counter = 0;
            for (EvaluatedIndividual individual : individuals) {
                sumOfScores += individual.getScore();
                counter++;
            }
            mediumScores.add(sumOfScores / counter);
        }
        return mediumScores;
    }

    @FXML
    public void handleOnBestScores() {

    }

}
