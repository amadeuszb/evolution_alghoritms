package mutation;

public class BoundaryMutator implements Mutator {
    @Override
    public byte[] mutate(byte[] chromosome) {
        byte boundaryValue = chromosome[chromosome.length - 1];
        chromosome[chromosome.length - 1] = boundaryValue == 0 ? (byte) 1 : (byte) 0;
        return chromosome;
    }
}
