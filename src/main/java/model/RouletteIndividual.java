package model;

public class RouletteIndividual {

    private Individual individual;

    private Double distribuant;


    public Individual getIndividual() {
        return individual;
    }

    public RouletteIndividual(Individual individual, double distribuant) {
        this.individual = individual;
        this.distribuant = distribuant;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public double getDistribuant() {
        return distribuant;
    }

    public void setDistribuant(double distribuant) {
        this.distribuant = distribuant;
    }
}
