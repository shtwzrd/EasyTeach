/* Author: 	Oliver Nielsen
 * Date: 	06. December, 2013
 * Version:	1.2

 * Following is the SQL code (DML) for manipulating the tables for the 
 * "MBO EasyTeach" application.

 * Before manipulating the tables, please make sure that the database 
 * called "easyTeach" is in use. */

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
/* Data Manipulation Language */


/* Student Select Procedure */
DROP PROCEDURE IF EXISTS selectClassRows;
DROP PROCEDURE IF EXISTS selectClassRowWithClassNo;
DROP PROCEDURE IF EXISTS selectClassRowWithClassName;

DROP PROCEDURE IF EXISTS selectCourseRows;
DROP PROCEDURE IF EXISTS selectCourseRowWithCourseNo;
DROP PROCEDURE IF EXISTS selectCourseRowWithCourseName;

DROP PROCEDURE IF EXISTS selectUserRows;
DROP PROCEDURE IF EXISTS selectUserRowWithUserNo;
DROP PROCEDURE IF EXISTS selectUserRowWithEmail;

DROP PROCEDURE IF EXISTS selectQuestionRows;
DROP PROCEDURE IF EXISTS selectQuestionRowWithQuestionNo;

DROP PROCEDURE IF EXISTS selectTagRows;
DROP PROCEDURE IF EXISTS selectTagRowWithTagNo;

DROP PROCEDURE IF EXISTS selectAnswerRows;
DROP PROCEDURE IF EXISTS selectAnswerRowsWithQuestionNo;

DROP PROCEDURE IF EXISTS selectExerciseParameterRows;
DROP PROCEDURE IF EXISTS selectExerciseParameterRowWithExerciseParameterNo;

DROP PROCEDURE IF EXISTS selectExerciseRows;
DROP PROCEDURE IF EXISTS selectExerciseRowWithExerciseNo;
DROP PROCEDURE IF EXISTS selectExerciseRowsWithCourseNo;

DROP PROCEDURE IF EXISTS selectClassCourseRelationRows;
DROP PROCEDURE IF EXISTS selectClassCourseRelationRowsWithClassNo;
DROP PROCEDURE IF EXISTS selectClassCourseRelationRowsWithCourseNo;

DROP PROCEDURE IF EXISTS selectUserTestResultRows;
DROP PROCEDURE IF EXISTS selectUserTestResultRowsWithUserNo;
DROP PROCEDURE IF EXISTS selectUserTestResultRowsWithExerciseNo;

DROP PROCEDURE IF EXISTS selectClassUserRelationRows;
DROP PROCEDURE IF EXISTS selectClassUserRelationRowsWithClassNo;
DROP PROCEDURE IF EXISTS selectClassUserRelationRowsWithUserNo;

DROP PROCEDURE IF EXISTS selectUserQuestionStateRows;
DROP PROCEDURE IF EXISTS selectUserQuestionStateRowsWithUserNo;
DROP PROCEDURE IF EXISTS selectUserQuestionStateRowsWithQuestionNo;

DROP PROCEDURE IF EXISTS selectQuestionTagRelationRows;
DROP PROCEDURE IF EXISTS selectQuestionTagRelationRowsWithQuestionNo;
DROP PROCEDURE IF EXISTS selectQuestionTagRelationRowsWithTagNo;

DROP PROCEDURE IF EXISTS selectExerciseQuestionRelationRows;
DROP PROCEDURE IF EXISTS selectExerciseQuestionRelationRowsWithExerciseNo;
DROP PROCEDURE IF EXISTS selectExerciseQuestionRelationRowsWithQuestionNo;


/* NEW DOMAIN LOGIC PROCEDURES DROPS */
DROP PROCEDURE IF EXISTS selectUserRowsWithClassNo;

DROP PROCEDURE IF EXISTS selectClassRowsWithUserNo;

DROP PROCEDURE IF EXISTS selectExerciseRowsWithTagNo;

DROP PROCEDURE IF EXISTS selectQuestionRowsWithTagNo;

DROP PROCEDURE IF EXISTS selectExerciseRowsWithTag;

DROP PROCEDURE IF EXISTS selectQuestionRowsWithTag;

DROP PROCEDURE IF EXISTS selectTagRowWithTag;

/* Returns every user for a class */
DELIMITER //
CREATE PROCEDURE selectUserRowsWithClassNo (
	IN pClassNo			VARCHAR(36))
BEGIN
	SELECT u.*
		FROM User u
		INNER JOIN ClassUserRelation cur
			ON cur.userNo = u.userNo
		WHERE cur.classNo = pClassNo;
END //
DELIMITER ;

/* Returns every class for a user */
DELIMITER //
CREATE PROCEDURE selectClassRowsWithUserNo (
	IN pUserNo			VARCHAR(36))
