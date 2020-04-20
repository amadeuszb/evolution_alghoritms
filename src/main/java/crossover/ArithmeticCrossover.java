package crossover;

import model.Individual;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class ArithmeticCrossover implements CrossoverMethodReal {

    private final Random random;

    public ArithmeticCrossover(Random random) {
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
        double k = getK();
        double firstX1 = k * first.getX1() + (1 - k) * second.getX1();
        double secondX1 = k * second.getX1() + (1 - k) * first.getX1();
        double firstX2 = k * first.getX2() + (1 - k) * second.getX2();
        double secondX2 = k * second.getX2() + (1 - k) * first.getX2();
        ArrayList<Individual> result = new ArrayList<>();
        result.add(new Individual(firstX1, secondX1));
        result.add(new Individual(firstX2, secondX2));
        return result;
    }
}
