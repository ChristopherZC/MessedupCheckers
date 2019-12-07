package position;

public interface Position {
	
	
	public int getX();
	public int getY();
	
	default public boolean equals(Position p) {
		return (getX() == p.getX()) && (getY() == p.getY());
	}
	default public int distance(Position p) {
		return Math.abs(getX()-p.getX())+Math.abs(getY()-p.getY());
	}
	public boolean isOutofBounds(); // isOutofBounds and between should be made into default method
	
	public Position between(Position p);
}
