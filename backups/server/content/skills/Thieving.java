package server.content.skills;

import server.Server;
import server.game.npcs.*;
import server.Config;
import core.util.Misc;
import server.game.players.Client;

/**
 * @author Izumi
 **/
 
public class Thieving {
	
	private Client c;
		
	public Thieving(Client c) {
		this.c = c;
	}
	
	public void stealFromNPC(int id) {
		if (System.currentTimeMillis() - c.lastThieve < 2000)//fine tune thieving delay here
			return;
		for (int j = 0; j < npcThieving.length; j++) {
			if (npcThieving[j][0] == id) {
				if (c.playerLevel[c.playerThieving] >= npcThieving[j][1]) {
                    c.sendMessage("You attempt to pick the man's pocket...");
					if (Misc.random(c.playerLevel[c.playerThieving] + 2 - npcThieving[j][1]) != 1) {
						c.getPA().addSkillXP(npcThieving[j][2] * Config.THIEVING_EXPERIENCE, c.playerThieving);
						c.getItems().addItem(995, npcThieving[j][3]);
						c.startAnimation(881);
						c.lastThieve = System.currentTimeMillis();
						c.sendMessage("You manage to steal some coins.");
						break;
					} else {
						c.setHitDiff(npcThieving[j][4]);
						c.setHitUpdateRequired(true);
						c.playerLevel[3] -= npcThieving[j][4];
						c.getPA().refreshSkill(3);
						c.lastThieve = System.currentTimeMillis() + 2000;
						c.sendMessage("You were caught pickpocketing.");
						//forceNPCChat("I'm sosowwy");
						break;
					}
				} else {
					c.sendMessage("You need a thieving level of " + npcThieving[j][1] + " to pick this pocket.");
				}
			}		
		}
		
	}
	public boolean updateRequired;
	public boolean forcedChatRequired;
	public String forcedText;
	public void forceNPCChat(String text) {
		forcedText = text;
		forcedChatRequired = true;
		updateRequired = true;
	}
	
	public void stealFromStall(int id, int xp, int level) {
		if (System.currentTimeMillis() - c.lastThieve < 2500)
			return;
		if (Misc.random(100) == 0) {
			return;
		}
		if (c.playerLevel[c.playerThieving] >= level) {
			if (c.getItems().addItem(id,1)) {
				c.startAnimation(832);
				c.getPA().addSkillXP(xp * Config.THIEVING_EXPERIENCE, c.playerThieving);
				c.lastThieve = System.currentTimeMillis();
				c.sendMessage("You steal a " + server.game.items.Item.getItemName(id).toLowerCase() + ".");
			}		
		} else {
			c.sendMessage("You must have a thieving level of " + level + " to thieve from this stall.");
		}
	}
	//npc, level, exp, coin amount
	public int[][] npcThieving = {{1,1,8,100 + Misc.random(100),1},{2,1,8,100 + Misc.random(100),1},{3,1,8,100 + Misc.random(100),1},{4,1,8,100 + Misc.random(100),1},{5,1,8,100 + Misc.random(100),1},{6,1,8,100 + Misc.random(100),1},{7,10,17,150 + Misc.random(100),1},{18,25,26,200 + Misc.random(100),1},{9,40,47,400 + Misc.random(100),2},{26,55,85,1500 + Misc.random(500),3},{20,70,152,750 + Misc.random(250),4},{21,80,273,1250 + Misc.random(500),5}};

}