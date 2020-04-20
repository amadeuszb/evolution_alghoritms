package mutation;

import model.Individual;

import java.util.List;

public interface PopulationMutator {
    List<Individual> mutatePopulation(List<Individual> population);
}
