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
 * logic) for the tags to the @Response class.
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

	private TagResourceRules() {
	}

	/**
	 * Sends a respond of whether or not a tag has been added.
	 * 
	 * @param tag
	 *            An instance of TagResource.
	 * @return A response of either type success or failure and the new added
	 *         Tag if successful.
	 * @see TagWrapper
	 */
	public static Response addTag(TagResource tag) {
		Tag tagEntity = new Tag();
		tagEntity.setTagNo(tag.getTagNo()); // Is this supposed to be here?
		tagEntity.setTag(tag.getTag());

		if (tagEntity != null && tagEntity.equals(tag)) {
			return new Response(ResponseStatus.SUCCESS, new TagResource(
					tagEntity.getTagNo(), tagEntity.getTag()));
		} else {
			return new Response(ResponseStatus.FAILURE);
		}
	}

	/**
	 * Sends a respond of whether or not a tag has been found.
	 * 
	 * @param tag
	 *            An instance of TagResources.
	 * @return A response of either type success or failure and returns the
	 *         tagResource if successful.
	 * @see TagWrapper
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

	/**
	 * Sends a respond of whether or not all tags has been found.
	 * 
	 * @param tag
	 *            An instance of TagResources.
	 * @return A response of either type success or failure and returns a list
	 *         of all the tagResources if successful.
	 * @see TagWrapper
	 */

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

	/**
	 * Sends a respond of whether or not a tag has been removed.
	 * 
	 * @param tag
	 *            An instance of TagResources.
	 * @return A response of either type success or failure.
	 * @see TagWrapper
	 */

	public static Response removeTag(TagResource tag) {
		if (TagWrapper.deleteTagRow(tag.getTagNo())) {
			return new Response(ResponseStatus.SUCCESS);
		} else {
			return new Response(ResponseStatus.FAILURE);
		}
	}

	/**
	 * Sends a respond of whether or not a tag has been updated.
	 * 
	 * @param tag
	 *            An instance of TagResources.
	 * @return A response of either type success or failure and returns the
	 *         newly updated tag.
	 * @see TagWrapper
	 */

	public static Response updateTag(TagResource tag) {
		Tag tagEntity = new Tag();
		tagEntity.setTagNo(tag.getTagNo());
		tagEntity.setTag(tag.getTag());
		if (tagEntity != null && !tagEntity.equals(tag)) {
			return new Response(ResponseStatus.SUCCESS, new TagResource(
					tagEntity.getTagNo(), tagEntity.getTag()));
		} else {
			return new Response(ResponseStatus.FAILURE);
		}
	}
	
	/**
	 * A method for converting a Tag into a TagResource.
	 * 
	 * @param tag an instance of Tag
	 * @return a tag that have been converted into a TagResource
	 * @see Tag
	 * @see TagResource
	 */

	private static TagResource convert(Tag tag) {
		TagResource tagResource = new TagResource(tag.getTagNo(), tag.getTag());
		return tagResource;
	}

	/**
	 * A method for converting a list of Tags into TagResources.
	 * 
	 * @param tag A HashSet of tags of type Tag
	 * @return A list of tags that have been converted into TagResources.
	 * @see Tag
	 * @see TagResource
	 */
	private static HashSet<TagResource> convertList(HashSet<Tag> tag) {
		HashSet<TagResource> tagResourceList = new HashSet<TagResource>();
		for (Tag t : tag) {
			tagResourceList.add(convert(t));
		}
		return tagResourceList;
	}

}
