package games.stendhal.server.actions.admin;

import games.stendhal.server.actions.CommandCenter;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPRuleProcessor;
import games.stendhal.server.entity.player.Player;
import marauroa.common.game.RPAction;

public class AlterQuestAction extends AdministrationAction {

	private static final int REQUIREDLEVEL = 900;

	@Override
	protected void perform(final Player player, final RPAction action) {
		

		// find player
		final StendhalRPRuleProcessor rules = SingletonRepository.getRuleProcessor();
		final Player target = rules.getPlayer(action.get("target"));
		if (target != null) {

			// old state
			final String questName = action.get("name");
			String oldQuestState = null;
			if (target.hasQuest(questName)) {
				oldQuestState = target.getQuest(questName);
			}

			// new state (or null to remove the quest)
			final String newQuestState = action.get("state");
			

			// set the quest
			target.setQuest(questName, newQuestState);

			// notify admin and altered player
			target.sendPrivateText("Admin " + player.getTitle()
					+ " changed your state of the quest '" + questName
					+ "' from '" + oldQuestState + "' to '" + newQuestState
					+ "'");
			player.sendPrivateText("Changed the state of quest '" + questName
					+ "' from '" + oldQuestState + "' to '" + newQuestState
					+ "'");
		} else {
			player.sendPrivateText(action.get("target") + " is not logged in");
		}

	}

	public static void register() {
		CommandCenter.register("alterquest", new AlterQuestAction(), REQUIREDLEVEL);
	}

}
