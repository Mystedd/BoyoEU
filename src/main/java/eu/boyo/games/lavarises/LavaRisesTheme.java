package eu.boyo.games.lavarises;

import org.bukkit.Material;

import java.util.HashMap;

public enum LavaRisesTheme {

    LUSH { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.MOSS_BLOCK);
        theme.put(Material.COBBLESTONE, Material.STONE);
        return theme;
    }},

    DRIPSTONE { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.DRIPSTONE_BLOCK);
        theme.put(Material.COBBLESTONE, Material.DRIPSTONE_BLOCK);
        return theme;
    }},

    SCULK { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.SCULK);
        theme.put(Material.COBBLESTONE, Material.SCULK);
        return theme;
    }},

    CAVE { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.STONE);
        theme.put(Material.COBBLESTONE, Material.DEEPSLATE);
        return theme;
    }},

    NETHER { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.NETHER_BRICKS);
        theme.put(Material.COBBLESTONE, Material.NETHERRACK);
        return theme;
    }},

    SOUL { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.SOUL_SOIL);
        theme.put(Material.COBBLESTONE, Material.BONE_BLOCK);
        return theme;
    }},

    CRIMSON { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.CRIMSON_HYPHAE);
        theme.put(Material.COBBLESTONE, Material.NETHER_WART_BLOCK);
        return theme;
    }},

    WARPED { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.WARPED_HYPHAE);
        theme.put(Material.COBBLESTONE, Material.WARPED_WART_BLOCK);
        return theme;
    }},

    BASALT { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.BASALT);
        theme.put(Material.COBBLESTONE, Material.NETHERRACK);
        return theme;
    }},

    PLAINS { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.GRASS_BLOCK);
        theme.put(Material.COBBLESTONE, Material.DIRT);
        return theme;
    }},

    MUSHROOM { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.RED_MUSHROOM_BLOCK);
        theme.put(Material.COBBLESTONE, Material.MUSHROOM_STEM);
        return theme;
    }},

    BADLANDS { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.ORANGE_TERRACOTTA);
        theme.put(Material.COBBLESTONE, Material.YELLOW_TERRACOTTA);
        return theme;
    }},

    END { public HashMap<Material, Material> getTheme() {
        HashMap<Material, Material> theme = new HashMap<>();
        theme.put(Material.GRASS_BLOCK, Material.END_STONE_BRICKS);
        theme.put(Material.COBBLESTONE, Material.END_STONE);
        return theme;
    }};

    public HashMap<Material, Material> getTheme() {
        return new HashMap<>();
    }
}
