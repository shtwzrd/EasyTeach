/* Author: 	Tonni Hyldgaard
 * Date: 	29. November, 2013
 * Version:	1.01

 * Following is the SQL code (DML) for deleting the tables for the 
 * "MBO EasyTeach" application.

 * Before manipulating the tables, please make sure that the database 
 * called "easyTeach" is in use. */

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
/* Data Manipulation Language */
DROP PROCEDURE IF EXISTS deleteClassRow;
DROP PROCEDURE IF EXISTS deleteCourseRow;
DROP PROCEDURE IF EXISTS deleteUserRow;
DROP PROCEDURE IF EXISTS deleteQuestionRow;
DROP PROCEDURE IF EXISTS deleteTagRow;
DROP PROCEDURE IF EXISTS deleteAnswerRow;
DROP PROCEDURE IF EXISTS deleteExerciseParameterRow;
DROP PROCEDURE IF EXISTS deleteExerciseRow;
DROP PROCEDURE IF EXISTS deleteClassCourseRelationRow;
DROP PROCEDURE IF EXISTS deleteUserTestResultRow;
DROP PROCEDURE IF EXISTS deleteClassUserRelationRow;
DROP PROCEDURE IF EXISTS deleteUserQuestionStateRow;
DROP PROCEDURE IF EXISTS deleteQuestionTagRelationRow;
DROP PROCEDURE IF EXISTS deleteExerciseQuestionRelationRow;

/* Procedure for deleting a row from the Class table */
DELIMITER //
CREATE PROCEDURE deleteClassRow (
	IN pClassNo VARCHAR(32))
BEGIN
	START TRANSACTION;
	DELETE FROM Class 
		WHERE classNo = pClassNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the Course table */
DELIMITER //
CREATE PROCEDURE deleteCourseRow (
	IN pCourseNo VARCHAR(32))
BEGIN
	START TRANSACTION;
	DELETE FROM Course 
		WHERE courseNo = pCourseNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the User table */
DELIMITER //
CREATE PROCEDURE deleteUserRow (
	IN pUserNo VARCHAR(32))
BEGIN 
	START TRANSACTION;
	DELETE FROM User 
		WHERE userNo = pUserNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the Question table */
DELIMITER //
CREATE PROCEDURE deleteQuestionRow (
	IN pQuestionNo VARCHAR(32))
BEGIN 
	START TRANSACTION;
	DELETE FROM Question
		WHERE questionNo = pQuestionNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the Tag table */
DELIMITER //
CREATE PROCEDURE deleteTagRow (
	IN pTagNo VARCHAR(32))
BEGIN 
	START TRANSACTION;
	DELETE FROM Tag
		WHERE tagNo = pTagNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the Answer table */
DELIMITER //
CREATE PROCEDURE deleteAnswerRow (
	IN pQuestionNo			VARCHAR(32),
	IN pAnswerNo			VARCHAR(32))
BEGIN
	START TRANSACTION;
		DELETE FROM Answer
			WHERE answerNo = pAnswerNo AND questionNo = pQuestionNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the ExerciseParameter table */
DELIMITER //
CREATE PROCEDURE deleteExerciseParameterRow (
	IN pExerciseParameterNo VARCHAR(32))
BEGIN 
	START TRANSACTION;
	DELETE FROM ExerciseParameter
		WHERE exerciseParameterNo = pExerciseParameterNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the Exercise table */
DELIMITER //
CREATE PROCEDURE deleteExerciseRow (
	IN pExerciseNo VARCHAR(32))
BEGIN
	START TRANSACTION;
	DELETE FROM Exercise
		WHERE exerciseNo = pExerciseNo;
	COMMIT;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE deleteClassCourseRelationRow (
	IN pClassNo 	VARCHAR(32),
	IN pCourseNo 	VARCHAR(32))
BEGIN
	START TRANSACTION;
	DELETE FROM ClassCourseRelation
		WHERE classNo = pClassNo AND courseNo = pCourseNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the UserTestResults table */
DELIMITER //
CREATE PROCEDURE deleteUserTestResultRow (
	IN pUserNo 		VARCHAR(32),
	IN pExerciseNo	VARCHAR(32))
BEGIN 
	START TRANSACTION;
	DELETE FROM UserTestResult
		WHERE userNo = pUserNo AND exerciseNo = pExerciseNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the ClassUserRelation table */
DELIMITER //
CREATE PROCEDURE deleteClassUserRelationRow (
	IN pClassNo VARCHAR(32),
	IN pUserNo	VARCHAR(32))
BEGIN 
	START TRANSACTION;
	DELETE FROM ClassUserRelation
		WHERE classNo = pClassNo AND userNo = pUserNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the UserQuestionState table */
DELIMITER //
CREATE PROCEDURE deleteUserQuestionStateRow (
	IN pUserNo 		VARCHAR(32),
	IN pQuestionNo	VARCHAR(32))
BEGIN
	START TRANSACTION;
	DELETE FROM UserQuestionState
		WHERE userNo = pUserNo AND questionNo = pQuestionNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the QuestionTagRelation table */
DELIMITER //
CREATE PROCEDURE deleteQuestionTagRelationRow (
	IN pQuestionNo 	VARCHAR(32),
	IN pTagNo		VARCHAR(32))
BEGIN 
	START TRANSACTION;
	DELETE FROM QuestionTagRelation
		WHERE questionNo = pQuestionNo AND tagNo = pTagNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for deleting a row from the ExerciseQuestionRelation table */
DELIMITER //
CREATE PROCEDURE deleteExerciseQuestionRelationRow (
	IN pExerciseNo	VARCHAR(32),
	IN pQuestionNo	VARCHAR(32))
BEGIN
	START TRANSACTION;
	DELETE FROM ExerciseQuestionRelation
		WHERE exerciseNo = pExerciseNo AND questionNo = pQuestionNo;
	COMMIT;
END //
DELIMITER ;



/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */