package model;

public class EvaluatedIndividual implements Comparable<EvaluatedIndividual> {
    private final Individual individual;
    private final double y;
    private final double score;

    public EvaluatedIndividual(Individual individual, double y, double score) {
        this.individual = individual;
        this.y = y;
        this.score = score;
    }

    public Individual getIndividual() {
        return individual;
    }

    public double getScore() {
        return score;
    }

    public double getY() {
        return y;
    }

    @Override
    public int compareTo(EvaluatedIndividual o) {
        return Double.compare(o.score, score);
    }
}
