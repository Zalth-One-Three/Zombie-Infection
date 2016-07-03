package com.zalthonethree.zombieinfection.client.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;

public enum ZI2Loader implements ICustomModelLoader {
	INSTANCE;
	
	private IResourceManager manager;
	private Set<String> enabledDomains = new HashSet<String>();
	private final Map<ResourceLocation, ZI2Model> cache = new HashMap<ResourceLocation, ZI2Model>();
}