package io.github.unixsupremacist.mineralization.type;

import net.minecraft.block.Block;

import java.util.HashSet;
import java.util.List;

public class StoneType {
    public String name;
    public Block block;
    public String textureLocation;
    public HashSet tags = new HashSet<>();

    public StoneType(String name, String textureLocation, Block block, String... tags){
        this.name = name;
        this.block = block;
        this.textureLocation = textureLocation;
        this.tags.addAll(List.of(tags));
    }

    public StoneType(Block block, String... tags){
        this.name = block.getTranslationKey().replace("block.minecraft.", "");
        this.block = block;
        this.textureLocation = "minecraft:block/"+block.getTranslationKey().replace("block.minecraft.", "");
        this.tags.addAll(List.of(tags));
    }

    public StoneType(String name, Block block, String... tags){
        this.name = name;
        this.block = block;
        this.textureLocation = "minecraft:block/"+name;
        this.tags.addAll(List.of(tags));
    }
}
