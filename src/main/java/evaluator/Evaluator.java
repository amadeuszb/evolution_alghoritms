package evaluator;

import function.Function;
import model.Individual;

import java.util.LinkedList;

public class Evaluator {

    private Function function;

    public Evaluator(Function function) {
        this.function = function;
    }

    public LinkedList<Individual> evaluation(LinkedList<Individual> population) {
        for (Individual individual : population) {
            individual.setY(function.fun(individual.getX1(), individual.getX2()));
        }
        return population;
    }
}
