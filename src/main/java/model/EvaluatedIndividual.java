package model;

public class EvaluatedIndividual implements Comparable<EvaluatedIndividual> {
    private Individual individual;
    private double score;

    public EvaluatedIndividual(Individual individual, double score) {
        this.individual = individual;
        this.score = score;
    }

    public Individual getIndividual() {
        return individual;
    }

    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(EvaluatedIndividual o) {
        return Double.compare(o.score, score);
    }
}
