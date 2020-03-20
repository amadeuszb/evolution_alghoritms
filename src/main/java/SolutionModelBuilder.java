import converter.Converter;
import crossover.CrossoverMethod;
import crossover.CrossoverMethodFactory;
import crossover.CrossoverType;
import crossover.PopulationCrossover;
import evaluator.Evaluator;
import function.Function;
import initializer.Initializer;
import model.Individual;
import mutation.MutationType;
import mutation.Mutator;
import mutation.MutatorFactory;
import mutation.MutatorImpl;
import selection.SelectionMethod;
import selection.SelectionMethodFactory;
import selection.SelectionMethodType;

import java.util.LinkedList;

public class SolutionModelBuilder {
    private int populationSize;
    private Function function;
    private SelectionMethodType selectionMethodType;
    private CrossoverType crossoverType;
    private MutationType mutationType;

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

    public SolutionModel build() {
        LinkedList<Individual> population = new Initializer().getInitialPopulation(populationSize, function.getBeginOfSquare(), function.getEndOfSquare());
        CrossoverMethod crossoverMethod = new CrossoverMethodFactory().getCrossoverMethod(crossoverType);
        SelectionMethod selectionMethod = new SelectionMethodFactory().getSelectionMethod(selectionMethodType);
        Mutator mutator = new MutatorFactory().getMutator(mutationType);
        Converter converter = new Converter(function);
        PopulationCrossover populationCrossover = new PopulationCrossover(converter, crossoverMethod);
        MutatorImpl populationMutator = new MutatorImpl(converter, mutator);
        Evaluator evaluator = new Evaluator(function);
        return new SolutionModel(population, evaluator, selectionMethod, populationCrossover, populationMutator);
    }
}
