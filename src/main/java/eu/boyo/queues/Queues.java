package eu.boyo.queues;

import java.util.HashMap;
import java.util.Set;

import eu.boyo.games.lavarises.LavaRisesQueue;
import eu.boyo.games.tag.TagQueue;
import org.bukkit.entity.Player;

public class Queues {

    private static HashMap<String, Queue> queues = new HashMap<String, Queue>();
    private static Set<String> queueNames;

    static {
        queues.put("lavarises", new LavaRisesQueue("Lava Rises"));
        queues.put("tag", new TagQueue("Tag"));
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

    public static Queue removePlayer(Player player) {
        Queue queue = getPlayerQueue(player);
        if (queue == null) return null;
        queue.removePlayer(player);
        return queue;
    }
}
