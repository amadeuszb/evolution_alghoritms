package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
                if (Math.abs(individual.getScore() - 1) < bestScore) {
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

    public List<Double> standardDeviationsOfEpochs() {
        return getEpochs().
                stream()
                .map(this::standardDeviation)
                .collect(Collectors.toList());
    }

    private Double standardDeviation(List<EvaluatedIndividual> evaluatedIndividuals) {
        List<Double> scores = evaluatedIndividuals.stream()
                .map(EvaluatedIndividual::getScore)
                .collect(Collectors.toList());
        double sum = 0.0, standardDeviation = 0.0;
        int length = scores.size();

        for (double num : scores) {
            sum += num;
        }

        double mean = sum / length;

        for (double num : scores) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);
    }


}
