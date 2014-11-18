package com.github.mourthag.CommandsGroupingPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.mourthag.MainGroupingPlugin.Group;
import com.github.mourthag.MainGroupingPlugin.Main;

public class GroupIntCommand implements CommandExecutor {
	
	static Main mainPlugin;
	
	public GroupIntCommand(Main main)
	{
		mainPlugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player p;
		if(sender instanceof Player)
		{
			p = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("msgGroup"))
			{
				Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
				if(curGroup != null)
				{
					String msg = p.getName() + ": ";					
					for(String arg : args)
					{
						msg += arg + " ";
					}

					if(curGroup.admin == p)
					{
						curGroup.sendMessage(msg, ChatColor.AQUA);
						return true;
					}
					else
					{
						curGroup.sendMessage(msg);
						return true;
					}
				}
				else
				{
					p.sendMessage(ChatColor.BLUE + "You are in no Group");
					return true;
				}
			}
			if(cmd.getName().equalsIgnoreCase("listGroup"))
			{
				Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
				if(curGroup != null)
				{
					p.sendMessage(ChatColor.AQUA + "There are currently " + curGroup.member.size() + " members in your Group:");
					for(Player curp: curGroup.member)
					{
						p.sendMessage(ChatColor.AQUA + curp.getName());
					}
					return true;
				}
				else
				{
					p.sendMessage(ChatColor.BLUE + "You are in no Group");
					return true;
				}
			}
		}
		return false;
	}

}
