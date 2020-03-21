package mutation;

import java.util.Random;

public class OnePointMutator implements Mutator {
    private final Random random;

    public OnePointMutator(Random random) {
        this.random = random;
    }

    @Override
    public byte[] mutate(byte[] chromosome) {
        int mutatedByteIndex = random.nextInt(chromosome.length);
        if(chromosome[mutatedByteIndex] == 0){
            chromosome[mutatedByteIndex] = 1;
        }else {
            chromosome[mutatedByteIndex] = 0;
        }
        return chromosome;
    }
}
