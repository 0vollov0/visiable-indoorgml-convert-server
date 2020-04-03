package com.gml.primalspace;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gml.multilayered.MultiLayeredGraph;

@XmlRootElement(name = "IndoorFeatures")
public class IndoorFeatures  {
	private String id;
	private String schemaLocation;
	private BoundedBy boundedBy;
	private PrimalSpaceFeatures primalSpaceFeatures;
	private MultiLayeredGraph multiLayeredGraph;
	
	
	private String navi;
	private String nonnavi;
	private String ns6;
	private String ns7;
	
	public IndoorFeatures() {
	}


	public String getId() {
		return id;
	}

	@XmlAttribute(name = "id",namespace = "http://www.opengis.net/gml/3.2")
	public void setId(String id) {
		this.id = id;
	}

	public String getSchemaLocation() {
		return schemaLocation;
	}

	@XmlAttribute(name = "schemaLocation", namespace = "http://www.w3.org/2001/XMLSchema-instance")
	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}
	

	public String getNavi() {
		return navi;
	}

	@XmlAttribute(name = "navi", namespace = "http://www.opengis.net/indoorgml/1.0/navigation")
	public void setNavi(String navi) {
		this.navi = navi;
	}


	public String getNonnavi() {
		return nonnavi;
	}

	@XmlAttribute(name = "nonnavi", namespace = "http://indoorgml.net/repository/NonNaviSpace")
	public void setNonnavi(String nonnavi) {
		this.nonnavi = nonnavi;
	}


	public String getNs6() {
		return ns6;
	}

	@XmlAttribute(name = "ns6", namespace = "http://indoorgml.net/extensions/PSExt")
	public void setNs6(String ns6) {
		this.ns6 = ns6;
	}


	public String getNs7() {
		return ns7;
	}

	@XmlAttribute(name = "ns7", namespace = "http://indoorgml.net/extensions/textureext")
	public void setNs7(String ns7) {
		this.ns7 = ns7;
	}


	public BoundedBy getBoundedBy() {
		return boundedBy;
	}


	@XmlElement(name = "boundedBy",namespace = "http://www.opengis.net/gml/3.2")
	public void setBoundedBy(BoundedBy boundedBy) {
		this.boundedBy = boundedBy;
	}
	
	public PrimalSpaceFeatures getPrimalSpaceFeatures() {
		return primalSpaceFeatures;
	}
	
	@XmlElement(name = "primalSpaceFeatures",namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setPrimalSpaceFeatures(PrimalSpaceFeatures primalSpaceFeatures) {
		this.primalSpaceFeatures = primalSpaceFeatures;
	}

	
	public MultiLayeredGraph getMultiLayeredGraph() {
		return multiLayeredGraph;
	}

	@XmlElement(name = "multiLayeredGraph",namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setMultiLayeredGraph(MultiLayeredGraph multiLayeredGraph) {
		this.multiLayeredGraph = multiLayeredGraph;
	}


	@Override
	public String toString() {
		return "IndoorFeatures [id=" + id + ", schemaLocation=" + schemaLocation + ", boundedBy=" + boundedBy
				+ ", primalSpaceFeatures=" + primalSpaceFeatures + ", multiLayeredGraph=" + multiLayeredGraph + "]";
	}
	
	
	
}

