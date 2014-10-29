package com.github.mourthag.ListenerGroupingPlugin;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import com.github.mourthag.MainGroupingPlugin.Group;
import com.github.mourthag.MainGroupingPlugin.Main;

public class XPEventListener implements Listener
{
	static Main mainPlugin;
	
	public XPEventListener(Main main)
	{
		mainPlugin = main;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onLVLChange(PlayerLevelChangeEvent e)
	{
		mainPlugin.getLogger().info("LVLup");
		Player p = e.getPlayer();
		Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
		if(curGroup != null)
			curGroup.sendMessage(p.getName() + "is now Level" + e.getNewLevel() + "!");
	}

}
