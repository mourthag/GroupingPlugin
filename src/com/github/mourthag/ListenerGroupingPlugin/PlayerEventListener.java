package com.github.mourthag.ListenerGroupingPlugin;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.mourthag.MainGroupingPlugin.*;

public class PlayerEventListener implements Listener
{
	static Main mainPlugin;
	
	public PlayerEventListener(Main main)
	{
		mainPlugin = main;
		mainPlugin.getServer().getPluginManager().registerEvents(this, mainPlugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLogout(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
		
		if(curGroup != null)
		{
			curGroup.removePlayer(p);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		Player p = e.getEntity();
		Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
		
		if(curGroup != null)
		{
			curGroup.sendMessage(e.getDeathMessage());
		}
		else
		{
			e.setDeathMessage("");
		}
	}
}
