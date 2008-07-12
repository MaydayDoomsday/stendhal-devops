package games.stendhal.client.soundreview;

import games.stendhal.client.entity.User;

import java.awt.geom.Rectangle2D;

/**
 * the area around the userAvatar that the avatar can hear the position should
 * be updated by every step the avatar makes.
 * 
 * @author astrid
 * 
 */
public abstract class HearingArea {

	public static final int HEARINGDIST = 20;

	private static int left;

	private static int right;

	private static int lower;

	private static int upper;

	public static void set(final int x, final int y) {
		left = x - HEARINGDIST;
		right = x + HEARINGDIST;
		upper = y - HEARINGDIST;
		lower = y + HEARINGDIST;
	}

	public static boolean contains(final double x, final double y) {
		if (!User.isNull()) {
			set(User.get().getX(), User.get().getY());
		}
		if (left < x ? x < right : false) {
			return (upper < y ? y < lower : false);
		}
		return false;
	}

	public static void moveTo(final int x, final int y) {
		set(x, y);

	}

	public static void set(final double x, final double y) {
		set((int) x, (int) y);

	}

	public static Rectangle2D getAsRect() {
		if (!User.isNull()) {
			set(User.get().getX(), User.get().getY());
		}
		return new Rectangle2D.Double(left, upper, 2 * HEARINGDIST,
				2 * HEARINGDIST);
	}

}
