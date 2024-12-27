package com.linnett.hidden_caves.common.entity.gingerbread_entity;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class GingerBreadModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("modid", "gingerbreadmodel"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart face;
	private final ModelPart eye_r;
	private final ModelPart eye_l;
	private final ModelPart arn_l;
	private final ModelPart arm_r;
	private final ModelPart leg_r;
	private final ModelPart leg_l;

	public GingerBreadModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.face = this.head.getChild("face");
		this.eye_r = this.face.getChild("eye_r");
		this.eye_l = this.face.getChild("eye_l");
		this.arn_l = this.body.getChild("arn_l");
		this.arm_r = this.body.getChild("arm_r");
		this.leg_r = this.body.getChild("leg_r");
		this.leg_l = this.body.getChild("leg_l");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-0.5F, 17.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 7).addBox(-2.5F, -1.425F, -0.4F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-2.5F, -1.425F, -0.6F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.5F, -0.6F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.9375F, -0.4625F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 21).addBox(-0.5F, -6.1125F, -0.5375F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.4875F, 0.0625F));

		PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create(), PartPose.offset(-0.5F, -0.0125F, 0.5125F));

		PartDefinition eye_r = face.addOrReplaceChild("eye_r", CubeListBuilder.create().texOffs(25, 0).addBox(-0.5F, -0.525F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.525F, -2.4F, -0.5F));

		PartDefinition eye_l = face.addOrReplaceChild("eye_l", CubeListBuilder.create().texOffs(25, 0).mirror().addBox(-0.5F, -0.525F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.525F, -2.4F, -0.5F));

		PartDefinition arn_l = body.addOrReplaceChild("arn_l", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -0.925F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -0.5F, 0.6F));

		PartDefinition arm_r = body.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-3.0F, -0.925F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, -0.5F, 0.6F));

		PartDefinition leg_r = body.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(14, 5).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.5F, 2.5F, 0.6F));

		PartDefinition leg_l = body.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(14, 5).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.5F, 0.6F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);

		this.animateWalk(GingerBreadAnimation.walking, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((GingerBreadEntity) entity).idleAnimationState, GingerBreadAnimation.idle, ageInTicks, 1f);
		this.animate(((GingerBreadEntity) entity).runAnimationState, GingerBreadAnimation.run, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public ModelPart root() {
		return root;
	}
}