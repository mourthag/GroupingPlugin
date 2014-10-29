package com.github.mourthag.CommandsGroupingPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.mourthag.MainGroupingPlugin.Group;
import com.github.mourthag.MainGroupingPlugin.Main;

public class GroupManCommand implements CommandExecutor
{
	static Main mainPlugin;
	
	public GroupManCommand(Main main)
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
			
			if(cmd.getName().equalsIgnoreCase("createGroup"))
			{
				if(mainPlugin.gHandler.findGroupByPlayer(p) == null)
				{
					mainPlugin.gHandler.createGroup(p);
					p.sendMessage(ChatColor.AQUA + "Group created, You are now the admin");
					return true;
				}
				else
				{
					p.sendMessage(ChatColor.BLUE + "You are already in a Group!");
					return false;
				}
			}
			
			else if(cmd.getName().equalsIgnoreCase("leaveGroup"))
			{
				Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
				if(curGroup != null)
				{
					if(curGroup.admin == p)
					{
						curGroup.member.remove(p);
						curGroup.sendMessage("The admin of the Group dissolved this group", ChatColor.AQUA);
						mainPlugin.gHandler.deleteGroup(curGroup);
						p.sendMessage(ChatColor.AQUA + "Group dissolved");
						return true;
					}
					else
					{
						curGroup.member.remove(p);
						p.sendMessage(ChatColor.BLUE + "You left your Group");
						curGroup.sendMessage(p.getName() + "left your Group");
						return true;
					}
				}
				else
				{
					p.sendMessage(ChatColor.BLUE + "You are not in a Group");
					return false;
				}
			}
			else
			{
				p.sendMessage(ChatColor.BLUE + "Command not implemented yet");
				return false;
			}
		}
		sender.sendMessage("You are no Player!");
		return false;
	}

}
