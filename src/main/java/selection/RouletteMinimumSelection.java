package selection;

import model.Individual;
import model.RouletteIndividual;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RouletteMinimumSelection extends SelectionMethod {
    public RouletteMinimumSelection(Random random) {
        super(random);
    }

    @Override
    public List<Individual> select(List<Individual> population) {
        double sumOfResults = 0;
        int sizeOfPopulation = population.size();
        for (Individual individual : population) {
            sumOfResults += getInverseY(individual);
        }
        LinkedList<RouletteIndividual> probabilityPopulation = new LinkedList<RouletteIndividual>();
        double previousSum = 0;
        for (Individual x : population) {
            previousSum += getInverseY(x) / sumOfResults;
            probabilityPopulation.add(new RouletteIndividual(x, previousSum));
        }

        return getNewPopulation(sizeOfPopulation, probabilityPopulation);
    }

    private double getInverseY(Individual individual) {
        if (individual.getY() == 0)
            return 1;
        return Math.abs(1 / individual.getY());
    }
}
