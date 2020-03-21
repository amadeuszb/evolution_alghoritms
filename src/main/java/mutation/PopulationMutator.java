package mutation;

import converter.Converter;
import model.Individual;

import java.util.List;
import java.util.Random;

public class PopulationMutator {

    private final Converter converter;
    private final Mutator mutator;
    private final double mutationProbability;
    private final Random random = new Random();

    public PopulationMutator(Converter converter, Mutator mutator, double mutationProbability) {
        this.converter = converter;
        this.mutator = mutator;
        this.mutationProbability = mutationProbability;
    }

    public List<Individual> mutatePopulation(List<Individual> population) {
        for (Individual individual : population) {
            if (random.nextDouble() < mutationProbability) {
                byte[] ioneBinary = converter.toBinary(individual.getX1());
                byte[] newTwoIndividuals = mutator.mutate(ioneBinary);
                individual.setX1(converter.toDecimal(newTwoIndividuals));
                byte[] ioneBinaryX2 = converter.toBinary(individual.getX2());
                byte[] newTwoIndividualsX2 = mutator.mutate(ioneBinaryX2);
                individual.setX2(converter.toDecimal(newTwoIndividualsX2));
            }
        }
        return population;
    }

}
