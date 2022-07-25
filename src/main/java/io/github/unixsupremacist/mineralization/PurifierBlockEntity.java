package io.github.unixsupremacist.mineralization;

import io.github.unixsupremacist.mineralization.machine.CustomMachineBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.screen.BuiltScreenHandlerProvider;
import reborncore.common.util.RebornInventory;

public class PurifierBlockEntity extends CustomMachineBlockEntity implements BuiltScreenHandlerProvider {
    public PurifierBlockEntity(BlockPos pos, BlockState state) {
        super(Mineralization.PURIFIER_BLOCK_ENTITY, pos, state, "purifier", 128, 512, Mineralization.purifier, 2);
        final int[] inputs = new int[]{0};
        final int[] outputs = new int[]{1};
        this.inventory = new RebornInventory<>(3, "PurifierBlockEntity", 64, this);
        this.crafter = new RecipeCrafter(Mineralization.purifierRecipes, this, 2, 1, this.inventory, inputs, outputs);
    }
}
