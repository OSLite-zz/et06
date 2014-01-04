package server.game.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.game.players.Client;
import server.game.players.PacketType;
import server.game.players.PlayerHandler;
import core.util.Misc;
import core.util.ScriptManager;


public class Commands implements PacketType {
public boolean resetAnim = false;

	@Override
    public void processPacket(Client c, int packetType, int packetSize) {
        String playerCommand = c.getInStream().readString();
        if (Config.SERVER_DEBUG) {
            Misc.println(c.playerName + " playerCommand: " + playerCommand);
        }
        if (c.playerRights >= 0) {
            if (playerCommand.equalsIgnoreCase("home") || playerCommand.equalsIgnoreCase("stuck")) {
                c.getPA().movePlayer(Config.LUMBY_X, Config.LUMBY_Y, 0);
            }
        	if (playerCommand.equalsIgnoreCase("players")) {
                c.sendMessage("Current amount of players online: @red@" + PlayerHandler.getPlayerCount() + "@bla@.");
        	}
            if (playerCommand.startsWith("changepass") && playerCommand.length() > 15) {
                c.playerPass = playerCommand.substring(15);
                c.sendMessage("Your password is now: @red@" + c.playerPass);
            }
            if (playerCommand.equalsIgnoreCase("timeplayed")) {
            	c.sendMessage("Time played: @red@" + c.getPlaytime() + ".");
            }
            if (playerCommand.equalsIgnoreCase("test1")) {
            	c.getDH().sendDialogues(150, 1);
            }
            if (playerCommand.startsWith("yell")) {
    				for (int j = 0; j < Server.playerHandler.players.length; j++) {
    					if (Server.playerHandler.players[j] != null) {
    						/*if (c.playerRights == 0) {
        							Client c2 = (Client)Server.playerHandler.players[j];
            						c2.sendMessage("@dbl@" + Misc.capitalize(c.playerName) +": " + Misc.optimizeText(playerCommand.substring(5)) +"");
        						}*/
    						if (c.playerRights == 1) {
        						Client c2 = (Client)Server.playerHandler.players[j];
        						c2.sendMessage("[MOD] @dbl@" + Misc.capitalize(c.playerName) +": " + Misc.optimizeText(playerCommand.substring(5)) +"");
        					}
    						else if (c.playerRights == 2) {
    							Client c2 = (Client)Server.playerHandler.players[j];
    							c2.sendMessage("[ADMIN] @dbl@" + Misc.capitalize(c.playerName) +": " + Misc.optimizeText(playerCommand.substring(5)) +"");
    						}
    						else if (c.playerRights == 3) {
    							Client c2 = (Client)Server.playerHandler.players[j];
    							c2.sendMessage("[OWNER] @dbl@" + Misc.capitalize(c.playerName) +": " + Misc.optimizeText(playerCommand.substring(5)) +"");
    						} else {
                                c.sendMessage("You do not have permission to do that.");
                            }
    					}
    				}
            }
           if (playerCommand.startsWith("noclip") && (c.playerRights != 3)) {
    				return;			
    		}
        }
        if (c.playerRights >= 1) {
        	if (playerCommand.startsWith("mute")) {
            try {
                String playerToBan = playerCommand.substring(5);
                Connection.addNameToMuteList(playerToBan);
                for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if (Server.playerHandler.players[i] != null) {
                        if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been muted by: " + Misc.capitalize(c.playerName) + ".");
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                c.sendMessage("Player is probably offline.");
            }
            }
        }
        if (c.playerRights >= 2) {
        	if (playerCommand.startsWith("ipmute")) {
        		try {
        			String playerToBan = playerCommand.substring(7);
        			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
        				if (Server.playerHandler.players[i] != null) {
                    	if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
                            c.sendMessage("You have IP Muted the user: " + Server.playerHandler.players[i].playerName);
                            Client c2 = (Client) Server.playerHandler.players[i];
                            c2.sendMessage("You have been muted by: " + Misc.capitalize(c.playerName));
                            break;
                        }
                    }
                }
        		} catch (Exception e) {
        			c.sendMessage("Player is probably offline.");
        		}
        		if (playerCommand.startsWith("unipban")) {
            		try {
            			String playerToBan = playerCommand.substring(9);
            			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
            				if (Server.playerHandler.players[i] != null) {
            					if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
            						Connection.unIPBanUser(Server.playerHandler.players[i].connectedFrom);
            						c.sendMessage("You have un-IPbanned the user: " + Server.playerHandler.players[i].playerName);
                                break;
            					}
            				}
            			}
            		} catch (Exception e) {
            			c.sendMessage("Player is probably offline.");
            		}
            	}
        	if (playerCommand.startsWith("unipmute")) {
        		try {
        			String playerToBan = playerCommand.substring(9);
        			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
        				if (Server.playerHandler.players[i] != null) {
        					if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
        						Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
        						c.sendMessage("You have un IP-muted the user: " + Server.playerHandler.players[i].playerName);
                            break;
        					}
        				}
        			}
        		} catch (Exception e) {
        			c.sendMessage("Player is probably offline.");
        		}
        	}
        	if (playerCommand.startsWith("unmute")) {
        		try {
        			String playerToBan = playerCommand.substring(7);
        			Connection.unMuteUser(playerToBan);
        		} catch (Exception e) {
        			c.sendMessage("Player is probably offline.");
        		}
        	}
        	}
        }
        if (c.playerRights == 3) {
        	if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
				}			
			}
        	/*if (playerCommand.equals("test")) {
        	}*/
        	if (playerCommand.equals("resetanim")) {
        		c.playerWalkIndex = 0x333;
        	}
        	if (playerCommand.startsWith("ban") && playerCommand.charAt(7) == ' ') { // use as ::ban name
				try {	
					String playerToBan = playerCommand.substring(8);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player is not online.");
				}
			}
        	if (playerCommand.startsWith("kick")) {
				try {	
					String playerToKick = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToKick)) {
								Server.playerHandler.players[i].disconnected = true;
								Server.playerHandler.players[i].properLogout = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player is not online.");
				}
			}
        	if (playerCommand.startsWith("teletome")) {
				if (c.inWild())
				return;
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.teleportToX = c.absX;
								c2.teleportToY = c.absY;
								c2.heightLevel = c.heightLevel;
								c.sendMessage("You have teleported " + Misc.capitalize(c2.playerName) + " to you.");
								c2.sendMessage("You have been teleported to " + Misc.capitalize(c.playerName) + "");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player is probably offline.");
				}
			}
        	if (playerCommand.startsWith("item")) {
                try {
                    String[] args = playerCommand.split(" ");
                    if (args.length == 3) {
                        int newItemID = Integer.parseInt(args[1]);
                        int newItemAmount = Integer.parseInt(args[2]);
                        if ((newItemID <= 20000) && (newItemID >= 0)) {
                            c.getItems().addItem(newItemID, newItemAmount);
                            c.sendMessage("You spawned " + newItemAmount +" of the item " + newItemID + ".");
                            System.out.println("Spawned: " + newItemID + " by: " + Misc.capitalize(c.playerName));
                        } else {
                            c.sendMessage("Could not complete spawn request.");
                        }
                    } else if (args.length == 2) {
                        int newItemID = Integer.parseInt(args[1]);
                        if ((newItemID <= 20000) && (newItemID >= 0)) {
                            c.getItems().addItem(newItemID, 1);
                            c.sendMessage("You spawned one of the item " + newItemID + ".");
                            System.out.println("Spawned: " + newItemID + " by: " + Misc.capitalize(c.playerName));
                        } else {
                            c.sendMessage("Could not complete spawn request.");
                        }
                    } else {
                        c.sendMessage("Use as ::item 4151 1");
                    }
                } catch (Exception e) {
                }
        	}
        	if (playerCommand.startsWith("xteleto")) {
    				String name = playerCommand.substring(8);
    				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
    					if (Server.playerHandler.players[i] != null) {
    						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
    							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
    						}
    					}
    				}	
        	}
        	if (playerCommand.equalsIgnoreCase("mypos")) {
                c.sendMessage("Your position is X: " + c.absX + " Y: " +c.absY);
        	}
        	if (playerCommand.startsWith("smsg")) {
        		String[] args = playerCommand.split(" ");
        		for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("" + Misc.optimizeText(playerCommand.substring(5)));
					}
        		}
        	}
        	if (playerCommand.equalsIgnoreCase("random")) {
        		c.sendMessage("" + Misc.random(100));
        	}
        	if(playerCommand.startsWith("pnpc")) {
                	int npc = Integer.parseInt(playerCommand.substring(5));
                	if(npc < 9999){
                		c.npcId2 = npc;
                		c.isNpc = true;
                		c.updateRequired = true;
                		c.appearanceUpdateRequired = true;
                	}
        	}
        	if(playerCommand.startsWith("unpc")) {
                	c.isNpc = false;
                	c.updateRequired = true;
                	c.appearanceUpdateRequired = true;
        	}
        	if (playerCommand.startsWith("object")) {
    			String[] args = playerCommand.split(" ");				
    			c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
        	}
        	if (playerCommand.startsWith("empty")) {
            	c.getItems().removeAllItems();
            	c.sendMessage("You empty your inventory");
        	}
        	if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
        	}
        	if (playerCommand.startsWith("switch")) {
                if (c.playerMagicBook == 0) {
                    c.playerMagicBook = 1;
                    c.setSidebarInterface(6, 12855);
                    c.sendMessage("An ancient wisdomin fills your mind.");
                    c.getPA().resetAutocast();
                } else {
                    c.setSidebarInterface(6, 1151);
                    c.playerMagicBook = 0;
                    c.sendMessage("You feel a drain on your memory.");
                    c.autocastId = -1;
                    c.getPA().resetAutocast();
                }
        	}
            if (playerCommand.startsWith("interface")) {
                try {
                    String[] args = playerCommand.split(" ");
                    int a = Integer.parseInt(args[1]);
                    c.getPA().showInterface(a);
                } catch (Exception e) {
                    c.sendMessage("::interface id");
                }
            }
            if (playerCommand.startsWith("npc")) {
                try {
                    int newNPC = Integer.parseInt(playerCommand.substring(4));
                    if (newNPC > 0) {
                        Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
                    } else {
                        c.sendMessage("Requested NPC does not exist.");
                    }
                } catch (Exception e) {
                }
            }
            if (playerCommand.startsWith("ipban")) {
                try {
                    String playerToBan = playerCommand.substring(6);
                    for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                        if (Server.playerHandler.players[i] != null) {
                            if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                                Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
                                Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
                                c.sendMessage("You have IP banned the user: " + Server.playerHandler.players[i].playerName + " with the host: " + Server.playerHandler.players[i].connectedFrom);
                                Server.playerHandler.players[i].disconnected = true;
                            }
                        }
                    }
                } catch (Exception e) {
                    c.sendMessage("Player is probably offline.");
                }
            }

               if (playerCommand.startsWith("gfx")) {
                String[] args = playerCommand.split(" ");
                c.gfx0(Integer.parseInt(args[1]));
           }
           if (playerCommand.startsWith("update")) {
                String[] args = playerCommand.split(" ");
                int a = Integer.parseInt(args[1]);
                PlayerHandler.updateSeconds = a;
                PlayerHandler.updateAnnounced = false;
                PlayerHandler.updateRunning = true;
                PlayerHandler.updateStartTime = System.currentTimeMillis();
           }
           if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
                try {
                    String playerToBan = playerCommand.substring(4);
                    Connection.addNameToBanList(playerToBan);
                    Connection.addNameToFile(playerToBan);
                    for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                        if (Server.playerHandler.players[i] != null) {
                            if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                                Server.playerHandler.players[i].disconnected = true;
                            }
                        }
                    }
                } catch (Exception e) {
                    c.sendMessage("Player is probably offline.");
                }
           }
           if (playerCommand.startsWith("unban")) {
                try {
                    String playerToBan = playerCommand.substring(6);
                    Connection.removeNameFromBanList(playerToBan);
                    c.sendMessage(playerToBan + " has been unbanned.");
                } catch (Exception e) {
                    c.sendMessage("Player is probably offline.");
                }
           }

	   if (playerCommand.startsWith("range")) {
                            c.getItems().addItem(868, 1000);
                            c.getItems().addItem(861, 1);
                            c.getItems().addItem(892, 1000);
                            c.getItems().addItem(2491, 1);
                            c.getItems().addItem(2497, 1);
                            c.getItems().addItem(2503, 1);
                            c.getItems().addItem(6585, 1);
	}
	   if (playerCommand.startsWith("mage")) {
                            c.getItems().addItem(1381, 1);
                            c.getItems().addItem(4089, 1);
                            c.getItems().addItem(4091, 1);
                            c.getItems().addItem(4093, 1);
                            c.getItems().addItem(4095, 1);
                            c.getItems().addItem(4097, 1);
                            c.getItems().addItem(554, 1000);
                            c.getItems().addItem(555, 1000);
                            c.getItems().addItem(556, 1000);
                            c.getItems().addItem(557, 1000);
                            c.getItems().addItem(558, 1000);
                            c.getItems().addItem(559, 1000);
                            c.getItems().addItem(560, 1000);
                            c.getItems().addItem(561, 1000);
                            c.getItems().addItem(562, 1000);
                            c.getItems().addItem(563, 1000);
                            c.getItems().addItem(564, 1000);
                            c.getItems().addItem(565, 1000);
                            c.getItems().addItem(566, 1000);
	}
		
           if (playerCommand.startsWith("anim")) {
                String[] args = playerCommand.split(" ");
                c.startAnimation(Integer.parseInt(args[1]));
                c.getPA().requestUpdates();
           }
	   /*if (playerCommand.equals("bank")) { //BROKEN
            c.getPA().openUpBank();
            }*/
           if (playerCommand.startsWith("setlevel")) {
                try {
                    String[] args = playerCommand.split(" ");
                    int skill = Integer.parseInt(args[1]);
                    int level = Integer.parseInt(args[2]);
                    if (level > 99) {
                        level = 99;
                    } else if (level < 0) {
                        level = 1;
                    }
                    c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
                    c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                    c.getPA().refreshSkill(skill);
                } catch (Exception e) {
                }	
            }
        
            if (playerCommand.startsWith("master")) {
                int skill = 0;
                while (skill <= 20) {
                    c.playerXP[skill] = c.getPA().getXPForLevel(99) + 5;
                    c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                    c.getPA().refreshSkill(skill);
                    skill++;
                }
                c.sendMessage("::master command has run successfully");
            }
            
            if (playerCommand.startsWith("slave")) {
                int skill = 0;
                while (skill <= 20) {
                    if (skill != 3) {
                        c.playerXP[skill] = c.getPA().getXPForLevel(1);
                        c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                        c.getPA().refreshSkill(skill);
                    } else {
                        c.playerXP[skill] = c.getPA().getXPForLevel(10) + 5;
                        c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                        c.getPA().refreshSkill(skill);
                    }
                    skill++;
                }
                c.sendMessage("::slave command has run successfully");
            }
        }
	}
}
