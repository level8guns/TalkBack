package me.JayzaSapphire;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TalkBackListener implements Listener {

	private TalkBack pl;
	public TalkBackListener(TalkBack pl) {
		this.pl = pl;
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		final Player player = event.getPlayer();
		final String message = event.getMessage();
		
		if (pl.isExistant(message)) {
			pl.getServer().getScheduler().scheduleAsyncDelayedTask(pl, new Runnable() {
				public void run() {
					if (pl.getExistant(player, message).startsWith("/")) {
						pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), pl.getExistant(player, message).replaceFirst("/", ""));
					} else {
						player.sendMessage(pl.getTalkBackName() + pl.getExistant(player, message));
					}
				}
			}, 1);
		}
	}
}
