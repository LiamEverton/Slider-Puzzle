package puzzle.type;

/**
 * Direction constant to deal with direction offsets.
 *
 * @author Liam Everton
 */
public enum Direction {

	NORTH(0, -1),
	EAST(1, 0),
	WEST(-1, 0),
	SOUTH(0, 1);

	private int offsetX;
	private int offsetY;

	/**
	 * Constructs a new Direction.
	 *
	 * @param offsetX - the offset of x
	 * @param offsetY - the offset of y.
	 */
	Direction(int offsetX, int offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	/**
	 * Gets the Direction's offset value of the x co-ordinate.
	 *
	 * @return a positive or negative integer value.
	 */
	public int getOffsetX() {
		return this.offsetX;
	}

	/**
	 * Gets the Direction's offset value of the y co-ordinate.
	 *
	 * @return a positive or negative integer value.
	 */
	public int getOffsetY() {
		return this.offsetY;
	}
}
