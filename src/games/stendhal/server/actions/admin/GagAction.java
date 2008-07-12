package games.stendhal.server.actions.admin;

import games.stendhal.server.actions.CommandCenter;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.player.Player;
import marauroa.common.game.RPAction;

import static games.stendhal.server.actions.WellKnownActionConstants.MINUTES;
import static games.stendhal.server.actions.WellKnownActionConstants.TARGET;

public class GagAction extends AdministrationAction {
	private static final String USAGE_GAG_NAME_MINUTES_REASON = "Usage: /gag name minutes reason";
	private static final String _REASON = "reason";

	private static final String _GAG = "gag";

	public static void register() {
		CommandCenter.register(_GAG, new GagAction(), 400);

	}

	@Override
	public void perform(final Player player, final RPAction action) {

		if (action.has(TARGET) && action.has(MINUTES)) {
			final String target = action.get(TARGET);
			String reason = "";
			if (action.has(_REASON)) {
				reason = action.get(_REASON);
			}
			try {
				final int minutes = action.getInt(MINUTES);
				SingletonRepository.getRuleProcessor().addGameEvent(player.getName(),
						_GAG, target, Integer.toString(minutes), reason);
				SingletonRepository.getGagManager().gag(target, player, minutes, reason);
			} catch (final NumberFormatException e) {
				player.sendPrivateText(USAGE_GAG_NAME_MINUTES_REASON);
			}
		} else {
			player.sendPrivateText(USAGE_GAG_NAME_MINUTES_REASON);
		}
	}

}
