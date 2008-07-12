package games.stendhal.server.entity.creature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.mapstuff.spawner.SheepFood;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.common.Log4J;
import marauroa.common.game.RPObject;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import utilities.PlayerTestHelper;
import utilities.RPClass.CorpseTestHelper;
import utilities.RPClass.SheepFoodTestHelper;
import utilities.RPClass.SheepTestHelper;

public class SheepTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		assertTrue(MockStendlRPWorld.get() instanceof MockStendlRPWorld);
		PlayerTestHelper.generateCreatureRPClasses();
		SheepTestHelper.generateRPClasses();
		SheepFoodTestHelper.generateRPClasses();
	}

	@Test
	public void testSearchForFoodNotThere() {
		final Sheep meh = new Sheep();
		final StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		zone.add(meh);
		assertFalse(meh.searchForFood());
	}

	@Test
	public void testSearchForFoodNextTo() {
		final Sheep meh = new Sheep();
		final StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		zone.add(meh);
		final RPObject foodobject = new RPObject();
		foodobject.put("amount", 1);

		final SheepFood food = new SheepFood(foodobject);
		assertTrue(food.getAmount() > 0);
		zone.add(food);

		assertTrue(meh.searchForFood());
		assertEquals("eat", meh.getIdea());
	}

	@Test
	public void testSearchForFoodNotNextTo() {
		final Sheep meh = new Sheep();
		final StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		assertTrue(zone.getSheepFoodList().isEmpty());
		zone.add(meh);
		final RPObject foodobject = new RPObject();
		foodobject.put("amount", 1);
		foodobject.put("x", 3);
		foodobject.put("y", 3);
		final SheepFood food = new SheepFood(foodobject);
		assertTrue(food.getAmount() > 0);
		assertFalse(food.nextTo(meh));

		zone.add(food);
		assertFalse(zone.getSheepFoodList().isEmpty());
		assertTrue(meh.searchForFood());
		assertEquals("found food and thinks of it", "food", meh.getIdea());
	}

	@Test
	public void testSearchForBlockedFood() {
		final Sheep meh = new Sheep();
		final StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		zone.add(meh);
		final RPObject foodobject = new RPObject();
		foodobject.put("amount", 1);
		foodobject.put("x", 2);
		foodobject.put("y", 2);
		final SheepFood food = new SheepFood(foodobject);
		assertTrue(food.getAmount() > 0);
		assertFalse(food.nextTo(meh));

		zone.add(food);

		for (int x = 0; x <= zone.getWidth(); x++) {
			for (int y = 0; y <= zone.getHeight(); y++) {
				zone.collisionMap.setCollide(x, y);
			}
		}
		assertFalse("no path found", meh.searchForFood());
		assertEquals(null, meh.getIdea());
	}

	@Test
	public void testGetFarerNotBlockedFood() {

		final boolean[][] collisions = { { false, true, false, true, false, false, false, false, false, false },
				{ false, true, false, true, false, false, false, false, false, false },
				{ false, true, false, true, false, false, false, false, false, false },
				{ false, true, false, true, false, false, false, false, false, false },
				{ false, true, true, true, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false, false } };
		final StendhalRPZone zone = new StendhalRPZone("testzone", 9, 9);
		for (int x = 0; x <= zone.getWidth(); x++) {
			for (int y = 0; y <= zone.getHeight(); y++) {
				if (collisions[x][y]) {
					zone.collisionMap.setCollide(x, y);
				}
			}
		}

		final Sheep meh = new Sheep();

		zone.add(meh);
		final RPObject foodobject = new RPObject();
		foodobject.put("amount", 1);
		foodobject.put("x", 2);
		foodobject.put("y", 2);
		final SheepFood food = new SheepFood(foodobject);
		assertTrue(food.getAmount() > 0);
		assertFalse(food.nextTo(meh));

		final RPObject foodobject2 = new RPObject();
		foodobject2.put("amount", 1);
		foodobject2.put("x", 0);
		foodobject2.put("y", 3);
		final SheepFood food2 = new SheepFood(foodobject2);
		assertTrue(food2.getAmount() > 0);
		assertFalse(food2.nextTo(meh));

		zone.add(food2);

		assertTrue("no path found", meh.searchForFood());
		assertEquals("food", meh.getIdea());
	}

	@Test
	public void testDescribe() {
		final Sheep meh = new Sheep();
		assertEquals("You see a sheep; it looks like it weighs about 0.", meh.describe());
		meh.setDescription("mehmeh");
		assertEquals("mehmeh", meh.describe());
	}

	@Ignore
	@Test
	public void testOnDeadEntity() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testOnDeadString() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testLogic() {
		fail("Not yet implemented");
	}

	@Test
	public void testSheep() {
		new Sheep();
	}

	@Test
	public void testSheepPlayer() {
		final StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		MockStendlRPWorld.get().addRPZone(zone);

		final Player bob = PlayerTestHelper.createPlayer("bob");
		zone.add(bob);

		final Sheep meh = new Sheep(bob);

		assertSame(bob, meh.getOwner());
		assertSame(meh, bob.getSheep());
	}

	@Test
	public void testSheepRPObjectPlayer() {
		final StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		MockStendlRPWorld.get().addRPZone(zone);

		final Player bob = PlayerTestHelper.createPlayer("bob");
		zone.add(bob);

		final Sheep meh = new Sheep();
		final Sheep meh2 = new Sheep(meh, bob);

		assertNotSame(meh, meh2);
		assertSame(bob, meh2.getOwner());
		assertSame(meh2, bob.getSheep());
	}

	@Test
	public void testOnHungry() {
		final Sheep meh = new Sheep();
		final StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		zone.add(meh);
		assertFalse("there is no food in the zone yet, so what hunt", meh.onHungry());
		meh.setIdea("food");
		assertFalse("there is no food in the zone yet, so what hunt", meh.onHungry());
		meh.setIdea("food");
		meh.setSpeed(1.0);
		assertTrue("pretend hunting", meh.onHungry());
	}

	@Ignore
	@Test
	public void testOnIdle() {
		fail("Not yet implemented");
	}

	@Test
	public void testOnStarve() {
		CorpseTestHelper.generateRPClasses();
		final Sheep meh = new Sheep();
		final StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		zone.add(meh);

		meh.setWeight(1);
		meh.setHP(2);
		meh.onStarve();
		assertEquals(0, meh.getWeight());
		assertEquals(2, meh.getHP());

		meh.onStarve();
		assertEquals(0, meh.getWeight());
		assertEquals(1, meh.getHP());

		meh.onStarve();
		assertEquals(0, meh.getWeight());
		assertEquals(0, meh.getHP());
	}

	@Test
	public void testEat() {
		final RPObject foodobject = new RPObject();
		foodobject.put("amount", 10);
		final SheepFood food = new SheepFood(foodobject);
		
		final Sheep meh = new Sheep();
		
		meh.setWeight(1);
		meh.setHP(5);
		meh.eat(food);
		
		assertEquals(2, meh.getWeight());
		assertEquals(10, meh.getHP());
		meh.eat(food);
		assertEquals(3, meh.getWeight());
		assertEquals(15, meh.getHP());
		meh.eat(food);
		assertEquals(4, meh.getWeight());
		assertEquals(20, meh.getHP());
		
		meh.setWeight(99);
		
		meh.eat(food);
		assertEquals(100, meh.getWeight());

		meh.eat(food);
		assertEquals(100, meh.getWeight());
	}

}
