package server.game.players.packets;

import core.util.Misc;
import server.game.players.Client;
import server.game.players.PacketType;
import server.Server;
/**
 * Chat
 **/
public class ClanChat implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		String textSent = Misc.longToPlayerName2(c.getInStream().readQWord());
		textSent = textSent.replaceAll("_", " ");
		//c.sendMessage(textSent);
		Server.clanChat.handleClanChat(c, textSent);
	}	
}
