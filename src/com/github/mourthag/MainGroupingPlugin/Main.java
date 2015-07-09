package com.github.mourthag.MainGroupingPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.mourthag.CommandsGroupingPlugin.*;
import com.github.mourthag.ListenerGroupingPlugin.*;

public class Main extends JavaPlugin
{
	public GroupHandler gHandler; 
	public InvitedPlayerHandler invHandler;
	
	public void onEnable()
	{
		new XPEventListener(this);
		new PlayerEventListener(this);
		new InviteEventListener(this);
		gHandler = new GroupHandler();
		invHandler = new InvitedPlayerHandler(this);
		this.getLogger().info("Grouping Plugin v1.1.0 enabled");
		this.getLogger().info("Author: Mourthag");
		setCommands();
		this.reloadConfig();
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void onDisable()
	{
		gHandler = null;
		invHandler = null;
		this.getLogger().info("Grouping Plugin disabled");
		this.getLogger().info("Thanks for using");
	}
	
	private void setCommands()
	{
		getCommand("party").setExecutor(new GroupManCommand(this));
		getCommand("partyCreate").setExecutor(new GroupManCommand(this));
		getCommand("partyLeave").setExecutor(new GroupManCommand(this));
		getCommand("partyInvite").setExecutor(new GroupManCommand(this));
		getCommand("partymsg").setExecutor(new GroupIntCommand(this));
		getCommand("partyAccept").setExecutor(new InviteAnswerCommand(this));
		getCommand("partyDecline").setExecutor(new InviteAnswerCommand(this));
		getCommand("partyList").setExecutor(new GroupIntCommand(this));
		getCommand("partyKick").setExecutor(new GroupIntCommand(this));
	}
}
