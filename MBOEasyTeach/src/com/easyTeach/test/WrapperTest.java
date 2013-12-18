package com.easyTeach.test;

import java.util.HashSet;

import com.easyTeach.common.entity.Answer;
import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.ClassCourseRelation;
import com.easyTeach.common.entity.ClassUserRelation;
import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.entity.ExerciseParameter;
import com.easyTeach.common.entity.ExerciseQuestionRelation;
import com.easyTeach.common.entity.Question;
import com.easyTeach.common.entity.QuestionTagRelation;
import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.entity.UserQuestionState;
import com.easyTeach.common.entity.UserTestResult;
import com.easyTeach.server.databaseWrapper.AnswerWrapper;
import com.easyTeach.server.databaseWrapper.ClassCourseRelationWrapper;
import com.easyTeach.server.databaseWrapper.ClassUserRelationWrapper;
import com.easyTeach.server.databaseWrapper.ClassWrapper;
import com.easyTeach.server.databaseWrapper.CourseWrapper;
import com.easyTeach.server.databaseWrapper.ExerciseParameterWrapper;
import com.easyTeach.server.databaseWrapper.ExerciseQuestionRelationWrapper;
import com.easyTeach.server.databaseWrapper.ExerciseWrapper;
import com.easyTeach.server.databaseWrapper.QuestionTagRelationWrapper;
import com.easyTeach.server.databaseWrapper.QuestionWrapper;
import com.easyTeach.server.databaseWrapper.TagWrapper;
import com.easyTeach.server.databaseWrapper.UserQuestionStateWrapper;
import com.easyTeach.server.databaseWrapper.UserTestResultWrapper;
import com.easyTeach.server.databaseWrapper.UserWrapper;

	/**
	 * <p>
	 * This Class is for testing all the wrappers with the entities and the databases'
	 * CRUD (Insert, Select, Update, Delete) procedures.
	 * See all the wrappers and entity classes for reference to the testing information.
	 * </p>
	 * 
	 * @author Tonni Hyldgaard
	 * @version 1.2
	 * @data 4. December, 2013.
	 */

public class WrapperTest {
	
	public static void main(String[] args) {
		new WrapperTest();
	}
	
