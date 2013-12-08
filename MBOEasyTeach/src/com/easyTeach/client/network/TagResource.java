package com.easyTeach.client.network;

import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.network.Resource;

/**
 * <p>
 * The TagResource class is for receiving or sending relevant information from
 * or to our server about a tag.
 * </p>
 * 
 * @author Oliver Nielsen
 * @version 0.2
 * @date 8. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters).
 */

public class TagResource implements Resource {

	private static final long serialVersionUID = 6795562493975090180L;

	private String tag;

	/**
	 * <p>
	 * Constructor for receiving information for a tag from the server side of
	 * the application.
	 * </p>
	 * 
	 * <p>
	 * The constructor is also used for sending information for a tag to the
	 * server side of the application.
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

	public String getTag() {
		return tag;
	}

}
