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
#   superraredrops = [<SUPER RARE DROPS>]
#	dropItems(player, npc, server, commondrops, uncommondrops, raredrops, veryraredrops, superraredrops)

#^remember to separate the drops with commas (",")

def npcDrop_181(player, npc, server):
	commondrops = [1150]
	uncommondrops = [4586, 4088]
	raredrops = [1188]
	veryraredrops = [3141]
	superraredrops = []
	dropItems(player, npc, server, commondrops, uncommondrops, raredrops, veryraredrops, superraredrops)
	

def rarityCalculator():
	roll = random.randrange(10)
	if roll == 1:
		roll = random.randrange(10)
		if roll == 1:
			roll = random.randrange(10)
			if roll == 1:
				roll = random.randrange(10)
				if roll == 1:
					roll = random.randrange(10)
					if roll == 1:
						return "SUPER RARE"
					else:
						return "VERY RARE"
				else:
					return "RARE"
			else:
				return "UNCOMMON"
		else:
			return "COMMON"
	else:
		return -1
		
#randomly selects drop given the rarity previously determined

def determineDrop(rarity, commondrops, uncommondrops, raredrops, veryraredrops, superraredrops):
	if rarity == "SUPER RARE":
		if len(veryraredrops) != 0:
			drop = superraredrops[random.randrange(len(veryraredrops))]
		else:
			rarity == "VERY RARE"
	if rarity == "VERY RARE":
		if len(veryraredrops) != 0:
			drop = veryraredrops[random.randrange(len(veryraredrops))]
		else:
			rarity == "RARE"
	if rarity == "RARE":
		if len(raredrops) != 0:
			drop = raredrops[random.randrange(len(raredrops))]
		else:
			rarity == "UNCOMMON"
	if rarity == "UNCOMMON":
		if len(uncommondrops) != 0:
			drop = uncommondrops[random.randrange(len(uncommondrops))]
		else:
			rarity == "COMMON"
	if rarity == "COMMON":
		if len(commondrops) != 0:
			drop = commondrops[random.randrange(len(commondrops))]
		else:
			return -1
	return drop
	
#randomly selects rare drop table drop

def determineRareDrop():
	commonrdt = [1623, 1621, 1619, 1452]
	uncommonrdt = [1617, 1462, 987, 985, 830]
	rarerdt = [995, 1247, 1319, 1373, 2366, 1149, 563, 560, 561, 566, 565, 892, 443]
	veryrarerdt = [1201, 1249, 2366]
	superrarerdt = []
	rarity = rareDropRarity()
	if rarity == "SUPER RARE":
		drop = superrarerdt[random.randrange(len(superrarerdt))]
	if rarity == "VERY RARE":
		drop = veryrarerdt[random.randrange(len(veryrarerdt))]
	if rarity == "RARE":
		drop = rarerdt[random.randrange(len(rarerdt))]
	if rarity == "UNCOMMON":
		drop = uncommonrdt[random.randrange(len(uncommonrdt))]
	if rarity == "COMMON":
		drop = commonrdt[random.randrange(len(commonrdt))]
	else
		drop = -1
	return drop
	
#determines rarity on rare drop table
	
def rareDropRarity()
	roll = random.randrange(10000)
	if roll == 0:
		return "SUPER RARE"
	elif roll > 0 && roll < 6:
		return "VERY RARE"
	elif roll > 5 && roll < 16:
		return "RARE"
	elif roll > 15 && roll < 36:
		return"UNCOMMON"
	elif roll < 50:
		return "COMMON"
	else
		return -1

#main dropping function
	
def dropItems(player, npc, server, commondrops, uncommondrops, raredrops, veryraredrops, superraredrops):
	NPCAlwaysDrops(player, npc, server)
	rarity = rarityCalculator()
	drop = determineDrop(rarity, commondrops, uncommondrops, raredrops, veryraredrops, superraredrops)
	if isNPCOnRDT(npc.npcType) == 1:
		drop2 = determineRareDrop()
	else:
		drop2 = -1
	server.itemHandler.createGroundItem(player, drop, npc.absX, npc.absY, getAmount(drop, npc.npcType), player.playerId)
	server.itemHandler.createGroundItem(player, drop2, npc.absX, npc.absY, getAmountRDT(drop2, npc.npcType), player.playerId)
	
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
	return 1













































	