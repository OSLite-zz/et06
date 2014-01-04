package server.game.items;

import server.game.players.Client;


import core.util.Misc;
import server.Config;

/**
 * 
 * @author Ryan / Lmctruck30 & 1% Izumi
 *
 */

public class UseItem {

	public static void ItemonObject(Client c, int objectID, int objectX, int objectY, int itemId) {
		if (!c.getItems().playerHasItem(itemId, 1))
			return;
		switch(objectID) {
		
			case 8151:
			case 8389:
				c.getFarming().checkItemOnObject(itemId);
			break;
			case 2783:
				c.getSmithingInt().showSmithInterface(itemId);
			break;
			case 409:
				if (c.getPrayer().IsABone(itemId))
					c.getPrayer().bonesOnAltar(itemId);
			break;
		default:
			if(c.playerRights == 3)
				Misc.println("Player At Object id: "+objectID+" with Item id: "+itemId);
			break;
		}
		
	}

	public static void ItemonItem(Client c, int itemUsed, int useWith) {
		if (itemUsed == 227 || useWith == 227)
			c.getHerblore().handlePotMaking(itemUsed,useWith);
		if (c.getItems().getItemName(itemUsed).contains("(") && c.getItems().getItemName(useWith).contains("("))
			c.getPotMixing().mixPotion2(itemUsed, useWith);
		if (itemUsed == 1733 || useWith == 1733)
			c.getCrafting().handleLeather(itemUsed, useWith);
		if (itemUsed == 1755 || useWith == 1755)
			c.getCrafting().handleChisel(itemUsed,useWith);
		if ((itemUsed == 1540 && useWith == 11286) || (itemUsed == 11286 && useWith == 1540)) {
			if (c.playerLevel[c.playerSmithing] >= 95) {
				c.getItems().deleteItem(1540, c.getItems().getItemSlot(1540), 1);
				c.getItems().deleteItem(11286, c.getItems().getItemSlot(11286), 1);
				c.getItems().addItem(11284,1);
				c.sendMessage("You combine the two materials to create a dragonfire shield.");
				c.getPA().addSkillXP(500 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
			} else {
				c.sendMessage("You need a smithing level of 95 to create a dragonfire shield.");
			}
		}
		if (itemUsed == 9142 && useWith == 9190 || itemUsed == 9190 && useWith == 9142) {
			if (c.playerLevel[c.playerFletching] >= 58) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9241, boltsMade);
				c.getPA().addSkillXP(boltsMade * 6 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 58 to fletch this item.");
			}
		}
		if (itemUsed == 9143 && useWith == 9191 || itemUsed == 9191 && useWith == 9143) {
			if (c.playerLevel[c.playerFletching] >= 63) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9242, boltsMade);
				c.getPA().addSkillXP(boltsMade * 7 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 63 to fletch this item.");
			}		
		}
		if (itemUsed == 9143 && useWith == 9192 || itemUsed == 9192 && useWith == 9143) {
			if (c.playerLevel[c.playerFletching] >= 65) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9243, boltsMade);
				c.getPA().addSkillXP(boltsMade * 7 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 65 to fletch this item.");
			}		
		}
		if (itemUsed == 9144 && useWith == 9193 || itemUsed == 9193 && useWith == 9144) {
			if (c.playerLevel[c.playerFletching] >= 71) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9244, boltsMade);
				c.getPA().addSkillXP(boltsMade * 10 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 71 to fletch this item.");
			}		
		}
		if (itemUsed == 9144 && useWith == 9194 || itemUsed == 9194 && useWith == 9144) {
			if (c.playerLevel[c.playerFletching] >= 58) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9245, boltsMade);
				c.getPA().addSkillXP(boltsMade * 13 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 58 to fletch this item.");
			}		
		}
		if (itemUsed == 1601 && useWith == 1755 || itemUsed == 1755 && useWith == 1601) {
			if (c.playerLevel[c.playerFletching] >= 63) {
				c.getItems().deleteItem(1601, c.getItems().getItemSlot(1601), 1);
				c.getItems().addItem(45, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 63 to fletch this item.");
			}
		}
		if (itemUsed == 1607 && useWith == 1755 || itemUsed == 1755 && useWith == 1607) {
			if (c.playerLevel[c.playerFletching] >= 65) {
				c.getItems().deleteItem(1607, c.getItems().getItemSlot(1607), 1);
				c.getItems().addItem(9189, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 65 to fletch this item.");
			}
		}
		if (itemUsed == 1605 && useWith == 1755 || itemUsed == 1755 && useWith == 1605) {
			if (c.playerLevel[c.playerFletching] >= 71) {
				c.getItems().deleteItem(1605, c.getItems().getItemSlot(1605), 1);
				c.getItems().addItem(9190, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 71 to fletch this item.");
			}
		}
		if (itemUsed == 1603 && useWith == 1755 || itemUsed == 1755 && useWith == 1603) {
			if (c.playerLevel[c.playerFletching] >= 73) {
				c.getItems().deleteItem(1603, c.getItems().getItemSlot(1603), 1);
				c.getItems().addItem(9191, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 73 to fletch this item.");
			}
		}
		if (itemUsed == 1615 && useWith == 1755 || itemUsed == 1755 && useWith == 1615) {
			if (c.playerLevel[c.playerFletching] >= 73) {
				c.getItems().deleteItem(1615, c.getItems().getItemSlot(1615), 1);
				c.getItems().addItem(9193, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 73 to fletch this item.");
			}
		}
		if (itemUsed >= 11710 && itemUsed <= 11714 && useWith >= 11710 && useWith <= 11714) {
			if (c.getItems().hasAllShards()) {
				c.getItems().makeBlade();
			}		
		}
		if (itemUsed == 2368 && useWith == 2366 || itemUsed == 2366 && useWith == 2368) {
			c.getItems().deleteItem(2368, c.getItems().getItemSlot(2368),1);
			c.getItems().deleteItem(2366, c.getItems().getItemSlot(2366),1);
			c.getItems().addItem(1187,1);
		}
		
		if (c.getItems().isHilt(itemUsed) || c.getItems().isHilt(useWith)) {
			int hilt = c.getItems().isHilt(itemUsed) ? itemUsed : useWith;
			int blade = c.getItems().isHilt(itemUsed) ? useWith : itemUsed;
			if (blade == 11690) {
				c.getItems().makeGodsword(hilt);
			}
		}
		if (itemUsed == 187 && useWith == 1205) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1205,1);
				c.getItems().addItem(1221,1);
		
		}
if (itemUsed == 187 && useWith == 1237) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1237,1);
				c.getItems().addItem(1251,1);
		
		}

if (itemUsed == 187 && useWith == 11367) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(11367,1);
				c.getItems().addItem(11379,1);
		
		}

