package mutation;

import java.util.Random;

public class BoundaryMutator implements Mutator {
    private final byte[] upperBound;
    private final byte[] lowerBound;
    private final Random random;

    public BoundaryMutator(byte[] upperBound, byte[] lowerBound, Random random) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.random = random;
    }

    @Override
    public byte[] mutate(byte[] chromosome) {
        if (random.nextBoolean()) {
            return upperBound;
        } else {
            return lowerBound;
        }
    }
}
