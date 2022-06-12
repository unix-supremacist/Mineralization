package io.github.unixsupremacist.mineralization;

public class MaterialItem {
    String name;
    int color;
    boolean metallic;
    boolean ore;
    int tier;

    public MaterialItem(String name, int color, boolean metallic, boolean ore, int tier){
        this.name = name;
        this.color = color;
        this.metallic = metallic;
        this.ore = ore;
        this.tier = tier;
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
}
