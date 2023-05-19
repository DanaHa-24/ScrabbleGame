package Test;

public class GameManagerTest {

    public void testStartGame() {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
        assertTrue(gameManager.isGameStarted());
    }

    public void testAddPlayer() {
        GameManager gameManager = new GameManager();
        Player player1 = new Player("Player1","icon1.png");
        Player player2 = new Player("Player2", "icon2.png");
        gameManager.addPlayer(player1);
        gameManager.addPlayer(player2);
        assertEquals(2, gameManager.getPlayers().size());
        assertTrue(gameManager.getPlayers().contains(player1));
        assertTrue(gameManager.getPlayers().contains(player2));
    }
}