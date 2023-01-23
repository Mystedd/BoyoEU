package eu.boyo.queues;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.entity.Player;

public class Queues {

    private static HashMap<String, Queue> queues = new HashMap<String, Queue>();
    private static Set<String> queueNames;

    static {
        queues.put("lavarises", new LavaRisesQueue("Lava Rises"));
        queueNames = queues.keySet();
    }

    private Queues() {}

    public static HashMap<String, Queue> getQueues() {
        return queues;
    }

    public static Set<String> getQueueNames() {
        return queueNames;
    }

    public static Queue getQueue(String queueName) {
        return queues.get(queueName);
    }

    public static Queue getPlayerQueue(Player player) {
        for (String name : queueNames) {
            Queue queue = queues.get(name);
            if (queue.containsPlayer(player)) return queue;
        }
        return null;
    }

    public static boolean removePlayer(Player player) {
        Queue queue = getPlayerQueue(player);
        if (queue == null) return false;
        queue.removePlayer(player);
        return true;
    }
}
