package converter;

public class ByteSwitcher {

    public byte[] switchByte(byte[] chromosome, int index) {
        if (chromosome[index] == 0) {
            chromosome[index] = 1;
        } else {
            chromosome[index] = 0;
        }
        return chromosome;
    }
}
