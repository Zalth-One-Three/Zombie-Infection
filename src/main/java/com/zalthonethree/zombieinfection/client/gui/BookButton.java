package com.zalthonethree.zombieinfection.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;

public class BookButton extends GuiButton {
	private ItemStack buttonImage;
	
	public BookButton(int buttonId, ItemStack image, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
		this.buttonImage = image;
	}
	
	public BookButton(int buttonId, ItemStack image, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.buttonImage = image;
	}
	
	@Override public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(this.buttonImage, this.x, this.y);
			this.mouseDragged(mc, mouseX, mouseY);
			this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, 0);
		}
	}
}