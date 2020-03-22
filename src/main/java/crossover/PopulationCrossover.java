package crossover;

import converter.Converter;
import model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationCrossover {
    private final Converter converter;
    private final CrossoverMethod crossoverMethod;
    private final double crossoverProbability;
    private final Random random;

    public PopulationCrossover(Converter converter, CrossoverMethod crossoverMethod, double crossoverProbability, Random random) {
        this.converter = converter;
        this.crossoverMethod = crossoverMethod;
        this.crossoverProbability = crossoverProbability;
        this.random = random;
    }

    public List<Individual> crossover(List<Individual> population) {
        ArrayList<Individual> newPopulation = new ArrayList<>();
        int sizeOfPopulation = population.size();
        for (int i = 0; i < sizeOfPopulation; i += 2) {
            Individual ione = population.get(i);
            Individual itwo = population.get(i + 1);
            if (random.nextDouble() > crossoverProbability) {
                byte[] ioneBinary = converter.toBinary(ione.getX1());
                byte[] itwoBinary = converter.toBinary(itwo.getX1());
                byte[][] newTwoIndividuals = crossoverMethod.crossover(ioneBinary, itwoBinary);
                double ioneX1 = (converter.toDecimal(newTwoIndividuals[0]));
                double itwoX1 = (converter.toDecimal(newTwoIndividuals[1]));
                byte[] ioneBinaryX2 = converter.toBinary(ione.getX2());
                byte[] itwoBinaryX2 = converter.toBinary(itwo.getX2());
                byte[][] newTwoIndividualsX2 = crossoverMethod.crossover(ioneBinaryX2, itwoBinaryX2);
                double ioneX2 = (converter.toDecimal(newTwoIndividualsX2[0]));
                double itwoX2 = (converter.toDecimal(newTwoIndividualsX2[1]));
                newPopulation.add(new Individual(ioneX1, ioneX2));
                newPopulation.add(new Individual(itwoX1, itwoX2));
            } else {
                newPopulation.add(ione);
                newPopulation.add(itwo);
            }
        }
        return newPopulation;
    }
}
