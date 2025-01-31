package solution;

import converter.ByteSwitcher;
import converter.Converter;
import crossover.CrossoverMethodBinary;
import crossover.CrossoverMethodFactory;
import crossover.CrossoverType;
import crossover.PopulationCrossoverBinary;
import elite.EliteStrategy;
import evaluator.Evaluator;
import function.Function;
import initializer.Initializer;
import inversion.InversionOperator;
import inversion.PopulationInverter;
import model.Individual;
import mutation.MutationType;
import mutation.Mutator;
import mutation.MutatorFactory;
import mutation.PopulationMutatorBinary;
import selection.SelectionMethod;

import java.util.List;
import java.util.Random;

public class SolutionModelBuilderBinary implements SolutionModelBuilder {
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

    public SolutionModelBuilderBinary withPopulationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public SolutionModelBuilderBinary withFunction(Function function) {
        this.function = function;
        return this;
    }

    public SolutionModelBuilderBinary withSelectionMethod(SelectionMethod selectionMethod) {
        this.selectionMethod = selectionMethod;
        return this;
    }

    public SolutionModelBuilderBinary withCrossoverType(CrossoverType crossoverType) {
        this.crossoverType = crossoverType;
        return this;
    }

    public SolutionModelBuilderBinary withMutationType(MutationType mutationType) {
        this.mutationType = mutationType;
        return this;
    }

    public SolutionModelBuilderBinary withMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
        return this;
    }

    public SolutionModelBuilderBinary withCrossoverProbability(double crossoverProbability) {
        this.crossoverProbability = crossoverProbability;
        return this;
    }

    public SolutionModelBuilderBinary withInversionProbability(double inversionProbability) {
        this.inversionProbability = inversionProbability;
        return this;
    }

    public SolutionModelBuilderBinary withRandom(Random random) {
        this.random = random;
        return this;
    }

    public SolutionModelBuilderBinary withElitesCount(int elitesCount) {
        this.elitesCount = elitesCount;
        return this;
    }

    public SolutionModelBuilderBinary withSelectionPercentage(double selectionPercentage) {
        this.selectionPercentage = selectionPercentage;
        return this;
    }

    public SolutionModel build() {
        List<Individual> population = new Initializer(random).getInitialPopulation(populationSize,
                function.getBeginOfSquare(), function
                .getEndOfSquare());
        CrossoverMethodBinary crossoverMethod = new CrossoverMethodFactory(random).getCrossoverMethod(crossoverType);
        SelectionMethod selectionMethod = this.selectionMethod;
        Converter converter = new Converter(function);
        Mutator mutator = new MutatorFactory(function, converter, random, byteSwitcher).getMutator(mutationType);
        PopulationCrossoverBinary populationCrossover = new PopulationCrossoverBinary(converter, crossoverMethod,
                crossoverProbability, random);
        PopulationMutatorBinary populationMutator = new PopulationMutatorBinary(converter, mutator, mutationProbability, random);
        Evaluator evaluator = new Evaluator(function);
        InversionOperator inversionOperator = new InversionOperator(random);
        PopulationInverter populationInverter = new PopulationInverter(inversionOperator, converter, random,
                inversionProbability);
        EliteStrategy eliteStrategy = new EliteStrategy(elitesCount);
        int selectionCount = (int) ((populationSize - elitesCount) * selectionPercentage);
        return new SolutionModelBinary(population, evaluator, selectionMethod, populationCrossover, populationMutator,
                populationInverter, eliteStrategy, selectionCount);
    }
}
