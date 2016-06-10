package waddles.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import waddles.entity.EntityPenguin;

@SideOnly(Side.CLIENT)
public class ModelPenguin extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer beak;
    public ModelRenderer wingRight;
    public ModelRenderer wingLeft;
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer tail;

    public ModelPenguin() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.beak = new ModelRenderer(this, 18, 0);
        this.beak.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.beak.addBox(-0.5F, -3.0F, -4.0F, 1, 2, 3, 0.0F);
        this.setRotateAngle(beak, 0.08726646259971647F, -0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.setRotationPoint(0.0F, 12.0F, 1.0F);
        this.body.addBox(-2.5F, 0.0F, -2.0F, 5, 11, 5, 0.0F);
        this.legRight = new ModelRenderer(this, 0, 25);
        this.legRight.setRotationPoint(-1.0F, 11.0F, 0.0F);
        this.legRight.addBox(-2.0F, 0.0F, -3.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(legRight, 0.0F, 0.2617993877991494F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.head.addBox(-2.0F, -4.0F, -2.0F, 4, 4, 5, 0.0F);
        this.tail = new ModelRenderer(this, 20, 20);
        this.tail.setRotationPoint(0.0F, 11.0F, 3.0F);
        this.tail.addBox(-1.5F, -1.0F, 0.0F, 3, 3, 1, 0.0F);
        this.setRotateAngle(tail, 1.2566370614359172F, 0.0F, 0.0F);
        this.wingRight = new ModelRenderer(this, 20, 10);
        this.wingRight.setRotationPoint(-2.5F, 1.0F, 0.0F);
        this.wingRight.addBox(-1.0F, 0.0F, -1.0F, 1, 7, 3, 0.0F);
        this.setRotateAngle(wingRight, 0.0F, 0.0F, 0.08726646259971647F);
        this.legLeft = new ModelRenderer(this, 0, 25);
        this.legLeft.mirror = true;
        this.legLeft.setRotationPoint(1.0F, 11.0F, 0.0F);
        this.legLeft.addBox(0.0F, 0.0F, -3.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(legLeft, 0.0F, -0.2617993877991494F, 0.0F);
        this.wingLeft = new ModelRenderer(this, 20, 10);
        this.wingLeft.mirror = true;
        this.wingLeft.setRotationPoint(2.5F, 1.0F, 0.0F);
        this.wingLeft.addBox(0.0F, 0.0F, -1.0F, 1, 7, 3, 0.0F);
        this.setRotateAngle(wingLeft, 0.0F, 0.0F, -0.08726646259971647F);
        this.head.addChild(this.beak);
        this.body.addChild(this.legRight);
        this.body.addChild(this.tail);
        this.body.addChild(this.wingRight);
        this.body.addChild(this.legLeft);
        this.body.addChild(this.wingLeft);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.body.render(scale);
        this.head.render(scale);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        EntityPenguin penguin = (EntityPenguin) entity;

        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.legRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.wingRight.rotateAngleZ = (float) -penguin.rotationWing / 2;
        this.wingLeft.rotateAngleZ = (float) penguin.rotationWing / 2;
        this.tail.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        /*if (entity.isInWater()) { //TODO Move to onLivingUpdate in EntityPenguin, otherwise it will be applied to ALL penguins in the world
            this.body.rotateAngleX = ((float) Math.PI / 2F);
            this.wingRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
            this.wingLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        }*/
    }
}