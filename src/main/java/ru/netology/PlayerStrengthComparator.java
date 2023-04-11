package ru.netology;

import java.util.Comparator;

public class PlayerStrengthComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getStrength() > o2.getStrength()) {
            return 1;
        } else if (o2.getStrength() > o1.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }
}
