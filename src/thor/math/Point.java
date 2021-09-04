package thor.math;

public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static int calculateManhattanDistance(Point p1, Point p2) {
		return Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
