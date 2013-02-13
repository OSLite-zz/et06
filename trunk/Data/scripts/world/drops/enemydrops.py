#Script to handle enemy drops including rare drop table
#by Xerxes

import random

#calculates the rarity of the drop

#FORMAT FOR ADDING NEW NPC:

#def npcDrop_<NPCID>(player, npc, server):
#	commondrops = [<COMMON DROPS>]
#	uncommondrops = [<UNCOMMON DROPS>]
#	raredrops = [<RARE DROPS>]
#	veryraredrops = [<VERY RARE DROPS>]
#	dropItems(player, npc, server, commondrops, uncommondrops, raredrops, veryraredrops)

#^remember to separate the drops with commas (",")

def npcDrop_181(player, npc, server):
	commondrops = [1150]
	uncommondrops = [4586, 4088]
	raredrops = [1188]
	veryraredrops = [3141]
	dropItems(player, npc, server, commondrops, uncommondrops, raredrops, veryraredrops)
	

def rarityCalculator():
	roll = random.randrange(1000)
	if roll < 750:
		return "COMMON"
	elif roll >= 750 and roll < 910:
		return "UNCOMMON"
	elif roll >= 910 and roll < 999:
		return "RARE"
	elif roll == 999:
		return "VERY RARE"

#calculates rarity for rare drop table

def rareDropTableRarity(rarity):
	roll = random.randrange(100)
	if rarity == "COMMON":
		if roll < 80:
			return "COMMON"
		elif roll >= 80 and roll < 92:
			return "UNCOMMON"
		elif roll >= 92 and roll < 97:
			return "RARE"
		elif roll == 98 or roll == 99:
			return "VERY RARE"
	elif rarity == "UNCOMMON":
		if roll < 50:
			return "COMMON"
		elif roll >= 50 and roll < 85:
			return "UNCOMMON"
		elif roll >= 85 and roll < 95:
			return "RARE"
		elif roll >= 95:
			return "VERY RARE"
	elif rarity == "RARE":
		if roll < 40:
			return "COMMON"
		elif roll >= 40 and roll < 75:
			return "UNCOMMON"
		elif roll >= 75 and roll < 90:
			return "RARE"
		elif roll >= 90:
			return "VERY RARE"
	elif rarity == "VERY RARE":
		if roll < 30:
			return "COMMON"
		elif roll >= 30 and roll < 65:
			return "UNCOMMON"
		elif roll >= 65 and roll < 85:
			return "RARE"
		elif roll >= 85:
			return "VERY RARE"
	else:
		return "COMMON"
		
#randomly selects drop given the rarity previously determined

def determineDrop(rarity, commondrops, uncommondrops, raredrops, veryraredrops):
	if rarity == "VERY RARE":
		if len(veryraredrops) != 0:
			drop = veryraredrops[random.randrange(len(veryraredrops))]
		else:
			rarity == "RARE"
	elif rarity == "RARE":
		if len(raredrops) != 0:
			drop = raredrops[random.randrange(len(raredrops))]
		else:
			rarity == "UNCOMMON"
	elif rarity == "UNCOMMON":
		if len(uncommondrops) != 0:
			drop = uncommondrops[random.randrange(len(uncommondrops))]
		else:
			rarity == "COMMON"
	elif rarity == "COMMON":
		if len(commondrops) != 0:
			drop = commondrops[random.randrange(len(commondrops))]
		else:
			return -1
	else:
		return -1
	return drop
	
#randomly selects rare drop table drop

def determineRareDrop(rarity):
	commonrdt = [1623, 1621, 1619, 1452]
	uncommonrdt = [1617, 1462, 987, 985, 830]
	rarerdt = [995, 1247, 1319, 1373, 2366, 1149, 563, 560, 561, 566, 565, 892, 443]
	veryrarerdt = [1201, 1249]
	if rarity == "VERY RARE":
		drop = veryrarerdt[random.randrange(len(veryrarerdt))]
	if rarity == "RARE":
		drop = rarerdt[random.randrange(len(rarerdt))]
	if rarity == "UNCOMMON":
		drop = uncommonrdt[random.randrange(len(uncommonrdt))]
	if rarity == "COMMON":
		drop = commonrdt[random.randrange(len(commonrdt))]
	return drop
	
#main dropping function
	
def dropItems(player, npc, server, commondrops, uncommondrops, raredrops, veryraredrops):
	NPCAlwaysDrops(player, npc, server)
	rarity = rarityCalculator()
	drop = determineDrop(rarity, commondrops, uncommondrops, raredrops, veryraredrops)
	if isNPCOnRDT(npc.npcType) == 1:
		if random.randrange(100) == 0:
			rdt_rarity = rareDropTableRarity(rarity)
			drop2 = determineRareDrop(rdt_rarity)
		else:
			drop2 = -1
	else:
		drop2 = -1
	if random.randrange(20) == 0:
		drop3 = determineDrop("COMMON", commondrops, uncommondrops, raredrops, veryraredrops)
	else:
		drop3 = -1
	server.itemHandler.createGroundItem(player, drop, npc.absX, npc.absY, getAmount(drop, npc.npcType), player.playerId)
	server.itemHandler.createGroundItem(player, drop2, npc.absX, npc.absY, getAmountRDT(drop2, npc.npcType), player.playerId)
	server.itemHandler.createGroundItem(player, drop3, npc.absX, npc.absY, getAmount(drop3, npc.npcType), player.playerId)
	
#returns amount of items for arrows/runes/etc
	
def getAmount(drop, npcId):
	###TODO
	return 1
	
#same as above but specific to the rare drop table
	
def getAmountRDT(drop, npcID):
	###TODO
	return 1
	
#drops an npc's 100% drop
	
def NPCAlwaysDrops(player, npc, server):
	###TODO
	drop = 526
	server.itemHandler.createGroundItem(player, drop, npc.absX, npc.absY, 1, player.playerId)

#determines if monster is part of the rare drop table; 1 for yes, 0 (or anything else) for no

def isNPCOnRDT(npcId):
	###TODO
	return 1;













































	