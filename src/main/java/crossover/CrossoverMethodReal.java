package crossover;

import model.Individual;

import java.util.Collection;

public interface CrossoverMethodReal {
    Collection<Individual> crossover(Individual first, Individual second);
}
