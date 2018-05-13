package sorter;

import java.util.List;

import api.Position;
import lombok.AllArgsConstructor;
import score.ScoreCalculator;

@AllArgsConstructor
public final class DescendingSorter implements PositionsSorter {

    private ScoreCalculator calculator;

    @Override
    public List<Position> sort(List<Position> positions) {
        positions.sort((p1, p2) -> {
            calculator.calculate(p1);
            calculator.calculate(p2);
            return p2.getScore() - p1.getScore();
        });
        return positions;
    }
}
