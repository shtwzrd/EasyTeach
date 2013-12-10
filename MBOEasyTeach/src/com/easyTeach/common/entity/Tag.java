package com.easyTeach.common.entity;

/**
 * <p>
 * The Tag class represents a row from the Tag table in the easyTeach database.
 * The class contains getters and setters for the two attributes derived from
 * the Tag table, namely; tagNo and tag
 * </p>
 * 
 * <p>
 * In the database the primary key of the Tag table is the tagNo.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters/setters).
 */

public class Tag implements Resource {

	private static final long serialVersionUID = 2838453778906794818L;
	private String tagNo; // INTEGER in DB
	private String tag;

	public Tag() {

	}

	public Tag(String tagNo, String tag) {
		this.tagNo = tagNo;
		this.tag = tag;
	}

	public String getTagNo() {
		return tagNo;
	}

	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String getName() {
		return "Tag";
	}

}
