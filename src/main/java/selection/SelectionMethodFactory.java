package selection;

public class SelectionMethodFactory {
    public SelectionMethod getSelectionMethod(SelectionMethodType methodType) {
        switch (methodType) {
            case Best:
                return new BestSelection();
            case RouletteMaximum:
                return new RouletteMaximumSelection();
            case RouletteMinimum:
                return new RouletteMinimumSelection();
            case Tournament:
                return new TournamentSelection();
        }
        throw new IllegalArgumentException();
    }
}
