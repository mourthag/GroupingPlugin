package com.github.mourthag.MainGroupingPlugin;

import java.util.ArrayList;
import java.util.Iterator;
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
	public boolean addPlayer(Player p)
	{
		if(member.size() < 4)
		{
			member.add(p);
			sendMessage(p.getName() + " joined the Group");
			p.sendMessage(ChatColor.BLUE + "Use /p to send a message to your group");
			return true;
		}
		else
		{
			p.sendMessage(ChatColor.BLUE + "Group is full");
			return false;
		}
	}
	
	public void removePlayer(Player p)
	{
		try
		{
			member.remove(p);
			sendMessage(p.getName() + " left your Group");
		}
		catch(Exception e)
		{
			
		}
	}
	public void removeAllMember()
	{
		Iterator<Player> iter = member.iterator();
		
		while(iter.hasNext())
		{
			Player p = iter.next();
			iter.remove();
			sendMessage(p.getName() + " left your Group");
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