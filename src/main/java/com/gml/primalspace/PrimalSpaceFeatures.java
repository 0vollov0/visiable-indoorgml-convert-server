package com.gml.primalspace;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class PrimalSpaceFeatures {
	private String id;
	private PrimalSpaceFeatures primalSpaceFeatures;
	private BoundedBy boundedBy;
	//private CellSpaceMember cellSpaceMember;
	private List<CellSpaceMember> cellSpaceMember;

	public PrimalSpaceFeatures() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	@XmlAttribute(name = "id", namespace = "http://www.opengis.net/gml/3.2")
	public void setId(String id) {
		this.id = id;
	}

	public PrimalSpaceFeatures getPrimalSpaceFeatures() {
		return primalSpaceFeatures;
	}

	@XmlElement(name = "PrimalSpaceFeatures", namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setPrimalSpaceFeatures(PrimalSpaceFeatures primalSpaceFeatures) {
		this.primalSpaceFeatures = primalSpaceFeatures;
	}

	public void attachPrimalSpaceFeatures(PrimalSpaceFeatures primalSpaceFeatures) {
		this.primalSpaceFeatures = primalSpaceFeatures;
	}

	public BoundedBy getBoundedBy() {
		return boundedBy;
	}
	@XmlElement(name = "boundedBy", namespace = "http://www.opengis.net/gml/3.2")
	public void setBoundedBy(BoundedBy boundedBy) {
		this.boundedBy = boundedBy;
	}

//	public CellSpaceMember getCellSpaceMember() {
//		return cellSpaceMember;
//	}
//	@XmlElement(name = "cellSpaceMember", namespace = "http://www.opengis.net/indoorgml/1.0/core")
//	public void setCellSpaceMember(CellSpaceMember cellSpaceMember) {
//		this.cellSpaceMember = cellSpaceMember;
//	}
	
	
	public List<CellSpaceMember> getCellSpaceMember() {
		return cellSpaceMember;
	}
	
	@XmlElement(name = "cellSpaceMember", namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setCellSpaceMember(List<CellSpaceMember> cellSpaceMember) {
		this.cellSpaceMember = cellSpaceMember;
	}

	@Override
	public String toString() {
		return "PrimalSpaceFeatures [id=" + id + ", primalSpaceFeatures=" + primalSpaceFeatures + ", boundedBy="
				+ boundedBy + ", cellSpaceMember=" + cellSpaceMember + "]";
	}

	
	
}
