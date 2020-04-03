package function;

public class DropwaveFunction implements Function {

    private final int accuracyOfChromosome;
    private double beginOfSquare = -5.12;
    private double endOfSquare = 5.12;

    public DropwaveFunction(int accuracyOfChromosome){
        this.accuracyOfChromosome = accuracyOfChromosome;
    }

    @Override
    public double getBeginOfSquare() {
        return beginOfSquare;
    }

    @Override
    public double getEndOfSquare() {
        return endOfSquare;
    }


    public int sizeOfBinaryString() {
        return (int) Math.ceil(Math.log((endOfSquare - beginOfSquare) * Math.pow(10, accuracyOfChromosome)) + Math.log(1));
    }

    public double fun(double x1, double x2) {
        double sumSquares = x1 * x1 + x2 * x2;
        return -(1 + Math.cos(12 * Math.sqrt(sumSquares))) / (0.5 * sumSquares + 2);
    }

    @Override
    public double evaluate(double x1, double x2) {
        return -fun(x1, x2);
    }
}
