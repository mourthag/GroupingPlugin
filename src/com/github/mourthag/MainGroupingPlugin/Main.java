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
		gHandler = new GroupHandler();
		invHandler = new InvitedPlayerHandler();
		this.getLogger().info("Grouping Plugin v1.0.0 enabled");
		this.getLogger().info("Author: Mourthag");
		setCommands();
		this.reloadConfig();
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	public void onDisable()
	{
		gHandler = null;
		this.getLogger().info("Grouping Plugin disabled");
		this.getLogger().info("Thanks for using");
	}
	
	private void setCommands()
	{
		getCommand("createGroup").setExecutor(new GroupManCommand(this));
		getCommand("leaveGroup").setExecutor(new GroupManCommand(this));
		getCommand("invite").setExecutor(new GroupManCommand(this));
		getCommand("msgGroup").setExecutor(new GroupIntCommand(this));
		getCommand("acceptInvite").setExecutor(new InviteAnswerCommand(this));
		getCommand("declineInvite").setExecutor(new InviteAnswerCommand(this));
	}
}
