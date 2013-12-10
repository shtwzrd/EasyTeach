package com.easyTeach.common.entity;

import java.util.HashSet;

public class ResourceSet extends HashSet<Resource> implements Resource {

	private static final long serialVersionUID = -3049950630415788611L;

	@Override
	public String getName() {
		return "ResourceSet";
	}

}
