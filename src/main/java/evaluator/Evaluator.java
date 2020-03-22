package evaluator;

import function.Function;
import model.EvaluatedIndividual;
import model.Individual;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {

    private Function function;

    public Evaluator(Function function) {
        this.function = function;
    }

    public List<EvaluatedIndividual> evaluation(List<Individual> population) {
        ArrayList<EvaluatedIndividual> evaluatedIndividuals = new ArrayList<>();
        for (Individual individual : population) {
            evaluatedIndividuals.add(new EvaluatedIndividual(individual, function.evaluate(individual.getX1(), individual.getX2())));
        }
        return evaluatedIndividuals;
    }
}
