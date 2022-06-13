package io.github.unixsupremacist.mineralization;

public class Parts {
    //public static Part ingot, plate;

    public static void registerParts(){
        //MineralizationRegistry.registerPart(new Part("bolt", 2));
        //MineralizationRegistry.registerPart(new Part("curved_plate", 2));
        //MineralizationRegistry.registerPart(new Part("ring", 2));
        //MineralizationRegistry.registerPart(new Part("hot_ingot", 4));
        MineralizationRegistry.registerPart(new Part("rod", 2));
        MineralizationRegistry.registerPart(new Part("hull", 2));
        //MineralizationRegistry.registerPart(new Part("wire", 3));
        MineralizationRegistry.registerPart(new Part("gear", 2));
        //MineralizationRegistry.registerPart(new Part("raw_ore", 2));
        MineralizationRegistry.registerPart(new Part("plate", 2));
        MineralizationRegistry.registerPart(new Part("raw_material", 0));
        //MineralizationRegistry.registerPart(new Part("nugget", 1));
        MineralizationRegistry.registerPart(new Part("ingot", 1));
        //.registerPart(new Part("tiny_dust", 0));
        //MineralizationRegistry.registerPart(new Part("dust", 0));
    }
}
