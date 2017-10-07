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
				if (ConfigurationHandler.enableSlowness()) player.addPotionEffect(PotionHelper.createSlowness(timeInfected < 60 ? 0 : 1));
				if (ConfigurationHandler.enableHunger() && timeInfected >= 20) player.addPotionEffect(PotionHelper.createHunger(timeInfected < 80 ? 0 : 1));
				if (ConfigurationHandler.enableMiningFatigue() && timeInfected >= 40) player.addPotionEffect(PotionHelper.createMiningFatigue(timeInfected < 100 ? 0 : 1));
				if (ConfigurationHandler.enableWeakness() && timeInfected >= 60) player.addPotionEffect(PotionHelper.createWeakness(timeInfected < 120 ? 0 : 1));
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
						&& timeInfected >= 80
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