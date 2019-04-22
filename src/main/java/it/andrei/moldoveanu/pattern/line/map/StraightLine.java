package it.andrei.moldoveanu.pattern.line.map;

public class StraightLine {

	private Point a;
	private Point b;
	
	private Double m;
	private Double q;
	
	/**
	 * create line with y=mx+q formula
	 * @param a
	 * @param b
	 */
	public StraightLine(Point a, Point b) {
		super();
		this.a = a;
		this.b = b;
		calculateLine();
	}
	
	private void calculateLine() {
		m= (b.getY() - a.getY())/(b.getX() - a.getX());
		q= -a.getX() * m + a.getY();
	}

	public Point getA() {
		return a;
	}

	public void setA(Point a) {
		this.a = a;
	}

	public Point getB() {
		return b;
	}

	public void setB(Point b) {
		this.b = b;
	}

	public Double getM() {
		return m;
	}

	public void setM(Double m) {
		this.m = m;
	}

	public Double getQ() {
		return q;
	}

	public void setQ(Double q) {
		this.q = q;
	}

	@Override
	public String toString() {
		return "Line [ y = " + m + " x + q=" + q + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		result = prime * result + ((q == null) ? 0 : q.hashCode());
		return result;
	}

	/**
	 * Override, needed to calculate equals just on "m" and "q"
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StraightLine other = (StraightLine) obj;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		if (q == null) {
			if (other.q != null)
				return false;
		} else if (!q.equals(other.q))
			return false;
		return true;
	}

	
	
}
