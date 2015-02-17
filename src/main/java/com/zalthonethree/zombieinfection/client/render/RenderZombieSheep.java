package com.zalthonethree.zombieinfection.client.render;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombieSheep;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT) public class RenderZombieSheep extends RenderLiving/*, EntityDragon*/ {
	private static final ResourceLocation zombiesheepTextures = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiesheep.png");
	
	public RenderZombieSheep(ModelBase model, float shadow) {
		super(model, shadow);
	}
	
	protected ResourceLocation getEntityTexture(EntityZombieSheep entity) {
		return zombiesheepTextures;
	}
	
	@Override protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityZombieSheep) entity);
	}
}