package server.game.players.packets;

import server.Server;


import core.util.*;
import server.game.players.Client;
import server.event.Event;
import server.event.EventContainer;
import server.event.EventManager;
import core.util.Misc;
import server.game.players.PacketType;
import server.content.skills.Prayer;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;


/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {
	
	private static final int[] CASKET_REWARDS = {2577, 2581, 7388, 7392, 7396, 2583, 2585, 
		2587, 2589, 2599, 2601, 2603, 2605, 2623, 2625, 2627, 2629, 3472,
		3474, 3477, 7364, 7368, 7372, 7376, 7380, 7384, 7388, 7392, 7396};
	public static int getLength() {
		return CASKET_REWARDS.length;
	}
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
		}
	               if (itemId == 6199) {
                int mysteryReward = Misc.random(13);
                if (mysteryReward == 1) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(454, Misc.random(30));
                	c.sendMessage("You open the box and get some coal.");
		}
		else if (mysteryReward == 2) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(448, Misc.random(5));
               		c.sendMessage("You open the box and get some mithril ore.");
		}
		else if (mysteryReward == 3) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(140, Misc.random(5));
                	c.sendMessage("You open the box and get some prayer potions.");
		}
		else if (mysteryReward == 4) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(392, Misc.random(20));
                	c.sendMessage("You open the box and get some manta rays.");
		}
		else if (mysteryReward == 5) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(995, Misc.random(35000));
                	c.sendMessage("You open the box and get some coins.");
		}
		else if (mysteryReward == 6) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(561, Misc.random(50));
                	c.sendMessage("You open the box and get some nature runes.");
		}
		else if (mysteryReward == 7) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(563, Misc.random(30));
               		c.sendMessage("You open the box and get some law runes.");
		}
		else if (mysteryReward == 8) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(565, Misc.random(60));
                	c.sendMessage("You open the box and get some blood runes.");
		}
		else if (mysteryReward == 9) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(560, Misc.random(65));
                	c.sendMessage("You open the box and get some death runes.");
		}
		else if (mysteryReward == 10) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(556, Misc.random(300));
               		c.sendMessage("You open the box and get some air runes.");
		}
		else if (mysteryReward == 11) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(884, Misc.random(80));
                	c.sendMessage("You open the box and get some iron arrows.");
		}
		else if (mysteryReward == 12) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(441, Misc.random(20));
                	c.sendMessage("You open the box and get some iron ore.");
		}
		else if (mysteryReward == 13) {
                	c.getItems().deleteItem(6199, 1);
			c.getItems().addItem(537, Misc.random(3));
                	c.sendMessage("You open the box and get some dragon bones.");
		}
	}
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		if (c.getHerblore().isUnidHerb(itemId))
			c.getHerblore().handleHerbClick(itemId);
		if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		if (c.getPrayer().IsABone(itemId))
			c.getPrayer().buryBone(itemId);
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (itemId == 405) {
			c.getItems().deleteItem(405, 1);
			c.getItems().addItem(995, Misc.random(50000));
			c.sendMessage("You find some coins in the casket.");
			if (Misc.random(19) == 0) {
				c.getItems().addItem(CASKET_REWARDS[Misc.random(getLength() - 1)], 1);
				c.sendMessage("Congratulations, you find a rare item in the casket!");
			}
		}
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);
		if (itemId == 952) {
			c.sendMessage("You start digging...");
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			} else if(c.inArea(2835, 3336, 2835, 3336)) {
				c.teleTimer = 3;
				c.newLocation = 7;
			} else if(c.inArea(2834, 3336, 2834, 3336)) {
				c.teleTimer = 3;
				c.newLocation = 8;
			}
		}
	}

}
