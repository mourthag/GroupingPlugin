package com.github.mourthag.ListenerGroupingPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.mourthag.EventsGroupingPlugin.InviteEvent;
import com.github.mourthag.MainGroupingPlugin.Group;
import com.github.mourthag.MainGroupingPlugin.Main;

public class InviteEventListener implements Listener{

	static Main mainPlugin;
	
	public InviteEventListener(Main main)
	{
		mainPlugin = main;
		mainPlugin.getServer().getPluginManager().registerEvents(this, mainPlugin);
	}
	
	@EventHandler
	public void onInvite(InviteEvent e)
	{
		mainPlugin.getLogger().info("5");
		Player source = e.getInviter();
		Player target = e.getInvited();
		Group srcGroup = e.getGroup();
		
		target.sendMessage(ChatColor.BLUE + source.getName() + " wants you to join your Group");
		
		mainPlugin.invHandler.addPlayer(target, e);
	}
}
