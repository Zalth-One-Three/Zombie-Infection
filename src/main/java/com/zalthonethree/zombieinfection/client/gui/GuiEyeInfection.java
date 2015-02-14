package com.zalthonethree.zombieinfection.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.utility.TimeInfectedTrackingClient;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GuiEyeInfection extends Gui {
	private Minecraft minecraftInstance;
	
	public GuiEyeInfection(Minecraft MC) {
		super();
		minecraftInstance = MC;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL) public void onRender(RenderGameOverlayEvent.Post event) {
		if (event.isCanceled() || event.type != ElementType.EXPERIENCE || TimeInfectedTrackingClient.getSecondsInfected() < 60) return;
		ScaledResolution sr = new ScaledResolution(minecraftInstance, minecraftInstance.displayWidth, minecraftInstance.displayHeight);
		
		minecraftInstance.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/gui/eyeinfection.png"));
		drawModalRectWithCustomSizedTexture(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
	}
}