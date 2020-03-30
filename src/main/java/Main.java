import model.EvaluatedIndividual;
import model.SolutionScore;
import selection.BestSelection;
import solution.SolutionModel;
import solution.SolutionModelBuilder;
import crossover.CrossoverType;
import function.DropwaveFunction;
import function.Function;
import mutation.*;

import java.util.*;

public class Main {
    private final static int sizeOfPopulation = 500;
    private final static int amountOfEras = 1000;
    private static Function function = new DropwaveFunction();

    public static void main(String[] args) {
        SolutionModelBuilder modelBuilder = new SolutionModelBuilder();
        modelBuilder.withCrossoverType(CrossoverType.OnePoint)
                .withFunction(function)
                .withMutationType(MutationType.ONE_POINT)
                .withPopulationSize(sizeOfPopulation)
                .withSelectionMethod(new BestSelection(0.3))
                .withCrossoverProbability(0.8)
                .withMutationProbability(0.05)
                .withInversionProbability(0.001)
                .withRandom(new Random(1))
                .withElitesCount(10);

        SolutionModel solutionModel = modelBuilder.build();

        SolutionScore score = solutionModel.learn(amountOfEras);
        List<List<EvaluatedIndividual>> epochs = score.getEpochs();
        List<EvaluatedIndividual> lastEpoch = epochs.get(epochs.size() - 1);
        for (EvaluatedIndividual i : lastEpoch) {
            System.out.println("X1: " + i.getIndividual().getX1() + " X2: " + i.getIndividual().getX2() + " Y: " + i.getScore());
        }
        System.out.println("Czas wykonania: " + score.getTimeOfExecution() + "ms");
    }

}
