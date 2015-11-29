package com.zalthonethree.zombieinfection.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelZombieDragonEgg extends ModelBase {
	public ModelRenderer layer1;
	public ModelRenderer layer2;
    public ModelRenderer layer3;
    public ModelRenderer layer4;
    public ModelRenderer layer5;
    public ModelRenderer layer6;
    public ModelRenderer layer7;

    public ModelZombieDragonEgg() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.layer2 = new ModelRenderer(this, 0, 0);
        this.layer2.setRotationPoint(-12.0F, 18.0F, -12.0F);
        this.layer2.addBox(0.0F, 0.0F, 0.0F, 24, 4, 24, 0.0F);
        this.layer3 = new ModelRenderer(this, 0, 0);
        this.layer3.setRotationPoint(-14.0F, 8.0F, -14.0F);
        this.layer3.addBox(0.0F, 0.0F, 0.0F, 28, 10, 28, 0.0F);
        this.layer6 = new ModelRenderer(this, 0, 0);
        this.layer6.setRotationPoint(-6.0F, -6.0F, -6.0F);
        this.layer6.addBox(0.0F, 0.0F, 0.0F, 12, 4, 12, 0.0F);
        this.layer4 = new ModelRenderer(this, 0, 0);
        this.layer4.setRotationPoint(-12.0F, 2.0F, -12.0F);
        this.layer4.addBox(0.0F, 0.0F, 0.0F, 24, 6, 24, 0.0F);
        this.layer7 = new ModelRenderer(this, 0, 0);
        this.layer7.setRotationPoint(-4.0F, -8.0F, -4.0F);
        this.layer7.addBox(0.0F, 0.0F, 0.0F, 8, 2, 8, 0.0F);
        this.layer1 = new ModelRenderer(this, 0, 0);
        this.layer1.setRotationPoint(-10.0F, 22.0F, -10.0F);
        this.layer1.addBox(0.0F, 0.0F, 0.0F, 20, 2, 20, 0.0F);
        this.layer5 = new ModelRenderer(this, 0, 0);
        this.layer5.setRotationPoint(-10.0F, -2.0F, -10.0F);
        this.layer5.addBox(0.0F, 0.0F, 0.0F, 20, 4, 20, 0.0F);
    }

	@Override public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.layer2.render(f5);
		this.layer3.render(f5);
		this.layer6.render(f5);
		this.layer4.render(f5);
		this.layer7.render(f5);
		this.layer1.render(f5);
		this.layer5.render(f5);
	}

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}