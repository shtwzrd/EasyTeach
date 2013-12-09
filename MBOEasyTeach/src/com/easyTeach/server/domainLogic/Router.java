package com.easyTeach.server.domainLogic;

import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.Resource;
import com.easyTeach.common.network.resource.RoleResource;
import com.easyTeach.common.network.resource.RoleResource.Role;

public final class Router {

	private Router() {

	}

	public static void main(String[] args) {
		Resource resource = new RoleResource((Role.STUDENT));

		System.out.println(resource.getClass().getName());
	}

	public static Response getResponse(Request toRoute) {
		Action action = toRoute.getAction();
		Resource resource = toRoute.getResource();
		Role role = toRoute.getRole();

		switch (role.toString()) {
		case "TEACHER":
			switch (resource.getName()) {
			case "Tag":
				switch (action.getType().toString()) {
				case "CREATE":
					return TagResourceRules.addTag(resource);
					break;
				case "READ":
					switch (action.getAttribute()) {
					case "all":
						return TagResourceRules.getAllTags(resource);
						break;
					}
				}

			}
		}

		return new Response(ResponseStatus.SUCCESS);
	}
}
