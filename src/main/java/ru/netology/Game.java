package ru.netology;

import java.util.HashMap;

public class Game {
    PlayerStrengthComparator strengthComparator = new PlayerStrengthComparator();
    HashMap<String, Player> playerRegistered = new HashMap<>();

    //Метод для регистрации игроков в турнире
    public void register(Player player) {
        if (playerRegistered.containsValue(player)) {
            throw new NotRegisteredException("Данный игрок уже зарегистрирован");
        } else {
            playerRegistered.put(player.getName(), player);
        }

    }

    // Метод для поиска информации об игроке по его имени
    public Player infoByName (String name) {
        Player infoResult = null;
        for (String key : playerRegistered.keySet()) {
            Player players = playerRegistered.get(key);

            if (players.getName().equals(name)) {
                infoResult = players;
            }
        }
        return infoResult;
    }

    public HashMap<String, Player> findRegistered() {
        return playerRegistered;
    }

    //Метод соревнования между игроками
    public int round(String playerName1, String playerName2) {
        Player playerFirst = null;
        Player playerSecond = null;

        for (Player player : playerRegistered.values()) {
            if (player.getName().equals(playerName1)) {
                playerFirst = player;
            }
            if (player.getName().equals(playerName2)) {
                playerSecond = player;
            }
        }

        if ((playerFirst == null) | (playerSecond == null)) {
            if (playerFirst == null) {
                throw new NotRegisteredException(
                        "Игрок с именем " + playerName1 + " не зарегистрирован"
                );
            } else {
                throw new NotRegisteredException(
                        "Игрок с именем " + playerName2 + " не зарегистрирован"
                );
            }
        }

        return strengthComparator.compare(playerFirst, playerSecond);
    }
}
