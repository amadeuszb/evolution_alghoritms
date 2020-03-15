public class DropwaveFunction {

    private static double beginOfSquare = -5.12;
    private static double endOfSquare = 5.12;

    public static int sizeOfBinaryString(){
        return (int) Math.ceil(Math.log((endOfSquare-beginOfSquare)*Math.pow(10,6)) + Math.log(1));
    }

    public static double fun(double x1, double x2) {
        double sumSquares = x1 * x1 + x2 * x2;
        return -(1 + Math.cos(12 * Math.sqrt(sumSquares))) / (0.5 * sumSquares + 2);
    }

}
