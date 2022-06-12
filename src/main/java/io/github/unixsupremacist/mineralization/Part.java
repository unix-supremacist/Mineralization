package io.github.unixsupremacist.mineralization;

public class Part {
    public String name;
    public String textureLocation;
    public int tier = 0;

    public Part(String name, String textureLocation){
        this.name = name;
        this.textureLocation = textureLocation;
    }

    public Part(String name){
        this.name = name;
        this.textureLocation = Mineralization.MODID+":item/part/"+name;
    }

    public Part(String name, String textureLocation, int tier){
        this.name = name;
        this.textureLocation = textureLocation;
        this.tier = tier;
    }

    public Part(String name, int tier){
        this.name = name;
        this.textureLocation = Mineralization.MODID+":item/part/"+name;
        this.tier = tier;
    }
}
