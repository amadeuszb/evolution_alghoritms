import converter.Converter;
import function.DropwaveFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    private final Converter converter = new Converter(new DropwaveFunction());
    private final Random random = new Random();

    @org.junit.jupiter.api.Test
    void toBinary() {
        double[] testData = new double[10000];
        for (int i = 0; i < testData.length; i++) {
            testData[i] = round(random.nextDouble() * 10.24 - 5.12, 3);
        }
        double[] results = new double[10000];
        for (int i = 0; i < testData.length; i++) {
            byte[] binary = converter.toBinary(testData[i]);
            results[i] = round(converter.toDecimal(binary),3);
        }
        assertArrayEquals(testData, results);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}