package crossover;

import converter.Converter;
import model.Individual;

import java.util.LinkedList;
import java.util.Random;

public class PopulationCrossover {

    Random random = new Random();
    Converter converter;

    public PopulationCrossover(Converter converter) {
        this.converter = converter;
    }

    public LinkedList<Individual> crossover(LinkedList<Individual> population, double probability, CrossoverMethod crossoverMethod) {
        int sizeOfPopulation = population.size();
        for (int i = 0; i < sizeOfPopulation; i += 2) {
            if (random.nextDouble() > probability) {
                Individual ione = population.get(i);
                Individual itwo = population.get(i + 1);
                byte[] ioneBinary = converter.toBinary(ione.getX1());
                byte[] itwoBinary = converter.toBinary(itwo.getX1());
                byte[][] newTwoIndividuals = crossoverMethod.crossover(ioneBinary, itwoBinary);
                ione.setX1(converter.toDecimal(newTwoIndividuals[0]));
                itwo.setX1(converter.toDecimal(newTwoIndividuals[1]));
                byte[] ioneBinaryX2 = converter.toBinary(ione.getX2());
                byte[] itwoBinaryX2 = converter.toBinary(itwo.getX2());
                byte[][] newTwoIndividualsX2 = crossoverMethod.crossover(ioneBinaryX2, itwoBinaryX2);
                ione.setX2(converter.toDecimal(newTwoIndividualsX2[0]));
                itwo.setX2(converter.toDecimal(newTwoIndividualsX2[1]));
            }
        }
        return population;
    }
}
