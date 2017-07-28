package com.zalthonethree.zombieinfection.potion;

import com.zalthonethree.zombieinfection.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

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
			super(badEffect, potionColor);
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
			BufferBuilder bufferBuffer = tessellator.getBuffer();
			bufferBuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
			bufferBuffer.pos(x, y + height, 0.0D).tex(0, 16);
			bufferBuffer.pos(x + width, y + height, 0.0D).tex(16, 16);
			bufferBuffer.pos(x + width, y, 0.0D).tex(16, 0);
			bufferBuffer.pos(x, y, 0.0D).tex(0, 0);
			tessellator.draw();
		}
	}
}