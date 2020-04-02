package com.visiable.convert.dijkstra;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import com.gml.multilayered.StateMember;
import com.gml.multilayered.TransitionMember;
import com.gml.primalspace.Pos;



public class Dijkstra {
	final private String spaceLayerId;

	final private List<StateMember> stateMembers;
	final private List<TransitionMember> transitionMembers;

	final private List<Vertex> nodes;
	final private List<Edge> edges;

	final private Graph graph;
	final private DijkstraShortPath dijkstra;

	private Vertex start;
	private Vertex end;

	private LinkedList<Vertex> path;

	public Dijkstra(String spaceLayerId, List<StateMember> stateMembers, List<TransitionMember> transitionMembers) {
		this.spaceLayerId = spaceLayerId;
		this.stateMembers = stateMembers;
		this.transitionMembers = transitionMembers;
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();

		for (StateMember stateMember : stateMembers) {
			nodes.add(new Vertex(stateMember));
		}

		for (TransitionMember transitionMember : transitionMembers) {
			String firstStateId = transitionMember.getTransition().getConnects().get(0).getHref().replace("#", "");
			String secondStateId = transitionMember.getTransition().getConnects().get(1).getHref().replace("#", "");
			Vertex firstNode = null;
			Vertex secondNode = null;

			for (Vertex node : nodes) {
				if (node.getStateId().equals(firstStateId))
					firstNode = node;
				if (node.getStateId().equals(secondStateId))
					secondNode = node;
			}

			List<Pos> pos = transitionMember.getTransition().getGeometry().getLineString().getPos();

			double weight = getDistance(pos.get(0).getX(), pos.get(0).getY(), pos.get(1).getX(), pos.get(1).getY());

			edges.add(new Edge(transitionMember.getTransition().getId(), firstNode, secondNode, weight));
		}

		graph = new Graph(nodes, edges);
		dijkstra = new DijkstraShortPath(graph);

		start = end = null;
	}

	public String getSpaceLayerId() {
		return spaceLayerId;
	}

	public List<StateMember> getStateMembers() {
		return stateMembers;
	}

	public List<TransitionMember> getTransitionMembers() {
		return transitionMembers;
	}

	public boolean setStartEnd(String startId, String endId) {
		for (Vertex node : nodes) {
			if (node.getStateId().equals(startId)) {
				start = node;
			}
			if (node.getStateId().equals(endId)) {
				end = node;
			}
		}
		dijkstra.execute(start);
		path = dijkstra.getPath(end);
		
		if (path != null) return true;
		return false;
	}

	public Vertex getStart() {
		return start;
	}

	public Vertex getEnd() {
		return end;
	}

	public void printResult() {
		if (path == null) {
			System.out.println("wrong setting");
			return;
		}
		List<String> route = new ArrayList<String>();
		System.out.println("Start State : " + start + " End State : " + end);
		for (Vertex vertex : path) {
			route.add(vertex.toString());
			System.out.println(vertex.getStateMember().getState().getGeometry().getPoint().getPos().get(0).getVector());
		}
	}
	
	public JSONObject getResult() {
		JSONObject result = new JSONObject();
		List<String> route_state = new ArrayList<String>();
		List<String> route_point = new ArrayList<String>();
		for (Vertex vertex : path) {
			route_state.add(vertex.toString());
			String point = "";
			point = point + vertex.getStateMember().getState().getGeometry().getPoint().getPos().get(0).getX() +","+ vertex.getStateMember().getState().getGeometry().getPoint().getPos().get(0).getY(); 
			route_point.add(point);
		}

		result.put("start", start);
		result.put("end",end);
		result.put("route_state",route_state);
		result.put("route_point",route_point);
		
		
		return result;
	}

	private double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}
