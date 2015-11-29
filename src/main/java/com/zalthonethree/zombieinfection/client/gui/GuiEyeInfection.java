package com.zalthonethree.zombieinfection.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.utility.TimeInfectedTrackingClient;

public class GuiEyeInfection extends Gui {
	private Minecraft minecraftInstance;
	private ResourceLocation uncracked = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/eyeinfection.png");
	private ResourceLocation cracked = new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/eyeinfectioncracked.png");
	
	public GuiEyeInfection(Minecraft MC) {
		super();
		minecraftInstance = MC;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL) public void onRender(RenderGameOverlayEvent.Pre event) {
		int timeInfected = TimeInfectedTrackingClient.getSecondsInfected();
		if (event.isCanceled() || event.type != ElementType.HOTBAR || timeInfected < 60) return;
		ScaledResolution sr = new ScaledResolution(minecraftInstance);
		
		GlStateManager.pushAttrib();
		GlStateManager.enableBlend();
		GlStateManager.depthMask(false);
		minecraftInstance.renderEngine.bindTexture(uncracked);
		drawModalRectWithCustomSizedTexture(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
		if (timeInfected >= 120) {
			minecraftInstance.renderEngine.bindTexture(cracked);
			drawModalRectWithCustomSizedTexture(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
		}
		GlStateManager.popAttrib();
	}
}