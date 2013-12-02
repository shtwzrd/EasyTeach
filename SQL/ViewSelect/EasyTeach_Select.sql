/* Author: 	Oliver Nielsen
 * Date: 	30. November, 2013
 * Version:	1.01

 * Following is the SQL code (DML) for manipulating the tables for the 
 * "MBO EasyTeach" application.

 * Before manipulating the tables, please make sure that the database 
 * called "easyTeach" is in use. */

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
/* Data Manipulation Language */


/* Student Select Procedure */
DROP PROCEDURE IF EXISTS selectExercise;
DROP PROCEDURE IF EXISTS selectExerciseWithExerciseNo;
DROP PROCEDURE IF EXISTS selectExerciseWithExerciseName;
DROP PROCEDURE IF EXISTS selectQuestionWithExerciseNo;
DROP PROCEDURE IF EXISTS selectUserTestResult;
DROP PROCEDURE IF EXISTS selectUserTestResultWithExerciseNo;

/* Teacher Select Procedure */
DROP PROCEDURE IF EXISTS selectQuestionWithTagNo;
DROP PROCEDURE IF EXISTS selectQuestionWithTag;
DROP PROCEDURE IF EXISTS selectTags;
DROP PROCEDURE IF EXISTS selectTagWithTagNo;
DROP PROCEDURE IF EXISTS selectTagWithTag;

/* Admin Select Procedure */
DROP PROCEDURE IF EXISTS selectUserToCourseWithClassNo;



/* Returns every exercise */
DELIMITER //
CREATE PROCEDURE selectExercise ()
BEGIN
	SELECT vse.*
		FROM v_StudentExercise vse;
END //
DELIMITER ;


/* Returns the exercise with the specific exerciseNo */
DELIMITER //
CREATE PROCEDURE selectExerciseWithExerciseNo (
	IN pExerciseNo			INTEGER(10))
BEGIN
	SELECT vse.*
		FROM v_StudentExercise vse
		WHERE vse.exerciseNo = pExerciseNo;
END //
DELIMITER ;


/* Returns the exercise with the specific exerciseName */
DELIMITER //
CREATE PROCEDURE selectExerciseWithExerciseName (
	IN pExerciseName		VARCHAR(100))
BEGIN
	SELECT vse.*
		FROM v_StudentExercise vse
		WHERE vse.exerciseName = pExerciseName;
END //
DELIMITER ;


/* Returns questions that's related with a specific exercise (eg. exerciseNo) */
DELIMITER //
CREATE PROCEDURE selectQuestionWithExerciseNo (
	IN pExerciseNo			INTEGER(10))
BEGIN
	SELECT q.*
		FROM v_StudentExercise vse
		INNER JOIN ExerciseQuestionRelation eqr
			ON eqr.exerciseNo = pExerciseNo
		INNER JOIN Question q
			ON q.questionNo = eqr.questionNo;
END //
DELIMITER ;


/* Returns test result for a specific user */
DELIMITER //
CREATE PROCEDURE selectUserTestResult ()
BEGIN
	SELECT vse.*, utr.*
		FROM v_StudentExercise vse
		INNER JOIN UserTestResult utr
			ON utr.exerciseNo = vse.exerciseNo
		INNER JOIN User u
			ON u.userNo = utr.userNo
		WHERE vse.isTest = TRUE;
END //
DELIMITER ;


/* Returns test result for a specific user that has the exerciseNo */
DELIMITER //
CREATE PROCEDURE selectUserTestResultWithExerciseNo (
	IN pExerciseNo			INTEGER(10))
BEGIN
	SELECT vse.*, utr.* 
		FROM v_StudentExercise vse
		INNER JOIN UserTestResult utr
			ON utr.exerciseNo = pExerciseNo
		INNER JOIN User u
			ON u.userNo = utr.userNo
		WHERE vse.isTest = TRUE;
END //
DELIMITER ;


/* Returns questions that's related with a specific tag (eg. tagNo) */
DELIMITER //
CREATE PROCEDURE selectQuestionWithTagNo (
	IN pTagNo				INTEGER(6))
BEGIN
	SELECT vtq.*
		FROM v_TeacherQuestion vtq
		INNER JOIN QuestionTagRelation qtr
			ON qtr.pTagNo = pTagNo;
END //
DELIMITER ;


/* Returns questions that's related with a specific tag (eg. tag) */
DELIMITER //
CREATE PROCEDURE selectQuestionWithTag (
	IN pTag					VARCHAR(50))
BEGIN
	SELECT q.*, vtt.*
		FROM v_TeacherTag vtt
		INNER JOIN QuestionTagRelation qtr
			ON qtr.pTag = pTag
		INNER JOIN Question q
			ON q.questionNo = qtr.questionNo;
END //
DELIMITER ;


/* Returns all tags */
DELIMITER //
CREATE PROCEDURE selectTags ()
BEGIN
	SELECT vtt.*
		FROM v_TeacherTag vtt;
END //
DELIMITER ;


/* Returns tags with a specific tagNo */
DELIMITER //
CREATE PROCEDURE selectTagWithTagNo (
	IN pTagNo				INTEGER(6))
BEGIN
	SELECT vtt.*
		FROM v_TeacherTag vtt
		WHERE vvt.tagNo = pTagNo;
END //
DELIMITER ;


/* Returns tag with a specific tag */
DELIMITER //
CREATE PROCEDURE selectTagWithTag (
	IN pTag					VARCHAR(50))
BEGIN
	SELECT vtt.*
		FROM v_TeacherTag vtt
		WHERE vvt.tag = pTag;
END //
DELIMITER ;


/* Returns users to be added into a new course from their class */
DELIMITER //
CREATE PROCEDURE selectUserToCourseWithClassNo (
	IN pClassNo				INTEGER(6))
BEGIN
	SELECT u.email, u.firstName, u.lastName, u.dateAdded
		FROM v_AdminClass vac
		INNER JOIN ClassUserRelation cur
			ON cur.classNo = vac.classNo
		INNER JOIN User u
			ON u.userNo = cur.userNo
		WHERE vac.classNo = pClassNo;
END //
DELIMITER ;



