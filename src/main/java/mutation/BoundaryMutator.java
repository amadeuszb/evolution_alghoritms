package mutation;

public class BoundaryMutator implements Mutator {
    @Override
    public byte[] mutate(byte[] chromosome) {
        byte boundaryValue = chromosome[chromosome.length - 1];
        if (boundaryValue == 0) chromosome[chromosome.length - 1] = 1;
        else chromosome[chromosome.length - 1] = 0;
        return chromosome;
    }
}
