package selection;

import model.EvaluatedIndividual;
import model.Individual;

import java.util.List;

public interface SelectionMethod {
    List<Individual> select(List<EvaluatedIndividual> individuals, int newPopulationSize);
}
