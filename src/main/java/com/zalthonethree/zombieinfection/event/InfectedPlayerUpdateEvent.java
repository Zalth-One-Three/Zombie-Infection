package com.zalthonethree.zombieinfection.event;

import java.util.Calendar;
import java.util.Iterator;

import com.zalthonethree.zombieinfection.api.ICustomInfectionEffect;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.handler.PacketHandler;
import com.zalthonethree.zombieinfection.init.ModRegistry;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.FoodTracking;
import com.zalthonethree.zombieinfection.utility.TimeInfectedTracking;
import com.zalthonethree.zombieinfection.utility.Utilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.security.auth.login.Configuration;

public class InfectedPlayerUpdateEvent {
	private static int lastSecond = 0;
	
	@SubscribeEvent public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
		if (Utilities.isServerSide()) {
			EntityPlayer player = event.player;
			if (player.isPotionActive(ModRegistry.POTION_INFECTION) && !player.isPotionActive(ModRegistry.POTION_CURE)) {
				int timeInfected = TimeInfectedTracking.getSecondsInfected(player);
				for (ICustomInfectionEffect customEffect : ZombieInfectionAPI.getCustomInfectionEffects()) {
					customEffect.run(player, timeInfected);
				}
				
				player.addPotionEffect(PotionHelper.createInfection(timeInfected < 60 ? 1 : 1));
				if (ConfigurationHandler.enableHunger() && timeInfected >= ConfigurationHandler.getHungerTime()) player.addPotionEffect(PotionHelper.createHunger(timeInfected < ConfigurationHandler.getUpgradedHungerTime() ? 0 : ConfigurationHandler.enableUpgradedHunger()));
				if (ConfigurationHandler.enableMiningFatigue() && timeInfected >= ConfigurationHandler.getMiningFatigueTime()) player.addPotionEffect(PotionHelper.createMiningFatigue(timeInfected < ConfigurationHandler.getUpgradedMiningFatigueTime() ? 0 : ConfigurationHandler.enableUpgradedMiningFatigue()));
				if (ConfigurationHandler.enableSlowness() && timeInfected >= ConfigurationHandler.getSlownessTime()) player.addPotionEffect(PotionHelper.createSlowness(timeInfected < ConfigurationHandler.getUpgradedSlownessTime() ? 0 : ConfigurationHandler.enableUpgradedSlowness()));
				if (ConfigurationHandler.enableWeakness() && timeInfected >= ConfigurationHandler.getWeaknessTime()) player.addPotionEffect(PotionHelper.createWeakness(timeInfected < ConfigurationHandler.getUpgradedWeaknessTime() ? 0 : ConfigurationHandler.enableUpgradedWeakness()));
				if (ConfigurationHandler.enableWither() && timeInfected >= 480) player.addPotionEffect(PotionHelper.createWither(0));
				
				if (player.getFoodStats().getFoodLevel() > FoodTracking.get(player)) {
					player.getFoodStats().addStats(FoodTracking.get(player) - player.getFoodStats().getFoodLevel(), 0);
					EntityPlayerMP playerMP = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(player.getName());
					PacketHandler.INSTANCE.sendTo(PacketHandler.getFoodChangePacket(player.getFoodStats().getFoodLevel(), player.getFoodStats().getSaturationLevel()), playerMP);
				}
				
				FoodTracking.put(player);
				
				int curSecond = Calendar.getInstance().get(Calendar.SECOND);
				if (curSecond != lastSecond) {
					lastSecond = curSecond;
					TimeInfectedTracking.update(player);
				}
				
				if (player.world.canBlockSeeSky(new BlockPos((int) player.posX, (int) player.posY, (int) player.posZ))
						&& player.world.isDaytime()
						&& !player.world.isRaining()
						&& !player.world.isThundering()
						&& ConfigurationHandler.enableBurning()
						&& timeInfected >= ConfigurationHandler.getBurningTime()
						&& player.inventory.armorInventory.get(3).isEmpty()) {
					player.setFire(1);
				}
				
				if (!FMLCommonHandler.instance().getMinecraftServerInstance().isPVPEnabled() && ConfigurationHandler.getSpreadEnabled()) {
					Iterator<EntityPlayerMP> players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers().iterator();
					
					while (players.hasNext()) {
						Object thing = players.next();
						if (thing instanceof EntityPlayer) {
							EntityPlayer anotherPlayer = (EntityPlayer) thing;
							if (anotherPlayer.getDistance(player) < ConfigurationHandler.getSpreadDistance()) {
								if (anotherPlayer.getUniqueID() != player.getUniqueID()) {
									if (!anotherPlayer.isPotionActive(ModRegistry.POTION_INFECTION)) {
										anotherPlayer.sendMessage(new TextComponentTranslation("zombieinfection.chat.playerinfected"));
										anotherPlayer.addPotionEffect(PotionHelper.createInfection(0));
									}
								}
							}
						}
					}
				}
			} else {
				TimeInfectedTracking.remove(player);
				FoodTracking.remove(player);
			}
		}
	}
}