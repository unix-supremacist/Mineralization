package io.github.unixsupremacist.mineralization;

import io.github.unixsupremacist.mineralization.machine.CustomMachineBlockEntity;
import io.github.unixsupremacist.mineralization.machine.CustomMachineGui;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;

public class Basic2SlotGui extends CustomMachineGui {

    public Basic2SlotGui(int syncID, final PlayerEntity player, final CustomMachineBlockEntity blockEntity) {
        super(syncID, player, blockEntity);
    }

    @Override
    protected void drawBackground(MatrixStack matrixStack, final float f, final int mouseX, final int mouseY) {
        super.drawBackground(matrixStack, f, mouseX, mouseY);
        final Layer layer = Layer.BACKGROUND;

        drawSlot(matrixStack, 8, 72, layer);
        drawSlot(matrixStack, 55, 45, layer);
        drawOutputSlot(matrixStack, 101, 45, layer);
        drawOutputSlot(matrixStack, 101, 63, layer);

        builder.drawJEIButton(matrixStack, this, 158, 5, layer);
    }
}
