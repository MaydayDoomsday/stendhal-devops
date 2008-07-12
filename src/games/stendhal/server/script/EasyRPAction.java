package games.stendhal.server.script;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.scripting.ScriptImpl;
import games.stendhal.server.entity.player.Player;

import java.util.List;

import marauroa.common.game.RPAction;

/**
 * For testing purposes.
 * 
 * /script EasyRPAction.class type moveto x 1 y 1
 * 
 */

public class EasyRPAction extends ScriptImpl {

	@Override
	public void execute(final Player admin, final List<String> args) {
		super.execute(admin, args);

		if ((args.size() == 0) || (args.size() % 2 != 0)) {
			admin.sendPrivateText("/script EasyRPAction.class <key1> <value1> [<key2> <value2>] ...");
			return;
		}

		final RPAction action = new RPAction();

		for (int i = 0; i < (args.size() / 2); i++) {
			action.put(args.get(i * 2), args.get(i * 2 + 1));
		}

		SingletonRepository.getRuleProcessor().execute(admin, action);
	}

}
