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
			
			if(cmd.getName().equalsIgnoreCase("partyCreate"))
			{
				if(mainPlugin.gHandler.findGroupByPlayer(p) == null)
				{
					mainPlugin.gHandler.createGroup(p);
					p.sendMessage(ChatColor.AQUA + "Party created, You are now the admin");
					return true;
				}
				else
				{
					p.sendMessage(ChatColor.DARK_AQUA + "You are already in a Party!");
					return true;
				}
			}
			
			else if(cmd.getName().equalsIgnoreCase("party"))
			{
				p.sendMessage(ChatColor.DARK_AQUA + "/party - Lists all Commands and their usage");
				p.sendMessage(ChatColor.DARK_AQUA + "/partyCreate - Create a Party");
				p.sendMessage(ChatColor.DARK_AQUA + "/partyInvite - Invite a player int your party");
				p.sendMessage(ChatColor.DARK_AQUA + "/partyAccept - Accept the current Invite");
				p.sendMessage(ChatColor.DARK_AQUA + "/partyDecline - Decline your current Invite");
				p.sendMessage(ChatColor.DARK_AQUA + "/partyLeave - Leave your current Party");
				p.sendMessage(ChatColor.DARK_AQUA + "/partymsg - Send a message to your party members (also /p)");
				p.sendMessage(ChatColor.DARK_AQUA + "/partyKick - Kick a player of your party");
				p.sendMessage(ChatColor.DARK_AQUA + "/partylist - Lists all current party members");
				return true;
			}
			
			else if(cmd.getName().equalsIgnoreCase("partyLeave"))
			{
				Group curGroup = mainPlugin.gHandler.findGroupByPlayer(p);
				if(curGroup != null)
				{
					if(curGroup.admin == p)
					{
						if(mainPlugin.gHandler.deleteGroup(curGroup))
						{
							p.sendMessage(ChatColor.AQUA + "Party dissolved");
						}
						else
							p.sendMessage(ChatColor.DARK_AQUA + "Unknown Error occured please inform the admin");
						
						return true;
					}
					else
					{
						curGroup.removePlayer(p);
						p.sendMessage(ChatColor.DARK_AQUA + "You left your Party");
						return true;
					}
				}
				else
				{
					p.sendMessage(ChatColor.DARK_AQUA + "You are not in a party");
					return true;
				}
			}
			else if(cmd.getName().equalsIgnoreCase("partyInvite"))
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
									p.sendMessage(ChatColor.DARK_AQUA + "Player " + player + " not found");
								}
								else
								{
									p.sendMessage(ChatColor.DARK_AQUA + "Player " + inv.getName() + " is already in a Party");
								}
							}
							return true;				
						}
						else
						{
							p.sendMessage(ChatColor.DARK_AQUA + "You are not the admin");
							return true;
						}
					}
					else
					{
						p.sendMessage(ChatColor.DARK_AQUA + "You are in no Party");
						return true;
					}
				}
				else
				{
					p.sendMessage(ChatColor.DARK_AQUA + "Enter a player");
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