BEGIN
	SELECT c.*
		FROM Class c
		INNER JOIN ClassUserRelation cur
			ON cur.classNo = c.classNo
		WHERE cur.userNo = pUserNo;
END //
DELIMITER ;

/* Returns every exercise with questions that has a relation to the specific tagNo */
DELIMITER //
CREATE PROCEDURE selectExerciseRowsWithTagNo (
	IN pTagNo			VARCHAR(36))
BEGIN
	SELECT e.*
		FROM Exercise e
		INNER JOIN ExerciseQuestionRelation eqr
			ON eqr.exerciseNo = e.exerciseNo
		INNER JOIN Question q
			ON q.questionNo = eqr.questionNo
		INNER JOIN QuestionTagRelation qtr
			ON qtr.questionNo = q.questionNo
		WHERE qtr.tagNo = pTagNo;
END //
DELIMITER ;

/* Returns every question that has a relation to the specific tagNo */
DELIMITER //
CREATE PROCEDURE selectQuestionRowsWithTagNo (
	IN pTagNo			VARCHAR(36))
BEGIN
	SELECT q.*
		FROM Question q
		INNER JOIN QuestionTagRelation qtr
			ON qtr.questionNo = q.questionNo
		WHERE qtr.tagNo = pTagNo;
END //
DELIMITER ;

/* RUN THIS!!!! */

/* Returns every exercise with questions that has a relation to the specific tag */
DELIMITER //
CREATE PROCEDURE selectExerciseRowsWithTag (
	IN pTag				VARCHAR(50))
BEGIN
	SELECT e.*
		FROM Exercise e
		INNER JOIN ExerciseQuestionRelation eqr
			ON eqr.exerciseNo = e.exerciseNo
		INNER JOIN Question q
			ON q.questionNo = eqr.questionNo
		INNER JOIN QuestionTagRelation qtr
			ON qtr.questionNo = q.questionNo
		INNER JOIN Tag t
			ON t.tagNo = qtr.tagNo
		WHERE t.tag = pTag;
END //
DELIMITER ;

/* Returns every question that has a relation to the specific tag */
DELIMITER //
CREATE PROCEDURE selectQuestionRowsWithTag (
	IN pTag				VARCHAR(50))
BEGIN
	SELECT q.*
		FROM Question q
		INNER JOIN QuestionTagRelation qtr
			ON qtr.questionNo = q.questionNo
		INNER JOIN Tag t
			ON t.tagNo = qtr.tagNo
		WHERE t.tag = pTag;
END //
DELIMITER ;

/* Returns the tag that has the specific tagNo */
DELIMITER //
CREATE PROCEDURE selectTagRowWithTag (
	IN pTagNo			VARCHAR(36))
BEGIN
	SELECT t.*
		FROM Tag t
		WHERE t.tagNo = tTagNo;
END //
DELIMITER ;














/* Returns every class */
DELIMITER //
CREATE PROCEDURE selectClassRows ()
BEGIN
	SELECT c.*
		FROM Class c;
END //
DELIMITER ;

/* Returns the class with the specific classNo */
DELIMITER //
CREATE PROCEDURE selectClassRowWithClassNo (
	IN pClassNo				VARCHAR(36))
BEGIN
	SELECT c.*
		FROM Class c
		WHERE c.classNo = pClassNo;
END //
DELIMITER ;

/* Returns the class with the specific className */
DELIMITER //
CREATE PROCEDURE selectClassRowWithClassName (
	IN pClassName			VARCHAR(50))
BEGIN
	SELECT c.*
		FROM Class c
		WHERE c.className = pClassName;
END //
DELIMITER ;

/* Returns every course */
DELIMITER //
CREATE PROCEDURE selectCourseRows ()
BEGIN
	SELECT c.*
		FROM Course c;
END //
DELIMITER ;

/* Returns the course with the specific courseNo */
DELIMITER //
CREATE PROCEDURE selectCourseRowWithCourseNo (
	IN pCourseNo			VARCHAR(36))
BEGIN
	SELECT c.*
		FROM Course c
		WHERE c.courseNo = pCourseNo;
END //
DELIMITER ;

/* Returns the course with the specific courseName */
DELIMITER //
CREATE PROCEDURE selectCourseRowWithCourseName (
	IN pCourseName			VARCHAR(50))
BEGIN
	SELECT c.*
		FROM Course c
		WHERE c.courseName = pCourseName;
END //
DELIMITER ;

/* Returns every user */
DELIMITER //
CREATE PROCEDURE selectUserRows ()
BEGIN
	SELECT u.*
		FROM User u;
END //
DELIMITER ;

