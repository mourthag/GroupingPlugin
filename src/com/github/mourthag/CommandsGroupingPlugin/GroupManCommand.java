package com.github.mourthag.CommandsGroupingPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.mourthag.EventsGroupingPlugin.InviteEvent;
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
					return true;
				}
			}
			
			else if(cmd.getName().equalsIgnoreCase("leaveGroup"))
			{
				Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
				if(curGroup != null)
				{
					if(curGroup.admin == p)
					{
						if(mainPlugin.gHandler.deleteGroup(curGroup))
						{
							p.sendMessage(ChatColor.AQUA + "Group dissolved");
						}
						else
							p.sendMessage(ChatColor.BLUE + "Unknown Error occured please inform the admin");
						
						return true;
					}
					else
					{
						curGroup.removePlayer(p);
						p.sendMessage(ChatColor.BLUE + "You left your Group");
						return true;
					}
				}
				else
				{
					p.sendMessage(ChatColor.BLUE + "You are not in a Group");
					return true;
				}
			}
			else if(cmd.getName().equalsIgnoreCase("invite"))
			{
				if(args.length > 0)
				{
					Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
					
					if(curGroup != null)
					{
						if(curGroup.admin == p)
						{
							for(String player: args)
							{
								Player inv = mainPlugin.getServer().getPlayer(player);
								Group otherGroup = mainPlugin.gHandler.findGroupByPlayer(inv);
								if(inv != null && otherGroup == null)
								{
									InviteEvent invite = new InviteEvent( p, inv, curGroup);
									mainPlugin.getServer().getPluginManager().callEvent(invite);
								}
								else if(inv == null)
								{
									p.sendMessage(ChatColor.BLUE + "Player " + player + " not found");
								}
								else
								{
									p.sendMessage(ChatColor.BLUE + "Player " + inv.getName() + " is already in a Group");
								}
							}
							return true;				
						}
						else
						{
							p.sendMessage(ChatColor.BLUE + "You are not the admin");
							return true;
						}
					}
					else
					{
						p.sendMessage(ChatColor.BLUE + "You are in no Group");
						return true;
					}
				}
				else
				{
					p.sendMessage(ChatColor.BLUE + "Enter a player");
					return true;
				}
			}
			else
			{
				return false;
			}
		}
		sender.sendMessage("You are no Player!");
		return true;
	}

}
