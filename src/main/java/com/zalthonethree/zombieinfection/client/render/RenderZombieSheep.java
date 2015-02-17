package com.zalthonethree.zombieinfection.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombieSheep;

@SideOnly(Side.CLIENT) public class RenderZombieSheep extends RenderLiving/*, EntityDragon*/ {
	private static final ResourceLocation zombiesheepTextures = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiesheep.png");
	
	public RenderZombieSheep(RenderManager manager, ModelBase model, float shadow) {
		super(manager, model, shadow);
	}
	
	protected ResourceLocation getEntityTexture(EntityZombieSheep entity) {
		return zombiesheepTextures;
	}
	
	@Override protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityZombieSheep) entity);
	}
}