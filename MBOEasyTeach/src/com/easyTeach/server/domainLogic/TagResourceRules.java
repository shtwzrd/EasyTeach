package com.easyTeach.server.domainLogic;

import java.util.HashSet;

import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.TagResource;
import com.easyTeach.server.databaseWrapper.TagWrapper;

public class TagResourceRules {

	private TagResourceRules() {

	}

	public static Response addTag(TagResource tag) {
		return null;
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
		HashSet<Tag> tagEntities = TagWrapper.getTagRows();

		if (tagEntities != null) {
			HashSet<TagResource> tagResource = convertList(tagEntities);
			return new Response(ResponseStatus.SUCCESS, new TagResource(
					tagResource));
		} else {
			return new Response(ResponseStatus.FAILURE);
		}
	}

	public static Response removeTag(TagResource tag) {
		
	}

	public static Response updateTag(TagResource tag) {
	}

	private static TagResource convert(Tag tag) {
		TagResource tagResource = new TagResource(tag.getTagNo(), tag.getTag());
		return tagResource;
	}

	private static HashSet<TagResource> convertList(HashSet<Tag> tag) {
		HashSet<TagResource> tagResourceList = new HashSet<TagResource>();
		for (Tag t : tag) {
			tagResourceList.add(convert(t));
		}
		return tagResourceList;
	}

}