/* Returns the user with the specific userNo */
DELIMITER //
CREATE PROCEDURE selectUserRowWithUserNo (
	IN pUserNo				VARCHAR(36))
BEGIN
	SELECT u.*
		FROM User u
		WHERE u.userNo = pUserNo;
END //
DELIMITER ;

/* Returns the user with the specific email */
DELIMITER //
CREATE PROCEDURE selectUserRowWithEmail (
	IN pEmail				VARCHAR(256))
BEGIN
	SELECT u.*
		FROM User u
		WHERE u.email = pEmail;
END //
DELIMITER ;

/* Returns every question */
DELIMITER //
CREATE PROCEDURE selectQuestionRows ()
BEGIN
	SELECT q.*
		FROM Question q;
END //
DELIMITER ;

/* Returns the question with the specific questionNo */
DELIMITER //
CREATE PROCEDURE selectQuestionRowWithQuestionNo (
	IN pQuestionNo			VARCHAR(36))
BEGIN
	SELECT q.*
		FROM Question q
		WHERE q.questionNo = pQuestionNo;
END //
DELIMITER ;

/* Returns every tag */
DELIMITER //
CREATE PROCEDURE selectTagRows ()
BEGIN
	SELECT t.*
		FROM Tag t;
END //
DELIMITER ;

/* Returns the tag with the specific tagNo */
DELIMITER //
CREATE PROCEDURE selectTagRowWithTagNo (
	IN pTagNo				VARCHAR(36))
BEGIN
	SELECT t.*
		FROM Tag t
		WHERE t.tagNo = pTagNo;
END //
DELIMITER ;

/* Returns all answers */
DELIMITER //
CREATE PROCEDURE selectAnswerRows()
BEGIN
	SELECT a.*
		FROM Answer a;
END //
DELIMITER ;

/* Returns the answers for the specific questionNo */
DELIMITER //
CREATE PROCEDURE selectAnswerRowsWithQuestionNo (
	IN pQuestionNo			VARCHAR(36))
BEGIN
	SELECT a.*
		FROM Answer a
		WHERE a.questionNo = pQuestionNo;
END //
DELIMITER ;

/*  Returns all of the exercise parameters */
DELIMITER //
CREATE PROCEDURE selectExerciseParameterRows ()
BEGIN
	SELECT eP.*
		FROM ExerciseParameter eP;
END //
DELIMITER ;

/* Returns the exercise parameter with the specific exerciseParameterNo */
DELIMITER //
CREATE PROCEDURE selectExerciseParameterRowWithExerciseParameterNo (
	IN pExerciseParameterNo	VARCHAR(36))
BEGIN
	SELECT ep.*
		FROM ExerciseParameter ep
		WHERE ep.exerciseParameterNo = pExerciseParameterNo;
END //
DELIMITER ;

/* Returns all the exercises */
DELIMITER //
CREATE PROCEDURE selectExerciseRows ()
BEGIN
	SELECT e.*
		FROM Exercise e;
END //
DELIMITER ;

/* Returns the exercise with the specific exerciseNo */
DELIMITER //
CREATE PROCEDURE selectExerciseRowWithExerciseNo (
	IN pExerciseNo			VARCHAR(36))
BEGIN
	SELECT e.*
		FROM Exercise e
		WHERE e.exerciseNo = pExerciseNo;
END //
DELIMITER ;

/* Returns the exercises for the specific courseNo */
DELIMITER //
CREATE PROCEDURE selectExerciseRowsWithCourseNo (
	IN pCourseNo			VARCHAR(36))
BEGIN
	SELECT e.*
		FROM Exercise e
		WHERE e.courseNo = pCourseNo;
END //
DELIMITER ;

/* Returns every class course relation */
DELIMITER //
CREATE PROCEDURE selectClassCourseRelationRows ()
BEGIN
	SELECT ccr.*
		FROM ClassCourseRelation ccr;
END //
DELIMITER ;

/* Returns the class course relations with the specific classNo */
DELIMITER //
CREATE PROCEDURE selectClassCourseRelationRowsWithClassNo (
	IN pClassNo				VARCHAR(36))
BEGIN
	SELECT ccr.*
		FROM ClassCourseRelation ccr
		WHERE ccr.classNo = pClassNo;
END //
DELIMITER ;

/* Returns the class course relations with the specific courseNo */
DELIMITER //
CREATE PROCEDURE selectClassCourseRelationRowsWithCourseNo (
	IN pCourseNo			VARCHAR(36))
BEGIN
	SELECT ccr.*
		FROM ClassCourseRelation ccr
		WHERE ccr.courseNo = pCourseNo;
END //
DELIMITER ;

/* Returns every user test result */
DELIMITER //
CREATE PROCEDURE selectUserTestResultRows ()
BEGIN
	SELECT utr.*
		FROM UserTestResult utr;
