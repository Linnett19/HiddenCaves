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

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "grasshopper"), "main");
    private final ModelPart gammaroach;
    private final ModelPart bone;
    private final ModelPart gammaroach_head;

    public GrasshopperModel(ModelPart root) {
        this.gammaroach = root.getChild("gammaroach");
        this.bone = this.gammaroach.getChild("bone");
        this.gammaroach_head = this.bone.getChild("gammaroach_head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition gammaroach = partdefinition.addOrReplaceChild("gammaroach", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, -7.0F));

        PartDefinition bone = gammaroach.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 85).addBox(-6.5F, -2.75F, -8.5F, 13.0F, 5.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(1, 1).addBox(-5.0F, -2.25F, -8.0F, 10.0F, 5.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, 7.0F));

        PartDefinition gammaroach_head = bone.addOrReplaceChild("gammaroach_head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.25F, -3.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -8.5F));

        PartDefinition bone4 = gammaroach_head.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -0.5F, -1.5F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.25F, -1.5F));

        PartDefinition gammaroach_left_attenae = gammaroach_head.addOrReplaceChild("gammaroach_left_attenae", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.2F, 0.0F));

        PartDefinition bone2 = gammaroach_left_attenae.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(4.0F, -1.0F, -2.0F));

        PartDefinition cube_r1 = bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(10, 8).addBox(0.0F, 0.0F, -16.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5312F, -0.0184F, 0.436F));

        PartDefinition bone3 = gammaroach_left_attenae.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-2.0F, -1.0F, -2.0F));

        PartDefinition cube_r2 = bone3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(10, 8).mirror().addBox(0.0F, 0.0F, -16.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5312F, 0.0184F, -0.436F));

        PartDefinition gammaroach_left_leg1 = gammaroach.addOrReplaceChild("gammaroach_left_leg1", CubeListBuilder.create().texOffs(58, 12).addBox(-0.4258F, 0.0F, 0.8187F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, 3.0F, 0.0F, 1.3526F, 0.0F));

        PartDefinition gammaroach_right_leg1 = gammaroach.addOrReplaceChild("gammaroach_right_leg1", CubeListBuilder.create().texOffs(58, 12).mirror().addBox(-8.1566F, 0.0F, -0.5373F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 3.0F, 3.0F, 0.0F, -1.3526F, 0.0F));

        PartDefinition gammaroach_left_leg2 = gammaroach.addOrReplaceChild("gammaroach_left_leg2", CubeListBuilder.create().texOffs(49, 1).addBox(0.0F, 0.0F, 0.0F, 12.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 1.0F, 5.0F));

        PartDefinition gammaroach_right_leg2 = gammaroach.addOrReplaceChild("gammaroach_right_leg2", CubeListBuilder.create().texOffs(49, 1).mirror().addBox(-12.0F, 0.0F, 0.0F, 12.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 1.0F, 5.0F));

        PartDefinition gammaroach_right_leg4 = gammaroach.addOrReplaceChild("gammaroach_right_leg4", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 1.0F, 12.0F, 0.0F, 1.7017F, 0.0F));

        PartDefinition cube_r3 = gammaroach_right_leg4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 58).mirror().addBox(-19.1566F, 0.0F, 0.5373F, 20.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

        PartDefinition gammaroach_right_leg3 = gammaroach.addOrReplaceChild("gammaroach_right_leg3", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 1.0F, 12.0F, 0.0F, 0.8901F, 0.0F));

        PartDefinition cube_r4 = gammaroach_right_leg3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 58).mirror().addBox(-19.1566F, 0.0F, 0.5373F, 20.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

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

        this.gammaroach_head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.gammaroach_head.xRot = pHeadPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        gammaroach.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return gammaroach;
    }
}
