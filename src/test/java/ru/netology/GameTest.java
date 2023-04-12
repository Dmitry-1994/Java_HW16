package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameTest {
    Game game = new Game();
    Player player_1 = new Player(111, "Dima", 100);
    Player player_2 = new Player(222, "Kiril", 50);
    Player player_3 = new Player(333, "Igor", 70);
    Player player_4 = new Player(444, "Oleg", 70);
    Player player_5 = new Player(444, "Oleg", 70);

    @Test
    public void mustBeRegisteredNull() {
        HashMap<String, Player> expected = new HashMap<>();
        HashMap<String, Player> actual = game.findRegistered();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mustBeRegisteredOne() {
        game.register(player_1);

        HashMap<String, Player> expected = new HashMap<>();
        expected.put(player_1.getName(), player_1);
        HashMap<String, Player> actual = game.findRegistered();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mustBeRegisteredMore() {
        game.register(player_1);
        game.register(player_2);
        game.register(player_3);
        game.register(player_4);

        HashMap<String, Player> expected = new HashMap<>();
        expected.put(player_1.getName(), player_1);
        expected.put(player_2.getName(), player_2);
        expected.put(player_3.getName(), player_3);
        expected.put(player_4.getName(), player_4);
        HashMap<String, Player> actual = game.findRegistered();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void mustBeRegisteredNegative() {
        game.register(player_4);
        game.register(player_5);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.register(player_5);
        });
    }

    @Test
    public void foundByNameNull() {
        game.register(player_1);

        Player expected = null;
        Player actual = game.infoByName("Oleg");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void foundByNameOne() {
        game.register(player_1);
        game.register(player_2);
        game.register(player_3);
        game.register(player_4);

        Player expected = player_3;
        Player actual = game.infoByName("Igor");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void roundWonFirst() {
        game.register(player_1);
        game.register(player_2);

        int expected = 1;
        int actual = game.round("Dima", "Kiril");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void roundWonSecond() {
        game.register(player_1);
        game.register(player_2);
        game.register(player_3);

        int expected = 2;
        int actual = game.round("Kiril", "Igor");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void roundWonDraw() {
        game.register(player_2);
        game.register(player_3);
        game.register(player_4);

        int expected = 0;
        int actual = game.round("Igor", "Oleg");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void roundName_1Negative() {
        game.register(player_1);
        game.register(player_2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Igor", "Kiril");
        });
    }

    @Test
    public void roundName_2Negative() {
        game.register(player_1);
        game.register(player_2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Dima", "Igor");
        });
    }

    @Test
    public void roundNameBothNegative() {
        game.register(player_1);
        game.register(player_2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Igor", "Igor");
        });
    }
}
