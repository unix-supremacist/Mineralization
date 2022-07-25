package io.github.unixsupremacist.mineralization;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import techreborn.client.GuiFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class ClientGuiType<T extends BlockEntity> {
    public static final Map<Identifier, ClientGuiType<?>> TYPES = new HashMap<>();

    public static final ClientGuiType<RoasterBlockEntity> ROASTER = register(GuiTypes.ROASTER, Basic2SlotGui::new);
    public static final ClientGuiType<PurifierBlockEntity> PURIFIER = register(GuiTypes.PURIFIER, BasicGui::new);

    private static <T extends BlockEntity> ClientGuiType<T> register(GuiTypes<T> type, GuiFactory<T> factory) {
        return new ClientGuiType<>(type, factory);
    }

    public static void validate() {
        for (Identifier identifier : GuiTypes.TYPES.keySet()) {
            Objects.requireNonNull(TYPES.get(identifier), "No ClientGuiType for " + identifier);
        }
    }

    public ClientGuiType(GuiTypes<T> guiType, GuiFactory<T> guiFactory) {
        Objects.requireNonNull(guiType);
        Objects.requireNonNull(guiFactory);
        ScreenRegistry.register(guiType.getScreenHandlerType(), guiFactory);

        TYPES.put(guiType.identifier, this);
    }
}

