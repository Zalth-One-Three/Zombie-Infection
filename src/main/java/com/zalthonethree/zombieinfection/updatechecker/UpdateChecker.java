package com.zalthonethree.zombieinfection.updatechecker;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.utility.LogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class UpdateChecker {
	private static final String UPDATE_LOC = "https://raw.githubusercontent.com/Zalth-One-Three/Zombie-Infection/Versions/Versions.txt";
	private static boolean MAJOR_UPDATE = false;
	private static boolean MINOR_UPDATE = false;
	
	public static boolean getMajorUpdate() {
		return MAJOR_UPDATE;
	}
	
	public static boolean getMinorUpdate() {
		return MINOR_UPDATE;
	}
	
	public static final void CheckForUpdates() {
		try {
			String dat = getUrl(UPDATE_LOC, false);
			String[] lines = dat.split("\\n");
			ArrayList<String> mcVer_modVer_MAP = new ArrayList<String>();
			String tempver = "";
			int Count = 0;
			for (String line : lines) {
				if (Count % 2 == 0) {
					tempver = line;
				} else {
					mcVer_modVer_MAP.add(tempver + "@@" + line);
				}
				Count ++;
			}
			for (String s : mcVer_modVer_MAP) {
				String mcVer = s.split("@@")[0];
				String modVer = s.split("@@")[1];
				if (mcVer.equalsIgnoreCase(Reference.MINECRAFT_VERSION)) {
					String majVer = modVer.split("\\.")[1];
					String minVer = modVer.split("\\.")[2];
					Integer majVerI = Integer.parseInt(majVer);
					Integer minVerI = Integer.parseInt(minVer);
					Integer thisMajVerI = Integer.parseInt(Reference.VERSION.split("\\.")[1]);
					Integer thisMinVerI = Integer.parseInt(Reference.VERSION.split("\\.")[2]);
					if (majVerI > thisMajVerI) {
						MAJOR_UPDATE = true;
					}
					if (minVerI > thisMinVerI) {
						MINOR_UPDATE = true;
					}
				}
			}
		} catch (Exception e) {}
	}
	
	private static final String getUrl(String link, boolean doRedirect) throws IOException {
		URL url = new URL(link);
		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(false);
		con.setReadTimeout(20000);
		con.setRequestProperty("Connection", "keep-alive");
		
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:16.0) Gecko/20100101 Firefox/16.0");
		((HttpURLConnection) con).setRequestMethod("GET");
		con.setConnectTimeout(5000);
		BufferedInputStream in = new BufferedInputStream(con.getInputStream());
		int responseCode = con.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_MOVED_PERM) {
			LogHelper.warn("Update request returned response code: " + responseCode + " " + con.getResponseMessage());
		} else if (responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
			if (doRedirect) {
				try {
					return getUrl(con.getHeaderField("location"), false);
				} catch (IOException e) {
					throw e;
				}
			} else {
				throw new IOException();
			}
		}
		StringBuffer buffer = new StringBuffer();
		int chars_read;
		while ((chars_read = in.read()) != -1) {
			char g = (char) chars_read;
			buffer.append(g);
		}
		final String page = buffer.toString();
		
		return page;
	}
	
	@SubscribeEvent public void onJoin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		if (MAJOR_UPDATE) {
			player.addChatComponentMessage(new ChatComponentTranslation("zombieinfection.update.major"));
			IChatComponent component = IChatComponent.Serializer.jsonToComponent(StatCollector.translateToLocal("zombieinfection.update.download"));
			player.addChatMessage(component);
		}
		if (!MAJOR_UPDATE && MINOR_UPDATE) {
			player.addChatComponentMessage(new ChatComponentTranslation("zombieinfection.update.minor"));
			IChatComponent component = IChatComponent.Serializer.jsonToComponent(StatCollector.translateToLocal("zombieinfection.update.download"));
			player.addChatMessage(component);
		}
	}
}