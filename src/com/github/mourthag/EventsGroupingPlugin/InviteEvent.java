package com.github.mourthag.EventsGroupingPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.github.mourthag.MainGroupingPlugin.Group;

public class InviteEvent extends Event implements Cancellable
{
	private boolean cancelled;
	private Player inviter;
	private Player invited;
	private Group invGroup;
	private boolean accept;
	private static final HandlerList handlers = new HandlerList();
	 
	public InviteEvent(Player source, Player target, Group srcGroup)
	{
		inviter = source;
		invited = target;
		invGroup = srcGroup;
	}
	public Player getInviter() {
		return inviter;
	}
	
	public Player getInvited() {
		return invited;
	}
	
	public Group getGroup() {
		return invGroup;
	}
	
	public void setAnswer(boolean answer)
	{
		accept = answer;
	}
	
	public boolean getAnswer()
	{
		return accept;
	}
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
}
