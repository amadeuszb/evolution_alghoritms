import crossover.CrossoverType;
import evaluator.Evaluator;
import function.DropwaveFunction;
import function.Function;
import model.Individual;
import mutation.*;
import selection.SelectionMethodType;

import java.util.*;

public class Main {
    private final static int sizeOfPopulation = 500;
    private final static int amountOfEras = 1000;
    private static Function function = new DropwaveFunction();

    public static void main(String[] args) {
        SolutionModelBuilder modelBuilder = new SolutionModelBuilder();
        modelBuilder.withCrossoverType(CrossoverType.ThreePoints)
                .withFunction(function)
                .withMutationType(MutationType.ONE_POINT)
                .withPopulationSize(sizeOfPopulation)
                .withSelectionMethod(SelectionMethodType.RouletteMaximum)
                .withCrossoverProbability(0.9)
                .withMutationProbability(0.05)
                .withInversionProbability(0.001)
                .withRandomSeed(1)
                .withElitesCount(10);

        SolutionModel solutionModel = modelBuilder.build();

        long startTime = System.nanoTime();
        List<Individual> population = solutionModel.learn(amountOfEras);
        long endTime = System.nanoTime();

        Evaluator evaluator = new Evaluator(function);
        for (Individual i : evaluator.evaluation(population)) {
            System.out.println("X1: " + i.getX1() + " X2: " + i.getX2() + " Y: " + i.getY());
        }
        long durationMs = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Czas wykonania: " + durationMs + "ms");
    }

}
