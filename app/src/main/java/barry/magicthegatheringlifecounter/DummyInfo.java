package barry.magicthegatheringlifecounter;

/**
 * Created by 20074828 on 16/03/2018.
 */

public abstract class DummyInfo {
    // created player array

    public static Player[] players = new Player[]{
        new Player("Barry",13, 10, 3, 30),
                new Player("Eoin",9, 7, 2, 21),
                new Player("Jack",12, 7, 5, 21),
                new Player("Kate",6, 3, 3, 9),
                new Player("Kevin",2, 2, 0, 6)
    };

    public static Player[] playersName = new Player[]{
            new Player("john"),
            new Player("Eoin"),
            new Player("Jack"),
            new Player("Kate"),
            new Player("Kevin")
    };

    public static Player[] playersLife = new Player[]{
            new Player(20),
            new Player(20),
            new Player(20),
            new Player(20),
            new Player(20)
    };
}