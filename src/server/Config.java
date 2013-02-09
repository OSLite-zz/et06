package server;

import core.util.Misc;


public class Config {

	public static final boolean SERVER_DEBUG = false;
    public static final boolean WOODCUTTING_DEBUG = false;
	public static final boolean CLICK_OBJECT_DEBUG = true;
	
	public static final String SERVER_NAME = "ET06";
	public static final String WELCOME_MESSAGE = "Welcome to ET06!";
	public static final String FORUMS = "Unavailable";
	
	public static final int CLIENT_VERSION = 1;
	
	public static int MESSAGE_DELAY = 6000;
	public static final int ITEM_LIMIT = 16000;
	public static final int MAXITEM_AMOUNT = Integer.MAX_VALUE;
	public static final int BANK_SIZE = 352;//standard is 352
	public static final int MAX_PLAYERS = 100;
	
	public static final int CONNECTION_DELAY = 100;
	public static final int IPS_ALLOWED = 3;
		
	public static final boolean WORLD_LIST_FIX = false;
	
	public static final int[] ITEM_SELLABLE 		=	{3842,3844,3840,8844,8845,8846,8847,8848,8849,8850,10551,6570,7462,7461,7460,7459,7458,7457,7456,7455,7454,8839,8840,8842,11663,11664,11665,10499,
														9748,9754,9751,9769,9757,9760,9763,9802,9808,9784,9799,9805,9781,9796,9793,9775,9772,9778,9787,9811,9766,
														9749,9755,9752,9770,9758,9761,9764,9803,9809,9785,9800,9806,9782,9797,9794,9776,9773,9779,9788,9812,9767,
														9747,9753,9750,9768,9756,9759,9762,9801,9807,9783,9798,9804,9780,9795,9792,9774,9771,9777,9786,9810,9765,995};
	public static final int[] ITEM_TRADEABLE 		= 	{3842,3844,3840,8844,8845,8846,8847,8848,8849,8850,10551,6570,7462,7461,7460,7459,7458,7457,7456,7455,7454,8839,8840,8842,11663,11664,11665,10499,
														9748,9754,9751,9769,9757,9760,9763,9802,9808,9784,9799,9805,9781,9796,9793,9775,9772,9778,9787,9811,9766,
														9749,9755,9752,9770,9758,9761,9764,9803,9809,9785,9800,9806,9782,9797,9794,9776,9773,9779,9788,9812,9767,
														9747,9753,9750,9768,9756,9759,9762,9801,9807,9783,9798,9804,9780,9795,9792,9774,9771,9777,9786,9810,9765};
	public static final int[] UNDROPPABLE_ITEMS 	= 	{};
	
	public static final int[] FUN_WEAPONS	=	{2460,2461,2462,2463,2464,2465,2466,2467,2468,2469,2470,2471,2471,2473,2474,2475,2476,2477};
	
	public static final boolean ADMIN_CAN_TRADE = true;
	public static final boolean ADMIN_CAN_SELL_ITEMS = true;
	public static final boolean ADMIN_DROP_ITEMS = true;
	
	public static final int START_LOCATION_X = 3222;
	public static final int START_LOCATION_Y = 3218;
	public static final int RESPAWN_X = 3222 + Misc.random(2);
	public static final int RESPAWN_Y = 3218 + Misc.random(2);
	public static final int DUELING_RESPAWN_X = 3362;
	public static final int DUELING_RESPAWN_Y = 3263;
	public static final int RANDOM_DUELING_RESPAWN = 5;
	
	public static final int NO_TELEPORT_WILD_LEVEL = 20;
	public static final int SKULL_TIMER = 1200;
	public static final int TELEBLOCK_DELAY = 20000;
	public static final boolean SINGLE_AND_MULTI_ZONES = true;
	public static final boolean COMBAT_LEVEL_DIFFERENCE = true;
	
	public static final boolean itemRequirements = true;
	
	public static final int INCREASE_SPECIAL_AMOUNT = 14500;
	public static final boolean PRAYER_POINTS_REQUIRED = true;
	public static final boolean PRAYER_LEVEL_REQUIRED = true;
	public static final boolean MAGIC_LEVEL_REQUIRED = true;
	public static final int GOD_SPELL_CHARGE = 300000;
	public static final boolean RUNES_REQUIRED = true;
	public static final boolean CORRECT_ARROWS = true;
	public static final boolean CRYSTAL_BOW_DEGRADES = true;
	
