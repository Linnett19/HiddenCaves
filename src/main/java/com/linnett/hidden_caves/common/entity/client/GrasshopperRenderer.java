package com.linnett.hidden_caves.common.entity.client;

import com.linnett.hidden_caves.common.entity.custom.GrasshopperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrasshopperRenderer extends MobRenderer<GrasshopperEntity, GrasshopperModel<GrasshopperEntity>> {

    public GrasshopperRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GrasshopperModel<>(pContext.bakeLayer(GrasshopperModel.LAYER_LOCATION)), 0.85f);
    }


    @Override
    public void render(GrasshopperEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            pPoseStack.scale(1f, 1f, 1f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(GrasshopperEntity entity) {
        return ResourceLocation.fromNamespaceAndPath("hidden_caves", "textures/entity/grasshopper/grasshopper.png");
    }


}
