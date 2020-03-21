package converter;

import function.Function;

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

    public double toDecimal(byte[] chromosome) {
        int numeralChromosome = toInteger(chromosome);

        return beginOfSquare + numeralChromosome * binaryResolution;
    }

}