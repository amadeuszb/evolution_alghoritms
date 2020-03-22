package elite;

import model.EvaluatedIndividual;
import model.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EliteStrategy {
    private final int eliteCount;

    public EliteStrategy(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    public List<Individual> getElites(List<EvaluatedIndividual> population) {
        Collections.sort(population);
        ArrayList<Individual> elites = new ArrayList<>(eliteCount);
        for (int i = 0; i < eliteCount; i++) {
            elites.add(population.get(i).getIndividual());
        }
        return elites;
    }
}
