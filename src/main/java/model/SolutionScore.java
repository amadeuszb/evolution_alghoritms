package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SolutionScore {

    public SolutionScore() {
        epochs = new LinkedList<>();
    }

    private long timeOfExecution;

    private LinkedList<List<Individual>> epochs;

    public long getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecutionInMs(long timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }

    public LinkedList<List<Individual>> getEpochs() {
        return epochs;
    }

    public void setEpochs(LinkedList<List<Individual>> epochs) {
        this.epochs = epochs;
    }



}