	public static final int SAVE_TIMER = 10;
	public static final int NPC_RANDOM_WALK_DISTANCE = 4;
	public static final int NPC_FOLLOW_DISTANCE = 10;												
	public static final int[] UNDEAD_NPCS = {90,91,92,93,94,103,104,73,74,75,76,77};
	public static final int[][] NPC_DROPS = {
	
	/*	NPC/ID/AMOUNT/POSSIBILITY */
			// Men
			{1,526,1,0}, {2,526,1,0}, {3,526,1,0},
			// skeletons
			{92, 526, 1, 0},
			// Al-Kharid warrior
			{18, 526, 1, 0}, {18, 995, 2000 + Misc.random(10000), 5}, 
			// Mugger
			{175,526,1,0},
			// Guards
			{9,526,1,0},
			// Skeletal Wyverns
			{3068, 4832, 1, 0}, {3068, 4087, 1, 40}, {3068, 3140, 1, 40}, {3068, 1187, 1, 40},
			// Ice Queen
			{795, 6585, 1, 50}, {795, 4832, 1, 0}, {795, 555, 200 + Misc.random(150), 10}, 
			{795, 560, 150 + Misc.random(100), 10}, {795, 565, 100 + Misc.random(100), 10},
			{795, 392, 25 + Misc.random(25), 10}, {795, 386, 30 + Misc.random(30), 10},
			{795, 6731, 1, 40}, {795, 2434, 1, 10}, {795, 2436, 1,10}, {795, 2440, 1, 10},
			{795, 2442, 1, 10},
			//Tzhaar-Xil
			{2607, 526, 1, 0}, {2607, 6529, 2500 + Misc.random(5000), 2}, {2607, 6522, 200 + Misc.random(300), 5},
			//goblin
			{101, 526, 1, 0}, {101, 995, 100 + Misc.random(2000), 10},  {101, 6199, 1, 40},
			//abyssal demon
			{1615, 4151, 1, 40}, {1615, 592, 1, 0}, {1615, 995, 50000 + Misc.random(100000),10}, {1615, 565, 200 + Misc.random(200), 12},
			{1615, 566, 200 + Misc.random(200), 12}, {1615, 560, 200 + Misc.random(200), 12}, {1615, 561, 200 + Misc.random(200), 12},
			//blue dragon
			{55, 536, 1, 0}, {55, 1751, 1, 0}, {55, 1540, 1, 20}, {55, 6199, 1, 30}, {55, 995, 10000 + Misc.random(1000), 20},
			{55, 1149, 1, 25}, {55, 1187, 1, 30}, {55, 555, 100 + Misc.random(100), 10}, {55, 554, 100 + Misc.random(100), 10},
			//green dragon
			{941, 536, 1, 0}, {941, 1753, 1, 0}, {941, 1540, 1, 15}, {941, 6199, 1, 25}, {941, 995, 10000 + Misc.random(1000),10},
			{941, 1149, 1, 20}, {941, 1187, 1, 20}, {941, 555, 100 + Misc.random(100), 10}, {941, 554, 100 + Misc.random(100), 10},
			//black dragon
			{54, 536, 1, 0}, {54, 1747, 1, 0}, {54, 1540, 1, 5}, {54, 6199, 1, 10}, {54, 995, 10000 + Misc.random(1000), 5},
			{54, 1149, 1, 10}, {54, 1187, 1, 10}, {54, 555, 100 + Misc.random(100), 5}, {54, 554, 100 + Misc.random(100), 5},
			//red dragon
			{53, 536, 1, 0}, {53, 1749, 1, 0}, {53, 1540, 1, 5}, {53, 6199, 1, 10}, {53, 995, 10000 + Misc.random(1000), 5},
			{53, 1149, 1, 10}, {53, 1187, 1, 10}, {53, 555, 100 + Misc.random(100), 5}, {53, 554, 100 + Misc.random(100), 5},
			//ice giant
			{111, 532, 1, 0}, {111, 6199, 1, 20}, {111, 995, 10000 + Misc.random(5000), 5}, {111, 1631, 1, 5},
			//ice warrior
			{125, 526, 1, 0}, {125, 6199, 1, 20}, {125, 995, 10000 + Misc.random(5000), 5}, {125, 1631, 1, 5},
			//shantay
			{836, 526, 1, 0}, {837, 526, 1, 0}, {838, 526, 1, 0},
			//scorpion
			{107, 526, 1, 0},
			//river troll
			{396, 4832, 1, 0}, {396, 2577, 1, 40}, {396, 6199, 1, 10}, {396, 1631, 1, 10}, {396, 6199, 1, 9},
			//troll
			{72, 526, 1, 0}, {72, 1631, 1, 25}, {72, 4832, 1, 4}, {72, 6199, 1, 10}, {72, 555, 100 + Misc.random(120), 5},
			//ogre
			{115, 532, 1, 0}, {115, 1079, 1, 5}, {115, 1333, 1, 5}, {115, 1127, 1, 5}, {115, 3105, 1, 5},
			//thugs and thiefs
			{186, 526, 1, 0}, {282, 526, 1, 0},
			//farmer
			{7, 526, 1, 0}, {7, 952, 1, 5}, {7, 5291, 10 + Misc.random(10), 3},
			//wolfs
			{96, 526, 1, 0}, {96, 6199, 1, 10}, {97, 526, 1, 0}, {97, 6199, 1, 10}, {141, 526, 1, 0},
			{141, 6199, 1, 10}, {1558, 532, 1, 0}, {1558, 6199, 1, 5},
			//Hill giant
			{117, 532, 1, 0}, {117, 1333, 1, 5}, {117, 6199, 1, 10}, {117, 995, 5000 + Misc.random(10000), 5},
			//chaos druid
			{181, 249, 1, 3}, {181, 526, 1, 0}, {181, 251, 1, 3}, {181, 253, 1, 3}, {181, 255, 1, 3}, 
			{181, 257, 1, 3}, {181, 259, 1, 3}, {181, 261, 1, 3}, {181, 263, 1, 3}, {181, 267, 1, 3}, 
			{181, 265, 1, 3}, {181, 5291, 3 + Misc.random(10), 3}, {181, 5292, 3 + Misc.random(10), 3}, 
			{181, 5293, 3 + Misc.random(10), 3}, {181, 5294, 3 + Misc.random(10), 3}, {181, 5295, 3 + Misc.random(10), 3}, 
			{181, 5296, 3 + Misc.random(10), 3}, {181, 5297, 3 + Misc.random(10), 3}, {181, 5298, 3 + Misc.random(10),3},
			{181, 5299, 3 + Misc.random(10), 3}, {181, 5300, 3 + Misc.random(10), 3}, {181, 5301, 3 + Misc.random(10), 3}, 
			{181, 5302, 3 + Misc.random(10), 3}, {181, 5303, 3 + Misc.random(10), 3}, 
			//zombie
			{73, 526, 1, 0}, {73, 1351, 1, 3},
			//hobgoblin
			{122, 526, 1, 0},
			//rock crab
			{1265, 526, 1, 0}, {1265, 1631, 1, 10}, {1265, 6199, 1, 10}, 
			//lesser demon
			{82, 592, 1, 0}, {82, 1333, 1, 10}, {82, 6199, 1, 20},
			//red spider
			{63, 592, 1, 0}, {63, 6199, 1, 20},
			//ghost
			{103, 526, 1, 0}, {103, 6199, 1, 30},
			//rat
			{86, 526, 1, 0},
			//crawling hands
			{1648, 526, 1, 0}, {1648, 995, 2000 + Misc.random(2000), 5},
			//banshee
			{1612, 526, 1, 0}, {1612, 995, 5000 + Misc.random(2000), 5}, {1612, 6199, 1, 30},
			//infernal mage
			{1643, 526, 1, 0}, {1643, 554, 245, 5}, {1643, 555, 285, 5}, {1643, 556, 305, 5},
			{1643, 557, 125, 5}, {1643, 560, 250, 10}, {1643, 565, 200, 10}, {1643, 6199, 1, 20},
			{1643, 4675, 1, 10},
			//bloodveld
			{1618, 526, 1, 0}, {1618, 6199, 1, 20}, {1618, 995, 10000 + Misc.random(10000), 10},
			{1618, 1333, 1, 10},
			//dust devil
			{1624, 592, 1, 0}, {1624, 4131, 1, 10}, {1624, 1079, 1, 10}, {1624, 6199, 1, 15},
			{1624, 3140, 1, 40},
			//gargoyle
			{1611, 526, 1, 0}, {1611, 3122, 1, 20}, {1611, 4153, 1, 30}, {1611, 6199, 1, 20},
			//dark beasts
			{2783, 526, 1, 0}, {2783, 1516, 250 + Misc.random(50), 20}, {2783, 561, 400 + Misc.random(500), 20},
			{2783, 565, 400 + Misc.random(500), 20}, {2783, 560, 400 + Misc.random(500), 20},
			{2783, 452, 50 + Misc.random(50), 25}, {2783, 2364, 50 + Misc.random(50), 25},
			//babybluedrag
			{52, 534, 1, 0},
			//hellhound
			{49, 526, 1, 0}, {49, 6199, 1, 14},
			//Monkey Guard
			{1459, 526, 1, 0}, {1459, 6199, 1, 5},
			
			
	};