	public WrapperTest() {
		//testUserWrapper();
		//testClassWrapper();
		//testCourseWrapper();
		//testExerciseParameterWrapper();
		//testExerciseWrapper();
		//testTag();
		//testQuestion();
		//testQuestionTagRelation();
		//testClassCourseRelation();
		//testClassUserRelation();
		//testExerciseQuestionRelation();
		//testAnswerWrapper();
		//testUserQuestionState();
		//testUserTestResult();
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
		//UserWrapper.deleteuserRow("1");
		
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
     * The testExerciseParameterWrapper checks whether the CRUD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see ExerciseParameter
     * @see ExerciseParameterWrapper
     */
	
	public void testExerciseParameterWrapper() {
		
		// Testing the C in CRUD - The Create (Insert) Statements.
		ExerciseParameter exParam1 = new ExerciseParameter();
		exParam1.setIsTest(true);
		exParam1.setIsLocked(true);
		//exParam1.setAccessBegin(getBeginDate());
		//exParam1.setAccessEnd(getEndDate());
		exParam1.setTimeLimit(20);
		
		ExerciseParameter exParam2 = new ExerciseParameter();
		exParam2.setIsTest(false);
		exParam2.setIsLocked(false);
		
		new ExerciseParameterWrapper();
		ExerciseParameterWrapper.insertIntoExerciseParameter(exParam1);
		ExerciseParameterWrapper.insertIntoExerciseParameter(exParam2);

		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<ExerciseParameter> hashSetExerciseParameter = ExerciseParameterWrapper.getExerciseParameterRows();
		for (ExerciseParameter eP : hashSetExerciseParameter) {
			System.out.print(eP.getExerciseParameterNo() + ", ");
			System.out.print(eP.getIsTest() + ", ");
			System.out.print(eP.getIsLocked() + ", ");
			System.out.print(eP.getAccessBegin() + ", ");
			System.out.print(eP.getAccessEnd() + ", ");
			System.out.println(eP.getTimeLimit());
		}
		
		// Testing the U in CRUD - The Update Statements.
		exParam1.setTimeLimit(40);
		exParam1.setExerciseParameterNo("1");
		ExerciseParameterWrapper.updateExerciseParameterRow(exParam1);

		// Testing the D in CRUD - The Delete Statements.
		ExerciseParameterWrapper.deleteExerciseParameterRow("1");
	}
	
	private static java.sql.Date getBeginDate() {	
		long test1 = new java.util.Date().getTime();
		long test2 = new java.util.Date().getSeconds() * 1000;
		
		java.sql.Date test = new java.sql.Date(test1 + test2);
		
		return test;
	}
	
	private static java.sql.Date getEndDate() {
		return new java.sql.Date(2014-12-01);
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
		exercise1.setAuthor("2");
		exercise1.setExerciseParameterNo("12");
		exercise1.setExerciseName("DataExer");
		exercise1.setPassword("password231#");
		
		Exercise exercise2 = new Exercise();
		exercise2.setAuthor("3");
		exercise2.setExerciseParameterNo("13");
		exercise2.setExerciseName("Something");
		
		Exercise exercise3 = new Exercise();
		exercise3.setCourseNo("2");
		exercise3.setAuthor("3");
		exercise3.setExerciseParameterNo("20");
		exercise3.setExerciseName("This is an exercise");
		exercise3.setPassword("SomethingSomething");
		
		new ExerciseWrapper();
		ExerciseWrapper.insertIntoExercise(exercise1);
		ExerciseWrapper.insertIntoExercise(exercise2);
		ExerciseWrapper.insertIntoExercise(exercise3);

		
		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<Exercise> hashSetExercise1 = ExerciseWrapper.getExerciseRows();
		System.out.println("getClassCourseRelationRows():");
		for (Exercise ex : hashSetExercise1) {
			System.out.print(ex.getExerciseNo() + ", ");
			System.out.print(ex.getCourseNo() + ", ");
			System.out.print(ex.getAuthor() + ", ");
			System.out.print(ex.getExerciseParameterNo() + ", ");
			System.out.print(ex.getExerciseName() + ", ");
			System.out.print(ex.getDateAdded() + ", ");
			System.out.println(ex.getPassword());
		}
		System.out.println();
		HashSet<Exercise> hashSetExercise2 = ExerciseWrapper.getExerciseRowsWithCourseNo("2");
		System.out.println("getClassCourseRelationRowsWithCourseNo():");
		for (Exercise ex : hashSetExercise2) {
			System.out.print(ex.getExerciseNo() + ", ");
			System.out.print(ex.getCourseNo() + ", ");
			System.out.print(ex.getAuthor() + ", ");
			System.out.print(ex.getExerciseParameterNo() + ", ");
			System.out.print(ex.getExerciseName() + ", ");
			System.out.print(ex.getDateAdded() + ", ");
			System.out.println(ex.getPassword());
		}
		System.out.println();
		System.out.println("getClassCourseRelationRowWithExerciseNo(): ");
		exercise3 = ExerciseWrapper.getExerciseRowWithExerciseNo("3");
		System.out.print(exercise3.getExerciseNo() + ", ");
		System.out.print(exercise3.getCourseNo() + ", ");
		System.out.print(exercise3.getAuthor() + ", ");
		System.out.print(exercise3.getExerciseParameterNo() + ", ");
		System.out.print(exercise3.getExerciseName() + ", ");
		System.out.print(exercise3.getDateAdded() + ", ");
		System.out.println(exercise3.getPassword());
				

		// Testing the U in CRUD - The Update Statements.
		exercise1.setPassword("newPassword123");
		exercise1.setExerciseNo("3");
		ExerciseWrapper.updateExerciseRow(exercise1);

		// Testing the D in CRUD - The Delete Statements.
		ExerciseWrapper.deleteExerciseRow("4");

	}
	
	/**
     * The testTag checks whether the CRUD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see Tag
     * @see TagWrapper
     */
	
	public void testTag() {
		
		// Testing the C in CRUD - The Create (Insert) Statements.
		Tag tag1 = new Tag();
		tag1.setTag("This Is a Tag");
		
		Tag tag2 = new Tag();
		tag2.setTag("And yet another one");
		
		new TagWrapper();
		TagWrapper.insertIntoTag(tag1);
		TagWrapper.insertIntoTag(tag2);
		
		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<Tag> hashSetTag = TagWrapper.getTagRows();
		for (Tag t : hashSetTag) {
			System.out.print(t.getTagNo() + ", ");
			System.out.println(t.getTag());
			
		}
		
		// Testing the U in CRUD - The Update Statements.
		tag1.setTag("Changed Tag Name");
		tag1.setTagNo("1");
		TagWrapper.updateTagRow(tag1);

		// Testing the D in CRUD - The Delete Statements.
		TagWrapper.deleteTagRow("1");
	}
	
	/**
     * The testQuestion checks whether the CRUD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see Question
     * @see QuestionWrapper
     */
	
	public void testQuestion() {
		
		// Testing the C in CRUD - The Create (Insert) Statements.
		Question question1 = new Question();
		question1.setQuestionType("Mulitiple");
		question1.setQuestion("What is the size of this stuff");
		question1.setPoints(5);
		
		Question question2 = new Question();
		question2.setQuestionType("Single Answer");
		question2.setQuestion("Say my name");
		question2.setPoints(50);
		
		new QuestionWrapper();
		QuestionWrapper.insertIntoQuestion(question1);
		QuestionWrapper.insertIntoQuestion(question2);
		
		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<Question> hashSetQuestion = QuestionWrapper.getQuestionRows();
		for (Question q : hashSetQuestion) {
			System.out.print(q.getQuestionNo() + ", ");
			System.out.print(q.getQuestionType() + ", ");
			System.out.print(q.getQuestion() + ", ");
			System.out.println(q.getPoints());
			
		}
		
		// Testing the U in CRUD - The Update Statements.
		question2.setPoints(25);
		question2.setQuestionNo("2");
		QuestionWrapper.updateQuestionRow(question2);

		// Testing the D in CRUD - The Delete Statements.
		QuestionWrapper.deleteQuestionRow("1");
	}
	
	/**
     * The testQuestionTagRelation checks whether the CRD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see QuestionTagRelation
     * @see QuestionTagRelationWrapper
     */
	
	public void testQuestionTagRelation() {
		
		// Testing the C in CRD - The Create (Insert) Statements.
		QuestionTagRelation qTR1 = new QuestionTagRelation();
		qTR1.setQuestionNo("3");
		qTR1.setTagNo("4");
		
		QuestionTagRelation qTR2 = new QuestionTagRelation();
		qTR2.setQuestionNo("2");
		qTR2.setTagNo("4");
		
		new QuestionTagRelationWrapper();
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qTR1);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qTR2);

		
		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<QuestionTagRelation> hashSetQTR1 = QuestionTagRelationWrapper.getQuestionTagRelationRows();
		System.out.println("getQuestionTagRelationRows():");
		for (QuestionTagRelation qTR : hashSetQTR1) {
			System.out.print(qTR.getQuestionNo() + ", ");
			System.out.println(qTR.getTagNo());
		}
		System.out.println();
		HashSet<QuestionTagRelation> hashSetQTR2 = QuestionTagRelationWrapper.getQuestionTagRelationRowsWithQuestionNo("2");
		System.out.println("getQuestionTagRelationRowsWithQuestionNo():");
		for (QuestionTagRelation qTR : hashSetQTR2) {
			System.out.print(qTR.getQuestionNo() + ", ");
			System.out.println(qTR.getTagNo());
		}
		System.out.println();
		HashSet<QuestionTagRelation> hashSetQTR3 = QuestionTagRelationWrapper.getQuestionTagRelationRowsWithTagNo("6");
		System.out.println("getQuestionTagRelationRowsWithTagNo():");
		for (QuestionTagRelation qTR : hashSetQTR3) {
			System.out.print(qTR.getQuestionNo() + ", ");
			System.out.println(qTR.getTagNo());
		}
		
