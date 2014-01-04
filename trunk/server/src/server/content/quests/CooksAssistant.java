package server.content.quests;

import server.Server;
import server.game.npcs.*;
import server.game.players.Client;
import server.game.players.Player;

/**
* Cooks' Assistant
* @author Izumi
*/

public class CooksAssistant {

	public Client client;
	
	public CooksAssistant(Client client) {
		this.client = client;
	}
	
	public void showInformation() {
		for(int i = 8144; i < 8195; i++) {
			client.getPA().sendFrame126("", i);
		}
		client.getPA().sendFrame126("@dre@Cook's Assistant", 8144);
		client.getPA().sendFrame126("", 8145);
		if(client.cooksA == 0) {
			client.getPA().sendFrame126("To start the quest, you should talk with Cook", 8147);
			client.getPA().sendFrame126("found in Lumbridge.", 8148);
		} else if(client.cooksA == 1) {
			client.getPA().sendFrame126("@str@To start the quest, you should talk with Cook", 8147);
			client.getPA().sendFrame126("@str@found in Lumbridge.", 8148);
			client.getPA().sendFrame126("", 8149);
			client.getPA().sendFrame126("The cook has asked you to get the following items:", 8150);
			client.getPA().sendFrame126("A bucket of milk", 8151);
			client.getPA().sendFrame126("A pot of flour", 8152);
			client.getPA().sendFrame126("An egg", 8153);
		} else if(client.cooksA == 2) {
			client.getPA().sendFrame126("@str@To start the quest, you should talk with Cook", 8147);
			client.getPA().sendFrame126("@str@found in Lumbridge.", 8148);
			client.getPA().sendFrame126("", 8149);
			client.getPA().sendFrame126("@str@Cook have asked you to get the following items:", 8150);
			client.getPA().sendFrame126("@str@A bucket of milk", 8151);
			client.getPA().sendFrame126("@str@A pot of Flour", 8152);
			client.getPA().sendFrame126("@str@An egg", 8153);
		} else if(client.cooksA == 3) {
			client.getPA().sendFrame126("@str@To start the quest, you should talk with Cook", 8147);
			client.getPA().sendFrame126("@str@found in Lumbridge.", 8148);
			client.getPA().sendFrame126("", 8149);
			client.getPA().sendFrame126("@str@Cook have asked you to get the following items:", 8150);
			client.getPA().sendFrame126("@str@A bucket of milk", 8151);
			client.getPA().sendFrame126("@str@A pot of flour", 8152);
			client.getPA().sendFrame126("@str@An egg", 8153);
			client.getPA().sendFrame126("", 8154);
			client.getPA().sendFrame126("You have completed this quest!", 8155);
			client.getPA().sendFrame126("The cook gave you some cooking experience as a reward.", 8156);
		}
		client.getPA().showInterface(8134);
	}
	
}
