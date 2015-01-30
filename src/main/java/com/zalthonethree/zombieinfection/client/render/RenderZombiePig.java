package com.zalthonethree.zombieinfection.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombiePig;

@SideOnly(Side.CLIENT) public class RenderZombiePig extends RenderLiving<EntityZombiePig> {
	private static final ResourceLocation zombiepigTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiepig.png");
	
	public RenderZombiePig(RenderManager manager, ModelBase model, float shadow) {
		super(manager, model, shadow);
	}
	
	@Override protected ResourceLocation getEntityTexture(EntityZombiePig entity) {
		return zombiepigTexture;
	}
}