package score;

import api.Position;

public final class NegativeScoreCalculator implements ScoreCalculator {

    @Override
    public void calculate(Position position) {
        int score = 0;
        for (int i = 0; i < position.getLines().length; i++) {
            int notFilledCount = 0;
            for (int j = 0; j < position.getLines()[i].length; j++) {
                if (!position.getLines()[i][j].isFilled()) {
                    notFilledCount++;
                }
            }
            if (notFilledCount == 0) {
                score += position.getLines()[i].length;
            } else if (notFilledCount == 2) {
                score -= position.getLines()[i].length;
            }
        }
        position.setScore(score);
    }
}
