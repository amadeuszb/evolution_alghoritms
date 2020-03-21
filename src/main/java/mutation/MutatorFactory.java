package mutation;

import converter.Converter;
import function.Function;

public class MutatorFactory {
    private final Function function;
    private final Converter converter;

    public MutatorFactory(Function function, Converter converter) {
        this.function = function;
        this.converter = converter;
    }

    public Mutator getMutator(MutationType mutationType) {
        switch (mutationType) {
            case BOUNDARY:
                return new BoundaryMutator(converter.toBinary(function.getBeginOfSquare()), converter.toBinary(function.getEndOfSquare()));
            case ONE_POINT:
                return new OnePointMutator();
            case TWO_POINTS:
                return new TwoPointMutator();
        }
        throw new IllegalArgumentException();
    }
}
