package selection;

import model.Individual;

import java.util.LinkedList;

public interface SelectionMethods {

    LinkedList<Individual> rouletteMinimum(LinkedList<Individual> individuals);

    LinkedList<Individual> rouletteMaximum(LinkedList<Individual> individuals);

    LinkedList<Individual> tournament(LinkedList<Individual> individuals);

    LinkedList<Individual> theBest(LinkedList<Individual> individuals);

}
