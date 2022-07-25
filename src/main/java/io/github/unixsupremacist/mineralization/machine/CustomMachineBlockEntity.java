package io.github.unixsupremacist.mineralization.machine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import reborncore.client.screen.builder.ScreenHandlerBuilder;
import reborncore.common.screen.BuiltScreenHandler;
import reborncore.common.screen.BuiltScreenHandlerProvider;
import techreborn.blockentity.machine.GenericMachineBlockEntity;

public class CustomMachineBlockEntity extends GenericMachineBlockEntity implements BuiltScreenHandlerProvider {
    public final String name;

    public CustomMachineBlockEntity(BlockEntityType bet, BlockPos pos, BlockState state, String name, int maxInput, int maxEnergy, Block toolDrop, int energySlot) {
        super(bet, pos, state, name, maxInput, maxEnergy, toolDrop, energySlot);
        this.name = name;
    }

    @Override
    public BuiltScreenHandler createScreenHandler(int syncID, PlayerEntity player) {
        return new ScreenHandlerBuilder(name).player(player.getInventory()).inventory().hotbar().addInventory().blockEntity(this)
                .slot(0, 55, 45).outputSlot(1, 101, 45).energySlot(2, 8, 72).syncEnergyValue().syncCrafterValue()
                .addInventory().create(this, syncID);
    }
}
