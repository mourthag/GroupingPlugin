package com.github.mourthag.MainGroupingPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.github.mourthag.EventsGroupingPlugin.InviteEvent;

public class InvitedPlayerHandler 
{
	public List<InvitedPlayer> invitedPlayers;
	
	public InvitedPlayerHandler()
	{
		invitedPlayers = new ArrayList<InvitedPlayer>();
	}
	
	public void addPlayer(Player p, InviteEvent e)
	{
		removePlayer(p);
		invitedPlayers.add(new InvitedPlayer(p, e));
	}
	
	public void removePlayer(Player p)
	{
		if(findByPlayer(p) != null)
		{
			invitedPlayers.remove(p);
		}
		
	}
	
	public InvitedPlayer findByPlayer( Player p)
	{
		if(invitedPlayers.contains(p))
		{
			for(int i = 0; i < invitedPlayers.size(); i++)
			{
				if(invitedPlayers.get(i).p == p)
				{
					return invitedPlayers.get(i);
				}
			}
		}
		return null;
	}
}
