import crossover.PopulationCrossover;
import evaluator.Evaluator;
import inversion.PopulationInverter;
import model.Individual;
import mutation.PopulationMutator;
import selection.SelectionMethod;

import java.util.LinkedList;
import java.util.List;

public class SolutionModel {
    private final Evaluator evaluator;
    List<Individual> population;
    private final SelectionMethod selectionMethod;
    private final PopulationCrossover populationCrossover;
    private final PopulationMutator populationMutator;
    private final PopulationInverter populationInverter;

    public SolutionModel(LinkedList<Individual> population, Evaluator evaluator, SelectionMethod selectionMethod,
                         PopulationCrossover populationCrossover, PopulationMutator populationMutator,
                         PopulationInverter populationInverter) {
        this.population = population;
        this.evaluator = evaluator;
        this.selectionMethod = selectionMethod;
        this.populationCrossover = populationCrossover;
        this.populationMutator = populationMutator;
        this.populationInverter = populationInverter;
    }

    public List<Individual> learn(int epochs) {
        for (int i = 0; i < epochs; i++) {
            population = evaluator.evaluation(population);
            population = selectionMethod.select(population);
            population = populationCrossover.crossover(population);
            population = populationMutator.mutatePopulation(population);
            population = populationInverter.invertPopulation(population);
        }
        return population;
    }
}
