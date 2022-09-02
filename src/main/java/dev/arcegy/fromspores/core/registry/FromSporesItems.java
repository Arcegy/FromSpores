package dev.arcegy.fromspores.core.registry;

import dev.arcegy.fromspores.FromSpores;
import dev.arcegy.fromspores.common.item.SporeBombItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FromSporesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FromSpores.MOD_ID);

    public static final RegistryObject<Item> CRAVER_SPAWN_EGG = ITEMS.register("craver_spawn_egg",
            () -> new ForgeSpawnEggItem(FromSporesEntityTypes.CRAVER, 1474182, 16753768, new Item.Properties().tab(CreativeModeTab.TAB_MISC).rarity(Rarity.COMMON)));

    public static final RegistryObject<Item> FUNGUS_SPORE = ITEMS.register("fungus_spore",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).rarity(Rarity.COMMON)));

    public static final RegistryObject<SporeBombItem> SPORE_BOMB = ITEMS.register("spore_bomb",
            () -> new SporeBombItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).rarity(Rarity.COMMON).stacksTo(16)));
}
