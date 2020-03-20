package crossover;

public class CrossoverMethodFactory {
    public CrossoverMethod getCrossoverMethod(CrossoverType crossoverType) {
        switch (crossoverType) {
            case OnePoint:
                return new OnePointCrossover();
            case TwoPoints:
                return new TwoPointCrossover();
            case ThreePoints:
                return new ThreePointCrossover();
            case Homogeneous:
                return new HomogeneousCrossover();
        }
        throw new IllegalArgumentException();
    }
}
