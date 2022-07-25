package io.github.unixsupremacist.mineralization.type;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.HashMap;

public class OreType {
    public String name;
    public Item item;
    public int color;
    public HashMap<String, Block> oreBlocks = new HashMap<>();

    public OreType(String name, Item item, int color) {
        this.name = name;
        this.item = item;
        this.color = color;
    }
}