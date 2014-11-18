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
	}
	
	public void removePlayer(Player p)
	{
		if(invitedPlayers.contains(p))
		{			
			invitedPlayers.remove(p);
		}
	}
	
	public InvitedPlayer findByPlayer( Player p)
	{
		for(InvitedPlayer curp: invitedPlayers)
		{
			if(curp.p == p && curp.invite.isCancelled() != true)
			{
				return curp;
			}
		}
	
		return null;
	}
}
