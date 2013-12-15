package com.easyTeach.common.entity;

import java.util.HashSet;

public class ResourceSet extends HashSet<Resource> implements Resource {

	private static final long serialVersionUID = -3049950630415788611L;

	@Override
	public String getName() {
		Object[] r = this.toArray();
		if (r != null && r.length != 0) {
			Resource t = (Resource) r[0];
			return t.getName();
		}
		return "ResourceSet";
	}

}
