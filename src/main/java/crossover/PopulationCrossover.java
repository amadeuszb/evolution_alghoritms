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

    public List<Individual> crossover(List<Individual> population, int sizeOfPopulation) {
        ArrayList<Individual> newPopulation = new ArrayList<>();
        int selectedPopulationSize = population.size();
        for (int i = 0; i < sizeOfPopulation; i += 2) {
            Individual ione = population.get(random.nextInt(selectedPopulationSize));
            Individual itwo = population.get(random.nextInt(selectedPopulationSize));
            if (random.nextDouble() < crossoverProbability) {
                byte[] ioneBinary = converter.toBinary(ione);
                byte[] itwoBinary = converter.toBinary(itwo);
                byte[][] newTwoIndividuals = crossoverMethod.crossover(ioneBinary, itwoBinary);
                newPopulation.add(converter.toIndividual(newTwoIndividuals[0]));
                newPopulation.add(converter.toIndividual(newTwoIndividuals[1]));
            } else {
                newPopulation.add(ione);
                newPopulation.add(itwo);
            }
        }
        return newPopulation;
    }
}
