package selection;

import model.Individual;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BestSelection extends SelectionMethod {

    private final double percentageOfBest;

    public BestSelection(Random random, double percentageOfBest) {
        super(random);
        this.percentageOfBest = percentageOfBest;
    }

    @Override
    public List<Individual> select(List<Individual> individuals, int newPopulationSize) {
        int amountOfBests = (int) (individuals.size() / percentageOfBest);
        Collections.sort(individuals);
        LinkedList<Individual> elites = new LinkedList<>();
        for (int i = 0; i < amountOfBests; i++) {
            elites.add(individuals.get(i));
        }
        return elites;
    }
}
