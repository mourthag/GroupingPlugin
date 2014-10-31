package com.github.mourthag.MainGroupingPlugin;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GroupHandler 
{
	public List<Group> activeGroups = new ArrayList<Group>();
	
	public GroupHandler()
	{
		
	}
	
	public boolean deleteGroup(Group GToDelete)
	{
		if(activeGroups.contains(GToDelete))
		{
			GToDelete.sendMessage("The admin has left your Group");
			GToDelete.sendMessage("Your Group is being dissolved");
			GToDelete.removeAllMember();
			activeGroups.remove(GToDelete);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void createGroup(Player creator)
	{
		Group addedGroup = new Group(creator);
		activeGroups.add(addedGroup);
		addedGroup.sendMessage("Group created");
	}
	
	public Group findGroupByPlayer(Player p)
	{
		for(Group g: activeGroups)
		{
			if(g.contains(p))
				return g;
		}
		return null;
	}

}
