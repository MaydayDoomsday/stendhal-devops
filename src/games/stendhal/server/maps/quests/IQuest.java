package games.stendhal.server.maps.quests;

import games.stendhal.server.entity.player.Player;

import java.util.List;

/**
 * All quests MUST implement this interface or extend the abstract class
 * AbstractQuest in order for the loader to recognize them
 */
public interface IQuest {

	/**
	 * Initialize the quest on server startup.
	 *
	 * @param name
	 *            Name of this quest
	 */
	void init(String name);

	/**
	 * adds the quest to the game world (e.g. by placing SpeakerNPCs there)
	 */
	void addToWorld();

	/**
	 * Was the quest started?
	 *
	 * @param player
	 *            Player
	 * @return true, if it was started, false otherwise
	 */
	boolean isStarted(Player player);

	/**
	 * Was the quest completed?<!--sic--> Note: A quest can be completed
	 * without its status beeing "Done" (e. g. rejected, failed).
	 *
	 * @param player
	 *            Player
	 * @return true, if it was completed, false otherwise
	 */
	boolean isCompleted(Player player);

	/**
	 * May the quest be repeated?
	 *
	 * @param player
	 *            Player
	 * @return true, if it can be repeated, false otherwise
	 */
	boolean isRepeatable(Player player);

	/**
	 * Gets a list of history item-names. The texts will be looked up in
	 * quest.xml
	 *
	 * @param player
	 *            Player
	 * @return list of history item-names
	 */
	List<String> getHistory(Player player);

	/**
	 * Gets a list of possible hint-names. The hint system will ensure that the
	 * same hint is not displayed twice. This class creates a list of useful
	 * hints (without hints about already completed parts). The texts will be
	 * looked up in quest.xml
	 *
	 * @param player
	 *            Player
	 * @return list of history item-names
	 */
	List<String> getHint(Player player);

	/**
	 * Returns the name of the quest
	 *
	 * @return name
	 */
	String getName();

}
