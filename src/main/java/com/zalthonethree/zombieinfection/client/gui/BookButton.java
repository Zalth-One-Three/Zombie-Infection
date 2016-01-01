package com.zalthonethree.zombieinfection.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderItem;
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
	
	@Override public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			RenderItem.getInstance().renderItemAndEffectIntoGUI(fontrenderer, Minecraft.getMinecraft().getTextureManager(), this.buttonImage, this.xPosition, this.yPosition);
			this.mouseDragged(mc, mouseX, mouseY);
			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 0);
		}
	}
}