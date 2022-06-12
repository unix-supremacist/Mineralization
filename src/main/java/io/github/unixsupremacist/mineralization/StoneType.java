package io.github.unixsupremacist.mineralization;

import net.minecraft.block.Block;

public class StoneType {
    public String name;
    public Block block;
    public String textureLocation;

    public StoneType(String name, Block block, String textureLocation){
        this.name = name;
        this.block = block;
        this.textureLocation = textureLocation;
    }

    public StoneType(String name, Block block){
        this.name = name;
        this.block = block;
        this.textureLocation = "minecraft:block/"+name;
    }
}
