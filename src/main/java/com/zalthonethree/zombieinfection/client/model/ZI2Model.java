package com.zalthonethree.zombieinfection.client.model;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelCustomData;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.IRetexturableModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.sun.prism.Material;
import com.zalthonethree.zombieinfection.block.BlockBase;
import com.zalthonethree.zombieinfection.utility.LogHelper;

public class ZI2Model implements IModelCustomData {
	private final ResourceLocation resourceLocation;
	
	public ZI2Model(ResourceLocation resourceLocation) {
		this.resourceLocation = resourceLocation;
	}
	
	@Override public Collection<ResourceLocation> getDependencies() {
		return Collections.emptyList();
	}
	
	@Override public Collection<ResourceLocation> getTextures() {
		return ImmutableSet.of(resourceLocation); // TODO Convert the model location to a texture location.
	}
	
	@Override public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		ImmutableMap<TransformType,TRSRTransformation> map = IPerspectiveAwareModel.MapWrapper.getTransforms(state);
		return new ZI2BakedModel(state, map, format, bakedTextureGetter);
	}
	
	@Override public IModelState getDefaultState() {
		return ModelRotation.X0_Y0;
	}
	
	public static enum ZI2Loader implements ICustomModelLoader {
		INSTANCE;
		
		public void onResourceManagerReload(IResourceManager resourceManager) {}
		
		public boolean accepts(ResourceLocation modelLocation) {
			return modelLocation.getResourceDomain().contains("zombieinfection");
		}
		
		public IModel loadModel(ResourceLocation modelLocation) {
			return new ZI2Model(modelLocation);
		}
	}
	
	private static final class ZI2BakedModel implements IPerspectiveAwareModel {
		private static final int[] x = {0, 0, 1, 1};
		private static final int[] z = {0, 1, 1, 0};
		
		private final LoadingCache<Long, ZI2BakedModel> modelCache = CacheBuilder.newBuilder().maximumSize(200).build(new CacheLoader<Long, ZI2BakedModel>() {
			public ZI2BakedModel load(Long key) throws Exception {
				return new ZI2BakedModel(transformation, transforms, format);
			}
			
			private final Optional<TRSRTransformation> transformation;
			private final ImmutableMap<TransformType, TRSRTransformation> transforms;
			private final VertexFormat format;
			private final TextureAtlasSprite texture;
			private final EnumMap<EnumFacing, List<BakedQuad>> faceQuads;
			
			public ZI2BakedModel(Optional<TRSRTransformation> transformation, ImmutableMap<TransformType, TRSRTransformation> transforms, TextureAtlasSprite texture) {
				
			}
		});
	}
}