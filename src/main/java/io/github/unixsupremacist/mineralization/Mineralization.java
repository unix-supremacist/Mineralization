package io.github.unixsupremacist.mineralization;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mineralization implements ModInitializer {
	public static final String MODID = "mineralization";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(MODID +":resource-pack");
	public static final String hammerTag = "c:tools/hammer";
	public static final ItemGroup MATERIAL_GROUP = FabricItemGroupBuilder.create(
					new Identifier(MODID, "materials"))
			.icon(() -> new ItemStack(MineralizationRegistry.items.get("gear_copper")))
			.build();

	@Override
	public void onInitialize() {
		StoneTypes.registerStoneTypes();
		Parts.registerParts();
		Materials.registerMaterials(MODID);
		OreGens.registerOreGens(MODID);
		LOGGER.info("Hello Fabric world!");
		MineralizationRegistry.registerTags(MineralizationRegistry.hammers, "modern_industrialization:items/forge_hammer_tools");
		MineralizationRegistry.hammers.add("#modern_industrialization:forge_hammer_tools");
		MineralizationRegistry.registerTags(MineralizationRegistry.hammers, "c:items/tools/hammer");
		RRPCallback.BEFORE_VANILLA.register(a -> a.add(RESOURCE_PACK));
		Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MODID, "advanced_recipe"), AdvancedRecipeSerializer.INSTANCE);
	}
}
