package io.github.unixsupremacist.mineralization.type;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class MaterialAll extends MaterialBasic implements ArmorMaterial, ToolMaterial {
    int[] protection;
    float toughness;
    float resistance;
    int durability;

    float power;
    int mining_level;
    int magic;

    private static final int[] BASE_ARMOR = new int[] {13, 15, 16, 11};

    public MaterialAll(String name, int color, boolean metallic, boolean ore, int tier, Parts parts, int durablity, int[] protection, float power, int mining_level, int magic, float toughness, float resistance){
        super(name, color, metallic, ore, tier, parts);
        this.durability = durablity;
        this.power = power;
        this.mining_level = mining_level;
        this.magic = magic;
        this.protection = protection;
        this.toughness = toughness;
        this.resistance = resistance;
    }

    public MaterialAll(String name, int color,boolean metallic, boolean ore, int tier, Parts parts, int durablity, int[] protection, float power, int mining_level, int magic ){
        super(name, color, metallic, ore, tier, parts);
        this.durability = durablity;
        this.power = power;
        this.mining_level = mining_level;
        this.magic = magic;
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
    public int getDurability() {
        return durability * 16;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return power * 3;
    }

    @Override
    public float getAttackDamage() {
        return power;
    }

    @Override
    public int getMiningLevel() {
        return mining_level;
    }

    @Override
    public int getEnchantability() {
        return magic;
    }

    @Override
    public SoundEvent getEquipSound() {
        return (metallic) ? SoundEvents.ITEM_ARMOR_EQUIP_IRON : SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
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