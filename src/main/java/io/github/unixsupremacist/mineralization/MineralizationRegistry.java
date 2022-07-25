package io.github.unixsupremacist.mineralization;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import draylar.magna.item.ExcavatorItem;
import draylar.magna.item.HammerItem;
import io.github.feltmc.feltapi.api.ore_feature.v1.OreFeatures;
import io.github.feltmc.feltapi.api.tool.*;
import io.github.unixsupremacist.mineralization.recipe.CustomIngredient;
import io.github.unixsupremacist.mineralization.type.*;
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
    public static HashSet<StoneType> stoneEarth = new HashSet<>();
    public static HashSet<StoneType> stoneNether = new HashSet<>();
    public static HashSet<StoneType> stoneEnd = new HashSet<>();
    public static HashSet<OreType> oreTypes = new HashSet<>();
    public static HashSet<MaterialBasic> materials = new HashSet<>();

    public static HashSet<String> hammers = new HashSet<>();
    public static HashMap<String, String> items = new HashMap<>();
    public static HashMap<String, String> blocks = new HashMap<>();
    public static final HashSet<String> pickaxeMineable = new HashSet<>();
    public static MaterialBasic registerMaterial(MaterialBasic mat, String modid){
        materials.add(mat);
        for (String part : mat.parts.parts){
            if (!items.containsKey(part+"_"+mat.getName())){
                Item item = registerItem(modid, mat.getName(), part, Mineralization.MATERIAL_GROUP);
                if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> mat.getColor(), item);
                items.put(part+"_"+mat.getName(), modid+":"+part+"_"+mat.getName());
                if (part == "rod"){
                    CustomIngredient ingot = new CustomIngredient("c:ingots/"+mat.getName(), "tag");
                    registerCustomRecipe(modid, "filing_"+mat.getName()+"_ingot", Mineralization.MODID+":advanced_recipe", modid+":"+part+"_"+mat.getName(), 1, new CustomIngredient(Mineralization.hammerTag, "tag"), ingot, ingot);
                }
            }
        }

        if(mat instanceof ToolMaterial){
            Item sword = registerToolItem(modid, new SwordItem((ToolMaterial) mat, 3, -2.4f, new Item.Settings()), "sword", mat.getName());
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? mat.getColor() : -1, sword);

            Item shovel = registerToolItem(modid, new ShovelItem((ToolMaterial) mat, 1, -3.0f, new Item.Settings()), "shovel", mat.getName());
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? mat.getColor() : -1, shovel);

            Item axe = registerToolItem(modid, new FeltAxeItem((ToolMaterial) mat, 6, -3.0f, new Item.Settings(), true), "axe", mat.getName());
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? mat.getColor() : -1, axe);

            Item pickaxe = registerToolItem(modid, new FeltPickaxeItem((ToolMaterial) mat, 1, -2.8f, new Item.Settings(), true), "pickaxe", mat.getName());
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? mat.getColor() : -1, pickaxe);

            Item hoe = registerToolItem(modid, new FeltHoeItem((ToolMaterial) mat, 0, -3.0f, new Item.Settings()), "hoe", mat.getName());
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? mat.getColor() : -1, hoe);

            Item hammer = registerToolItem(modid, new HammerItem((ToolMaterial) mat, 1, -2.8f, new Item.Settings()), "hammer", mat.getName());
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? mat.getColor() : -1, hammer);

            Item excavator = registerToolItem(modid, new ExcavatorItem((ToolMaterial) mat, 1, -2.8f, new Item.Settings()), "excavator", mat.getName());
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? mat.getColor() : -1, excavator);

            Item file = registerToolItem(modid, new ToolItem((ToolMaterial) mat, new Item.Settings()), "file", mat.getName());
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? mat.getColor() : -1, file);
            hammers.add(Mineralization.MODID+":"+"file_"+mat.getName());
        }

        if(mat instanceof ArmorMaterial) {
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName() + "_helmet"), new ArmorItem((ArmorMaterial) mat, EquipmentSlot.HEAD, new Item.Settings()));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName() + "_chestplate"), new ArmorItem((ArmorMaterial) mat, EquipmentSlot.CHEST, new Item.Settings()));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName() + "_leggings"), new ArmorItem((ArmorMaterial) mat, EquipmentSlot.LEGS, new Item.Settings()));
            Registry.register(Registry.ITEM, new Identifier(modid, mat.getName() + "_boots"), new ArmorItem((ArmorMaterial) mat, EquipmentSlot.FEET, new Item.Settings()));
        }

        if(mat.isOre()){
            oreTypes.add(new OreType(mat.getName(), null, mat.getColor()));
            if (mat.getTags().contains("earth")) for (StoneType stoneType : stoneEarth) addOreBlock(stoneType, mat, modid);
            if (mat.getTags().contains("nether")) for (StoneType stoneType : stoneNether) addOreBlock(stoneType, mat, modid);
            if (mat.getTags().contains("end")) for (StoneType stoneType : stoneEnd) addOreBlock(stoneType, mat, modid);
        }
        return mat;
    }

    public static void addOreBlock(StoneType stoneType, MaterialBasic mat, String modid){
        if (!blocks.containsKey(stoneType.name+"_"+mat.getName())){
            String modelName = stoneType.name+"_"+mat.getName();
            Identifier blockModelIdentifier = new Identifier(modid, "block/"+modelName);
            Block block = Registry.register(Registry.BLOCK, new Identifier(modid, modelName), new Block(Block.Settings.of(Material.STONE).strength(4.0f)));
            BlockItem item = Registry.register(Registry.ITEM, new Identifier(modid, modelName), new BlockItem(block, new Item.Settings()));
            Mineralization.RESOURCE_PACK.addBlockState(new JState().add(new JVariant().put("", new JBlockModel(blockModelIdentifier))), new Identifier(modid, modelName));
            Mineralization.RESOURCE_PACK.addModel(JModel.model().parent(modid+":block/ore").textures(new JTextures().layer0(stoneType.textureLocation).layer1(modid+":block/ore")), blockModelIdentifier);
            Mineralization.RESOURCE_PACK.addModel(JModel.model().parent(modid+":block/"+modelName), new Identifier(modid, "item/"+modelName));
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
                ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> mat.getColor(), block);
                ColorProviderRegistry.ITEM.register((stack, tintIndex) -> mat.getColor(), item);
                BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
            }
            blocks.put(stoneType.name+"_"+mat.getName(), modid+":"+stoneType.name+"_"+mat.getName());
        }
        getOreType(mat.getName()).oreBlocks.put(stoneType.name, Registry.BLOCK.get(new Identifier(blocks.get(stoneType.name+"_"+mat.getName()))));
    }

    public static void registerData(String modid){
        for (MaterialBasic mat : materials) {
            if (mat.isOre()){
                if (mat.getTags().contains("earth")) for (StoneType stoneType : stoneEarth) addOreData(stoneType, mat, modid);
                if (mat.getTags().contains("nether")) for (StoneType stoneType : stoneNether) addOreData(stoneType, mat, modid);
                if (mat.getTags().contains("end")) for (StoneType stoneType : stoneEnd) addOreData(stoneType, mat, modid);
            }
        }
    }

    public static void addOreData(StoneType stoneType, MaterialBasic mat, String modid){
        String modelName = stoneType.name+"_"+mat.getName();
        pickaxeMineable.add(modid + ":" + modelName);
        JsonObject predicate = new JsonObject();
        JsonArray enchantmentsArray = new JsonArray();
        JsonObject enchantmentsObject = new JsonObject();
        JsonObject silkLevel = new JsonObject();
        predicate.add("enchantments", enchantmentsArray);
        enchantmentsArray.add(enchantmentsObject);
        enchantmentsObject.addProperty("enchantment", "minecraft:silk_touch");
        enchantmentsObject.add("levels", silkLevel);
        silkLevel.addProperty("min", 1);
        Mineralization.RESOURCE_PACK.addLootTable(new Identifier(modid + ":" + "blocks/" + modelName), JLootTable.loot("minecraft:block")
                .pool(new JPool().rolls(1)
                        .entry(new JEntry().type("minecraft:alternatives")
                                .child(new JEntry().type("minecraft:item").name(modid + ":" + modelName).condition(new JCondition().condition("minecraft:match_tool").parameter("predicate", predicate)))
                                .child(new JEntry().type("minecraft:item").name(items.get("raw_material_"+mat.getName())).condition("minecraft:survives_explosion").function(new JFunction("minecraft:apply_bonus").parameter("enchantment", "minecraft:fortune").parameter("formula", "minecraft:ore_drops")))
                        )
                )
        );
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
        if (stoneType.tags.contains("earth")) stoneEarth.add(stoneType);
        if (stoneType.tags.contains("nether")) stoneNether.add(stoneType);
        if (stoneType.tags.contains("end")) stoneEnd.add(stoneType);
    }

    public static OreType getOreType(String name){
        for (OreType oreType : oreTypes) if (oreType.name == name) return oreType;
        return null;
    }

    public static Item registerItem(String MODID, String material, String part, ItemGroup itemGroup){
        Item item = new Item(new Item.Settings().group(itemGroup));
        Registry.register(Registry.ITEM, new Identifier(MODID, part+"_"+material), item);
        Mineralization.RESOURCE_PACK.addModel(JModel.model("item/generated").textures(new JTextures().layer0(Mineralization.MODID+":item/part/"+part)), new Identifier(MODID, "item/"+part+"_"+material));
        Mineralization.RESOURCE_PACK.addTag(new Identifier("c:items/"+part+"s/"+material), new JTag().add(new Identifier(MODID, part+"_"+material)));
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
            for (String string : hashset)
                if (string.startsWith("#")) jtag.tag(new Identifier(string.substring(1)));
                else jtag.add(new Identifier(string));
        Mineralization.RESOURCE_PACK.addTag(new Identifier(namespace), jtag);
    }
}