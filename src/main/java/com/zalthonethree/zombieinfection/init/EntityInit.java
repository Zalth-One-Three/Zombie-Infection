package com.zalthonethree.zombieinfection.init;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.entity.EntityZombieChicken;
import com.zalthonethree.zombieinfection.entity.EntityZombieCow;
import com.zalthonethree.zombieinfection.entity.EntityZombiePig;
import com.zalthonethree.zombieinfection.entity.EntityZombieSheep;
import com.zalthonethree.zombieinfection.entity.zend.EntityEnderZombie;
import com.zalthonethree.zombieinfection.entity.zend.EntityZombieDragon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit /*extends EntityDragon*/ {
	
	private static int nextEntityID = 1;
	private static int eggID = 400;
	
	private static int getNextEntityID() {
		nextEntityID ++;
		return nextEntityID - 1;
	}
	
	@SuppressWarnings("unused") private static void registerEntity(Class<? extends Entity> clazz) { registerEntity(clazz, 0, 0); }
	private static void registerEntity(Class<? extends Entity> clazz, int bkEggColor, int fgEggColor) { registerEntity(clazz, clazz.getName().toLowerCase(), bkEggColor, fgEggColor); }
	private static void registerEntity(Class<? extends Entity> clazz, String name, int bkEggColor, int fgEggColor) {
		EntityRegistry.registerModEntity(clazz, name, getNextEntityID(), ZombieInfection.instance, 80, 4, true);
		registerEgg(clazz, bkEggColor, fgEggColor);
	}
	
	@SuppressWarnings("unchecked") private static void registerEgg(Class<? extends Entity> clazz, int bkEggColor, int fgEggColor) {
		int uniqueID = getUniqueEggId();
		
		EntityList.idToClassMapping.put(uniqueID, clazz);
		EntityList.entityEggs.put(uniqueID, new EntityList.EntityEggInfo(uniqueID, bkEggColor, fgEggColor));
	}
	
	private static int getUniqueEggId() {
		do {
			eggID ++;
		} while (EntityList.getStringFromID(eggID) != null);
		
		return eggID;
	}
	
	@SuppressWarnings("unused") private static void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
		EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.CREATURE, biomes);
	}
	
	
	public static void init() {
		registerEntities();
	}
	
	private static void registerEntities() {
		registerEntity(EntityZombieChicken.class, "ZIZombieChicken", 0xa3a3a3, 0x566c58);
		registerEntity(EntityZombieCow.class, "ZIZombieCow", 0x3e2e09, 0x566c58);
		registerEntity(EntityZombiePig.class, "ZIZombiePig", 0xc87e7e, 0x566c58);
		registerEntity(EntityZombieSheep.class, "ZIZombieSheep", 0xe8e8e8, 0x566c58);
		registerEntity(EntityEnderZombie.class, "ZIEnderZombie", 0xe8e8e8, 0x566c58); //TODO Change foreground color
		registerEntity(EntityZombieDragon.class, "ZIZombieDragon", 0xe8e8e8, 0x566c58); //TODO Change foreground color
	}
}