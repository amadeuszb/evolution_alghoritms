package crossover;

import converter.Converter;
import function.Function;
import model.Individual;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class CrossoverImpl implements Crossover {

    Random random = new Random();
    Converter converter;
    Function function;

    public CrossoverImpl(Converter converter, Function function) {
        this.converter = converter;
        this.function = function;
    }

    @Override
    public byte[][] onePointCrossover(byte[] individualOne, byte[] individualTwo) {
        int sizeOfChromosome = individualOne.length;
        byte[][] outArray = new byte[2][];
        outArray[0] = Arrays.copyOf(individualOne, sizeOfChromosome);
        outArray[1] = Arrays.copyOf(individualTwo, sizeOfChromosome);
        Random random = new Random();
        int pointOfCrossover = random.nextInt(sizeOfChromosome);
        for (int i = pointOfCrossover; i < sizeOfChromosome; i++) {
            outArray[0][i] = individualTwo[i];
            outArray[1][i] = individualOne[i];
        }
        return outArray;
    }

    @Override
    public byte[][] twoPointsCrossover(byte[] individualOne, byte[] individualTwo) {
        return new byte[0][];
    }

    @Override
    public byte[][] threePointsCrossover(byte[] individualOne, byte[] individualTwo) {
        return new byte[0][];
    }

    @Override
    public byte[][] homogeneousCrossover(byte[] individualOne, byte[] individualTwo) {
        return new byte[0][];
    }

    public LinkedList<Individual> crossover(LinkedList<Individual> population) { //TODO: set crossover method by Enum, add to interface
        int sizeOfPopulation = population.size();
        for (int i = 0; i < sizeOfPopulation; i += 2) {
            if (random.nextDouble() > 0.2) {
                Individual ione = population.get(i);
                Individual itwo = population.get(i + 1);
                byte[] ioneBinary = converter.toBinary(ione.getX1(), function.sizeOfBinaryString());
                byte[] itwoBinary = converter.toBinary(itwo.getX1(), function.sizeOfBinaryString());
                byte[][] newTwoIndividuals = onePointCrossover(ioneBinary, itwoBinary);
                ione.setX1(converter.toDecimal(newTwoIndividuals[0], function.sizeOfBinaryString()));
                itwo.setX1(converter.toDecimal(newTwoIndividuals[1], function.sizeOfBinaryString()));
                byte[] ioneBinaryX2 = converter.toBinary(ione.getX2(), function.sizeOfBinaryString());
                byte[] itwoBinaryX2 = converter.toBinary(itwo.getX2(), function.sizeOfBinaryString());
                byte[][] newTwoIndividualsX2 = onePointCrossover(ioneBinaryX2, itwoBinaryX2);
                ione.setX2(converter.toDecimal(newTwoIndividualsX2[0], function.sizeOfBinaryString()));
                itwo.setX2(converter.toDecimal(newTwoIndividualsX2[1], function.sizeOfBinaryString()));
            }
        }
        return population;
    }


}
