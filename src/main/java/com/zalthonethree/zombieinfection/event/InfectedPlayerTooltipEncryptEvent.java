package com.zalthonethree.zombieinfection.event;

import java.util.ArrayList;
import java.util.Collections;

import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.init.ModRegistry;
import com.zalthonethree.zombieinfection.utility.TimeInfectedTrackingClient;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InfectedPlayerTooltipEncryptEvent {
	@SubscribeEvent(priority = EventPriority.LOWEST) public void encryptTooltip(ItemTooltipEvent event) {
		if (event.getEntityPlayer() == null) return;
		if (event.getEntityPlayer().isPotionActive(ModRegistry.POTION_INFECTION)
		&& !event.getEntityPlayer().isPotionActive(ModRegistry.POTION_CURE)) {
			if (TimeInfectedTrackingClient.getSecondsInfected() > 60) {
				boolean doShuffle = true;
				if (ZombieInfectionAPI.getEncryptionExclusions().contains(event.getItemStack().getUnlocalizedName())) doShuffle = false;
				if (ZombieInfectionAPI.getEncryptionSwitches().containsKey(event.getItemStack().getUnlocalizedName())) doShuffle = false;
				if (doShuffle) {
					for (int i = 0; i < event.getToolTip().size(); i ++) {
						String s = event.getToolTip().get(i);
						ArrayList<String> chars = new ArrayList<String>();
						for (int i2 = 0; i2 < s.length(); i2 ++) {
							chars.add(s.substring(i2, i2 + 1));
						}
						Collections.shuffle(chars);
						String ns = "";
						for (String s2 : chars) {
							ns = ns + s2;
						}
						event.getToolTip().set(i, ns);
					}
					event.getToolTip().add(I18n.format("tooltip.infectedeyes"));
				}
				if (ZombieInfectionAPI.getEncryptionSwitches().containsKey(event.getItemStack().getUnlocalizedName())) {
					event.getToolTip().set(0, I18n.format(ZombieInfectionAPI.getEncryptionSwitches().get(event.getItemStack().getUnlocalizedName())));
					if (ZombieInfectionAPI.getEncryptionSwitchesTooltips().containsKey(event.getItemStack().getUnlocalizedName())) {
						for (String tT : ZombieInfectionAPI.getEncryptionSwitchesTooltips().get(event.getItemStack().getUnlocalizedName())) {
							event.getToolTip().add(I18n.format(tT));
						}
					}
				}
			}
		}
	}
}