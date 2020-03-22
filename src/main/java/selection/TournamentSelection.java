package selection;

import model.Individual;

import java.util.*;

public class TournamentSelection extends SelectionMethod {

    private final Random random;
    private final int tournamentSize;

    public TournamentSelection(Random random, int tournamentSize) {
        super(random);
        this.random = random;
        this.tournamentSize = tournamentSize;
    }

    @Override
    public List<Individual> select(List<Individual> individuals, int newPopulationSize) {
        ArrayList<Individual> winners = new ArrayList<>();
        for (int i = 0; i < newPopulationSize; i++) {
            winners.add(makeDeterministicTournament(individuals));
        }
        return winners;
    }

    private Individual makeDeterministicTournament(List<Individual> individuals) {
        HashSet<Integer> chosenContestants = new HashSet<>();
        while (chosenContestants.size() < tournamentSize) {
            chosenContestants.add(random.nextInt(individuals.size()));
        }
        double maxY = Double.MIN_VALUE;
        Individual winner = null;
        for (int i : chosenContestants) {
            if (individuals.get(i).getY() > maxY) {
                winner = individuals.get(i);
                maxY = winner.getY();
            }
        }
        return winner;
    }

}
