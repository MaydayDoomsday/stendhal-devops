package games.stendhal.server.entity.item.consumption;

import static org.junit.Assert.assertEquals;
import games.stendhal.server.entity.item.Drink;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.RPClass.ItemTestHelper;

public class FeederFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetEaterForSoup() {
		ItemTestHelper.generateRPClasses();
		final String name = "soup";
		final String clazz = "drink";
		final String subclass = "soup";
		final Map<String, String> map = new HashMap<String, String>();
		map.put("description",
				"You see a bowl of soup. Its contents fill you up.");
		map.put("amount", "2500");
		map.put("frequency", "10");
		map.put("quantity", "1");
		map.put("regen", "25");

		final Drink soup = new Drink(name, clazz, subclass, map);
		assertEquals(Eater.class, FeederFactory.get(soup).getClass());
	}
}
