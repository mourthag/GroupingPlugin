package com.github.mourthag.ListenerGroupingPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.mourthag.EventsGroupingPlugin.InviteEvent;
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
		Player source = e.getInviter();
		Player target = e.getInvited();
		
		source.sendMessage(ChatColor.AQUA + "You invited player " + target.getName()+ " to your party");
		target.sendMessage(ChatColor.DARK_AQUA + source.getName() + " wants you to join your Party");
		target.sendMessage(ChatColor.DARK_AQUA + "Accept his invite with /partyAccept or decline it with /partyDecline");
		
		mainPlugin.invHandler.addPlayer(target, e);
	}
}
