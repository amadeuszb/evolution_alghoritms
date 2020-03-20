package mutation;

import converter.Converter;
import function.Function;
import model.Individual;

import java.util.LinkedList;
import java.util.Random;

public class MutatorImpl {

    private Converter converter;
    private Function function;

    public MutatorImpl(Converter converter, Function function) {
        this.converter = converter;
        this.function = function;
    }

    public LinkedList<Individual> mutatePopulation(LinkedList<Individual> population, double probability, Mutator mutator) {
        Random random = new Random();
        for (Individual individual : population) {
            if (random.nextDouble() < probability) {
                byte[] ioneBinary = converter.toBinary(individual.getX1(), function.sizeOfBinaryString());
                byte[] newTwoIndividuals = mutator.mutate(ioneBinary);
                individual.setX1(converter.toDecimal(newTwoIndividuals, function.sizeOfBinaryString()));
                byte[] ioneBinaryX2 = converter.toBinary(individual.getX2(), function.sizeOfBinaryString());
                byte[] newTwoIndividualsX2 = mutator.mutate(ioneBinaryX2);
                individual.setX2(converter.toDecimal(newTwoIndividualsX2, function.sizeOfBinaryString()));
            }
        }
        return population;
    }

}
