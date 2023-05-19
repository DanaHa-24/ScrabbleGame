package Model;

import Backend.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class GameManager implements Observer {
    private int currentPlayerIndex;
    private List<Player> players;
    private Game game;
    private DictionaryManager dictionaryManager;
    private Map<Player, Integer> scores;

    public GameManager(List<String> playerNames, GameSettings gameSettings, DictionaryManager dictionaryManager) {
        this.players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        this.game = new Game(gameSettings);
        this.dictionaryManager = dictionaryManager;
        this.scores = new HashMap<>();
        for (Player player : players) {
            scores.put(player, 0);
        }
    }
    public void startGame() {
        Collections.shuffle(players);
        currentPlayerIndex = 0;
        for (Player player : players) {
            player.resetTiles();
            game.fillTilesBag(player.getTilesRack());
        }
    }

    public void update(Observable o, Object arg) {
        Move move = (Move) arg;
        if (isValidMove(move)) {
            int wordScore = game.calculateScore(move);
            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.addScore(wordScore);
            scores.put(currentPlayer, scores.get(currentPlayer) + wordScore);
            currentPlayer.removeTiles(move.getTiles());
            if (game.isGameOver()) {
                endGame();
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
    }

    private boolean isValidMove(Move move) {
        return game.isMoveValid(move) && dictionaryManager.isWordValid(move.getWord());
    }

    private void endGame() {
        int maxScore = 0;
        List<Player> winners = new ArrayList<>();
        for (Player player : players) {
            int score = scores.get(player);
            if (score > maxScore) {
                maxScore = score;
                winners.clear();
                winners.add(player);
            } else if (score == maxScore) {
                winners.add(player);
            }
        }
        System.out.println("Game Over! The winners are: " + winners);
    }
}
