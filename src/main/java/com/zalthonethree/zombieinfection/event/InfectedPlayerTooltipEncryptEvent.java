package com.zalthonethree.zombieinfection.event;

import java.util.ArrayList;
import java.util.Collections;

import com.zalthonethree.zombieinfection.ZombieInfection;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class InfectedPlayerTooltipEncryptEvent /*extends EntityDragon*/ {
	@SubscribeEvent(priority = EventPriority.LOWEST) public void encryptTooltip(ItemTooltipEvent event) {
		if (event.entityPlayer.isPotionActive(ZombieInfection.potionInfection)) {
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
		}
	}
}