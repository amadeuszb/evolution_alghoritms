package selection;

import model.Individual;
import model.RouletteIndividual;

import java.util.*;

public class RouletteMaximumSelection extends SelectionMethod {

    public RouletteMaximumSelection(Random random) {
        super(random);
    }

    @Override
    public List<Individual> select(List<Individual> population, int newPopulationSize) {
        double sumOfResults = 0;
        for (Individual individual : population) {
            sumOfResults += individual.getY();
        }
        ArrayList<RouletteIndividual> probabilityPopulation = new ArrayList<>();
        double previousSum = 0;
        for (Individual x : population) {
            previousSum += x.getY() / sumOfResults;
            probabilityPopulation.add(new RouletteIndividual(x, previousSum));
        }

        return getNewPopulation(newPopulationSize, probabilityPopulation);
    }
}
