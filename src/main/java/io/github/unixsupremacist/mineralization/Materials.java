package io.github.unixsupremacist.mineralization;

public class Materials {
    public static MaterialItem
            gold, copper, tetrahedrite, iron, pyrite, emerald, nickel, garnierite, galena, lead, silver, platinum, sheldonite, invar, electrum, tin, cassiterite, bronze, steel,
            aluminium, bauxite, nikolite, ruby, sapphire, olivine, redalloy, tungsten, tungstate, titanium, rutile, sphalerite, zinc, brass, aluminiumbrass, cobalt, wroughtiron,
            chromium, chromite, tungstensteel, osmium, iridium, magnesium, magnesite, cupronickel, uranium, thorium, plutonium;

    public static void registerMaterials(String MODID){
       // testMaterial = MineralizationRegistry.registerMaterial(new MaterialAll("test", 0x000000, 15, new int[] {2, 5, 6, 2}, 0.0f, 0.0f, 2.0f, 2, 14, true, true, 1), MODID);
       // test2Material = MineralizationRegistry.registerMaterial(new MaterialAll("test2", 0x000000, 15, new int[] {2, 5, 6, 2}, 2.0f, 2, 14, true, true, 1), MODID);
       // test3Material = MineralizationRegistry.registerMaterial(new MaterialTool("test3", 0x000000, 15, 2.0f, 2, 14, true, false, 0), MODID);
       // test4Material = MineralizationRegistry.registerMaterial(new MaterialItem("test4", 0x000000, true, false, 0), MODID);

         gold = MineralizationRegistry.registerMaterial(new MaterialItem("gold", 0xe7ca53, true, true, 3), MODID);
         copper = MineralizationRegistry.registerMaterial(new MaterialItem("copper", 0xc78621, true, true, 3), MODID);
         tetrahedrite = MineralizationRegistry.registerMaterial(new MaterialItem("tetrahedrite", 0xc78621, true, true, 0), MODID);
         iron = MineralizationRegistry.registerMaterial(new MaterialItem("iron", 0xE0E0E0, true, true, 2), MODID);
         pyrite = MineralizationRegistry.registerMaterial(new MaterialItem("pyrite", 0xE0E0E0, true, true, 0), MODID);
         emerald = MineralizationRegistry.registerMaterial(new MaterialItem("emerald", 0x08dd7b, false, true, 0), MODID);
         nickel = MineralizationRegistry.registerMaterial(new MaterialItem("nickel", 0x464D19, true, true, 2), MODID);
         garnierite = MineralizationRegistry.registerMaterial(new MaterialItem("garnierite", 0x464D19, true, true, 0), MODID);
         galena = MineralizationRegistry.registerMaterial(new MaterialItem("galena", 0xFFFFFF, true, true, 0), MODID);
         lead = MineralizationRegistry.registerMaterial(new MaterialItem("lead", 0x544773, true, true, 2), MODID);
         silver = MineralizationRegistry.registerMaterial(new MaterialItem("silver", 0x9cbddc, true, true, 2), MODID);
         platinum = MineralizationRegistry.registerMaterial(new MaterialItem("platinum", 0x70b6f7, true, true, 4), MODID);
         sheldonite = MineralizationRegistry.registerMaterial(new MaterialItem("sheldonite", 0x181b07, true, true, 0), MODID);
         invar = MineralizationRegistry.registerMaterial(new MaterialItem("invar", 0xcebe7c, true, false, 2), MODID);
         electrum = MineralizationRegistry.registerMaterial(new MaterialItem("electrum", 0xf3d248, true, false, 3), MODID);
         tin = MineralizationRegistry.registerMaterial(new MaterialItem("tin", 0xE0E0FF, true, true, 3), MODID);
         cassiterite = MineralizationRegistry.registerMaterial(new MaterialItem("cassiterite", 0xE0E0FF, true, true, 0), MODID);
         bronze = MineralizationRegistry.registerMaterial(new MaterialTool("bronze", 0xc69114, 250, 2.0f, 2, 14, true, true, 3), MODID);
         steel = MineralizationRegistry.registerMaterial(new MaterialTool("steel", 0x424c55, 500, 2.2f, 2, 10, true, false, 2), MODID);
         aluminium = MineralizationRegistry.registerMaterial(new MaterialItem("aluminium", 0xbad4ec, true, true, 3), MODID);
         bauxite = MineralizationRegistry.registerMaterial(new MaterialItem("bauxite", 0xbad4ec, true, true, 0), MODID);
         nikolite = MineralizationRegistry.registerMaterial(new MaterialItem("nikolite", 0x1273de, true, true, 0), MODID);
         ruby = MineralizationRegistry.registerMaterial(new MaterialItem("ruby", 0xbb4a1d, false, true, 1), MODID);
         sapphire = MineralizationRegistry.registerMaterial(new MaterialItem("sapphire", 0x1b55b8, false, true, 1), MODID);
         olivine = MineralizationRegistry.registerMaterial(new MaterialItem("olivine", 0x08dd7b, false, true, 1), MODID);
         redalloy = MineralizationRegistry.registerMaterial(new MaterialItem("redalloy", 0x000000, true, false, 2), MODID);
         tungsten = MineralizationRegistry.registerMaterial(new MaterialItem("tungsten", 0x181b07, true, false, 4), MODID);
         tungstate = MineralizationRegistry.registerMaterial(new MaterialItem("tungstate", 0x181b07, true, true, 0), MODID);
         titanium = MineralizationRegistry.registerMaterial(new MaterialItem("titanium", 0xc4b4ed, true, false, 4), MODID);
         rutile = MineralizationRegistry.registerMaterial(new MaterialItem("rutile", 0xc4b4ed, true, true, 0), MODID);
         sphalerite = MineralizationRegistry.registerMaterial(new MaterialItem("sphalerite", 0x181b07, true, true, 0), MODID);
         zinc = MineralizationRegistry.registerMaterial(new MaterialItem("zinc", 0xbba69f, true, false, 2), MODID);
         brass = MineralizationRegistry.registerMaterial(new MaterialItem("brass", 0xdba31e, true, false, 2), MODID);
         aluminiumbrass = MineralizationRegistry.registerMaterial(new MaterialItem("aluminiumbrass", 0xdba31e, true, false, 2), MODID);
         cobalt = MineralizationRegistry.registerMaterial(new MaterialItem("cobalt", 0x505080, true, true, 4), MODID);
         wroughtiron = MineralizationRegistry.registerMaterial(new MaterialItem("wroughtiron", 0xceaa9f, true, false, 2), MODID);
         chromium = MineralizationRegistry.registerMaterial(new MaterialItem("chromium", 0xf4c4b5, true, false, 2), MODID);
         chromite = MineralizationRegistry.registerMaterial(new MaterialItem("chromite", 0xf4c4b5, true, true, 0), MODID);
         tungstensteel = MineralizationRegistry.registerMaterial(new MaterialItem("tungstensteel", 0x274562, true, false, 4), MODID);
         osmium = MineralizationRegistry.registerMaterial(new MaterialItem("osmium", 0x93bbe8, true, true, 4), MODID);
         iridium = MineralizationRegistry.registerMaterial(new MaterialItem("iridium", 0xFFFFFF, true, true, 4), MODID);
         magnesium = MineralizationRegistry.registerMaterial(new MaterialItem("magnesium", 0x000000, true, true, 2), MODID);
         magnesite = MineralizationRegistry.registerMaterial(new MaterialItem("magnesite", 0x000000, true, true, 0), MODID);
         cupronickel = MineralizationRegistry.registerMaterial(new MaterialItem("cupronickel", 0x000000, true, false, 2), MODID);
         uranium = MineralizationRegistry.registerMaterial(new MaterialItem("uranium", 0x000000, true, true, 4), MODID);
         thorium = MineralizationRegistry.registerMaterial(new MaterialItem("thorium", 0x000000, true, false, 4), MODID);
         plutonium = MineralizationRegistry.registerMaterial(new MaterialItem("plutonium", 0x000000, true, false, 4), MODID);
    }
}
