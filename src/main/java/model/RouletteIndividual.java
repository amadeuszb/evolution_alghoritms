package model;

public class RouletteIndividual {

    private Individual individual;

    private Double distribuant;

    public RouletteIndividual(Individual individual, double distribuant) {
        this.individual = individual;
        this.distribuant = distribuant;
    }

    public Individual getIndividual() {
        return individual;
    }

    public double getDistribuant() {
        return distribuant;
    }
}
