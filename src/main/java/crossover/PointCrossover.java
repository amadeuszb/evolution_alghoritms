package crossover;

public abstract class PointCrossover {
    protected void swapBetweenPoints(byte[] individualOne, byte[] individualTwo, int point1, int point2, byte[][] outArray) {
        for (int i = point1; i < point2; i++) {
            outArray[0][i] = individualTwo[i];
            outArray[1][i] = individualOne[i];
        }
    }
}
