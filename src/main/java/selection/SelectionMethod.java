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

    public abstract List<Individual> select(List<Individual> individuals, int newPopulationSize);

    protected List<Individual> getNewPopulation(int newPopulationSize, LinkedList<RouletteIndividual> probabilityPopulation) {
        LinkedList<Individual> newPopulation = new LinkedList<Individual>();
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
