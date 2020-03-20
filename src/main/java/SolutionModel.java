import crossover.PopulationCrossover;
import evaluator.Evaluator;
import model.Individual;
import mutation.MutatorImpl;
import selection.SelectionMethod;

import java.util.LinkedList;
import java.util.List;

public class SolutionModel {
    private final Evaluator evaluator;
    List<Individual> population;
    private final SelectionMethod selectionMethod;
    private final PopulationCrossover populationCrossover;
    private final MutatorImpl populationMutator;

    public SolutionModel(LinkedList<Individual> population, Evaluator evaluator, SelectionMethod selectionMethod, PopulationCrossover populationCrossover, MutatorImpl populationMutator) {
        this.population = population;
        this.evaluator = evaluator;
        this.selectionMethod = selectionMethod;
        this.populationCrossover = populationCrossover;
        this.populationMutator = populationMutator;
    }

    public List<Individual> learn(int epochs) {
        for (int i = 0; i < epochs; i++) {
            population = evaluator.evaluation(population);
            population = selectionMethod.select(population);// TODO change all to List
            population = populationCrossover.crossover(population, 0.8);
            population = populationMutator.mutatePopulation(population, 0.2);
        }
        return population;
    }
}
