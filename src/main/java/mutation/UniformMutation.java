package mutation;

import function.Function;
import model.Individual;

import java.util.Random;

public class UniformMutation implements MutationReal {
    private final Function function;
    private final Random random;

    public UniformMutation(Function function, Random random) {
        this.function = function;
        this.random = random;
    }

    private double getRandomInBoundary() {
        return random.nextDouble() * (function.getEndOfSquare() - function.getBeginOfSquare()) + function.getBeginOfSquare();
    }

    @Override
    public Individual mutate(Individual individual) {
        if (random.nextBoolean()) {
            return new Individual(individual.getX1(), getRandomInBoundary());
        } else {
            return new Individual(getRandomInBoundary(), individual.getX2());
        }
    }
}
