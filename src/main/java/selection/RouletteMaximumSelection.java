package selection;

import model.Individual;
import model.RouletteIndividual;

import java.util.*;

public class RouletteMaximumSelection implements SelectionMethod {
    private final Random random;

    public RouletteMaximumSelection(Random random){
        this.random = random;
    }

    @Override
    public List<Individual> select(List<Individual> population) {
        double sumOfResults = 0;
        int sizeOfPopulation = population.size();
        for (Individual individual : population) {
            sumOfResults += Math.abs(individual.getY());
        }
        LinkedList<RouletteIndividual> propabilityPopulation = new LinkedList<RouletteIndividual>();
        for (Individual x : population) {
            propabilityPopulation.add(new RouletteIndividual(x, 0, (Math.abs(x.getY()) / sumOfResults)));
        }
        Collections.sort(propabilityPopulation);
        double previousSum = 0;
        for (RouletteIndividual individual : propabilityPopulation) {
            individual.setDistribuant(previousSum + individual.getPropability());
            previousSum += individual.getPropability();
        }
        LinkedList<Individual> newPopulation = new LinkedList<Individual>();
        for (int i = 0; i < sizeOfPopulation; i++) {
            double randomNumber = random.nextDouble();
            for (int j = 0; j < sizeOfPopulation; j++) {
                if (propabilityPopulation.get(j).getDistribuant() > randomNumber) {
                    newPopulation.add(propabilityPopulation.get(j).getIndividual());
                    break;
                }
            }
        }

        return newPopulation;
    }
}
