package me.JayzaSapphire;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TalkBack extends JavaPlugin {

	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
		
		getServer().getPluginManager().registerEvents(new TalkBackListener(this), this);
	}
	
	public boolean isExistant(String it) {
		it = it.replace(" ", "+");
		if (getConfig().getString("words." + it.toLowerCase()) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getExistant(Player player, String it) {
		it = it.toLowerCase();
		it = it.replace(" ", "+");
		
		String back = getConfig().getString("words." + it);
		
		back = translateNodes(player, back);
		
		return ChatColor.translateAlternateColorCodes('&', back);
	}
	
	private String translateNodes(Player player, String it) {
		it = it.replace("%player", player.getName());
		it = it.replace("%world", player.getWorld().getName());
		it = it.replace("%health", String.valueOf(player.getHealth()));
		it = it.replace("%food", String.valueOf(player.getFoodLevel()));
		it = it.replace("%level", String.valueOf(player.getLevel()));
		it = it.replace("%displayname", player.getDisplayName());
		
		return it;
	}
	
	public String getTalkBackName() {
		String name = getConfig().getString("talkback");
		String ret = null;
		
		if (name != null) {
			if (!name.endsWith(" ")) {
				ret = ChatColor.translateAlternateColorCodes('&', name) + " ";
			} else {
				ret = ChatColor.translateAlternateColorCodes('&', name);
			}
		} else {
			ret = "";
		}
		
		return ret;
	}
	
	public void onDisable() {
		
	}
}
