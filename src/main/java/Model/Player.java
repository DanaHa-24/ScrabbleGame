package Model;
import Backend;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.Map;
public class Player {
    private int score;
    private String name;
    private Image icon;
    private Tile[] rack;
    private boolean isTurn;
    private boolean isRemote;
    private String ip;
    private int port;
    private static final String ICONS_PATH = "main/recources/GameIcons/";
    private static final String[] ICON_NAMES = {"icon1.png", "icon2.png", "icon3.png", "icon4.png", "icon5.png", "icon6.png", "icon7.png", "icon8.png",
            "icon9.png", "icon10.png", "icon11.png", "icon12.png", "icon13.png", "icon14.png", "icon15.png", "icon16.png"};
    private static final Map<String, Image> ICONS_MAP = new HashMap<>();
    static {
        for (String iconName : ICON_NAMES) {
            String iconPath = ICONS_PATH + iconName;
            Image iconImage = new ImageIcon(iconPath).getImage();
            ICONS_MAP.put(iconName, iconImage);
        }
    }
    public Player(String name, String iconName, String ip = null, int port = 0) {
        this.name = name;
        this.icon = ICONS_MAP.get(iconName);
        this.score = 0;
        this.rack = new Tile[7];
        this.ip = ip;
        this.port = port;
        this.isTurn = false;
        this.isRemote = (ip != null && port != 0);
    }
    public int getScore() {
        return score;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(String iconName) {
        this.icon = ICONS_MAP.get(iconName);;
    }

    public String getName() {
        return name;
    }

    public Image getScore() {
        return score;
    }

    public void addToScore(int points) {
        this.score += points;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public boolean isRemote() {
        return isRemote;
    }

    public void setRemote(boolean isRemote) {
        this.isRemote = isRemote;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Tile[] getRack() {
        return rack;
    }

    public void setRack(Tile[] rack) {
        System.arraycopy(rack, 0, this.rack, 0, 7);
    }

    public Tile setTile(int index, Tile tile)
    {
        rack[index] = tile;
    }

    public boolean hasTiles() {
        for (Tile t : rack) {
            if (t != null)
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Player p = new Player("Omer", "icon1");

        if(p.getName() != "Omer")
            system.out.println("problem with constructor or getName()");

        p.setName("Or");
        if(p.getName() != "Or")
            system.out.println("problem with setName()");

        if(p.getScore() != 0)
            system.out.println("problem with constructor or getName()");

        p.addToScore(10);
        if(p.getScore() != 10)
            system.out.println("problem with addToScore()");

        if(p.isTurn() != false)
            system.out.println("problem with constructor or isTurn()");

        p.setTurn(true);
        if(p.isTurn() != true)
            system.out.println("problem with setTurn()");

        if(p.isRemote() != false)
            system.out.println("problem with constructor or isRemote()");

        p.setRemote(true);
        if(p.isRemote() != true)
            system.out.println("problem with setRemote()");

        if(p.getIp() != false)
            system.out.println("problem with constructor or getIp()");

        p.setIp(true);
        if(p.getIp() != true)
            system.out.println("problem with setIp()");

        if(p.getPort() != 0)
            system.out.println("problem with constructor or getPort()");

        p.setPort(80);
        if(p.getPort() != 80)
            system.out.println("problem with setPort()");

        if(p.getRack() != null)
            system.out.println("problem with constructor or getRack()");

        Tile[] r = new Tile[](new Tile("A", 1), null, new Tile("B", 3), null, null, null, null);
        p.setRack(r);
        if(p.getRack() == r)
            system.out.println("problem with arraycopy in setRack()");
        if(p.getRack()[0].letter != "A" || p.getRack()[0].score != 1 || p.getRack()[2].letter != "B" || p.getRack()[2].score != 3)
            system.out.println("problem with setRack()");

        if(p.hasTiles() != true)
            system.out.println("problem with hasTiles()");

        p.setTile(0, null);
        if(p.getRack()[0] != null)
            system.out.println("problem with setTile()");

        p.setTile(2, new Tile("C", 3));
        if(p.getRack()[2].letter != "c" || p.getRack()[2].score != 3)
            system.out.println("problem with setRack()");

        p.setTile(2, null);
        if(p.hasTiles() != false)
            system.out.println("problem with hasTiles() with empty rack");

        System.out.println("done");
    }
}