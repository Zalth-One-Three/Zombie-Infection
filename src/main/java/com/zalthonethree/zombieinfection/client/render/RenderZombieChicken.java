package com.zalthonethree.zombieinfection.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.entity.EntityZombieChicken;

@SideOnly(Side.CLIENT) public class RenderZombieChicken extends RenderLiving<EntityZombieChicken> {
	private static final ResourceLocation zombiechickenTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/entity/zombiechicken.png");
	
	public RenderZombieChicken(RenderManager manager, ModelBase model, float shadow) {
		super(manager, model, shadow);
	}
	
	@Override protected ResourceLocation getEntityTexture(EntityZombieChicken entity) {
		return zombiechickenTexture;
	}
	
	@Override protected float handleRotationFloat(EntityZombieChicken livingBase, float partialTicks) {
		float f1 = livingBase.lastWingRotation + (livingBase.wingRotation - livingBase.lastWingRotation) * partialTicks;
		float f2 = livingBase.lastDestPos + (livingBase.destPos - livingBase.lastDestPos) * partialTicks;
		return (MathHelper.sin(f1) + 1.0F) * f2;
	}
}