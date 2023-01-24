package eu.boyo.queues;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public abstract class Queue {

    String name;
    int capacity;
    boolean isFull = false;
    ArrayList<Player> players = new ArrayList<Player>();

    public Queue(String newName) {
        name = newName;
    }

    public Queue(String newName, int newCapacity) {
        name = newName;
        capacity = newCapacity;
    }

    public String getName() {
        return name;
    }

    public abstract void queueFull();

    public void resetQueue() {
        for (Player player : players) players.remove(player);
        isFull = false;
    }

    public void addPlayer(Player newPlayer) {
        if (!isFull) {
            players.add(newPlayer);
        }
        if (players.size() >= capacity) {
            isFull = true;
            queueFull();
        }
    }

    public void removePlayer(Player oldPlayer) {
        players.remove(oldPlayer);
        isFull = players.size() >= capacity;
    }

    public boolean containsPlayer(Player player) {
        return players.contains(player);
    }
}
