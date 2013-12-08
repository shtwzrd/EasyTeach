package com.easyTeach.server.domainLogic;

import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;

public final class Router {

	private Router() {}
	
	public static Response getResponse(Request toRoute) {
		return new Response(ResponseStatus.SUCCESS);
	}

}
