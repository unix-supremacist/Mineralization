package io.github.unixsupremacist.mineralization;

import io.github.unixsupremacist.mineralization.machine.CustomMachineBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import reborncore.client.screen.builder.ScreenHandlerBuilder;
import reborncore.common.recipes.RecipeCrafter;
import reborncore.common.screen.BuiltScreenHandler;
import reborncore.common.screen.BuiltScreenHandlerProvider;
import reborncore.common.util.RebornInventory;

public class RoasterBlockEntity extends CustomMachineBlockEntity implements BuiltScreenHandlerProvider {
    public RoasterBlockEntity(BlockPos pos, BlockState state) {
        super(Mineralization.ROASTER_BLOCK_ENTITY, pos, state, "roaster", 128, 512, Mineralization.roaster, 3);
        final int[] inputs = new int[]{0};
        final int[] outputs = new int[]{1,2};
        this.inventory = new RebornInventory<>(4, "RoasterBlockEntity", 64, this);
        this.crafter = new RecipeCrafter(Mineralization.roasterRecipes, this, 2, 2, this.inventory, inputs, outputs);
    }

    @Override
    public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
        return new ScreenHandlerBuilder(name).player(player.getInventory()).inventory().hotbar().addInventory().blockEntity(this)
                .slot(0, 55, 45).outputSlot(1, 101, 45).outputSlot(2, 101, 63).energySlot(3, 8, 72).syncEnergyValue().syncCrafterValue()
                .addInventory().create(this, syncID);
    }
}
