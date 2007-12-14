package games.stendhal.server.maps.fado.hut;

import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.SpeakerNPCFactory;
import games.stendhal.server.entity.npc.behaviour.adder.SellerAdder;
import games.stendhal.server.entity.npc.behaviour.impl.SellerBehaviour;

/**
 * A lady wizard who sells potions and antidotes. Original name: Sarzina
 */
public class SellerNPC extends SpeakerNPCFactory {

	@Override
	public void createDialog(SpeakerNPC npc) {
		npc.addGreeting();
		npc.addJob("I make potions and antidotes, to #offer to warriors.");
		npc.addHelp("You can take one of my prepared medicines with you on your travels; just ask for an #offer.");
		new SellerAdder().addSeller(npc, new SellerBehaviour(ShopList.get().get("superhealing")));
		npc.addGoodbye();
	}
}
