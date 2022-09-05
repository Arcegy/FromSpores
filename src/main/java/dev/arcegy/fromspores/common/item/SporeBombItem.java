package dev.arcegy.fromspores.common.item;

import dev.arcegy.fromspores.common.entity.SporeBombEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SporeBombItem extends Item {
    public SporeBombItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.getCooldowns().addCooldown(this, 20);

        if (!world.isClientSide) {
            SporeBombEntity sporeBombEntity = new SporeBombEntity(world, player);
            sporeBombEntity.setItem(itemStack);
            sporeBombEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), -15, 1, 0.5F);
            world.addFreshEntity(sporeBombEntity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));

        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}
