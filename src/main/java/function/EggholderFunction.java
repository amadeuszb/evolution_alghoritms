package function;

public class EggholderFunction implements Function {

    private double beginOfSquare = -512;
    private double endOfSquare = 512;

    @Override
    public double fun(double x, double y) {
        return -(y+47)*Math.sin(Math.sqrt(Math.abs((x/2)+(y+47))))-x*Math.sin(Math.sqrt(Math.abs(x-(y+47))));

    }

    @Override
    public int sizeOfBinaryString() {
        return (int) Math.ceil(Math.log((endOfSquare-beginOfSquare)*Math.pow(10,6)) + Math.log(1));
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
