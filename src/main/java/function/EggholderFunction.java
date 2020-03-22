package function;

public class EggholderFunction implements Function {

    private double beginOfSquare = -512;
    private double endOfSquare = 512;

    @Override
    public double fun(double x1, double x2) {
        return -(x2 + 47) * Math.sin(Math.sqrt(Math.abs((x1 / 2) + (x2 + 47)))) - x1 * Math.sin(Math.sqrt(Math.abs(x1 - (x2 + 47))));
    }

    @Override
    public double evaluate(double x1, double x2) {
        double val = (fun(x1, x2) + 959.6407);
        if (val == 0)
            return Double.MAX_VALUE;
        return 1 / val;
    }

    @Override
    public int sizeOfBinaryString() {
        return (int) Math.ceil(Math.log((endOfSquare - beginOfSquare) * Math.pow(10, 6)) + Math.log(1));
    }

    @Override
    public double getBeginOfSquare() {
        return beginOfSquare;
    }

    @Override
    public double getEndOfSquare() {
        return endOfSquare;
    }
}
