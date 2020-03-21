package mutation;

import converter.Converter;
import function.Function;

import java.util.Random;

public class MutatorFactory {
    private final Function function;
    private final Converter converter;
    private final Random random;

    public MutatorFactory(Function function, Converter converter, Random random) {
        this.function = function;
        this.converter = converter;
        this.random = random;
    }

    public Mutator getMutator(MutationType mutationType) {
        switch (mutationType) {
            case BOUNDARY:
                return new BoundaryMutator(converter.toBinary(function.getBeginOfSquare()),
                        converter.toBinary(function.getEndOfSquare()), random);
            case ONE_POINT:
                return new OnePointMutator();
            case TWO_POINTS:
                return new TwoPointMutator();
        }
        throw new IllegalArgumentException();
    }
}
