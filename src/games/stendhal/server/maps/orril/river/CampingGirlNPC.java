package games.stendhal.server.maps.orril.river;

import games.stendhal.common.Direction;
import games.stendhal.server.StendhalRPZone;
import games.stendhal.server.config.ZoneConfigurator;
import games.stendhal.server.entity.npc.SpeakerNPC;

import java.util.Map;

/**
 * Configure Orril River South Campfire (Outside/Level 0).
 */
public class CampingGirlNPC implements ZoneConfigurator {
	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	public void configureZone(StendhalRPZone zone, Map<String, String> attributes) {
		buildCampfireArea(zone);
	}

	private void buildCampfireArea(StendhalRPZone zone) {
		SpeakerNPC sally = new SpeakerNPC("Sally") {

			@Override
			protected void createPath() {
				// NPC does not move
				setPath(null);
			}

			@Override
			protected void createDialog() {
				//addGreeting();
				addJob("Work? I'm just a little girl! I'm a scout, you know.");
				addHelp("You can find lots of useful stuff in the forest; wood and mushrooms, for example. But beware, some mushrooms are poisonous!");
				addGoodbye();
				// remaining behaviour is defined in maps.quests.Campfire.				
			}
		};

		sally.setEntityClass("littlegirlnpc");
		sally.setPosition(40, 61);
		sally.setDirection(Direction.RIGHT);
		sally.initHP(100);
		zone.add(sally);
	}
}
