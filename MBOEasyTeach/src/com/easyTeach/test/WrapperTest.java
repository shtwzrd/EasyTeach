package com.easyTeach.test;

import java.util.HashSet;

import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.entity.ExerciseParameter;
import com.easyTeach.common.entity.User;
import com.easyTeach.server.databaseWrapper.ClassWrapper;
import com.easyTeach.server.databaseWrapper.CourseWrapper;
import com.easyTeach.server.databaseWrapper.ExerciseParameterWrapper;
import com.easyTeach.server.databaseWrapper.ExerciseWrapper;
import com.easyTeach.server.databaseWrapper.UserWrapper;

	/**
	 * <p>
	 * This Class is for testing all the wrappers with the entities and the databases'
	 * CRUD (Insert, Select, Update, Delete) procedures.
	 * See all the wrappers and entity classes for reference to the testing information.
	 * </p>
	 * 
	 * @author Tonni Hyldgaard
	 * @version 1.0
	 * @data 3. December, 2013.
	 */

public class WrapperTest {
	
	public static void main(String[] args) {
		new WrapperTest();
	}
	
	public WrapperTest() {
		//testUserWrapper();
		//testClassWrapper();
		//testCourseWrapper();
		//testExerciseWrapper();
		testExerciseParameterWrapper();
	}
	
	/**
     * The testUserWrapper checks whether the CRUD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see User
     * @see UserWrapper
     */
	public void testUserWrapper()  {
		// Testing the C in CRUD - The Create (Insert) Statements.
		User user1 = new User();
		user1.setEmail("tonni@test.com");
		user1.setUserType("Student");
		user1.setFirstName("Tonni");
		user1.setLastName("Kristensen");
		user1.setPassword("randompassword123#");
		
		User user2 = new User();
		user2.setEmail("morten@test.com");
		user2.setUserType("Admin");
		user2.setFirstName("Morten");
		user2.setLastName("Faarkrog");
		user2.setPassword("randompassword456#");
		
		new UserWrapper();
		UserWrapper.insertIntoUser(user1);
		UserWrapper.insertIntoUser(user2);
		
		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<User> hashSetUser = UserWrapper.getUserRows();
		for (User u : hashSetUser) {
			System.out.print(u.getUserNo() + ", ");
			System.out.print(u.getEmail() + ", ");
			System.out.print(u.getUserType() + ", ");
			System.out.print(u.getFirstName() + ", ");
			System.out.print(u.getLastName() + ", ");
			System.out.print(u.getPassword() + ", ");
			System.out.println(u.getDateAdded());
		}
		
		// Testing the U in CRUD - The Update Statements.
		user1.setFirstName("Bob");
		user1.setUserNo("1");
		UserWrapper.updateUserRow(user1);
		
		// Testing the D in CRUD - The Delete Statements.
		UserWrapper.deleteuserRow("1");
		
	}
	
	
	/**
     * The testClassWrapper checks whether the CRUD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see Class
     * @see ClassWrapper
     */
	public void testClassWrapper() {
		// Testing the C in CRUD - The Create (Insert) Statements.
		Class class1 = new Class();
		class1.setYear(1992);
		class1.setClassName("TestName1");
		
		Class class2 = new Class();
		class2.setYear(1222);
		class2.setClassName("DAT22a");
		
		new ClassWrapper();
		ClassWrapper.insertIntoClass(class1);
		ClassWrapper.insertIntoClass(class2);
		
		
		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<Class> hashSetClass = ClassWrapper.getClassRows();
		for (Class c : hashSetClass) {
			System.out.print(c.getClassNo() + ", ");
			System.out.print(c.getYear() + ", ");
			System.out.println(c.getClassName());
		}
		
		// Testing the U in CRUD - The Update Statements.
		class2.setYear(1993);
		class2.setClassNo("2");
		ClassWrapper.updateClassRow(class2);
		
		// Testing the D in CRUD - The Delete Statements.
		ClassWrapper.deleteClassRow("2");
	}
	
	/**
     * The testCouseWrapper checks whether the CRUD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see Course
     * @see CourseWrapper
     */
	public void testCourseWrapper() {
		// Testing the C in CRUD - The Create (Insert) Statements.
		Course course1 = new Course();
		Course course2 = new Course();
		course1.setCourseName("Physics101");
		course2.setCourseName("Biology101");
		
		new CourseWrapper();
		CourseWrapper.insertIntoCourse(course1);
		CourseWrapper.insertIntoCourse(course2);
		
		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<Course> hashSetCourse = CourseWrapper.getCourseRows();
		for (Course c : hashSetCourse) {
			System.out.print(c.getCourseNo() + ", ");
			System.out.println(c.getCourseName());
	}
		
		// Testing the U in CRUD - The Update Statements.
		course1.setCourseName("Physics102");
		course1.setCourseNo("1");
		CourseWrapper.updateCourseRow(course1);
		
		
		// Testing the D in CRUD - The Delete Statements.
		CourseWrapper.deleteCourseRow("1");
	}
	
	/**
     * The testExerciseWrapper checks whether the CRUD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see Exercise
     * @see ExerciseWrapper
     */
	public void testExerciseWrapper() {
		// Testing the C in CRUD - The Create (Insert) Statements.
		Exercise exercise1 = new Exercise();
		exercise1.setAuthor("1");
		exercise1.setExerciseParameterNo("1");
		exercise1.setExerciseName("DataExer");
		exercise1.setPassword("password231#");
		
		Exercise exercise2 = new Exercise();
		exercise1.setAuthor("2");
		exercise1.setExerciseName("Something");
		
		new ExerciseWrapper();
		ExerciseWrapper.insertIntoExercise(exercise1);
		ExerciseWrapper.insertIntoExercise(exercise2);
		
		//Testing the R in CRUD - The Read (Select) Statements.

		// Testing the U in CRUD - The Update Statements.

		// Testing the D in CRUD - The Delete Statements.

		
	}
	
	public void testExerciseParameterWrapper() {
		// Testing the C in CRUD - The Create (Insert) Statements.
		ExerciseParameter exParam1 = new ExerciseParameter();
		exParam1.setIsTest(true);
		exParam1.setIsLocked(true);
		exParam1.setAccessBegin(getBeginDate());
		exParam1.setAccessEnd(getEndDate());
		exParam1.setTimeLimit(20);
		
		ExerciseParameter exParam2 = new ExerciseParameter();
		exParam2.setIsTest(false);
		exParam2.setIsLocked(false);
		
		new ExerciseParameterWrapper();
		ExerciseParameterWrapper.insertIntoExerciseParameter(exParam1);
		ExerciseParameterWrapper.insertIntoExerciseParameter(exParam2);
	}
	
	private static java.sql.Timestamp getBeginDate() {
		java.util.Date test = new java.util.Date(2013 - 1900, 12 - 1, 20, 15, 20, 0);
		return new java.sql.Timestamp(test.getTime());
	}
		
	private static java.sql.Timestamp getEndDate() {
		java.util.Date test = new java.util.Date(2013 - 1900, 12 - 1, 22, 15, 20, 0);
		return new java.sql.Timestamp(test.getTime());
	}
}
