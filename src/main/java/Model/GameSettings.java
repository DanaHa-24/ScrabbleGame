package Model;


public class GameSettings {
    private int boardSize;
    private int numOfPlayers;

    public GameSettings(int boardSize, int numOfPlayers) {
        this.boardSize = boardSize;
        this.numOfPlayers = numOfPlayers;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }
}
