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
	public boolean addPlayer(Player p)
	{
		if(member.size() < 4)
		{
			member.add(p);
			sendMessage(p.getName() + " joined the Group");
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
			sendMessage(p.getName() + "left your Group");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void sendMessage(String message)
	{
		sendMessage(new String[]{message});
	}
	
	public void sendMessage(String message, ChatColor color)
	{
		sendMessage(new String[]{message}, color);
	}
	
	//send Message to all Members
	public void sendMessage(String[] message)
	{
		sendMessage(message, ChatColor.BLUE);
	}
	
	public void sendMessage(String[] message, ChatColor color)
	{
		if(message.length > 0)
		{
			String newmsg = "";
			
			for(String mPart: message)
			{
				newmsg += mPart + " ";
			}
			
			for(Player p: member)
			{
				p.sendMessage(color + newmsg);
			}
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
