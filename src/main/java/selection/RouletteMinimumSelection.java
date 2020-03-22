package selection;

import model.Individual;
import model.RouletteIndividual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteMinimumSelection extends SelectionMethod {
    public RouletteMinimumSelection(Random random) {
        super(random);
    }

    @Override
    public List<Individual> select(List<Individual> population, int newPopulationSize) {
        double sumOfResults = 0;
        for (Individual individual : population) {
            sumOfResults += getInverseY(individual);
        }
        ArrayList<RouletteIndividual> probabilityPopulation = new ArrayList<>();
        double previousSum = 0;
        for (Individual x : population) {
            previousSum += getInverseY(x) / sumOfResults;
            probabilityPopulation.add(new RouletteIndividual(x, previousSum));
        }

        return getNewPopulation(newPopulationSize, probabilityPopulation);
    }

    private double getInverseY(Individual individual) {
        if (individual.getY() == 0)
            return 1;
        return Math.abs(1 / individual.getY());
    }
}
