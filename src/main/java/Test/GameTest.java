package Test;

public class GameTest {

    public void testPlayTurnValidWord() {
        // Arrange
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        Board board = new Board();
        DictionaryManager dictionaryManager = new DictionaryManager(new Dictionary());
        GameManager gameManager = new GameManager();
        Game game = new Game(players, board, dictionaryManager, gameManager);
        List<BoardPosition> positions = new ArrayList<>();
        positions.add(new BoardPosition(7, 7));
        positions.add(new BoardPosition(7, 8));
        positions.add(new BoardPosition(7, 9));

        // Act
        game.playTurn("CAT", positions);

        // Assert
        assertEquals(6, game.getCurrentPlayer().getScore());
        assertEquals(players.get(1), game.getCurrentPlayer());
        assertFalse(game.isGameOver());
    }

    public void testPlayTurnInvalidWord() {
        // Arrange
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        Board board = new Board();
        Dictionary dictionary = new Dictionary();
        dictionary.addWord("DOG");
        DictionaryManager dictionaryManager = new DictionaryManager(dictionary);
        GameManager gameManager = new GameManager();
        Game game = new Game(players, board, dictionaryManager, gameManager);
        List<BoardPosition> positions = new ArrayList<>();
        positions.add(new BoardPosition(7, 7));
        positions.add(new BoardPosition(7, 8));
        positions.add(new BoardPosition(7, 9));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            game.playTurn("CAT", positions);
        });
        assertEquals(0, game.getCurrentPlayer().getScore());
        assertEquals(players.get(0), game.getCurrentPlayer());
        assertFalse(game.isGameOver());
    }

    public void testPlayTurnInvalidPlacement() {
        // Arrange
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        Board board = new Board();
        DictionaryManager dictionaryManager = new DictionaryManager(new Dictionary());
        GameManager gameManager = new GameManager();
        Game game = new Game(players, board, dictionaryManager, gameManager);
        List<BoardPosition> positions = new ArrayList<>();
        positions.add(new BoardPosition(7, 7));
        positions.add(new BoardPosition(7, 8));
        positions.add(new BoardPosition(7, 10));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            game.playTurn("CAT", positions);
        });
        assertEquals(0, game.getCurrentPlayer().getScore());
        assertEquals(players.get(0), game.getCurrentPlayer());
        assertFalse(game.isGameOver());
    }

    public void testIsGameOver() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        Board board = new Board();
        DictionaryManager dictionaryManager = new DictionaryManager(new Dictionary());
        GameManager gameManager = new GameManager();
        Game game = new Game(players, board, dictionaryManager, gameManager);

        assertFalse(game.isGameOver());
        board.clear();
        assertTrue(game.isGameOver());
    }
}
