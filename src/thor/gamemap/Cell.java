package thor.gamemap;

import thor.entities.Entity;
import thor.entities.Giant;
import thor.math.Point;

public class Cell {
	private final Point position;
	private Entity content; // Careful! Multiple giants can stand on a cell

	public Cell(Point position) {
		this.position = position;
	}

	public boolean doesNotContainsAGiant() {
		return !(content instanceof Giant);
	}

	public Point getPosition() {
		return position;
	}

	public Entity getContent() {
		return content;
	}

	public void placeContent(Entity content) {
		this.content = content;
	}

	public void free() {
		this.content = null;
	}
}
