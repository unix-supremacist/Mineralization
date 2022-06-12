package io.github.unixsupremacist.mineralization;

import net.minecraft.block.Blocks;

public class StoneTypes {
    public static void registerStoneTypes(){
        MineralizationRegistry.registerStoneType(new StoneType("stone", Blocks.STONE));
        MineralizationRegistry.registerStoneType(new StoneType("sandstone", Blocks.SANDSTONE));
        MineralizationRegistry.registerStoneType(new StoneType("deepslate", Blocks.DEEPSLATE));
        MineralizationRegistry.registerStoneType(new StoneType("netherrack", Blocks.NETHERRACK));
        MineralizationRegistry.registerStoneType(new StoneType("endstone", Blocks.END_STONE));
        MineralizationRegistry.registerStoneType(new StoneType("gravel", Blocks.GRAVEL));
        MineralizationRegistry.registerStoneType(new StoneType("tuff", Blocks.TUFF));
        MineralizationRegistry.registerStoneType(new StoneType("sand", Blocks.SAND));
        MineralizationRegistry.registerStoneType(new StoneType("granite", Blocks.GRANITE));
        MineralizationRegistry.registerStoneType(new StoneType("diorite", Blocks.DIORITE));
        MineralizationRegistry.registerStoneType(new StoneType("andesite", Blocks.ANDESITE));
        MineralizationRegistry.registerStoneType(new StoneType("basalt", Blocks.BASALT));
        MineralizationRegistry.registerStoneType(new StoneType("smooth_basalt", Blocks.SMOOTH_BASALT));
        MineralizationRegistry.registerStoneType(new StoneType("calcite", Blocks.CALCITE));
    }
}
