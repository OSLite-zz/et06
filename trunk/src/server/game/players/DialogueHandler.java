package server.game.players;

import core.util.*;

public class DialogueHandler {

	private Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	public void sendDialogues(int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch(dialogue) {
		case 9001:
			sendOption2("Teleport me to a random place!", "Leave me here.");
			c.dialogueAction = 9001;
			break;
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 2:
			sendOption2("Yea! I'm fearless!",  "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat1("'Ello, and what are you after then?", c.talkingNpc, "Chaeldar");
			c.nextChat = 4;
		break;
		case 5:
			sendNpcChat4("Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ", 
						"to earn points towards receiving magic related prizes?", c.talkingNpc, "Kolodion");
			c.nextChat = 6;
		break;
		case 6:
			sendNpcChat4("The way the game works is as follows...", "You will be teleported to the wilderness,", 
			"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion");
			c.nextChat = 15;
		break;
		case 11:
			sendNpcChat1("'Ello, and what are you after then?", c.talkingNpc, "Chaeldar");
			c.nextChat = 12;
		break;
		case 12:
			sendOption2("I need an assignment.", "Err... nothing.");
			c.dialogueAction = 5;
		break;
		case 13:
			sendNpcChat1("'Ello, and what are you after then?", c.talkingNpc, "Chaeldar");
			c.nextChat = 14;
		break;
		case 14:
			sendOption2("I need an easier assignment.", "Err... nothing.");
			c.dialogueAction = 6;
		break;
		case 15:
			sendOption2("Yes I would like to play", "No, sounds too dangerous for me.");
			c.dialogueAction = 7;
		break;
		case 16:
			sendOption2("I would like to reset my barrows count.", "I would like to fix all my barrows");
			c.dialogueAction = 8;
		break;
		case 17:
			sendOption5("Air altar", "Mind altar", "Water altar", "Earth altar", "More");
			c.dialogueAction = 10;
			c.dialogueId = 17;
			c.teleAction = -1;
		break;
		case 18:
			sendOption5("Fire altar", "Body altar", "Cosmic altar", "Astral altar", "More");
			c.dialogueAction = 11;
			c.dialogueId = 18;
			c.teleAction = -1;
		break;
		case 19:
			sendOption5("Nature altar", "Law altar", "Death altar", "Blood altar", "More");
			c.dialogueAction = 12;
			c.dialogueId = 19;
			c.teleAction = -1;
		break;
		
		case 57:
			c.getPA().sendFrame126("Teleport to shops?", 2460);
			c.getPA().sendFrame126("Yes.", 2461);
			c.getPA().sendFrame126("No.", 2462);
			c.getPA().sendFrame164(2459);
			c.dialogueAction = 27;
		break;
		case 58:
			sendNpcChat1("Where would you like me to take you?", c.talkingNpc, "Sailor");
			c.nextChat = 59;
		break;
		case 59:
			sendOption3("Port Phasmatys", "Karamja", "Brimhaven");
			c.dialogueAction = 13;
			c.dialogueId = 59;
			c.teleAction = -1;
			break;
		case 60:
			sendNpcChat1("Welcome to the church of Entrana, my brother.", c.talkingNpc, "Brother Jered");
			c.nextChat = 61;
			break;
		case 61:
			sendPlayerChat1("Thanks, brother Jered.");
			c.nextChat = 0;
			break;
		case 62:
			sendStatement("This is your first time playing, welcome to ET06!");
			c.nextChat = 0;
			break;
		case 63:
			sendNpcChat1("Would you like to change your appearance?", c.talkingNpc, "Make-over mage");
			c.nextChat = 64;
			break;
		case 64:
			sendOption2("Yes please.", "No thanks.");
			c.dialogueAction = 14;
			c.dialogueId = 64;
			break;
		case 65:
			sendPlayerChat1("No thanks.");
			c.nextChat = 0;
			break;
		case 66:
			sendNpcChat1("Do you want to take a look at my fishing supplies?", c.talkingNpc, "Master fisher");
			c.nextChat = 67;
			break;
		case 67:
			sendOption2("Yes.", "No.");
			c.dialogueAction = 15;
			c.dialogueId = 67;
			break;
		case 68:
			sendOption3("Duel Arena", "Barrows", "The TzHaar City");
			c.dialogueAction = 16;
			c.dialogueId = 68;
			break;
		case 69:
			sendNpcChat2("You even defeated TzTok-Jad, I am most impressed!", "Please accept this gift as a reward.", c.talkingNpc, "Tzhaar-Mej-Tal");
			c.nextChat = -1;
			break;
		case 70:
			sendNpcChat2("Hey, do you want me to bring you into the icy cavern?", "I can't help you in there though, it's too dangerous.", c.talkingNpc, "Wizard Mizgog");
			c.nextChat = 71;
			break;
		case 71:
			sendStatement4("@red@WARNING!", "This cavern contains very dangerous monsters.", "You can only escape by teleporting.", "Do you really want to enter?");
			c.nextChat = 72;
			break;
		case 72:
			sendOption2("Yes, follow Mizgog into the cavern.", "No.");
			c.dialogueAction = 17;
			c.dialogueId = 69;
			break;
		case 73:
			sendNpcChat1("Hello " + Misc.capitalize(c.playerName) + ", take a look at my herblore supplies!", c.talkingNpc, "Kaqemeex");
			c.nextChat = 74;
			break;
		case 74:
			c.getShops().openShop(12);
			break;
		case 75:
			sendNpcChat2("Hello sir, I am specialized in tribal weaponry. Do you", "want to take a look at my shop?", c.talkingNpc, "Tribal Weapon Salesman");
			c.nextChat = 76;
			break;
		case 76:
			sendOption2("Sure, why not?", "Not right now.");
			c.dialogueAction = 18;
			c.dialogueId = 76;
			break;
		case 77:
			sendNpcChat1("Hello, do you want to take a look at my farming shop?", c.talkingNpc, "Farmer Brumty");
			c.nextChat = 78;
			break;
		case 78:
			sendOption2("Yes, I'm in need of farming supplies.", "No thanks.");
			c.dialogueAction = 19;
			c.dialogueId = 78;
			break;
		case 79:
			sendStatement2("The ship will take you to the river troll's island.", "Are you sure you want to go?");
			c.nextChat = 80;
			break;
		case 80:
			sendOption2("Yes!", "No.");
			c.dialogueAction = 20;
			c.dialogueId = 80;
			break;
		case 81:
			sendNpcChat1("Do you want me to teleport you to the slayer tower?", c.talkingNpc, "Old Man");
			c.nextChat = 82;
			break;
		case 82:
			sendOption2("Yes.", "No.");
			c.dialogueAction = 21;
			c.dialogueId = 82;
			break;
		case 83:
			sendOption2("Teleport to rock crabs.", "Nevermind.");
			c.dialogueAction = 22;
			c.dialogueId = 83;
			break;
		case 84:
			sendNpcChat2("Hey there! I've gained a lot of gear lately. Do you", "want to take a look?", c.talkingNpc, "Bandit shopkeeper");
			c.nextChat = 85;
			break;
		case 85:
			sendOption2("Sure.", "No thanks.");
			c.dialogueAction = 23;
			c.dialogueId = 85;
			break;
		case 86:
			sendNpcChat1("Do you want to take a look at my pickaxes?", c.talkingNpc, "Nulodion");
			c.nextChat = 87;
			break;
		case 87:
			sendOption2("Yes.", "No thanks.");
			c.dialogueAction = 24;
			c.dialogueId = 87;
			break;
		case 88:
			sendNpcChat1("Do you want to go to the gnome agility course?", c.talkingNpc, "Gnome");
			c.nextChat = 89;
			break;
		case 89:
			sendOption2("Yes.", "No thanks.");
			c.dialogueAction = 25;
			c.dialogueId = 89;
			break;
			
			/**
			* Cook's Assistant
			*/
		case 100:
			sendNpcChat1("Oh dear, what am I going to do?", c.talkingNpc, "Cook");
			c.nextChat = 101;
			break;
		case 101:
			sendPlayerChat1("What's wrong?");
			c.nextChat = 102;
			break;
		case 102:
			sendNpcChat2("It's the king's birthday and I'm supposed to make a cake.", "But I have forgotten to get the ingredients.", c.talkingNpc, "Cook");
			c.nextChat = 103;
			break;
		case 103:
			sendNpcChat2("The king is going to kill me if I don't make him a cake!", "Can you please help me get the ingredients?", c.talkingNpc, "Cook");
			c.nextChat = 104;
			break;
		case 104:
			sendOption2("Yes, I'll help you.", "No, I don't have time, sorry.");
			c.dialogueAction = 100;
			break;
		case 105: //accept quest
			c.cooksA += 1;
			c.getPA().loadQuests();
			sendNpcChat1("Thank you so much, "+ Misc.capitalize(c.playerName) +"!", c.talkingNpc, "Cook");
			c.nextChat = 106;
			break;
		case 106: 
			sendNpcChat1("Please get me milk, flour and an egg.", c.talkingNpc, "Cook");
			c.nextChat = 0;
			break;
		case 107://refuse quest
			sendPlayerChat1("I don't have time, sorry.");
			c.nextChat = 0;
			break;
		case 108:
			sendNpcChat1("How are you getting on with the ingredients?", c.talkingNpc, "Cook");
			c.nextChat = 109;
			break;
		case 109:
			if (c.getItems().playerHasItem(1927) && c.getItems().playerHasItem(1944) && c.getItems().playerHasItem(1933)) {
				c.getItems().deleteItem(1927, 1);
				c.getItems().deleteItem(1944, 1);
				c.getItems().deleteItem(1933, 1);
				c.getDH().sendPlayerChat1("Here are the ingredients.");
				c.cooksA += 1;
				c.getPA().loadQuests();
				c.nextChat = 110;
			} else {
				c.getDH().sendPlayerChat1("I'm still working on it.");
				c.nextChat = 0;
			}
			break;
		case 110:
			sendNpcChat2("Thank you so much for the help!", "I will reward you for this!", c.talkingNpc, "Cook");
			c.cooksA += 1;
			c.questPoints += 3;
			c.getPA().loadQuests();
			c.nextChat = 0;
			break;
		case 111:
			break;
		case 112:
			sendNpcChat1("It's such a beautiful day!", c.talkingNpc, "Cook");
			c.nextChat = 0;
			break;
			
		case 150:
			sendItemChat1("Hello", "Test", 4151, 10);
			c.nextChat = 0;
			break;
	/*
	 * Banker dialogues
	 */
		case 1000:
		sendNpcChat1("Hello, how may I help you?", c.talkingNpc, "Banker");
		c.nextChat = 1001;
		break;
		case 1001:
		sendPlayerChat1("I would like to access my bank account, please.");
		c.nextChat = 1002;
		break;
		case 1002:
		sendNpcChat1("Of course.", c.talkingNpc, "Banker");
		c.nextChat = 1003;
		break;
		case 1003:
		c.sendMessage("The banker opens up your bank account.");
		c.getPA().openUpBank();
		c.nextChat = 0;
		break;
		
	/*
	 * Zaff dialogues
	 */
		case 1004:
		sendNpcChat1("Hello "+Misc.capitalize(c.playerName)+"!", c.talkingNpc, "Zaff");
		c.nextChat = 1005;
		break;
		case 1005:
		sendPlayerChat1("Hello Zaff.");
		c.nextChat = 1006;
		break;
		case 1006:
		sendNpcChat1("So how are you today?", c.talkingNpc, "Zaff");
		c.nextChat = 1007;
		break;
		case 1007:
		sendPlayerChat1("I'm fine!");
		c.nextChat = 0;
		break;
		
	/*
	 * Thessalia dialogues
	 */
		case 1008:
		sendNpcChat1("Hi...", c.talkingNpc, "Thessalia");
		c.nextChat = 1009;
		break;
		case 1009:
		sendPlayerChat1("Hello Thessalia, how are you?");
		c.nextChat = 1010;
		break;
		case 1010:
		sendNpcChat1("I guess not so good...", c.talkingNpc, "Thessalia");
		c.nextChat = 1011;
		break;
		case 1011:
		sendPlayerChat1("What's wrong?");
		c.nextChat = 1012;
		break;
		case 1012:
		sendNpcChat2("None of your business! Do you want to buy any", "clothes or not?!", c.talkingNpc, "Thessalia");
		c.nextChat = 1013;
		break;
		case 1013:
		sendPlayerChat1("Err... Maybe later...");
		//c.sendMessage("Thessalia starts crying... What was that all about?");
		c.nextChat = 0;
		break;

	/*
	 * Random.
	 */
	 	case 996:
		sendNpcChat1("I don't want to talk to people unexperienced with magic!", c.talkingNpc, "Aubury");
		c.nextChat = 0;
		break;
	 	case 997:
		sendNpcChat1("Take a look in the chest over there.", c.talkingNpc, "Tramp");
		c.nextChat = 0;
		break;
	 	case 999:
		sendNpcChat1("Hello!", c.talkingNpc, "Shop Keeper");
		c.nextChat = 0;
		break;
	 	case 998:
		sendNpcChat1("You have no business here!", c.talkingNpc, "Guard");
		c.nextChat = 0;
		break;
	 	case 2000:
	 		sendNpcChat2("Did you know that I completely forgot", "to code this dialogue?", c.talkingNpc, "");
	 		c.nextChat = 2001;
	 	break;
	 	case 2001:
	 		sendPlayerChat1("Umm... are you feeling okay?");
	 		c.nextChat = 0;
	 		break;
		}
	}
	
	

	
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Item chat
	 */
	
	public void sendItemChat1(String header, String one, int item, int zoom) {
		c.getPA().sendFrame246(4883, zoom, item);
		c.getPA().sendFrame126(header, 4884);
		c.getPA().sendFrame126(one, 4885);
		c.getPA().sendFrame164(4882);
	}

	public void sendItemChat2(String header, String one, String two, int item, int zoom) {
		c.getPA().sendFrame246(4888, zoom, item);
		c.getPA().sendFrame126(header, 4889);
		c.getPA().sendFrame126(one, 4890);
		c.getPA().sendFrame126(two, 4891);
		c.getPA().sendFrame164(4887);
	}

	public void sendItemChat3(String header, String one, String two, String three, int item, int zoom) {
		c.getPA().sendFrame246(4894, zoom, item);
		c.getPA().sendFrame126(header, 4895);
		c.getPA().sendFrame126(one, 4896);
		c.getPA().sendFrame126(two, 4897);
		c.getPA().sendFrame126(three, 4898);
		c.getPA().sendFrame164(4893);
	}

	public void sendItemChat4(String header, String one, String two, String three, String four, int item, int zoom) {
		c.getPA().sendFrame246(4901, zoom, item);
		c.getPA().sendFrame126(header, 4902);
		c.getPA().sendFrame126(one, 4903);
		c.getPA().sendFrame126(two, 4904);
		c.getPA().sendFrame126(three, 4905);
		c.getPA().sendFrame126(four, 4906);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Options
	 */
	
	public void sendOption(String s) {
		c.getPA().sendFrame126("Select an Option", 2470);
	 	c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}	
	
	public void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	public void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2470);
		c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126(s2, 2473);
		c.getPA().sendFrame164(2469);
	}
	
	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}
	
	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	private void sendStatement(String s) {
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	
	private void sendStatement2(String s, String s1) {
		c.getPA().sendFrame126(s, 360);
		c.getPA().sendFrame126(s1, 361);
		c.getPA().sendFrame126("Click here to continue", 362);
		c.getPA().sendFrame164(359);
	}
	
	private void sendStatement3(String s, String s1, String s2) {
		c.getPA().sendFrame126(s, 364);
		c.getPA().sendFrame126(s1, 365);
		c.getPA().sendFrame126(s2, 366);
		c.getPA().sendFrame126("Click here to continue", 367);
		c.getPA().sendFrame164(363);
	}
	
	private void sendStatement4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126(s, 369);
		c.getPA().sendFrame126(s1, 370);
		c.getPA().sendFrame126(s2, 371);
		c.getPA().sendFrame126(s3, 372);
		c.getPA().sendFrame126("Click here to continue", 373);
		c.getPA().sendFrame164(368);
	}
	
	private void sendStatement5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126(s, 375);
		c.getPA().sendFrame126(s1, 376);
		c.getPA().sendFrame126(s2, 377);
		c.getPA().sendFrame126(s3, 378);
		c.getPA().sendFrame126(s4, 379);
		c.getPA().sendFrame126("Click here to continue", 380);
		c.getPA().sendFrame164(374);
	}
	
	/*
	 * Npc Chatting
	 */
	
	private void sendNpcChat1(String s, int ChatNpc, String name) {
		c.getPA().sendFrame200(4883, 591);
		c.getPA().sendFrame126(name, 4884);
		c.getPA().sendFrame126(s, 4885);
		c.getPA().sendFrame75(ChatNpc, 4883);
		c.getPA().sendFrame164(4882);
	}
	
	private void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 591);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}

	private void sendNpcChat3(String s, String s1, String s2, int ChatNpc, String name) {
		c.getPA().sendFrame200(4894, 591);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}
	
	private void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 591);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Player Chating Back
	 */
	
	private void sendPlayerChat1(String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(Misc.capitalize(c.playerName), 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	private void sendPlayerChat2(String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(Misc.capitalize(c.playerName), 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}
	
	private void sendPlayerChat3(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 591);
		c.getPA().sendFrame126(Misc.capitalize(c.playerName), 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}
	
	private void sendPlayerChat4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(987, 591);
		c.getPA().sendFrame126(Misc.capitalize(c.playerName), 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}
}
