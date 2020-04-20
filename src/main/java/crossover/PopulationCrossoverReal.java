package crossover;

import model.Individual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PopulationCrossoverReal implements PopulationCrossover {
    private final CrossoverMethodReal crossoverMethod;
    private final double crossoverProbability;
    private final Random random;

    public PopulationCrossoverReal(CrossoverMethodReal crossoverMethod, double crossoverProbability, Random random) {
        this.crossoverMethod = crossoverMethod;
        this.crossoverProbability = crossoverProbability;
        this.random = random;
    }

    @Override
    public List<Individual> crossover(List<Individual> population, int sizeOfPopulation) {
        ArrayList<Individual> newPopulation = new ArrayList<>();
        int selectedPopulationSize = population.size();
        for (int i = 0; i < sizeOfPopulation; i += 2) {
            Individual ione = population.get(random.nextInt(selectedPopulationSize));
            Individual itwo = population.get(random.nextInt(selectedPopulationSize));
            if (random.nextDouble() < crossoverProbability) {
                newPopulation.addAll(Arrays.asList(crossoverMethod.crossover(ione, itwo)));
            } else {
                newPopulation.add(ione);
                newPopulation.add(itwo);
            }
        }
        return newPopulation;
    }
}
