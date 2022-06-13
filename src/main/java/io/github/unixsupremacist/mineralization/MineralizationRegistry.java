package io.github.unixsupremacist.mineralization;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.feltmc.feltapi.api.ore_feature.v1.OreFeatures;
import io.github.feltmc.feltapi.api.tool.*;
import net.devtech.arrp.json.blockstate.JBlockModel;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.loot.*;
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
    public static HashSet<String> hammers = new HashSet<>();
    public static HashMap<String, Item> items = new HashMap<>();
    public static final HashSet<String> pickaxeMineable = new HashSet<>();
    public static MaterialItem registerMaterial(MaterialItem mat, String modid){
        for (Part part : parts){
            if (part.tier <= mat.getTier()){
                Item item = registerItem(modid, mat.getName(), part, Mineralization.MATERIAL_GROUP);
                if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> mat.getColor(), item);
                items.put(part.name+"_"+mat.getName(), item);
                if (part.name == "plate"){
                    CustomIngredient ingot = new CustomIngredient("c:ingots/"+mat.getName(), "tag");
                    registerCustomRecipe(modid, "hammering_"+mat.getName()+"_ingot", Mineralization.MODID+":advanced_recipe", modid+":"+part.name+"_"+mat.getName(), 1, new CustomIngredient(Mineralization.hammerTag, "tag"), ingot, ingot);
                }
            }
        }

        if(mat instanceof ToolMaterial){
            //Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_sword"), new SwordItem((ToolMaterial) mat, 3, -2.4f, new Item.Settings()));
            //Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_shovel"), new ShovelItem((ToolMaterial) mat, 1, -3.0f, new Item.Settings()));
            //Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_axe"), new FeltAxeItem((ToolMaterial) mat, 6, -3.0f, new Item.Settings(), true));
            //Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_pickaxe"), new FeltPickaxeItem((ToolMaterial) mat, 1, -2.8f, new Item.Settings(), true));
            Item hammer = registerToolItem(modid, new FeltPickaxeItem((ToolMaterial) mat, 1, -2.8f, new Item.Settings(), true), "hammer", mat.getName());
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? mat.getColor() : -1, hammer);
            hammers.add(Mineralization.MODID+":"+"hammer_"+mat.getName());
            //Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_hammer"), new FeltPickaxeItem((ToolMaterial) mat, 1, -2.8f, new Item.Settings(), true));
            //Registry.register(Registry.ITEM, new Identifier(modid, mat.getName()+"_hoe"), new FeltHoeItem((ToolMaterial) mat, 0, -3.0f, new Item.Settings()));
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
                pickaxeMineable.add(modid+":"+modelName);
                JsonObject predicate = new JsonObject();
                JsonArray enchantmentsArray = new JsonArray();
                JsonObject enchantmentsObject = new JsonObject();
                JsonObject silkLevel = new JsonObject();
                predicate.add("enchantments", enchantmentsArray);
                enchantmentsArray.add(enchantmentsObject);
                enchantmentsObject.addProperty("enchantment", "minecraft:silk_touch");
                enchantmentsObject.add("levels", silkLevel);
                silkLevel.addProperty("min", 1);
                Mineralization.RESOURCE_PACK.addLootTable(new Identifier(modid+":"+"blocks/"+modelName), JLootTable.loot("minecraft:block")
                    .pool(new JPool().rolls(1)
                    .entry(new JEntry().type("minecraft:alternatives")
                    .child(new JEntry().type("minecraft:item").name(modid+":"+modelName).condition(new JCondition().condition("minecraft:match_tool").parameter("predicate", predicate)))
                    .child(new JEntry().type("minecraft:item").name(modid+":raw_material_"+mat.getName()).condition("minecraft:survives_explosion").function(new JFunction("minecraft:apply_bonus").parameter("enchantment", "minecraft:fortune").parameter("formula", "minecraft:ore_drops")))
                )));

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

    public static Item registerToolItem(String MODID, Item item, String toolType, String toolMaterial){
        Registry.register(Registry.ITEM, new Identifier(MODID, toolType+"_"+toolMaterial), item);
        Mineralization.RESOURCE_PACK.addModel(JModel.model("item/generated").textures(new JTextures().layer0(Mineralization.MODID+":item/stick").layer1(Mineralization.MODID+":item/tool/"+toolType)), new Identifier(MODID, "item/"+toolType+"_"+toolMaterial));
        Mineralization.RESOURCE_PACK.addTag(new Identifier("c:items/"+toolType+"s/"+toolMaterial), new JTag().add(new Identifier(MODID, toolType+"_"+toolMaterial)));
        return item;
    }

    public static void registerCustomRecipe(String MODID, String recipeName, String recipeType, String result, int count, CustomIngredient... ingredients){
        String recipe =
                "{\n\"type\": \""+recipeType+"\",\n" +
                "\"result\": {\n\"item\": \""+result+"\",\n\"count\": "+count+"\n},\n" +
                "\"ingredients\": [\n";
                for (int i = 0; i < ingredients.length; i++){
                    CustomIngredient ingredient = ingredients[i];
                    if (i + 1 < ingredients.length)
                        recipe += "{\n\""+ingredient.ingredientType+"\": \""+ingredient.ingredient+"\"\n},\n";
                    else
                        recipe += "{\n\""+ingredient.ingredientType+"\": \""+ingredient.ingredient+"\"\n}\n";
                }
                recipe += "]\n}";
        Mineralization.RESOURCE_PACK.addData(new Identifier(MODID, "recipes/"+recipeName+".json"), recipe.getBytes());
    }

    public static void registerTags(HashSet<String> hashset, String namespace){
        JTag jtag = new JTag();
            for (String string : hashset){
                if (string.startsWith("#"))
                    jtag.tag(new Identifier(string.substring(1)));
                else
                    jtag.add(new Identifier(string));
            }
        Mineralization.RESOURCE_PACK.addTag(new Identifier(namespace), jtag);
    }
}
