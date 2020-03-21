package crossover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomogeneousCrossoverTest {

    @Test
    void crossover() {
        byte[] testChromosome = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] testChromosome2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        HomogeneousCrossover crossover = new HomogeneousCrossover();
        byte[][] result = crossover.crossover(testChromosome, testChromosome2);
        byte[] expected = {0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
        byte[] expected2 = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
        assertArrayEquals(expected, result[0]);
        assertArrayEquals(expected2, result[1]);
    }
}