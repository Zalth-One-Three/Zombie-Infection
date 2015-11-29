package com.zalthonethree.zombieinfection.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.zend.EntityEnderZombie;

@SideOnly(Side.CLIENT)
public class RenderEnderZombie extends RenderLiving<EntityEnderZombie> {
	private static final ResourceLocation enderZombie = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/enderzombie.png");
	
	public RenderEnderZombie(RenderManager manager, ModelBase model, float shadow) {
		super(manager, model, shadow);
	}
	
	@Override protected ResourceLocation getEntityTexture(EntityEnderZombie entity) {
		return enderZombie;
	}
}