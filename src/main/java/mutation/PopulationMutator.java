package mutation;

import converter.Converter;
import model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationMutator {

    private final Converter converter;
    private final Mutator mutator;
    private final double mutationProbability;
    private final Random random;

    public PopulationMutator(Converter converter, Mutator mutator, double mutationProbability, Random random) {
        this.converter = converter;
        this.mutator = mutator;
        this.mutationProbability = mutationProbability;
        this.random = random;
    }

    public List<Individual> mutatePopulation(List<Individual> population) {
        ArrayList<Individual> newIndividuals = new ArrayList<>();
        for (Individual individual : population) {
            if (random.nextDouble() < mutationProbability) {
                byte[] ioneBinary = converter.toBinary(individual.getX1());
                byte[] mutated = mutator.mutate(ioneBinary);
                newIndividuals.add(converter.toIndividual(mutated));
            } else {
                newIndividuals.add(individual);
            }
        }
        return newIndividuals;
    }

}
