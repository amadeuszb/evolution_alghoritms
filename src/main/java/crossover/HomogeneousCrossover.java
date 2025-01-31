package crossover;

import java.util.Arrays;

public class HomogeneousCrossover implements CrossoverMethodBinary {
    @Override
    public byte[][] crossover(byte[] individualOne, byte[] individualTwo) {
        int sizeOfChromosome = individualOne.length;
        byte[][] outArray = new byte[2][];
        outArray[0] = Arrays.copyOf(individualOne, sizeOfChromosome);
        outArray[1] = Arrays.copyOf(individualTwo, sizeOfChromosome);

        for (int i = 1; i < sizeOfChromosome; i += 2) {
            outArray[0][i] = individualTwo[i];
            outArray[1][i] = individualOne[i];
        }
        return outArray;
    }
}
