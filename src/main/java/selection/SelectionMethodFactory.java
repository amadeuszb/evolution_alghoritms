package selection;

import java.util.Random;

public class SelectionMethodFactory {
    private final Random random;

    public SelectionMethodFactory(Random random) {
        this.random = random;
    }

    public SelectionMethod getSelectionMethod(SelectionMethodType methodType) {
        switch (methodType) {
            case Best:
                return new BestSelection(random);
            case RouletteMaximum:
                return new RouletteMaximumSelection(random);
            case RouletteMinimum:
                return new RouletteMinimumSelection(random);
            case Tournament:
                return new TournamentSelection(random, 5); //TODO: 
        }
        throw new IllegalArgumentException();
    }
}
