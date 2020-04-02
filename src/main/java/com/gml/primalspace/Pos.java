package com.gml.primalspace;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Pos {
	private String srsDimension;
	private String vector;

	private Double x;
	private Double y;
	private Double z;

	public String getSrsDimension() {
		return srsDimension;
	}

	@XmlAttribute(name = "srsDimension")
	public void setSrsDimension(String srsDimension) {
		this.srsDimension = srsDimension;
	}

	public String getVector() {
		return vector;
	}

	public String getSmallCommaVector() {
		return x + ", " + y;
	}

	@XmlValue
	public void setVector(String vector) {
		this.vector = vector;

		String[] splitedVector = vector.split(" ");

		this.x = Double.parseDouble(splitedVector[0]);
		this.y = Double.parseDouble(splitedVector[1]);
		this.z = Double.parseDouble(splitedVector[2]);
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public Double getZ() {
		return z;
	}
	
	public void changeX(Double x) {
		this.x = x;
	}
	
	public void changeY(Double y) {
		this.y = y;
	}
	
	public void changeZ(Double z) {
		this.z = z;
	}
	
	public void changeVector() {
		this.vector = x + " " + y + " " + z;
	}

}
