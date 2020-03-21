package mutation;

import java.util.Random;

public class BoundaryMutator implements Mutator {
    private final byte[] upperBound;
    private final byte[] lowerBound;

    public BoundaryMutator(byte[] upperBound, byte[] lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }

    Random random = new Random();

    @Override
    public byte[] mutate(byte[] chromosome) {
        if (random.nextBoolean()) {
            return upperBound;
        } else {
            return lowerBound;
        }
    }
}
