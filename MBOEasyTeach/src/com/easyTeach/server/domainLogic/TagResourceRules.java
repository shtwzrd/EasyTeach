package com.easyTeach.server.domainLogic;

import java.util.HashSet;

import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.resource.TagResource;
import com.easyTeach.server.databaseWrapper.TagWrapper;

/**
 * <p>
 * The TagResourceRules class returns all needed domain logic (or business
 * logic) to the @Response class.
 * </p>
 * 
 * @author Tonni Hyldgaard
 * @version 0.1
 * @see Tag
 * @see TagResource
 * @see TagWrapper
 * @see Response
 * @data 9. December, 2013.
 */

public class TagResourceRules {

	private TagResourceRules() {}

	/**
	 * 
	 * @param tag
	 * @return
	 */
	public static Response addTag(TagResource tag) {
		return null;
	}

	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param tag
	 * @return
	 */
	
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
		if (TagWrapper.deleteTagRow(tag.getTagNo())) {
			return new Response(ResponseStatus.SUCCESS);
		} else {
			return new Response(ResponseStatus.FAILURE);
		}
	}

	public static Response updateTag(TagResource tag) {
		if (TagWrapper.updateTagRow(new Tag(tag.))) {
			return new Response()
		}
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
