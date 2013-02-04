package server.game.players.packets;

/**
 * @author Ryan / Lmctruck30
 */

import server.game.items.UseItem;
import server.content.skills.*;
import server.game.players.Client;
import server.game.players.PacketType;

public class ItemOnObject implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		/*
		 * a = ?
		 * b = ?
		 */
		
		int a = c.getInStream().readUnsignedWord();
		int objectId = c.getInStream().readSignedWordBigEndian();
		int objectY = c.getInStream().readSignedWordBigEndianA();
		int b = c.getInStream().readUnsignedWord();
		int objectX = c.getInStream().readSignedWordBigEndianA();
		int itemId = c.getInStream().readUnsignedWord();
		if(!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		c.cookingCoords[0] = objectX;
		c.cookingCoords[1] = objectY;
		c.turnPlayerTo(objectX, objectY);
		UseItem.ItemonObject(c, objectId, objectX, objectY, itemId);
		switch (objectId) {
		case 12269:
		case 2732:
		c.startAnimation(897);
		case 114:
		case 9374:
		case 2728:
		case 25465:
		case 11404:
		case 11405:
		case 11406:
			Cooking.cookThisFood(c, itemId, objectId); 
			break;
	case 8748:
		if (!c.getItems().playerHasItem(1779, 1)) {
		c.sendMessage("You need some flax.");
		return;
	}
		c.startAnimation(894);
		c.getItems().addItem(1777, 1);
		c.getItems().deleteItem(1779, c.getItems().getItemSlot(1779), 1);
		c.sendMessage("You make a bow string from the flax.");
	break;

case 8747:
if (!c.getItems().playerHasItem(1925, 1)) {
c.sendMessage("You need an empty bucket.");
return;
}
c.startAnimation(832);
c.getItems().addItem(1929, 1);
c.getItems().deleteItem(1925, c.getItems().getItemSlot(1925), 1);
c.sendMessage("You fill the bucket up with water.");
break;


		}

	}
}
