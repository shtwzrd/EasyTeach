package com.easyTeach.test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import org.jasypt.util.password.StrongPasswordEncryptor;

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
 * The ContentDatabaseTest class is the class where we insert information into
 * all the different tables that we got in the database and creates the relation
 * between them. This will help the understanding of the program, because of the
 * data presented while you use the program MBO EasyTeach. All information
 * inserted are based on real persons and real question and exercises. This
 * makes it easier for the client to relate to the new system that they are
 * going to implement, as they can see a real-a-like example.
 * </p>
 * 
 * @author Tonni Hyldgaard
 * @version 1.0
 * @date 12. December, 2013
 */

public class ContentDatabaseTest {

	public static void main(String[] args) {
		new ContentDatabaseTest();
		System.out.println("End of Session");
	}

	private ContentDatabaseTest() {
		// Creates an instances of the StrongPasswordEncryptor
		StrongPasswordEncryptor pEncrypt = new StrongPasswordEncryptor();

		// Creates an instance of the {@link User} entity
		User u1 = new User(UUID.randomUUID().toString(), "admi1234@kea.dk",
				"Administrator", "Douglas", "Beaver",
				pEncrypt.encryptPassword("admi1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u2 = new User(UUID.randomUUID().toString(), "admi5678@kea.dk",
				"Administrator", "Admin", "Admin",
				pEncrypt.encryptPassword("admi5678"), new Date(2013 - 1900,
						12 - 1, 12));
		User u3 = new User(UUID.randomUUID().toString(), "cayh1234@kea.dk",
				"Teacher", "Cay", "Holmgaard",
				pEncrypt.encryptPassword("cayh1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u4 = new User(UUID.randomUUID().toString(), "chri1234@kea.dk",
				"Teacher", "Christian", "Bastlund",
				pEncrypt.encryptPassword("chri1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u5 = new User(UUID.randomUUID().toString(), "mari1234@kea.dk",
				"Teacher", "Marianne", "Nielsen",
				pEncrypt.encryptPassword("mari1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u6 = new User(UUID.randomUUID().toString(), "john1234@kea.dk",
				"Teacher", "John", "Preus",
				pEncrypt.encryptPassword("john1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u7 = new User(UUID.randomUUID().toString(), "doug1234@kea.dk",
				"Teacher", "Douglas", "Beaver",
				pEncrypt.encryptPassword("doug1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u8 = new User(UUID.randomUUID().toString(),
				"bran1234@stud.kea.dk", "Student", "Brandon", "Lucas",
				pEncrypt.encryptPassword("bran1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u9 = new User(UUID.randomUUID().toString(),
				"oliv1234@stud.kea.dk", "Student", "Oliver", "Nielsen",
				pEncrypt.encryptPassword("oliv1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u10 = new User(UUID.randomUUID().toString(),
				"mort1234@stud.kea.dk", "Student", "Morten", "Faarkrog",
				pEncrypt.encryptPassword("mort1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u11 = new User(UUID.randomUUID().toString(),
				"tonn1234@stud.kea.dk", "Student", "Tonni", "Kristensen",
				pEncrypt.encryptPassword("tonn1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u12 = new User(UUID.randomUUID().toString(),
				"kimh1234@stud.kea.dk", "Student", "Kim", "Nyhuus",
				pEncrypt.encryptPassword("kimh1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u13 = new User(UUID.randomUUID().toString(),
				"simo1234@stud.kea.dk", "Student", "Simon", "Kaae",
				pEncrypt.encryptPassword("simo1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u14 = new User(UUID.randomUUID().toString(),
				"mark1234@stud.kea.dk", "Student", "Mark", "Belov",
				pEncrypt.encryptPassword("mark1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u15 = new User(UUID.randomUUID().toString(),
				"stoy1234@stud.kea.dk", "Student", "Stoyan", "Bonchev",
				pEncrypt.encryptPassword("stoy1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u16 = new User(UUID.randomUUID().toString(),
				"andr1234@stud.kea.dk", "Student", "Andrea", "Dumitrascu",
				pEncrypt.encryptPassword("jean1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u17 = new User(UUID.randomUUID().toString(),
				"mart1234@stud.kea.dk", "Student", "Martynas", "Janulevicius",
				pEncrypt.encryptPassword("mart1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u18 = new User(UUID.randomUUID().toString(),
				"aasa1234@stud.kea.dk", "Student", "Aasa Susanna", "Wegelius",
				pEncrypt.encryptPassword("aasa1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u19 = new User(UUID.randomUUID().toString(),
				"sadi1234@stud.kea.dk", "Student", "Sadia", "Anwar",
				pEncrypt.encryptPassword("sadi1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u20 = new User(UUID.randomUUID().toString(),
				"soni1234@stud.kea.dk", "Student", "Soniya", "Begha",
				pEncrypt.encryptPassword("soni1234"), new Date(2013 - 1900,
						12 - 1, 12));
		User u21 = new User(UUID.randomUUID().toString(),
				"hani1234@stud.kea.dk", "Student", "Hanif",
				"Babollahzadeh-Zivlaei", pEncrypt.encryptPassword("hani1234"),
				new Date(2013 - 1900, 12 - 1, 12));
		User u22 = new User(UUID.randomUUID().toString(),
				"thor1234@stud.kea.dk", "Student", "Thor-Bjoern", "Bohme",
				pEncrypt.encryptPassword("bran1234"), new Date(2013 - 1900,
						12 - 1, 12));

		// Inserts the created {@link User} instances in the database though the
		// {@link UserWrapper}
		UserWrapper.insertIntoUser(u1);
		UserWrapper.insertIntoUser(u2);
		UserWrapper.insertIntoUser(u3);
		UserWrapper.insertIntoUser(u4);
		UserWrapper.insertIntoUser(u5);
		UserWrapper.insertIntoUser(u6);
		UserWrapper.insertIntoUser(u7);
		UserWrapper.insertIntoUser(u8);
		UserWrapper.insertIntoUser(u9);
		UserWrapper.insertIntoUser(u10);
		UserWrapper.insertIntoUser(u11);
		UserWrapper.insertIntoUser(u12);
		UserWrapper.insertIntoUser(u13);
		UserWrapper.insertIntoUser(u14);
		UserWrapper.insertIntoUser(u15);
		UserWrapper.insertIntoUser(u16);
		UserWrapper.insertIntoUser(u17);
		UserWrapper.insertIntoUser(u18);
		UserWrapper.insertIntoUser(u19);
		UserWrapper.insertIntoUser(u20);
		UserWrapper.insertIntoUser(u21);
		UserWrapper.insertIntoUser(u22);

		// Creates an instance of the {@link Class} entity
		Class cl1 = new Class(UUID.randomUUID().toString(), 2013, "DAT13A");
		Class cl2 = new Class(UUID.randomUUID().toString(), 2013, "DAT13B");
		Class cl3 = new Class(UUID.randomUUID().toString(), 2012, "DAT12W");

		// Inserts the created {@link Class} instances in the database though
		// the {@link ClassWrapper}
		ClassWrapper.insertIntoClass(cl1);
		ClassWrapper.insertIntoClass(cl2);
		ClassWrapper.insertIntoClass(cl3);

		// Creates an instance of the {@link Course} entity
		Course co1 = new Course(UUID.randomUUID().toString(),
				"Software Construction");
		Course co2 = new Course(UUID.randomUUID().toString(), "Software Design");
		Course co3 = new Course(UUID.randomUUID().toString(),
				"Information Technology within Organisation");
		Course co4 = new Course(UUID.randomUUID().toString(),
				"Computer Architecture and Operating Systems");
		Course co5 = new Course(UUID.randomUUID().toString(),
				"Cops and Hackers");
		Course co6 = new Course(UUID.randomUUID().toString(),
				"Mobile Apps Development");
		Course co7 = new Course(UUID.randomUUID().toString(), "Web Development");

		// Inserts the created {@link Course} instances in the database though
		// the {@link CourseWrapper}
		CourseWrapper.insertIntoCourse(co1);
		CourseWrapper.insertIntoCourse(co2);
		CourseWrapper.insertIntoCourse(co3);
		CourseWrapper.insertIntoCourse(co4);
		CourseWrapper.insertIntoCourse(co5);
		CourseWrapper.insertIntoCourse(co6);
		CourseWrapper.insertIntoCourse(co7);

		// Creates an instance of the {@link ClassUserRelation} entity
		ClassUserRelation cur1 = new ClassUserRelation(cl1.getClassNo(),
				u8.getUserNo());
		ClassUserRelation cur2 = new ClassUserRelation(cl1.getClassNo(),
				u9.getUserNo());
		ClassUserRelation cur3 = new ClassUserRelation(cl1.getClassNo(),
				u10.getUserNo());
		ClassUserRelation cur4 = new ClassUserRelation(cl1.getClassNo(),
				u11.getUserNo());
		ClassUserRelation cur5 = new ClassUserRelation(cl1.getClassNo(),
				u12.getUserNo());
		ClassUserRelation cur6 = new ClassUserRelation(cl2.getClassNo(),
				u13.getUserNo());
		ClassUserRelation cur7 = new ClassUserRelation(cl2.getClassNo(),
				u14.getUserNo());
		ClassUserRelation cur8 = new ClassUserRelation(cl2.getClassNo(),
				u15.getUserNo());
		ClassUserRelation cur9 = new ClassUserRelation(cl2.getClassNo(),
				u16.getUserNo());
		ClassUserRelation cur10 = new ClassUserRelation(cl2.getClassNo(),
				u17.getUserNo());
		ClassUserRelation cur11 = new ClassUserRelation(cl3.getClassNo(),
				u18.getUserNo());
		ClassUserRelation cur12 = new ClassUserRelation(cl3.getClassNo(),
				u19.getUserNo());
		ClassUserRelation cur13 = new ClassUserRelation(cl3.getClassNo(),
				u20.getUserNo());
		ClassUserRelation cur14 = new ClassUserRelation(cl3.getClassNo(),
				u21.getUserNo());
		ClassUserRelation cur15 = new ClassUserRelation(cl3.getClassNo(),
				u22.getUserNo());

		// Inserts the created {@link ClassUserRelation} instances in the
		// database though the {@link ClassUserRelationWrapper}
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur1);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur2);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur3);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur4);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur5);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur6);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur7);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur8);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur9);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur10);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur11);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur12);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur13);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur14);
		ClassUserRelationWrapper.insertIntoClassUserRelation(cur15);

		// Creates an instance of the {@link ClassCourseRelation} entity
		ClassCourseRelation ccr1 = new ClassCourseRelation(cl1.getClassNo(),
				co1.getCourseNo());
		ClassCourseRelation ccr2 = new ClassCourseRelation(cl1.getClassNo(),
				co2.getCourseNo());
		ClassCourseRelation ccr3 = new ClassCourseRelation(cl1.getClassNo(),
				co3.getCourseNo());
		ClassCourseRelation ccr4 = new ClassCourseRelation(cl2.getClassNo(),
				co4.getCourseNo());
		ClassCourseRelation ccr5 = new ClassCourseRelation(cl2.getClassNo(),
				co5.getCourseNo());
		ClassCourseRelation ccr6 = new ClassCourseRelation(cl2.getClassNo(),
				co6.getCourseNo());
		ClassCourseRelation ccr7 = new ClassCourseRelation(cl3.getClassNo(),
				co1.getCourseNo());
		ClassCourseRelation ccr8 = new ClassCourseRelation(cl3.getClassNo(),
				co3.getCourseNo());
		ClassCourseRelation ccr9 = new ClassCourseRelation(cl3.getClassNo(),
				co5.getCourseNo());
		ClassCourseRelation ccr10 = new ClassCourseRelation(cl3.getClassNo(),
				co7.getCourseNo());

		// Inserts the created {@link ClassCourseRelation} instances in the
		// database though the {@link ClassCourseRelationWrapper}
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr1);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr2);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr3);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr4);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr5);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr6);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr7);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr8);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr9);
		ClassCourseRelationWrapper.insertIntoClassCourseRelation(ccr10);

		// Creates an instance of the {@link ExerciseParameter} entity
		ExerciseParameter ep1 = new ExerciseParameter(UUID.randomUUID()
				.toString(), false, false, new Timestamp(2014 - 1900, 2 - 1,
				17, 15, 30, 00, 0), new Timestamp(2014 - 1900, 2 - 1, 17, 16,
				00, 00, 0), 30);
		ExerciseParameter ep2 = new ExerciseParameter(UUID.randomUUID()
				.toString(), true, true, new Timestamp(2014 - 1900, 3 - 1, 3,
				15, 30, 00, 0), new Timestamp(2014 - 1900, 2 - 1, 17, 16, 00,
				00, 0), 30);
		ExerciseParameter ep3 = new ExerciseParameter(UUID.randomUUID()
				.toString(), true, true, new Timestamp(2014 - 1900, 4 - 1, 4,
				15, 30, 00, 0), new Timestamp(2014 - 1900, 2 - 1, 17, 16, 00,
				00, 0), 30);

		// Inserts the created {@link ExerciseParameterRelation} instances in
		// the database though the {@link ExerciseParameterWrapper}
		ExerciseParameterWrapper.insertIntoExerciseParameter(ep1);
		ExerciseParameterWrapper.insertIntoExerciseParameter(ep2);
		ExerciseParameterWrapper.insertIntoExerciseParameter(ep3);

		// Creates an instance of the {@link Exercise} entity
		Exercise e1 = new Exercise(UUID.randomUUID().toString(),
				co1.getCourseNo(), u3.getUserNo(),
				ep1.getExerciseParameterNo(), "SWC Exercise", new Timestamp(
						2013 - 1900, 12 - 1, 12, 14, 14, 30, 0),
				pEncrypt.encryptPassword("exercise1234"));
		Exercise e2 = new Exercise(UUID.randomUUID().toString(),
				co2.getCourseNo(), u4.getUserNo(),
				ep2.getExerciseParameterNo(), "SWD Exercise", new Timestamp(
						2013 - 1900, 12 - 1, 12, 15, 17, 45, 0),
				pEncrypt.encryptPassword("exercise1234"));
		Exercise e3 = new Exercise(UUID.randomUUID().toString(),
				co3.getCourseNo(), u5.getUserNo(),
				ep3.getExerciseParameterNo(), "ITO Exercise", new Timestamp(
						2013 - 1900, 12 - 1, 12, 16, 19, 15, 0),
				pEncrypt.encryptPassword("exercise1234"));

		// Inserts the created {@link Exercise} instances in the database though
		// the {@link ExerciseWrapper}
		ExerciseWrapper.insertIntoExercise(e1);
		ExerciseWrapper.insertIntoExercise(e2);
		ExerciseWrapper.insertIntoExercise(e3);

		// Creates an instance of the {@link Question} entity
		Question q1 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice", "What is a Binary Number?", 3);
		Question q2 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice", "What is a Program?", 7);
		Question q3 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice",
				"Does Java compile directly into machine code?", 9);
		Question q4 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice", "What is an Identifier?", 5);
		Question q5 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice", "What does UP stand for?", 4);
		Question q6 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice", "What does SWD stand for?", 5);
		Question q7 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice",
				"What is not one of the Three Golden Rules?", 2);
		Question q8 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice",
				"What is the first phase in the Unified Process?", 6);
		Question q9 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice",
				"What is not a part of the Emotional Competence Inventory?", 4);
		Question q10 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice",
				"What is not a part of Kolb's learning cycle?", 7);
		Question q11 = new Question(UUID.randomUUID().toString(),
				"Multiple Choice",
				"What is not a part of Alderfe's needs hierarchy?", 9);

		// Inserts the created {@link Question} instances in the database though
		// the {@link QuestionWrapper}
		QuestionWrapper.insertIntoQuestion(q1);
		QuestionWrapper.insertIntoQuestion(q2);
		QuestionWrapper.insertIntoQuestion(q3);
		QuestionWrapper.insertIntoQuestion(q4);
		QuestionWrapper.insertIntoQuestion(q5);
		QuestionWrapper.insertIntoQuestion(q6);
		QuestionWrapper.insertIntoQuestion(q7);
		QuestionWrapper.insertIntoQuestion(q8);
		QuestionWrapper.insertIntoQuestion(q9);
		QuestionWrapper.insertIntoQuestion(q10);
		QuestionWrapper.insertIntoQuestion(q11);

		// Creates an instance of the {@link Answer} entity
		Answer a1 = new Answer(q1.getQuestionNo(),
				UUID.randomUUID().toString(),
				"A way of numbering lines in java", false);
		Answer a2 = new Answer(q1.getQuestionNo(),
				UUID.randomUUID().toString(), "A method for getting numbers",
				false);
		Answer a3 = new Answer(q1.getQuestionNo(),
				UUID.randomUUID().toString(), "A number containing 1 to 4",
				false);
		Answer a4 = new Answer(q1.getQuestionNo(),
				UUID.randomUUID().toString(), "A number containing 0s and 1s",
				true);

		Answer a5 = new Answer(q2.getQuestionNo(),
				UUID.randomUUID().toString(),
				"A list of instructions to be carried out by a computer.", true);
		Answer a6 = new Answer(q2.getQuestionNo(),
				UUID.randomUUID().toString(),
				"An ArrayList of instructions to be carried out by a computer",
				false);
		Answer a7 = new Answer(q2.getQuestionNo(),
				UUID.randomUUID().toString(), "A String of numbers", false);
		Answer a8 = new Answer(q2.getQuestionNo(),
				UUID.randomUUID().toString(), "An Integer", false);

		Answer a9 = new Answer(q3.getQuestionNo(),
				UUID.randomUUID().toString(), "Yes", false);
		Answer a10 = new Answer(q3.getQuestionNo(), UUID.randomUUID()
				.toString(), "No, it compile into Java bytecodes", true);
		Answer a11 = new Answer(q3.getQuestionNo(), UUID.randomUUID()
				.toString(), "No", true);
		Answer a12 = new Answer(q3.getQuestionNo(), UUID.randomUUID()
				.toString(), "No, it does not need to compile", false);

		Answer a13 = new Answer(q4.getQuestionNo(), UUID.randomUUID()
				.toString(), "A name given to an entity in a program", true);
		Answer a14 = new Answer(q4.getQuestionNo(), UUID.randomUUID()
				.toString(), "A name given to a class", true);
		Answer a15 = new Answer(q4.getQuestionNo(), UUID.randomUUID()
				.toString(), "The value you give an int", false);
		Answer a16 = new Answer(q4.getQuestionNo(), UUID.randomUUID()
				.toString(), "There is no such thing as an Identifier in Java",
				false);

		Answer a17 = new Answer(q5.getQuestionNo(), UUID.randomUUID()
				.toString(), "UP stands for the Unified Process", true);
		Answer a18 = new Answer(q5.getQuestionNo(), UUID.randomUUID()
				.toString(), "UP stands for the Universal Process", false);
		Answer a19 = new Answer(q5.getQuestionNo(), UUID.randomUUID()
				.toString(), "UP stands for the Unique Processing", false);
		Answer a20 = new Answer(q5.getQuestionNo(), UUID.randomUUID()
				.toString(), "UP stands for the Universal Processing", false);

		Answer a21 = new Answer(q6.getQuestionNo(), UUID.randomUUID()
				.toString(), "SWD stands for the Software Destruction", true);
		Answer a22 = new Answer(q6.getQuestionNo(), UUID.randomUUID()
				.toString(), "UP stands for the Software Design", false);
		Answer a23 = new Answer(q6.getQuestionNo(), UUID.randomUUID()
				.toString(), "UP stands for the Soft Design", false);
		Answer a24 = new Answer(q6.getQuestionNo(), UUID.randomUUID()
				.toString(), "UP stands for the Software Duplication", false);

		Answer a25 = new Answer(q7.getQuestionNo(), UUID.randomUUID()
				.toString(), "Place the user in control", false);
		Answer a26 = new Answer(q7.getQuestionNo(), UUID.randomUUID()
				.toString(), "Reduce the user's memory load", false);
		Answer a27 = new Answer(q7.getQuestionNo(), UUID.randomUUID()
				.toString(), "Make the interface consistent", false);
		Answer a28 = new Answer(q7.getQuestionNo(), UUID.randomUUID()
				.toString(), "Make the interface good looking", true);

		Answer a29 = new Answer(q8.getQuestionNo(), UUID.randomUUID()
				.toString(), "Inception", true);
		Answer a30 = new Answer(q8.getQuestionNo(), UUID.randomUUID()
				.toString(), "Elaboration", false);
		Answer a31 = new Answer(q8.getQuestionNo(), UUID.randomUUID()
				.toString(), "Construction", false);
		Answer a32 = new Answer(q8.getQuestionNo(), UUID.randomUUID()
				.toString(), "Transition", false);

		Answer a33 = new Answer(q9.getQuestionNo(), UUID.randomUUID()
				.toString(), "Self-awareness", false);
		Answer a34 = new Answer(q9.getQuestionNo(), UUID.randomUUID()
				.toString(), "Emotional Resilience", false);
		Answer a35 = new Answer(q9.getQuestionNo(), UUID.randomUUID()
				.toString(), "Intuition", false);
		Answer a36 = new Answer(q9.getQuestionNo(), UUID.randomUUID()
				.toString(), "Hate", true);

		Answer a37 = new Answer(q10.getQuestionNo(), UUID.randomUUID()
				.toString(), "Concrete Experience", false);
		Answer a38 = new Answer(q10.getQuestionNo(), UUID.randomUUID()
				.toString(), "Evaluation", true);
		Answer a39 = new Answer(q10.getQuestionNo(), UUID.randomUUID()
				.toString(), "Abstract Conceptualism", false);
		Answer a40 = new Answer(q10.getQuestionNo(), UUID.randomUUID()
				.toString(), "Observation and Reflection", false);

		Answer a41 = new Answer(q11.getQuestionNo(), UUID.randomUUID()
				.toString(), "Growth", false);
		Answer a42 = new Answer(q11.getQuestionNo(), UUID.randomUUID()
				.toString(), "God Job", true);
		Answer a43 = new Answer(q11.getQuestionNo(), UUID.randomUUID()
				.toString(), "Relatedness", false);
		Answer a44 = new Answer(q11.getQuestionNo(), UUID.randomUUID()
				.toString(), "Existence", false);

		// Inserts the created {@link Answer} instances in the database though
		// the {@link AnswerWrapper}
		AnswerWrapper.insertIntoAnswer(a1);
		AnswerWrapper.insertIntoAnswer(a2);
		AnswerWrapper.insertIntoAnswer(a3);
		AnswerWrapper.insertIntoAnswer(a4);
		AnswerWrapper.insertIntoAnswer(a5);
		AnswerWrapper.insertIntoAnswer(a6);
		AnswerWrapper.insertIntoAnswer(a7);
		AnswerWrapper.insertIntoAnswer(a8);
		AnswerWrapper.insertIntoAnswer(a9);
		AnswerWrapper.insertIntoAnswer(a10);
		AnswerWrapper.insertIntoAnswer(a11);
		AnswerWrapper.insertIntoAnswer(a12);
		AnswerWrapper.insertIntoAnswer(a13);
		AnswerWrapper.insertIntoAnswer(a14);
		AnswerWrapper.insertIntoAnswer(a15);
		AnswerWrapper.insertIntoAnswer(a16);
		AnswerWrapper.insertIntoAnswer(a17);
		AnswerWrapper.insertIntoAnswer(a18);
		AnswerWrapper.insertIntoAnswer(a19);
		AnswerWrapper.insertIntoAnswer(a20);
		AnswerWrapper.insertIntoAnswer(a21);
		AnswerWrapper.insertIntoAnswer(a22);
		AnswerWrapper.insertIntoAnswer(a23);
		AnswerWrapper.insertIntoAnswer(a24);
		AnswerWrapper.insertIntoAnswer(a25);
		AnswerWrapper.insertIntoAnswer(a26);
		AnswerWrapper.insertIntoAnswer(a27);
		AnswerWrapper.insertIntoAnswer(a28);
		AnswerWrapper.insertIntoAnswer(a29);
		AnswerWrapper.insertIntoAnswer(a30);
		AnswerWrapper.insertIntoAnswer(a41);
		AnswerWrapper.insertIntoAnswer(a42);
		AnswerWrapper.insertIntoAnswer(a43);
		AnswerWrapper.insertIntoAnswer(a44);

		// Creates an instance of the {@link Tag} entity
		Tag t1 = new Tag(UUID.randomUUID().toString(), "Binary Numbers");
		Tag t2 = new Tag(UUID.randomUUID().toString(), "SWC");
		Tag t3 = new Tag(UUID.randomUUID().toString(), "SWD");
		Tag t4 = new Tag(UUID.randomUUID().toString(), "ITO");
		Tag t5 = new Tag(UUID.randomUUID().toString(), "UP");
		Tag t6 = new Tag(UUID.randomUUID().toString(), "Kolb");
		Tag t7 = new Tag(UUID.randomUUID().toString(), "Emotional Competence");
		Tag t8 = new Tag(UUID.randomUUID().toString(), "Alderfe");

		// Inserts the created {@link Tag} instances in the database though the
		// {@link TagWrapper}
		TagWrapper.insertIntoTag(t1);
		TagWrapper.insertIntoTag(t2);
		TagWrapper.insertIntoTag(t3);
		TagWrapper.insertIntoTag(t4);
		TagWrapper.insertIntoTag(t5);
		TagWrapper.insertIntoTag(t6);
		TagWrapper.insertIntoTag(t7);
		TagWrapper.insertIntoTag(t8);

		// Creates an instance of the {@link QuestionTagRelation} entity
		QuestionTagRelation qtr1 = new QuestionTagRelation(q1.getQuestionNo(),
				t1.getTagNo());
		QuestionTagRelation qtr2 = new QuestionTagRelation(q1.getQuestionNo(),
				t2.getTagNo());
		QuestionTagRelation qtr3 = new QuestionTagRelation(q2.getQuestionNo(),
				t2.getTagNo());
		QuestionTagRelation qtr4 = new QuestionTagRelation(q3.getQuestionNo(),
				t2.getTagNo());
		QuestionTagRelation qtr5 = new QuestionTagRelation(q4.getQuestionNo(),
				t3.getTagNo());
		QuestionTagRelation qtr6 = new QuestionTagRelation(q4.getQuestionNo(),
				t5.getTagNo());
		QuestionTagRelation qtr7 = new QuestionTagRelation(q5.getQuestionNo(),
				t3.getTagNo());
		QuestionTagRelation qtr8 = new QuestionTagRelation(q6.getQuestionNo(),
				t3.getTagNo());
		QuestionTagRelation qtr9 = new QuestionTagRelation(q7.getQuestionNo(),
				t3.getTagNo());
		QuestionTagRelation qtr10 = new QuestionTagRelation(q8.getQuestionNo(),
				t3.getTagNo());
		QuestionTagRelation qtr11 = new QuestionTagRelation(q9.getQuestionNo(),
				t4.getTagNo());
		QuestionTagRelation qtr12 = new QuestionTagRelation(q9.getQuestionNo(),
				t7.getTagNo());
		QuestionTagRelation qtr13 = new QuestionTagRelation(
				q10.getQuestionNo(), t4.getTagNo());
		QuestionTagRelation qtr14 = new QuestionTagRelation(
				q10.getQuestionNo(), t6.getTagNo());
		QuestionTagRelation qtr15 = new QuestionTagRelation(
				q11.getQuestionNo(), t4.getTagNo());
		QuestionTagRelation qtr16 = new QuestionTagRelation(
				q11.getQuestionNo(), t8.getTagNo());

		// Inserts the created {@link QuestionTagRelation} instances in the
		// database though the {@link QuestionTagRelationWrapper}
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr1);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr2);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr3);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr4);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr5);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr6);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr7);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr8);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr9);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr10);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr11);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr12);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr13);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr14);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr15);
		QuestionTagRelationWrapper.insertIntoQuestionTagRelation(qtr16);

		// Creates an instance of the {@link ExerciseQuestionRelation} entity
		ExerciseQuestionRelation eqr1 = new ExerciseQuestionRelation(
				e1.getExerciseNo(), q1.getQuestionNo());
		ExerciseQuestionRelation eqr2 = new ExerciseQuestionRelation(
				e1.getExerciseNo(), q2.getQuestionNo());
		ExerciseQuestionRelation eqr3 = new ExerciseQuestionRelation(
				e1.getExerciseNo(), q3.getQuestionNo());
		ExerciseQuestionRelation eqr4 = new ExerciseQuestionRelation(
				e1.getExerciseNo(), q4.getQuestionNo());
		ExerciseQuestionRelation eqr5 = new ExerciseQuestionRelation(
				e2.getExerciseNo(), q5.getQuestionNo());
		ExerciseQuestionRelation eqr6 = new ExerciseQuestionRelation(
				e2.getExerciseNo(), q6.getQuestionNo());
		ExerciseQuestionRelation eqr7 = new ExerciseQuestionRelation(
				e2.getExerciseNo(), q7.getQuestionNo());
		ExerciseQuestionRelation eqr8 = new ExerciseQuestionRelation(
				e2.getExerciseNo(), q8.getQuestionNo());
		ExerciseQuestionRelation eqr9 = new ExerciseQuestionRelation(
				e3.getExerciseNo(), q9.getQuestionNo());
		ExerciseQuestionRelation eqr10 = new ExerciseQuestionRelation(
				e3.getExerciseNo(), q10.getQuestionNo());
		ExerciseQuestionRelation eqr11 = new ExerciseQuestionRelation(
				e3.getExerciseNo(), q11.getQuestionNo());

		// Inserts the created {@link ExerciseQuestionRelation} instances in the
		// database though the {@link ExerciseQuestionRelationWrapper}
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr1);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr2);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr3);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr4);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr5);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr6);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr7);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr8);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr9);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr10);
		ExerciseQuestionRelationWrapper
				.insertIntoExerciseQuestionRelation(eqr11);

		// Creates an instance of the {@link UserTestResult} entity
		UserTestResult utr1 = new UserTestResult(u8.getUserNo(),
				e1.getExerciseNo(), 24);
		UserTestResult utr2 = new UserTestResult(u8.getUserNo(),
				e2.getExerciseNo(), 17);
		UserTestResult utr3 = new UserTestResult(u9.getUserNo(),
				e2.getExerciseNo(), 17);
		UserTestResult utr4 = new UserTestResult(u9.getUserNo(),
				e3.getExerciseNo(), 20);
		UserTestResult utr5 = new UserTestResult(u10.getUserNo(),
				e1.getExerciseNo(), 24);
		UserTestResult utr6 = new UserTestResult(u10.getUserNo(),
				e3.getExerciseNo(), 20);
		UserTestResult utr7 = new UserTestResult(u11.getUserNo(),
				e1.getExerciseNo(), 24);
		UserTestResult utr8 = new UserTestResult(u11.getUserNo(),
				e2.getExerciseNo(), 17);

		// Inserts the created {@link UserTestResult} instances in the database
		// though the {@link UserTestResultWrapper}
		UserTestResultWrapper.insertIntoUserTestResult(utr1);
		UserTestResultWrapper.insertIntoUserTestResult(utr2);
		UserTestResultWrapper.insertIntoUserTestResult(utr3);
		UserTestResultWrapper.insertIntoUserTestResult(utr4);
		UserTestResultWrapper.insertIntoUserTestResult(utr5);
		UserTestResultWrapper.insertIntoUserTestResult(utr6);
		UserTestResultWrapper.insertIntoUserTestResult(utr7);
		UserTestResultWrapper.insertIntoUserTestResult(utr8);

		// Creates an instance of the {@link UserQuestionState} entity
		UserQuestionState uqs1 = new UserQuestionState(u8.getUserNo(),
				q1.getQuestionNo(), true);
		UserQuestionState uqs2 = new UserQuestionState(u8.getUserNo(),
				q2.getQuestionNo(), true);
		UserQuestionState uqs3 = new UserQuestionState(u8.getUserNo(),
				q3.getQuestionNo(), true);
		UserQuestionState uqs4 = new UserQuestionState(u8.getUserNo(),
				q4.getQuestionNo(), true);
		UserQuestionState uqs5 = new UserQuestionState(u8.getUserNo(),
				q5.getQuestionNo(), true);
		UserQuestionState uqs6 = new UserQuestionState(u8.getUserNo(),
				q6.getQuestionNo(), true);
		UserQuestionState uqs7 = new UserQuestionState(u8.getUserNo(),
				q7.getQuestionNo(), true);
		UserQuestionState uqs8 = new UserQuestionState(u8.getUserNo(),
				q8.getQuestionNo(), true);
		UserQuestionState uqs9 = new UserQuestionState(u8.getUserNo(),
				q9.getQuestionNo(), false);
		UserQuestionState uqs10 = new UserQuestionState(u8.getUserNo(),
				q10.getQuestionNo(), false);
		UserQuestionState uqs11 = new UserQuestionState(u8.getUserNo(),
				q11.getQuestionNo(), false);
		UserQuestionState uqs12 = new UserQuestionState(u9.getUserNo(),
				q1.getQuestionNo(), false);
		UserQuestionState uqs13 = new UserQuestionState(u9.getUserNo(),
				q2.getQuestionNo(), false);
		UserQuestionState uqs14 = new UserQuestionState(u9.getUserNo(),
				q3.getQuestionNo(), false);
		UserQuestionState uqs15 = new UserQuestionState(u9.getUserNo(),
				q4.getQuestionNo(), false);
		UserQuestionState uqs16 = new UserQuestionState(u9.getUserNo(),
				q5.getQuestionNo(), true);
		UserQuestionState uqs17 = new UserQuestionState(u9.getUserNo(),
				q6.getQuestionNo(), true);
		UserQuestionState uqs18 = new UserQuestionState(u9.getUserNo(),
				q7.getQuestionNo(), true);
		UserQuestionState uqs19 = new UserQuestionState(u9.getUserNo(),
				q8.getQuestionNo(), true);
		UserQuestionState uqs20 = new UserQuestionState(u9.getUserNo(),
				q9.getQuestionNo(), true);
		UserQuestionState uqs21 = new UserQuestionState(u9.getUserNo(),
				q10.getQuestionNo(), true);
		UserQuestionState uqs22 = new UserQuestionState(u9.getUserNo(),
				q11.getQuestionNo(), true);
		UserQuestionState uqs23 = new UserQuestionState(u10.getUserNo(),
				q1.getQuestionNo(), true);
		UserQuestionState uqs24 = new UserQuestionState(u10.getUserNo(),
				q2.getQuestionNo(), true);
		UserQuestionState uqs25 = new UserQuestionState(u10.getUserNo(),
				q3.getQuestionNo(), true);
		UserQuestionState uqs26 = new UserQuestionState(u10.getUserNo(),
				q4.getQuestionNo(), true);
		UserQuestionState uqs27 = new UserQuestionState(u10.getUserNo(),
				q5.getQuestionNo(), false);
		UserQuestionState uqs28 = new UserQuestionState(u10.getUserNo(),
				q6.getQuestionNo(), false);
		UserQuestionState uqs29 = new UserQuestionState(u10.getUserNo(),
				q7.getQuestionNo(), false);
		UserQuestionState uqs30 = new UserQuestionState(u10.getUserNo(),
				q8.getQuestionNo(), false);
		UserQuestionState uqs31 = new UserQuestionState(u10.getUserNo(),
				q9.getQuestionNo(), true);
		UserQuestionState uqs32 = new UserQuestionState(u10.getUserNo(),
				q10.getQuestionNo(), true);
		UserQuestionState uqs33 = new UserQuestionState(u10.getUserNo(),
				q11.getQuestionNo(), true);
		UserQuestionState uqs34 = new UserQuestionState(u11.getUserNo(),
				q1.getQuestionNo(), true);
		UserQuestionState uqs35 = new UserQuestionState(u11.getUserNo(),
				q2.getQuestionNo(), true);
		UserQuestionState uqs36 = new UserQuestionState(u11.getUserNo(),
				q3.getQuestionNo(), true);
		UserQuestionState uqs37 = new UserQuestionState(u11.getUserNo(),
				q4.getQuestionNo(), true);
		UserQuestionState uqs38 = new UserQuestionState(u11.getUserNo(),
				q5.getQuestionNo(), true);
		UserQuestionState uqs39 = new UserQuestionState(u11.getUserNo(),
				q6.getQuestionNo(), true);
		UserQuestionState uqs40 = new UserQuestionState(u11.getUserNo(),
				q7.getQuestionNo(), true);
		UserQuestionState uqs41 = new UserQuestionState(u11.getUserNo(),
				q8.getQuestionNo(), true);
		UserQuestionState uqs42 = new UserQuestionState(u11.getUserNo(),
				q9.getQuestionNo(), false);
		UserQuestionState uqs43 = new UserQuestionState(u11.getUserNo(),
				q10.getQuestionNo(), false);
		UserQuestionState uqs44 = new UserQuestionState(u11.getUserNo(),
				q11.getQuestionNo(), false);

		// Inserts the created {@link UserQuestionState} instances in the
		// database though the {@link UserQuestionStateWrapper}
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs1);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs2);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs3);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs4);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs5);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs6);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs7);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs8);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs9);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs10);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs11);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs12);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs13);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs14);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs15);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs16);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs17);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs18);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs19);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs20);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs21);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs22);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs23);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs24);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs25);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs26);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs27);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs28);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs29);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs30);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs31);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs32);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs33);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs34);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs35);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs36);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs37);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs38);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs39);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs40);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs41);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs42);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs43);
		UserQuestionStateWrapper.insertIntoUserQuestionState(uqs44);
	}

}
