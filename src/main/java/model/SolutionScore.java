package model;

import java.util.ArrayList;
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



}
