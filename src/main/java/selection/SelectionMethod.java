package selection;

import model.Individual;

import java.util.List;

public interface SelectionMethod {
    List<Individual> select(List<Individual> individuals);
}
