package crossover;

import model.Individual;

import java.util.List;

public interface PopulationCrossover {
    List<Individual> crossover(List<Individual> population, int sizeOfPopulation);
}
