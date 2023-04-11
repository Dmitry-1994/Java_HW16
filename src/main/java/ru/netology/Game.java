package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class Game {
    PlayerStrengthComparator strengthComparator = new PlayerStrengthComparator();
    List<Player> playerRegistered = new ArrayList<>();

    //Метод для регистрации игроков в турнире
    public void register(Player player) {
        int index = 0;
        for (Player players : playerRegistered) {
            if (players.getId() == player.getId()) {
                index++;
                break;
            }
        }
        if (index == 0) {
            playerRegistered.add(player);
        } else {
            throw new NotRegisteredException(
                    "Данный игрок уже зарегистрирован");
        }
    }

    public List<Player> findRegistered() {
        return playerRegistered;
    }

    //Метод соревнования между игроками
    public int round(String playerName1, String playerName2) {
        Player playerFirst = null;
        Player playerSecond = null;

        for (Player player : playerRegistered) {
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
