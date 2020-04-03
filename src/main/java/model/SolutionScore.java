package model;

import java.util.ArrayList;
import java.util.Comparator;
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

    public List<Double> bestScoresOfEpochs() {
        return getEpochs().stream()
                .map(p -> p.stream()
                        .max(Comparator.comparingDouble(EvaluatedIndividual::getScore))
                        .map(EvaluatedIndividual::getY)
                        .get())
                .collect(Collectors.toList());
    }

    public List<Double> meanScoresOfEpochs() {
        return getEpochs().stream()
                .map(p -> p.stream().mapToDouble(EvaluatedIndividual::getY).average().orElse(Double.NaN))
                .collect(Collectors.toList());
    }

    public List<Double> standardDeviationsOfEpochs() {
        return getEpochs().stream().map(this::standardDeviation).collect(Collectors.toList());
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
