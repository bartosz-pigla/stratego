package algorithm;

import api.Game;
import api.Player;
import api.Position;
import gui.Gui;
import lombok.AllArgsConstructor;
import score.ScoreCalculator;

@AllArgsConstructor
public final class Human implements Algorithm {

    private Gui gui;
    private Game game;
    private ScoreCalculator calculator;

    @Override
    public int play(Player player) {
//        gui.printStateOfGame(game.getGameMatrix());
//        gui.printScoreOfPlayer(player);
        Position position = gui.getPosition(game, player);
        game.fillPosition(position.getPosX(), position.getPosY(), true);
        calculator.calculate(position);
        return position.getScore();
    }
}
