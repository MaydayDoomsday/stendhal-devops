package games.stendhal.server.maps.wofol.bakery;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.behaviour.adder.ProducerAdder;
import games.stendhal.server.entity.npc.behaviour.impl.ProducerBehaviour;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Builds the wofol baker NPC.
 *
 * @author kymara
 */
public class BakerNPC implements ZoneConfigurator {
	//
	// ZoneConfigurator
	//

	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	public void configureZone(StendhalRPZone zone, Map<String, String> attributes) {
		buildNPC(zone, attributes);
	}

	//
	// BakerNPC
	//

	private void buildNPC(StendhalRPZone zone, Map<String, String> attributes) {
		SpeakerNPC baker = new SpeakerNPC("Kroip") {

			@Override
			protected void createPath() {
				List<Node> nodes = new LinkedList<Node>();
				nodes.add(new Node(15, 3));
				nodes.add(new Node(15, 8));
				nodes.add(new Node(13, 8));
				nodes.add(new Node(13, 10));
				nodes.add(new Node(10, 10));
				nodes.add(new Node(10, 12));
				nodes.add(new Node(7, 12));
				nodes.add(new Node(7, 6));
				nodes.add(new Node(2, 6));
				nodes.add(new Node(2, 4));
				nodes.add(new Node(8, 4));
				nodes.add(new Node(8, 3));
				nodes.add(new Node(2, 3));
				nodes.add(new Node(2, 6));
				nodes.add(new Node(27, 6));
				nodes.add(new Node(27, 3));
				setPath(new FixedPath(nodes, true));
			}

			@Override
			protected void createDialog() {
				// This isn't bad grammar. It's his tyle of speech! Do't correct pls.
				addJob("I #make #pizza. I have learn from the great baker #Leander.");
				addReply(Arrays.asList("button mushroom", "porcini"),
				        "#Leander taught me mushroom grow in wood area.");
				addReply("flour", "Mill near Semos produce from grain.");
				addReply("cheese", "Cheese? I know not.");
				addReply("tomato", "This grow in glass houses.");
				addReply("ham", "The pig animal have ham.");
				addHelp("I have work with #Leander, I #make #pizza.");
				addReply("Leander", "I was with human, in Semos. The great Leander taught to #make #pizza");
				addQuest("#Leander need pizza send. I #make #pizza, you have ingredients.");
				addGoodbye("You no take candle!");

				// makes a pizza if you bring flour cheese mushroom porcini and ham
				// (uses sorted TreeMap instead of HashMap)
				Map<String, Integer> requiredResources = new TreeMap<String, Integer>();
				requiredResources.put("flour", 2);
				requiredResources.put("cheese", 1);
				requiredResources.put("tomato", 1);
				requiredResources.put("button mushroom", 1);
				requiredResources.put("porcini", 1);
				requiredResources.put("ham", 1);

				ProducerBehaviour behaviour = new ProducerBehaviour("kroip_make_pizza", "make", "pizza",
				        requiredResources, 5 * 60, true);

				new ProducerAdder().addProducer(this, behaviour,
				        "Welkom!");
			}
		};

		baker.setEntityClass("koboldchefnpc");
		baker.setPosition(15, 3);
		baker.initHP(1000);
		zone.add(baker);
	}
}
