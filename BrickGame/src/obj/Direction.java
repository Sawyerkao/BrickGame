package obj;

public enum Direction {
	North,
	NorthEast,
	East,
	SouthEast,
	South,
	SouthWest,
	West,
	NorthWest,
	Center;
	
	public static final Direction table[][]= {
		{ NorthWest,  North, NorthEast},
		{      West, Center,      East},
		{ SouthWest,  South, SouthEast}
	};
	
	public static final String toString[] = {
		"North",
		"NorthEast",
		"East",
		"SouthEast",
		"South",
		"SouthWest",
		"West",
		"NorthWest",
		"Center"
	};
}