//iron
if (itemUsed == 187 && useWith == 1203) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1203,1);
				c.getItems().addItem(1219,1);
		
		}

if (itemUsed == 187 && useWith == 1239) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1239,1);
				c.getItems().addItem(1253,1);
		
		}

if (itemUsed == 187 && useWith == 11369) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(11369,1);
				c.getItems().addItem(11386,1);
		
		}

//steel
if (itemUsed == 187 && useWith == 1207) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1207,1);
				c.getItems().addItem(1223,1);
		
		}

if (itemUsed == 187 && useWith == 1241) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1241,1);
				c.getItems().addItem(1255,1);
		
		}

if (itemUsed == 187 && useWith == 11371) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(11371,1);
				c.getItems().addItem(11393,1);
		
		}

//black
if (itemUsed == 187 && useWith == 1217) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1217,1);
				c.getItems().addItem(1233,1);
		
		}

if (itemUsed == 187 && useWith == 4580) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(4580,1);
				c.getItems().addItem(4582,1);
		
		}
//mith
if (itemUsed == 187 && useWith == 1209) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1209,1);
				c.getItems().addItem(1225,1);
		
		}

if (itemUsed == 187 && useWith == 1243) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1243,1);
				c.getItems().addItem(1257,1);
		
		}

if (itemUsed == 187 && useWith == 11373) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(11373,1);
				c.getItems().addItem(11400,1);
		
		}

