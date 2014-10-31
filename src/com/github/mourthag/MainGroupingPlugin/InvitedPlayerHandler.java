package com.github.mourthag.MainGroupingPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.github.mourthag.EventsGroupingPlugin.InviteEvent;

public class InvitedPlayerHandler 
{
	public List<InvitedPlayer> invitedPlayers;
	static Main mainPlugin;
	
	public InvitedPlayerHandler(Main main)
	{
		invitedPlayers = new ArrayList<InvitedPlayer>();
		mainPlugin = main;
	}
	
	public void addPlayer(Player p, InviteEvent e)
	{
		removePlayer(p);
		invitedPlayers.add(new InvitedPlayer(p, e));
		mainPlugin.getLogger().info("Joho");
	}
	
	public void removePlayer(Player p)
	{
		invitedPlayers.remove(p);
		mainPlugin.getLogger().info("Test");
	}
	
	public InvitedPlayer findByPlayer( Player p)
	{
		mainPlugin.getLogger().info("Schleife");
			for(InvitedPlayer curp: invitedPlayers)
			{
				if(curp.p == p)
				{
					return curp;
				}
			}
		return null;
	}
}
