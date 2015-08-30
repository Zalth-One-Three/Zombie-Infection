package com.zalthonethree.zombieinfection.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelZombieDragonEgg extends ModelBase {
    public ModelRenderer Layer3;
    public ModelRenderer Layer1;
    public ModelRenderer Layer7;
    public ModelRenderer Layer2;
    public ModelRenderer Layer4;
    public ModelRenderer Layer5;
    public ModelRenderer Layer6;

    public ModelZombieDragonEgg() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Layer2 = new ModelRenderer(this, 0, 0);
        this.Layer2.setRotationPoint(-12.0F, 18.0F, -12.0F);
        this.Layer2.addBox(0.0F, 0.0F, 0.0F, 24, 4, 24, 0.0F);
        this.Layer3 = new ModelRenderer(this, 0, 0);
        this.Layer3.setRotationPoint(-14.0F, 8.0F, -14.0F);
        this.Layer3.addBox(0.0F, 0.0F, 0.0F, 28, 10, 28, 0.0F);
        this.Layer6 = new ModelRenderer(this, 0, 0);
        this.Layer6.setRotationPoint(-6.0F, -6.0F, -6.0F);
        this.Layer6.addBox(0.0F, 0.0F, 0.0F, 12, 4, 12, 0.0F);
        this.Layer4 = new ModelRenderer(this, 0, 0);
        this.Layer4.setRotationPoint(-12.0F, 2.0F, -12.0F);
        this.Layer4.addBox(0.0F, 0.0F, 0.0F, 24, 6, 24, 0.0F);
        this.Layer7 = new ModelRenderer(this, 0, 0);
        this.Layer7.setRotationPoint(-4.0F, -8.0F, -4.0F);
        this.Layer7.addBox(0.0F, 0.0F, 0.0F, 8, 2, 8, 0.0F);
        this.Layer1 = new ModelRenderer(this, 0, 0);
        this.Layer1.setRotationPoint(-10.0F, 22.0F, -10.0F);
        this.Layer1.addBox(0.0F, 0.0F, 0.0F, 20, 2, 20, 0.0F);
        this.Layer5 = new ModelRenderer(this, 0, 0);
        this.Layer5.setRotationPoint(-10.0F, -2.0F, -10.0F);
        this.Layer5.addBox(0.0F, 0.0F, 0.0F, 20, 4, 20, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Layer2.render(f5);
        this.Layer3.render(f5);
        this.Layer6.render(f5);
        this.Layer4.render(f5);
        this.Layer7.render(f5);
        this.Layer1.render(f5);
        this.Layer5.render(f5);
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