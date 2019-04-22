package it.andrei.moldoveanu.pattern.line.map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Point{
	@JsonProperty("x")
    private Double x;
	@JsonProperty("y")
    private Double y;
    
    @JsonCreator
    public Point(@JsonProperty("x")Double x, @JsonProperty("y") Double y){
        this.x = x;
        this.y = y;
    }

	@JsonProperty("x")
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	@JsonProperty("y")
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
	
	
    
}