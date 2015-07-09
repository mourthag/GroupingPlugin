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
			
			if(cmd.getName().equalsIgnoreCase("partymsg"))
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
					p.sendMessage(ChatColor.DARK_AQUA + "You are in no party");
					return true;
				}
			}
			if(cmd.getName().equalsIgnoreCase("partyList"))
			{
				Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
				if(curGroup != null)
				{
					p.sendMessage(ChatColor.AQUA + "There are currently " + curGroup.member.size() + " members in your party:");
					for(Player curp: curGroup.member)
					{
						p.sendMessage(ChatColor.AQUA + curp.getName());
					}
					return true;
				}
				else
				{
					p.sendMessage(ChatColor.DARK_AQUA + "You are in no party");
					return true;
				}
			}
			if(cmd.getName().equalsIgnoreCase("partyKick"))
			{
				Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
				
				if(curGroup != null)
				{
					if(curGroup.admin == p)
					{
						for(String player: args)
						{
							Player kick = mainPlugin.getServer().getPlayer(player);
							if(kick != null)
							{
								curGroup.removePlayer(kick);
								kick.sendMessage(ChatColor.DARK_AQUA + "You were kicked from your party");
								p.sendMessage(ChatColor.DARK_AQUA + "You kicked player " + kick.getName() + " from your party!" );
								return true;
							}
							else
							{
								p.sendMessage(ChatColor.DARK_AQUA + "Can't find the player " + player + " in your group");
								return false;
							}
						}
					}
					else
					{
						p.sendMessage(ChatColor.DARK_AQUA + "You must be the admin of the party");
						return false;
					}
				}
			}
		}
		return false;
	}

}
