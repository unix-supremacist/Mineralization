package io.github.unixsupremacist.mineralization;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class MaterialTool extends MaterialItem implements ToolMaterial{
    String name;
    int color;
    int durability;

    float power;
    int mining_level;
    int magic;
    boolean metallic;
    boolean ore;

    public MaterialTool(String name, int color, int durablity, float power, int mining_level, int magic, boolean metallic, boolean ore, int tier){
        super(name, color, metallic, ore, tier);
        this.durability = durablity;
        this.power = power;
        this.mining_level = mining_level;
        this.magic = magic;
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
    public Ingredient getRepairIngredient() {
        return null;
    }
}
