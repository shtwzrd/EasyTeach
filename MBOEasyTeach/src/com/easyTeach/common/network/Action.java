package com.easyTeach.common.network;

import java.io.Serializable;

public final class Action implements Serializable {

	private static final long serialVersionUID = -6452781884558430146L;
	private ActionType type;
	private String attribute;
	
	public Action(ActionType type) {
		this.type = type;
		attribute = "";
	}
	
	public Action(ActionType type, String attribute) {
		this.type = type;
		this.attribute = attribute;
	}
	
	public ActionType getType() {
		return type;
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public enum ActionType {
		CREATE, READ, UPDATE, DELETE, CLOSE;
	}

}
