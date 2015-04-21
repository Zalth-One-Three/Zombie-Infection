package com.zalthonethree.zombieinfection.client.gui.bookpages;

import java.awt.Color;

import com.zalthonethree.zombieinfection.client.gui.KnowledgeBook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class MainPage extends GuiScreen {
	public static final MainPage instance = new MainPage();
	
	@Override public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		int x = (KnowledgeBook.topLeft[0] * 2) + 8;
		int y = (KnowledgeBook.topLeft[1] * 2) + 8;
		Minecraft.getMinecraft().fontRendererObj.drawString("HALLO THIS IS DOGE :D :D :D", x, y, (new Color(0, 0, 0)).getRGB());
		GlStateManager.scale(2F, 2F, 2F);
	}
}