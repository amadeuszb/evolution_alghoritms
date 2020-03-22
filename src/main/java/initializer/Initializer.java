package initializer;

import model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Initializer {
    private final Random random;

    public Initializer(Random random) {
        this.random = random;
    }

    public List<Individual> getInitialPopulation(int sizeOfPopulation, double beginOfSquare, double endOfSquare) {
        ArrayList<Individual> population = new ArrayList<>(sizeOfPopulation);
        for (int i = 0; i < sizeOfPopulation; i++) {
            double x1 = beginOfSquare + (endOfSquare - beginOfSquare) * random.nextDouble();
            double x2 = beginOfSquare + (endOfSquare - beginOfSquare) * random.nextDouble();
            population.add(new Individual(x1, x2));
        }
        return population;
    }
}
