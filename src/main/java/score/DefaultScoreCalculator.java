package score;

import api.Position;

public final class DefaultScoreCalculator implements ScoreCalculator {

    @Override
    public void calculate(Position position) {
        int score = 0;
        for (int i = 0; i < position.getLines().length; i++) {
            score += position.getLines()[i].length;
            for (int j = 0; j < position.getLines()[i].length; j++) {
                if (!position.getLines()[i][j].isFilled()) {
                    score -= position.getLines()[i].length;
                    break;
                }
            }
        }
        position.setScore(score);
    }
}