	/**
	 * Glory
	 */
	public static final int EDGEVILLE_X = 3087;
	public static final int EDGEVILLE_Y = 3500;
	public static final String EDGEVILLE = "";
	public static final int AL_KHARID_X = 3293;
	public static final int AL_KHARID_Y = 3174;
	public static final String AL_KHARID = "";
	public static final int KARAMJA_X = 3087;
	public static final int KARAMJA_Y = 3500;
	public static final String KARAMJA = "";
	public static final int MAGEBANK_X = 2538;
	public static final int MAGEBANK_Y = 4716;
	public static final String MAGEBANK = "";
	
	/**
	* Teleport Spells
	**/
    
    
    public static final int TELEDELAY = 4500;
    public static final boolean FREE_TELE = false;//not actually implemented yet
    
    
	// modern
	public static final int VARROCK_X = 3212;
	public static final int VARROCK_Y = 3424;
	public static final String VARROCK = "";
	public static final int LUMBY_X = 3222;
	public static final int LUMBY_Y = 3218;
	public static final String LUMBY = "";
    public static final int FALADOR_X = 2964;
	public static final int FALADOR_Y = 3378;
	public static final String FALADOR = "";
	public static final int CAMELOT_X = 2757;
	public static final int CAMELOT_Y = 3477;
	public static final String CAMELOT = "";
	public static final int CATHERBY_X = 2804;
	public static final int CATHERBY_Y = 3433;
	public static final String CATHERBY = "";
	public static final int ARDOUGNE_X = 2662;
	public static final int ARDOUGNE_Y = 3305;
	public static final String ARDOUGNE = "";
	public static final int WATCHTOWER_X = 2549;
	public static final int WATCHTOWER_Y = 3113;
	public static final String WATCHTOWER = "";
	public static final int TROLLHEIM_X = 2801;
	public static final int TROLLHEIM_Y = 3670;
	public static final String TROLLHEIM = "";
	public static final int APE_ATOLL_X = 2762;
	public static final int APE_ATOLL_Y = 2784;
	public static final String APE_ATOLL = "";
	
