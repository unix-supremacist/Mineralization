package io.github.unixsupremacist.mineralization;

import io.github.unixsupremacist.mineralization.recipe.AdvancedRecipeSerializer;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reborncore.common.crafting.RebornRecipe;
import reborncore.common.crafting.RebornRecipeType;
import reborncore.common.crafting.RecipeManager;
import techreborn.blocks.GenericMachineBlock;

public class Mineralization implements ModInitializer {
	public static final String MODID = "mineralization";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(MODID +":resource-pack");
	public static final String hammerTag = "c:tools/hammer";
	public static final ItemGroup MATERIAL_GROUP = FabricItemGroupBuilder.create(
					new Identifier(MODID, "materials"))
			.icon(() -> new ItemStack(	Registry.ITEM.get(new Identifier(MineralizationRegistry.items.get("rod_copper")))))
			.build();
	public static final RebornRecipeType<RebornRecipe> roasterRecipes = RecipeManager.newRecipeType(new Identifier(MODID, "roaster"));
	public static final RebornRecipeType<RebornRecipe> purifierRecipes = RecipeManager.newRecipeType(new Identifier(MODID, "purifier"));
	public static final Block roaster = new GenericMachineBlock(GuiTypes.ROASTER, RoasterBlockEntity::new);
	public static BlockEntityType<RoasterBlockEntity> ROASTER_BLOCK_ENTITY;
	public static final Block purifier = new GenericMachineBlock(GuiTypes.PURIFIER, PurifierBlockEntity::new);
	public static BlockEntityType<PurifierBlockEntity> PURIFIER_BLOCK_ENTITY;

	@Override
	public void onInitialize() {
		MineralizationContent.registerStoneTypes();
		MineralizationContent.registerMaterialOverides();
		MineralizationContent.registerMaterials(MODID);
		MineralizationRegistry.registerData(MODID);
		MineralizationContent.registerOreGens(MODID);
		MineralizationContent.registerSplashes();
		LOGGER.info("Hello Fabric world!");
		MineralizationRegistry.registerTags(MineralizationRegistry.hammers, "modern_industrialization:items/forge_hammer_tools");
		MineralizationRegistry.hammers.add("#modern_industrialization:forge_hammer_tools");
		MineralizationRegistry.registerTags(MineralizationRegistry.hammers, "c:items/tools/hammer");
		MineralizationRegistry.registerTags(MineralizationRegistry.pickaxeMineable, "minecraft:blocks/mineable/pickaxe");
		MineralizationRegistry.registerTags(MineralizationRegistry.pickaxeMineable, "minecraft:blocks/needs_stone_tool");
		RRPCallback.BEFORE_VANILLA.register(a -> a.add(RESOURCE_PACK));
		Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MODID, "advanced_recipe"), AdvancedRecipeSerializer.INSTANCE);

		Registry.register(Registry.BLOCK, new Identifier(MODID, "roaster"), roaster);
		Registry.register(Registry.ITEM, new Identifier(MODID, "roaster"), new BlockItem(roaster, new FabricItemSettings().group(ItemGroup.MISC)));
		ROASTER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MODID, "roaster"), FabricBlockEntityTypeBuilder.create(RoasterBlockEntity::new, roaster).build(null));
		Registry.register(Registry.BLOCK, new Identifier(MODID, "purifier"), purifier);
		Registry.register(Registry.ITEM, new Identifier(MODID, "purifier"), new BlockItem(purifier, new FabricItemSettings().group(ItemGroup.MISC)));
		PURIFIER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MODID, "purifier"), FabricBlockEntityTypeBuilder.create(PurifierBlockEntity::new, purifier).build(null));

		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)
			ClientGuiType.validate();
	}
}
