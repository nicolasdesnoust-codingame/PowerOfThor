package thor.math;

public enum Direction {
	SOUTH_EAST("SE", 1, 1),
	NORTH_EAST("NE", 1, -1),
	SOUTH_WEST("SW", -1, 1),
	NORTH_WEST("NW", -1, -1),
	SOUTH("S", 0, 1),
	EAST("E", 1, 0),
	WEST("W", -1, 0),
	NORTH("N", 0, -1);

	public final String label;
	public final int xMove;
	public final int yMove;

	private Direction(String label, int xMove, int yMove) {
		this.label = label;
		this.xMove = xMove;
		this.yMove = yMove;
	}

	@Override
	public String toString() {
		return label;
	}

	public static Direction from(Point source, Point destination) {
		int sourceX = source.getX();
		int sourceY = source.getY();
		int destinationX = destination.getX();
		int destinationY = destination.getY();

		if (destinationX > sourceX && destinationY == sourceY) {
			return Direction.EAST;
		} else if (destinationX < sourceX && destinationY == sourceY) {
			return Direction.WEST;
		} else if (destinationX == sourceX && destinationY > sourceY) {
			return Direction.SOUTH;
		} else if (destinationX == sourceX && destinationY < sourceY) {
			return Direction.NORTH;
		} else if (destinationX > sourceX && destinationY < sourceY) {
			return Direction.NORTH_EAST;
		} else if (destinationX < sourceX && destinationY > sourceY) {
			return Direction.SOUTH_WEST;
		} else if (destinationX > sourceX && destinationY > sourceY) {
			return Direction.SOUTH_EAST;
		} else {
			return Direction.NORTH_WEST;
		}
	}
}
