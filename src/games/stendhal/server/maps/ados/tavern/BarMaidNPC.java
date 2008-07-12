package games.stendhal.server.maps.ados.tavern;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.behaviour.adder.SellerAdder;
import games.stendhal.server.entity.npc.behaviour.impl.SellerBehaviour;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Ados Tavern (Inside / Level 0).
 *
 * @author hendrik
 */
public class BarMaidNPC implements ZoneConfigurator {

	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildTavern(zone, attributes);
	}

	private void buildTavern(final StendhalRPZone zone, final Map<String, String> attributes) {
		final SpeakerNPC tavernMaid = new SpeakerNPC("Coralia") {

			@Override
			protected void createPath() {
				final List<Node> nodes = new LinkedList<Node>();
				nodes.add(new Node(13, 9));
				nodes.add(new Node(13, 7));
				nodes.add(new Node(6, 7));
				nodes.add(new Node(6, 12));
				nodes.add(new Node(10, 12));
				nodes.add(new Node(10, 5));
				nodes.add(new Node(17, 5));
                nodes.add(new Node(17, 3));
                nodes.add(new Node(3, 3));
                nodes.add(new Node(3, 6));
                nodes.add(new Node(13, 6));
				setPath(new FixedPath(nodes, true));
			}

			@Override
			protected void createDialog() {
				addGreeting();
				addJob("I am the bar maid for this fair tavern. We sell both imported and local beers, and fine food.");
				addHelp("This tavern is a great place to take a break and meet new people! Just ask if you want me to #offer you a drink.");
				final Map<String, Integer> offerings = new HashMap<String, Integer>();
				offerings.put("beer", 15);
				offerings.put("pie", 160);
				offerings.put("bread", 50);
				offerings.put("cheese", 20);
				offerings.put("tomato", 30);
				new SellerAdder().addSeller(this, new SellerBehaviour(offerings));
				addGoodbye();
			}
		};

		tavernMaid.setEntityClass("maidnpc");
		tavernMaid.setPosition(13, 9);
		tavernMaid.initHP(100);
		zone.add(tavernMaid);
	}
}
