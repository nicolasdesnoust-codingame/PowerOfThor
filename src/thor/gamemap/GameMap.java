package thor.gamemap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import thor.entities.Entity;
import thor.entities.Giant;
import thor.entities.Thor;
import thor.math.Point;

public class GameMap {
	private final Cell[][] map;
	private final int width;
	private final int height;
	private final List<Entity> giants;
	private Thor thor;

	public GameMap(int width, int height) {
		giants = new ArrayList<>();

		this.width = width;
		this.height = height;
		map = new Cell[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				map[x][y] = new Cell(new Point(x, y));
			}
		}
	}

	public void placeThor(Thor thor) {
		placeEntity(thor);
		this.thor = thor;
	}

	public void placeGiant(Giant giant) {
		placeEntity(giant);
		giants.add(giant);
	}

	private void placeEntity(Entity entity) {
		int entityX = entity.getPosition().getX();
		int entityY = entity.getPosition().getY();

		map[entityX][entityY].placeContent(entity);
	}

	public void moveThorTo(Point position) {
		int thorX = thor.getPosition().getX();
		int thorY = thor.getPosition().getY();

		map[thorX][thorY].free();
		map[position.getX()][position.getY()].placeContent(thor);
	}

	public void removeAllGiants() {
		giants.forEach(giant -> map[giant.getPosition().getX()][giant.getPosition().getY()].free());
		giants.clear();
	}

	public boolean isNotNearAGiant(Cell source) {
		return isNotNearAGiant(source.getPosition());
	}

	public boolean isNotNearAGiant(Point source) {
		return getCellsAround(source).stream()
				.allMatch(Cell::doesNotContainsAGiant);
	}

	public List<Cell> getCellsAround(Point source) {
		int sourceX = source.getX();
		int sourceY = source.getY();

		List<Cell> cellsAround = new ArrayList<>(8);
		for (int x = sourceX - 1; x <= sourceX + 1; x++) {
			for (int y = sourceY - 1; y <= sourceY + 1; y++) {
				if (cellExists(x, y) && (x != sourceX || y != sourceY)) {
					cellsAround.add(map[x][y]);
				}
			}
		}

		return cellsAround;
	}

	private boolean cellExists(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	public Collection<Entity> getGiants() {
		return giants;
	}

	public Thor getThor() {
		return thor;
	}
}
