package mutation;

import model.Individual;

import java.util.LinkedList;

public interface Mutator {//TODO methods

    byte[] boundaryMutation(byte[] chromosome);

    byte[] onePointMutation(byte[] chromosome);

    byte[] twoPointsMutation(byte[] chromosome);

    LinkedList<Individual> mutatePopulation(LinkedList<Individual> population, double probability, MutationType mutationType);

}

