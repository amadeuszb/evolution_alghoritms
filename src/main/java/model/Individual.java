package model;

public class Individual implements Comparable<Individual> {

    private double x1;
    private double x2;
    private double y;

    public Individual() {
    }

    public Individual(double x1, double x2, double y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int compareTo(Individual o) {
        return Double.compare(o.y, y);
    }
}
