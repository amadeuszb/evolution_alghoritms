package selection;

import model.Individual;
import model.RouletteIndividual;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class SelectionMethod {
    private final Random random;

    public SelectionMethod(Random random) {
        this.random = random;
    }

    public abstract List<Individual> select(List<Individual> individuals);

    protected List<Individual> getNewPopulation(int sizeOfPopulation, LinkedList<RouletteIndividual> probabilityPopulation) {
        LinkedList<Individual> newPopulation = new LinkedList<Individual>();
        for (int i = 0; i < sizeOfPopulation; i++) {
            double randomNumber = random.nextDouble();
            for (int j = 0; j < sizeOfPopulation; j++) {
                if (probabilityPopulation.get(j).getDistribuant() > randomNumber) {
                    newPopulation.add(probabilityPopulation.get(j).getIndividual());
                    break;
                }
            }
        }
        return newPopulation;
    }
}
