package com.valiom.practice.model;

import org.bukkit.Material;

import java.util.List;

public class GameMode {
    private final String id;
    private final String displayName;
    private final Material icon;
    private final List<String> lore;

    public GameMode(String id, String displayName, Material icon, List<String> lore) {
        this.id = id;
        this.displayName = displayName;
        this.icon = icon;
        this.lore = lore;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getIcon() {
        return icon;
    }

    public List<String> getLore() {
        return lore;
    }
}
