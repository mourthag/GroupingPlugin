package com.github.mourthag.MainGroupingPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Group {
	public List<Player> member = new ArrayList<Player>();
	public Player admin;
	
	public Group(Player creator)
	{
		admin = creator;
		member.add(creator);
	}
	
	//add a Player to the Group
	public boolean addPlayer(Player PToAdd)
	{
		if(member.size() < 4)
		{
			member.add(PToAdd);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//send Message to all Members
	public void sendMessage(String message)
	{
		sendMessage(message, ChatColor.BLUE);
	}
	
	public void sendMessage(String message, ChatColor color)
	{
		for(Player p: member)
		{
			p.sendMessage(color + message);
		}
	}
	
	//divide the XP and share it with the members
	public void shareXp(int sharedXP)
	{
		int individualXP = sharedXP / member.size();
		
		for(Player p: member)
		{
			p.giveExp(individualXP);
		}
	}
	
	public boolean contains(Player p)
	{
		if(member.contains(p))
			return true;
		else
			return false;
	}
}
