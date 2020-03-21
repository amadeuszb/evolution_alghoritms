package crossover;

import java.util.Random;

public class CrossoverMethodFactory {
    private final Random random;

    public CrossoverMethodFactory(Random random) {
        this.random = random;
    }

    public CrossoverMethod getCrossoverMethod(CrossoverType crossoverType) {
        switch (crossoverType) {
            case OnePoint:
                return new OnePointCrossover(random);
            case TwoPoints:
                return new TwoPointCrossover(random);
            case ThreePoints:
                return new ThreePointCrossover(random);
            case Homogeneous:
                return new HomogeneousCrossover();
        }
        throw new IllegalArgumentException();
    }
}
