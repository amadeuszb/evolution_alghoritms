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
import mutation.MutationType;
import mutation.MutatorImpl;
import selection.SelectionMethods;
import selection.SelectionMethodsImpl;

import java.util.*;

public class Main {


    private static double beginOfSquare;
    private static double endOfSquare;
    private static int sizeOfPopulation = 500;
    private static double amountOfEras = 50;
    private static Function function = new EggholderFunction();
    private static Initializer initializer = new Initializer();
    
    public static void main(String[] args) {
        beginOfSquare = function.getBeginOfSquare();
        endOfSquare = function.getEndOfSquare();
        Converter converter = new Converter(function);
        MutatorImpl mutator = new MutatorImpl(converter, function);
        SelectionMethods selectionMethods = new SelectionMethodsImpl();
        Crossover crossover = new CrossoverImpl(converter, function);
        Evaluator evaluator = new Evaluator(function);
        LinkedList<Individual> initialPopulation = initializer.getInitialPopulation(sizeOfPopulation, endOfSquare, beginOfSquare);
        for (int i = 0; i < amountOfEras; i++) {
            initialPopulation = evaluator.evaluation(initialPopulation);
            initialPopulation = selectionMethods.rouletteMinimum(initialPopulation);
            initialPopulation = crossover.crossover(initialPopulation, 0.2, CrossoverType.ONE_POINT_CROSSOVER);
            initialPopulation = mutator.mutatePopulation(initialPopulation, 0.2, MutationType.BOUNDARY); //TODO revert propability
        }
        for (Individual i : evaluator.evaluation(initialPopulation)) {
            System.out.println(i.getY());
        }
    }

}
