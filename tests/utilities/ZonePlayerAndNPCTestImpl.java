package utilities;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.npc.SpeakerNPC;

import java.util.List;
import java.util.Vector;

import org.junit.After;

/**
 * NPCTest is the base class for tests dealing with NPCs.
 *
 * @author Martin Fuchs
 */
public abstract class ZonePlayerAndNPCTestImpl extends ZoneAndPlayerTestImpl {

	private final List<String> npcNames = new Vector<String>();
	
	/**
	 * Register NPC names for cleanup in tearDown().
	 * @param zoneName 
	 * 
	 * @param npcNames
	 */
	protected ZonePlayerAndNPCTestImpl(final String zoneName, final String... npcNames) {
		super(zoneName);
		
		assertTrue(npcNames.length > 0);

		for (final String npcName : npcNames) {
			this.npcNames.add(npcName);
		}
    }

	/**
	 * Reset all involved NPCs.
	 *
	 * @throws Exception
	 */
	@Override
	@After
	public void tearDown() throws Exception {
		for (final String npcName : npcNames) {
			resetNPC(npcName);
		}
		
		super.tearDown();
	}

	/**
	 * Return the SpeakerNPC of the given name.
	 * 
	 * @param npcName
	 * @return SpeakerNPC
	 */
	protected SpeakerNPC getNPC(final String npcName) {
		final SpeakerNPC npc = SingletonRepository.getNPCList().get(npcName);

		assertNotNull(npc);

		return npc;
	}

}
