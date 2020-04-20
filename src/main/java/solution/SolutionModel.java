package solution;

import crossover.PopulationCrossoverBinary;
import elite.EliteStrategy;
import evaluator.Evaluator;
import inversion.PopulationInverter;
import model.EvaluatedIndividual;
import model.Individual;
import model.SolutionScore;
import mutation.PopulationMutator;
import selection.SelectionMethod;

import java.util.List;

public class SolutionModel {
    private final Evaluator evaluator;
    List<Individual> population;
    private final SelectionMethod selectionMethod;
    private final PopulationCrossoverBinary populationCrossover;
    private final PopulationMutator populationMutator;
    private final PopulationInverter populationInverter;
    private final EliteStrategy eliteStrategy;
    private final int selectionCount;

    public SolutionModel(List<Individual> population, Evaluator evaluator, SelectionMethod selectionMethod,
                         PopulationCrossoverBinary populationCrossover, PopulationMutator populationMutator,
                         PopulationInverter populationInverter, EliteStrategy eliteStrategy, int selectionCount) {
        this.population = population;
        this.evaluator = evaluator;
        this.selectionMethod = selectionMethod;
        this.populationCrossover = populationCrossover;
        this.populationMutator = populationMutator;
        this.populationInverter = populationInverter;
        this.eliteStrategy = eliteStrategy;
        this.selectionCount = selectionCount;
    }

    public SolutionScore learn(int epochs) {
        SolutionScore solutionScore = new SolutionScore();
        List<EvaluatedIndividual> evaluatedPopulation;
        evaluatedPopulation = evaluator.evaluation(population);

        solutionScore.getEpochs().add(evaluatedPopulation);
        long startTime = System.nanoTime();
        for (int i = 0; i < epochs; i++) {
            List<Individual> elites = eliteStrategy.getElites(evaluatedPopulation);
            population = selectionMethod.select(evaluatedPopulation, selectionCount);
            population = populationCrossover.crossover(population, evaluatedPopulation.size() - elites.size());
            population = populationMutator.mutatePopulation(population);
            population = populationInverter.invertPopulation(population);
            population.addAll(elites);
            evaluatedPopulation = evaluator.evaluation(population);
            solutionScore.getEpochs().add(evaluatedPopulation);
        }
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        solutionScore.setTimeOfExecutionInMs(durationMs);
        return solutionScore;
    }
}
