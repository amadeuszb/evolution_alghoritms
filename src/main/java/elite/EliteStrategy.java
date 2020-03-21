package elite;

import model.Individual;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EliteStrategy {
    private final int eliteCount;

    public EliteStrategy(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    public List<Individual> getElites(List<Individual> population) {
        Collections.sort(population);
        LinkedList<Individual> elites = new LinkedList<Individual>();
        for (int i = 0; i < eliteCount; i++) {
            elites.add(population.get(i));
        }
        return elites;
    }
}
