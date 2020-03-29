package selection;

import model.EvaluatedIndividual;
import model.Individual;
import model.RouletteIndividual;

import java.util.*;

public class RouletteSelection implements SelectionMethod {

    private final Random random;

    public RouletteSelection(Random random) {
        this.random = random;
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

    protected List<Individual> getNewPopulation(int newPopulationSize, List<RouletteIndividual> probabilityPopulation) {
        ArrayList<Individual> newPopulation = new ArrayList<>();
        for (int i = 0; i < newPopulationSize; i++) {
            double randomNumber = random.nextDouble();
            for (RouletteIndividual rouletteIndividual : probabilityPopulation) {
                if (rouletteIndividual.getDistribuant() > randomNumber) {
                    newPopulation.add(rouletteIndividual.getIndividual());
                    break;
                }
            }
        }
        return newPopulation;
    }
}
