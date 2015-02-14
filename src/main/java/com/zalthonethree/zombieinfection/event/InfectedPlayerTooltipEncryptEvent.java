package com.zalthonethree.zombieinfection.event;

import java.util.ArrayList;
import java.util.Collections;

import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.utility.TimeInfectedTrackingClient;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class InfectedPlayerTooltipEncryptEvent /*extends EntityDragon*/ {
	@SubscribeEvent(priority = EventPriority.LOWEST) public void encryptTooltip(ItemTooltipEvent event) {
		if (event.entityPlayer.isPotionActive(ZombieInfection.potionInfection)
		&& !event.entityPlayer.isPotionActive(ZombieInfection.potionCure)) {
			if (TimeInfectedTrackingClient.getSecondsInfected() > 60) {
				boolean doShuffle = true;
				if (ZombieInfectionAPI.getEncryptionExclusions().contains(event.itemStack.getUnlocalizedName())) doShuffle = false;
				if (ZombieInfectionAPI.getEncryptionSwitches().containsKey(event.itemStack.getUnlocalizedName())) doShuffle = false;
				if (doShuffle) {
					for (int i = 0; i < event.toolTip.size(); i ++) {
						String s = event.toolTip.get(i);
						ArrayList<String> chars = new ArrayList<String>();
						for (int i2 = 0; i2 < s.length(); i2 ++) {
							chars.add(s.substring(i2, i2 + 1));
						}
						Collections.shuffle(chars);
						String ns = "";
						for (String s2 : chars) {
							ns = ns + s2;
						}
						event.toolTip.set(i, ns);
					}
					event.toolTip.add(StatCollector.translateToLocal("tooltip.infectedeyes"));
				}
				if (ZombieInfectionAPI.getEncryptionSwitches().containsKey(event.itemStack.getUnlocalizedName())) {
					event.toolTip.set(0, StatCollector.translateToLocal(ZombieInfectionAPI.getEncryptionSwitches().get(event.itemStack.getUnlocalizedName())));
					if (ZombieInfectionAPI.getEncryptionSwitchesTooltips().containsKey(event.itemStack.getUnlocalizedName())) {
						for (String tT : ZombieInfectionAPI.getEncryptionSwitchesTooltips().get(event.itemStack.getUnlocalizedName())) {
							event.toolTip.add(StatCollector.translateToLocal(tT));
						}
					}
				}
			}
		}
	}
}