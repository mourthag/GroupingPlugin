package com.github.mourthag.MainGroupingPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	public GroupHandler gHandler; 
	
	public void onEnable()
	{
		gHandler = new GroupHandler();
		this.getLogger().info("Grouping Plugin v1.0.0 enabled");
		this.getLogger().info("Author: Mourthag");
	}
	
	public void onDisable()
	{
		gHandler = null;
		this.getLogger().info("Grouping Plugin disabled");
		this.getLogger().info("Thanks for using");
	}

}
