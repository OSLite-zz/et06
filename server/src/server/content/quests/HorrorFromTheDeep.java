package server.content.quests;

import server.Server;
import server.game.npcs.*;
import server.game.players.Client;
import server.game.players.Player;

/**
* Horror from the Deep
* @author Ian
*/

public class HorrorFromTheDeep {

	public Client client;
	
	public HorrorFromTheDeep(Client client) {
		this.client = client;
	}
	
	public void changeMother(int i) {
		int npcType = getNextDag(Server.npcHandler.npcs[i].npcType);
		int x = Server.npcHandler.npcs[i].absX;
		int y = Server.npcHandler.npcs[i].absY;
		int heightLevel = Server.npcHandler.npcs[i].heightLevel;
		int WalkingType = Server.npcHandler.npcs[i].walkingType;
		int HP = Server.npcHandler.npcs[i].HP;
		int MaxHP = Server.npcHandler.npcs[i].MaxHP;
		int maxHit = Server.npcHandler.npcs[i].maxHit;
		int attack = Server.npcHandler.npcs[i].attack;
		int defence = Server.npcHandler.npcs[i].defence;
		Server.npcHandler.npcs[i].absX = 0;
		Server.npcHandler.npcs[i].absY = 0;
		Server.npcHandler.npcs[i] = null;
		int slot = -1;
		for (int j = 1; j < Server.npcHandler.maxNPCs; j++) {
			if (Server.npcHandler.npcs[j] == null) {
				slot = j;
				break;
			}
		}

		if(slot == -1) return;
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = MaxHP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		Server.npcHandler.npcs[slot] = newNPC;
		Server.npcHandler.npcs[slot].updateRequired = true;
		client.getPA().requestUpdates();
		Server.npcHandler.npcs[slot].spawnedBy = client.playerId;
		Server.npcHandler.npcs[slot].killerId = client.playerId;
		Server.npcHandler.npcs[slot].underAttack = true;
		Server.npcHandler.npcs[slot].dagColor = getDagColor(npcType);
		client.getPA().drawHeadicon(1, slot, 0, 0);
		client.sendMessage("Color: "+Server.npcHandler.npcs[slot].dagColor);
	}
	
	public int getNextDag(int lastDag) {
		switch(lastDag) {
			case 1351:
				return 1352;
			case 1352:
				return 1356;
			case 1356:
				return 1353;
			case 1353:
				return 1354;
			case 1354:
				return 1355;
			case 1355:
				return 1351;
			default:
			return -1;
		}
	}
	
	public String getDagColor(int dag) {
		switch(dag) {
			case 1351:
				return "white";
			case 1352:
				return "blue";
			case 1356:
				return "brown";
			case 1353:
				return "red";
			case 1354:
				return "orange";
			case 1355:
				return "green";
			default:
			return "";
		}
	}
	
	public void showInformation() {
		for(int i = 8144; i < 8195; i++) {
			client.getPA().sendFrame126("", i);
		}
		client.getPA().sendFrame126("@dre@Horror from the Deep", 8144);
		client.getPA().sendFrame126("", 8145);
		if(client.horrorFromDeep == 0) {
			client.getPA().sendFrame126("Talk to Jossik in Draynor", 8147);
			client.getPA().sendFrame126("To start this quest.", 8148);
		} else if(client.horrorFromDeep == 1) {
			client.getPA().sendFrame126("@str@Talk to Jossik in Draynor", 8147);
			client.getPA().sendFrame126("@str@To start this quest.", 8148);
			client.getPA().sendFrame126("", 8149);
			client.getPA().sendFrame126("Talk to Jossik to refight", 8150);
			client.getPA().sendFrame126("The Dagannoth mother.", 8151);
		} else if(client.horrorFromDeep == 2) {
			client.getPA().sendFrame126("@str@Talk to Jossik in Draynor", 8147);
			client.getPA().sendFrame126("@str@To start this quest.", 8148);
			client.getPA().sendFrame126("", 8149);
			client.getPA().sendFrame126("@str@Talk to Jossik to refight", 8150);
			client.getPA().sendFrame126("@str@The Dagannoth mother.", 8151);
			client.getPA().sendFrame126("", 8152);
			client.getPA().sendFrame126("You have completed this quest!", 8153);
		}
		client.getPA().showInterface(8134);
	}
	
	public boolean checkForDagChange(int i) {
		int[] dags = {
			1351, 1352, 1353, 1354, 1355, 1356
		};
		for(int dag : dags) {
			if(Server.npcHandler.npcs[i] != null) {
				if(Server.npcHandler.npcs[i].npcType == dag && !Server.npcHandler.npcs[i].isDead && Server.npcHandler.npcs[i].killerId > 0) {
					if(client.lastDagChange == -1) {
						client.lastDagChange = System.currentTimeMillis();
					}
					if(System.currentTimeMillis() - client.lastDagChange >= 10000) {
						changeMother(i);
						client.lastDagChange = System.currentTimeMillis();
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
