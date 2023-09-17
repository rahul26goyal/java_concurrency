package com.rahulg.collections;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Java Comparable example
class Player implements Comparable<Player> {
    private int ranking;
    private String name;
    private int age;

    Player(String name, int rank, int age) {
        this.name = name;
        this.ranking = rank;
        this.age = age;
    }

    public  String toString() {
        return String.format("(Name: %s, RANK: %d, AGE: %d)", name, ranking, age);
    }

    public int getAge(){
        return age;
    }


    @Override
    public int compareTo(Player o) {
        if(this.ranking > o.ranking) {
            return 1;
        }
        else  if(this.ranking < o.ranking) {
            return -1;
        }
        return 0;
    }
}

// Java Comparator example
class PlayerAgeBasedComparator implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        if(p1.getAge() > p2.getAge()) {
            return 1;
        } else if(p1.getAge() < p2.getAge()) {
            return -1;
        }
        return 0;
    }
}

public class JavaComparableExample {

    @Test
    public void testRankBasedComparableDefault() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("a", 10, 12));
        players.add(new Player("b", 1, 10));
        players.add(new Player("c", 100, 8));
        players.add(new Player("d", 50, 5));

        System.out.println("Before sorting players = " + players);

        Collections.sort(players);
        System.out.println("After sorting players = " + players);
    }

    @Test
    public void testAgeBasedComparator() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("a", 10, 12));
        players.add(new Player("b", 1, 10));
        players.add(new Player("c", 100, 8));
        players.add(new Player("d", 50, 5));

        System.out.println("Before sorting players = " + players);

        PlayerAgeBasedComparator ageBasedComparator = new PlayerAgeBasedComparator();
        Collections.sort(players, ageBasedComparator);
        System.out.println("After Age based sorting players = " + players);
    }

    @Test
    public void test_soringUsingLambdaExpression(){

        List<Player> players = new ArrayList<>();
        players.add(new Player("a", 10, 12));
        players.add(new Player("b", 1, 10));
        players.add(new Player("c", 100, 8));
        players.add(new Player("d", 50, 5));

        System.out.println("Before sorting players = " + players);

        Comparator<Player> lambdaComparator = (Player p1, Player p2) -> {
            return Integer.compare(p1.getAge(), p2.getAge());
        };

        Collections.sort(players, lambdaComparator);
        System.out.println("After Age based sorting players = " + players);

    }

    @Test
    public void test_soringUsingComparingConstruct(){

        List<Player> players = new ArrayList<>();
        players.add(new Player("a", 10, 12));
        players.add(new Player("b", 1, 10));
        players.add(new Player("c", 100, 8));
        players.add(new Player("d", 50, 5));
        Comparator<Player> comparingByAge = Comparator.
            comparing(Player::getAge);

        // Collections.sort(players, comparingByAge);
        // without modifying the original list
        List<Player> sortedPlayers = new ArrayList<>(players);
        players.add(new Player("e", 50, 0));
        sortedPlayers.sort(comparingByAge);
        System.out.println("Before sorting players = " + players);
        System.out.println("After Age based sorting players = " + sortedPlayers);

    }

}
