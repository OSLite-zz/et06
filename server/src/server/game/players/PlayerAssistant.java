package server.game.players;

import server.Config;

import server.content.skills.*;
import server.Server;
import server.game.npcs.NPCHandler;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;
import core.util.Misc;

public class PlayerAssistant{
	
	private Client c;
	public PlayerAssistant(Client Client) {
		this.c = Client;
	}
	
	public int CraftInt, Dcolor, FletchInt;
	
	/**
	 * MulitCombat icon
	 * @param i1 0 = off 1 = on
	 */
	public void multiWay(int i1) {
		//synchronized(c) {
			c.outStream.createFrame(61);
			c.outStream.writeByte(i1);
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
		
	}
	
	public void setSidebarInterfaces(Client c) {
			int[] inter = {
				2434,	//Attack
				3917,	//Skill
				638,	//Quest
				3213,	//Inventory
				1644,	//Equip
				5608,	//Prayer 5608, 22500
				-1,	//Magic
				-1,	//Clan chat
				5065,	//Friends
				5715,	//Ignores
				2449,	//Logout
				904,	//Settings
				147,	//Animation
				29500,	//Music
			};
			for(int i = 0; i < 14; i++) {
				c.setSidebarInterface(i, inter[i]);
			}
			if(c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855);
			} else if(c.playerMagicBook == 2) {
				c.setSidebarInterface(6, 16640);
			} else {
				c.setSidebarInterface(6, 1151);
			}
			c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));	
	}
	
	public void writeEnergy() {
		sendFrame126(c.playerEnergy + "%", 149);
	}
	
	public int totalLevel() {
		int total = 0;
		for(int i = 0; i <= 20; i++) {
			total += getLevelForXP(c.playerXP[i]);
		}
		return total;
	}
	
	public int xpTotal() {
		int xp = 0;
		for(int i = 0; i <= 20; i++) {
			xp += c.playerXP[i];
		}
		return xp;
	}
	
	public void resting() {
		c.isResting = true;
		c.startAnimation(4853);
	}
	
	public int raiseTimer() {
		if(!c.isResting) 
			if (c.playerLevel[16] >= 2 && c.playerLevel[16] < 10)
				return 6500;
			if (c.playerLevel[16] >= 10 && c.playerLevel[16] < 25)
				return 6000;
			if (c.playerLevel[16] >= 25 && c.playerLevel[16] < 40)
				return 5500;
			if (c.playerLevel[16] >= 40 && c.playerLevel[16] < 55)
				return 5000;
			if (c.playerLevel[16] >= 55 && c.playerLevel[16] < 70)
				return 4500;
			if (c.playerLevel[16] >= 70 && c.playerLevel[16] < 85)
				return 4000;
			if (c.playerLevel[16] >= 85 && c.playerLevel[16] < 99)
				return 3500;
			if (c.playerLevel[16] == 99)
				return 3000;
			return 7000;
	}
		
	public int raiseTimer2() {
		if (c.isResting) 
			if (c.playerLevel[16] >= 2 && c.playerLevel[16] < 10)
				return 2250;
			if (c.playerLevel[16] >= 10 && c.playerLevel[16] < 25)
				return 2000;
			if (c.playerLevel[16] >= 25 && c.playerLevel[16] < 40)
				return 1750;
			if (c.playerLevel[16] >= 40 && c.playerLevel[16] < 55)
				return 1500;
			if (c.playerLevel[16] >= 55 && c.playerLevel[16] < 70)
				return 1250;
			if (c.playerLevel[16] >= 70 && c.playerLevel[16] < 85)
				return 1000;
			if (c.playerLevel[16] >= 85 && c.playerLevel[16] < 99)
				return 750;
			if (c.playerLevel[16] == 99)
				return 500;
			return 2500;			
	}
	
	public void playerWalk(int x, int y) {
		PathFinder.getPathFinder().findRoute(c, x, y, true, 1, 1);
	}
	
	int tmpNWCX[] = new int[50];
 	int tmpNWCY[] = new int[50];

	
	
	public void clearClanChat() {
		c.clanId = -1;
		c.getPA().sendFrame126("Talking in: ", 18139);
		c.getPA().sendFrame126("Owner: ", 18140);
		for (int j = 18144; j < 18244; j++)
			c.getPA().sendFrame126("", j);
	}
	
	/*public void resetAutocast() {
		c.autocastId = 0;
		c.autocasting = false;
		c.getPA().sendFrame36(108, 0);
	}*/
	
	public void resetAutocast() {
		c.autocastId = -1;
		c.autocasting = false;
		c.setSidebarInterface(0, 328);
		c.getPA().sendFrame36(108, 0);
		c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
	}
	
	public int getItemSlot(int itemID) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if ((c.playerItems[i] - 1) == itemID) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isItemInBag(int itemID) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if ((c.playerItems[i] - 1) == itemID) {
				return true;
			}
		}
		return false;
	}
	
	public int freeSlots() {
		int freeS = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}
	
	public void turnTo(int pointX, int pointY) {
		c.focusPointX = 2 * pointX + 1;
		c.focusPointY = 2 * pointY + 1;
		c.updateRequired = true;
	}
	
		public void movePlayer(int x, int y, int h) {
		c.resetWalkingQueue();
		c.teleportToX = x;
        c.teleportToY = y;
		c.heightLevel = h;
		requestUpdates();
	}
	
	public int getX() {
		return absX;
	}
	
	public int getY() {
		return absY;
	}
	
	public int absX, absY;
	public int heightLevel;
	public static void QuestReward(Client c, String questName, int PointsGain, String Line1, String Line2, String Line3, String Line4, String Line5, String Line6, int itemID) {
		c.getPA().sendFrame126("You have completed "+questName+"!",12144);
		sendQuest(c, ""+(c.questPoints),12147);
		//c.QuestPoints += PointsGain;
		sendQuest(c, Line1,12150);
		sendQuest(c, Line2,12151);
		sendQuest(c, Line3,12152);
		sendQuest(c, Line4,12153);
		sendQuest(c, Line5,12154);
		sendQuest(c, Line6,12155);
		c.getPlayerAssistant().sendFrame246(12145, 250, itemID);
		showInterface(c, 12140);
		Server.getStillGraphicsManager().stillGraphics(c,c.getX(), c.getY(),c.getHeightLevel(), 199, 0);
	}
	
	public static void showInterface(Client client, int i) {
		client.getOutStream().createFrame(97);
		client.getOutStream().writeWord(i);
		client.flushOutStream();
	}
	
	public static void sendQuest(Client client, String s, int i) {
		client.getOutStream().createFrameVarSizeWord(126);
		client.getOutStream().writeString(s);
		client.getOutStream().writeWordA(i);
		client.getOutStream().endFrameVarSizeWord();
		client.flushOutStream();
	}
	
	public void sendStillGraphics(int id, int heightS, int y, int x, int timeBCS) {
		c.getOutStream().createFrame(85);
		c.getOutStream().writeByteC(y - (c.mapRegionY * 8));
		c.getOutStream().writeByteC(x - (c.mapRegionX * 8));
		c.getOutStream().createFrame(4);
		c.getOutStream().writeByte(0);// Tiles away (X >> 4 + Y & 7)
											// //Tiles away from
		// absX and absY.
		c.getOutStream().writeWord(id); // Graphic ID.
		c.getOutStream().writeByte(heightS); // Height of the graphic when
													// cast.
		c.getOutStream().writeWord(timeBCS); // Time before the graphic
													// plays.
		c.flushOutStream();
	}
	
	public void createArrow(int type, int id) {
	if(c != null){
		c.getOutStream().createFrame(254); //The packet ID
		c.getOutStream().writeByte(type); //1=NPC, 10=Player
		c.getOutStream().writeWord(id); //NPC/Player ID
		c.getOutStream().write3Byte(0); //Junk
	}
	}
	public void createArrow(int x, int y, int height, int pos) {
	if(c != null){
		c.getOutStream().createFrame(254); //The packet ID
		c.getOutStream().writeByte(pos); //Position on Square(2 = middle, 3 = west, 4 = east, 5 = south, 6 = north)
		c.getOutStream().writeWord(x); //X-Coord of Object
		c.getOutStream().writeWord(y); //Y-Coord of Object
		c.getOutStream().writeByte(height); //Height off Ground
	}
	}
	
	public void sendQuest(String s, int i) {
		c.getOutStream().createFrameVarSizeWord(126);
		c.getOutStream().writeString(s);
		c.getOutStream().writeWordA(i);
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
	}
	   public void handleTiara() {
    	int[] tiaras = {5527, 5529, 5531, 5535, 5537, 5533, 5539, 5543, 5541, 5545, 5547};
    	if (c.wearId >= tiaras[0] && c.wearId <= tiaras[10]) {
    		for (int i = 0; i < tiaras.length; i++) {
    			if (c.wearId == tiaras[i]) {
    				int tempInt = 1;
    				int loc = i;
    				while (loc > 0) {
    					tempInt *= 2;
    					loc--;
    				}
    				c.getPA().setConfig(491, tempInt);
    			}
    		}
    	}
    }
	  public void setConfig(int id, int state) {
		c.outStream.createFrame(36);
		c.outStream.writeWordBigEndian(id);
		c.outStream.writeByte(state);
	}
	/**
	* Quest tab information
	**/
	public void loadQuests() {		
		//c.getAA2().sendQuestTab();
		/*c.getPA().sendFrame126("QUESTS:", 663);
		
		c.getPA().sendFrame126("Black Knights' Fortress", 7332);
		if (c.cooksA == 1) {
			c.getPA().sendFrame126("@yel@Cook's Assistant", 7333);
		}
		if (c.cooksA == 2) {
			c.getPA().sendFrame126("@yel@Cook's Assistant", 7333);
		}
		if (c.cooksA == 3) {
			c.getPA().sendFrame126("@gre@Cook's Assistant", 7333);
		}
		c.getPA().sendFrame126("Demon Slayer", 7334);
		c.getPA().sendFrame126("Dragon Slayer", 7335);
		c.getPA().sendFrame126("Dwarf Cannon", 7336);
		c.getPA().sendFrame126("Herpquest", 7339);
		c.getPA().sendFrame126("", 7338);
		c.getPA().sendFrame126("", 7340);
		c.getPA().sendFrame126("", 7346);
		c.getPA().sendFrame126("", 7341);
		c.getPA().sendFrame126("", 7342);
		c.getPA().sendFrame126("", 7337);
		c.getPA().sendFrame126("", 7343);
		c.getPA().sendFrame126("", 7335);
		c.getPA().sendFrame126("", 7344);
		c.getPA().sendFrame126("", 7345);
		c.getPA().sendFrame126("", 7347);
		c.getPA().sendFrame126("", 7348);
		/*Members Quests*/
		/*sendFrame126("", 12772);
		
		// unknown id
		c.getPA().sendFrame126("", 7352);
		c.getPA().sendFrame126("", 12129);
		c.getPA().sendFrame126("", 8438);
		c.getPA().sendFrame126("", 12852);
		c.getPA().sendFrame126("", 7354);
		c.getPA().sendFrame126("", 7355);
		c.getPA().sendFrame126("", 7356);
		c.getPA().sendFrame126("", 8679);
		c.getPA().sendFrame126("", 7459);
		c.getPA().sendFrame126("", 7357);
		c.getPA().sendFrame126("", 12836);
		c.getPA().sendFrame126("", 7358);
		c.getPA().sendFrame126("", 7359);
		c.getPA().sendFrame126("", 14169);
		c.getPA().sendFrame126("", 10115);
		c.getPA().sendFrame126("", 14604);
		c.getPA().sendFrame126("", 7360);
		c.getPA().sendFrame126("", 12282);
		c.getPA().sendFrame126("", 13577);
		c.getPA().sendFrame126("", 12839);
		c.getPA().sendFrame126("", 7361);
		c.getPA().sendFrame126("", 11857);
		c.getPA().sendFrame126("", 7362);
		c.getPA().sendFrame126("", 7363);
		c.getPA().sendFrame126("", 7364);
		c.getPA().sendFrame126("", 10135);
		c.getPA().sendFrame126("", 4508);
		c.getPA().sendFrame126("", 11907);
		c.getPA().sendFrame126("", 7365);
		c.getPA().sendFrame126("", 7366);
		c.getPA().sendFrame126("", 7367);
		c.getPA().sendFrame126("", 13389);
		c.getPA().sendFrame126("", 7368);
		c.getPA().sendFrame126("", 11132);
		c.getPA().sendFrame126("", 7369);
		c.getPA().sendFrame126("", 12389);
		c.getPA().sendFrame126("", 13974);
		c.getPA().sendFrame126("", 7370);
		c.getPA().sendFrame126("", 8137);
		c.getPA().sendFrame126("", 7371);
		c.getPA().sendFrame126("", 12345);
		c.getPA().sendFrame126("", 7372);
		c.getPA().sendFrame126("", 8115);
		// unknown id
		c.getPA().sendFrame126("", 8576);
		c.getPA().sendFrame126("", 12139);
		c.getPA().sendFrame126("", 7373);
		c.getPA().sendFrame126("", 7374);
		c.getPA().sendFrame126("", 8969);
		c.getPA().sendFrame126("", 7375);
		c.getPA().sendFrame126("", 7376);
		c.getPA().sendFrame126("", 1740);
		c.getPA().sendFrame126("", 3278);
		c.getPA().sendFrame126("", 7378);
		c.getPA().sendFrame126("", 6518);
		c.getPA().sendFrame126("", 7379);
		c.getPA().sendFrame126("", 7380);
		c.getPA().sendFrame126("", 7381);
		c.getPA().sendFrame126("", 11858);
		// unknown id
		c.getPA().sendFrame126("", 9927);
		c.getPA().sendFrame126("", 7349);
		c.getPA().sendFrame126("", 7350);
		c.getPA().sendFrame126("", 7351);
		c.getPA().sendFrame126("", 13356);
		//more
		c.getPA().sendFrame126("", 6024);
		c.getPA().sendFrame126("", 191);
		c.getPA().sendFrame126("", 15235);
		c.getPA().sendFrame126("", 249);
		c.getPA().sendFrame126("", 15592);
		c.getPA().sendFrame126("", 15098);
		c.getPA().sendFrame126("", 15352);
		c.getPA().sendFrame126("", 14912);
		c.getPA().sendFrame126("", 668);
		c.getPA().sendFrame126("", 18306);
		c.getPA().sendFrame126("", 15499);
		c.getPA().sendFrame126("", 18684);
		c.getPA().sendFrame126("", 6027);
		c.getPA().sendFrame126("", 15487);
		c.getPA().sendFrame126("", 18517);
		c.getPA().sendFrame126("", 16128);
		c.getPA().sendFrame126("", 6987);
		c.getPA().sendFrame126("", 16149);
		c.getPA().sendFrame126("", 15841);
		c.getPA().sendFrame126("", 7353);
		c.getPA().sendFrame126("", 17510);
		c.getPA().sendFrame126("", 673);
		/*END OF ALL QUESTS*/
	}
	
	public void sendFrame126(String s, int id) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null ) {
				c.getOutStream().createFrameVarSizeWord(126);
				c.getOutStream().writeString(s);
				c.getOutStream().writeWordA(id);
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
			}
		
	}
	
	
	public void sendLink(String s) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null ) {
				c.getOutStream().createFrameVarSizeWord(187);
				c.getOutStream().writeString(s);
			}
			
	}
	
	public void setSkillLevel(int skillNum, int currentLevel, int XP) {
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(134);
				c.getOutStream().writeByte(skillNum);
				c.getOutStream().writeDWord_v1(XP);
				c.getOutStream().writeByte(currentLevel);
				c.flushOutStream();
			}
		}
	}
	
	public void sendFrame106(int sideIcon) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(106);
				c.getOutStream().writeByteC(sideIcon);
				c.flushOutStream();
				requestUpdates();
			}
		}
	
	
	public void sendFrame107() {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(107);
				c.flushOutStream();
			}
		}
	
	public void sendFrame36(int id, int state) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(36);
				c.getOutStream().writeWordBigEndian(id);
				c.getOutStream().writeByte(state);
				c.flushOutStream();
			}
		
	}
	
	public void sendFrame185(int Frame) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(185);
				c.getOutStream().writeWordBigEndianA(Frame);
			}
		
	}
	
	public void showInterface(int interfaceid) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(97);
				c.getOutStream().writeWord(interfaceid);
				c.flushOutStream();
			
		}
	}
	
	public void sendFrame248(int MainFrame, int SubFrame) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(248);
				c.getOutStream().writeWordA(MainFrame);
				c.getOutStream().writeWord(SubFrame);
				c.flushOutStream();
			
		}
	}
	
	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(246);
				c.getOutStream().writeWordBigEndian(MainFrame);
				c.getOutStream().writeWord(SubFrame);
				c.getOutStream().writeWord(SubFrame2);
				c.flushOutStream();
			
		}
	}
	
	public void sendFrame171(int MainFrame, int SubFrame) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(171);
				c.getOutStream().writeByte(MainFrame);
				c.getOutStream().writeWord(SubFrame);
				c.flushOutStream();
			
		}
	}
	
	public void sendFrame200(int MainFrame, int SubFrame) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(200);
				c.getOutStream().writeWord(MainFrame);
				c.getOutStream().writeWord(SubFrame);
				c.flushOutStream();
			}
		}
	
	
	public void sendFrame70(int i, int o, int id) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(70);
				c.getOutStream().writeWord(i);
				c.getOutStream().writeWordBigEndian(o);
				c.getOutStream().writeWordBigEndian(id);
				c.flushOutStream();
			}
		
	}

	public void sendFrame75(int MainFrame, int SubFrame) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(75);
				c.getOutStream().writeWordBigEndianA(MainFrame);
				c.getOutStream().writeWordBigEndianA(SubFrame);
				c.flushOutStream();
			}
		
	}
	
	public void sendFrame164(int Frame) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(164);
				c.getOutStream().writeWordBigEndian_dup(Frame);
				c.flushOutStream();
			}
		
	}
	
	public void setPrivateMessaging(int i) { // friends and ignore list status
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
		        c.getOutStream().createFrame(221);
		        c.getOutStream().writeByte(i);
				c.flushOutStream();
			}
		
    }
	
	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(206);
				c.getOutStream().writeByte(publicChat);	
				c.getOutStream().writeByte(privateChat);	
				c.getOutStream().writeByte(tradeBlock);
				c.flushOutStream();
			}
		
	}
	
	public void sendFrame87(int id, int state) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(87);
				c.getOutStream().writeWordBigEndian_dup(id);	
				c.getOutStream().writeDWord_v1(state);
				c.flushOutStream();
			}
		
	}
	
	public void sendPM(long name, int rights, byte[] chatmessage, int messagesize) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrameVarSize(196);
				c.getOutStream().writeQWord(name);
				c.getOutStream().writeDWord(c.lastChatId++);
				c.getOutStream().writeByte(rights);
				c.getOutStream().writeBytes(chatmessage, messagesize, 0);
				c.getOutStream().endFrameVarSize();
				c.flushOutStream();
				//String chatmessagegot = Misc.textUnpack(chatmessage, messagesize);
				//String target = Misc.longToPlayerName(name);
			}	
		
	}
	
	public void createPlayerHints(int type, int id) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(254);
				c.getOutStream().writeByte(type);
				c.getOutStream().writeWord(id); 
				c.getOutStream().write3Byte(0);
				c.flushOutStream();
			}
		
	}

	public void createObjectHints(int x, int y, int height, int pos) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(254);
				c.getOutStream().writeByte(pos);
				c.getOutStream().writeWord(x);
				c.getOutStream().writeWord(y);
				c.getOutStream().writeByte(height);
				c.flushOutStream();
			}
		
	}
	
	public void loadPM(long playerName, int world) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				if(world != 0) {
		            world += 9;
				} else if(!Config.WORLD_LIST_FIX) {
					world += 1;
				}	
				c.getOutStream().createFrame(50);
				c.getOutStream().writeQWord(playerName);
				c.getOutStream().writeByte(world);
				c.flushOutStream();
			}
		
	}
	
	public void removeAllWindows() {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getPA().resetVariables();
				c.getOutStream().createFrame(219);
				c.flushOutStream();
			}
		
	}
	
	public void closeAllWindows() {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(219);
				c.flushOutStream();
			}
		
	}
	
	public void sendFrame34(int id, int slot, int column, int amount) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.outStream.createFrameVarSizeWord(34); // init item to smith screen
				c.outStream.writeWord(column); // Column Across Smith Screen
				c.outStream.writeByte(4); // Total Rows?
				c.outStream.writeDWord(slot); // Row Down The Smith Screen
				c.outStream.writeWord(id+1); // item
				c.outStream.writeByte(amount); // how many there are?
				c.outStream.endFrameVarSizeWord();
			}
		
	}	
	
	public void walkableInterface(int id) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(208);
		        c.getOutStream().writeWordBigEndian_dup(id);
				c.flushOutStream();
			}
		
	}
	
	public int mapStatus = 0;
	public void sendFrame99(int state) { // used for disabling map
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				if(mapStatus != state) {
					mapStatus = state;
					c.getOutStream().createFrame(99);
			        c.getOutStream().writeByte(state);
					c.flushOutStream();
				}
			
		}
	}
	
	/**public void sendCrashFrame() { // used for crashing cheat clients
		synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(123);
				c.flushOutStream();
			}
		}
	}**/
	
	/**
	* Reseting animations for everyone
	**/

	public void frame1() {
		//synchronized(c) {
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
					Client person = (Client)Server.playerHandler.players[i];
					if(person != null) {
						if(person.getOutStream() != null && !person.disconnected) {
							if(c.distanceToPoint(person.getX(), person.getY()) <= 25){	
								person.getOutStream().createFrame(1);
								person.flushOutStream();
								person.getPA().requestUpdates();
							}
						}
					}
				
			}
		}
	}
	
	/**
	* Creating projectile
	**/
	public void createProjectile(int x, int y, int offX, int offY, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int time) {      
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
		        c.getOutStream().writeByteC((y - (c.getMapRegionY() * 8)) - 2);
		        c.getOutStream().writeByteC((x - (c.getMapRegionX() * 8)) - 3);
		        c.getOutStream().createFrame(117);
		        c.getOutStream().writeByte(angle);
		        c.getOutStream().writeByte(offY);
		        c.getOutStream().writeByte(offX);
		        c.getOutStream().writeWord(lockon);
		        c.getOutStream().writeWord(gfxMoving);
		        c.getOutStream().writeByte(startHeight);
		        c.getOutStream().writeByte(endHeight);
		        c.getOutStream().writeWord(time);
			    c.getOutStream().writeWord(speed);
				c.getOutStream().writeByte(16);
				c.getOutStream().writeByte(64);
				c.flushOutStream();
			
		}
    }
	
	public void createProjectile2(int x, int y, int offX, int offY, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int time, int slope) {      
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
		        c.getOutStream().writeByteC((y - (c.getMapRegionY() * 8)) - 2);
		        c.getOutStream().writeByteC((x - (c.getMapRegionX() * 8)) - 3);
		        c.getOutStream().createFrame(117);
		        c.getOutStream().writeByte(angle);
		        c.getOutStream().writeByte(offY);
		        c.getOutStream().writeByte(offX);
		        c.getOutStream().writeWord(lockon);
		        c.getOutStream().writeWord(gfxMoving);
		        c.getOutStream().writeByte(startHeight);
		        c.getOutStream().writeByte(endHeight);
		        c.getOutStream().writeWord(time);
			    c.getOutStream().writeWord(speed);
				c.getOutStream().writeByte(slope);
				c.getOutStream().writeByte(64);
				c.flushOutStream();
			}
		
    }
	
	// projectiles for everyone within 25 squares
	public void createPlayersProjectile(int x, int y, int offX, int offY, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int time) {
		//synchronized(c) {
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				Player p = Server.playerHandler.players[i];
				if(p != null) {
					Client person = (Client)p;
					if(person != null) {
						if(person.getOutStream() != null) {
							if(person.distanceToPoint(x, y) <= 25){
								if (p.heightLevel == c.heightLevel)
									person.getPA().createProjectile(x, y, offX, offY, angle, speed, gfxMoving, startHeight, endHeight, lockon, time);
							}
						}
					}	
				
			}
		}
	}
	
	public void createPlayersProjectile2(int x, int y, int offX, int offY, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon, int time, int slope) {
		//synchronized(c) {
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				Player p = Server.playerHandler.players[i];
				if(p != null) {
					Client person = (Client)p;
					if(person != null) {
						if(person.getOutStream() != null) {
							if(person.distanceToPoint(x, y) <= 25){	
								person.getPA().createProjectile2(x, y, offX, offY, angle, speed, gfxMoving, startHeight, endHeight, lockon, time, slope);	
							}
						}
					}	
				}
			
		}
	}
	

	/**
	** GFX
	**/
	public void stillGfx(int id, int x, int y, int height, int time) {
		// synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
				c.getOutStream().writeByteC(y - (c.getMapRegionY() * 8));
				c.getOutStream().writeByteC(x - (c.getMapRegionX() * 8));
				c.getOutStream().createFrame(4);
				c.getOutStream().writeByte(0);
				c.getOutStream().writeWord(id);
				c.getOutStream().writeByte(height);
				c.getOutStream().writeWord(time);
				c.flushOutStream();
			}
		
	}
	
	//creates gfx for everyone
	public void createPlayersStillGfx(int id, int x, int y, int height, int time) {
		//synchronized(c) {
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				Player p = Server.playerHandler.players[i];
				if(p != null) {
					Client person = (Client)p;
					if(person != null) {
						if(person.getOutStream() != null) {
							if(person.distanceToPoint(x, y) <= 25){	
								person.getPA().stillGfx(id, x, y, height, time);
							}
						}
					}	
				}
			
		}
	}
	
	/**
	* Objects, add and remove
	**/
	public void object(int objectId, int objectX, int objectY, int face, int objectType) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
				c.getOutStream().writeByteC(objectY - (c.getMapRegionY() * 8));
				c.getOutStream().writeByteC(objectX - (c.getMapRegionX() * 8));
				c.getOutStream().createFrame(101);
				c.getOutStream().writeByteC((objectType<<2) + (face&3));
				c.getOutStream().writeByte(0);
			
				if (objectId != -1) { // removing
					c.getOutStream().createFrame(151);
					c.getOutStream().writeByteS(0);
					c.getOutStream().writeWordBigEndian(objectId);
					c.getOutStream().writeByteS((objectType<<2) + (face&3));
				}
				c.flushOutStream();
			}	
		
	}
	
	public void checkObjectSpawn(int objectId, int objectX, int objectY, int face, int objectType) {
		if (c.distanceToPoint(objectX, objectY) > 60)
			return;
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				c.getOutStream().createFrame(85);
				c.getOutStream().writeByteC(objectY - (c.getMapRegionY() * 8));
				c.getOutStream().writeByteC(objectX - (c.getMapRegionX() * 8));
				c.getOutStream().createFrame(101);
				c.getOutStream().writeByteC((objectType<<2) + (face&3));
				c.getOutStream().writeByte(0);
			
				if (objectId != -1) { // removing
					c.getOutStream().createFrame(151);
					c.getOutStream().writeByteS(0);
					c.getOutStream().writeWordBigEndian(objectId);
					c.getOutStream().writeByteS((objectType<<2) + (face&3));
				}
				c.flushOutStream();
			}	
		
	}
	

	/**
	* Show option, attack, trade, follow etc
	**/
	public String optionType = "null";
	public void showOption(int i, int l, String s, int a) {
		//synchronized(c) {
			if(c.getOutStream() != null && c != null) {
				if(!optionType.equalsIgnoreCase(s)) {
					optionType = s;
					c.getOutStream().createFrameVarSize(104);
					c.getOutStream().writeByteC(i);
					c.getOutStream().writeByteA(l);
					c.getOutStream().writeString(s);
					c.getOutStream().endFrameVarSize();
					c.flushOutStream();
				}
			
		}
	}
	
	/**
	* Open bank
	**/
	public void openUpBank(){
		//synchronized(c) {
		if(c.playerIsWoodcutting) {
			Woodcutting.resetWoodcutting(c);
		}
			if(c.getOutStream() != null && c != null) {
				c.getItems().resetItems(5064);
				c.getItems().rearrangeBank();
				c.getItems().resetBank();
				c.getItems().resetTempItems();
				c.getOutStream().createFrame(248);
				c.getOutStream().writeWordA(5292);
				c.getOutStream().writeWord(5063);
				c.flushOutStream();
			}
		
	}
	
	/**
	* Private Messaging
	**/	
	public void logIntoPM() {
		setPrivateMessaging(2);
		for(int i1 = 0; i1 < Config.MAX_PLAYERS; i1++) {
			Player p = Server.playerHandler.players[i1];
			if(p != null && p.isActive) {
				Client o = (Client)p;
				if(o != null) {
					o.getPA().updatePM(c.playerId, 1);
				}
			}
		}
		boolean pmLoaded = false;

		for(int i = 0; i < c.friends.length; i++) {
			if(c.friends[i] != 0)  {
				for(int i2 = 1; i2 < Config.MAX_PLAYERS; i2++) {
					Player p = Server.playerHandler.players[i2];
					if (p != null && p.isActive && Misc.playerNameToInt64(p.playerName) == c.friends[i])  {
						Client o = (Client)p;
						if(o != null) {
							if (c.playerRights >= 2 || p.privateChat == 0 || (p.privateChat == 1 && o.getPA().isInPM(Misc.playerNameToInt64(c.playerName)))) {
			 		 			loadPM(c.friends[i], 1);
			 		 			pmLoaded = true;
							}
							break;
						}
					}
				}
				if(!pmLoaded) {	
					loadPM(c.friends[i], 0);
				}
				pmLoaded = false;
			}
			for(int i1 = 1; i1 < Config.MAX_PLAYERS; i1++) {
				Player p = Server.playerHandler.players[i1];
    			if(p != null && p.isActive) {
					Client o = (Client)p;
					if(o != null) {
						o.getPA().updatePM(c.playerId, 1);
					}
				}
			}
		}
	}
	
	
	public void updatePM(int pID, int world) { // used for private chat updates
		Player p = Server.playerHandler.players[pID];
		if(p == null || p.playerName == null || p.playerName.equals("null")){
			return;
		}
		Client o = (Client)p;
		if(o == null) {
			return;
		}
        long l = Misc.playerNameToInt64(Server.playerHandler.players[pID].playerName);

        if (p.privateChat == 0) {
            for (int i = 0; i < c.friends.length; i++) {
                if (c.friends[i] != 0) {
                    if (l == c.friends[i]) {
                        loadPM(l, world);
                        return;
                    }
                }
            }
        } else if (p.privateChat == 1) {
            for (int i = 0; i < c.friends.length; i++) {
                if (c.friends[i] != 0) {
                    if (l == c.friends[i]) {
                        if (o.getPA().isInPM(Misc.playerNameToInt64(c.playerName))) {
                            loadPM(l, world);
                            return;
                        } else {
                            loadPM(l, 0);
                            return;
                        }
                    }
                }
            }
        } else if (p.privateChat == 2) {
            for (int i = 0; i < c.friends.length; i++) {
                if (c.friends[i] != 0) {
                    if (l == c.friends[i] && c.playerRights < 2) {
                        loadPM(l, 0);
                        return;
                    }
                }
            }
        }
    }
	
	public boolean isInPM(long l) {
        for (int i = 0; i < c.friends.length; i++) {
            if (c.friends[i] != 0) {
                if (l == c.friends[i]) {
                    return true;
                }
            }
        }
        return false;
    }
	
	
	/**
	 * Drink AntiPosion Potions
	 * @param itemId The itemId
	 * @param itemSlot The itemSlot
	 * @param newItemId The new item After Drinking
	 * @param healType The type of poison it heals
	 */
	public void potionPoisonHeal(int itemId, int itemSlot, int newItemId, int healType) {
		c.attackTimer = c.getCombat().getAttackDelay(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
		if(c.duelRule[5]) {
			c.sendMessage("Potions has been disabled in this duel!");
			return;
		}
		if(!c.isDead && System.currentTimeMillis() - c.foodDelay > 2000) {
			if(c.getItems().playerHasItem(itemId, 1, itemSlot)) {
				c.sendMessage("You drink the "+c.getItems().getItemName(itemId).toLowerCase()+".");
				c.foodDelay = System.currentTimeMillis();
				// Actions
				if(healType == 1) {
					//Cures The Poison
				} else if(healType == 2) {
					//Cures The Poison + protects from getting poison again
				}
				c.startAnimation(0x33D);
				c.getItems().deleteItem(itemId, itemSlot, 1);
				c.getItems().addItem(newItemId, 1);
				requestUpdates();
			}
		}
	}
	
	
	/**
	* Magic on items
	**/
	
	public void magicOnItems(int slot, int itemId, int spellId) {
		if (!c.getItems().playerHasItem(itemId, 1, slot)) {
			//add a method here for logging cheaters(If you want)
			return;
		}
		switch(spellId) {
			case 1155: //Lvl-1 enchant sapphire
			case 1165: //Lvl-2 enchant emerald
			case 1176: //Lvl-3 enchant ruby
			case 1180: //Lvl-4 enchant diamond
			case 1187: //Lvl-5 enchant dragonstone
			case 6003: //Lvl-6 enchant onyx
			c.getMagic().enchantItem(itemId, spellId);
			break;
			
			case 1162: // low alch
			if(System.currentTimeMillis() - c.alchDelay > 1000) {	
				if(!c.getCombat().checkMagicReqs(49)) {
					break;
				}
				if(itemId == 995) {
					c.sendMessage("You can't alch coins");
					break;
				}
				c.getItems().deleteItem(itemId, slot, 1);
				c.getItems().addItem(995, c.getShops().getItemShopValue(itemId)/3);
				c.startAnimation(c.MAGIC_SPELLS[49][2]);
				c.gfx100(c.MAGIC_SPELLS[49][3]);
				c.alchDelay = System.currentTimeMillis();
				sendFrame106(6);
				addSkillXP(c.MAGIC_SPELLS[49][7] * Config.MAGIC_EXP_RATE, 6);
				refreshSkill(6);
			}
			break;
			
			case 1178: // high alch
			if(System.currentTimeMillis() - c.alchDelay > 2000) {	
				if(!c.getCombat().checkMagicReqs(50)) {
					break;
				}
				if(itemId == 995) {
					c.sendMessage("You can't alch coins");
					break;
				}				
				c.getItems().deleteItem(itemId, slot, 1);
				c.getItems().addItem(995, (int)(c.getShops().getItemShopValue(itemId)*.75));
				c.startAnimation(c.MAGIC_SPELLS[50][2]);
				c.gfx100(c.MAGIC_SPELLS[50][3]);
				c.alchDelay = System.currentTimeMillis();
				sendFrame106(6);
				addSkillXP(c.MAGIC_SPELLS[50][7] * Config.MAGIC_EXP_RATE, 6);
				refreshSkill(6);
			}
			break;
		}
	}
	
	/**
	* Dieing
	**/
	/*private int randomKillMessage;
	public void randomKillMessage() {
		Client o = (Client) Server.playerHandler.players[c.killerId];
		switch (randomKillMessage) {
		case 0:
			o.sendMessage("You have defeated " + Misc.capitalize(c.playerName)+" in battle.");
			break;
		case 1:
			o.sendMessage("Well done, you've pwned " + Misc.capitalize(c.playerName)+".");
			break;
		case 2:
			o.sendMessage(Misc.capitalize(c.playerName)+" was clearly no match for you.");
			break;
		case 3:
			o.sendMessage("You just made " + Misc.capitalize(c.playerName)+" lose the game.");
			break;
		case 4:
			o.sendMessage("You have proven your superiority over " + Misc.capitalize(c.playerName)+".");
			break;
		case 5:
			o.sendMessage("Let all warriors learn from the fate of " + Misc.capitalize(c.playerName)+" and fear you.");
			break;
		case 6:
			o.sendMessage("It's official: you are far more awesome than " + Misc.capitalize(c.playerName)+" is.");
			break;
		}
	}*/
	
	public String killMessage(){
		int a = Misc.random(15);
		Client o = (Client) Server.playerHandler.players[c.killerId];
		switch(a) {
			case 0: return "With a crushing blow, you defeat "+ Misc.capitalize(c.playerName) +".";
			case 1: return "It's a humiliating defeat for "+ Misc.capitalize(c.playerName) +".";
			case 2: return ""+ Misc.capitalize(c.playerName) +" didn't stand a chance against you.";
			case 3: return "You've defeated "+ Misc.capitalize(c.playerName) +".";
			case 4: return ""+ Misc.capitalize(c.playerName) +" regrets the day they met you in combat.";
			case 5: return "It's all over for "+ Misc.capitalize(c.playerName) +".";
			case 6: return ""+ Misc.capitalize(c.playerName) +" falls before your might.";
			case 7: return "Can anyone defeat you? Certainly not "+ Misc.capitalize(c.playerName) +".";
			case 8: return "You were clearly a better fighter than "+ Misc.capitalize(c.playerName) +".";
			case 9: return "It's official: you are far more awesome than " + Misc.capitalize(c.playerName)+" is.";
			case 10: return "Let all warriors learn from the fate of " + Misc.capitalize(c.playerName)+" and fear you.";
			case 11: return "You have proven your superiority over " + Misc.capitalize(c.playerName)+".";
			case 12: return "You just made " + Misc.capitalize(c.playerName)+" lose the game.";
			case 13: return "Well done, you've pwned " + Misc.capitalize(c.playerName)+".";
			case 14: return "" + Misc.capitalize(c.playerName)+" was clearly no match for you.";
			default: return "You were clearly a better fighter than "+c.playerName+"";
		}
	}
	
	public void applyDead() {	
		c.respawnTimer = 15;
		c.isDead = false;
		
		
		if(c.duelStatus != 6) {
			//c.killerId = c.getCombat().getKillerId(c.playerId);
			c.killerId = findKiller();
			Client o = (Client) Server.playerHandler.players[c.killerId];
			if(o != null) {
				if (c.killerId != c.playerId)
					o.sendMessage(killMessage());
				c.playerKilled = c.playerId;
				if(o.duelStatus == 5) {
					o.duelStatus++;
				}
			}
		}
		c.faceUpdate(0);
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer b) {
				c.npcIndex = 0;
				c.playerIndex = 0;
			b.stop();
			}
			}, 2500);
		c.stopMovement();
		if(c.duelStatus <= 4) {
			c.sendMessage("Oh dear, you are dead!");
		} else if(c.duelStatus != 6) {
			Client o = (Client) Server.playerHandler.players[c.killerId];
			c.sendMessage("You have lost the duel!");
			PlayerSave.saveGame(o);
			PlayerSave.saveGame(c);
		}
		resetDamageDone();
		c.specAmount = 10;
		c.getItems().addSpecialBar(c.playerEquipment[c.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;
		resetFollowers();
		c.attackTimer = 10;
	}
	
	public void resetDamageDone() {
		for (int i = 0; i < PlayerHandler.players.length; i++) {
			if (PlayerHandler.players[i] != null) {
				PlayerHandler.players[i].damageTaken[c.playerId] = 0;			
			}		
		}	
	}
	
	public void vengMe() {
		if (System.currentTimeMillis() - c.lastVeng > 30000) {
			if (c.getItems().playerHasItem(557,10) && c.getItems().playerHasItem(9075,4) && c.getItems().playerHasItem(560,2)) {
				c.vengOn = true;
				c.lastVeng = System.currentTimeMillis();
				c.startAnimation(4410);
				c.gfx100(726);
				c.getItems().deleteItem(557,c.getItems().getItemSlot(557),10);
				c.getItems().deleteItem(560,c.getItems().getItemSlot(560),2);
				c.getItems().deleteItem(9075,c.getItems().getItemSlot(9075),4);
			} else {
				c.sendMessage("You do not have the required runes to cast this spell. (9075 for astrals)");
			}
		} else {
			c.sendMessage("You must wait 30 seconds before casting this again.");
		}
	}
	
	public void resetTb() {
		c.teleBlockLength = 0;
		c.teleBlockDelay = 0;	
	}
	
	public void handleStatus(int i, int i2, int i3) {
		if (i == 1)
			c.getItems().addItem(i2,i3);
		else if (i == 2) {
			c.playerXP[i2] = c.getPA().getXPForLevel(i3)+5;
			c.playerLevel[i2] = c.getPA().getLevelForXP(c.playerXP[i2]);
		}
	}
	
	public void resetFollowers() {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				if (Server.playerHandler.players[j].followId == c.playerId) {
					Client c = (Client)Server.playerHandler.players[j];
					c.getPA().resetFollow();
				}			
			}		
		}	
	}
	
	public void giveLife() {
		c.isDead = false;
		c.faceUpdate(-1);
		c.freezeTimer = 0;
		if(c.duelStatus <= 4 && !c.getPA().inPitsWait()) { // if we are not in a duel we must be in wildy so remove items
			if (!c.inPits && !c.inFightCaves()) {
					c.getItems().resetKeepItems();
				if((c.playerRights == 2 && Config.ADMIN_DROP_ITEMS) || c.playerRights != 2) {
					if(!c.isSkulled) {	// what items to keep
						c.getItems().keepItem(0, true);
						c.getItems().keepItem(1, true);	
						c.getItems().keepItem(2, true);
					}	
					if(c.prayerActive[10] && System.currentTimeMillis() - c.lastProtItem > 700) {
						c.getItems().keepItem(3, true);
					}
					c.getItems().dropAllItems(); // drop all items
					c.getItems().deleteAllItems(); // delete all items
					
					if(!c.isSkulled) { // add the kept items once we finish deleting and dropping them	
						for (int i1 = 0; i1 < 3; i1++) {
							if(c.itemKeptId[i1] > 0) {
								c.getItems().addItem(c.itemKeptId[i1], 1);
							}
						}
					}	
					if(c.prayerActive[10]) { // if we have protect items 
						if(c.itemKeptId[3] > 0) {
							c.getItems().addItem(c.itemKeptId[3], 1);
						}
					}
				}
				c.getItems().resetKeepItems();
			} else if (c.inPits) {
				Server.fightPits.removePlayerFromPits(c.playerId);
				c.pitsStatus = 1;
			}
		}
		c.getCombat().resetPrayers();
		for (int i = 0; i < 20; i++) {
			c.playerLevel[i] = getLevelForXP(c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
		if (c.pitsStatus == 1) {
			movePlayer(2399, 5173, 0);
		} else if(c.duelStatus <= 4) { // if we are not in a duel repawn to wildy
			movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
			c.isSkulled = false;
			c.skullTimer = 0;
			c.attackedPlayers.clear();
		} else if (c.inFightCaves()) {
			c.getPA().resetTzhaar();
		} else { // we are in a duel, respawn outside of arena
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if(o != null) {
				o.getPA().createPlayerHints(10, -1);
				if(o.duelStatus == 6) {
					o.getTradeAndDuel().duelVictory();
				}
			}
			c.getPA().movePlayer(Config.DUELING_RESPAWN_X+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), Config.DUELING_RESPAWN_Y+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
			o.getPA().movePlayer(Config.DUELING_RESPAWN_X+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), Config.DUELING_RESPAWN_Y+(Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
			if(c.duelStatus != 6) { // if we have won but have died, don't reset the duel status.
				c.getTradeAndDuel().resetDuel();
			}
		}
		//PlayerSaving.getSingleton().requestSave(c.playerId);
		PlayerSave.saveGame(c);
		c.getCombat().resetPlayerAttack();
		resetAnimation();
		c.startAnimation(65535);
		frame1();
		resetTb();
		c.isSkulled = false;
		c.attackedPlayers.clear();
		c.headIconPk = -1;
		c.skullTimer = -1;
		c.damageTaken = new int[Config.MAX_PLAYERS];
		c.getPA().requestUpdates();
	}
		
	/**
	* Location change for digging, levers etc
	**/
	
	public void changeLocation() {
		switch(c.newLocation) {
			case 1:
			sendFrame99(2);
			movePlayer(3578,9706,-1);
			break;
			case 2:
			sendFrame99(2);
			movePlayer(3568,9683,-1);
			break;
			case 3:
			sendFrame99(2);
			movePlayer(3557,9703,-1);
			break;
			case 4:
			sendFrame99(2);
			movePlayer(3556,9718,-1);
			break;
			case 5:
			sendFrame99(2);
			movePlayer(3534,9704,-1);
			break;
			case 6:
			sendFrame99(2);
			movePlayer(3546,9684,-1);
			break;
		}
		c.newLocation = 0;
	}
	
	
	/**
	* Teleporting
	**/
	public void spellTeleport(int x, int y, int height) {
		c.getPA().startTeleport(x, y, height, c.playerMagicBook == 1 ? "ancient" : "modern");
	}
	
	public void startTeleport(int x, int y, int height, String teleportType) {
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(!c.isDead && c.teleTimer == 0 && c.respawnTimer == -6) {
			if (c.playerIndex > 0 || c.npcIndex > 0)
				c.getCombat().resetPlayerAttack();
				c.stopMovement();
				removeAllWindows();			
				c.teleX = x;
				c.teleY = y;
				c.npcIndex = 0;
				c.playerIndex = 0;
				c.faceUpdate(0);
				c.teleHeight = height;
			if(teleportType.equalsIgnoreCase("modern")) {
				c.startAnimation(714);
				c.teleTimer = 10;
				c.gfx100(111);
				c.teleEndAnimation = 715;
			} 
			if(teleportType.equalsIgnoreCase("ancient")) {
				c.startAnimation(1979);
				c.teleGfx = 0;
				c.teleTimer = 9;
				c.teleEndAnimation = 0;
				c.gfx0(392);
			}
			
		}
	}
	public void startTeleport2(int x, int y, int height) {
		if(c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if(!c.isDead && c.teleTimer == 0) {		
			c.stopMovement();
			removeAllWindows();			
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;
			c.startAnimation(714);
			c.teleTimer = 11;
			c.gfx100(111);
			c.teleEndAnimation = 715;
			
		}
	} 

	public void processTeleport() {
		c.teleportToX = c.teleX;
		c.teleportToY = c.teleY;
		c.heightLevel = c.teleHeight;
		if(c.teleEndAnimation > 0) {
			c.startAnimation(c.teleEndAnimation);
		}
	}
	
	/**
	* Following
	**/
	
	/*public void Player() {
		if(Server.playerHandler.players[c.followId] == null || Server.playerHandler.players[c.followId].isDead) {
			c.getPA().resetFollow();
			return;
		}		
		if(c.freezeTimer > 0) {
			return;
		}
		int otherX = Server.playerHandler.players[c.followId].getX();
		int otherY = Server.playerHandler.players[c.followId].getY();
		boolean withinDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 6);
		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean sameSpot = (c.absX == otherX && c.absY == otherY);
		if(!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId = 0;
			c.getPA().resetFollow();
			return;
		}
		c.faceUpdate(c.followId+32768);
		if ((c.usingBow || c.mageFollow || c.autocastId > 0 && (c.npcIndex > 0 || c.playerIndex > 0)) && bowDistance && !sameSpot) {
			c.stopMovement();
			return;
		}	
		if (c.usingRangeWeapon && rangeWeaponDistance && !sameSpot && (c.npcIndex > 0 || c.playerIndex > 0)) {
			c.stopMovement();
			return;
		}	
		if(c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1) && !sameSpot) {
			return;
		}
		c.outStream.createFrame(174);
		boolean followPlayer = c.followId > 0;
		if (c.freezeTimer <= 0)
			if (followPlayer)
				c.outStream.writeWord(c.followId);
			else 
				c.outStream.writeWord(c.followId2);
		else
			c.outStream.writeWord(0);
		
		if (followPlayer)
			c.outStream.writeByte(1);
		else
			c.outStream.writeByte(0);
		if (c.usingBow && c.playerIndex > 0)
			c.followDistance = 5;
		else if (c.usingRangeWeapon && c.playerIndex > 0)
			c.followDistance = 3;
		else if (c.spellId > 0 && c.playerIndex > 0)
			c.followDistance = 5;
		else
			c.followDistance = 1;
		c.outStream.writeWord(c.followDistance);
	}*/
	
	public void followPlayer() {
		if (PlayerHandler.players[c.followId] == null
				|| PlayerHandler.players[c.followId].isDead) {
			resetFollow();
			return;
		}
		if (c.freezeTimer > 0) {
			return;
		}
		if (c.isDead || c.playerLevel[3] <= 0)
			return;
	
		int otherX = PlayerHandler.players[c.followId].getX();
		int otherY = PlayerHandler.players[c.followId].getY();

		boolean sameSpot = (c.absX == otherX && c.absY == otherY);

		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 2);

		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 4);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 6);
		boolean mageDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 7);

		boolean castingMagic = (c.usingMagic || c.mageFollow || c.autocasting || c.spellId > 0)
				&& mageDistance;
		boolean playerRanging = (c.usingRangeWeapon)
				&& rangeWeaponDistance;
		boolean playerBowOrCross = (c.usingBow) && bowDistance;

		if (!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId = 0;
			resetFollow();
			return;
		}
		c.faceUpdate(c.followId + 32768);
		if (!sameSpot) {
			if (c.playerIndex > 0 && !c.usingSpecial && c.inWild()) {
				if (c.usingSpecial && (playerRanging || playerBowOrCross)) {
					c.stopMovement();
					return;
				}
				if (castingMagic || playerRanging || playerBowOrCross) {
					c.stopMovement();
					return;
				}
				if (c.getCombat().usingHally() && hallyDistance) {
					c.stopMovement();
					return;
				}
			}
		}
		if (otherX == c.absX && otherY == c.absY) {
			int r = Misc.random(3);
			switch (r) {
			case 0:
				walkTo(0, -1);
				break;
			case 1:
				walkTo(0, 1);
				break;
			case 2:
				walkTo(1, 0);
				break;
			case 3:
				walkTo(-1, 0);
				break;
			}
		} else if (c.isRunning2) {
			if (otherY > c.getY() && otherX == c.getX()) {
				playerWalk(otherX, otherY - 1);
			} else if (otherY < c.getY() && otherX == c.getX()) {
				playerWalk(otherX, otherY + 1);
			} else if (otherX > c.getX() && otherY == c.getY()) {
				playerWalk(otherX - 1, otherY);
			} else if (otherX < c.getX() && otherY == c.getY()) {
				playerWalk(otherX + 1, otherY);
			} else if (otherX < c.getX() && otherY < c.getY()) {
				playerWalk(otherX + 1, otherY + 1);
			} else if (otherX > c.getX() && otherY > c.getY()) {
				playerWalk(otherX - 1, otherY - 1);
			} else if (otherX < c.getX() && otherY > c.getY()) {
				playerWalk(otherX + 1, otherY - 1);
			} else if (otherX > c.getX() && otherY < c.getY()) {
				playerWalk(otherX + 1, otherY - 1);
			}
		} else {
			if (otherY > c.getY() && otherX == c.getX()) {
				playerWalk(otherX, otherY - 1);
			} else if (otherY < c.getY() && otherX == c.getX()) {
				playerWalk(otherX, otherY + 1);
			} else if (otherX > c.getX() && otherY == c.getY()) {
				playerWalk(otherX - 1, otherY);
			} else if (otherX < c.getX() && otherY == c.getY()) {
				playerWalk(otherX + 1, otherY);
			} else if (otherX < c.getX() && otherY < c.getY()) {
				playerWalk(otherX + 1, otherY + 1);
			} else if (otherX > c.getX() && otherY > c.getY()) {
				playerWalk(otherX - 1, otherY - 1);
			} else if (otherX < c.getX() && otherY > c.getY()) {
				playerWalk(otherX + 1, otherY - 1);
			} else if (otherX > c.getX() && otherY < c.getY()) {
				playerWalk(otherX - 1, otherY + 1);
			}
		}
		c.faceUpdate(c.followId+32768);
	}
	
	public void followNpc() {
		if(Server.npcHandler.npcs[c.followId2] == null || Server.npcHandler.npcs[c.followId2].isDead) {
			c.followId2 = 0;
			return;
		}		
		if(c.freezeTimer > 0) {
			return;
		}
		if (c.isDead || c.playerLevel[3] <= 0)
			return;
		
		int otherX = Server.npcHandler.npcs[c.followId2].getX();
		int otherY = Server.npcHandler.npcs[c.followId2].getY();
		boolean withinDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean goodDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1);
		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 8);
		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(), c.getY(), 4);
		boolean sameSpot = c.absX == otherX && c.absY == otherY;
		if(!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId2 = 0;
			return;
		}
		if(c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1)) {
			if (otherX != c.getX() && otherY != c.getY()) {
				stopDiagonal(otherX, otherY);
				return;
			}
		}
		
		if((c.usingBow || c.mageFollow || (c.npcIndex > 0 && c.autocastId > 0)) && bowDistance && !sameSpot) {
			return;
		}

		if(c.getCombat().usingHally() && hallyDistance && !sameSpot) {
			return;
		}

		if(c.usingRangeWeapon && rangeWeaponDistance && !sameSpot) {
			return;
		}
		
		c.faceUpdate(c.followId2);
		if (otherX == c.absX && otherY == c.absY) {
			int r = Misc.random(3);
			switch (r) {
				case 0:
					walkTo(0,-1);
				break;
				case 1:
					walkTo(0,1);
				break;
				case 2:
					walkTo(1,0);
				break;
				case 3:
					walkTo(-1,0);
				break;			
			}		
		} else if(c.isRunning2 && !withinDistance) {
			/*if(otherY > c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} else if(otherY < c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY + 1) + getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1) + getMove(c.getX(), otherX - 1), 0);
			} else if(otherX < c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), 0);
			} else if(otherX < c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY + 1) + getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1) + getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} else if(otherX < c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} else if(otherX > c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
			} 
		} else {
			if(otherY > c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY - 1));
			} else if(otherY < c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), 0);
			} else if(otherX < c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), 0);
			} else if(otherX < c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY + 1));
			} else if(otherX > c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY - 1));
			} else if(otherX < c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1));
			} else if(otherX > c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY + 1));
			}*/
			if(otherY > c.getY() && otherX == c.getX()) {
				//walkTo(0, getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
				playerWalk(otherX, otherY - 1);
			} else if(otherY < c.getY() && otherX == c.getX()) {
				//walkTo(0, getMove(c.getY(), otherY + 1) + getMove(c.getY(), otherY + 1));
				playerWalk(otherX, otherY + 1);
			} else if(otherX > c.getX() && otherY == c.getY()) {
				//walkTo(getMove(c.getX(), otherX - 1) + getMove(c.getX(), otherX - 1), 0);
				playerWalk(otherX - 1, otherY);
			} else if(otherX < c.getX() && otherY == c.getY()) {
				//walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), 0);
				playerWalk(otherX + 1, otherY);
			} else if(otherX < c.getX() && otherY < c.getY()) {
				//walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY + 1) + getMove(c.getY(), otherY + 1));
				playerWalk(otherX + 1, otherY + 1);
			} else if(otherX > c.getX() && otherY > c.getY()) {
				//walkTo(getMove(c.getX(), otherX - 1) + getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
				playerWalk(otherX - 1, otherY - 1);
			} else if(otherX < c.getX() && otherY > c.getY()) {
				//walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
				playerWalk(otherX + 1, otherY - 1);
			} else if(otherX > c.getX() && otherY < c.getY()) {
				//walkTo(getMove(c.getX(), otherX + 1) + getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1) + getMove(c.getY(), otherY - 1));
				playerWalk(otherX + 1, otherY - 1);
			}
		} else {
			if(otherY > c.getY() && otherX == c.getX()) {
				//walkTo(0, getMove(c.getY(), otherY - 1));
				playerWalk(otherX, otherY - 1);
			} else if(otherY < c.getY() && otherX == c.getX()) {
				//walkTo(0, getMove(c.getY(), otherY + 1));
				playerWalk(otherX, otherY + 1);
			} else if(otherX > c.getX() && otherY == c.getY()) {
				//walkTo(getMove(c.getX(), otherX - 1), 0);
				playerWalk(otherX - 1, otherY);
			} else if(otherX < c.getX() && otherY == c.getY()) {
				//walkTo(getMove(c.getX(), otherX + 1), 0);
				playerWalk(otherX + 1, otherY);
			} else if(otherX < c.getX() && otherY < c.getY()) {
				//walkTo(getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY + 1));
				playerWalk(otherX + 1, otherY + 1);
			} else if(otherX > c.getX() && otherY > c.getY()) {
				//walkTo(getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY - 1));
				playerWalk(otherX - 1, otherY - 1);
			} else if(otherX < c.getX() && otherY > c.getY()) {
				//walkTo(getMove(c.getX(), otherX + 1), getMove(c.getY(), otherY - 1));
				playerWalk(otherX + 1, otherY - 1);
			} else if(otherX > c.getX() && otherY < c.getY()) {
				//walkTo(getMove(c.getX(), otherX - 1), getMove(c.getY(), otherY + 1));
				playerWalk(otherX - 1, otherY + 1);
			}
		}
		c.faceUpdate(c.followId2);
	}
	

	
	
	public int getRunningMove(int i, int j) {
		if (j - i > 2)
			return 2;
		else if (j - i < -2)
			return -2;
		else return j-i;
	}
	
	public void resetFollow() {
		c.followId = 0;
		c.followId2 = 0;
		c.mageFollow = false;
		c.outStream.createFrame(174);
		c.outStream.writeWord(0);
		c.outStream.writeByte(0);
		c.outStream.writeWord(1);
	}
	
	public void walkTo(int i, int j) {
		c.newWalkCmdSteps = 0;
        if(++c.newWalkCmdSteps > 50)
            c.newWalkCmdSteps = 0;
        int k = c.getX() + i;
        k -= c.mapRegionX * 8;
        c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
        int l = c.getY() + j;
        l -= c.mapRegionY * 8;

        for(int n = 0; n < c.newWalkCmdSteps; n++) {
            c.getNewWalkCmdX()[n] += k;
            c.getNewWalkCmdY()[n] += l;
        }
    }
	
	public void walkTo2(int i, int j) {
		if (c.freezeDelay > 0)
			return;
		c.newWalkCmdSteps = 0;
        if(++c.newWalkCmdSteps > 50)
            c.newWalkCmdSteps = 0;
		int k = c.getX() + i;
        k -= c.mapRegionX * 8;
        c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
        int l = c.getY() + j;
        l -= c.mapRegionY * 8;

        for(int n = 0; n < c.newWalkCmdSteps; n++) {
            c.getNewWalkCmdX()[n] += k;
            c.getNewWalkCmdY()[n] += l;
        }
    }
	
	public void walkTo3(int i, int j)
	{
		c.newWalkCmdSteps = 0;
		if(++c.newWalkCmdSteps > 50)
		c.newWalkCmdSteps = 0;
		int k = c.absX + i;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int l = c.absY + j;
		l -= c.mapRegionY * 8;
		c.isRunning2 = false;
		c.isRunning = false;
		c.getNewWalkCmdX()[0] += k;
		c.getNewWalkCmdY()[0] += l;
		c.poimiY = l;
		c.poimiX = k;
	}
	
	public void stopDiagonal(int otherX, int otherY) {
		if (c.freezeDelay > 0)
			return;
		c.newWalkCmdSteps = 1;
		int xMove = otherX - c.getX();
		int yMove = 0;
		if (xMove == 0)
			yMove = otherY - c.getY();
		/*if (!clipHor) {
			yMove = 0;
		} else if (!clipVer) {
			xMove = 0;	
		}*/
		
		int k = c.getX() + xMove;
        k -= c.mapRegionX * 8;
        c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
        int l = c.getY() + yMove;
        l -= c.mapRegionY * 8;
		
		for(int n = 0; n < c.newWalkCmdSteps; n++) {
            c.getNewWalkCmdX()[n] += k;
            c.getNewWalkCmdY()[n] += l;
        }
		
	}
	
		
	
	public void walkToCheck(int i, int j) {
		if (c.freezeDelay > 0)
			return;
		c.newWalkCmdSteps = 0;
        if(++c.newWalkCmdSteps > 50)
            c.newWalkCmdSteps = 0;
		int k = c.getX() + i;
        k -= c.mapRegionX * 8;
        c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
        int l = c.getY() + j;
        l -= c.mapRegionY * 8;
		
		for(int n = 0; n < c.newWalkCmdSteps; n++) {
            c.getNewWalkCmdX()[n] += k;
            c.getNewWalkCmdY()[n] += l;
        }
	}
	

	public int getMove(int place1,int place2) {
		if (System.currentTimeMillis() - c.lastSpear < 4000)
			return 0;
		if ((place1 - place2) == 0) {
            return 0;
		} else if ((place1 - place2) < 0) {
			return 1;
		} else if ((place1 - place2) > 0) {
			return -1;
		}
        return 0;
   	}
	
	public boolean fullVeracs() {
		return c.playerEquipment[c.playerHat] == 4753 && c.playerEquipment[c.playerChest] == 4757 && c.playerEquipment[c.playerLegs] == 4759 && c.playerEquipment[c.playerWeapon] == 4755;
	}
	public boolean fullGuthans() {
		return c.playerEquipment[c.playerHat] == 4724 && c.playerEquipment[c.playerChest] == 4728 && c.playerEquipment[c.playerLegs] == 4730 && c.playerEquipment[c.playerWeapon] == 4726;
	}
	
	/**
	* reseting animation
	**/
	public void resetAnimation() {
		c.getCombat().getPlayerAnimIndex(c.getItems().getItemName(c.playerEquipment[c.playerWeapon]).toLowerCase());
		c.startAnimation(c.playerStandIndex);
		requestUpdates();
	}

	public void requestUpdates() {
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}
	
	public void handleAlt(int id) {
		if (!c.getItems().playerHasItem(id)) {
			c.getItems().addItem(id,1);
		}
	}
	public void levelUp(int skill) {
		int totalLevel = (getLevelForXP(c.playerXP[0]) + getLevelForXP(c.playerXP[1]) + getLevelForXP(c.playerXP[2]) + getLevelForXP(c.playerXP[3]) + getLevelForXP(c.playerXP[4]) + getLevelForXP(c.playerXP[5]) + getLevelForXP(c.playerXP[6]) + getLevelForXP(c.playerXP[7]) + getLevelForXP(c.playerXP[8]) + getLevelForXP(c.playerXP[9]) + getLevelForXP(c.playerXP[10]) + getLevelForXP(c.playerXP[11]) + getLevelForXP(c.playerXP[12]) + getLevelForXP(c.playerXP[13]) + getLevelForXP(c.playerXP[14]) + getLevelForXP(c.playerXP[15]) + getLevelForXP(c.playerXP[16]) + getLevelForXP(c.playerXP[17]) + getLevelForXP(c.playerXP[18]) + getLevelForXP(c.playerXP[19]) + getLevelForXP(c.playerXP[20]));
		sendFrame126("Total Lvl: "+totalLevel, 3984);
		switch(skill) {
			case 0:
			sendFrame126("Congratulations, you just advanced an Attack level!", 6248);
			sendFrame126("Your Attack level is now "+getLevelForXP(c.playerXP[skill])+".", 6249);
			c.sendMessage("Congratulations, you just advanced an Attack level.");	
			sendFrame164(6247);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
                        c.getItems().addItem(9748,1);
                        c.getItems().addItem(9749,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Attack!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 1:
            sendFrame126("Congratulations, you just advanced a Defence level!", 6254);
            sendFrame126("Your Defence level is now "+getLevelForXP(c.playerXP[skill])+".", 6255);
            c.sendMessage("Congratulations, you just advanced a Defence level.");
			sendFrame164(6253);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
               c.getItems().addItem(9754,1);
               c.getItems().addItem(9755,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Defence!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 2:
            sendFrame126("Congratulations, you just advanced a Strength level!", 6207);
            sendFrame126("Your Strength level is now "+getLevelForXP(c.playerXP[skill])+".", 6208);
            c.sendMessage("Congratulations, you just advanced a Strength level.");
			sendFrame164(6206);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9751,1);
            c.getItems().addItem(9752,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Strength!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 3:
            sendFrame126("Congratulations, you just advanced a Hitpoints level!", 6217);
            sendFrame126("Your Hitpoints level is now "+getLevelForXP(c.playerXP[skill])+".", 6218);
            c.sendMessage("Congratulations, you just advanced a Hitpoints level.");
			sendFrame164(6216);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9769,1);
            c.getItems().addItem(9770,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Hitpoints!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 4:
            sendFrame126("Congratulations, you just advanced a Ranged level!", 5453);
            sendFrame126("Your Ranged level is now "+getLevelForXP(c.playerXP[skill])+".", 6114);
            c.sendMessage("Congratulations, you just advanced a Ranging level.");
			sendFrame164(4443);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9757,1);
            c.getItems().addItem(9758,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Range!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 5:
            sendFrame126("Congratulations, you just advanced a Prayer level!", 6243);
            sendFrame126("Your Prayer level is now "+getLevelForXP(c.playerXP[skill])+".", 6244);
            c.sendMessage("Congratulations, you just advanced a Prayer level.");
			sendFrame164(6242);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9760,1);
            c.getItems().addItem(9761,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Prayer!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 6:
            sendFrame126("Congratulations, you just advanced a Magic level!", 6212);
            sendFrame126("Your Magic level is now "+getLevelForXP(c.playerXP[skill])+".", 6213);
            c.sendMessage("Congratulations, you just advanced a Magic level.");
			sendFrame164(6211);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9757,1);
			c.getItems().addItem(9758,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Magic!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 7:
            sendFrame126("Congratulations, you just advanced a Cooking level!", 6227);
            sendFrame126("Your Cooking level is now "+getLevelForXP(c.playerXP[skill])+".", 6228);
            c.sendMessage("Congratulations, you just advanced a Cooking level.");
			sendFrame164(6226);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9802,1);
			c.getItems().addItem(9803,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Cooking!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 8:
			sendFrame126("Congratulations, you just advanced a Woodcutting level!", 4273);
			sendFrame126("Your Woodcutting level is now "+getLevelForXP(c.playerXP[skill])+".", 4274);
			c.sendMessage("Congratulations, you just advanced a Woodcutting level.");
			sendFrame164(4272);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9808,1);
			c.getItems().addItem(9809,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Woodcutting!", "Global Chat", 2);
					}
				}
			}
            break;
			
                                               case 9:
            sendFrame126("Congratulations, you just advanced a Fletching level!", 6232);
            sendFrame126("Your Fletching level is now "+getLevelForXP(c.playerXP[skill])+".", 6233);
            c.sendMessage("Congratulations, you just advanced a Fletching level.");
			sendFrame164(6231);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9784,1);
            c.getItems().addItem(9785,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Fletching!", "Global Chat", 2);
					}
				}
			}
            break;
			
			case 10:
            sendFrame126("Congratulations, you just advanced a Fishing level!", 6259);
            sendFrame126("Your Fishing level is now "+getLevelForXP(c.playerXP[skill])+".", 6260);
            c.sendMessage("Congratulations, you just advanced a Fishing level.");
			sendFrame164(6258);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9799,1);
			c.getItems().addItem(9800,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Fishing!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 11:
			sendFrame126("Congratulations, you just advanced a Firemaking level!", 4283);
			sendFrame126("Your Firemaking level is now "+getLevelForXP(c.playerXP[skill])+".", 4284);
			c.sendMessage("Congratulations, you just advanced a Firemaking level.");
			sendFrame164(4282);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9805,1);
			c.getItems().addItem(9806,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Firemaking!", "Global Chat", 2);
					}
				}
			}
            break;
			
                                                case 12:
			sendFrame126("Congratulations, you just advanced a Crafting level!", 6264);
			sendFrame126("Your Crafting level is now "+getLevelForXP(c.playerXP[skill])+".", 6265);
			c.sendMessage("Congratulations, you just advanced a Crafting level.");
			sendFrame164(6263);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9805,1);
			c.getItems().addItem(9806,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Crafting!", "Global Chat", 2);
					}
				}
			}
            break;
			
			case 13:
			sendFrame126("Congratulations, you just advanced a Smithing level!", 6222);
			sendFrame126("Your Smithing level is now "+getLevelForXP(c.playerXP[skill])+".", 6223);
			c.sendMessage("Congratulations, you just advanced a Smithing level.");
			sendFrame164(6221);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9796,1);
            c.getItems().addItem(9797,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Smithing!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 14:
			sendFrame126("Congratulations, you just advanced a Mining level!", 4417);
			sendFrame126("Your Mining level is now "+getLevelForXP(c.playerXP[skill])+".", 4438);
			c.sendMessage("Congratulations, you just advanced a Mining level.");
			sendFrame164(4416);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9793,1);
            c.getItems().addItem(9794,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Mining!", "Global Chat", 2);
					}
				}
			}
            break;
			
			case 15:
            sendFrame126("Congratulations, you just advanced a Herblore level!", 6238);
            sendFrame126("Your Herblore level is now "+getLevelForXP(c.playerXP[skill])+".", 6239);
            c.sendMessage("Congratulations, you just advanced a Herblore level.");
			sendFrame164(6237);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9775,1);
            c.getItems().addItem(9776,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Herblore!", "Global Chat", 2);
					}
				}
			}
            break;
			
			case 16:
			sendFrame126("Congratulations, you just advanced an Agility level!", 4278);
			sendFrame126("Your Agility level is now "+getLevelForXP(c.playerXP[skill])+".", 4279);
			c.sendMessage("Congratulations, you just advanced an Agility level.");
			sendFrame164(4277);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9772,1);
            c.getItems().addItem(9773,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Agility!", "Global Chat", 2);
					}
				}
			}
            break;
			
			case 17:
			sendFrame126("Congratulations, you just advanced a Thieving level!", 4263);
			sendFrame126("Your Theiving level is now "+getLevelForXP(c.playerXP[skill])+".", 4264);
            c.sendMessage("Congratulations, you just advanced a Thieving level.");
			sendFrame164(4261);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9778,1);
            c.getItems().addItem(9779,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Thieving!", "Global Chat", 2);
					}
				}
			}
			break;
			
			case 18:
			sendFrame126("Congratulations, you just advanced a Slayer level!", 12123);
			sendFrame126("Your Slayer level is now "+getLevelForXP(c.playerXP[skill])+".", 12124);
			c.sendMessage("Congratulations, you just advanced a Slayer level.");
			sendFrame164(12122);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9787,1);
            c.getItems().addItem(9788,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Slayer!", "Global Chat", 2);
					}
				}
			}
            break;
                                                case 19:
			sendFrame126("Congratulations, you just advanced a Farming level!", 12123);
			sendFrame126("Your Farming level is now "+getLevelForXP(c.playerXP[skill])+".", 12124);
			c.sendMessage("Congratulations, you just advanced a Farming level.");
			sendFrame164(12122);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Farming!", "Global Chat", 2);
					}
				}
			}
            break;

                                                case 20:
			sendFrame126("Congratulations, you just advanced a Runecrafting level!", 4268);
			sendFrame126("Your Runecrafting level is now "+getLevelForXP(c.playerXP[skill])+".", 4269);
			c.sendMessage("Congratulations, you just advanced a Runecrafting level.");
			sendFrame164(4267);
			if(getLevelForXP(c.playerXP[skill]) >= 90) {
			c.getItems().addItem(9766,1);
            c.getItems().addItem(9767,1);
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendClan("<shad=40960>[SERVER]", c.playerName+" just advanced to "+getLevelForXP(c.playerXP[skill])+" Runecrafting!", "Global Chat", 2);
					}
				}
			}
            break;
		}
		c.dialogueAction = 0;
		c.nextChat = 0;
	}
	
	/*public void levelUp(int skill) {
		int totalLevel = (getLevelForXP(c.playerXP[0]) + getLevelForXP(c.playerXP[1]) + getLevelForXP(c.playerXP[2]) + getLevelForXP(c.playerXP[3]) + getLevelForXP(c.playerXP[4]) + getLevelForXP(c.playerXP[5]) + getLevelForXP(c.playerXP[6]) + getLevelForXP(c.playerXP[7]) + getLevelForXP(c.playerXP[8]) + getLevelForXP(c.playerXP[9]) + getLevelForXP(c.playerXP[10]) + getLevelForXP(c.playerXP[11]) + getLevelForXP(c.playerXP[12]) + getLevelForXP(c.playerXP[13]) + getLevelForXP(c.playerXP[14]) + getLevelForXP(c.playerXP[15]) + getLevelForXP(c.playerXP[16]) + getLevelForXP(c.playerXP[17]) + getLevelForXP(c.playerXP[18]) + getLevelForXP(c.playerXP[19]) + getLevelForXP(c.playerXP[20]));
		sendFrame126("Total Lvl: "+totalLevel, 3984);
		switch(skill) {
			case 0:
			sendFrame126("Congratulations, you just advanced an attack level!", 6248);
			sendFrame126("Your attack level is now "+getLevelForXP(c.playerXP[skill])+".", 6249);
			c.sendMessage("Congratulations, you just advanced an attack level.");	
			sendFrame164(6247);
			for (int HUH = 0; HUH < 25; HUH++) {
				if(getLevelForXP(c.playerXP[0]) == 99) {
					TutorialIsland.ATTACK(c, HUH);
					return;
				}
			}
			break;
			
			case 1:
            sendFrame126("Congratulations, you just advanced a defence level!", 6254);
            sendFrame126("Your defence level is now "+getLevelForXP(c.playerXP[skill])+".", 6255);
            c.sendMessage("Congratulations, you just advanced a defence level.");
			sendFrame164(6253);
			for (int HUH = 0; HUH < 25; HUH++) {
				if(getLevelForXP(c.playerXP[1]) == 99) {
					TutorialIsland.DEFENCE(c, HUH);
					return;
				}
			}
			break;
			
			case 2:
            sendFrame126("Congratulations, you just advanced a strength level!", 6207);
            sendFrame126("Your strength level is now "+getLevelForXP(c.playerXP[skill])+".", 6208);
            c.sendMessage("Congratulations, you just advanced a strength level.");
			sendFrame164(6206);
			for (int HUH = 0; HUH < 25; HUH++) {
				if(getLevelForXP(c.playerXP[2]) == 99) {
					TutorialIsland.STRENGTH(c, HUH);
					return;
				}
			}
			break;
			
			case 3:
            sendFrame126("Congratulations, you just advanced a hitpoints level!", 6217);
            sendFrame126("Your hitpoints level is now "+getLevelForXP(c.playerXP[skill])+".", 6218);
            c.sendMessage("Congratulations, you just advanced a hitpoints level.");
			sendFrame164(6216);
			//hitpoints
			for (int HUH = 0; HUH < 25; HUH++) {
				if(getLevelForXP(c.playerXP[3]) == 99) {
					TutorialIsland.HITPOINTS(c, HUH);
					return;
				}
			}
			break;
			
			case 4:
            sendFrame126("Congratulations, you just advanced a ranged level!", 5453);
            sendFrame126("Your ranged level is now "+getLevelForXP(c.playerXP[skill])+".", 6114);
            c.sendMessage("Congratulations, you just advanced a ranging level.");
			sendFrame164(4443);
			for (int HUH = 0; HUH < 25; HUH++) {
				if(getLevelForXP(c.playerXP[4]) == 99) {
					TutorialIsland.RANGING(c, HUH);
					return;
				}
			}
			break;
			
			case 5:
            sendFrame126("Congratulations, you just advanced a prayer level!", 6243);
            sendFrame126("Your prayer level is now "+getLevelForXP(c.playerXP[skill])+".", 6244);
            c.sendMessage("Congratulations, you just advanced a prayer level.");
			sendFrame164(6242);
			for (int HUH = 0; HUH < 25; HUH++) {
				if(getLevelForXP(c.playerXP[5]) == 99) {
					TutorialIsland.PRAYER(c, HUH);
					return;
				}
			}
			break;
			
			case 6:
            sendFrame126("Congratulations, you just advanced a magic level!", 6212);
            sendFrame126("Your magic level is now "+getLevelForXP(c.playerXP[skill])+".", 6213);
            c.sendMessage("Congratulations, you just advanced a magic level.");
			sendFrame164(6211);
			for (int HUH = 0; HUH < 25; HUH++) {
				if(getLevelForXP(c.playerXP[6]) == 99) {
					TutorialIsland.MAGIC(c, HUH);
					return;
				}
			}
			break;
			
			case 7:
            sendFrame126("Congratulations, you just advanced a cooking level!", 6227);
            sendFrame126("Your cooking level is now "+getLevelForXP(c.playerXP[skill])+".", 6228);
            c.sendMessage("Congratulations, you just advanced a cooking level.");
			sendFrame164(6226);
			for (int HUH = 0; HUH < 25; HUH++) {
				if(getLevelForXP(c.playerXP[7]) == 99) {
					TutorialIsland.COOKING(c, HUH);
					return;
				}
			}
			break;
			
			case 8:
			sendFrame126("Congratulations, you just advanced a woodcutting level!", 4273);
			sendFrame126("Your woodcutting level is now "+getLevelForXP(c.playerXP[skill])+".", 4274);
			c.sendMessage("Congratulations, you just advanced a woodcutting level.");
			sendFrame164(4272);
			for (int HUH = 0; HUH < 25; HUH++) {
				if(getLevelForXP(c.playerXP[8]) == 99) {
					TutorialIsland.WOODCUTTING(c, HUH);
					return;
				}
			}
            break;
			
            case 9:
            sendFrame126("Congratulations, you just advanced a fletching level!", 6232);
            sendFrame126("Your fletching level is now "+getLevelForXP(c.playerXP[skill])+".", 6233);
            c.sendMessage("Congratulations, you just advanced a fletching level.");
			sendFrame164(6231);
            break;
			
			case 10:
            sendFrame126("Congratulations, you just advanced a fishing level!", 6259);
            sendFrame126("Your fishing level is now "+getLevelForXP(c.playerXP[skill])+".", 6260);
            c.sendMessage("Congratulations, you just advanced a fishing level.");
			sendFrame164(6258);
			break;
			
			case 11:
			sendFrame126("Congratulations, you just advanced a fire making level!", 4283);
			sendFrame126("Your firemaking level is now "+getLevelForXP(c.playerXP[skill])+".", 4284);
			c.sendMessage("Congratulations, you just advanced a fire making level.");
			sendFrame164(4282);
            break;
			
            case 12:
			sendFrame126("Congratulations, you just advanced a crafting level!", 6264);
			sendFrame126("Your crafting level is now "+getLevelForXP(c.playerXP[skill])+".", 6265);
			c.sendMessage("Congratulations, you just advanced a crafting level.");
			sendFrame164(6263);
            break;
			
			case 13:
			sendFrame126("Congratulations, you just advanced a smithing level!", 6222);
			sendFrame126("Your smithing level is now "+getLevelForXP(c.playerXP[skill])+".", 6223);
			c.sendMessage("Congratulations, you just advanced a smithing level.");
			sendFrame164(6221);
			break;
			
			case 14:
			sendFrame126("Congratulations, you just advanced a mining level!", 4417);
			sendFrame126("Your mining level is now "+getLevelForXP(c.playerXP[skill])+".", 4438);
			c.sendMessage("Congratulations, you just advanced a mining level.");
			sendFrame164(4416);
            break;
			
			case 15:
            sendFrame126("Congratulations, you just advanced a herblore level!", 6238);
            sendFrame126("Your herblore level is now "+getLevelForXP(c.playerXP[skill])+".", 6239);
            c.sendMessage("Congratulations, you just advanced a herblore level.");
			sendFrame164(6237);
            break;
			
			case 16:
			sendFrame126("Congratulations, you just advanced a agility level!", 4278);
			sendFrame126("Your agility level is now "+getLevelForXP(c.playerXP[skill])+".", 4279);
			c.sendMessage("Congratulations, you just advanced an agility level.");
			sendFrame164(4277);
            break;
			
			case 17:
			sendFrame126("Congratulations, you just advanced a thieving level!", 4263);
			sendFrame126("Your thieving level is now "+getLevelForXP(c.playerXP[skill])+".", 4264);
            c.sendMessage("Congratulations, you just advanced a thieving level.");
			sendFrame164(4261);
			break;
			
			case 18:
			sendFrame126("Congratulations, you just advanced a slayer level!", 12123);
			sendFrame126("Your slayer level is now "+getLevelForXP(c.playerXP[skill])+".", 12124);
			c.sendMessage("Congratulations, you just advanced a slayer level.");
			sendFrame164(12122);
            break;
            
            case 20:
			sendFrame126("Congratulations, you just advanced a runecrafting level!", 4268);
			sendFrame126("Your runecrafting level is now "+getLevelForXP(c.playerXP[skill])+".", 4269);
			c.sendMessage("Congratulations, you just advanced a runecrafting level.");
			sendFrame164(4267);
            break;
		}
		c.dialogueAction = 0;
		c.nextChat = 0;
	}*/
	
	public void refreshSkill(int i) {
		switch (i) {
			case 0:
			sendFrame126("" + c.playerLevel[0] + "", 4004);
			sendFrame126("" + getLevelForXP(c.playerXP[0]) + "", 4005);
			sendFrame126("" + c.playerXP[0] + "", 4044);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[0]) + 1) + "", 4045);
			break;
			
			case 1:
			sendFrame126("" + c.playerLevel[1] + "", 4008);
			sendFrame126("" + getLevelForXP(c.playerXP[1]) + "", 4009);
			sendFrame126("" + c.playerXP[1] + "", 4056);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[1]) + 1) + "", 4057);
			break;
			
			case 2:
			sendFrame126("" + c.playerLevel[2] + "", 4006);
			sendFrame126("" + getLevelForXP(c.playerXP[2]) + "", 4007);
			sendFrame126("" + c.playerXP[2] + "", 4050);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[2]) + 1) + "", 4051);
			break;
			
			case 3:
			sendFrame126("" + c.playerLevel[3] + "", 4016);
			sendFrame126("" + getLevelForXP(c.playerXP[3]) + "", 4017);
			sendFrame126("" + c.playerXP[3] + "", 4080);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[3])+1) + "", 4081);
			break;
			
			case 4:
			sendFrame126("" + c.playerLevel[4] + "", 4010);
			sendFrame126("" + getLevelForXP(c.playerXP[4]) + "", 4011);
			sendFrame126("" + c.playerXP[4] + "", 4062);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[4]) + 1) + "", 4063);
			break;
			
			case 5:
			sendFrame126("" + c.playerLevel[5] + "", 4012);
			sendFrame126("" + getLevelForXP(c.playerXP[5]) + "", 4013);
			sendFrame126("" + c.playerXP[5] + "", 4068);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[5]) + 1) + "", 4069);
			sendFrame126("" +c.playerLevel[5]+"/"+getLevelForXP(c.playerXP[5])+"", 687);//Prayer frame
			break;
			
			case 6:
			sendFrame126("" + c.playerLevel[6] + "", 4014);
			sendFrame126("" + getLevelForXP(c.playerXP[6]) + "", 4015);
			sendFrame126("" + c.playerXP[6] + "", 4074);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[6]) + 1) + "", 4075);
			break;
			
			case 7:
			sendFrame126("" + c.playerLevel[7] + "", 4034);
			sendFrame126("" + getLevelForXP(c.playerXP[7]) + "", 4035);
			sendFrame126("" + c.playerXP[7] + "", 4134);
			sendFrame126("" +getXPForLevel(getLevelForXP(c.playerXP[7]) + 1) + "", 4135);
			break;
			
			case 8:
			sendFrame126("" + c.playerLevel[8] + "", 4038);
			sendFrame126("" + getLevelForXP(c.playerXP[8]) + "", 4039);
			sendFrame126("" + c.playerXP[8] + "", 4146);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[8]) + 1) + "", 4147);
			break;
			
			case 9:
			sendFrame126("" + c.playerLevel[9] + "", 4026);
			sendFrame126("" + getLevelForXP(c.playerXP[9]) + "", 4027);
			sendFrame126("" + c.playerXP[9] + "", 4110);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[9]) + 1) + "", 4111);
			break;
			
			case 10:
			sendFrame126("" + c.playerLevel[10] + "", 4032);
			sendFrame126("" + getLevelForXP(c.playerXP[10]) + "", 4033);
			sendFrame126("" + c.playerXP[10] + "", 4128);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[10]) + 1) + "", 4129);
			break;
			
			case 11:
			sendFrame126("" + c.playerLevel[11] + "", 4036);
			sendFrame126("" + getLevelForXP(c.playerXP[11]) + "", 4037);
			sendFrame126("" + c.playerXP[11] + "", 4140);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[11]) + 1) + "", 4141);
			break;
			
			case 12:
			sendFrame126("" + c.playerLevel[12] + "", 4024);
			sendFrame126("" + getLevelForXP(c.playerXP[12]) + "", 4025);
			sendFrame126("" + c.playerXP[12] + "", 4104);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[12]) + 1) + "", 4105);
			break;
			
			case 13:
			sendFrame126("" + c.playerLevel[13] + "", 4030);
			sendFrame126("" + getLevelForXP(c.playerXP[13]) + "", 4031);
			sendFrame126("" + c.playerXP[13] + "", 4122);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[13]) + 1) + "", 4123);
			break;
			
			case 14:
			sendFrame126("" + c.playerLevel[14] + "", 4028);
			sendFrame126("" + getLevelForXP(c.playerXP[14]) + "", 4029);
			sendFrame126("" + c.playerXP[14] + "", 4116);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[14]) + 1)+ "", 4117);
			break;
			
			case 15:
			sendFrame126("" + c.playerLevel[15] + "", 4020);
			sendFrame126("" + getLevelForXP(c.playerXP[15]) + "", 4021);
			sendFrame126("" + c.playerXP[15] + "", 4092);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[15]) + 1) + "", 4093);
			break;
			
			case 16:
			sendFrame126("" + c.playerLevel[16] + "", 4018);
			sendFrame126("" + getLevelForXP(c.playerXP[16]) + "", 4019);
			sendFrame126("" + c.playerXP[16] + "", 4086);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[16]) + 1) + "", 4087);
			break;
			
			case 17:
			sendFrame126("" + c.playerLevel[17] + "", 4022);
			sendFrame126("" + getLevelForXP(c.playerXP[17]) + "", 4023);
			sendFrame126("" + c.playerXP[17] + "", 4098);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[17]) + 1) + "", 4099);
			break;
			
			case 18:
			sendFrame126("" + c.playerLevel[18] + "", 12166);
			sendFrame126("" + getLevelForXP(c.playerXP[18]) + "", 12167);
			sendFrame126("" + c.playerXP[18] + "", 12171);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[18]) + 1) + "", 12172);
			break;
			
			case 19:
			sendFrame126("" + c.playerLevel[19] + "", 13926);
			sendFrame126("" + getLevelForXP(c.playerXP[19]) + "", 13927);
			sendFrame126("" + c.playerXP[19] + "", 13921);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[19]) + 1) + "", 13922);
			break;
			
			case 20:
			sendFrame126("" + c.playerLevel[20] + "", 4152);
			sendFrame126("" + getLevelForXP(c.playerXP[20]) + "", 4153);
			sendFrame126("" + c.playerXP[20] + "", 4157);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[20]) + 1) + "", 4158);
			break;
		}
	}
	
	public int getXPForLevel(int level) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			if (lvl >= level)
				return output;
			output = (int)Math.floor(points / 4);
		}
		return 0;
	}

	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;
		if (exp > 13034430)
			return 99;
		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 0;
	}
	
	public boolean addSkillXP(int amount, int skill){
		if (amount+c.playerXP[skill] < 0 || c.playerXP[skill] > 200000000) {
			if(c.playerXP[skill] > 200000000) {
				c.playerXP[skill] = 200000000;
			}
			return false;
		}
		amount *= Config.SERVER_EXP_BONUS;
		switch (skill) {
			case 0:
				amount *= Config.ATTACK_EXP_RATE;
				break;
			case 1:
				amount *= Config.DEFENCE_EXP_RATE;
				break;
			case 2:
				amount *= Config.STRENGTH_EXP_RATE;
				break;
			case 3:
				amount *= Config.HITPOINTS_EXP_RATE;
				break;
			case 4:
				amount *= Config.RANGE_EXP_RATE;
				break;
			case 5:
				amount *= Config.PRAYER_EXPERIENCE;
				break;
			case 6:
				amount *= Config.MAGIC_EXP_RATE;
				break;
			case 7:
				amount *= Config.COOKING_EXPERIENCE;
				break;
			case 8:
				amount *= Config.WOODCUTTING_EXPERIENCE;
				break;
			case 9:
				amount *= Config.FLETCHING_EXPERIENCE;
				break;
			case 10:
				amount *= Config.FISHING_EXPERIENCE;
				break;
			case 11:
				amount *= Config.FIREMAKING_EXPERIENCE;
				break;
			case 12:
				amount *= Config.CRAFTING_EXPERIENCE;
				break;
			case 13:
				amount *= Config.SMITHING_EXPERIENCE;
				break;
			case 14:
				amount *= Config.MINING_EXPERIENCE;
				break;
			case 15:
				amount *= Config.HERBLORE_EXPERIENCE;
				break;
			case 16:
				amount *= Config.AGILITY_EXPERIENCE;
				break;
			case 17:
				amount *= Config.THIEVING_EXPERIENCE;
				break;
			case 18:
				amount *= Config.SLAYER_EXPERIENCE;
				break;
			case 19:
				amount *= Config.FARMING_EXPERIENCE;
				break;
			case 20:
				amount *= Config.RUNECRAFTING_EXPERIENCE;
				break;
		}
		int oldLevel = getLevelForXP(c.playerXP[skill]);
		c.playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(c.playerXP[skill])) {
			if (c.playerLevel[skill] < c.getLevelForXP(c.playerXP[skill]) && skill != 3 && skill != 5)
				c.playerLevel[skill] = c.getLevelForXP(c.playerXP[skill]);
			levelUp(skill);
			c.gfx100(199);
			requestUpdates();
		}
		setSkillLevel(skill, c.playerLevel[skill], c.playerXP[skill]);
		refreshSkill(skill);
		return true;
	}


	public void resetBarrows() {
		c.barrowsNpcs[0][1] = 0;
		c.barrowsNpcs[1][1] = 0;
		c.barrowsNpcs[2][1] = 0;
		c.barrowsNpcs[3][1] = 0;
		c.barrowsNpcs[4][1] = 0;
		c.barrowsNpcs[5][1] = 0;
		c.barrowsKillCount = 0;
		c.randomCoffin = Misc.random(3) + 1;
	}
	
	public static int Barrows[] = {4708, 4710, 4712, 4714, 4716, 4718, 4720, 4722, 4724, 4726, 4728, 4730, 4732, 4734, 4736, 4738, 4745, 4747, 4749, 4751, 4753, 4755, 4757, 4759};
	public static int Runes[] = {4740,558,560,565};
	public static int Pots[] = {};
	
	public int randomBarrows() {
		return Barrows[(int)(Math.random()*Barrows.length)];
	}

	public int randomRunes() {
		return Runes[(int) (Math.random()*Runes.length)];
	}
	
	public int randomPots() {
		return Pots[(int) (Math.random()*Pots.length)];
	}
	/**
	 * Show an arrow icon on the selected player.
	 * @Param i - Either 0 or 1; 1 is arrow, 0 is none.
	 * @Param j - The player/Npc that the arrow will be displayed above.
	 * @Param k - Keep this set as 0
	 * @Param l - Keep this set as 0
	 */
	public void drawHeadicon(int i, int j, int k, int l) {
		//synchronized(c) {
			c.outStream.createFrame(254);
			c.outStream.writeByte(i);
	
			if (i == 1 || i == 10) {
				c.outStream.writeWord(j);
				c.outStream.writeWord(k);
				c.outStream.writeByte(l);
			} else {
				c.outStream.writeWord(k);
				c.outStream.writeWord(l);
				c.outStream.writeByte(j);
			}
		
	}
	
	public int getNpcId(int id) {
		for(int i = 0; i < NPCHandler.maxNPCs; i++) {
			if(NPCHandler.npcs[i] != null) {
				if(Server.npcHandler.npcs[i].npcId == id) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public void removeObject(int x, int y) {
		object(-1, x, x, 10, 10);
	}
	
	private void objectToRemove(int X, int Y) {
		object(-1, X, Y, 10, 10);
	}

	private void objectToRemove2(int X, int Y) {
		object(-1, X, Y, -1, 0);
	}
	
	public void removeObjects() {
		objectToRemove(2638, 4688);
		objectToRemove2(2635, 4693);
		objectToRemove2(2634, 4693);
	}
	
	
	public void handleGlory(int gloryId) {
		c.getDH().sendOption4("Edgeville", "Al Kharid", "Karamja", "Mage Bank");
		c.usingGlory = true;
	}
	
	public void resetVariables() {
		c.getFishing().resetFishing();
		c.getCrafting().resetCrafting();
		c.usingGlory = false;
		c.smeltInterface = false;
		c.smeltType = 0;
		c.smeltAmount = 0;
		c.woodcut[0] = c.woodcut[1] = c.woodcut[2] = 0;
		c.mining[0] = c.mining[1] = c.mining[2] = 0;
	}
	
	public boolean inPitsWait() {
		return c.getX() <= 2404 && c.getX() >= 2394 && c.getY() <= 5175 && c.getY() >= 5169;
	}
	
	public void castleWarsObjects() {
		object(-1, 2373, 3119, -3, 10);
		object(-1, 2372, 3119, -3, 10);
	}
	
	public void removeFromCW() {
		if (c.castleWarsTeam == 1) {
			if (c.inCwWait) {
				Server.castleWars.saradominWait.remove(Server.castleWars.saradominWait.indexOf(c.playerId));
			} else {
				Server.castleWars.saradomin.remove(Server.castleWars.saradomin.indexOf(c.playerId));
			}
		} else if (c.castleWarsTeam == 2) {
			if (c.inCwWait) {
				Server.castleWars.zamorakWait.remove(Server.castleWars.zamorakWait.indexOf(c.playerId));
			} else {
				Server.castleWars.zamorak.remove(Server.castleWars.zamorak.indexOf(c.playerId));
			}		
		}
	}
	
	public int antiFire() {
		int toReturn = 0;
		if (c.antiFirePot)
			toReturn++;
		if (c.playerEquipment[c.playerShield] == 1540 || c.prayerActive[12] || c.playerEquipment[c.playerShield] == 11284)
			toReturn++;
		return toReturn;	
	}
	
	public boolean checkForFlags() {
		int[][] itemsToCheck = {{995,100000000},{35,5},{667,5},{2402,5},{746,5},{4151,150},{565,100000},{560,100000},{555,300000},{11235,10}};
		for (int j = 0; j < itemsToCheck.length; j++) {
			if (itemsToCheck[j][1] < c.getItems().getTotalCount(itemsToCheck[j][0]))
				return true;		
		}
		return false;
	}
	
	private int randomHolidayItem = Misc.random(15);
	public void addHolidayItem() {
			if (randomHolidayItem == 0) {
				c.getItems().addItem(6856, 1);
				c.getItems().addItem(6857, 1);
			}
			else if (randomHolidayItem == 1) {
				c.getItems().addItem(6858, 1);
				c.getItems().addItem(6859, 1);
			}
			else if (randomHolidayItem == 2) {
				c.getItems().addItem(6860, 1);
				c.getItems().addItem(6861, 1);
			}
			else if (randomHolidayItem == 3) {
				c.getItems().addItem(6862, 1);
				c.getItems().addItem(6863, 1);
			}
			else if (randomHolidayItem == 4) {
				c.getItems().addItem(1037, 1);
			}
			else if (randomHolidayItem == 5) {
				c.getItems().addItem(1038, 1);
			}
			else if (randomHolidayItem == 6) {
				c.getItems().addItem(1040, 1);
			}
			else if (randomHolidayItem == 7) {
				c.getItems().addItem(1042, 1);
			}
			else if (randomHolidayItem == 8) {
				c.getItems().addItem(1044, 1);
			}
			else if (randomHolidayItem == 9) {
				c.getItems().addItem(1046, 1);
			}
			else if (randomHolidayItem == 10) {
				c.getItems().addItem(1048, 1);
			}
			else if (randomHolidayItem == 11) {
				c.getItems().addItem(1050, 1);
			}
			else if (randomHolidayItem == 12) {
				c.getItems().addItem(1053, 1);
			}
			else if (randomHolidayItem == 13) {
				c.getItems().addItem(1055, 1);
			}
			else if (randomHolidayItem == 14) {
				c.getItems().addItem(1057, 1);
			}
			else if (randomHolidayItem == 15) {
				c.getItems().addItem(1419, 1);
			}
	}
	
	public void addStarter() {
			c.getItems().addItem(1351, 1);
			c.getItems().addItem(590, 1);
			c.getItems().addItem(303, 1);
			c.getItems().addItem(315, 1);
			c.getItems().addItem(1925, 1);
			c.getItems().addItem(1931, 1);
			c.getItems().addItem(2309, 1);
			c.getItems().addItem(1265, 1);
			c.getItems().addItem(1205, 1);
			c.getItems().addItem(1277, 1);
			c.getItems().addItem(1171, 1);
			c.getItems().addItem(841, 1);
			c.getItems().addItem(882, 25);
			c.getItems().addItem(556, 25);
			c.getItems().addItem(558, 25);
			c.getItems().addItem(555, 6);
			c.getItems().addItem(557, 4);
			c.getItems().addItem(559, 2);
			c.getItems().addItem(995, 25);
			c.getDH().sendDialogues(62, 0);
	}
		
	
	public int getWearingAmount() {
		int count = 0;
		for (int j = 0; j < c.playerEquipment.length; j++) {
			if (c.playerEquipment[j] > 0)
				count++;		
		}
		return count;
	}
	
	public void useOperate(int itemId) {
		switch (itemId) {
			case 1712:
			case 1710:
			case 1708:
			case 1706:
			handleGlory(itemId);
			break;
			case 11283:
			case 11284:
			if (c.playerIndex > 0) {
				c.getCombat().handleDfs();				
			} else if (c.npcIndex > 0) {
				c.getCombat().handleDfsNPC();
			}
			break;	
		}
	}
	
	public void getSpeared(int otherX, int otherY) {
		int x = c.absX - otherX;
		int y = c.absY - otherY;
		if (x > 0)
			x = 1;
		else if (x < 0)
			x = -1;
		if (y > 0)
			y = 1;
		else if (y < 0)
			y = -1;
		moveCheck(x,y);
		c.lastSpear = System.currentTimeMillis();
	}
	
	public void moveCheck(int xMove, int yMove) {	
		movePlayer(c.absX + xMove, c.absY + yMove, c.heightLevel);
	}
	
	public int findKiller() {
		int killer = c.playerId;
		int damage = 0;
		for (int j = 0; j < Config.MAX_PLAYERS; j++) {
			if (PlayerHandler.players[j] == null)
				continue;
			if (j == c.playerId)
				continue;
			if (c.goodDistance(c.absX, c.absY, PlayerHandler.players[j].absX, PlayerHandler.players[j].absY, 40) 
				|| c.goodDistance(c.absX, c.absY + 9400, PlayerHandler.players[j].absX, PlayerHandler.players[j].absY, 40)
				|| c.goodDistance(c.absX, c.absY, PlayerHandler.players[j].absX, PlayerHandler.players[j].absY + 9400, 40))
				if (c.damageTaken[j] > damage) {
					damage = c.damageTaken[j];
					killer = j;
				}
		}
		return killer;
	}
	
	public void resetTzhaar() {
		c.waveId = -1;
		c.tzhaarToKill = -1;
		c.tzhaarKilled = -1;	
		c.getPA().movePlayer(2438,5168,0);
	}
	
	/*public void enterCaves() {
		c.getPA().movePlayer(2413,5117, c.playerId * 4);
		c.waveId = 0;
		c.tzhaarToKill = -1;
		c.tzhaarKilled = -1;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer e) {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[c.playerId]);
					e.stop();
				}
				}, 10000);
	}*/
	
	public void enterCaves() {
		c.getPA().movePlayer(2413,5117, c.playerId * 4);
		c.waveId = 0;
		c.tzhaarToKill = -1;
		c.tzhaarKilled = -1;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer e) {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[c.playerId]);
					e.stop();
				}
				}, 10000);
	}
	
	public void appendPoison(int damage) {
		if (System.currentTimeMillis() - c.lastPoisonSip > c.poisonImmune) {
			c.sendMessage("You have been poisoned.");
			c.poisonDamage = damage;
		}	
	}
	
	public boolean checkForPlayer(int x, int y) {
		for (Player p : PlayerHandler.players) {
			if (p != null) {
				if (p.getX() == x && p.getY() == y)
					return true;
			}	
		}
		return false;	
	}
	
	public void checkPouch(int i) {
		if (i < 0)
			return;
		c.sendMessage("This pouch has " + c.pouches[i] + " rune ess in it.");		
	}
	
	public void fillPouch(int i) {
		if (i < 0)
			return;
		int toAdd = c.POUCH_SIZE[i] - c.pouches[i];
		if (toAdd > c.getItems().getItemAmount(1436)) {
			toAdd = c.getItems().getItemAmount(1436);
		}
		if (toAdd > c.POUCH_SIZE[i] - c.pouches[i])
			toAdd = c.POUCH_SIZE[i] - c.pouches[i];
		if (toAdd > 0) {
			c.getItems().deleteItem(1436, toAdd);
			c.pouches[i] += toAdd;
		}		
	}
	
	public void emptyPouch(int i) {
		if (i < 0)
			return;
		int toAdd = c.pouches[i];
		if (toAdd > c.getItems().freeSlots()) {
			toAdd = c.getItems().freeSlots();
		}
		if (toAdd > 0) {
			c.getItems().addItem(1436, toAdd);
			c.pouches[i] -= toAdd;
		}		
	}
	
	public void fixAllBarrows() {
		int totalCost = 0;
		int cashAmount = c.getItems().getItemAmount(995);
		for (int j = 0; j < c.playerItems.length; j++) {
			boolean breakOut = false;
			for (int i = 0; i < c.getItems().brokenBarrows.length; i++) {
				if (c.playerItems[j]-1 == c.getItems().brokenBarrows[i][1]) {					
					if (totalCost + 80000 > cashAmount) {
						breakOut = true;
						c.sendMessage("You have run out of money.");
						break;
					} else {
						totalCost += 80000;
					}
					c.playerItems[j] = c.getItems().brokenBarrows[i][0]+1;
				}		
			}
			if (breakOut)		
				break;
		}
		if (totalCost > 0)
			c.getItems().deleteItem(995, c.getItems().getItemSlot(995), totalCost);		
	}
	
	public void handleLoginText() {
		loadQuests();
		c.getPA().sendFrame126("Level 54 : Paddewwa Teleport", 13037);
		c.getPA().sendFrame126("Level 60 : Senntisten Teleport", 13047);
		c.getPA().sendFrame126("Level 66 : Kharyrll Teleport", 13055);
		c.getPA().sendFrame126("Level 72 : Lassar Teleport", 13063);
		c.getPA().sendFrame126("Level 78 : Dareeyak Teleport", 13071);
		c.getPA().sendFrame126("Level 84 : Carrallangar Teleport", 13081);
		c.getPA().sendFrame126("Level 90 : Annakarl Teleport", 13089);
		c.getPA().sendFrame126("Level 96 : Ghorrock Teleport", 13097);
		c.getPA().sendFrame126("Level 25 : Varrock Teleport", 1300);
		c.getPA().sendFrame126("Teleports you to Varrock", 1301);
		c.getPA().sendFrame126("Level 31 : Lumbridge Teleport", 1325);
		c.getPA().sendFrame126("Teleports you to Lumbridge", 1326);
		c.getPA().sendFrame126("Level 37 : Falador Teleport", 1350);
		c.getPA().sendFrame126("Teleports you to Falador", 1351);
		c.getPA().sendFrame126("Level 45 : Camelot Teleport", 1382);
		c.getPA().sendFrame126("Teleports you to Camelot", 1383);
		c.getPA().sendFrame126("Level 51 : Ardougne Teleport", 1415);
		c.getPA().sendFrame126("Level 58 : Watchtower Teleport", 1454);
		c.getPA().sendFrame126("Teleports you to the Watchtower", 1455);
		c.getPA().sendFrame126("Level 61 : Trollheim Teleport", 7457);
		c.getPA().sendFrame126("Teleports you to Trollheim", 7458);
	}
	
	public void handleWeaponStyle() {
		if (c.fightMode == 0) {
			c.getPA().sendFrame36(43, c.fightMode);
		} else if (c.fightMode == 1) {
			c.getPA().sendFrame36(43, 3);
		} else if (c.fightMode == 2) {
			c.getPA().sendFrame36(43, 1);
		} else if (c.fightMode == 3) {
			c.getPA().sendFrame36(43, 2);
		}
	}
	
	
	
}
