package crossover;

import java.util.Arrays;
import java.util.Random;

public class TwoPointCrossover extends PointCrossover implements CrossoverMethod {
    private final Random random;

    public TwoPointCrossover(Random random) {
        this.random = random;
    }

    @Override
    public byte[][] crossover(byte[] individualOne, byte[] individualTwo) {
        int sizeOfChromosome = individualOne.length;
        byte[][] outArray = new byte[2][];
        outArray[0] = Arrays.copyOf(individualOne, sizeOfChromosome);
        outArray[1] = Arrays.copyOf(individualTwo, sizeOfChromosome);
        int pointOfCrossover = random.nextInt(sizeOfChromosome);
        int secondPointOfCrossover = random.nextInt(sizeOfChromosome);
        if (pointOfCrossover > secondPointOfCrossover) {
            int temp = pointOfCrossover;
            pointOfCrossover = secondPointOfCrossover;
            secondPointOfCrossover = temp;
        }
        swapBetweenPoints(individualOne, individualTwo, pointOfCrossover, secondPointOfCrossover, outArray);
        return outArray;
    }
}
