package crossover;

public interface Crossover {

    byte[][] onePointCrossover(byte[] individualOne, byte[] individualTwo);

    byte[][] twoPointsCrossover(byte[] individualOne, byte[] individualTwo);

    byte[][] threePointsCrossover(byte[] individualOne, byte[] individualTwo);

    byte[][] homogeneousCrossover(byte[] individualOne, byte[] individualTwo);


}
