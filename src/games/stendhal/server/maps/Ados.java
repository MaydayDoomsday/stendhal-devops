package games.stendhal.server.maps;

import games.stendhal.common.Direction;
import games.stendhal.server.StendhalRPWorld;
import games.stendhal.server.StendhalRPZone;
import games.stendhal.server.entity.Portal;
import games.stendhal.server.entity.Sign;
import games.stendhal.server.entity.npc.NPCList;
import games.stendhal.server.entity.npc.SellerBehaviour;
import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.pathfinder.Path;
import marauroa.common.game.IRPZone;
import java.util.List;
import java.util.LinkedList;

public class Ados implements IContent {
	//private StendhalRPWorld world;

	private ShopList shops;

	private NPCList npcs;

	public Ados(StendhalRPWorld world) {
		this.npcs = NPCList.get();
		this.shops = ShopList.get();
		//this.world = world;

		buildRockArea((StendhalRPZone) world.getRPZone(new IRPZone.ID(
				"0_ados_rock")));
		buildZooArea((StendhalRPZone) world.getRPZone(new IRPZone.ID(
		"0_ados_outside_nw")));
		buildZooCaveSub1Area((StendhalRPZone) world.getRPZone(new IRPZone.ID(
		"-1_ados_outside_nw")));
	}


	private void buildRockArea(StendhalRPZone zone) {
		SpeakerNPC npc = new SpeakerNPC("Balduin") {
			protected void createPath() {
				// NPC doesn't move
				List<Path.Node> nodes = new LinkedList<Path.Node>();
				setPath(nodes, false);
			}

			protected void createDialog() {
				addHelp("There is a swamp east of this mountain where you might get some rare weapons.");
				addJob("I'm much too old for hard work. I'm just living here as a hermit.");
				addGoodbye("It was nice to meet you.");
			}
			// remaining behaviour is defined in maps.quests.WeaponsCollector.
		};
		npcs.add(npc);
		zone.assignRPObjectID(npc);
		npc.put("class", "oldwizardnpc");
		npc.set(16, 7);
		npc.setDirection(Direction.DOWN);
		npc.initHP(100);
		zone.addNPC(npc);
	}

	private void buildZooArea(StendhalRPZone zone) {
		Sign sign = new Sign();
		zone.assignRPObjectID(sign);
		sign.setx(53);
		sign.sety(48);
		sign.setText("Ados Wildlife Refuge|Home for endangered animals");
		zone.add(sign);
		
		Portal portal = new Portal();
		zone.assignRPObjectID(portal);
		portal.setx(67);
		portal.sety(24);
		portal.setNumber(0);
		portal.setDestination("-1_ados_outside_nw", 0);
		zone.addPortal(portal);
		
		SpeakerNPC npc = new SpeakerNPC("Katinka") {
			protected void createPath() {
				List<Path.Node> nodes = new LinkedList<Path.Node>();
				nodes.add(new Path.Node(41, 39));
				nodes.add(new Path.Node(51, 39));
				nodes.add(new Path.Node(51, 45));
				nodes.add(new Path.Node(58, 45));
				nodes.add(new Path.Node(58, 41));
				nodes.add(new Path.Node(51, 41));
				nodes.add(new Path.Node(51, 39));
				setPath(nodes, true);
			}

			protected void createDialog() {
				addHelp("Can you keep a secret? Dr. Feelgood, our veterinary, can sell you medicine that he doesn't need for the animals.");
				addJob("I'm the keeper of this animal refuge.");
				addGoodbye("Goodbye!");
			}
			// remaining behaviour is defined in maps.quests.ZooFood.
		};
		npcs.add(npc);
		zone.assignRPObjectID(npc);
		npc.put("class", "woman_007_npc");
		npc.set(41, 39);
		//npc.setDirection(Direction.DOWN);
		npc.initHP(100);
		zone.addNPC(npc);

		npc = new SpeakerNPC("Dr. Feelgood") {
			protected void createPath() {
				List<Path.Node> nodes = new LinkedList<Path.Node>();
				nodes.add(new Path.Node(53, 27));
				nodes.add(new Path.Node(53, 39));
				nodes.add(new Path.Node(62, 39));
				nodes.add(new Path.Node(62, 31));
				nodes.add(new Path.Node(63, 31));
				nodes.add(new Path.Node(63, 39));
				nodes.add(new Path.Node(51, 39));
				nodes.add(new Path.Node(51, 27));
				setPath(nodes, true);
			}

			protected void createDialog() {
				//Behaviours.addHelp(this,
				//				   "...");
				
				addReply("heal",
						"Sorry, I'm licensed to heal animals only, not humans. But... ssshh! I want to make you an #offer.");

				addJob("I'm the veterinary.");
				addSeller(new SellerBehaviour(world, shops.get("healing")) {
					@Override
					public int getUnitPrice(String item) {
						// Player gets 20 % rebate
						return (int) (0.8f * priceList.get(item));
					}
				});

				addGoodbye("Bye!");
			}
			// remaining behaviour is defined in maps.quests.ZooFood.
		};
		npcs.add(npc);
		zone.assignRPObjectID(npc);
		npc.put("class", "doctornpc");
		npc.set(53, 27);
		//npc.setDirection(Direction.DOWN);
		npc.initHP(100);
		zone.addNPC(npc);
	}

	private void buildZooCaveSub1Area(StendhalRPZone zone) {
		Portal portal = new Portal();
		zone.assignRPObjectID(portal);
		portal.setx(4);
		portal.sety(23);
		portal.setNumber(0);
		portal.setDestination("0_ados_outside_nw", 0);
		zone.addPortal(portal);
	}
}
