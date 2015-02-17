package com.zalthonethree.zombieinfection.client.render;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombieCow;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

@SideOnly(Side.CLIENT) public class RenderZombieCow extends RenderLiving/*, EntityDragon*/ {
	private static final ResourceLocation zombiecowTextures = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiecow.png");
	
	public RenderZombieCow(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}
	
	protected ResourceLocation getEntityTexture(EntityZombieCow cow) {
		return zombiecowTextures;
	}
	
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityZombieCow) entity);
	}
}