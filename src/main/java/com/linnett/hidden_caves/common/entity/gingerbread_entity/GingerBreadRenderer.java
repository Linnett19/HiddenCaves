package com.linnett.hidden_caves.common.entity.gingerbread_entity;

import com.google.common.collect.Maps;
import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.entity.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class GingerBreadRenderer extends MobRenderer<GingerBreadEntity, GingerBreadModel<GingerBreadEntity>> {

    private static final Map<GingerVariant, ResourceLocation> LOCATION_MAP =
            Util.make(Maps.newEnumMap(GingerVariant.class), map
            -> {
                map.put(GingerVariant.NORMAL,ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "textures/entity/gingerbread_man/gingerbread_man.png"));
                map.put(GingerVariant.HAPPY,ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "textures/entity/gingerbread_man/gingerbread_man_1.png"));
    });

    public GingerBreadRenderer(EntityRendererProvider.Context context) {
        super(context, new GingerBreadModel<>(context.bakeLayer(ModModelLayers.GINGERBREAD_MAN)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(GingerBreadEntity pEntity) {
        return LOCATION_MAP.get(pEntity.getVariant());
    }

    @Override
    public void render(GingerBreadEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
            MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}