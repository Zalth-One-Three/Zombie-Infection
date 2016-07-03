package com.zalthonethree.zombieinfection.client.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelCustomData;
import net.minecraftforge.client.model.IRetexturableModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.model.IModelState;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.sun.prism.Material;
import com.zalthonethree.zombieinfection.block.BlockBase;
import com.zalthonethree.zombieinfection.utility.LogHelper;

public class ZI2Model implements IModelCustomData {
	private final BlockBase block;
	
	public ZI2Model(BlockBase block) {
		this.block = block;
	}
	
	@Override public Collection<ResourceLocation> getDependencies() {
		return Collections.emptyList();
	}
	
	@Override public Collection<ResourceLocation> getTextures() {
		return ImmutableSet.of(block.getTexture());
	}
	
	@Override IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		ImmutableMap.Builder<String, TextureAtlasSprite> builder = ImmutableMap.builder();
		builder.put(ModelLoader.White.LOCATION.toString(), ModelLoader.White.INSTANCE);
		TextureAtlasSprite missing = bakedTextureGetter.apply(new ResourceLocation("missingno"));
		for (Map.Entry<String, Material> e : matLib.materials.entrySet()) {
			builder.put(e.getKey(), bakedTextureGetter.apply(e.getValue().getTexture().getTextureLocation()))
		}
		builder.put("missingno", missing);
		return new ZI2BakedModel(this, state, format, builder.build());
	}
	
	public MaterialLibrary getMatLib() {
		return this.matLib;
	}
	
	@Override public IModel process(ImmutableMap<String, String> customData) {
		ZI2Model ret = new ZI2Model(this.matLib, this.modelLocation, new CustomData(this.customData, customData));
		return ret;
	}
	
	@Override public IModel retexture(ImmutableMap<String, String> textures) {
		ZI2Model ret = new ZI2Model(this.matLib.makeLibWithReplacements(textures), this.modelLocation, this.customData);
		return ret;
	}
	
	static class CustomData {
		public boolean ambientOcclusion = true;
		public boolean gui3d = true;
		public boolean flipV = false;
		
		public CustomData(CustomData parent, ImmutableMap<String, String> customData) {
			this.ambientOcclusion = parent.ambientOcclusion;
			this.gui3d = parent.gui3d;
			this.flipV = parent.flipV;
			this.process(customData);
		}
		
		public CustomData() {}
		
		public void process(ImmutableMap<String, String> customData) {
			for (Map.Entry<String, String> e : customData.entrySet()) {
				if (e.getKey().equals("ambient"))
					this.ambientOcclusion = Boolean.valueOf(e.getValue());
				else if (e.getKey().equals("gui3d"))
					this.gui3d = Boolean.valueOf(e.getValue());
				else if (e.getKey().equals("flip-v"))
					this.flipV = Boolean.valueOf(e.getValue());
			}
		}
	}
	
	public static class Parser {
		private static final Pattern WHITE_SPACE = Pattern.compile("\\s+");
	}
}