//addy
if (itemUsed == 187 && useWith == 1211) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1211,1);
				c.getItems().addItem(1227,1);
		
		}

if (itemUsed == 187 && useWith == 1245) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1245,1);
				c.getItems().addItem(1259,1);
		
		}

if (itemUsed == 187 && useWith == 11375) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(11375,1);
				c.getItems().addItem(11407,1);
		
		}

//rune
if (itemUsed == 187 && useWith == 1213) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1213,1);
				c.getItems().addItem(1229,1);
		
		}

if (itemUsed == 187 && useWith == 1247) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1247,1);
				c.getItems().addItem(1261,1);
		
		}

if (itemUsed == 187 && useWith == 11377) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(11377,1);
				c.getItems().addItem(11414,1);
		
		}

//drag
if (itemUsed == 187 && useWith == 1215) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1215,1);
				c.getItems().addItem(1231,1);
		
		}

if (itemUsed == 187 && useWith == 1249) {
				c.getItems().deleteItem(187,1);
				c.getItems().deleteItem(1249,1);
				c.getItems().addItem(1263,1);
		
		}

//(+)

if (itemUsed == 5937 && useWith == 1205) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1205,1);
				c.getItems().addItem(5670,1);
		
		}
if (itemUsed == 5937 && useWith == 1237) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1237,1);
				c.getItems().addItem(5704,1);
		
		}

if (itemUsed == 5937 && useWith == 11367) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(11367,1);
				c.getItems().addItem(11382,1);
		
		}

//iron
if (itemUsed == 5937 && useWith == 1203) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1203,1);
				c.getItems().addItem(5668,1);
		
		}

if (itemUsed == 5937 && useWith == 1239) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1239,1);
				c.getItems().addItem(5706,1);
		
		}

if (itemUsed == 5937 && useWith == 11369) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(11369,1);
				c.getItems().addItem(11389,1);
		
		}

//steel
if (itemUsed == 5937 && useWith == 1207) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1207,1);
				c.getItems().addItem(5672,1);
		
		}

if (itemUsed == 5937 && useWith == 1241) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1241,1);
				c.getItems().addItem(5708,1);
		
		}

if (itemUsed == 5937 && useWith == 11371) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(11371,1);
				c.getItems().addItem(11396,1);
		
		}

//black
if (itemUsed == 5937 && useWith == 1217) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1217,1);
				c.getItems().addItem(5682,1);
		
		}

if (itemUsed == 5937 && useWith == 4580) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(4580,1);
				c.getItems().addItem(5734,1);
		
		}
//mith
if (itemUsed == 5937 && useWith == 1209) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1209,1);
				c.getItems().addItem(5674,1);
		
		}

if (itemUsed == 5937 && useWith == 1243) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1243,1);
				c.getItems().addItem(5710,1);
		
		}

if (itemUsed == 5937 && useWith == 11373) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(11373,1);
				c.getItems().addItem(11403,1);
		
		}

//addy
if (itemUsed == 5937 && useWith == 1211) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1211,1);
				c.getItems().addItem(5676,1);
		
		}

if (itemUsed == 5937 && useWith == 1245) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1245,1);
				c.getItems().addItem(5712,1);
		
		}

if (itemUsed == 5937 && useWith == 11375) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(11375,1);
				c.getItems().addItem(11410,1);
		
		}

//rune
if (itemUsed == 5937 && useWith == 1213) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1213,1);
				c.getItems().addItem(5678,1);
		
		}

if (itemUsed == 5937 && useWith == 1247) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1247,1);
				c.getItems().addItem(5714,1);
		
		}

