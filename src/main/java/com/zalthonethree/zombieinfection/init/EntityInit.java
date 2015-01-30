package com.zalthonethree.zombieinfection.init;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.entity.EntityZombieChicken;
import com.zalthonethree.zombieinfection.entity.EntityZombieCow;
import com.zalthonethree.zombieinfection.entity.EntityZombiePig;
import com.zalthonethree.zombieinfection.entity.EntityZombieSheep;

public class EntityInit {
	@SuppressWarnings("unused") private static void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
		EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.CREATURE, biomes);
	}
	
	public static void init() {
		EntityRegistry.registerModEntity(EntityZombieChicken.class, "ZIZombieChicken", 0, ZombieInfection.instance, 80, 4, true, 0xa3a3a3, 0x566c58);
		EntityRegistry.registerModEntity(EntityZombieCow.class, "ZIZombieCow", 1, ZombieInfection.instance, 80, 4, true, 0x3e2e09, 0x566c58);
		EntityRegistry.registerModEntity(EntityZombiePig.class, "ZIZombiePig", 2, ZombieInfection.instance, 80, 4, true, 0xc87e7e, 0x566c58);
		EntityRegistry.registerModEntity(EntityZombieSheep.class, "ZIZombieSheep", 3, ZombieInfection.instance, 80, 4, true, 0xe8e8e8, 0x566c58);
		// registerEntity(EntityZombieChicken.class, "ZIZombieChicken", 0xa3a3a3, 0x566c58);
		// registerEntity(EntityZombieCow.class, "ZIZombieCow", 0x3e2e09, 0x566c58);
		// registerEntity(EntityZombiePig.class, "ZIZombiePig", 0xc87e7e, 0x566c58);
		// registerEntity(EntityZombieSheep.class, "ZIZombieSheep", 0xe8e8e8, 0x566c58);
	}
}