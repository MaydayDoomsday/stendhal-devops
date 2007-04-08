/*
 * @(#) games/stendhal/client/entity/InvisibleEntity2DView.java
 *
 * $Id$
 */

package games.stendhal.client.entity;

//
//

import games.stendhal.client.GameScreen;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import marauroa.common.game.RPObject;

/**
 * The 2D view of a portal.
 */
public class InvisibleEntity2DView extends Entity2DView {
	/**
	 * Create a 2D view of an entity.
	 *
	 * @param	entity		The entity to render.
	 */
	public InvisibleEntity2DView(final InvisibleEntity entity) {
		super(entity);
	}


	//
	// Entity2DView
	//

	/**
	 * Build the visual representation of this entity.
	 *
	 * @param	object		An entity object.
	 */
	@Override
	protected void buildRepresentation(final RPObject object) {
	}


	/**
	 * Get the 2D area that is drawn in.
	 *
	 * @return	The 2D area this draws in.
	 */
	public Rectangle2D getDrawnArea() {
		return new Rectangle.Double(getX(), getY(), 1.0, 1.0);
        }

	/**
	 * Determines on top of which other entities this entity should be
	 * drawn. Entities with a high Z index will be drawn on top of ones
	 * with a lower Z index.
	 * 
	 * Also, players can only interact with the topmost entity.
	 * 
	 * @return	The drawing index.
	 */
	public int getZIndex() {
		return 5000;
	}


	//
	// <EntityView>
	//

	/**
	 * Draw the entity.
	 *
	 * @param	screen		The screen to drawn on.
	 */
	public void draw(final GameScreen screen) {
	}
}
