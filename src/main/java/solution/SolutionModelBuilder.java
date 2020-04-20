package solution;

import crossover.CrossoverType;
import function.Function;
import mutation.MutationType;
import selection.SelectionMethod;

import java.util.Random;

public interface SolutionModelBuilder {

    SolutionModelBuilder withPopulationSize(int populationSize);

    SolutionModelBuilder withFunction(Function function);

    SolutionModelBuilder withSelectionMethod(SelectionMethod selectionMethod);

    SolutionModelBuilder withCrossoverType(CrossoverType crossoverType);

    SolutionModelBuilder withMutationType(MutationType mutationType);

    SolutionModelBuilder withMutationProbability(double mutationProbability);

    SolutionModelBuilder withCrossoverProbability(double crossoverProbability);

    SolutionModelBuilder withInversionProbability(double inversionProbability);

    SolutionModelBuilder withRandom(Random random);

    SolutionModelBuilder withElitesCount(int elitesCount);

    SolutionModelBuilder withSelectionPercentage(double selectionPercentage);

    SolutionModel build();
}
