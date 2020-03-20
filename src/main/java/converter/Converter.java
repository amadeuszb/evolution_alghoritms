package converter;

import function.Function;

public class Converter {

    private int sizeOfChromosome;

    public Converter(Function function) {
        this.beginOfSquare = function.getBeginOfSquare();
        this.endOfSquare = function.getEndOfSquare();
        this.sizeOfChromosome = function.sizeOfBinaryString();
    }

    private double beginOfSquare;
    private double endOfSquare;

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
        int integerChromosome = (int) ((chromosome - beginOfSquare) / (endOfSquare - beginOfSquare) * ((Math.pow(2, sizeOfChromosome) - 1)));
        return integerToBinary(integerChromosome);
    }

    public double toDecimal(byte[] chromosome) {
        int numeralChromosome = toInteger(chromosome);
        return beginOfSquare + numeralChromosome * (endOfSquare - beginOfSquare) / ((Math.pow(2, sizeOfChromosome) - 1));
    }

}