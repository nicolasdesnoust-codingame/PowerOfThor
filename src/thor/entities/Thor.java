package thor.entities;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import thor.actions.Action;
import thor.actions.MoveAction;
import thor.actions.StrikeAction;
import thor.actions.WaitAction;
import thor.gamemap.Cell;
import thor.gamemap.GameMap;
import thor.math.Direction;
import thor.math.Point;
import thor.math.Rectangle;

public class Thor extends Entity {
	private int allowedHammerStrikes;
	private GameMap map;

	public Thor(int x, int y, int allowedHammerStrikes) {
		super(x, y);
		this.setAllowedHammerStrikes(allowedHammerStrikes);
	}

	public void playNextAction() {
		Action action = findBestPossibleAction();
		action.play();
	}

	private Action findBestPossibleAction() {
		Collection<Point> giantPositions = toPositions(map.getGiants());
		Rectangle boundingBox = Rectangle.getBoundingBoxOf(giantPositions);
		Point boundingBoxCenter = boundingBox.getCenter();
		if((Math.abs(boundingBox.getTopLeftCorner().getX() - boundingBox.getBottomRightCorner().getX())) < 3
		|| (Math.abs(boundingBox.getTopLeftCorner().getY() - boundingBox.getBottomRightCorner().getY())) < 3) {
			List<Point> doablePositions = findDoablePositionTo(boundingBoxCenter);
			if(doablePositions.size() >= 2) {
				return new MoveAction(this, doablePositions.get(1));
			}
		}
		Optional<Point> targetPosition = findClosestSafePositionTo(boundingBoxCenter);
		if (targetPosition.isPresent()) {
			return new MoveAction(this, targetPosition.get());
		} else if (isNotInDanger()) {
			return new WaitAction();
		} else {
			return new StrikeAction();
		}
	}

	private List<Point> findDoablePositionTo(Point boundingBoxCenter) {
		List<Cell> cells = map.getCellsAround(boundingBoxCenter);

		Comparator<Point> byManhattanDistance = Comparator
				.comparingInt(point -> Point.calculateManhattanDistance(point, boundingBoxCenter));

		return cells.stream()
				.filter(Cell::doesNotContainsAGiant)
				.filter(map::isNotNearAGiant)
				.map(Cell::getPosition)
				.sorted(byManhattanDistance)
				.collect(Collectors.toList());
	}

	// regarder les cases autour de thor
	// eviter celles qui contiennent un géant ou qui sont à une case d'un géant
	// prendre celle la plus proche de boundingboxcenter
	private Optional<Point> findClosestSafePositionTo(Point boundingBoxCenter) {
		List<Cell> cells = map.getCellsAround(boundingBoxCenter);

		Comparator<Point> byManhattanDistance = Comparator
				.comparingInt(point -> Point.calculateManhattanDistance(point, boundingBoxCenter));

		return cells.stream()
				.filter(Cell::doesNotContainsAGiant)
				.filter(map::isNotNearAGiant)
				.map(Cell::getPosition)
				.min(byManhattanDistance);
	}

	private boolean isNotInDanger() {
		return map.isNotNearAGiant(position);
	}
	
	public void moveIn(Direction direction) {
		int x = position.getX() + direction.xMove;
		int y = position.getY() + direction.yMove;
		
		Point newPosition = new Point(x, y);
		map.moveThorTo(newPosition);
		setPosition(newPosition);
	}
	
	public int getAllowedHammerStrikes() {
		return allowedHammerStrikes;
	}

	public void setAllowedHammerStrikes(int allowedHammerStrikes) {
		this.allowedHammerStrikes = allowedHammerStrikes;
	}

	public void setMap(GameMap map) {
		this.map = map;
	}

}
