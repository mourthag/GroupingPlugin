package com.github.mourthag.ListenerGroupingPlugin;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import com.github.mourthag.MainGroupingPlugin.*;

public class XPEventListener implements Listener
{
	static Main mainPlugin;
	
	public XPEventListener(Main main)
	{
		mainPlugin = main;
		mainPlugin.getServer().getPluginManager().registerEvents(this, mainPlugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onLVLChange(PlayerLevelChangeEvent e)
	{
		Player p = e.getPlayer();
		Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
		if(curGroup != null && e.getNewLevel() > e.getOldLevel())
			curGroup.sendMessage(p.getName() + " is now Level " + e.getNewLevel() + "!");
	}

}
