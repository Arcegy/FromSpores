package dev.arcegy.fromspores.core.tags;

import dev.arcegy.fromspores.FromSpores;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class FromSporesBlockTags {
    public static final TagKey<Block> CRAVERS_SPAWNABLE_ON = makeTag("cravers_spawnable_on");

    private static TagKey<Block> makeTag(String id) {
        return BlockTags.create(new ResourceLocation(FromSpores.MOD_ID, id));
    }
}
