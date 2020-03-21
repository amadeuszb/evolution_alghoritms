import converter.ByteSwitcher;
import converter.Converter;
import crossover.CrossoverMethod;
import crossover.CrossoverMethodFactory;
import crossover.CrossoverType;
import crossover.PopulationCrossover;
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
import mutation.PopulationMutator;
import selection.SelectionMethod;
import selection.SelectionMethodFactory;
import selection.SelectionMethodType;

import java.util.LinkedList;
import java.util.Random;

public class SolutionModelBuilder {
    private int populationSize;
    private Function function;
    private SelectionMethodType selectionMethodType;
    private CrossoverType crossoverType;
    private MutationType mutationType;
    private double mutationProbability;
    private double crossoverProbability;
    private double inversionProbability;
    private Random random = new Random();
    private ByteSwitcher byteSwitcher = new ByteSwitcher();
    private int elitesCount;

    public SolutionModelBuilder withPopulationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public SolutionModelBuilder withFunction(Function function) {
        this.function = function;
        return this;
    }

    public SolutionModelBuilder withSelectionMethod(SelectionMethodType selectionMethodType) {
        this.selectionMethodType = selectionMethodType;
        return this;
    }

    public SolutionModelBuilder withCrossoverType(CrossoverType crossoverType) {
        this.crossoverType = crossoverType;
        return this;
    }

    public SolutionModelBuilder withMutationType(MutationType mutationType) {
        this.mutationType = mutationType;
        return this;
    }

    public SolutionModelBuilder withMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
        return this;
    }

    public SolutionModelBuilder withCrossoverProbability(double crossoverProbability) {
        this.crossoverProbability = crossoverProbability;
        return this;
    }

    public SolutionModelBuilder withInversionProbability(double inversionProbability) {
        this.inversionProbability = inversionProbability;
        return this;
    }

    public SolutionModelBuilder withRandomSeed(long seed) {
        random.setSeed(seed);
        return this;
    }

    public SolutionModelBuilder withElitesCount(int elitesCount) {
        this.elitesCount = elitesCount;
        return this;
    }

    public SolutionModel build() {
        LinkedList<Individual> population = new Initializer(random).getInitialPopulation(populationSize, function.getBeginOfSquare(), function.getEndOfSquare());
        CrossoverMethod crossoverMethod = new CrossoverMethodFactory(random).getCrossoverMethod(crossoverType);
        SelectionMethod selectionMethod = new SelectionMethodFactory(random).getSelectionMethod(selectionMethodType);
        Converter converter = new Converter(function);
        Mutator mutator = new MutatorFactory(function, converter, random, byteSwitcher).getMutator(mutationType);
        PopulationCrossover populationCrossover = new PopulationCrossover(converter, crossoverMethod, crossoverProbability, random);
        PopulationMutator populationMutator = new PopulationMutator(converter, mutator, mutationProbability, random);
        Evaluator evaluator = new Evaluator(function);
        InversionOperator inversionOperator = new InversionOperator(random, byteSwitcher);
        PopulationInverter populationInverter = new PopulationInverter(inversionOperator, converter, random, inversionProbability);
        EliteStrategy eliteStrategy = new EliteStrategy(elitesCount);
        return new SolutionModel(population, evaluator, selectionMethod, populationCrossover, populationMutator, populationInverter, eliteStrategy);
    }
}
