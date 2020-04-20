package mutation;

import model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationMutatorReal implements PopulationMutator {
    private final MutationReal mutationReal;
    private final double mutationProbability;
    private final Random random;

    public PopulationMutatorReal(MutationReal mutationReal, double mutationProbability, Random random) {
        this.mutationReal = mutationReal;
        this.mutationProbability = mutationProbability;
        this.random = random;
    }

    public List<Individual> mutatePopulation(List<Individual> population) {
        ArrayList<Individual> newIndividuals = new ArrayList<>();
        for (Individual individual : population) {
            if (random.nextDouble() < mutationProbability) {
                newIndividuals.add(mutationReal.mutate(individual));
            } else {
                newIndividuals.add(individual);
            }
        }
        return newIndividuals;
    }
}
