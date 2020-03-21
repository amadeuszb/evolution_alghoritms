package selection;

import model.Individual;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BestSelection extends SelectionMethod {

    private final double percentageOfBest;

    public BestSelection(Random random, double percentageOfBest) {
        super(random);
        this.percentageOfBest = percentageOfBest;
    }

    @Override
    public List<Individual> select(List<Individual> individuals, int newPopulationSize) {
        LinkedList<Individual> newPopulation = new LinkedList<Individual>();
        LinkedList<Individual> theBestPopulation = new LinkedList<Individual>();
        int amountOfBests = (int) (individuals.size() / percentageOfBest);
        for (int i = 0; i < amountOfBests; i++) {
            Individual theBestOfPopulation = selectTheBest(individuals);
            individuals.remove(theBestOfPopulation);
            theBestPopulation.add(theBestOfPopulation);
        }
        for (int i = 0; i < newPopulationSize; i++) {
            newPopulation.add(theBestPopulation.get(i % amountOfBests));
        }
        return newPopulation;
    }

    private Individual selectTheBest(List<Individual> individuals) {
        Individual theBestIndividual = individuals.get(0);
        for (Individual individual : individuals) {
            if (theBestIndividual.getY() < individual.getY())
                theBestIndividual = individual;
        }
        return theBestIndividual;
    }
}
