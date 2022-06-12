package io.github.unixsupremacist.mineralization;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.HashSet;
import java.util.function.Predicate;

import static io.github.unixsupremacist.mineralization.MineralizationRegistry.registerOreGen;

public class OreGens {
    public static Predicate<BiomeSelectionContext> OVERWORLD = BiomeSelectors.foundInOverworld();
    public static Predicate<BiomeSelectionContext> NETHER = BiomeSelectors.foundInTheNether();
    public static Predicate<BiomeSelectionContext> END = BiomeSelectors.foundInTheEnd();
    public static Predicate<BiomeSelectionContext> WARM = BiomeSelectors.tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS); //warm_ocean, desert, mangrove_swamp, #is_jungle, #is_savanna, #is_badlands, #is_nether
    public static Predicate<BiomeSelectionContext> COLD = BiomeSelectors.tag(BiomeTags.SPAWNS_COLD_VARIANT_FROGS); //snowy_taiga, snowy_plains, ice_spikes, grove, frozen_peaks, jagged_peaks, snowy_slopes, frozen_ocean, deep_frozen_ocean, frozen_river, snowy_beach, deep_dark, #is_end
    public static Predicate<BiomeSelectionContext> MOUNTAIN = BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN);//meadow, frozen_peaks, jagged_peaks, stony_peaks, snowy_slopes
    public static Predicate<BiomeSelectionContext> JUNGLE = BiomeSelectors.tag(BiomeTags.IS_JUNGLE);//bamboo_jungle, jungle, sparse_jungle
    public static Predicate<BiomeSelectionContext> MUSHROOM = BiomeSelectors.includeByKey(BiomeKeys.MUSHROOM_FIELDS);
    public static Predicate<BiomeSelectionContext> OCEAN = BiomeSelectors.tag(BiomeTags.IS_OCEAN); //#is_deep_ocean, frozen_ocean, ocean, cold_ocean, lukewarm_ocean, warm_ocean
    public static Predicate<BiomeSelectionContext> PLAINS = BiomeSelectors.tag(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE);
    public static HashSet<OreType> oreTypes = MineralizationRegistry.oreTypes;

    public static void registerOreGens(String MODID){
        registerOreGen(MODID, new OreGen("garnierite", NETHER.or(MOUNTAIN).or(PLAINS), -12, 75, 20, 8, 0.0f, getOreType("garnierite")));
        registerOreGen(MODID, new OreGen("cassiterite", NETHER.or(COLD), 30, 120, 20, 60, 0.0f, getOreType("cassiterite")));
        registerOreGen(MODID, new OreGen("rutile", OCEAN.or(END), -32, 48, 8, 3, 0.3f, getOreType("rutile")));
        registerOreGen(MODID, new OreGen("uranium", NETHER, 0, 128, 7, 3, 0.2f, getOreType("uranium")));
        registerOreGen(MODID, new OreGen("bauxite", PLAINS.or(NETHER), 0, 128, 3, 20, 0.1f, getOreType("bauxite")));
        registerOreGen(MODID, new OreGen("sheldonite", MUSHROOM.or(END), -32, 80, 8, 4, 0.1f, getOreType("sheldonite")));
        registerOreGen(MODID, new OreGen("chromite", WARM.or(OCEAN), -16, 64, 3, 20, 0.1f, getOreType("chromite")));
        registerOreGen(MODID, new OreGen("pyrite", NETHER, 16, 96, 9, 10, 0.1f, getOreType("pyrite")));
        //registerOreGen(MODID, new OreGen("sodalite", END, 0, 64, 9, 30, 0.1f, getOreType("sodalite")));
        registerOreGen(MODID, new OreGen("olivine_magnesite", END.or(NETHER), 0, 64, 10, 15, 0.1f, getOreType("olivine"), getOreType("olivine"), getOreType("magnesite")));
        registerOreGen(MODID, new OreGen("ruby", WARM, 8, 48, 8, 6, 0.1f, getOreType("ruby")));
        registerOreGen(MODID, new OreGen("sapphire", OCEAN.or(END), 10, 60, 8, 6, 0.1f, getOreType("sapphire")));
        registerOreGen(MODID, new OreGen("nikolite", OCEAN.or(PLAINS).or(END), -64, 64, 4, 20, 0.1f, getOreType("nikolite")));
        registerOreGen(MODID, new OreGen("galena", NETHER.or(OVERWORLD), 0, 70, 10, 30, 0.1f, getOreType("galena")));
        registerOreGen(MODID, new OreGen("lead", NETHER.or(OVERWORLD), 0, 70, 20, 5, 0.1f, getOreType("lead"), getOreType("lead"), getOreType("lead"), getOreType("silver")));
        registerOreGen(MODID, new OreGen("silver", NETHER.or(OVERWORLD), 0, 70, 20, 5, 0.1f, getOreType("silver"), getOreType("silver"), getOreType("silver"), getOreType("lead")));
        registerOreGen(MODID, new OreGen("tungstate", END, 8, 72, 2, 2, 0.1f, getOreType("tungstate")));
        registerOreGen(MODID, new OreGen("tetrahedrite", JUNGLE.or(MUSHROOM).or(MOUNTAIN).or(NETHER).or(END), 32, 96, 7, 20, 0.1f, getOreType("tetrahedrite")));
        registerOreGen(MODID, new OreGen("gold", WARM, 0, 70, 10, 30, 0.1f, getOreType("gold")));
    }

    public static OreType getOreType(String name){
        return MineralizationRegistry.getOreType(name);
    }
}
