package games.stendhal.server.entity.npc.condition;

import static org.junit.Assert.assertFalse;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC.ChatCondition;
import games.stendhal.server.util.Area;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CompareConditionsTests {

	private List<ChatCondition> conditionsA2;

	private List<ChatCondition> conditionsB2;

	@SuppressWarnings("serial")
	@Before
	public void setUp() throws Exception {
		final Area ar = new Area(new StendhalRPZone("test"), new Rectangle() {
			// just an empty sub class
		});
		final ChatCondition[] conarray = new ChatCondition[] { new AdminCondition(),
				new AlwaysTrueCondition(),
				new AndCondition(new AlwaysTrueCondition()),
				new NakedCondition(),
				new NotCondition(new AlwaysTrueCondition()),
				new PlayerHasItemWithHimCondition("itemName"),
				new PlayerInAreaCondition(ar),
				new QuestActiveCondition("questname"),
				new QuestCompletedCondition("questname"),
				new QuestInStateCondition("questname", "stae"),
				new QuestNotCompletedCondition("questname"),
				new QuestNotInStateCondition("questname", "stae"),
				new QuestNotStartedCondition("questname"),
				new QuestStartedCondition("questname") };

		conditionsA2 = Arrays.asList(conarray);
		conditionsB2 = Arrays.asList(conarray);

	}

	@Test
	public void testname() throws Exception {
		for (final ChatCondition cond1 : conditionsA2) {
			for (final ChatCondition cond2 : conditionsB2) {
				if (cond1 != cond2) {					
					assertFalse(cond1.toString() + "should not equal" + cond2.toString(), cond1.equals(cond2));
					assertFalse(cond2.toString() + "should not equal" + cond1.toString(), cond2.equals(cond1));
				}
			}
		}
	}

	
}
