/* Author: 	Tonni Hyldgaard
 * Date: 	29. November, 2013
 * Version:	1.04

 * Following is the SQL code (DML) for manipulating the tables for the 
 * "MBO EasyTeach" application.

 * Before manipulating the tables, please make sure that the database 
 * called "easyTeach" is in use. */

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
/* Data Manipulation Language */

DROP PROCEDURE IF EXISTS insertIntoClass;
DROP PROCEDURE IF EXISTS insertIntoCourse;
DROP PROCEDURE IF EXISTS insertIntoUser;
DROP PROCEDURE IF EXISTS insertIntoQuestion;
DROP PROCEDURE IF EXISTS insertIntoTag;
DROP PROCEDURE IF EXISTS insertIntoAnswer;
DROP PROCEDURE IF EXISTS insertIntoExerciseParameter;
DROP PROCEDURE IF EXISTS insertIntoExercise;
DROP PROCEDURE IF EXISTS insertIntoClassCourseRelation;
DROP PROCEDURE IF EXISTS insertIntoUserTestResult;
DROP PROCEDURE IF EXISTS insertIntoClassUserRelation;
DROP PROCEDURE IF EXISTS insertIntoUserQuestionState;
DROP PROCEDURE IF EXISTS insertIntoQuestionTagRelation;
DROP PROCEDURE IF EXISTS insertIntoExerciseQuestionRelation;

/* Procedure for inserting a row into the Class table */
DELIMITER // 
CREATE PROCEDURE insertIntoClass(
	IN classNo				VARCHAR(36),
	IN year 				INTEGER(4),
	IN className 			VARCHAR(50))
BEGIN
	START TRANSACTION;
		INSERT INTO Class 
			VALUES (classNo, year, className);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the Course table */
DELIMITER //
CREATE PROCEDURE insertIntoCourse(
	IN courseNo				VARCHAR(36),
	IN courseName 			VARCHAR(50))
BEGIN
	START TRANSACTION;
		INSERT INTO Course
			VALUES (courseNo, courseName);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the User table */
DELIMITER //
CREATE PROCEDURE insertIntoUser(
	IN userNo				VARCHAR(36),
	IN email 				VARCHAR(40),
	IN userType 			VARCHAR(30),
	IN firstName 			VARCHAR(50),
	IN lastName 			VARCHAR(50),
	IN password 			VARCHAR(256))
BEGIN
	START TRANSACTION;
		INSERT INTO User
			VALUES (userNo, email, userType, firstName, lastName, password, CURDATE());
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the Question table */
DELIMITER //
CREATE PROCEDURE insertIntoQuestion(
	IN questionNo			VARCHAR(36),
	IN questionType			VARCHAR(50),
	IN question 			TEXT(65535),
	IN points				INTEGER(5))
BEGIN
	START TRANSACTION;
		INSERT INTO Question
			VALUES (questionNo, questionType, question, points);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the Tag table */
DELIMITER //
CREATE PROCEDURE insertIntoTag (
	IN tagNo				VARCHAR(36),
	IN tag					VARCHAR(50))
BEGIN
	START TRANSACTION;
		INSERT INTO Tag
			VALUES (tagNo, tag);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the Answer table */
DELIMITER //
CREATE PROCEDURE insertIntoAnswer (
	IN questionNo			VARCHAR(36),
	IN answerNo				VARCHAR(36),
	IN answer				TEXT(65535),
	IN isCorrect			BOOLEAN)
BEGIN
	START TRANSACTION;
		INSERT INTO Answer 
			VALUES (questionNo, answerNo, answer, isCorrect);
	COMMIT;
END //
DELIMITER ;	

/* Procedure for inserting a row into the ExerciseParameter table */
DELIMITER //
CREATE PROCEDURE insertIntoExerciseParameter (
	IN exerciseParameterNo	VARCHAR(36),
	IN isTest				BOOLEAN,
	IN isLocked				BOOLEAN,
	IN accessBegin			DATETIME,
	IN accessEnd			DATETIME,
	IN timeLimit			INTEGER(5))
BEGIN
	START TRANSACTION;
		INSERT INTO ExerciseParameter
			VALUES (exerciseParameterNo, isTest, isLocked, accessBegin, accessEnd, timeLimit);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the Exercise table */
DELIMITER //
CREATE PROCEDURE insertIntoExercise (
	IN exerciseNo			VARCHAR(36),
	IN courseNo				VARCHAR(36),
	IN author				VARCHAR(36),
	IN exerciseParameterNo	VARCHAR(36),
	IN exerciseName			VARCHAR(100),
	IN dateAdded			DATE,
	IN password				VARCHAR(256))
BEGIN
	START TRANSACTION;
		INSERT INTO Exercise
			VALUES (exerciseNo, courseNo, author, exerciseParameterNo, exerciseName, dateAdded, password);
	COMMIT;
END //	
DELIMITER ;

/* Procedure for inserting a row into the ClassCourseRelation table */
DELIMITER //
CREATE PROCEDURE insertIntoClassCourseRelation (
	IN classNo				VARCHAR(36),
	IN courseNo				VARCHAR(36))
BEGIN 
	START TRANSACTION;
		INSERT INTO ClassCourseRelation 
			VALUES (classNo, courseNo);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the UserTestResult table */
DELIMITER //
CREATE PROCEDURE insertIntoUserTestResult (
	IN userNo				VARCHAR(36),
	IN exerciseNo			VARCHAR(36),
	IN score				INTEGER(5))
BEGIN 
	START TRANSACTION;
		INSERT INTO UserTestResult
			VALUES (userNo, exerciseNo, score);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the ClassUserRelation table */
DELIMITER //
CREATE PROCEDURE insertIntoClassUserRelation (
	IN classNo				VARCHAR(36),
	IN userNo				VARCHAR(36))
BEGIN 
	START TRANSACTION;
		INSERT INTO ClassUserRelation 
			VALUES (classNo, userNo);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the UserQuestionState table */
DELIMITER //
CREATE PROCEDURE insertIntoUserQuestionState (
	IN userNo				VARCHAR(36),
	IN questionNo			VARCHAR(36),
	IN hasCompleted 		BOOLEAN)
BEGIN
	START TRANSACTION;
		INSERT INTO UserQuestionState
			VALUES (userNo, questionNo, hasCompleted);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the QuestionTagRelation table */
DELIMITER //
CREATE PROCEDURE insertIntoQuestionTagRelation (
	IN questionNo			VARCHAR(36),
	IN tagNo				VARCHAR(36))
BEGIN
	START TRANSACTION;
		INSERT INTO QuestionTagRelation
			VALUES (questionNo, tagNo);
	COMMIT;
END //
DELIMITER ;

/* Procedure for inserting a row into the ExerciseQuestionRelation table */
DELIMITER //
CREATE PROCEDURE insertIntoExerciseQuestionRelation (
	IN exerciseNo			VARCHAR(36),
	IN questionNo			VARCHAR(36))
BEGIN
	START TRANSACTION;
		INSERT INTO ExerciseQuestionRelation
			VALUES (exerciseNo, questionNo);
	COMMIT;
END //
DELIMITER ;

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */