package mutation;

import converter.ByteSwitcher;
import converter.Converter;
import function.Function;

import java.util.Random;

public class MutatorFactory {
    private final Function function;
    private final Converter converter;
    private final Random random;
    private final ByteSwitcher byteSwitcher;

    public MutatorFactory(Function function, Converter converter, Random random, ByteSwitcher byteSwitcher) {
        this.function = function;
        this.converter = converter;
        this.random = random;
        this.byteSwitcher = byteSwitcher;
    }

    public Mutator getMutator(MutationType mutationType) {

        switch (mutationType) {
            case BOUNDARY:
                return new BoundaryMutator(converter.toBinary(function.getBeginOfSquare()),
                        converter.toBinary(function.getEndOfSquare()), random);
            case ONE_POINT:
                return new OnePointMutator(random, byteSwitcher);
            case TWO_POINTS:
                return new TwoPointMutator(random, byteSwitcher);
        }
        throw new IllegalArgumentException();
    }
}
