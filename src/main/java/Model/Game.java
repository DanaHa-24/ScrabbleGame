package Model;

import java.util.List;
import Backend.*;

public class Game {
    private Player currentPlayer;
    private List<Player> players;
    private Board board;
    private DictionaryManager dictionaryManager;
    private GameManager gameManager;
    private boolean isGameOver;

    public Game(List<Player> players, Board board, DictionaryManager dictionaryManager, GameManager gameManager) {
        this.players = players;
        this.board = board;
        this.dictionaryManager = dictionaryManager;
        this.gameManager = gameManager;
        this.isGameOver = false;
        this.currentPlayer = players.get(0);
    }

    public void playTurn(String word, List<BoardPosition> positions) {
        if (isGameOver) {
            throw new IllegalStateException("The game is over.");
        }

        if (!dictionaryManager.isValidWord(word)) {
            throw new IllegalArgumentException("Invalid word.");
        }

        if (!board.isWordPossible(positions)) {
            throw new IllegalArgumentException("Invalid word placement.");
        }

        int score = board.placeWord(word, positions);
        currentPlayer.addScore(score);

        if (board.isEmpty()) {
            endGame();
        } else {
            endTurn();
        }
    }

    private void endTurn() {
        int currentIndex = players.indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % players.size();
        currentPlayer = players.get(nextIndex);
    }

    private void endGame() {
        isGameOver = true;
        Player winner = gameManager.getWinner(players);
        gameManager.showEndGameMessage(winner);
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}