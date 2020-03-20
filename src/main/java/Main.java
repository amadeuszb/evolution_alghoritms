import converter.Converter;
import crossover.Crossover;
import crossover.CrossoverImpl;
import crossover.CrossoverType;
import evaluator.Evaluator;
import function.DropwaveFunction;
import function.EggholderFunction;
import function.Function;
import initializer.Initializer;
import model.Individual;
import mutation.*;
import selection.SelectionMethods;
import selection.SelectionMethodsImpl;

import java.util.*;

public class Main {


    private static double beginOfSquare;
    private static double endOfSquare;
    private static int sizeOfPopulation = 500;
    private static double amountOfEras = 50;
    private static Function function = new DropwaveFunction();
    private static Initializer initializer = new Initializer();

    public static void main(String[] args) {
        beginOfSquare = function.getBeginOfSquare();
        endOfSquare = function.getEndOfSquare();
        Converter converter = new Converter(function);
        MutatorImpl populationMutator = new MutatorImpl(converter, function);
        SelectionMethods selectionMethods = new SelectionMethodsImpl();
        Crossover crossover = new CrossoverImpl(converter, function);
        Evaluator evaluator = new Evaluator(function);
        Mutator mutator = new MutatorFactory().getMutator(MutationType.BOUNDARY);
        long startTime = System.nanoTime();
        LinkedList<Individual> initialPopulation = initializer.getInitialPopulation(sizeOfPopulation, endOfSquare, beginOfSquare);
        for (int i = 0; i < amountOfEras; i++) {
            initialPopulation = evaluator.evaluation(initialPopulation);
            initialPopulation = selectionMethods.rouletteMinimum(initialPopulation);
            initialPopulation = crossover.crossover(initialPopulation, 0.8, CrossoverType.ONE_POINT_CROSSOVER);
            initialPopulation = populationMutator.mutatePopulation(initialPopulation, 0.2, mutator);
        }
        long endTime = System.nanoTime();


        for (Individual i : evaluator.evaluation(initialPopulation)) {
            System.out.println("X1: " + i.getX1() + " X2: " + i.getX2() + " Y: " + i.getY());
        }
        long durationMs = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Czas wykonania: " + durationMs + "ms");
    }

}
