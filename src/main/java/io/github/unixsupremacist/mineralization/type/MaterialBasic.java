package io.github.unixsupremacist.mineralization.type;

import java.util.HashSet;
import java.util.List;

public class MaterialBasic {
    String name;
    int color;
    boolean metallic;
    boolean ore;
    int tier;
    HashSet tags = new HashSet<>();
    public Parts parts;

    public MaterialBasic(String name, int color, boolean metallic, boolean ore, int tier, Parts parts, String... tags){
        this.name = name;
        this.color = color;
        this.metallic = metallic;
        this.ore = ore;
        this.tier = tier;
        this.parts = parts;
        this.tags.addAll(List.of(tags));
    }

    public String getName(){
        return name;
    }

    public int getColor(){
        return color;
    }

    public boolean isMetallic(){
        return metallic;
    }

    public boolean isOre(){
        return ore;
    }

    public int getTier(){
        return tier;
    }

    public HashSet getTags(){
        return tags;
    }
}
