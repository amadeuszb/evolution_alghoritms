package crossover;

import java.util.Arrays;
import java.util.Random;

public class OnePointCrossover implements CrossoverMethod {
    private Random random = new Random();

    @Override
    public byte[][] crossover(byte[] individualOne, byte[] individualTwo) {
        int sizeOfChromosome = individualOne.length;
        byte[][] outArray = new byte[2][];
        outArray[0] = Arrays.copyOf(individualOne, sizeOfChromosome);
        outArray[1] = Arrays.copyOf(individualTwo, sizeOfChromosome);
        int pointOfCrossover = random.nextInt(sizeOfChromosome);
        for (int i = pointOfCrossover; i < sizeOfChromosome; i++) {
            outArray[0][i] = individualTwo[i];
            outArray[1][i] = individualOne[i];
        }
        return outArray;
    }
}