		// Testing the D in CRUD - The Delete Statements.
		QuestionTagRelationWrapper.deleteQuestionTagRelationRow("2", "6");
	}
	
	
	/**
     * The testClassCourseRelation checks whether the CRD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see ClassCourseRelation
     * @see ClassCourseRelationWrapper
     */
	
	public void testClassCourseRelation() {
		
		// Testing the C in CRD - The Create (Insert) Statements.
		ClassCourseRelation cCR1 = new ClassCourseRelation();
		cCR1.setClassNo("1");
		cCR1.setCourseNo("2");
		
		ClassCourseRelation cCR2 = new ClassCourseRelation();
		cCR2.setClassNo("1");
		cCR2.setCourseNo("3");
		
		new ClassCourseRelationWrapper();
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(cCR1);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(cCR2);

		
		//Testing the R in CRD - The Read (Select) Statements.
		HashSet<ClassCourseRelation> hashSetcCR1 = ClassCourseRelationWrapper.getClassCourseRelationRows();
		System.out.println("getClassCourseRelationRows():");
		for (ClassCourseRelation cCR : hashSetcCR1) {
			System.out.print(cCR.getClassNo() + ", ");
			System.out.println(cCR.getCourseNo());
		}
		System.out.println();
		HashSet<ClassCourseRelation> hashSetcCR2 = ClassCourseRelationWrapper.getClassCourseRelationRowsWithClassNo("1");
		System.out.println("getClassCourseRelationRowsWithClassNo():");
		for (ClassCourseRelation cCR : hashSetcCR2) {
			System.out.print(cCR.getClassNo() + ", ");
			System.out.println(cCR.getCourseNo());
		}
		System.out.println();
		HashSet<ClassCourseRelation> hashSetcCR3 = ClassCourseRelationWrapper.getClassCourseRelationRowsWithCourseNo("3");
		System.out.println("getClassCourseRelationRowsWithCourseNo():");
		for (ClassCourseRelation cCR : hashSetcCR3) {
			System.out.print(cCR.getClassNo() + ", ");
			System.out.println(cCR.getCourseNo());
		}
		
		// Testing the D in CRD - The Delete Statements.
		ClassCourseRelationWrapper.deleteClassCourseRelationRow("1", "2");
	}
	
	/**
     * The testClassUserRelation checks whether the CRD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see ClassUserRelation
     * @see ClassUserRelationWrapper
     */
	
	public void testClassUserRelation() {
		
		// Testing the C in CRD - The Create (Insert) Statements.
		ClassUserRelation cUR1 = new ClassUserRelation();
		cUR1.setClassNo("1");
		cUR1.setUserNo("2");
		
		ClassUserRelation cUR2 = new ClassUserRelation();
		cUR2.setClassNo("1");
		cUR2.setUserNo("3");
		
		new ClassUserRelationWrapper();
		ClassUserRelationWrapper.insertIntoClassUserRelation(cUR1);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cUR2);

		
		//Testing the R in CRD - The Read (Select) Statements.
		HashSet<ClassUserRelation> hashSetCUR1 = ClassUserRelationWrapper.getClassUserRelationRows();
		System.out.println("getClassUserRelationRows():");
		for (ClassUserRelation cUR : hashSetCUR1) {
			System.out.print(cUR.getClassNo() + ", ");
			System.out.println(cUR.getUserNo());
		}
		System.out.println();
		HashSet<ClassUserRelation> hashSetCUR2 = ClassUserRelationWrapper.getClassUserRelationRowsWithClassNo("1");
		System.out.println("getClassUserRelationRowsWithClassNo():");
		for (ClassUserRelation cUR : hashSetCUR2) {
			System.out.print(cUR.getClassNo() + ", ");
			System.out.println(cUR.getUserNo());
		}
		System.out.println();
		HashSet<ClassUserRelation> hashSetCUR3 = ClassUserRelationWrapper.getClassUserRelationRowsWithUserNo("2");
		System.out.println("getClassUserRelationRowsUserNo():");
		for (ClassUserRelation cUR : hashSetCUR3) {
			System.out.print(cUR.getClassNo() + ", ");
			System.out.println(cUR.getUserNo());
		}
		
		// Testing the D in CRD - The Delete Statements.
		ClassUserRelationWrapper.deleteClassUserRelationRow("1", "2");
	}

	
	/**
     * The testExerciseQuestionRelation checks whether the CRD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see ExerciseQuestionRelation
     * @see ExerciseQuestionRelationWrapper
     */
	
	public void testExerciseQuestionRelation() {
		
		// Testing the C in CRD - The Create (Insert) Statements.
		ExerciseQuestionRelation eQR1 = new ExerciseQuestionRelation();
		eQR1.setExerciseNo("5");
		eQR1.setQuestionNo("2");
		
		ExerciseQuestionRelation eQR2 = new ExerciseQuestionRelation();
		eQR2.setExerciseNo("6");
		eQR2.setQuestionNo("4");
		
		new ExerciseQuestionRelationWrapper();
		ExerciseQuestionRelationWrapper.insertIntoExerciseQuestionRelation(eQR1);
		ExerciseQuestionRelationWrapper.insertIntoExerciseQuestionRelation(eQR2);

		
		//Testing the R in CRD - The Read (Select) Statements.
		HashSet<ExerciseQuestionRelation> hashSetEQR1 = ExerciseQuestionRelationWrapper.getExerciseQuestionRelationRows();
		System.out.println("getExerciseQuestionRelationRows():");
		for (ExerciseQuestionRelation eQR : hashSetEQR1) {
			System.out.print(eQR.getExerciseNo() + ", ");
			System.out.println(eQR.getQuestionNo());
		}
		System.out.println();
		HashSet<ExerciseQuestionRelation> hashSetEQR2 = ExerciseQuestionRelationWrapper.getExerciseQuestionRelationRowsWithExerciseNo("5");
		System.out.println("getExerciseQuestionRelationRowsWithExerciseNo():");
		for (ExerciseQuestionRelation eQR : hashSetEQR2) {
			System.out.print(eQR.getExerciseNo() + ", ");
			System.out.println(eQR.getQuestionNo());
		}
		System.out.println();
		HashSet<ExerciseQuestionRelation> hashSetEQR3 = ExerciseQuestionRelationWrapper.getExerciseQuestionRelationRowsWithQuestionNo("4");
		System.out.println("getExerciseQuestionRelationRowsWithQuestionNo():");
		for (ExerciseQuestionRelation eQR : hashSetEQR3) {
			System.out.print(eQR.getExerciseNo() + ", ");
			System.out.println(eQR.getQuestionNo());
		}
		
		// Testing the D in CRD - The Delete Statements.
		ExerciseQuestionRelationWrapper.deleteExerciseQuestionRelationRow("5", "2");
	}
	
	/**
     * The testAnswerWrapper checks whether the CRUD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see Answer
     * @see AnswerWrapper
     */
	public void testAnswerWrapper() {
		// Testing the C in CRUD - The Create (Insert) Statements.
		Answer answer1 = new Answer();
		answer1.setQuestionNo("3");
		answer1.setAnswerNo("1");
		answer1.setAnswer("This is the answer");
		answer1.setIsCorrect(true);
		
		Answer answer2 = new Answer();
		answer2.setQuestionNo("3");
		answer2.setAnswerNo("2");
		answer2.setAnswer("Wrong answer");
		answer2.setIsCorrect(false);
		
		new AnswerWrapper();
		AnswerWrapper.insertIntoAnswer(answer1);
		AnswerWrapper.insertIntoAnswer(answer2);
		
		
		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<Answer> hashSetAnswer1 = AnswerWrapper.getAnswerRows();
		System.out.println("getAnswerRows(): ");
		for (Answer a : hashSetAnswer1) {
			System.out.print(a.getQuestionNo() + ", ");
			System.out.print(a.getAnswerNo() + ", ");
			System.out.print(a.getAnswer() + ", ");
			System.out.println(a.getIsCorrect());
		}
		
		HashSet<Answer> hashSetAnswer2 = AnswerWrapper.getAnswerRowsWithQuestionNo("3");
		System.out.println("getAnswerRowsWithQuestionNo(): ");
		for (Answer a : hashSetAnswer2) {
			System.out.print(a.getQuestionNo() + ", ");
			System.out.print(a.getAnswerNo() + ", ");
			System.out.print(a.getAnswer() + ", ");
			System.out.println(a.getIsCorrect());
		}
		
		// Testing the U in CRUD - The Update Statements.
		answer1.setAnswer("Right Answer");
		answer1.setQuestionNo("3");
		AnswerWrapper.updateAnswerRow(answer1);
		
		// Testing the D in CRUD - The Delete Statements.
		AnswerWrapper.deleteAnswerRow("3", "2");
	}
	
	/**
     * The testUserQuestionState checks whether the CRUD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see UserQuestionState
     * @see UserQuestionStateWrapper
     */
	public void testUserQuestionState() {
		// Testing the C in CRUD - The Create (Insert) Statements.
		UserQuestionState uQS1 = new UserQuestionState();
		uQS1.setUserNo("2");
		uQS1.setQuestionNo("4");
		uQS1.setHasCompleted(true);
		
		UserQuestionState uQS2 = new UserQuestionState();
		uQS2.setUserNo("2");
		uQS2.setQuestionNo("2");
		uQS2.setHasCompleted(false);
		
		new UserQuestionStateWrapper();
		UserQuestionStateWrapper.insertIntoUserQuestionState(uQS1);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uQS2);
		
		
		//Testing the R in CRUD - The Read (Select) Statements.
		HashSet<UserQuestionState> hashSetUQS1 = UserQuestionStateWrapper.getUserQuestionStateRows();
		System.out.println("getUserQuestionStateRows(): ");
		for (UserQuestionState uQS : hashSetUQS1) {
			System.out.print(uQS.getUserNo() + ", ");
			System.out.print(uQS.getQuestionNo() + ", ");
			System.out.println(uQS.getHasCompleted());
		}
		System.out.println();
		HashSet<UserQuestionState> hashSetUQS2 = UserQuestionStateWrapper.getUserQuestionStateRowsWithQuestionNo("4");
		System.out.println("getUserQuestionStateRowsWithQuestionNo(): ");
		for (UserQuestionState uQS : hashSetUQS2) {
			System.out.print(uQS.getUserNo() + ", ");
			System.out.print(uQS.getQuestionNo() + ", ");
			System.out.println(uQS.getHasCompleted());
		}
		System.out.println();
		HashSet<UserQuestionState> hashSetUQS3 = UserQuestionStateWrapper.getUserQuestionStateRowsWithUserNo("2");
		System.out.println("getUserQuestionStateRowsWithUserNo(): ");
		for (UserQuestionState uQS : hashSetUQS3) {
			System.out.print(uQS.getUserNo() + ", ");
			System.out.print(uQS.getQuestionNo() + ", ");
			System.out.println(uQS.getHasCompleted());
		}
		
		// Testing the U in CRUD - The Update Statements.
		uQS2.setHasCompleted(true);
		uQS2.setQuestionNo("2");
		UserQuestionStateWrapper.updateUserQuestionStateHasCompleted(uQS2);
		
		// Testing the D in CRUD - The Delete Statements.
		UserQuestionStateWrapper.deleteUserQuestionStateRow("2", "2");
	}

	
	/**
     * The testUserTestResult checks whether the CRD procedures from
     * the wrapper is working correctly with the database easyTeach.
     * 
     * @see UserTestResult
     * @see UserTestResultWrapper
     */
	public void testUserTestResult() {
		// Testing the C in CRD - The Create (Insert) Statements.
		UserTestResult uTR1 = new UserTestResult();
		uTR1.setUserNo("2");
		uTR1.setExerciseNo("6");
		uTR1.setScore(10);
		
		UserTestResult uTR2 = new UserTestResult();
		uTR2.setUserNo("2");
		uTR2.setExerciseNo("5");
		uTR2.setScore(15);
		
		new UserTestResultWrapper();
		UserTestResultWrapper.insertIntoUserTestResult(uTR1);
		UserTestResultWrapper.insertIntoUserTestResult(uTR2);
		
		
		//Testing the R in CRD - The Read (Select) Statements.
		HashSet<UserTestResult> hashSetUTR1 = UserTestResultWrapper.getUserTestResultRows();
		System.out.println("getUserTestResultRows(): ");
		for (UserTestResult uTR : hashSetUTR1) {
			System.out.print(uTR.getUserNo() + ", ");
			System.out.print(uTR.getExerciseNo() + ", ");
			System.out.println(uTR.getScore());
		}
		System.out.println();
		HashSet<UserTestResult> hashSetUTR2 = UserTestResultWrapper.getUserTestResultRowsWithExerciseNo("5");
		System.out.println("getUserTestResultRowsWithExerciseNo(): ");
		for (UserTestResult uTR : hashSetUTR2) {
			System.out.print(uTR.getUserNo() + ", ");
			System.out.print(uTR.getExerciseNo() + ", ");
			System.out.println(uTR.getScore());
		}
		System.out.println();
		HashSet<UserTestResult> hashSetUTR3 = UserTestResultWrapper.getUserTestResultRowsWithUserNo("2");
		System.out.println("getUserTestResultRowsWithUserNo(): ");
		for (UserTestResult uTR : hashSetUTR3) {
			System.out.print(uTR.getUserNo() + ", ");
			System.out.print(uTR.getExerciseNo() + ", ");
			System.out.println(uTR.getScore());
		}
		
		
		// Testing the D in CRD - The Delete Statements.
		UserTestResultWrapper.deleteUserTestResultRow("2", "5");
	}
	
}