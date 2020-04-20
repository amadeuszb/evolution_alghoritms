package crossover;

import model.Individual;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class HeuristicCrossover implements CrossoverMethodReal {
    private final Random random;

    public HeuristicCrossover(Random random) {
        this.random = random;
    }

    private double getK() {
        double k = random.nextDouble();
        if (k == 0)
            return random.nextDouble();
        else
            return k;
    }

    @Override
    public Collection<Individual> crossover(Individual first, Individual second) {
        ArrayList<Individual> result = new ArrayList<>();
        if (first.getX1() > second.getX1() && first.getX2() > second.getX2()) {
            result.add(heuristic(first, second));
        } else if (first.getX1() < second.getX1() && first.getX2() < second.getX2()) {
            result.add(heuristic(second, first));
        }
        return result;
    }

    private Individual heuristic(Individual first, Individual second) {
        double k = getK();
        double x1 = k * (first.getX1() - second.getX1()) + second.getX1();
        double x2 = k * (first.getX2() - second.getX2()) + second.getX2();
        return new Individual(x1, x2);
    }
}
