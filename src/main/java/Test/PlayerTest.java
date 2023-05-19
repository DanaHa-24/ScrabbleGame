package Test;

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