package Model;

import Backend.*;

public class ScrabbleFacade {
    private MyServer server;
    private Board board;
    private DictionaryManager dictionaryManager;
    private CacheManager cacheManager;

    public ScrabbleFacade() {
        this.server = null;
        this.board = null;
        this.dictionaryManager = null;
        this.cacheManager = null;
    }

    public void startServer(int port, ClientHandler ch) {
        this.server = new MyServer(port, ch);
        this.server.start();
    }

    public void stopServer() {
        this.server.close();
    }

    public void createBoard(int numRows, int numCols) {
        this.board = new Board(numRows, numCols);
    }

    public Board getBoard() {
        return this.board;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionaryManager = new DictionaryManager(dictionary);
    }

    public boolean checkWord(String word) {
        if (this.dictionaryManager == null) {
            throw new IllegalStateException("Dictionary has not been set.");
        }
        return this.dictionaryManager.checkWord(word);
    }

    // CacheManager related methods
    public void setCacheManager(CacheReplacementPolicy policy, int cacheSize) {
        this.cacheManager = new CacheManager(policy, cacheSize);
    }

    public boolean isCached(String word) {
        if (this.cacheManager == null) {
            throw new IllegalStateException("CacheManager has not been set.");
        }
        return this.cacheManager.isCached(word);
    }

    public void addToCache(String word) {
        if (this.cacheManager == null) {
            throw new IllegalStateException("CacheManager has not been set.");
        }
        this.cacheManager.addToCache(word);
    }

    public boolean isValidTile(char letter) {
        return Tile.isValidTile(letter);
    }

    public boolean isValidPosition(int row, int col) {
        return Board.isValidPosition(row, col);
    }

    public int getTileScore(char letter) {
        return Tile.getTileScore(letter);
    }

    public void placeWord(){

    }
    public void challenge(){

    }

    public void createServer(){


    }

    public void joinServer(){

    }

    public void getAvailableTiles(){

    }
}
