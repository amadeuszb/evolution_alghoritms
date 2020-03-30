package crossover;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TwoPointCrossoverTest {

    @Test
    void crossover() {
        Random random = new Random(1);
        byte[] testChromosome = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] testChromosome2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover(random);
        byte[][] result = twoPointCrossover.crossover(testChromosome, testChromosome2);
        byte[] expected = {0, 0, 0, 0, 0, 1, 1, 1, 0, 0};
        byte[] expected2 = {1, 1, 1, 1, 1, 0, 0, 0, 1, 1};
        assertArrayEquals(expected, result[0]);
        assertArrayEquals(expected2, result[1]);
    }
}