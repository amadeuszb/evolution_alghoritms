package selection;

import model.Individual;

import java.util.List;
import java.util.Random;

public class BestSelection extends SelectionMethod {
    public BestSelection(Random random) {
        super(random);
    }

    @Override
    public List<Individual> select(List<Individual> individuals, int newPopulationSize) {
        return null;
    }
}
