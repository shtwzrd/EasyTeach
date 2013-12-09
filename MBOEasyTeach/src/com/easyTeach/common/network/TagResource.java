package com.easyTeach.common.network;

import java.util.HashSet;

import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.network.resource.Resource;

/**
 * <p>
 * The TagResource class is for receiving or sending relevant information from
 * or to our server about a tag.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 1.0
 * @date 9. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class TagResource implements Resource {

	private static final long serialVersionUID = 6795562493975090180L;

	private String tagNo;
	private String tag;

	private HashSet<TagResource> tags;

	/**
	 * <p>
	 * This constructor for receiving information for a tag from the server side
	 * of the application.
	 * </p>
	 * 
	 * @param tag
	 *            The tag.
	 * 
	 * @see Tag
	 */
	public TagResource(String tag) {
		this.tag = tag;
	}

	/**
	 * <p>
	 * This constructor is used for sending information for a tag to the server
	 * side of the application.
	 * </p>
	 * 
	 * @param tagNo
	 *            The tag number.
	 * @param tag
	 *            The tag.
	 * 
	 * @see Tag
	 */
	public TagResource(String tagNo, String tag) {
		this.tagNo = tagNo;
		this.tag = tag;
	}

	/**
	 * <p>
	 * This constructor is used for sending information for a tag to the server
	 * side of the application.
	 * </p>
	 * 
	 * @param tags
	 *            A HashSet of all the tags.
	 * 
	 * @see Tag
	 */
	public TagResource(HashSet<TagResource> tags) {
		this.tags = tags;
	}

	public String getTagNo() {
		return tagNo;
	}

	public String getTag() {
		return tag;
	}

	public HashSet<TagResource> getTags() {
		return tags;
	}

}
