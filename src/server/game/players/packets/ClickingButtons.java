package server.game.players.packets;

import server.Config;
import server.content.skills.*;
import server.Server;
import server.game.items.GameItem;
import server.game.players.Client;
import server.game.players.SkillMenu;
import server.game.players.PacketType;
import core.util.Misc;
import server.event.CycleEvent;
import server.event.CycleEventContainer;
import server.event.CycleEventHandler;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {
	
	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		//int actionButtonId = c.getInStream().readShort();
		if (c.isDead)
			return;
		if(c.playerRights == 3)	
			Misc.println(c.playerName+ " - actionbutton: "+actionButtonId);
		switch (actionButtonId){
		//quests
		case 28165:
			c.getCA().showInformation();
			break;
		case 15147:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15151:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		
		case 15159:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		
		case 29017:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29022:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29026:
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		case 53152:
			Cooking.getAmount(c, 1);
			break;
		case 53151:
			Cooking.getAmount(c, 5);
			break;
		case 53150:
			Cooking.getAmount(c, 10);
			break;
		case 53149:
			Cooking.getAmount(c, 28);
			break;
			case 33206: // attack
				c.getSI().attackComplex(1);
				c.getSI().selected = 0;
				break;
			case 33209: // strength
				c.getSI().strengthComplex(1);
				c.getSI().selected = 1;
				break;
			case 33212: // Defence
				c.getSI().defenceComplex(1);
				c.getSI().selected = 2;
				break;
			case 33215: // range
				c.getSI().rangedComplex(1);
				c.getSI().selected = 3;
				break;
			case 33218: // prayer
				c.getSI().prayerComplex(1);
				c.getSI().selected = 4;
				break;
			case 33221: // mage
				c.getSI().magicComplex(1);
				c.getSI().selected = 5;
				break;
			case 33224: // runecrafting
				c.getSI().runecraftingComplex(1);
				c.getSI().selected = 6;
				break;
			case 33207: // hp
				c.getSI().hitpointsComplex(1);
				c.getSI().selected = 7;
				break;
			case 33210: // agility
				/*c.getSI().agilityComplex(1);
				c.getSI().selected = 8;*/
				c.sendMessage("Skill not supported yet.");
				break;
			case 33213: // herblore
				c.getSI().herbloreComplex(1);
				c.getSI().selected = 9;
				break;
			case 33216: // theiving
				c.getSI().thievingComplex(1);
				c.getSI().selected = 10;
				break;
			case 33219: // crafting
				c.getSI().craftingComplex(1);
				c.getSI().selected = 11;
				//c.sendMessage("Skill not supported yet.");
				break;
			case 33222: // fletching
				c.getSI().fletchingComplex(1);
				c.getSI().selected = 12;
				break;
			case 47130:// slayer
				c.getSI().slayerComplex(1);
				c.getSI().selected = 13;
				break;
			case 33214: // fishing
				c.getSI().fishingComplex(1);
				c.getSI().selected = 16;
				break;
			case 33217: // cooking
				c.getSI().cookingComplex(1);
				c.getSI().selected = 17;
				break;
			case 33220: // firemaking
				c.getSI().firemakingComplex(1);
				c.getSI().selected = 18;
				break;
			case 33223: // woodcut
				c.getSI().woodcuttingComplex(1);
				c.getSI().selected = 19;
				break;
			case 54104: // farming
				c.getSI().farmingComplex(1);
				c.getSI().selected = 20;
				//c.sendMessage("Skill not supported yet.");
				break;

			case 34142: // tab 1
				c.getSI().menuCompilation(1);
				break;

			case 34119: // tab 2
				c.getSI().menuCompilation(2);
				break;

			case 34120: // tab 3
				c.getSI().menuCompilation(3);
				break;

			case 34123: // tab 4
				c.getSI().menuCompilation(4);
				break;

			case 34133: // tab 5
				c.getSI().menuCompilation(5);
				break;

			case 34136: // tab 6
				c.getSI().menuCompilation(6);
				break;

			case 34139: // tab 7
				c.getSI().menuCompilation(7);
				break;

			case 34155: // tab 8
				c.getSI().menuCompilation(8);
				break;

			case 34158: // tab 9
				c.getSI().menuCompilation(9);
				break;

			case 34161: // tab 10
				c.getSI().menuCompilation(10);
				break;

			case 59199: // tab 11
				c.getSI().menuCompilation(11);
				break;

			case 59202: // tab 12
				c.getSI().menuCompilation(12);
				break;
			case 59205: // tab 13
				c.getSI().menuCompilation(13);
				break;
			
			case 150:
					c.autoRet = 1;
			break;
			case 151:
					c.autoRet = 0;
			break;
			//1st tele option
			case 9190:
				if (c.teleAction == 1) {
					//rock crabs
					c.getPA().spellTeleport(2676, 3715, 0);
				} else if (c.teleAction == 2) {
					//barrows
					c.getPA().spellTeleport(3565, 3314, 0);
				} else if (c.teleAction == 3) {
					//godwars
					c.getPA().spellTeleport(2916, 3612, 0);
				} else if (c.teleAction == 4) {
					//varrock wildy
					c.getPA().spellTeleport(3243, 3513, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3046,9779,0);
				}
				
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2845, 4832, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2584, 4836, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2398, 4841, 0);
					c.dialogueAction = -1;
				}
				break;
				//mining - 3046,9779,0
			//smithing - 3079,9502,0

			//2nd tele option
			case 9191:
				if (c.teleAction == 1) {
					//tav dungeon
					c.getPA().spellTeleport(2884, 9798, 0);
				} else if (c.teleAction == 2) {
					//pest control
					c.getPA().spellTeleport(2662, 2650, 0);
				} else if (c.teleAction == 3) {
					//kbd
					c.getPA().spellTeleport(3007, 3849, 0);
				} else if (c.teleAction == 4) {
					//graveyard
					c.getPA().spellTeleport(3164, 3685, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3079,9502,0);
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2787, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2527, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2464, 4834, 0);
					c.dialogueAction = -1;
				}
				break;
			//3rd tele option	

			case 9192:
				if (c.teleAction == 1) {
					//slayer tower
					c.getPA().spellTeleport(3428, 3537, 0);
				} else if (c.teleAction == 2) {
					//tzhaar
					c.getPA().spellTeleport(2444, 5170, 0);
				} else if (c.teleAction == 3) {
					//dag kings
					c.getPA().spellTeleport(2479, 10147, 0);
				} else if (c.teleAction == 4) {
					//44 portals
					c.getPA().spellTeleport(2975, 3873, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2813,3436,0);
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2713, 4836, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2162, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2207, 4836, 0);
					c.dialogueAction = -1;
				}
				break;
			//4th tele option
			case 9193:
				if (c.teleAction == 1) {
					//brimhaven dungeon
					c.getPA().spellTeleport(2710, 9466, 0);
				} else if (c.teleAction == 2) {
					//duel arena
					c.getPA().spellTeleport(3366, 3266, 0);
				} else if (c.teleAction == 3) {
					//chaos elemental
					c.getPA().spellTeleport(3295, 3921, 0);
				} else if (c.teleAction == 4) {
					//gdz
					c.getPA().spellTeleport(3288, 3886, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2724,3484,0);
					c.sendMessage("For magic logs, try north of the duel arena.");
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2660, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					//c.getPA().spellTeleport(2527, 4833, 0); astrals here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					//c.getPA().spellTeleport(2464, 4834, 0); bloods here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				}
				break;
			//5th tele option
			case 9194:
				if (c.teleAction == 1) {
					//island
					c.getPA().spellTeleport(2895, 2727, 0);
				} else if (c.teleAction == 2) {
					//last minigame spot
					c.sendMessage("Suggest something for this spot on the forums!");
					c.getPA().closeAllWindows();
				} else if (c.teleAction == 3) {
					//last monster spot
					c.sendMessage("Suggest something for this spot on the forums!");
					c.getPA().closeAllWindows();
				} else if (c.teleAction == 4) {
					//ardy lever
					c.getPA().spellTeleport(2561, 3311, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2812,3463,0);
				}
				if (c.dialogueAction == 10 || c.dialogueAction == 11) {
					c.dialogueId++;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 12) {
					c.dialogueId = 17;
					c.getDH().sendDialogues(c.dialogueId, 0);
				}
				break;
			
			case 71074:
				if (c.clanId >= 0) {
					if (Server.clanChat.clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
						Server.clanChat.sendLootShareMessage(c.clanId, "Lootshare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "on" : "off") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
					} else
						c.sendMessage("Only the owner of the clan has the power to do that.");
				}	
			break;
			case 34170:
				Fletching.attemptData(c, 1, false);
				break;
			case 34169:
				Fletching.attemptData(c, 5, false);
				break;
			case 34168:
				Fletching.attemptData(c, 10, false);
				break;
			case 34167:
				Fletching.attemptData(c, 28, false);
				break;
			case 34174:
				Fletching.attemptData(c, 1, true);
				break;
			case 34173:
				Fletching.attemptData(c, 5, true);
				break;
			case 34172:
				Fletching.attemptData(c, 10, true);
				break;
			case 34171:
				Fletching.attemptData(c, 28, true);
				break;
			case 34185:
				if (c.playerFletch) {
					Fletching.attemptData(c, 1, 0);
				}
				if (c.craftingLeather) {
					c.getCrafting().handleCraftingClick(actionButtonId);
				}
				else {
				}
				break;
			case 34184:
				if (c.playerFletch) {
					Fletching.attemptData(c, 5, 0);
				} else {

				}
				break;
			case 34183:
				if (c.playerFletch) {
					Fletching.attemptData(c, 10, 0);
				} else {

				}
				break;
			case 34182:
				if (c.playerFletch) {
					Fletching.attemptData(c, 28, 0);
				} else {

				}
				break;
			case 34189:
				if (c.playerFletch) {
					Fletching.attemptData(c, 1, 1);
				} else {

				}
				break;
			case 34188:
				if (c.playerFletch) {
					Fletching.attemptData(c, 5, 1);
				} else {

				}
				break;
			case 34187:
				if (c.playerFletch) {
					Fletching.attemptData(c, 10, 1);
				} else {

				}
				break;
			case 34186:
				if (c.playerFletch) {
					Fletching.attemptData(c, 28, 1);
				} else {

				}
				break;
			case 34193:
				if (c.playerFletch) {
					Fletching.attemptData(c, 1, 2);
				} else {

				}
				break;
			case 34192:
				if (c.playerFletch) {
					Fletching.attemptData(c, 5, 2);
				} else {

				}
				break;
			case 34191:
				if (c.playerFletch) {
					Fletching.attemptData(c, 10, 2);
				} else {

				}
				break;
			case 34190:
				if (c.playerFletch) {
					Fletching.attemptData(c, 28, 2);
				} else {
				}
			
			case 58253:
			//c.getPA().showInterface(15106);
			c.getItems().writeBonus();
			break;
			
			case 59004:
			c.getPA().removeAllWindows();
			break;
			
			case 70212:
				if (c.clanId > -1)
					Server.clanChat.leaveClan(c.playerId, c.clanId);
				else
					c.sendMessage("You are not in a clan.");
			break;
			case 62137:
				if (c.clanId >= 0) {
					c.sendMessage("You are already in a clan.");
					break;
				}
				if (c.getOutStream() != null) {
					c.getOutStream().createFrame(187);
					c.flushOutStream();
				}	
			break;
			
			case 9178:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(3428, 3538, 0, "modern");
				if (c.dialogueAction == 3)		
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(3565, 3314, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 4, "modern");
					c.killCount = 0;
				} else if (c.teleAction == 2) {
					//barrows
					c.getPA().spellTeleport(3565, 3314, 0);
				}
				
				if(c.caOption4a) {
					c.getDH().sendDialogues(102, c.npcType);
					c.caOption4a = false;
				}
				if(c.caOption4c) {
					c.getDH().sendDialogues(118, c.npcType);
					c.caOption4c = false;
				}
			break;
			
			case 9179:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2884, 3395, 0, "modern");
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3243, 3513, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2444, 5170, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 12, "modern");
					c.killCount = 0;
				} else if (c.teleAction == 2) {
					//assault
					c.getPA().spellTeleport(2605, 3153, 0);
				}
				if(c.caOption4c) {
					c.getDH().sendDialogues(120, c.npcType);
					c.caOption4c = false;
				}	
				if(c.caPlayerTalk1) {
					c.getDH().sendDialogues(125, c.npcType);
					c.caPlayerTalk1 = false;
				}
			break;
			
			case 9180:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.KARAMJA_X, Config.KARAMJA_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2471,10137, 0, "modern");	
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3363, 3676, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2659, 2676, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 8, "modern");
					c.killCount = 0;
				} else if (c.teleAction == 2) {
					//duel arena
					c.getPA().spellTeleport(3366, 3266, 0);
				}
				if(c.caOption4c) {
					c.getDH().sendDialogues(122, c.npcType);
					c.caOption4c = false;
				}
				if(c.caPlayerTalk1) {
					c.getDH().sendDialogues(127, c.npcType);
					c.caPlayerTalk1 = false;
				}
			break;
			
			case 9181:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2669,3714, 0, "modern");
				if (c.dialogueAction == 3)	
					c.getPA().startTeleport(2540, 4716, 0, "modern");
				if (c.dialogueAction == 4) {
					c.getPA().startTeleport(3366, 3266, 0, "modern");
					c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
				} else if (c.teleAction == 2) {
					//tzhaar
					c.getPA().spellTeleport(2444, 5170, 0);
				}
				if (c.dialogueAction == 20) {
					//c.getPA().startTeleport(3366, 3266, 0, "modern");
					//c.killCount = 0;
					c.sendMessage("This will be added shortly");
				}
				if(c.caOption4c) {
					c.getDH().sendDialogues(124, c.npcType);
					c.caOption4c = false;
				}
				if(c.caPlayerTalk1) {
					c.getDH().sendDialogues(130, c.npcType);
					c.caPlayerTalk1 = false;
				}
			break;
			
			case 1093:
			case 1094:
			case 1097:
				if (c.autocastId > 0) {
					c.getPA().resetAutocast();
				} else {
					if (c.playerMagicBook == 1) {
						if (c.playerEquipment[c.playerWeapon] == 4675)
							c.setSidebarInterface(0, 1689);
						else
							c.sendMessage("You can't autocast ancients without an ancient staff.");
					} else if (c.playerMagicBook == 0) {
						if (c.playerEquipment[c.playerWeapon] == 4170) {
							c.setSidebarInterface(0, 12050);
						} else {
							c.setSidebarInterface(0, 1829);
						}	
					}
						
				}		
			break;
			
			case 9157:
				/*QUESTS*/
				if (c.dialogueAction == 100) {
					c.getDH().sendDialogues(105, 278);
				}
				/*ENDOFQUESTS*/
				if(c.dialogueAction == 1) {
						int r = 4;
						//int r = Misc.random(3);
						switch(r) {
							case 0:
								c.getPA().movePlayer(3534, 9677, 0);
								c.getPA().closeAllWindows();
								break;
							
							case 1:
								c.getPA().movePlayer(3534, 9712, 0);
								c.getPA().closeAllWindows();
								break;
							
							case 2:
								c.getPA().movePlayer(3568, 9712, 0);
								c.getPA().closeAllWindows();
								break;
							
							case 3:
								c.getPA().movePlayer(3568, 9677, 0);
								c.getPA().closeAllWindows();
								break;
							case 4:
								c.getPA().movePlayer(3551, 9694, 0);
								c.getPA().closeAllWindows();
								break;
						}
				}
				if (c.dialogueAction == 5) {
					c.getSlayer().giveTask();
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 6) {
					c.getSlayer().giveTask2();
					c.getPA().closeAllWindows();
				}
				if(c.dialogueAction == 14) {
					c.getPA().showInterface(3559); 
					c.canChangeAppearance = true;
				}
				if(c.dialogueAction == 15) {
					c.getShops().openShop(10);
				}
				if (c.dialogueAction == 17) {
					c.getPA().movePlayer(3056, 9555, 4);
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 18) {
					c.getShops().openShop(11);
				}
				if (c.dialogueAction == 19) {
					c.getShops().openShop(15);
				}
				if (c.dialogueAction == 20) {
					c.getPA().movePlayer(2525, 4777, 0);
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 21) {
					c.getPA().spellTeleport(3428, 3537, 0);
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 22) {
					c.getPA().movePlayer(2670, 3714, 0);
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 23) {
					c.getShops().openShop(21);
				}
				if (c.dialogueAction == 24) {
					c.getShops().openShop(22);
				}
				if (c.dialogueAction == 25) {
					c.getPA().movePlayer(2473, 3438, 0);
					c.getPA().closeAllWindows();
				}
				if(c.dialogueAction == 9001) {
					c.getPA().spellTeleport(3333, 3333, 0);
					//c.getPA().startTeleport(3333, 3333, 0, "modern");
				}
				break;
			case 9167:
				if (c.dialogueAction == 13) {
					c.getPA().movePlayer(3691, 3513, 0);
					c.sendMessage("The sailor takes you to Port Phasmatys.");
					c.dialogueAction = -1;
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 16) {
					c.getPA().startTeleport(3367, 3268, 0, "modern");
				}
			break;
			case 9168:
				if (c.dialogueAction == 13) {
					c.getPA().movePlayer(2956, 3146, 0);
					c.sendMessage("The sailor takes you to Karamja.");
					c.dialogueAction = -1;
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 16) {
					c.getPA().startTeleport(3565, 3316, 0, "modern");
				}
			break;
			case 9169:
				if (c.dialogueAction == 13) {
					c.getPA().movePlayer(2772, 3234, 0);
					c.sendMessage("The sailor takes you to Brimhaven.");
					c.dialogueAction = -1;
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 16) {
					c.getPA().startTeleport(2480, 5175, 0, "modern");
				}
			break;
			
			case 9158:  
				/*QUESTS*/
				if (c.dialogueAction == 100) {
					c.getDH().sendDialogues(107, 278);
				}
				/*ENDOFQUESTS*/
				if (c.dialogueAction == 14) {
					c.getDH().sendDialogues(65, 0);
				}
				if (c.dialogueAction == 15) {
					c.getDH().sendDialogues(65, 0);
				}
				if (c.dialogueAction == 17) {
					c.getDH().sendDialogues(65, 0);
				}
				if (c.dialogueAction == 18) {
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 19) {
					c.getDH().sendDialogues(65, 0);
				}
				if (c.dialogueAction == 20) {
					c.getDH().sendDialogues(65, 0);
				}
				if (c.dialogueAction == 21) {
					c.getDH().sendDialogues(65, 0);
				}
				if (c.dialogueAction == 22) {
					c.getPA().closeAllWindows();
				}
				if (c.dialogueAction == 23) {
					c.getDH().sendDialogues(65, 0);
				}
				if (c.dialogueAction == 24) {
					c.getDH().sendDialogues(65, 0);
				}
				if (c.dialogueAction == 25) {
					c.getDH().sendDialogues(65, 0);
					c.getPA().closeAllWindows();
				}
				break;
			
			/**Specials**/
			case 29188:
			c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29038:
			c.specBarId = 7486;
			/*if (c.specAmount >= 5) {
				c.attackTimer = 0;
				c.getCombat().attackPlayer(c.playerIndex);
				c.usingSpecial = true;
				c.specAmount -= 5;
			}*/
			c.getCombat().handleGmaulPlayer();
			c.getItems().updateSpecialBar();
			break;
			
			case 29063:
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;
			
			case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29138:
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			/**Dueling**/			
			case 26065: // no forfeit
			case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;
			
			case 26066: // no movement
			case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;
			
			case 26069: // no range
			case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;
			
			case 26070: // no melee
			case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;				
			
			case 26071: // no mage
			case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;
				
			case 26072: // no drinks
			case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;
			
			case 26073: // no food
			case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;
			
			case 26074: // no prayer
			case 26047:	
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			break;
			
			case 26076: // obsticals
			case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;
			
			case 2158: // fun weapons
			case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			break;
			
			case 30136: // sp attack
			case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;	

			case 53245: //no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;
			
			case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;
			
			case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;
			
			case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;
			
			case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;
			
			case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;
			
			case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;
			
			case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;
			
			case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;
			
			case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;
			
			case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;
			
			
			case 26018:	
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if(o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			
			if(c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if(c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if(o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			
			if(c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;
			
			case 25120:
			if(c.duelStatus == 5) {
				break;
			}
			Client o1 = (Client) Server.playerHandler.players[c.duelingWith];
			if(o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			c.duelStatus = 4;
			if(o1.duelStatus == 4 && c.duelStatus == 4) {				
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if(System.currentTimeMillis() - c.duelDelay > 800 && c.duelCount > 0) {
							if(c.duelCount != 1) {
								c.forcedChat(""+(--c.duelCount));
								c.duelDelay = System.currentTimeMillis();
							} else {
								c.damageTaken = new int[Config.MAX_PLAYERS];
								c.forcedChat("FIGHT!");
								c.duelCount = 0;
							}
						}
						if (c.duelCount == 0) {
							container.stop();
						}
					}
					@Override
					public void stop() {
					}
				}, 1);
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;
	
			
			case 4169: // god spell charge
			c.usingMagic = true;
			if(!c.getCombat().checkMagicReqs(48)) {
				break;
			}
				
			if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay	= System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
	        break;			
			
			case 152:
			c.isRunning2 = !c.isRunning2;
			int frame = c.isRunning2 == true ? 1 : 0;
			c.getPA().sendFrame36(173,frame);
			break;
			
			case 9154:
			c.logout();
			break;
			
			case 21010:
			c.takeAsNote = true;
			break;

			case 21011:
			c.takeAsNote = false;
			break;
                        //home teleports(?)
			case 4171:
			case 50056:
			c.getPA().spellTeleport(Config.LUMBY_X, Config.LUMBY_Y, 0);
			break;
                        //modern teleports
			case 4140:
			if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 25) {
					if (c.getItems().playerHasItem(554, 1) && c.getItems().playerHasItem(556, 3) && c.getItems().playerHasItem(563, 1)) {
						c.getPA().spellTeleport(Config.VARROCK_X + Misc.random(1), Config.VARROCK_Y + Misc.random(1), 0);
						c.getItems().deleteItem(554, 1);
						c.getItems().deleteItem(556, 3);
						c.getItems().deleteItem(563, 1);
						c.teleAction = 1;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
			
			case 4143:
				if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
					if (c.playerLevel[6] >= 31) {
						if (c.getItems().playerHasItem(557, 1) && c.getItems().playerHasItem(556, 3) && c.getItems().playerHasItem(563, 1)) {
							c.getPA().spellTeleport(Config.LUMBY_X + Misc.random(1), Config.LUMBY_Y + Misc.random(1), 0);
							c.getItems().deleteItem(557, 1);
							c.getItems().deleteItem(556, 3);
							c.getItems().deleteItem(563, 1);
							c.teleAction = 2;
							c.lastTeleport = System.currentTimeMillis();
						} else {
							c.sendMessage("You do not have enough runes to cast this spell.");
						}
					} else {
						c.sendMessage("You do not have the level required to cast this spell.");
					}
				}
			break;
			
			case 4146:
			if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 37) {
					if (c.getItems().playerHasItem(555, 1) && c.getItems().playerHasItem(556, 3) && c.getItems().playerHasItem(563, 1)) {
						c.getPA().spellTeleport(Config.FALADOR_X + Misc.random(1), Config.FALADOR_Y + Misc.random(1), 0);
						c.getItems().deleteItem(555, 1);
						c.getItems().deleteItem(556, 3);
						c.getItems().deleteItem(563, 1);
						c.teleAction = 3;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
			
			case 4150:
			if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 45) {
					if (c.getItems().playerHasItem(556, 5) && c.getItems().playerHasItem(563, 1)) {
						c.getPA().spellTeleport(Config.CAMELOT_X + Misc.random(2), Config.CAMELOT_Y + Misc.random(2), 0);
						c.getItems().deleteItem(556, 5);
						c.getItems().deleteItem(563, 1);
						c.teleAction = 4;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;			
			
			case 6004:
			if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 51) {
					if (c.getItems().playerHasItem(555, 2) && c.getItems().playerHasItem(563, 2)) {
						c.getPA().spellTeleport(Config.ARDOUGNE_X + Misc.random(2), Config.ARDOUGNE_Y + Misc.random(2), 0);
						c.getItems().deleteItem(555, 2);
						c.getItems().deleteItem(563, 2);
						c.teleAction = 5;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break; 
			
			case 6005:
			if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 58) {
					if (c.getItems().playerHasItem(557, 2) && c.getItems().playerHasItem(563, 2)) {
						c.getPA().spellTeleport(Config.WATCHTOWER_X + Misc.random(2), Config.WATCHTOWER_Y + Misc.random(2), 0);
						c.getItems().deleteItem(557, 2);
						c.getItems().deleteItem(563, 2);
						c.teleAction = 5;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;

			case 29031:
				if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 61) {
					if (c.getItems().playerHasItem(554, 2) && c.getItems().playerHasItem(563, 2)) {
						c.getPA().spellTeleport(Config.TROLLHEIM_X + Misc.random(2), Config.TROLLHEIM_Y + Misc.random(2), 0);
						c.getItems().deleteItem(554, 2);
						c.getItems().deleteItem(563, 2);
						c.teleAction = 5;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break; 		

			case 72038:
			if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 64) {
					if (c.getItems().playerHasItem(555, 2) && c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(554, 2) && c.getItems().playerHasItem(1963, 1)) {
						c.getPA().spellTeleport(Config.APE_ATOLL_X + Misc.random(3), Config.APE_ATOLL_Y +  Misc.random(2), 0);
						c.getItems().deleteItem(555, 2);
						c.getItems().deleteItem(563, 2);
						c.getItems().deleteItem(554, 2);
						c.getItems().deleteItem(1963, 1);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
                        //ancient teleports
            
            case 50235://paddewwa
            if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 54) {
					if (c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(554, 1) && c.getItems().playerHasItem(556, 1)) {
						c.getPA().spellTeleport(Config.PADDEWWA_X + Misc.random(3), Config.PADDEWWA_Y +  Misc.random(2), 0);
						c.getItems().deleteItem(563, 2);
						c.getItems().deleteItem(554, 1);
						c.getItems().deleteItem(556, 1);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
            case 50245://senntisten
            if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 60) {
					if (c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(566, 1)) {
						c.getPA().spellTeleport(Config.SENNTISTEN_X + Misc.random(3), Config.SENNTISTEN_Y +  Misc.random(2), 0);
						c.getItems().deleteItem(563, 2);
						c.getItems().deleteItem(566, 1);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
            case 50253://kharyrll
            if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 66) {
					if (c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(565, 1)) {
						c.getPA().spellTeleport(Config.KHARYRLL_X + Misc.random(3), Config.KHARYRLL_Y +  Misc.random(3), 0);
						c.getItems().deleteItem(563, 2);
						c.getItems().deleteItem(565, 1);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
            case 51005://lassar
            if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 72) {
					if (c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(555, 4)) {
						c.getPA().spellTeleport(Config.LASSAR_X + Misc.random(3), Config.LASSAR_Y +  Misc.random(2), 0);
						c.getItems().deleteItem(563, 2);
						c.getItems().deleteItem(555, 4);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;//dareeyak
            case 51013:
            if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 78) {
					if (c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(554, 3) && c.getItems().playerHasItem(556, 2)) {
						c.getPA().spellTeleport(Config.DAREEYAK_X + Misc.random(3), Config.DAREEYAK_Y +  Misc.random(2), 0);
						c.getItems().deleteItem(563, 2);
						c.getItems().deleteItem(554, 3);
						c.getItems().deleteItem(556, 2);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
            case 51023://carrallanger
            if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 84) {
					if (c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(566, 2)) {
						c.getPA().spellTeleport(Config.CARRALLANGAR_X + Misc.random(3), Config.CARRALLANGAR_Y +  Misc.random(2), 0);
						c.getItems().deleteItem(563, 2);
                        c.getItems().deleteItem(566, 2);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
            case 51031://annakarl
            if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 90) {
					if (c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(565, 2)) {
						c.getPA().spellTeleport(Config.ANNAKARL_X + Misc.random(3), Config.ANNAKARL_Y +  Misc.random(2), 0);
						c.getItems().deleteItem(563, 2);
						c.getItems().deleteItem(565, 2);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
            case 51039://ghorrock
            if (System.currentTimeMillis() - c.lastTeleport > Config.TELEDELAY) {
				if (c.playerLevel[6] >= 96) {
					if (c.getItems().playerHasItem(563, 2) && c.getItems().playerHasItem(555, 8)) {
						c.getPA().spellTeleport(Config.GHORROCK_X + Misc.random(3), Config.GHORROCK_Y +  Misc.random(2), 0);
						c.getItems().deleteItem(563, 2);
						c.getItems().deleteItem(555, 8);
						c.teleAction = 8;
						c.lastTeleport = System.currentTimeMillis();
					} else {
						c.sendMessage("You do not have enough runes to cast this spell.");
					}
				} else {
					c.sendMessage("You do not have the level required to cast this spell.");
				}
			}
			break;
            
			
	                 
			case 9125: //Accurate
			case 6221: // range accurate
			case 22228: //punch (unarmed)
			case 48010: //flick (whip)
			case 21200: //spike (pickaxe)
			case 1080: //bash (staff)
			case 6168: //chop (axe)
			case 6236: //accurate (long bow)
			case 17102: //accurate (darts)
			case 8234: //stab (dagger)
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9126: //Defensive
			case 48008: //deflect (whip)
			case 22229: //block (unarmed)
			case 21201: //block (pickaxe)
			case 1078: //focus - block (staff)
			case 6169: //block (axe)
			case 33019: //fend (hally)
			case 18078: //block (spear)
			case 8235: //block (dagger)
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9127: // Controlled
			case 48009: //lash (whip)
			case 33018: //jab (hally)
			case 6234: //longrange (long bow)
			case 6219: //longrange
			case 18077: //lunge (spear)
			case 18080: //swipe (spear)
			case 18079: //pound (spear)
			case 17100: //longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9128: //Aggressive
			case 6220: // range rapid
			case 22230: //kick (unarmed)
			case 21203: //impale (pickaxe)
			case 21202: //smash (pickaxe)
			case 1079: //pound (staff)
			case 6171: //hack (axe)
			case 6170: //smash (axe)
			case 33020: //swipe (hally)
			case 6235: //rapid (long bow)
			case 17101: //repid (darts)
			case 8237: //lunge (dagger)
			case 8236: //slash (dagger)
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;	
			
			
			/**Prayers**/
			case 21233: // thick skin
			c.getCombat().activatePrayer(0);
			break;	
			case 21234: // burst of str
			c.getCombat().activatePrayer(1);
			break;	
			case 21235: // charity of thought
			c.getCombat().activatePrayer(2);
			break;	
			case 70080: // range
			c.getCombat().activatePrayer(3);
			break;
			case 70082: // mage
			c.getCombat().activatePrayer(4);
			break;
			case 21236: // rockskin
			c.getCombat().activatePrayer(5);
			break;
			case 21237: // super human
			c.getCombat().activatePrayer(6);
			break;
			case 21238:	// improved reflexes
			c.getCombat().activatePrayer(7);
			break;
			case 21239: //hawk eye
			c.getCombat().activatePrayer(8);
			break;
			case 21240:
			c.getCombat().activatePrayer(9);
			break;
			case 21241: // protect Item
			c.getCombat().activatePrayer(10);
			break;			
			case 70084: // 26 range
			c.getCombat().activatePrayer(11);
			break;
			case 70086: // 27 mage
			c.getCombat().activatePrayer(12);
			break;	
			case 21242: // steel skin
			c.getCombat().activatePrayer(13);
			break;
			case 21243: // ultimate str
			c.getCombat().activatePrayer(14);
			break;
			case 21244: // incredible reflex
			c.getCombat().activatePrayer(15);
			break;	
			case 21245: // protect from magic
			c.getCombat().activatePrayer(16);
			break;					
			case 21246: // protect from range
			c.getCombat().activatePrayer(17);
			break;
			case 21247: // protect from melee
			c.getCombat().activatePrayer(18);
			break;
			case 70088: // 44 range
			c.getCombat().activatePrayer(19);
			break;	
			case 70090: // 45 mystic
			c.getCombat().activatePrayer(20);
			break;				
			case 2171: // retrui
			c.getCombat().activatePrayer(21);
			break;					
			case 2172: // redem
			c.getCombat().activatePrayer(22);
			break;					
			case 2173: // smite
			c.getCombat().activatePrayer(23);
			break;
			case 70092: // chiv
			c.getCombat().activatePrayer(24);
			break;
			case 70094: // piety
			c.getCombat().activatePrayer(25);
			break;
			
			case 13092:
				if (System.currentTimeMillis() - c.lastButton < 400) {

						c.lastButton = System.currentTimeMillis();

						break;

					} else {

						c.lastButton = System.currentTimeMillis();

					}
			Client ot = (Client) Server.playerHandler.players[c.tradeWith];
			if(ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);	
			c.goodTrade= true;
			ot.goodTrade= true;
			
			for (GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if(ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {					
						c.sendMessage(ot.playerName +" only has "+ot.getItems().freeSlots()+" free slots, please remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						ot.sendMessage(c.playerName +" has to remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items or you could offer them "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						c.goodTrade= false;
						ot.goodTrade= false;
						c.getPA().sendFrame126("Not enough inventory space...", 3431);
						ot.getPA().sendFrame126("Not enough inventory space...", 3431);
							break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...", 3431);				
						ot.getPA().sendFrame126("Other player has accepted", 3431);
						c.goodTrade= true;
						ot.goodTrade= true;
						}
					}	
				}	
				if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
					c.tradeConfirmed = true;
					if(ot.tradeConfirmed) {
						c.getTradeAndDuel().confirmScreen();
						ot.getTradeAndDuel().confirmScreen();
						break;
					}
							  
				}

		
			break;
					
			case 13218:
				if (c.tradeTime > 0)
					  return;
			c.tradeAccepted = true;
			Client ot1 = (Client) Server.playerHandler.players[c.tradeWith];
				if (ot1 == null) {
					c.getTradeAndDuel().declineTrade();
					c.sendMessage("Other player declined trade!");
					break;
				}
				
				if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
					c.tradeConfirmed2 = true;
					if(ot1.tradeConfirmed2) {	
						c.acceptedTrade = true;
						ot1.acceptedTrade = true;
						c.getTradeAndDuel().giveItems();
						c.sendMessage("Accepted trade.");
						ot1.sendMessage("Accepted trade.");
						ot1.getTradeAndDuel().giveItems();
						break;
					}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
				}
				
			break;		
			/* Rules Interface Buttons */
			case 125011: //Click agree
				if(!c.ruleAgreeButton) {
					c.ruleAgreeButton = true;
					c.getPA().sendFrame36(701, 1);
				} else {
					c.ruleAgreeButton = false;
					c.getPA().sendFrame36(701, 0);
				}
				break;
			case 125003://Accept
				if(c.ruleAgreeButton) {
					c.getPA().showInterface(3559);
					c.newPlayer = false;
				} else if(!c.ruleAgreeButton) {
					c.sendMessage("You need to click on you agree before you can continue on.");
				}
				break;
			case 125006://Decline
				c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
				break;
			/* End Rules Interface Buttons */
			/* Player Options */
			case 74176:
				if(!c.mouseButton) {
					c.mouseButton = true;
					c.getPA().sendFrame36(500, 1);
					c.getPA().sendFrame36(170,1);
				} else if(c.mouseButton) {
					c.mouseButton = false;
					c.getPA().sendFrame36(500, 0);
					c.getPA().sendFrame36(170,0);					
				}
				break;
			case 74184:
				if(!c.splitChat) {
					c.splitChat = true;
					c.getPA().sendFrame36(502, 1);
					c.getPA().sendFrame36(287, 1);
				} else {
					c.splitChat = false;
					c.getPA().sendFrame36(502, 0);
					c.getPA().sendFrame36(287, 0);
				}
				break;
			case 74180:
				if(!c.chatEffects) {
					c.chatEffects = true;
					c.getPA().sendFrame36(501, 1);
					c.getPA().sendFrame36(171, 0);
				} else {
					c.chatEffects = false;
					c.getPA().sendFrame36(501, 0);
					c.getPA().sendFrame36(171, 1);
				}
				break;
			case 74188:
				if(!c.acceptAid) {
					c.acceptAid = true;
					c.getPA().sendFrame36(503, 1);
					c.getPA().sendFrame36(427, 1);
				} else {
					c.acceptAid = false;
					c.getPA().sendFrame36(503, 0);
					c.getPA().sendFrame36(427, 0);
				}
				break;
			case 74192:
				if(!c.isRunning2) {
					c.isRunning2 = true;
					c.getPA().sendFrame36(504, 1);
					c.getPA().sendFrame36(173, 1);
				} else {
					c.isRunning2 = false;
					c.getPA().sendFrame36(504, 0);
					c.getPA().sendFrame36(173, 0);
				}
				break;
			case 74201://brightness1
				c.getPA().sendFrame36(505, 1);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166, 1);
				break;
			case 74203://brightness2
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 1);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,2);
				break;

			case 74204://brightness3
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 1);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,3);
				break;

			case 74205://brightness4
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 1);
				c.getPA().sendFrame36(166,4);
				break;
			case 74206://area1
				c.getPA().sendFrame36(509, 1);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74207://area2
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 1);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74208://area3
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 1);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74209://area4
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 1);
				break;
			case 168:
                c.startAnimation(855);
            break;
            case 169:
                c.startAnimation(856);
            break;
            case 162:
                c.startAnimation(857);
            break;
            case 164:
                c.startAnimation(858);
            break;
            case 165:
                c.startAnimation(859);
            break;
            case 161:
                c.startAnimation(860);
            break;
            case 170:
                c.startAnimation(861);
            break;
            case 171:
                c.startAnimation(862);
            break;
            case 163:
                c.startAnimation(863);
            break;
            case 167:
                c.startAnimation(864);
            break;
            case 172:
                c.startAnimation(865);
            break;
            case 166:
                c.startAnimation(866);
            break;
            case 52050:
                c.startAnimation(2105);
            break;
            case 52051:
                c.startAnimation(2106);
            break;
            case 52052:
                c.startAnimation(2107);
            break;
            case 52053:
                c.startAnimation(2108);
            break;
            case 52054:
                c.startAnimation(2109);
            break;
            case 52055:
                c.startAnimation(2110);
            break;
            case 52056:
                c.startAnimation(2111);
            break;
            case 52057:
                c.startAnimation(2112);
            break;
            case 52058:
                c.startAnimation(2113);
            break;
            case 43092:
                c.startAnimation(0x558);
            break;
            case 2155:
                c.startAnimation(0x46B);
            break;
            case 25103:
                c.startAnimation(0x46A);
            break;
            case 25106:
                c.startAnimation(0x469);
            break;
            case 2154:
                c.startAnimation(0x468);
            break;
            case 52071:
                c.startAnimation(0x84F);
            break;
            case 52072:
                c.startAnimation(0x850);
            break;
            case 59062:
                c.startAnimation(2836);
            break;
            case 72032:
                c.startAnimation(3544);
            break;
            case 72033:
                c.startAnimation(3543);
            break;
            case 72254:
                c.startAnimation(3866);
            break;
			/* END OF EMOTES */
			
			case 24017:
				c.getPA().resetAutocast();
				//c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
				c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
				//c.setSidebarInterface(0, 328);
				//c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}

}
