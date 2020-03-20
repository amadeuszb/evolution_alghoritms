package converter;

import function.Function;

public class Converter {

    public Converter(Function function) {
        this.beginOfSquare = function.getBeginOfSquare();
        this.endOfSquare = function.getEndOfSquare();
    }

    private double beginOfSquare;
    private double endOfSquare;

    public int toInteger(byte[] chromosome) {
        int initializeValue = 0;
        int position = 0;
        for (byte b : chromosome) {
            initializeValue |= b << position;
            position++;
        }
        return initializeValue;
    }

    public byte[] toBinary(int chromosome, int sizeOfChromosome) {
        byte[] byteChromosome = new byte[sizeOfChromosome];
        for (int i = 0; i < sizeOfChromosome; i++) {
            byteChromosome[i] = (byte) ((chromosome & (1 << i)) >> i);
        }
        return byteChromosome;
    }

    public byte[] toBinary(double chromosome, int sizeOfChromosome) {
        int finishChromosome = (int) ((chromosome - beginOfSquare) / (endOfSquare - beginOfSquare) * ((Math.pow(2, sizeOfChromosome) - 1)));
        return toBinary(finishChromosome, sizeOfChromosome);
    }

    public double toDecimal(byte[] chromosome, int sizeOfChromosome) {
        int numeralChromosome = toInteger(chromosome);
        return beginOfSquare + numeralChromosome * (endOfSquare - beginOfSquare) / ((Math.pow(2, sizeOfChromosome) - 1));
    }

}