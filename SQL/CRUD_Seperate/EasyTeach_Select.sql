/* Author: 	Oliver Nielsen
 * Date: 	03. December, 2013
 * Version:	1.1

 * Following is the SQL code (DML) for manipulating the tables for the 
 * "MBO EasyTeach" application.

 * Before manipulating the tables, please make sure that the database 
 * called "easyTeach" is in use. */

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
/* Data Manipulation Language */


/* Student Select Procedure */
DROP PROCEDURE IF EXISTS selectClassRows;
DROP PROCEDURE IF EXISTS selectClassRowWithClassNo;

DROP PROCEDURE IF EXISTS selectCourseRows;
DROP PROCEDURE IF EXISTS selectCourseRowWithCourseNo;

DROP PROCEDURE IF EXISTS selectUserRows;
DROP PROCEDURE IF EXISTS selectUserRowWithUserNo;

DROP PROCEDURE IF EXISTS selectQuestionRows;
DROP PROCEDURE IF EXISTS selectQuestionRowWithQuestionNo;

DROP PROCEDURE IF EXISTS selectTagRows;
DROP PROCEDURE IF EXISTS selectTagRowWithTagNo;

DROP PROCEDURE IF EXISTS selectAnswerRowWithQuestionNo;

DROP PROCEDURE IF EXISTS selectExerciseParameterRowWithExerciseParameterNo;

DROP PROCEDURE IF EXISTS selectExerciseRowWithExerciseNo;
DROP PROCEDURE IF EXISTS selectExerciseRowWithCourseNo;

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
	IN pClassNo				INTEGER(6))
BEGIN
	SELECT c.*
		FROM Class c
		WHERE c.classNo = pClassNo;
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
	IN pCourseNo			INTEGER(6))
BEGIN
	SELECT c.*
		FROM Course c
		WHERE c.courseNo = pCourseNo;
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
	IN pUserNo				INTEGER(6))
BEGIN
	SELECT u.*
		FROM User u
		WHERE u.userNo = pUserNo;
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
	IN pQuestionNo			INTEGER(6))
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
	IN pTagNo				INTEGER(6))
BEGIN
	SELECT t.*
		FROM Tag t
		WHERE t.tagNo = pTagNo;
END //
DELIMITER ;

/* Returns the answer for the specific questionNo */
DELIMITER //
CREATE PROCEDURE selectAnswerRowWithQuestionNo (
	IN pQuestionNo			INTEGER(6))
BEGIN
	SELECT a.*
		FROM Answer a
		WHERE a.questionNo = pQuestionNo;
END //
DELIMITER ;

/* Returns the exercise parameter with the specific exerciseParameterNo */
DELIMITER //
CREATE PROCEDURE selectExerciseParameterRowWithExerciseParameterNo (
	IN pExerciseParameterNo	INTEGER(10))
BEGIN
	SELECT ep.*
		FROM ExerciseParameter ep
		WHERE ep.exerciseParameterNo = pExerciseParameterNo;
END //
DELIMITER ;

/* Returns the exercise with the specific exerciseNo */
DELIMITER //
CREATE PROCEDURE selectExerciseRowWithExerciseNo (
	IN pExerciseNo			INTEGER(6))
BEGIN
	SELECT e.*
		FROM Exercise e
		WHERE e.exerciseNo = pExerciseNo;
END //
DELIMITER ;

/* Returns the exercise for the specific courseNo */
DELIMITER //
CREATE PROCEDURE selectExerciseRowWithCourseNo (
	IN pCourseNo			INTEGER(6))
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
	IN pClassNo				INTEGER(6))
BEGIN
	SELECT ccr.*
		FROM ClassCourseRelation ccr
		WHERE ccr.classNo = pClassNo;
END //
DELIMITER ;

/* Returns the class course relations with the specific courseNo */
DELIMITER //
CREATE PROCEDURE selectClassCourseRelationRowsWithCourseNo (
	IN pCourseNo			INTEGER(6))
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
	IN pUserNo				INTEGER(6))
BEGIN
	SELECT utr.*
		FROM UserTestResult utr
		WHERE utr.userNo = pUserNo;
END //
DELIMITER ;

/* Returns the user test results with the specific exerciseNo */
DELIMITER //
CREATE PROCEDURE selectUserTestResultRowsWithExerciseNo (
	IN pExerciseNo			INTEGER(6))
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
	IN pClassNo				INTEGER(6))
BEGIN
	SELECT cur.*
		FROM ClassUserRelation cur
		WHERE cur.classNo = pClassNo;
END //
DELIMITER ;

/* Returns the class user relations for the specific UserNo */
DELIMITER //
CREATE PROCEDURE selectClassUserRelationRowsWithUserNo (
	IN pUserNo				INTEGER(6))
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
	IN pUserNo				INTEGER(6))
BEGIN
	SELECT uqs.*
		FROM UserQuestionState uqs
		WHERE uqs.userNo = pUserNo;
END //
DELIMITER ;

/* Returns the user question states with the specific questionNo */
DELIMITER //
CREATE PROCEDURE selectUserQuestionStateRowsWithQuestionNo (
	IN pQuestionNo			INTEGER(6))
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
	IN pQuestionNo			INTEGER(6))
BEGIN
	SELECT qtr.*
		FROM QuestionTagRelation qtr
		WHERE qtr.questionNo = pQuestionNo;
END //
DELIMITER ;

/* Returns the question tag relations with the specific tagNo */
DELIMITER //
CREATE PROCEDURE selectQuestionTagRelationRowsWithTagNo (
	IN pTagNo				INTEGER(6))
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
	IN pExerciseNo			INTEGER(6))
BEGIN
	SELECT eqr.*
		FROM ExerciseQuestionRelation eqr
		WHERE eqr.exerciseNo = pExerciseNo;
END //
DELIMITER ;

/* Returns the exercise question relations with the specific questionNo */
DELIMITER //
CREATE PROCEDURE selectExerciseQuestionRelationRowsWithQuestionNo (
	IN pQuestionNo			INTEGER(6))
BEGIN
	SELECT eqr.*
		FROM ExerciseQuestionRelation eqr
		WHERE eqr.questionNo = pQuestionNo;
END //
DELIMITER ;