END //
DELIMITER ;

/* Returns the user test results for the specific userNo */
DELIMITER //
CREATE PROCEDURE selectUserTestResultRowsWithUserNo (
	IN pUserNo				VARCHAR(36))
BEGIN
	SELECT utr.*
		FROM UserTestResult utr
		WHERE utr.userNo = pUserNo;
END //
DELIMITER ;

/* Returns the user test results with the specific exerciseNo */
DELIMITER //
CREATE PROCEDURE selectUserTestResultRowsWithExerciseNo (
	IN pExerciseNo			VARCHAR(36))
BEGIN
	SELECT utr.*
		FROM UserTestResult utr
		WHERE utr.exerciseNo = pExerciseNo;
END //
DELIMITER ;

/* Returns every class user relation */
DELIMITER //
CREATE PROCEDURE selectClassUserRelationRows ()
BEGIN
	SELECT cur.*
		FROM ClassUserRelation cur;
END //
DELIMITER ;

/* Returns the class user relations with the specific classNo */
DELIMITER //
CREATE PROCEDURE selectClassUserRelationRowsWithClassNo (
	IN pClassNo				VARCHAR(36))
BEGIN
	SELECT cur.*
		FROM ClassUserRelation cur
		WHERE cur.classNo = pClassNo;
END //
DELIMITER ;

/* Returns the class user relations for the specific UserNo */
DELIMITER //
CREATE PROCEDURE selectClassUserRelationRowsWithUserNo (
	IN pUserNo				VARCHAR(36))
BEGIN
	SELECT cur.*
		FROM ClassUserRelation cur
		WHERE cur.userNo = pUserNo;
END //
DELIMITER ;

/* Returns every user question state */
DELIMITER //
CREATE PROCEDURE selectUserQuestionStateRows ()
BEGIN
	SELECT uqs.*
		FROM UserQuestionState uqs;
END //
DELIMITER ;

/* Returns the user question states for the specific userNo */
DELIMITER //
CREATE PROCEDURE selectUserQuestionStateRowsWithUserNo (
	IN pUserNo				VARCHAR(36))
BEGIN
	SELECT uqs.*
		FROM UserQuestionState uqs
		WHERE uqs.userNo = pUserNo;
END //
DELIMITER ;

/* Returns the user question states with the specific questionNo */
DELIMITER //
CREATE PROCEDURE selectUserQuestionStateRowsWithQuestionNo (
	IN pQuestionNo			VARCHAR(36))
BEGIN
	SELECT uqs.*
		FROM UserQuestionState uqs
		WHERE uqs.questionNo = pQuestionNo;
END //
DELIMITER ;

/* Returns every question tag relation */
DELIMITER //
CREATE PROCEDURE selectQuestionTagRelationRows ()
BEGIN
	SELECT qtr.*
		FROM QuestionTagRelation qtr;
END //
DELIMITER ;

/* Returns the question tag relations with the specific questionNo */
DELIMITER //
CREATE PROCEDURE selectQuestionTagRelationRowsWithQuestionNo (
	IN pQuestionNo			VARCHAR(36))
BEGIN
	SELECT qtr.*
		FROM QuestionTagRelation qtr
		WHERE qtr.questionNo = pQuestionNo;
END //
DELIMITER ;

/* Returns the question tag relations with the specific tagNo */
DELIMITER //
CREATE PROCEDURE selectQuestionTagRelationRowsWithTagNo (
	IN pTagNo				VARCHAR(36))
BEGIN
	SELECT qtr.*
		FROM QuestionTagRelation qtr
		WHERE qtr.tagNo = pTagNo;
END //
DELIMITER ;

/* Returns every exercise question relation */
DELIMITER //
CREATE PROCEDURE selectExerciseQuestionRelationRows ()
BEGIN
	SELECT eqr.*
		FROM ExerciseQuestionRelation eqr;
END //
DELIMITER ;

/* Returns the exercise question relations with the specific exerciseNo */
DELIMITER //
CREATE PROCEDURE selectExerciseQuestionRelationRowsWithExerciseNo (
	IN pExerciseNo			VARCHAR(36))
BEGIN
	SELECT eqr.*
		FROM ExerciseQuestionRelation eqr
		WHERE eqr.exerciseNo = pExerciseNo;
END //
DELIMITER ;

/* Returns the exercise question relations with the specific questionNo */
DELIMITER //
CREATE PROCEDURE selectExerciseQuestionRelationRowsWithQuestionNo (
	IN pQuestionNo			VARCHAR(36))
BEGIN
	SELECT eqr.*
		FROM ExerciseQuestionRelation eqr
		WHERE eqr.questionNo = pQuestionNo;
END //
DELIMITER ;

