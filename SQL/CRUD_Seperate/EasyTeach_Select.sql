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

/* Returns every course */
DELIMITER //
CREATE PROCEDURE selectCourseRows ()
BEGIN
	SELECT c.*
		FROM Course c;
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

/* Returns every question */
DELIMITER //
CREATE PROCEDURE selectQuestionRows ()
BEGIN
	SELECT q.*
		FROM Question q;
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

/* Returns every answer */
DELIMITER //
CREATE PROCEDURE selectAnswerRows ()
BEGIN
	SELECT a.*
		FROM Answer a;
END //
DELIMITER ;

/* Returns every exericse parameter */
DELIMITER //
CREATE PROCEDURE selectExerciseParameterRows ()
BEGIN
	SELECT ep.*
		FROM ExerciseParameter ep;
END //
DELIMITER ;

/* Returns every exercise */
DELIMITER //
CREATE PROCEDURE selectExerciseRows ()
BEGIN
	SELECT e.*
		FROM Exercise e;
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

/* Returns every user test result */
DELIMITER //
CREATE PROCEDURE selectUserTestResultRows ()
BEGIN
	SELECT utr.*
		FROM UserTestResult utr;
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

/* Returns every user question state */
DELIMITER //
CREATE PROCEDURE selectUserQuestionStateRows ()
BEGIN
	SELECT uqs.*
		FROM UserQuestionState uqs;
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

/* Returns every exercise question relation */
DELIMITER //
CREATE PROCEDURE selectExerciseQuestionRelationRows ()
BEGIN
	SELECT eqr.*
		FROM ExerciseQuestionRelation eqr;
END //
DELIMITER ;

