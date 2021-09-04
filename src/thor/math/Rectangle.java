package thor.math;
import java.util.Collection;

public class Rectangle {
	private final Point topLeftCorner;
	private final Point bottomRightCorner;

	private Rectangle(Point topLeftCorner, Point bottomRightCorner) {
		this.topLeftCorner = topLeftCorner;
		this.bottomRightCorner = bottomRightCorner;
	}

	public static Rectangle getBoundingBoxOf(Collection<Point> points) {
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;

		for (Point point : points) {
			minX = Math.min(minX, point.getX());
			maxX = Math.max(maxX, point.getX());
			minY = Math.min(minY, point.getY());
			maxY = Math.max(maxY, point.getY());
		}

		return new Rectangle(new Point(minX, minY), new Point(maxX, maxY));
	}

	public Point getCenter() {
		int x = (int) Math.round((bottomRightCorner.getX() - topLeftCorner.getX()) / 2.0);
		int y = (int) Math.round((bottomRightCorner.getY() - topLeftCorner.getY()) / 2.0);

		return new Point(topLeftCorner.getX() + x, topLeftCorner.getY() + y);
	}

	public Point getTopLeftCorner() {
		return topLeftCorner;
	}

	public Point getBottomRightCorner() {
		return bottomRightCorner;
	}
}
