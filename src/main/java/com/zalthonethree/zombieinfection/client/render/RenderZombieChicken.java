package com.zalthonethree.zombieinfection.client.render;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombieChicken;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT) public class RenderZombieChicken extends RenderLiving/*, EntityDragon*/ {
	private static final ResourceLocation zombiechickenTextures = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiechicken.png");
	
	public RenderZombieChicken(ModelBase model, float shadow) {
		super(model, shadow);
	}
	
	public void doRender(EntityZombieChicken entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		super.doRender((EntityLiving) entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
	
	protected ResourceLocation getEntityTexture(EntityZombieChicken entity) {
		return zombiechickenTextures;
	}
	
	protected float handleRotationFloat(EntityZombieChicken entity, float p_77044_2_) {
		float f1 = entity.field_70888_h + (entity.field_70886_e - entity.field_70888_h) * p_77044_2_;
		float f2 = entity.field_70884_g + (entity.destPos - entity.field_70884_g) * p_77044_2_;
		return (MathHelper.sin(f1) + 1.0F) * f2;
	}
	
	@Override public void doRender(EntityLiving entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender((EntityZombieChicken) entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
	
	@Override protected float handleRotationFloat(EntityLivingBase entity, float p_77044_2_) {
		return this.handleRotationFloat((EntityZombieChicken) entity, p_77044_2_);
	}
	
	@Override public void doRender(EntityLivingBase entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender((EntityZombieChicken) entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
	
	@Override protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityZombieChicken) entity);
	}
	
	@Override public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender((EntityZombieChicken) entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
}