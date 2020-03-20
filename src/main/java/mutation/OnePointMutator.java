package mutation;

public class OnePointMutator implements Mutator {
    @Override
    public byte[] mutate(byte[] chromosome) {
        return new byte[0];
    }
}
