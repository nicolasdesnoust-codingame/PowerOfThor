package thor.entities;

import java.util.Collection;
import java.util.stream.Collectors;

import thor.math.Point;

public class Entity {
	protected Point position;

	public Entity(int x, int y) {
		this.position = new Point(x, y);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public static Collection<Point> toPositions(Collection<Entity> entities) {
		return entities.stream()
				.map(Entity::getPosition)
				.collect(Collectors.toList());
	}

}
