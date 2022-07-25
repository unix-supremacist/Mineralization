package io.github.unixsupremacist.mineralization;

import io.github.feltmc.feltapi.api.splash.SplashAPI;
import io.github.unixsupremacist.mineralization.type.*;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.HashSet;
import java.util.function.Predicate;

import static io.github.unixsupremacist.mineralization.MineralizationRegistry.registerOreGen;

public class MineralizationContent {
    public static MaterialBasic
            gold, copper, tetrahedrite, iron, pyrite, emerald, nickel, garnierite, invar, jade, jadeite, cobalt, cobaltite, monel, obsidian, flint, shulker, infinity, quartz, diamond, netherite, anthracite, coke, cinnabar;
    public static Block white_marble, black_marble, grey_marble, rose_marble, mariposite;
    public static Predicate<BiomeSelectionContext> OVERWORLD = BiomeSelectors.foundInOverworld();
    public static Predicate<BiomeSelectionContext> NETHER = BiomeSelectors.foundInTheNether();
    public static Predicate<BiomeSelectionContext> END = BiomeSelectors.foundInTheEnd();
    public static Predicate<BiomeSelectionContext> WARM = BiomeSelectors.categories(Biome.Category.DESERT).or(BiomeSelectors.categories(Biome.Category.MESA)).or(BiomeSelectors.tag(BiomeTags.IS_JUNGLE));//bamboo_jungle, jungle, sparse_jungle
    public static Predicate<BiomeSelectionContext> COLD = BiomeSelectors.categories(Biome.Category.ICY);
    public static Predicate<BiomeSelectionContext> MOUNTAIN = BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN);//meadow, frozen_peaks, jagged_peaks, stony_peaks, snowy_slopes
    public static Predicate<BiomeSelectionContext> MUSHROOM = BiomeSelectors.includeByKey(BiomeKeys.MUSHROOM_FIELDS);
    public static Predicate<BiomeSelectionContext> OCEAN = BiomeSelectors.tag(BiomeTags.IS_OCEAN); //#is_deep_ocean, frozen_ocean, ocean, cold_ocean, lukewarm_ocean, warm_ocean
    public static Predicate<BiomeSelectionContext> PLAINS = BiomeSelectors.tag(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE);
    public static HashSet<OreType> oreTypes = MineralizationRegistry.oreTypes;

    public static void registerBlocks(){

    }

    public static void registerStoneTypes(){
        Block[] earthStones = {
                Blocks.STONE, Blocks.SANDSTONE, Blocks.DEEPSLATE, Blocks.GRAVEL, Blocks.TUFF,
                Blocks.SAND, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.CALCITE
        };
        for (Block block : earthStones) MineralizationRegistry.registerStoneType(new StoneType(block, "earth"));
        Block[] netherStones = {Blocks.BLACKSTONE, Blocks.NETHERRACK, Blocks.GRAVEL, Blocks.SMOOTH_BASALT};
        for (Block block : netherStones) MineralizationRegistry.registerStoneType(new StoneType(block, "nether"));

        MineralizationRegistry.registerStoneType(new StoneType(Blocks.END_STONE, "end"));
        MineralizationRegistry.registerStoneType(new StoneType("basalt", "minecraft:block/basalt_top", Blocks.BASALT, "nether"));
        MineralizationRegistry.registerStoneType(new StoneType("white_marble", white_marble));
        MineralizationRegistry.registerStoneType(new StoneType("black_marble", black_marble));
        MineralizationRegistry.registerStoneType(new StoneType("grey_marble", grey_marble));
        MineralizationRegistry.registerStoneType(new StoneType("rose_marble", rose_marble));
        MineralizationRegistry.registerStoneType(new StoneType("mariposite", mariposite));
    }

    public static void registerMaterials(String MODID){
        Parts metal = new Parts("ingot", "dust", "nugget", "rod", "hull", "wafer", "coil", "raw_material", "crushed_ore","purified_ore","refined_ore","tiny_dust","impure_dust","purified_dust");
        Parts gem = new Parts("gem", "dust", "nugget", "crushed_ore", "purified_ore", "refined_ore", "tiny_dust", "impure_dust", "purified_dust");
        // testMaterial = MineralizationRegistry.registerMaterial(new MaterialAll("test", 0x000000, 15, new int[] {2, 5, 6, 2}, 0.0f, 0.0f, 2.0f, 2, 14, true, true, 1), MODID);
        // test2Material = MineralizationRegistry.registerMaterial(new MaterialAll("test2", 0x000000, 15, new int[] {2, 5, 6, 2}, 2.0f, 2, 14, true, true, 1), MODID);
        // test4Material = MineralizationRegistry.registerMaterial(new MaterialItem("test4", 0x000000, true, false, 0), MODID);

        gold = MineralizationRegistry.registerMaterial(new MaterialBasic("gold", 0xe7ca53, true, true, 3, metal), MODID);
        copper = MineralizationRegistry.registerMaterial(new MaterialBasic("copper", 0xc78621, true, true, 3, new Parts()), MODID);
        tetrahedrite = MineralizationRegistry.registerMaterial(new MaterialBasic("tetrahedrite", 0xc78621, true, true, 0, new Parts()), MODID);
        iron = MineralizationRegistry.registerMaterial(new MaterialBasic("iron", 0xE0E0E0, true, true, 2, new Parts()), MODID);
        pyrite = MineralizationRegistry.registerMaterial(new MaterialBasic("pyrite", 0xE0E0E0, true, true, 0, new Parts(), "nether"), MODID);
        emerald = MineralizationRegistry.registerMaterial(new MaterialBasic("emerald", 0x08dd7b, false, true, 0, new Parts()), MODID);
        nickel = MineralizationRegistry.registerMaterial(new MaterialBasic("nickel", 0x464D19, true, true, 2, new Parts()), MODID);
        garnierite = MineralizationRegistry.registerMaterial(new MaterialBasic("garnierite", 0x464D19, true, true, 0, new Parts(), "earth", "nether"), MODID);
        invar = MineralizationRegistry.registerMaterial(new MaterialBasic("invar", 0xcebe7c, true, false, 2, new Parts()), MODID);
        jade = MineralizationRegistry.registerMaterial(new MaterialAll("jade", 0x08dd7b, false, true, 1, new Parts(), 15, new int[] {3, 7, 7, 3}, 2.0f, 2, 14, 3, 0), MODID);
        jadeite = MineralizationRegistry.registerMaterial(new MaterialBasic("jadeite", 0x08dd7b, false, true, 1, new Parts()), MODID);
        cobalt = MineralizationRegistry.registerMaterial(new MaterialBasic("cobalt", 0x505080, true, false, 4, new Parts()), MODID);
        cobaltite = MineralizationRegistry.registerMaterial(new MaterialBasic("cobaltite", 0x505080, true, true, 4, new Parts()), MODID);
        monel = MineralizationRegistry.registerMaterial(new MaterialBasic("monel", 0xc78621, true, false, 2, new Parts()), MODID);
    }

    public static void registerMaterialOverides (){
        MineralizationRegistry.items.put("gem_emerald", "minecraft:emerald");
        MineralizationRegistry.items.put("raw_material_iron", "minecraft:raw_iron");
        MineralizationRegistry.items.put("ingot_iron", "minecraft:ingot_iron");
        MineralizationRegistry.items.put("nugget_iron", "minecraft:nugget_iron");
        MineralizationRegistry.blocks.put("stone_iron", "minecraft:iron_ore");
        MineralizationRegistry.blocks.put("deepslate_iron", "minecraft:deepslate_iron_ore");
        MineralizationRegistry.items.put("raw_material_gold", "minecraft:raw_gold");
        MineralizationRegistry.items.put("ingot_gold", "minecraft:ingot_gold");
        MineralizationRegistry.items.put("nugget_gold", "minecraft:nugget_gold");
        MineralizationRegistry.blocks.put("stone_gold", "minecraft:gold_ore");
        MineralizationRegistry.blocks.put("deepslate_gold", "minecraft:deepslate_gold_ore");
        MineralizationRegistry.blocks.put("netherrack_gold", "minecraft:nether_gold_ore");
        MineralizationRegistry.blocks.put("blackstone_gold", "minecraft:glided_blackstone");
        MineralizationRegistry.items.put("raw_material_copper", "minecraft:raw_copper");
        MineralizationRegistry.items.put("ingot_copper", "minecraft:ingot_copper");
        MineralizationRegistry.blocks.put("stone_copper", "minecraft:copper_ore");
        MineralizationRegistry.blocks.put("deepslate_copper", "minecraft:deepslate_copper_ore");
        MineralizationRegistry.blocks.put("stone_emerald", "minecraft:emerald_ore");
        MineralizationRegistry.blocks.put("deepslate_emerald", "minecraft:deepslate_emerald_ore");
    }

    public static void registerOreGens(String MODID){
        registerOreGen(MODID, new OreGen("garnierite_cobaltite", NETHER.or(MOUNTAIN).or(PLAINS), -12, 75, 10, 4, 0.0f, getOreType("garnierite"), getOreType("garnierite"), getOreType("garnierite"), getOreType("garnierite"), getOreType("cobaltite")));
        registerOreGen(MODID, new OreGen("diamond", MUSHROOM, -32, 80, 8, 4, 0.1f, getOreType("diamond")));
        registerOreGen(MODID, new OreGen("pyrite", NETHER, 16, 96, 9, 10, 0.1f, getOreType("pyrite")));
        registerOreGen(MODID, new OreGen("emerald_jadeite", MOUNTAIN, 10, 60, 8, 2, 0.1f, getOreType("emerald"), getOreType("emerald"), getOreType("jadeite")));
        registerOreGen(MODID, new OreGen("coal_anthracite", OVERWORLD, -64, 64, 4, 20, 0.1f, getOreType("coal"), getOreType("coal"), getOreType("coal"), getOreType("coal"), getOreType("anthracite")));
        registerOreGen(MODID, new OreGen("redstone", OVERWORLD, -64, 64, 4, 20, 0.1f, getOreType("redstone")));
        registerOreGen(MODID, new OreGen("cinnabar", NETHER, -64, 64, 4, 20, 0.1f, getOreType("cinnabar")));
        registerOreGen(MODID, new OreGen("quartz", NETHER, 0, 70, 10, 30, 0.1f, getOreType("quartz")));
        registerOreGen(MODID, new OreGen("copper_gold", OVERWORLD, 0, 70, 20, 5, 0.1f, getOreType("copper"), getOreType("copper"), getOreType("copper"), getOreType("copper"), getOreType("gold")));
        registerOreGen(MODID, new OreGen("iron", OVERWORLD.or(END), 0, 70, 20, 5, 0.1f, getOreType("iron")));
        registerOreGen(MODID, new OreGen("tetrahedrite", WARM.or(MUSHROOM).or(MOUNTAIN).or(NETHER).or(COLD).or(END), 32, 96, 7, 20, 0.1f, getOreType("tetrahedrite")));
        registerOreGen(MODID, new OreGen("gold_copper", WARM.or(PLAINS).or(NETHER), 0, 70, 10, 30, 0.1f, getOreType("gold"), getOreType("gold"), getOreType("gold"), getOreType("copper")));
    }

    public static void registerSplashes(){
        String[] splashes = {
            "Notch <3 ez!", "Made by Notch!", "The Work of Notch!", "110813!", "Sexy!",
            "Formerly Chuck's!", "Technoblade never dies!", "Subscribe to Technoblade!",
            "We will miss you Technoblade", "Villages closed due to raids",
            "They glow in the dark", "Empire of Dust!", "Cast him in!", "True and Honest!",
            "Not on Quilt!", "Ender stole my bike", "Heros get remembered, but legends never die.",
            "Technoblade never loses", "When you die from cancer, the cancer dies too.",
            "That’s not a loss that’s a draw"
        };

        for (String spl : splashes) SplashAPI.addSplash(spl);
    }

    public static OreType getOreType(String name){
        return MineralizationRegistry.getOreType(name);
    }
}