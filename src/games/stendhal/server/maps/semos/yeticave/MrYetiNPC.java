package games.stendhal.server.maps.semos.yeticave;

import games.stendhal.server.core.config.ZoneConfigurator;import games.stendhal.server.core.engine.StendhalRPZone;import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MrYetiNPC implements ZoneConfigurator {
	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildYeti(zone);
	}

	private void buildYeti(final StendhalRPZone zone) {
		final SpeakerNPC yetimale = new SpeakerNPC("Mr. Yeti") {

			@Override
			protected void createPath() {
				final List<Node> nodes = new LinkedList<Node>();
				nodes.add(new Node(29, 29));
				nodes.add(new Node(17, 29));
				nodes.add(new Node(17, 32));
				nodes.add(new Node(14, 32));
				nodes.add(new Node(14, 38));
				nodes.add(new Node(13, 38));
				nodes.add(new Node(13, 46));
				nodes.add(new Node(19, 46));
				nodes.add(new Node(19, 54));
				nodes.add(new Node(23, 54));
				nodes.add(new Node(21, 54));
				nodes.add(new Node(21, 45));
				nodes.add(new Node(26, 45));
				nodes.add(new Node(26, 37));
				nodes.add(new Node(29, 37));
				setPath(new FixedPath(nodes, true));
			}

			@Override
			protected void createDialog() {
				addGreeting("Greetings, strange foreigner!");
				addJob("My job is to clean up all this around you!");
				addHelp("I am not able to help you!");				
				addGoodbye();
			}
		};

		yetimale.setEntityClass("yetimalenpc");
		yetimale.setPosition(29, 29);
		yetimale.initHP(100);
		zone.add(yetimale);
	}
}