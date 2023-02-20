package com.dawnflyc.begalms.item;

import com.dawnflyc.begalms.BegAlms;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BegAlms.modid);
    public static final RegistryObject<Item> ironBowl = ITEMS.register("iron_bowl",IronBowl::new);
    public static final RegistryObject<Item> goldBowl = ITEMS.register("gold_bowl",GoldBowl::new);

}
