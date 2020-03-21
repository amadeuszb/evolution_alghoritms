package selection;

import model.Individual;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TournamentSelection extends SelectionMethod {

    private final int tournamentSize;

    public TournamentSelection(Random random, int tournamentSize) {
        super(random);
        this.tournamentSize = tournamentSize;
    }

    @Override
    public List<Individual> select(List<Individual> individuals, int newPopulationSize) {
        LinkedList<Individual> allTournamentsWinners = makeTournaments(individuals);
        LinkedList<Individual> newPopulation = new LinkedList<Individual>();
        for (int i = 0; i < allTournamentsWinners.size(); i++) {
            for (int j = 0; j < newPopulationSize / (allTournamentsWinners.size()); j++) {
                newPopulation.add(allTournamentsWinners.get(i));
            }
        }
        int rest = newPopulationSize - newPopulation.size();
        for (int i = 0; i < rest; i++) {
            newPopulation.add(allTournamentsWinners.get(i));
        }
        return newPopulation;
    }

    private LinkedList<Individual> makeTournaments(List<Individual> individuals) {
        int amountOfTournaments = individuals.size() / tournamentSize;
        int lastTournamentSize = individuals.size() - tournamentSize * amountOfTournaments;
        LinkedList<Individual> allTournamentsWinners = new LinkedList<Individual>();
        for (int i = 0; i < amountOfTournaments; i++) {
            LinkedList<Individual> tournament = new LinkedList<Individual>();
            for (int j = i * tournamentSize; j < (i + 1) * tournamentSize; j++) {
                tournament.add(individuals.get(j));
            }
            allTournamentsWinners.add(selectTheBest(tournament));
        }
        LinkedList<Individual> lastTournament = new LinkedList<Individual>();
        if (lastTournamentSize != 0) {
            for (int i = 1; i <= lastTournamentSize; i++) {
                lastTournament.add(individuals.get(individuals.size() - i));
            }
            allTournamentsWinners.add(selectTheBest(lastTournament));
        }
        return allTournamentsWinners;
    }

    private Individual selectTheBest(List<Individual> individuals) {
        Individual theBestIndividual = individuals.get(0);
        for (Individual individual : individuals) {
            if (theBestIndividual.getY() < individual.getY())
                theBestIndividual = individual;
        }
        return theBestIndividual;
    }

}
