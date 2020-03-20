package mutation;

import converter.Converter;
import function.Function;
import model.Individual;

import java.util.LinkedList;
import java.util.Random;

public class MutatorImpl implements Mutator {

    private Converter converter;
    private Function function;

    public MutatorImpl(Converter converter, Function function) {
        this.converter = converter;
        this.function = function;
    }

    public byte[] boundaryMutation(byte[] chromosome) {
        byte boundaryValue = chromosome[chromosome.length - 1];
        if (boundaryValue == 0) chromosome[chromosome.length - 1] = 1;
        else chromosome[chromosome.length - 1] = 0;
        return chromosome;
    }

    @Override
    public byte[] onePointMutation(byte[] chromosome) {
        return new byte[0];
    }

    @Override
    public byte[] twoPointsMutation(byte[] chromosome) {
        return new byte[0];
    }

    public LinkedList<Individual> mutatePopulation(LinkedList<Individual> population, double probability, MutationType mutationType) {
        int sizeOfPopulation = population.size();
        Random random = new Random();
        for (int i = 0; i < sizeOfPopulation; i++) {
            if (random.nextDouble() < probability) {
                Individual ione = population.get(i);
                byte[] ioneBinary = converter.toBinary(ione.getX1(), function.sizeOfBinaryString());
                byte[] newTwoIndividuals = mutate(ioneBinary, mutationType);
                ione.setX1(converter.toDecimal(newTwoIndividuals, function.sizeOfBinaryString()));
                byte[] ioneBinaryX2 = converter.toBinary(ione.getX2(), function.sizeOfBinaryString());
                byte[] newTwoIndividualsX2 = mutate(ioneBinaryX2, mutationType);
                ione.setX2(converter.toDecimal(newTwoIndividualsX2, function.sizeOfBinaryString()));
            }
        }
        return population;
    }

    private byte[] mutate (byte[] individual, MutationType mutationType){
        switch (mutationType){
            case BOUNDARY: return boundaryMutation(individual);
            case TWO_POINTS: return onePointMutation(individual);
            case ONE_POINT: return twoPointsMutation(individual);
        }
        return boundaryMutation(individual);
    }
}
