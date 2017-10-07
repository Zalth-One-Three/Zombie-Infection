package com.zalthonethree.zombieinfection.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class ModPotion {
	public static class PotionZI extends Potion {
		private ResourceLocation icon;
		
		public PotionZI(ResourceLocation location, ResourceLocation icon, boolean badEffect, int potionColor) {
			super(badEffect, potionColor);
			this.setPotionName("potion.zombieinfection." + location.getResourcePath());
			this.setRegistryName(location);
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