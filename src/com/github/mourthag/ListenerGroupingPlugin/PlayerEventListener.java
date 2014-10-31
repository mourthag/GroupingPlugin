package com.github.mourthag.ListenerGroupingPlugin;

import org.bukkit.ChatColor;
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
			if(curGroup.admin == p)
			{
				curGroup.removeAllMember();
				curGroup.sendMessage("The admin of the Group dissolved this group", ChatColor.AQUA);
				mainPlugin.gHandler.deleteGroup(curGroup);
				p.sendMessage(ChatColor.AQUA + "Group dissolved");
			}
			else
			{
				curGroup.removePlayer(p);
				p.sendMessage(ChatColor.BLUE + "You left your Group");
			}
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
		e.setDeathMessage(null);
	}
}
