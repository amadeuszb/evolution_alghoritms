package converter;

import function.Function;
import model.Individual;

import java.util.Arrays;

public class Converter {

    private int sizeOfChromosome;
    private final double binaryResolution;

    public Converter(Function function) {
        this.beginOfSquare = function.getBeginOfSquare();
        this.endOfSquare = function.getEndOfSquare();
        this.sizeOfChromosome = function.sizeOfBinaryString();
        this.binaryResolution = (endOfSquare - beginOfSquare) / ((Math.pow(2, sizeOfChromosome) - 1));
    }

    private final double beginOfSquare;
    private final double endOfSquare;

    private int toInteger(byte[] chromosome) {
        int initializeValue = 0;
        int position = 0;
        for (byte b : chromosome) {
            initializeValue |= b << position;
            position++;
        }
        return initializeValue;
    }

    private byte[] integerToBinary(int chromosome) {
        byte[] byteChromosome = new byte[sizeOfChromosome];
        for (int i = 0; i < sizeOfChromosome; i++) {
            byteChromosome[i] = (byte) ((chromosome & (1 << i)) >> i);
        }
        return byteChromosome;
    }

    public byte[] toBinary(double chromosome) {
        int integerChromosome = (int) ((chromosome - beginOfSquare) / binaryResolution);
        return integerToBinary(integerChromosome);
    }

    public byte[] toBinary(Individual individual) {
        byte[] x1 = toBinary(individual.getX1());
        byte[] x2 = toBinary(individual.getX2());
        return concat(x1, x2);
    }

    public Individual toIndividual(byte[] chromosome) {
        int n = chromosome.length;

        byte[] x1 = Arrays.copyOfRange(chromosome, 0, (n + 1) / 2);
        byte[] x2 = Arrays.copyOfRange(chromosome, (n + 1) / 2, n);
        return new Individual(toDecimal(x1), toDecimal(x2));
    }

    public double toDecimal(byte[] chromosome) {
        int numeralChromosome = toInteger(chromosome);

        return beginOfSquare + numeralChromosome * binaryResolution;
    }

    public byte[] concat(byte[] first, byte[] second) {
        byte[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}