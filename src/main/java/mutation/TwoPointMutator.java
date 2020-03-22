package mutation;

import converter.ByteSwitcher;

import java.util.Random;

public class TwoPointMutator implements Mutator {

    private final Random random;
    private final ByteSwitcher byteSwitcher;

    public TwoPointMutator(Random random, ByteSwitcher byteSwitcher) {
        this.random = random;
        this.byteSwitcher = byteSwitcher;
    }

    @Override
    public byte[] mutate(byte[] chromosome) {
        int firstMutatedByteIndex = random.nextInt(chromosome.length);
        int secondMutatedByteIndex = random.nextInt(chromosome.length);
        chromosome = byteSwitcher.switchByte(chromosome, firstMutatedByteIndex);
        chromosome = byteSwitcher.switchByte(chromosome, secondMutatedByteIndex);
        return chromosome;
    }


}
