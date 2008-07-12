package games.stendhal.server.entity.creature;

import org.apache.log4j.Logger;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.player.Player;

/**
 * A creature that will only reward the specified player.
 * 
 * @author hendrik
 */
public class DeathMatchCreature extends Creature {

	private int points;
	private String playerName; // save only the name to enable GC of the player object

	/**
	 * DeathCreature.
	 * 
	 * @param copy
	 *            creature to wrap
	 */
	public DeathMatchCreature(final Creature copy) {
		super(copy);
	}

	@Override
	protected void addPlayersToReward(final Entity player) {
		// don't reward the other attackers
	}

	@Override
	public Creature getInstance() {
		return new DeathMatchCreature(this);
	}

	/**
	 * Only this player gets an XP reward.
	 * 
	 * @param player
	 *            Player to reward
	 */
	public void setPlayerToReward(final Player player) {
		this.playerName = player.getName();
	}

	@Override
	protected void rewardKillers(final int oldXP) {
		final Player player =  SingletonRepository.getRuleProcessor().getPlayer(playerName);
		if (player == null) {
			return;
		}

		final Integer damageReceivedByPlayer = damageReceived.get(player);
		if (damageReceivedByPlayer != null) {
			points = player.getLevel()
					* (damageReceivedByPlayer / totalDamageReceived);

			// For some quests etc., it is required that the player kills a
			// certain creature without the help of others.
			// Find out if the player killed this RPEntity on his own, but
			// don't overwrite solo with shared.
			if (damageReceivedByPlayer == totalDamageReceived) {
				player.setSoloKill(getName());
			} else {
				player.setSharedKill(getName());
			}
			player.notifyWorldAboutChanges();

		} else {
			Logger.getLogger(DeathMatchCreature.class).error(this + " rewardkillers with amount: " + damageReceived);
		}
	}

	/**
	 * Calculates the deathmatch points for this kill.
	 * 
	 * @return number of points to reward
	 */
	public int getDMPoints() {
		return points;
	}

}
