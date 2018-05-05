package api;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class PlayerIterator {

    private int currentIdx;
    private Player[] players;

    public PlayerIterator(Player... players) {
        this.players = players;
        this.currentIdx = -1;
    }

    public Player next() {
        if (++currentIdx == players.length) {
            currentIdx = 0;
        }
        return players[currentIdx];
    }

    public List<Player> getPlayerList() {
        return ImmutableList.copyOf(players);
    }
}
