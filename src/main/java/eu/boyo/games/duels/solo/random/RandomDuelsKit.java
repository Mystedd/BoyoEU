package eu.boyo.games.duels.solo.random;

import eu.boyo.BoyoEU;
import eu.boyo.games.duels.DuelsKit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;

public enum RandomDuelsKit implements DuelsKit {

    RANDOM_ITEM("Random Item", true) {

        public void giveItems(Player player1, Player player2) {
            final int minPower = 2000;
            final int maxPower = 3000;
            final int tolerance = 20;
            int targetPower = (int) (Math.random() * (maxPower-minPower) + minPower);
            player1.getInventory().clear();
            giveItems(player1, targetPower, tolerance);
            player2.getInventory().clear();
            giveItems(player2, targetPower, tolerance);
        }

        public void giveItems(Player player, int targetPower, int tolerance) {
            PlayerInventory inventory = player.getInventory();
            ArrayList<ItemStack> items = generateItems(targetPower, tolerance);
            for (ItemStack item : items) {
                inventory.addItem(item);
            }
        }

        private ArrayList<ItemStack> generateItems(int targetPower, int tolerance) {
            ArrayList<ItemStack> items = new ArrayList<>();
            int power = 0;
            boolean done = false;
            for (int x=0; x<10000; x++) {
                if (items.size() > 36) {
                    int oldPower = removeWeakestStack(items);
                    power -= oldPower;
                    continue;
                }
                if (power < (targetPower - tolerance)) {
                    int newPower = addRandomStack(items);
                    power += newPower;
                    continue;
                }
                if (power > (targetPower + tolerance)) {
                    int oldPower = removeWeakestItem(items);
                    power -= oldPower;
                    continue;
                }
                done = true;
                Bukkit.broadcastMessage("Items generated in "+x+" adjustments");
                Bukkit.broadcastMessage("Power is "+power);
                break;
            }
            if (!done) Bukkit.broadcastMessage("Items took too long to generate");
            return items;
        }

        private int getItemPower(Material material) {
            return powerValues.get(material);
        }

        private int getStackPower(ItemStack stack) {
            int power = getItemPower(stack.getType());
            power *= stack.getAmount();
            return power;
        }

        private int removeWeakestItem(ArrayList<ItemStack> stacks) {
            int lowestPower = Integer.MAX_VALUE;
            ItemStack weakestItem = null;
            for (ItemStack stack : stacks) {
                int power = getItemPower(stack.getType());
                if (power < lowestPower) {
                    lowestPower = power;
                    weakestItem = stack;
                }
            }
            if (weakestItem.getAmount() > 1) {
                weakestItem.setAmount(weakestItem.getAmount() - 1);
            }
            else {
                stacks.remove(weakestItem);
            }
            return lowestPower;
        }

        private int removeWeakestStack(ArrayList<ItemStack> stacks) {
            int lowestPower = Integer.MAX_VALUE;
            ItemStack weakestStack = null;
            for (ItemStack stack : stacks) {
                int power = getStackPower(stack);
                if (power < lowestPower) {
                    lowestPower = power;
                    weakestStack = stack;
                }
            }
            stacks.remove(weakestStack);
            return lowestPower;
        }

        private int addRandomStack(ArrayList<ItemStack> stacks) {
            int n = (int) (Math.random() * validMaterials.size());
            Material material = validMaterials.get(n);
            byte amount = (byte) (Math.random() * material.getMaxStackSize() + 1);
            ItemStack stack = new ItemStack(material, amount);
            stacks.add(stack);
            return getStackPower(stack);
        }
    };

    final boolean hasHunger;
    final String name;
    private static final ArrayList<Material> validMaterials = new ArrayList<>();
    private static final HashMap<Material, Integer> powerValues = new HashMap<>();

    public HashMap<Integer, ItemStack> getItems() {
        return new HashMap<>();
    }

    public boolean getHunger() {
        return hasHunger;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return name + " duels";
    }

    public void giveItems(Player player) {

    }

    public void giveInventoryItems(Player player) {

    }

    static {
        // generate valid materials
        for (Material material : Material.values()) {
            if (material.isItem()) validMaterials.add(material);
        }
        // read power values
        FileConfiguration config = BoyoEU.plugin.getDuelsItemPowersConfig();
        for (Material material : validMaterials) {
            String namespace = material.name();
            namespace = namespace.replaceAll("^minecraft:", "");
            namespace = namespace.toLowerCase();
            int power = config.getInt(namespace);
            if (power == 0) power = 1;
            powerValues.put(material, power);
        }
    }

    RandomDuelsKit(String newName, boolean hunger) {
        name = newName;
        hasHunger = hunger;
    }
}
