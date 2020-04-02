package com.gml.primalspace;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gml.multilayered.MultiLayeredGraph;

@SuppressWarnings("restriction")
@XmlRootElement(name = "IndoorFeatures")
public class IndoorFeatures implements Serializable  {
	private String id;
	private String schemaLocation;
	private BoundedBy boundedBy;
	private PrimalSpaceFeatures primalSpaceFeatures;
	private MultiLayeredGraph multiLayeredGraph;
	
	public IndoorFeatures() {
		//this.schemaLocation = "http://www.opengis.net/indoorgml/1.0/core http://schemas.opengis.net/indoorgml/1.0/indoorgmlcore.xsd http://www.opengis.net/indoorgml/1.0/navigation http://schemas.opengis.net/indoorgml/1.0/indoorgmlnavi.xsd";
		//this.boundedBy = new BoundedBy();
		//this.primalSpaceFeatures = new PrimalSpaceFeatures(true,"lf5cd61d-1963-42c4-adf6-179ba51b28d5");
		//this.multiLayeredGraph = new MultiLayeredGraph();
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
