package io.github.unixsupremacist.mineralization;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class MaterialAll extends MaterialTool implements ArmorMaterial {
    int[] protection;
    float toughness;
    float resistance;

    private static final int[] BASE_ARMOR = new int[] {13, 15, 16, 11};

    public MaterialAll(String name, int color, int durablity, int[] protection, float toughness, float resistance, float power, int mining_level, int magic, boolean metallic, boolean ore, int tier){
        super(name, color, durablity, power, mining_level, magic, metallic, ore, tier);
        this.protection = protection;
        this.toughness = toughness;
        this.resistance = resistance;
    }

    public MaterialAll(String name, int color, int durablity, int[] protection, float power, int mining_level, int magic, boolean metallic, boolean ore, int tier){
        super(name, color, durablity, power, mining_level, magic, metallic, ore, tier);
        this.protection = protection;
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_ARMOR[slot.getEntitySlotId()] * durability;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return protection[slot.getEntitySlotId()];
    }

    @Override
    public SoundEvent getEquipSound() {
        return (metallic) ? SoundEvents.ITEM_ARMOR_EQUIP_IRON : SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return resistance;
    }
}