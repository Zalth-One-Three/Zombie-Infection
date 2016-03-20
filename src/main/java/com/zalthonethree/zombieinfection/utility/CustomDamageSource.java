package com.zalthonethree.zombieinfection.utility;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class CustomDamageSource extends DamageSource {
	String message;
	
	public CustomDamageSource(String type, String deathmessage) {
		super(type);
		this.setDamageIsAbsolute();
		this.message = deathmessage;
	}
	
	@Override public ITextComponent getDeathMessage(EntityLivingBase entity) {
		return new TextComponentTranslation(this.message, entity.getDisplayName());
	}
}