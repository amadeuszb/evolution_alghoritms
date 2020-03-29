package selection;

import model.EvaluatedIndividual;
import model.Individual;

import java.util.*;

public class TournamentSelection implements SelectionMethod {

    private final Random random;
    private final int tournamentSize;

    public TournamentSelection(Random random, int tournamentSize) {
        this.random = random;
        this.tournamentSize = tournamentSize;
    }

    @Override
    public List<Individual> select(List<EvaluatedIndividual> individuals, int newPopulationSize) {
        ArrayList<Individual> winners = new ArrayList<>();
        for (int i = 0; i < newPopulationSize; i++) {
            winners.add(makeDeterministicTournament(individuals));
        }
        return winners;
    }

    private Individual makeDeterministicTournament(List<EvaluatedIndividual> individuals) {
        HashSet<Integer> chosenContestants = new HashSet<>();
        while (chosenContestants.size() < tournamentSize) {
            chosenContestants.add(random.nextInt(individuals.size()));
        }
        double maxY = Double.MIN_VALUE;
        Individual winner = null;
        for (int i : chosenContestants) {
            if (individuals.get(i).getScore() > maxY) {
                winner = individuals.get(i).getIndividual();
                maxY = individuals.get(i).getScore();
            }
        }
        return winner;
    }

}
