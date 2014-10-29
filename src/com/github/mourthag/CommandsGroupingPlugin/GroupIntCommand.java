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
					if(curGroup.admin.getName() == p.getName())
					{
						curGroup.sendMessage(p.getName() + ": " + args[0], ChatColor.AQUA);
						return true;
					}
					else
					{
						curGroup.sendMessage(args[0]);
						return true;
					}
				}
				else
				{
					p.sendMessage(ChatColor.BLUE + "You are in no Group");
				}
			}
		}
		return false;
	}

}
