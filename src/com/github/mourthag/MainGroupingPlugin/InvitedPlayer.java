package com.github.mourthag.MainGroupingPlugin;

import org.bukkit.entity.Player;

import com.github.mourthag.EventsGroupingPlugin.InviteEvent;

/*
 * Class to connect Player and InviteEvent
 * for later Commands
 */
public class InvitedPlayer
{
	public Player p;
	public InviteEvent invite;
	
	public InvitedPlayer(Player player, InviteEvent inv)
	{
		p = player;
		invite = inv;
	}
}
