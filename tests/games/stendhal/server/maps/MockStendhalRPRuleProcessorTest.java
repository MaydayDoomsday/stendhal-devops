package games.stendhal.server.maps;

import static org.junit.Assert.*;
import games.stendhal.server.core.engine.StendhalRPRuleProcessor;
import games.stendhal.server.entity.player.Player;

import static org.hamcrest.core.Is.*;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.PlayerTestHelper;

public class MockStendhalRPRuleProcessorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PlayerTestHelper.removeAllPlayers();
	}

	@Test
	public void testGetTurn() {
		assertThat(MockStendhalRPRuleProcessor.get().getTurn(), is(0));
	}

	@Test
	public void testGet() {
		assertSame(MockStendhalRPRuleProcessor.get(),
				MockStendhalRPRuleProcessor.get());
	}

	@Test
	public void testAddPlayer() {
		final MockStendhalRPRuleProcessor processor = MockStendhalRPRuleProcessor.get();
		assertThat(StendhalRPRuleProcessor.getAmountOfOnlinePlayers(), is(0));

		final Player bob = PlayerTestHelper.createPlayer("bob");
		processor.addPlayer(bob);
		assertThat(StendhalRPRuleProcessor.getAmountOfOnlinePlayers(), is(1));
		assertSame(bob, processor.getPlayer("bob"));
		final Player bob2 = PlayerTestHelper.createPlayer("bob");
		processor.addPlayer(bob2);
		assertThat(StendhalRPRuleProcessor.getAmountOfOnlinePlayers(), is(1));
		assertSame(bob2, processor.getPlayer("bob"));
		assertNotSame(bob, processor.getPlayer("bob"));
	}

}
