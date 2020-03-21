package inversion;

import converter.ByteSwitcher;

import java.util.Random;

public class InversionOperator {

    private final Random random;
    private final ByteSwitcher byteSwitcher;

    public InversionOperator(Random random, ByteSwitcher byteSwitcher) {
        this.random = random;
        this.byteSwitcher = byteSwitcher;
    }

    public byte[] invert(byte[] chromosome) {
        int firstBoundary = random.nextInt(chromosome.length);
        int secondBoundary = random.nextInt(chromosome.length - firstBoundary) + firstBoundary;
        for (int i = firstBoundary; i < secondBoundary; i++) {
            chromosome = byteSwitcher.switchByte(chromosome, i);
        }
        return chromosome;
    }
}
