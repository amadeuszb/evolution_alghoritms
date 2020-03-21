package mutation;

import java.util.Random;

public class TwoPointMutator implements Mutator {
    private final Random random;

    public TwoPointMutator(Random random) {
        this.random = random;
    }

    @Override
    public byte[] mutate(byte[] chromosome) {
        int firstMutatedByteIndex = random.nextInt(chromosome.length);
        int secondMutatedByteIndex;
        while((secondMutatedByteIndex = random.nextInt(chromosome.length)) == firstMutatedByteIndex){}
        chromosome = mutateByte(chromosome, firstMutatedByteIndex);
        chromosome = mutateByte(chromosome, secondMutatedByteIndex);
        return chromosome;
    }

    private byte[] mutateByte(byte[] chromosome, int index) {
        if (chromosome[index] == 0) {
            chromosome[index] = 1;
        } else {
            chromosome[index] = 0;
        }
        return chromosome;
    }
}
