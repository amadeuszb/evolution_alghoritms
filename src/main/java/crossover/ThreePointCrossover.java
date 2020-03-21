package crossover;

import java.util.*;

public class ThreePointCrossover extends PointCrossover implements CrossoverMethod {
    private final Random random;

    public ThreePointCrossover(Random random) {
        this.random = random;
    }

    @Override
    public byte[][] crossover(byte[] individualOne, byte[] individualTwo) {
        int sizeOfChromosome = individualOne.length;
        byte[][] outArray = new byte[2][];
        outArray[0] = Arrays.copyOf(individualOne, sizeOfChromosome);
        outArray[1] = Arrays.copyOf(individualTwo, sizeOfChromosome);
        List<Integer> crossoverPoints = new ArrayList<Integer>(3);
        crossoverPoints.add(random.nextInt(sizeOfChromosome));
        crossoverPoints.add(random.nextInt(sizeOfChromosome));
        crossoverPoints.add(random.nextInt(sizeOfChromosome));
        Collections.sort(crossoverPoints);
        int pointOfCrossover = crossoverPoints.get(0);
        int secondPointOfCrossover = crossoverPoints.get(1);
        int thirdPointOfCrossover = crossoverPoints.get(2);

        swapBetweenPoints(individualOne, individualTwo, pointOfCrossover, secondPointOfCrossover, outArray);
        swapBetweenPoints(individualOne, individualTwo, thirdPointOfCrossover, sizeOfChromosome, outArray);

        return outArray;
    }
}
