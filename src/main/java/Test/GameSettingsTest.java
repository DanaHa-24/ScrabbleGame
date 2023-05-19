package Test

public class GameSettingsTest {

    public void testSetBoardSize() {
        GameSettings gameSettings = new GameSettings();
        gameSettings.setBoardSize(15);
        assertEquals(15, gameSettings.getBoardSize());
    }

    public void testSetMaxPlayers() {
        GameSettings gameSettings = new GameSettings();
        gameSettings.setMaxPlayers(4);
        assertEquals(4, gameSettings.getMaxPlayers());
    }
}