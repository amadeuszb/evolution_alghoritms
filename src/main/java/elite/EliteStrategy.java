package elite;

import model.EvaluatedIndividual;
import model.Individual;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EliteStrategy {
    private final int eliteCount;

    public EliteStrategy(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    public List<Individual> getElites(List<EvaluatedIndividual> population) {
        return population.stream()
                .sorted(Comparator.comparingDouble(EvaluatedIndividual::getScore).reversed())
                .map(EvaluatedIndividual::getIndividual)
                .limit(eliteCount)
                .collect(Collectors.toList());
    }
}
