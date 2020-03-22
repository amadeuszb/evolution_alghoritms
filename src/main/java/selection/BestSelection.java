package selection;

import model.Individual;

import java.util.*;

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
        ArrayList<Individual> elites = new ArrayList<>();
        for (int i = 0; i < amountOfBests; i++) {
            elites.add(individuals.get(i));
        }
        return elites;
    }
}
