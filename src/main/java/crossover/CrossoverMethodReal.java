package crossover;

import model.Individual;

public interface CrossoverMethodReal {
    Individual[] crossover(Individual first, Individual second);
}
