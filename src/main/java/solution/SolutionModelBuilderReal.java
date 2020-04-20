package solution;

import converter.ByteSwitcher;
import converter.Converter;
import crossover.*;
import elite.EliteStrategy;
import evaluator.Evaluator;
import function.Function;
import initializer.Initializer;
import inversion.InversionOperator;
import inversion.PopulationInverter;
import model.Individual;
import mutation.*;
import selection.SelectionMethod;

import java.util.List;
import java.util.Random;

public class SolutionModelBuilderReal implements SolutionModelBuilder {
    private int populationSize;
    private Function function;
    private SelectionMethod selectionMethod;
    private CrossoverType crossoverType;
    private MutationType mutationType;
    private double mutationProbability;
    private double crossoverProbability;
    private double inversionProbability;
    private Random random = new Random();
    private ByteSwitcher byteSwitcher = new ByteSwitcher();
    private int elitesCount;
    private double selectionPercentage;

    public SolutionModelBuilderReal withPopulationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public SolutionModelBuilderReal withFunction(Function function) {
        this.function = function;
        return this;
    }

    public SolutionModelBuilderReal withSelectionMethod(SelectionMethod selectionMethod) {
        this.selectionMethod = selectionMethod;
        return this;
    }

    public SolutionModelBuilderReal withCrossoverType(CrossoverType crossoverType) {
        this.crossoverType = crossoverType;
        return this;
    }

    public SolutionModelBuilderReal withMutationType(MutationType mutationType) {
        this.mutationType = mutationType;
        return this;
    }

    public SolutionModelBuilderReal withMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
        return this;
    }

    public SolutionModelBuilderReal withCrossoverProbability(double crossoverProbability) {
        this.crossoverProbability = crossoverProbability;
        return this;
    }

    public SolutionModelBuilderReal withInversionProbability(double inversionProbability) {
        this.inversionProbability = inversionProbability;
        return this;
    }

    public SolutionModelBuilderReal withRandom(Random random) {
        this.random = random;
        return this;
    }

    public SolutionModelBuilderReal withElitesCount(int elitesCount) {
        this.elitesCount = elitesCount;
        return this;
    }

    public SolutionModelBuilderReal withSelectionPercentage(double selectionPercentage) {
        this.selectionPercentage = selectionPercentage;
        return this;
    }

    public SolutionModel build() {
        List<Individual> population = new Initializer(random).getInitialPopulation(populationSize,
                function.getBeginOfSquare(), function
                .getEndOfSquare());
        CrossoverMethodReal crossoverMethod;
        if (this.crossoverType == CrossoverType.Arithmetic) {
            crossoverMethod = new ArithmeticCrossover(random);
        } else {
            crossoverMethod = new HeuristicCrossover(random);
        }
        MutationReal mutationReal;
        if (this.mutationType == MutationType.Uniform) {
            mutationReal = new UniformMutation(function, random);
        } else {
            mutationReal = new IndexMutation();
        }
        SelectionMethod selectionMethod = this.selectionMethod;
        PopulationCrossover populationCrossover = new PopulationCrossoverReal(crossoverMethod, crossoverProbability,
                random);
        PopulationMutator populationMutator = new PopulationMutatorReal(mutationReal, mutationProbability, random);
        Evaluator evaluator = new Evaluator(function);
        EliteStrategy eliteStrategy = new EliteStrategy(elitesCount);
        int selectionCount = (int) ((populationSize - elitesCount) * selectionPercentage);
        return new SolutionModelReal(population, evaluator, selectionMethod, populationCrossover, populationMutator,
                eliteStrategy, selectionCount);
    }
}