if (itemUsed == 5937 && useWith == 11377) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(11377,1);
				c.getItems().addItem(11417,1);
		
		}

//drag
if (itemUsed == 5937 && useWith == 1215) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1215,1);
				c.getItems().addItem(5680,1);
		
		}

if (itemUsed == 5937 && useWith == 1249) {
				c.getItems().deleteItem(5937,1);
				c.getItems().deleteItem(1249,1);
				c.getItems().addItem(5716,1);
		
		}


//(+)

if (itemUsed == 5940 && useWith == 1205) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1205,1);
				c.getItems().addItem(5688,1);
		
		}
if (itemUsed == 5940 && useWith == 1237) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1237,1);
				c.getItems().addItem(5718,1);
		
		}

if (itemUsed == 5940 && useWith == 11367) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(11367,1);
				c.getItems().addItem(11384,1);
		
		}

//iron
if (itemUsed == 5940 && useWith == 1203) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1203,1);
				c.getItems().addItem(5686,1);
		
		}

if (itemUsed == 5940 && useWith == 1239) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1239,1);
				c.getItems().addItem(5720,1);
		
		}

if (itemUsed == 5940 && useWith == 11369) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(11369,1);
				c.getItems().addItem(11391,1);
		
		}

//steel
if (itemUsed == 5940 && useWith == 1207) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1207,1);
				c.getItems().addItem(5690,1);
		
		}

if (itemUsed == 5940 && useWith == 1241) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1241,1);
				c.getItems().addItem(5722,1);
		
		}

if (itemUsed == 5940 && useWith == 11371) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(11371,1);
				c.getItems().addItem(11398,1);
		
		}

//black
if (itemUsed == 5940 && useWith == 1217) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1217,1);
				c.getItems().addItem(5700,1);
		
		}

if (itemUsed == 5940 && useWith == 4580) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(4580,1);
				c.getItems().addItem(5736,1);
		
		}
//mith
if (itemUsed == 5940 && useWith == 1209) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1209,1);
				c.getItems().addItem(5692,1);
		
		}

if (itemUsed == 5940 && useWith == 1243) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1243,1);
				c.getItems().addItem(5724,1);
		
		}

if (itemUsed == 5940 && useWith == 11373) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(11373,1);
				c.getItems().addItem(11405,1);
		
		}

//addy
if (itemUsed == 5940 && useWith == 1211) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1211,1);
				c.getItems().addItem(5694,1);
		
		}

if (itemUsed == 5940 && useWith == 1245) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1245,1);
				c.getItems().addItem(5726,1);
		
		}

if (itemUsed == 5940 && useWith == 11375) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(11375,1);
				c.getItems().addItem(11412,1);
		
		}

//rune
if (itemUsed == 5940 && useWith == 1213) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1213,1);
				c.getItems().addItem(5696,1);
		
		}

if (itemUsed == 5940 && useWith == 1247) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1247,1);
				c.getItems().addItem(5728,1);
		
		}

if (itemUsed == 5940 && useWith == 11377) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(11377,1);
				c.getItems().addItem(11419,1);
		
		}

//drag
if (itemUsed == 5940 && useWith == 1215) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1215,1);
				c.getItems().addItem(5698,1);
		
		}

if (itemUsed == 5940 && useWith == 1249) {
				c.getItems().deleteItem(5940,1);
				c.getItems().deleteItem(1249,1);
				c.getItems().addItem(5730,1);
		
		}

		
		switch(itemUsed) {
			
		default:
			if(c.playerRights == 3)
				Misc.println("Player used Item id: "+itemUsed+" with Item id: "+useWith);
			break;
		}	
	}
	public static void ItemonNpc(Client c, int itemId, int npcId, int slot) {
		switch(itemId) {
		
		default:
			if(c.playerRights == 3)
				Misc.println("Player used Item id: "+itemId+" with Npc id: "+npcId+" With Slot : "+slot);
			break;
		}
		
	}


}
