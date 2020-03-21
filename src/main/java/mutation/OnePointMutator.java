package mutation;

import converter.ByteSwitcher;

import java.util.Random;

public class OnePointMutator implements Mutator {

    private final Random random;
    private final ByteSwitcher byteSwitcher;

    public OnePointMutator(Random random, ByteSwitcher byteSwitcher) {
        this.random = random;
        this.byteSwitcher = byteSwitcher;
    }

    @Override
    public byte[] mutate(byte[] chromosome) {
        int mutatedByteIndex = random.nextInt(chromosome.length);
        chromosome = byteSwitcher.switchByte(chromosome, mutatedByteIndex);
        return chromosome;
    }
}