	// ancient
	
	public static final int PADDEWWA_X = 3098;
	public static final int PADDEWWA_Y = 9884;
	
	public static final int SENNTISTEN_X = 3322;
	public static final int SENNTISTEN_Y = 3336;

    public static final int KHARYRLL_X = 3492;
	public static final int KHARYRLL_Y = 3471;

	public static final int LASSAR_X = 3006;
	public static final int LASSAR_Y = 3471;
	
	public static final int DAREEYAK_X = 3161;
	public static final int DAREEYAK_Y = 3671;
	
	public static final int CARRALLANGAR_X = 3156;
	public static final int CARRALLANGAR_Y = 3666;
	
	public static final int ANNAKARL_X = 3288;
	public static final int ANNAKARL_Y = 3886;
	
	public static final int GHORROCK_X = 2977;
	public static final int GHORROCK_Y = 3873;
 
	public static final int TIMEOUT = 20;
	public static final int CYCLE_TIME = 600;
	public static final int BUFFER_SIZE = 10000;
	
	/**
	 * Slayer Variables
	 */
	public static final int[][] SLAYER_TASKS = {{1,87,90,4,5}, 
												{6,7,8,9,10},
												{11,12,13,14,15},
												{1,1,15,20,25},
												{30,35,40,45,50},
												{60,75,80,85,90}};
	
	/**
	* Skill Experience Multipliers
	*/	
	public static final int WOODCUTTING_EXPERIENCE = 1;
	public static final int MINING_EXPERIENCE = 1;
	public static final int SMITHING_EXPERIENCE = 1;
	public static final int FARMING_EXPERIENCE = 1;
	public static final int FIREMAKING_EXPERIENCE = 1;
	public static final int HERBLORE_EXPERIENCE = 1;
	public static final int FISHING_EXPERIENCE = 1;
	public static final int AGILITY_EXPERIENCE = 1;
	public static final int PRAYER_EXPERIENCE = 1;
	public static final int RUNECRAFTING_EXPERIENCE = 1;
	public static final int CRAFTING_EXPERIENCE = 1;
	public static final int THIEVING_EXPERIENCE = 1;
	public static final int SLAYER_EXPERIENCE = 1;
	public static final int COOKING_EXPERIENCE = 1;
	public static final int FLETCHING_EXPERIENCE = 1;	
	public static final int MELEE_EXP_RATE = 1;
	public static final int ATTACK_EXP_RATE = 1;
	public static final int DEFENCE_EXP_RATE = 1;
	public static final int STRENGTH_EXP_RATE = 1;
	public static final int RANGE_EXP_RATE = 1;
	public static final int MAGIC_EXP_RATE = 1;
	public static final int HITPOINTS_EXP_RATE = 1;
	public static final int SERVER_EXP_BONUS = 100;
}
