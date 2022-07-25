package io.github.unixsupremacist.mineralization;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import reborncore.api.blockentity.IMachineGuiHandler;
import reborncore.common.screen.BuiltScreenHandler;
import reborncore.common.screen.BuiltScreenHandlerProvider;

import java.util.HashMap;
import java.util.Map;

public class GuiTypes<T extends BlockEntity> implements IMachineGuiHandler {

    public static final Map<Identifier, GuiTypes<?>> TYPES = new HashMap<>();

    public static final GuiTypes<RoasterBlockEntity> ROASTER = register("roaster");
    public static final GuiTypes<PurifierBlockEntity> PURIFIER = register("purifier");

    private static <T extends BlockEntity> GuiTypes<T> register(String id) {
        return register(new Identifier(Mineralization.MODID, id));
    }

    public static <T extends BlockEntity> GuiTypes<T> register(Identifier identifier) {
        if (TYPES.containsKey(identifier)) {
            throw new RuntimeException("Duplicate gui type found");
        }

        return new GuiTypes<>(identifier);
    }

    public final Identifier identifier;
    public final ScreenHandlerType<BuiltScreenHandler> screenHandlerType;

    public GuiTypes(Identifier identifier) {
        this.identifier = identifier;
        this.screenHandlerType = ScreenHandlerRegistry.registerExtended(identifier, getScreenHandlerFactory());

        TYPES.put(identifier, this);
    }

    private ScreenHandlerRegistry.ExtendedClientHandlerFactory<BuiltScreenHandler> getScreenHandlerFactory() {
        return (syncId, playerInventory, packetByteBuf) -> {
            final BlockEntity blockEntity = playerInventory.player.world.getBlockEntity(packetByteBuf.readBlockPos());
            BuiltScreenHandler screenHandler = ((BuiltScreenHandlerProvider) blockEntity).createScreenHandler(syncId, playerInventory.player);

            //Set the screen handler type, not ideal but works lol
            screenHandler.setType(screenHandlerType);

            return screenHandler;
        };
    }

    @Override
    public void open(PlayerEntity player, BlockPos pos, World world) {
        if (!world.isClient) {
            //This is awful
            player.openHandledScreen(new ExtendedScreenHandlerFactory() {
                @Override
                public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
                    packetByteBuf.writeBlockPos(pos);
                }

                @Override
                public Text getDisplayName() {
                    return new LiteralText("What is this for?");
                }

                @Nullable
                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                    final BlockEntity blockEntity = player.world.getBlockEntity(pos);
                    BuiltScreenHandler screenHandler = ((BuiltScreenHandlerProvider) blockEntity).createScreenHandler(syncId, player);
                    screenHandler.setType(screenHandlerType);
                    return screenHandler;
                }
            });
        }
    }

    public ScreenHandlerType<BuiltScreenHandler> getScreenHandlerType() {
        return screenHandlerType;
    }
}
