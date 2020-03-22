package selection;

import model.EvaluatedIndividual;
import model.Individual;
import model.RouletteIndividual;

import java.util.*;

public class RouletteSelection extends SelectionMethod {

    public RouletteSelection(Random random) {
        super(random);
    }

    @Override
    public List<Individual> select(List<EvaluatedIndividual> population, int newPopulationSize) {
        double sumOfResults = 0;
        for (EvaluatedIndividual individual : population) {
            sumOfResults += individual.getScore();
        }
        ArrayList<RouletteIndividual> probabilityPopulation = new ArrayList<>();
        double previousSum = 0;
        for (EvaluatedIndividual x : population) {
            previousSum += x.getScore() / sumOfResults;
            probabilityPopulation.add(new RouletteIndividual(x.getIndividual(), previousSum));
        }

        return getNewPopulation(newPopulationSize, probabilityPopulation);
    }
}
