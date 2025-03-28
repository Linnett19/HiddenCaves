package com.linnett.hidden_caves.common.entity.client;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.entity.custom.GrasshopperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GrasshopperModel<T extends GrasshopperEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "grasshopper"), "main");
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;

    public GrasshopperModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, -7.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 85).addBox(-6.5F, -2.75F, -8.5F, 13.0F, 5.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-5.0F, -2.25F, -8.0F, 10.0F, 5.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 7.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.25F, -3.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -8.5F));

        PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -0.5F, -1.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.25F, -1.5F));

        PartDefinition attenae = head.addOrReplaceChild("attenae", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.2F, 0.0F));

        PartDefinition left = attenae.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(4.0F, -1.0F, -2.0F));

        PartDefinition cube_r1 = left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(10, 8).addBox(0.0F, 0.0F, -16.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5312F, -0.0184F, 0.436F));

        PartDefinition right = attenae.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.0F, -2.0F));

        PartDefinition cube_r2 = right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(10, 8).mirror().addBox(0.0F, 0.0F, -16.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5312F, 0.0184F, -0.436F));

        PartDefinition left_leg1 = root.addOrReplaceChild("left_leg1", CubeListBuilder.create().texOffs(58, 12).addBox(-0.4258F, 0.0F, 0.8187F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, 3.0F, 0.0F, 1.3526F, 0.0F));

        PartDefinition right_leg1 = root.addOrReplaceChild("right_leg1", CubeListBuilder.create().texOffs(58, 12).mirror().addBox(-8.1566F, 0.0F, -0.5373F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 3.0F, 3.0F, 0.0F, -1.3526F, 0.0F));

        PartDefinition left_leg2 = root.addOrReplaceChild("left_leg2", CubeListBuilder.create().texOffs(49, 1).addBox(0.0F, 0.0F, 0.0F, 12.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 1.0F, 5.0F));
        PartDefinition right_leg2 = root.addOrReplaceChild("right_leg2", CubeListBuilder.create().texOffs(49, 1).mirror().addBox(-12.0F, 0.0F, 0.0F, 12.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 1.0F, 5.0F));

        PartDefinition right_leg4 = root.addOrReplaceChild("right_leg4", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 1.0F, 12.0F, 0.0F, 1.7017F, 0.0F));

        PartDefinition cube_r3 = right_leg4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 58).mirror().addBox(-19.1566F, 0.0F, 0.5373F, 20.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

        PartDefinition right_leg3 = root.addOrReplaceChild("right_leg3", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 1.0F, 12.0F, 0.0F, 0.8901F, 0.0F));

        PartDefinition cube_r4 = right_leg3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 58).mirror().addBox(-19.1566F, 0.0F, 0.5373F, 20.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(GrasshopperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(GrasshopperAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, GrasshopperAnimations.IDLE, ageInTicks, 2f);
    }

    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return root;
    }
}