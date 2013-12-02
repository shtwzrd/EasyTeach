/* Author: 	Tonni Hyldgaard
 * Date: 	30. November, 2013
 * Version:	1.05
 
 * Update:
 * 1.05:	Corrected mistakes

 * Following is the SQL code (DML) for updating the tables for the 
 * "MBO EasyTeach" application.

 * Before manipulating the tables, please make sure that the database 
 * called "easyTeach" is in use. */

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
/* Data Manipulation Language */

-- Class Table Update Procedure
DROP PROCEDURE IF EXISTS updateClassRow;

-- Course Table Update Procedure
DROP PROCEDURE IF EXISTS updateCourseRow;

-- User Table Update Procedures
DROP PROCEDURE IF EXISTS updateUserPassword;
DROP PROCEDURE IF EXISTS updateUserRow;

-- Question Table Update Procedures
DROP PROCEDURE IF EXISTS updateQuestionRow;

-- Tag Table Update Procedure
DROP PROCEDURE IF EXISTS updateTag;

-- Answer Table Update Procedures
DROP PROCEDURE IF EXISTS updateAnswerRow;

-- ExerciserParameter Table Update Procedure
DROP PROCEDURE IF EXISTS updateExerciseParameterRow;

-- Exercise Table Update Procedure
DROP PROCEDURE IF EXISTS updateExerciseRow;

-- UserQuestionState Table Update Procedure
DROP PROCEDURE IF EXISTS updateHasCompleted;


/* A concatenated procedure for updating the whole row in the Class table */
DELIMITER //
CREATE PROCEDURE updateClassRow (
	IN classNo 	INTEGER(6),
	IN year 		INTEGER(4),
	IN className 	VARCHAR(50))
BEGIN
	START TRANSACTION;
		UPDATE Class c
		SET c.year = year, c.className = className
			WHERE c.classNo = classNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for updating the courseName in a row in the Course table */
DELIMITER //
CREATE PROCEDURE updateCourseRow (
	IN courseNo		INTEGER(6),
	INOUT courseName 	VARCHAR(50))
BEGIN
	START TRANSACTION;
		UPDATE Course c
		SET c.courseName = courseName
			WHERE c.courseNo = courseNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for updating the password in a row in the User table */
DELIMITER //
CREATE PROCEDURE updateUserPassword (
	IN userNo		INTEGER(6),
	INOUT password 	VARCHAR(256))
BEGIN
	START TRANSACTION;
		UPDATE User u
		SET u.password = password
			WHERE u.userNo = userNo;
	COMMIT;
END //
DELIMITER ;

/* A concatenated procedure for updating the whole row in the Class table */
DELIMITER //
CREATE PROCEDURE updateUserRow (
	IN userNo		INTEGER(6),
	IN email 		VARCHAR (40),
	IN userType 	VARCHAR(30),
	IN firstName	VARCHAR(50),
	IN lastName 	VARCHAR(50),
	IN password 	VARCHAR(256))
BEGIN
	START TRANSACTION;
		UPDATE User u
		SET u.email = email, u.userType = userType, u.firstName = firstName, u.lastName = lastName
			 WHERE u.userNo = userNo;
		CALL updateUserPassword(userNo, password);
	COMMIT;
END //
DELIMITER ;

/* Procedure for updating the whole row in the Question table */
DELIMITER //
CREATE PROCEDURE updateQuestionRow (
	IN questionNo 	INTEGER(6),
	IN questionType VARCHAR(50),
	IN question 	TEXT(65535),
	IN points 		INTEGER(5))
BEGIN
	START TRANSACTION;
		UPDATE Question q
		SET q.questionType = questionType, q.question = question, q.points = points
			WHERE q.questionNo = questionNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for updating the tag in a row in the Tag table */
DELIMITER //
CREATE PROCEDURE updateTag (
	IN tagNo	INTEGER(6),
	INOUT tag 	VARCHAR(50))
BEGIN
	START TRANSACTION;
		UPDATE Tag t
		SET t.tag = tag
			WHERE t.tagNo = tagNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for updating all content in the the Answer table */
DELIMITER //
CREATE PROCEDURE updateAnswerRow (
	IN questionNo	INTEGER(6),
	IN answerNo 	INTEGER(10),
	IN answer 		TEXT(65535),
	IN isCorrect 	BOOLEAN)
BEGIN
	START TRANSACTION;
		UPDATE Answer a
		SET a.answer = answer, a.isCorrect = isCorrect
			WHERE a.questionNo = questionNo AND a.answerNo = answerNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for updating all content in the the ExerciseParameter table */
DELIMITER //
CREATE PROCEDURE updateExerciseParameterRow (
	IN exerciseParameterNo 	INTEGER(10),
	IN isTest				BOOLEAN,
	IN isLocked				BOOLEAN,
	IN accessBegin 			DATETIME,
	IN accessEnd			DATETIME,
	IN timeLimit			INTEGER(5))
BEGIN
	START TRANSACTION;
		UPDATE ExerciseParameter eP
		SET eP.isTest = isTest, eP.isLocked = isLocked, eP.accessBegin = accessBegin, eP.accessEnd = accessEnd, eP.timeLimit = timeLimit
			WHERE eP.exerciseParameterNo = exerciseParameterNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for updating all content in the the Exercise table */
DELIMITER //
CREATE PROCEDURE updateExerciseRow (
	IN exerciseNo			INTEGER(10),
	IN courseNo 			INTEGER(6),
	IN exerciseParameterNo 	INTEGER(18),
	IN exerciseName			VARCHAR(100),
	IN password				VARCHAR(256))
BEGIN
	START TRANSACTION;
		UPDATE Exercise e
		SET e.courseNo = courseNo, e.exerciseParameterNo = exerciseParameterNo, e.exerciseName = exerciseName, e.password = password
			WHERE e.exerciseNo = exerciseNo;
	COMMIT;
END //
DELIMITER ;

/* Procedure for updating the hasCompleted in a row in the UserQuestionState table */
DELIMITER //
CREATE PROCEDURE updateHasCompleted (
	IN userNo		INTEGER(6),
	IN questionNo	INTEGER(6),
	IN hasCompleted BOOLEAN)
BEGIN
	START TRANSACTION;
		UPDATE UserQuestionState uQS
		SET uQS.hasCompleted = hasCompleted
			WHERE uQS.userNo = userNo AND uQS.questionNo = questionNo;
	COMMIT;
END //
DELIMITER ;

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */