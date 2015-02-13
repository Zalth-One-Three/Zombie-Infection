package com.zalthonethree.zombieinfection.event;

import java.util.Calendar;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.api.CustomInfectionEffect;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.FoodTracking;
import com.zalthonethree.zombieinfection.utility.TimeInfectedTracking;
import com.zalthonethree.zombieinfection.utility.Utilities;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class InfectedPlayerUpdateEvent /*extends EntityDragon*/ {
	private static int lastSecond = 0;
	
	@SuppressWarnings("rawtypes") @SubscribeEvent public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (player.isPotionActive(ZombieInfection.potionInfection) && !player.isPotionActive(ZombieInfection.potionCure)) {
			int timeInfected = TimeInfectedTracking.getSecondsInfected(player);
			for (CustomInfectionEffect customEffect : ZombieInfectionAPI.getCustomInfectionEffects()) {
				customEffect.run(player, timeInfected);
			}
			
			player.addPotionEffect(PotionHelper.createInfection(timeInfected < 60 ? 0 : 1));
			if (ConfigurationHandler.enableSlowness()) player.addPotionEffect(PotionHelper.createSlowness(timeInfected < 60 ? 0 : 1));
			if (ConfigurationHandler.enableHunger() && timeInfected >= 20) player.addPotionEffect(PotionHelper.createHunger(timeInfected < 80 ? 0 : 1));
			if (ConfigurationHandler.enableMiningFatigue() && timeInfected >= 40) player.addPotionEffect(PotionHelper.createMiningFatigue(timeInfected < 100 ? 0 : 1));
			if (ConfigurationHandler.enableWeakness() && timeInfected >= 60) player.addPotionEffect(PotionHelper.createWeakness(timeInfected < 120 ? 0 : 1));
			if (ConfigurationHandler.enableWither() && timeInfected >= 480) player.addPotionEffect(PotionHelper.createWither(0));
			
			if (player.getFoodStats().getFoodLevel() > FoodTracking.get(player)) {
				player.getFoodStats().addStats(FoodTracking.get(player) - player.getFoodStats().getFoodLevel(), 0);
			}
			
			FoodTracking.put(player);
			
			if (Utilities.isServerSide()) {
				int curSecond = Calendar.getInstance().get(Calendar.SECOND);
				if (curSecond != lastSecond) {
					lastSecond = curSecond;
					TimeInfectedTracking.update(player);
				}
				
				if (player.worldObj.canBlockSeeTheSky((int) player.posX, (int) player.posY, (int) player.posZ)
				&& player.worldObj.isDaytime()
				&& !player.worldObj.isRaining()
				&& !player.worldObj.isThundering()
				&& timeInfected >= 80
				&& player.inventory.armorInventory[3] == null) {
					player.setFire(1);
				}
				
				if (!FMLCommonHandler.instance().getMinecraftServerInstance().isPVPEnabled() && ConfigurationHandler.getSpreadEnabled()) {
					Iterator players = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.iterator();
					
					while (players.hasNext()) {
						Object thing = players.next();
						if (thing instanceof EntityPlayer) {
							EntityPlayer anotherPlayer = (EntityPlayer) thing;
							if (anotherPlayer.getDistanceToEntity(player) < ConfigurationHandler.getSpreadDistance()) {
								if (anotherPlayer.getUniqueID() != player.getUniqueID()) {
									if (!anotherPlayer.isPotionActive(ZombieInfection.potionInfection)) {
										anotherPlayer.addChatMessage(new ChatComponentTranslation("zombieinfection.chat.playerinfected"));
										anotherPlayer.addPotionEffect(PotionHelper.createInfection(0));
									}
								}
							}
						}
					}
				}
			}
		} else {
			if (Utilities.isServerSide()) TimeInfectedTracking.remove(player);
			FoodTracking.remove(player);
		}
	}
}
