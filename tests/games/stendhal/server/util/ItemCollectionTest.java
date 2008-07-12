package games.stendhal.server.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests for the area class.
 *
 * @author M. Fuchs
 */
public class ItemCollectionTest {
	
	@Test
	public void testCreateArea() {
	    final ItemCollection coll = new ItemCollection();
	    assertEquals("", coll.toStringForQuestState());
        assertEquals(Arrays.asList(), coll.toStringList());

	    coll.addItem("cheese", 5);
	    assertEquals("cheese=5", coll.toStringForQuestState());

	    coll.addFromQuestStateString("cheese=2;ham=3");
        assertEquals("cheese=7;ham=3", coll.toStringForQuestState());

        assertTrue(coll.removeItem("cheese", 1));
        assertEquals("cheese=6;ham=3", coll.toStringForQuestState());
        assertEquals(Arrays.asList("cheese=6", "ham=3"), coll.toStringList());
        assertEquals(Arrays.asList("6 #'pieces of cheese'", "3 #'pieces of ham'"), coll.toStringListWithHash());

        assertFalse(coll.removeItem("ham", 5));
        assertEquals("cheese=6;ham=3", coll.toStringForQuestState());

        assertTrue(coll.removeItem("cheese", 6));
        assertEquals("ham=3", coll.toStringForQuestState());

        assertTrue(coll.removeItem("ham", 3));
        assertEquals("", coll.toStringForQuestState());
	}

}
