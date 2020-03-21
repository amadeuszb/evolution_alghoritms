package inversion;

import converter.ByteSwitcher;

import java.util.Random;

public class InversionOperator {

    private final Random random;

    public InversionOperator(Random random) {
        this.random = random;
    }

    public byte[] invert(byte[] chromosome) {
        int firstBoundary = random.nextInt(chromosome.length);
        int secondBoundary = random.nextInt(chromosome.length - firstBoundary) + firstBoundary;
        byte placeholder;
        for (int i = firstBoundary; i < secondBoundary; i++) {
            placeholder = chromosome[i];
            chromosome[i] = chromosome[secondBoundary-i];
            chromosome[secondBoundary-i] = placeholder;
        }
        return chromosome;
    }
}
