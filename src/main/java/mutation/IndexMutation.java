package mutation;

import model.Individual;

public class IndexMutation implements MutationReal {

    @Override
    public Individual mutate(Individual individual) {
        return new Individual(individual.getX2(), individual.getX1());
    }
}
