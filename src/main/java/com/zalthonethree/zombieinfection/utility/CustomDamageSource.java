package com.zalthonethree.zombieinfection.utility;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class CustomDamageSource extends DamageSource {
	String message;
	
	public CustomDamageSource(String type, String deathmessage) {
		super(type);
		this.setDamageIsAbsolute();
		this.message = deathmessage;
	}
	
	@Override public IChatComponent getDeathMessage(EntityLivingBase entity) {
		return new ChatComponentTranslation(this.message, entity.getFormattedCommandSenderName());
	}
}