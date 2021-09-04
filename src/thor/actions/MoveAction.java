package thor.actions;

import thor.entities.Thor;
import thor.math.Direction;
import thor.math.Point;

public class MoveAction implements Action {

	private final Thor thor;
	private final Point destination;
	
	public MoveAction(Thor thor, Point destination) {
		this.thor = thor;
		this.destination = destination;
	}

	@Override
	public void play() {
		Direction direction = Direction.from(thor.getPosition(), destination);
		
		thor.moveIn(direction);
		
		System.out.println(direction);
	}

}
