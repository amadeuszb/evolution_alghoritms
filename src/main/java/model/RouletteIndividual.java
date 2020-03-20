package model;

public class RouletteIndividual implements Comparable<RouletteIndividual>{

    private Individual individual;

    private Double distribuant;

    private Double propability;

    public Individual getIndividual() {
        return individual;
    }

    public RouletteIndividual(Individual individual, double distribuant, double propability) {
        this.individual = individual;
        this.distribuant = distribuant;
        this.propability = propability;
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

    public Double getPropability() {
        return propability;
    }

    public void setPropability(double propability) {
        this.propability = propability;
    }

    public int compareTo(RouletteIndividual o) {
        return this.getPropability().compareTo(o.getPropability());
    }
}
