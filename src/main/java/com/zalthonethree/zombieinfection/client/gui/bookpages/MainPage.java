package com.zalthonethree.zombieinfection.client.gui.bookpages;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.zalthonethree.zombieinfection.client.gui.BookButton;
import com.zalthonethree.zombieinfection.client.gui.KnowledgeBook;

public class MainPage extends GuiScreen {
	public static final MainPage instance = new MainPage();
	
	public MainPage() {
		this.buttonList.add(new BookButton(100, new ItemStack(Items.rotten_flesh), 100, 200, 100, 50, "The Infection"));
	}
	
	@Override public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		int x = (KnowledgeBook.topLeft[0] * 2) + 8;
		int y = (KnowledgeBook.topLeft[1] * 2) + 8;
		Minecraft.getMinecraft().fontRendererObj.drawString("HALLO THIS IS DOGE :D :D :D", x, y, (new Color(0, 0, 0)).getRGB());
		// Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(Items.rotten_flesh), x, y + 8);
		GlStateManager.scale(2F, 2F, 2F);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}