package com.linnett.hidden_caves.common.entity;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.entity.gingerbread_entity.GingerBreadModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModModelLayers {
    public static final ModelLayerLocation GINGERBREAD_MAN = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "gingerbread_man"), "main");

    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GINGERBREAD_MAN, GingerBreadModel::createBodyLayer);
    }
}
