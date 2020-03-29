package selection;

import model.EvaluatedIndividual;
import model.Individual;

import java.util.*;

public class BestSelection implements SelectionMethod {

    private final double percentageOfBest;

    public BestSelection(double percentageOfBest) {
        this.percentageOfBest = percentageOfBest;
    }

    @Override
    public List<Individual> select(List<EvaluatedIndividual> individuals, int newPopulationSize) {
        int amountOfBests = (int) (individuals.size() * percentageOfBest);
        Collections.sort(individuals);
        ArrayList<Individual> elites = new ArrayList<>();
        for (int i = 0; i < newPopulationSize; i++) {
            elites.add(individuals.get(i % amountOfBests).getIndividual());
        }
        return elites;
    }
}
