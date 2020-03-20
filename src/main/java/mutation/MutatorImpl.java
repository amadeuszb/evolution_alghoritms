package mutation;

import converter.Converter;
import function.Function;
import model.Individual;

import java.util.LinkedList;
import java.util.Random;

public class MutatorImpl {

    private Converter converter;

    public MutatorImpl(Converter converter) {
        this.converter = converter;
    }

    public LinkedList<Individual> mutatePopulation(LinkedList<Individual> population, double probability, Mutator mutator) {
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
