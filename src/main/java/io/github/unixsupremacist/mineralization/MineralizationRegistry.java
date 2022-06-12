package io.github.unixsupremacist.mineralization;


import io.github.feltmc.feltapi.api.ore_feature.v1.OreFeatures;
import io.github.feltmc.feltapi.api.tool.*;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.tags.JTag;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MineralizationRegistry {
    public static HashSet<StoneType> stoneTypes = new HashSet<>();
    public static HashSet<OreType> oreTypes = new HashSet<>();
    public static HashSet<Part> parts = new HashSet<>();
    public static HashMap<String, Item> items = new HashMap<>();
    public static MaterialItem registerMaterial(MaterialItem mat, String modid){
        for (Part part : parts){
            if (part.tier <= mat.getTier()){
                Item item = registerItem(modid, mat.getName(), part, Mineralization.MATERIAL_GROUP);
                if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> mat.getColor(), item);
                items.put(part.name+"_"+mat.getName(), item);
            }
        }

        if(mat instanceof ToolMaterial){
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_sword"), new SwordItem((ToolMaterial) mat, 3, -2.4f, new Item.Settings()));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_shovel"), new ShovelItem((ToolMaterial) mat, 1, -3.0f, new Item.Settings()));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_axe"), new FeltAxeItem((ToolMaterial) mat, 6, -3.0f, new Item.Settings(), true));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_pickaxe"), new FeltPickaxeItem((ToolMaterial) mat, 1, -2.8f, new Item.Settings(), true));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_hoe"), new FeltHoeItem((ToolMaterial) mat, 0, -3.0f, new Item.Settings()));
        }

        if(mat instanceof ArmorMaterial) {
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName() + "_helm"), new ArmorItem((ArmorMaterial) mat, EquipmentSlot.HEAD, new Item.Settings()));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName() + "_chest"), new ArmorItem((ArmorMaterial) mat, EquipmentSlot.CHEST, new Item.Settings()));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName() + "_legs"), new ArmorItem((ArmorMaterial) mat, EquipmentSlot.LEGS, new Item.Settings()));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName() + "_boots"), new ArmorItem((ArmorMaterial) mat, EquipmentSlot.FEET, new Item.Settings()));
        }

        if(mat.isOre()){
            oreTypes.add(new OreType(mat.getName(), null, mat.getColor()));
            for (StoneType stoneType : stoneTypes){
                Block block = new Block(Block.Settings.of(Material.STONE).strength(4.0f));
                BlockItem item = new BlockItem(block, new Item.Settings());
                String modelName = stoneType.name+"_"+mat.getName();
                Identifier blockModelIdentifier = new Identifier(modid, "block/"+modelName);
                Registry.register(Registry.BLOCK, new Identifier(modid, modelName), block);
                Registry.register(Registry.ITEM, new Identifier(modid, modelName), item);
                Mineralization.RESOURCE_PACK.addBlockState(new JState().add(new JVariant().put("", new JBlockModel(blockModelIdentifier))), new Identifier(modid, modelName));
                Mineralization.RESOURCE_PACK.addModel(JModel.model().parent(modid+":block/ore").textures(new JTextures().layer0(stoneType.textureLocation).layer1(modid+":block/ore")), blockModelIdentifier);
                Mineralization.RESOURCE_PACK.addModel(JModel.model().parent(modid+":block/"+modelName), new Identifier(modid, "item/"+modelName));
                if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
                    ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> mat.getColor(), block);
                    ColorProviderRegistry.ITEM.register((stack, tintIndex) -> mat.getColor(), item);
                    BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
                }
                getOreType(mat.getName()).oreBlocks.put(stoneType.name, block);
                getOreType(mat.getName()).oreItems.put(stoneType.name, item);
            }
        }
        return mat;
    }

    public static void registerOreGen(String MODID, OreGen oreGen){
        OreFeatures.createFilteredOrePlacedFeature(MODID, oreGen.name, (block, r) -> {
            BlockState ore = null;
            for (StoneType stonetype : stoneTypes){
                if (block == stonetype.block.getDefaultState()){
                    ore = oreGen.oretypes[r.nextInt(oreGen.oretypes.length)].oreBlocks.get(stonetype.name).getDefaultState();
                }
            }

            if (ore == null) return null;
            return ore;
        }, oreGen.min, oreGen.max, oreGen.weight, oreGen.size, oreGen.discard, List.of(), oreGen.biome);
    }

    public static void registerStoneType(StoneType stoneType){
        stoneTypes.add(stoneType);
    }

    public static OreType getOreType(String name){
        for (OreType oreType : oreTypes){
            if (oreType.name == name) return oreType;
        }
        return null;
    }

    public static Part registerPart(Part part){
        parts.add(part);
        return part;
    }

    public static Item registerItem(String MODID, String material, Part part, ItemGroup itemGroup){
        Item item = new Item(new Item.Settings().group(itemGroup));
        Registry.register(Registry.ITEM, new Identifier(MODID, part.name+"_"+material), item);
        Mineralization.RESOURCE_PACK.addModel(JModel.model("item/generated").textures(new JTextures().layer0(part.textureLocation)), new Identifier(MODID, "item/"+part.name+"_"+material));
        Mineralization.RESOURCE_PACK.addTag(new Identifier("c:items/"+part.name+"s/"+material), new JTag().add(new Identifier(MODID, part.name+"_"+material)));
        return item;
    }
}
