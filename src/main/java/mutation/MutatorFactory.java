package mutation;

public class MutatorFactory {
    public Mutator getMutator(MutationType mutationType) {
        switch (mutationType) {
            case BOUNDARY:
                return new BoundaryMutator();
            case ONE_POINT:
                return new OnePointMutator();
            case TWO_POINTS:
                return new TwoPointMutator();
        }
        throw new IllegalArgumentException();
    }
}
