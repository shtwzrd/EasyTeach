package com.easyTeach.common.entity;

/**
 * <p>
 * The ClassCourseRelation class represents a row from the ClassCourseRelation
 * table in the easyTeach database. The class contains getters and setters for
 * the two attributes derived from the ClassCourseRelation table, namely;
 * classNo and courseNo. The table maps the relation between Classes and
 * Courses.
 * </p>
 * 
 * <p>
 * In the database the ClassCourseRelation table's primary key is the composite
 * of classNo and courseNo.
 * </p>
 * 
 * <p>
 * In the database the ClassCourseRelation table references the primary key of
 * the Class and Course tables.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 26. November, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters/setters).
 */

public class ClassCourseRelation implements Resource {

	private static final long serialVersionUID = 6937447384747520214L;
	private String classNo;
	private String courseNo;

	public ClassCourseRelation() {

	}

	public ClassCourseRelation(String classNo, String courseNo) {
		this.classNo = classNo;
		this.courseNo = courseNo;
	}

	public String getClassNo() {
		return this.classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getCourseNo() {
		return this.courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	@Override
	public String getName() {
		return "ClassCourseRelation";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		ClassCourseRelation relation = (ClassCourseRelation) obj;
		return this.courseNo.equals(relation.courseNo)
				&& (this.classNo.equals(relation.classNo));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.courseNo == null) ? 0 : this.courseNo.hashCode());
		result = prime * result + this.classNo.hashCode();
		return result;
	}

}
