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
			mainPlugin.getLogger().info(mainPlugin.invHandler.findByPlayer(p).p.getName());
			InvitedPlayer invP = mainPlugin.invHandler.findByPlayer(p);
			
			if(invP != null)
			{
				if(cmd.getName().equalsIgnoreCase("acceptInvite"))
				{
					invP.invite.setAnswer(true);
					invP.invite.getGroup().addPlayer(p);
					p.sendMessage(ChatColor.BLUE + "Group joined");
					return true;
				}
				if(cmd.getName().equalsIgnoreCase("declineInvite"))
				{
					invP.invite.setAnswer(false);
					invP.invite.getInviter().sendMessage(ChatColor.BLUE + p.getName() + " declined your Invite");
				}
			}
			else
			{
				p.sendMessage(ChatColor.BLUE + "You dont have any open invites");
				return false;
			}
		}
		
		return true;
	}
	

}
