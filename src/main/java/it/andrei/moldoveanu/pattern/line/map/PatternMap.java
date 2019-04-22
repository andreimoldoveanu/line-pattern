package it.andrei.moldoveanu.pattern.line.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PatternMap {

	// all points in the space
	private static List<Point> pointsInSpace = new ArrayList<>();
	// mapping of various point patterns
	private static Map<StraightLine, Set<Point>> pattern = new HashMap<>();

	/**
	 * add point in space and calculate the pattern with the other ones
	 * 
	 * @param point
	 * @return
	 */
	public static String addPoint(Point point) {
		if (pointsInSpace.size() == 0) {
			pointsInSpace.add(point);
			return "OK";
		}
		// if the point exists, don't add and return error
		if (pointsInSpace.contains(point)) {
			return "KO, point even is in the graph";
		}

		// control for every point, if it passes in one of the lines in the Map
		for (Point pointToCompare : pointsInSpace) {
			// calculate line equation
			StraightLine sl = new StraightLine(pointToCompare, point);
			// if there is a key on pattern map, add element on the set of points
			if (!pattern.containsKey(sl)) {
				Set<Point> setPoints = new HashSet<>();
				setPoints.add(pointToCompare);
				pattern.put(sl, setPoints);
			}
			pattern.get(sl).add(point);
		}

		pointsInSpace.add(point);
		return "OK";

	}

	/**
	 * Get all the lines with minimum n points in space shared
	 * 
	 * @param minimumPoints
	 * @return filtered map of patterns
	 */
	public static Map<Object, Object> getPatterns(Integer minimumPoints) {
		return pattern.entrySet().stream().filter(e -> e.getValue().size() >= minimumPoints)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	}

	public static List<Point> getSpace() {
		return pointsInSpace;
	}

	public static void deleteSpace() {
		pointsInSpace.clear();
		pattern.clear();
	}
}
