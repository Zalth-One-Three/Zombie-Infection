package com.zalthonethree.zombieinfection.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombieChicken;

@SideOnly(Side.CLIENT) public class RenderZombieChicken extends RenderLiving<EntityZombieChicken> {
	private static final ResourceLocation zombiechickenTextures = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiechicken.png");
	
	public RenderZombieChicken(RenderManager manager, ModelBase model, float shadow) {
		super(manager, model, shadow);
	}
	
	@Override protected ResourceLocation getEntityTexture(EntityZombieChicken entity) {
		return zombiechickenTextures;
	}
	
	@Override protected float handleRotationFloat(EntityZombieChicken livingBase, float partialTicks) {
		float f1 = livingBase.field_70888_h + (livingBase.field_70886_e - livingBase.field_70888_h) * partialTicks;
		float f2 = livingBase.field_70884_g + (livingBase.destPos - livingBase.field_70884_g) * partialTicks;
		return (MathHelper.sin(f1) + 1.0F) * f2;
	}
}