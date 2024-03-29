package com.gml.primalspace;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Geometry2D {
	private Polygon polygon;

	public Polygon getPolygon() {
		return polygon;
	}
	
	@XmlElement(name = "Polygon", namespace = "http://www.opengis.net/gml/3.2")
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

}
