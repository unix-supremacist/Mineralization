package io.github.unixsupremacist.mineralization;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mineralization implements ModInitializer {
	public static final String MODID = "mineralization";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(MODID +":resource-pack");
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
		RRPCallback.BEFORE_VANILLA.register(a -> a.add(RESOURCE_PACK));
	}
}
