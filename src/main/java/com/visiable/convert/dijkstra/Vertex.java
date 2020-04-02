package com.visiable.convert.dijkstra;

import com.gml.multilayered.StateMember;

public class Vertex {
	final private StateMember stateMember;

	public Vertex(StateMember stateMember) {
		this.stateMember = stateMember;
	}

	public StateMember getStateMember() {
		return stateMember;
	}

	public String getStateId() {
		return stateMember.getState().getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((stateMember.getState().getId() == null) ? 0 : stateMember.getState().getId().hashCode());
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
		Vertex other = (Vertex) obj;
		if (stateMember.getState().getId() == null) {
			if (stateMember.getState().getId() != null)
				return false;
		} else if (!stateMember.getState().getId().equals(other.stateMember.getState().getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return stateMember.getState().getId();
	}

}