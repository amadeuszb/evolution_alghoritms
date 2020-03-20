package initializer;

import model.Individual;

import java.util.LinkedList;
import java.util.Random;

public class Initializer {

    private Random random = new Random();

    public LinkedList<Individual> getInitialPopulation(int sizeOfPopulation, double beginOfSquare, double endOfSquare) {
        LinkedList<Individual> population = new LinkedList<Individual>();
        for (int i = 0; i < sizeOfPopulation; i++) {
            Individual individual = new Individual();
            double x1 = beginOfSquare + (endOfSquare - beginOfSquare) * random.nextDouble();
            double x2 = beginOfSquare + (endOfSquare - beginOfSquare) * random.nextDouble();
            individual.setX1(x1);
            individual.setX2(x2);
            population.add(individual);
        }
        return population;
    }
}
