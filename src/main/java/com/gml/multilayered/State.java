package com.gml.multilayered;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

import com.gml.primalspace.BoundedBy;
import com.gml.primalspace.XmlLink;

public class State {
	private String id;
	private String description;
	private Name name;
	private BoundedBy boundedBy;
	private XmlLink duality;
	private List<XmlLink> connects;
	private Geometry geometry;

	public String getId() {
		return id;
	}

	@XmlAttribute(name = "id", namespace = "http://www.opengis.net/gml/3.2")
	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name = "description", namespace = "http://www.opengis.net/gml/3.2")
	public void setDescription(String description) {
		this.description = description;
	}

	public Name getName() {
		return name;
	}

	@XmlElement(name = "name", namespace = "http://www.opengis.net/gml/3.2")
	public void setName(Name name) {
		this.name = name;
	}

	public BoundedBy getBoundedBy() {
		return boundedBy;
	}

	@XmlElement(name = "boundedBy", namespace = "http://www.opengis.net/gml/3.2")
	public void setBoundedBy(BoundedBy boundedBy) {
		this.boundedBy = boundedBy;
	}

	public XmlLink getDuality() {
		return duality;
	}

	@XmlElement(name = "duality", namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setDuality(XmlLink duality) {
		this.duality = duality;
	}

	public List<XmlLink> getConnects() {
		return connects;
	}
	
	@XmlElement(name = "connects", namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setConnects(List<XmlLink> connects) {
		this.connects = connects;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	@XmlElement(name = "geometry", namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

}
