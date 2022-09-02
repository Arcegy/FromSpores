package dev.arcegy.fromspores.core.registry;

import dev.arcegy.fromspores.FromSpores;
import dev.arcegy.fromspores.common.entity.CraverEntity;
import dev.arcegy.fromspores.common.entity.SporeBombEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = FromSpores.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FromSporesEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FromSpores.MOD_ID);

    public static final RegistryObject<EntityType<CraverEntity>> CRAVER = ENTITY_TYPES.register("craver", () ->
            EntityType.Builder.of(CraverEntity::new, MobCategory.MONSTER).sized(0.8F, 0.6F).clientTrackingRange(15).build(new ResourceLocation(FromSpores.MOD_ID, "craver").toString()));

    public static final RegistryObject<EntityType<SporeBombEntity>> SPORE_BOMB = ENTITY_TYPES.register("spore_bomb", () ->
            EntityType.Builder.<SporeBombEntity>of(SporeBombEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(new ResourceLocation(FromSpores.MOD_ID, "spore_bomb").toString()));

    @SubscribeEvent
    public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(CRAVER.get(), CraverEntity.registerAttributes().build());
    }
}
