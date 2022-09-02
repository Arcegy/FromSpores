package dev.arcegy.fromspores.client.entity.renderer.entity;

import dev.arcegy.fromspores.FromSpores;
import dev.arcegy.fromspores.client.entity.model.CraverModel;
import dev.arcegy.fromspores.common.entity.CraverEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CraverRenderer extends MobRenderer<CraverEntity, CraverModel<CraverEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(FromSpores.MOD_ID, "textures/entity/craver/craver.png");

    public CraverRenderer(EntityRendererProvider.Context context) {
        super(context, new CraverModel<>(context.bakeLayer(CraverModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(CraverEntity craverEntity) {
        return TEXTURE;
    }
}
