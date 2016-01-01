package com.zalthonethree.zombieinfection.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombieCow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT) public class RenderZombieCow extends RenderLiving {
	private static final ResourceLocation zombiecowTextures = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiecow.png");
	
	public RenderZombieCow(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}
	
	protected ResourceLocation getEntityTexture(EntityZombieCow cow) {
		return zombiecowTextures;
	}
	
	@Override protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityZombieCow) entity);
	}
}