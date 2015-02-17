package com.zalthonethree.zombieinfection.client.render;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombiePig;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT) public class RenderZombiePig extends RenderLiving/*, EntityDragon*/ {
	private static final ResourceLocation zombiepigTextures = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiepig.png");
	
	public RenderZombiePig(ModelBase model, float shadow) {
		super(model, shadow);
	}
	
	protected ResourceLocation getEntityTexture(EntityZombiePig entity) {
		return zombiepigTextures;
	}
	
	@Override protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityZombiePig) entity);
	}
}