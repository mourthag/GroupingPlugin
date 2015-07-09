package com.github.mourthag.CommandsGroupingPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.mourthag.MainGroupingPlugin.InvitedPlayer;
import com.github.mourthag.MainGroupingPlugin.Main;

public class InviteAnswerCommand implements CommandExecutor{

	static Main mainPlugin;
	
	public InviteAnswerCommand(Main main)
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
			InvitedPlayer invP = mainPlugin.invHandler.findByPlayer(p);
			
			if(invP != null)
			{
				if(cmd.getName().equalsIgnoreCase("partyAccept"))
				{
					invP.invite.setAnswer(true);
					invP.invite.getGroup().addPlayer(p);
					invP.invite.setCancelled(true);
					mainPlugin.invHandler.removePlayer(p);
					p.sendMessage(ChatColor.DARK_AQUA + "Party joined");
					return true;
				}
				if(cmd.getName().equalsIgnoreCase("partyDecline"))
				{
					invP.invite.setAnswer(false);
					invP.invite.setCancelled(true);
					mainPlugin.invHandler.removePlayer(p);
					invP.invite.getInviter().sendMessage(ChatColor.DARK_AQUA + p.getName() + " declined your Invite");
					return true;
				}
			}
			else
			{
				p.sendMessage(ChatColor.DARK_AQUA + "You dont have any open invites");
				return false;
			}
		}
		
		return true;
	}
	

}
