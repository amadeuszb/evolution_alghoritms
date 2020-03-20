package mutation;

import converter.Converter;
import model.Individual;

import java.util.List;
import java.util.Random;

public class MutatorImpl {

    private Converter converter;
    private final Mutator mutator;

    public MutatorImpl(Converter converter, Mutator mutator) {
        this.converter = converter;
        this.mutator = mutator;
    }

    public List<Individual> mutatePopulation(List<Individual> population, double probability) {
        Random random = new Random();
        for (Individual individual : population) {
            if (random.nextDouble() < probability) {
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
