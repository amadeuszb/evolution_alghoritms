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
                return new BestSelection(random, 0.3); //TODO
            case Roulette:
                return new RouletteSelection(random);
            case Tournament:
                return new TournamentSelection(random, 5);
        }
        throw new IllegalArgumentException();
    }
}
