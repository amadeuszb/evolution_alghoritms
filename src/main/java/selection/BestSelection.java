package selection;

import model.EvaluatedIndividual;
import model.Individual;

import java.util.*;
import java.util.stream.Collectors;

public class BestSelection implements SelectionMethod {
    @Override
    public List<Individual> select(List<EvaluatedIndividual> individuals, int newPopulationSize) {
        return individuals.stream()
                .sorted(Comparator.comparingDouble(EvaluatedIndividual::getScore).reversed())
                .map(EvaluatedIndividual::getIndividual)
                .limit(newPopulationSize)
                .collect(Collectors.toList());
    }
}
