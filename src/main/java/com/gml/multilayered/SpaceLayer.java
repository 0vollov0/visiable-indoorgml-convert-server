package com.gml.multilayered;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.gml.primalspace.BoundedBy;
import com.gml.primalspace.Pos;
import com.visiable.convert.dijkstra.Dijkstra;

public class SpaceLayer {
	private String id;
	private BoundedBy boundedBy;
	private Nodes nodes;
	private Edges edges;
	
	private Dijkstra dijkstras;
	
	public String getId() {
		return id;
	}
	
	@XmlAttribute(name = "id", namespace = "http://www.opengis.net/gml/3.2")
	public void setId(String id) {
		this.id = id;
	}

	public BoundedBy getBoundedBy() {
		return boundedBy;
	}
	
	@XmlElement(name = "boundedBy", namespace = "http://www.opengis.net/gml/3.2")
	public void setBoundedBy(BoundedBy boundedBy) {
		this.boundedBy = boundedBy;
	}

	public Nodes getNodes() {
		return nodes;
	}
	@XmlElement(name = "nodes", namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}

	public Edges getEdges() {
		return edges;
	}
	@XmlElement(name = "edges", namespace = "http://www.opengis.net/indoorgml/1.0/core")
	public void setEdges(Edges edges) {
		this.edges = edges;
	}

	public Dijkstra getDijkstras() {
		return dijkstras;
	}

	public void setDijkstras() {
		this.dijkstras = new Dijkstra(this.id,nodes.getStateMember(),edges.getTransitionMember());
	}
	
	public double getStateDistance(String stateId1,String stateId2) {
		Pos pos1 = null;
		Pos pos2 = null;
		
		for (StateMember stateMember : nodes.getStateMember()) {
			if (stateMember.getState().getId().equals(stateId1)) pos1 = stateMember.getState().getGeometry().getPoint().getPos().get(0);
			if (stateMember.getState().getId().equals(stateId2)) pos2 = stateMember.getState().getGeometry().getPoint().getPos().get(0);
		}
		
		if (pos1 == null || pos2 == null) return 0;
		
		return getDistance(pos1.getX(),pos1.getY(),pos2.getX(),pos2.getY());
	}
	
	private double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

}
