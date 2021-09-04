package thor;
import java.util.Scanner;

import thor.entities.Giant;
import thor.entities.Thor;
import thor.gamemap.GameMap;

public class Solver {
	private static final int MAP_WIDTH = 40;
	private static final int MAP_HEIGHT = 18;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		GameMap map = parseFirstTurnInputs(in);
		Thor thor = map.getThor();

		// game loop
		while (true) {
			parseTurnInputs(in, map);
			thor.playNextAction();
		}
	}

	private static GameMap parseFirstTurnInputs(Scanner in) {
		Thor thor = new Thor(in.nextInt(), in.nextInt(), in.nextInt());
		GameMap map = new GameMap(MAP_WIDTH, MAP_HEIGHT);
		map.placeThor(thor);
		thor.setMap(map);

		int giantCount = in.nextInt();
		for (int i = 0; i < giantCount; i++) {
			Giant giant = new Giant(in.nextInt(), in.nextInt());
			map.placeGiant(giant);
		}

		return map;
	}

	private static void parseTurnInputs(Scanner in, GameMap map) {
		Thor thor = map.getThor();
		thor.setAllowedHammerStrikes(in.nextInt()); // the remaining number of hammer strikes.

		map.removeAllGiants();
		int giantCount = in.nextInt(); // the number of giants which are still present on the map.
		for (int i = 0; i < giantCount; i++) {
			Giant giant = new Giant(in.nextInt(), in.nextInt());
			map.placeGiant(giant);
		}
	}
}
