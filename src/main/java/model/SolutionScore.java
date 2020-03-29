package model;

import function.Function;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionScore {

    private final Function function;

    public SolutionScore(Function function) {
        this.function = function;
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
            double bestScore = Double.MIN_VALUE;
            Individual bestIndividual = null;
            for (EvaluatedIndividual individual : individuals) {
                //The best score for our method
                if (individual.getScore() > bestScore) {
                    bestScore = individual.getScore();
                    bestIndividual = individual.getIndividual();
                }
            }
            bestScores.add(function.fun(bestIndividual.getX1(), bestIndividual.getX2()));
        }
        return bestScores;
    }

    public LinkedList<Double> mediumScoresOfEpochs() {
        LinkedList<Double> mediumScores = new LinkedList<>();
        for (List<EvaluatedIndividual> individuals : getEpochs()) {
            double sumOfScores = 0;
            for (EvaluatedIndividual individual : individuals) {
                sumOfScores += function.fun(individual.getIndividual().getX1(), individual.getIndividual().getX2());
            }
            mediumScores.add(sumOfScores / individuals.size());
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
