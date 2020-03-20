package crossover;

import model.Individual;

import java.util.LinkedList;

public interface Crossover {

    byte[][] onePointCrossover(byte[] individualOne, byte[] individualTwo);

    byte[][] twoPointsCrossover(byte[] individualOne, byte[] individualTwo);

    byte[][] threePointsCrossover(byte[] individualOne, byte[] individualTwo);

    byte[][] homogeneousCrossover(byte[] individualOne, byte[] individualTwo);

    LinkedList<Individual> crossover(LinkedList<Individual> population, double probability,CrossoverType type);

}
