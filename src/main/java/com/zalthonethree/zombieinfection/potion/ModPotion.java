package com.zalthonethree.zombieinfection.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import com.zalthonethree.zombieinfection.Reference;

public class ModPotion {
	public static PotionZI potionCure;
	public static PotionZI potionInfection;
	
	public static void init() {
		potionCure = new PotionZI(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "cure"), new ResourceLocation(Reference.MOD_ID.toLowerCase(), "potion/cure.png"), false, 0xff00ff);
		potionInfection = new PotionZI(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "infection"), new ResourceLocation(Reference.MOD_ID.toLowerCase(), "potion/infection.png"), false, 0xff00ff);
		
		potionCure.setPotionName("potion.zombieinfection.cure");
		potionInfection.setPotionName("potion.zombieinfection.infection");
	}
	
	protected static class PotionZI extends Potion {
		private ResourceLocation icon;
		protected PotionZI(ResourceLocation location, ResourceLocation icon, boolean badEffect, int potionColor) {
			super(location, badEffect, potionColor);
			this.setPotionName("potion." + location.getResourcePath());
			this.icon = icon;
		}
		
		@Override public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
			mc.getTextureManager().bindTexture(this.icon);
			
			x += 6;
			y += 7;
			
			int width = 18;
			int height = width;
			
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer worldrenderer = tessellator.getWorldRenderer();
			worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
			worldrenderer.pos(x, y + height, 0.0D).tex(0, 16);
			worldrenderer.pos(x + width, y + height, 0.0D).tex(16, 16);
			worldrenderer.pos(x + width, y, 0.0D).tex(16, 0);
			worldrenderer.pos(x, y, 0.0D).tex(0, 0);
			tessellator.draw();
		}
	}
}