package com.zalthonethree.zombieinfection.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombieCow;

@SideOnly(Side.CLIENT) public class RenderZombieCow extends RenderLiving<EntityZombieCow> {
	private static final ResourceLocation zombiecowTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiecow.png");
	
	public RenderZombieCow(RenderManager manager, ModelBase model, float shadowSize) {
		super(manager, model, shadowSize);
	}
	
	@Override protected ResourceLocation getEntityTexture(EntityZombieCow entity) {
		return zombiecowTexture;
	}
}