package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.TagResource;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.RoleResource;
import com.easyTeach.server.databaseWrapper.TagWrapper;

public class TagResourceRules {

	private TagResourceRules() {

	}

	public static Response addTag(TagResource tag) {

	}

	public static Response getTag(TagResource tag) {
		Tag tagEntity = TagWrapper.getTagRowWithTagNo(tag.getTagNo());

		if (tagEntity != null) {
			TagResource tagResource = convert(tagEntity);
			return new Response(ResponseStatus.SUCCESS, tagResource);
		} else { // We couldn't get that tag!
			return new Response(ResponseStatus.FAILURE);
		}

	}

	public static Response getAllTags(TagResource tag) {
	}

	public static Response removeTag(TagResource tag) {
	}

	public static Response updateTag(TagResource tag) {
	}
	
	private static TagResource convert(Tag tag) {
		TagResource tagResource = new TagResource(tag.getTagNo(), tag.getTag());
		return tagResource;
	}

}
