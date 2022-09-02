package dev.arcegy.fromspores.core.misc;

import dev.arcegy.fromspores.common.entity.CraverEntity;
import dev.arcegy.fromspores.core.registry.FromSporesEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class FromSporesSpawns {
    public static void registerSpawns() {
        registerSpawn(FromSporesEntityTypes.CRAVER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CraverEntity::checkCraverSpawnRules);
    }

    public static <T extends Mob> void registerSpawn(EntityType<T> entityType, SpawnPlacements.Type decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> predicate) {
        SpawnPlacements.register(entityType, decoratorType, heightMapType, predicate);
    }
}
