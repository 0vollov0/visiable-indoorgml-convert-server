package com.gml.primalspace;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class PrimalSpaceFeatures2 {
	private String id;
	private PrimalSpaceFeatures2 primalSpaceFeatures;
	private BoundedBy boundedBy;
	private CellSpaceMember cellSpaceMember;

	public PrimalSpaceFeatures2() {
		// TODO Auto-generated constructor stub
	}

	public PrimalSpaceFeatures2(boolean parent, String id) {
		this.primalSpaceFeatures = new PrimalSpaceFeatures2("lf5cd61d-1963-42c4-adf6-179ba51b28d5");
	}

	public PrimalSpaceFeatures2(String id) {
		this.id = id;
		this.boundedBy = new BoundedBy();
	}

	public String getId() {
		return id;
	}

	@XmlAttribute(name = "id", namespace = "http://www.opengis.net/gml/3.2")
	public void setId(String id) {
		this.id = id;
	}

	public PrimalSpaceFeatures2 getPrimalSpaceFeatures() {
		return primalSpaceFeatures;
	}

	@XmlElement(name = "PrimalSpaceFeatures", namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setPrimalSpaceFeatures(PrimalSpaceFeatures2 primalSpaceFeatures) {
		this.primalSpaceFeatures = primalSpaceFeatures;
	}

	public void attachPrimalSpaceFeatures(PrimalSpaceFeatures2 primalSpaceFeatures) {
		this.primalSpaceFeatures = primalSpaceFeatures;
	}

	public BoundedBy getBoundedBy() {
		return boundedBy;
	}
	@XmlElement(name = "boundedBy", namespace = "http://www.opengis.net/gml/3.2")
	public void setBoundedBy(BoundedBy boundedBy) {
		this.boundedBy = boundedBy;
	}

	public CellSpaceMember getCellSpaceMember() {
		return cellSpaceMember;
	}
	@XmlElement(name = "cellSpaceMember", namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setCellSpaceMember(CellSpaceMember cellSpaceMember) {
		this.cellSpaceMember = cellSpaceMember;
	}

	@Override
	public String toString() {
		return "PrimalSpaceFeatures [id=" + id + ", primalSpaceFeatures=" + primalSpaceFeatures + ", boundedBy="
				+ boundedBy + ", cellSpaceMember=" + cellSpaceMember + "]";
	}
	
	
}
