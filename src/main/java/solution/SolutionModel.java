package solution;

import model.SolutionScore;

public interface SolutionModel {
    SolutionScore learn(int epochs);
}
