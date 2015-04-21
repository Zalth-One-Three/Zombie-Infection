package com.zalthonethree.zombieinfection.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.zalthonethree.zombieinfection.client.gui.bookpages.MainPage;

public class KnowledgeBook extends GuiScreen {
	public static KnowledgeBook instance = new KnowledgeBook();
	private static final ResourceLocation background = new ResourceLocation("zombieinfection", "textures/gui/book.png");
	private GuiScreen currentPage;
	public static int[] topLeft = new int[] {0, 0};
	
	@Override public final void initGui() {
		super.initGui();
		currentPage = MainPage.instance;
		instance = this;
	}
	
	@Override public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(background);
		int xSize = 96;
		int ySize = 128;
		int xPos = width / 2 - xSize / 2;
		int yPos = height / 2 - ySize / 2;
		topLeft = new int[] {xPos - (xSize / 2), yPos};
		drawScaledCustomSizeModalRect(xPos - (xSize / 2), yPos, 0, 0, 256, 256, xSize, ySize, 256, 256);
		drawScaledCustomSizeModalRect(xPos + (xSize / 2), yPos, 0, 0, 256, 256, xSize, ySize, 256, 256);
		currentPage.drawScreen(mouseX, mouseY, partialTicks);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override protected void actionPerformed(GuiButton button) {
		
	}
	
	@Override public boolean doesGuiPauseGame() { return false; }
}