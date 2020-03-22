package selection;

import model.EvaluatedIndividual;
import model.Individual;
import model.RouletteIndividual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class SelectionMethod {
    private final Random random;

    public SelectionMethod(Random random) {
        this.random = random;
    }

    public abstract List<Individual> select(List<EvaluatedIndividual> individuals, int newPopulationSize);

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
