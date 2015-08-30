package com.zalthonethree.zombieinfection.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.zend.EntityEnderZombie;

public class RenderEnderZombie extends RenderLiving {
	private static final ResourceLocation enderZombie = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/enderzombie.png"); // Enderman form
	
	public RenderEnderZombie(RenderManager manager, ModelBase model, float shadow) {
		super(manager, model, shadow);
	}
	
	public void doRender(EntityEnderZombie entity, double x, double y, double z, float yaw, float partialTicks) {
		super.doRender((EntityLiving) entity, x, y, z, yaw, partialTicks);
	}
	
	protected ResourceLocation getEntityTexture(EntityEnderZombie entity) {
		return enderZombie;
	}
	
	@Override public void doRender(EntityLiving entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender((EntityEnderZombie) entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
	
	@Override public void doRender(EntityLivingBase entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender((EntityEnderZombie) entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
	
	@Override protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityEnderZombie) entity);
	}
	
	@Override public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender((EntityEnderZombie) entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
}