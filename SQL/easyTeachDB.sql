/* Author: 	Morten Faarkrog
 * Date: 	27. November, 2013
 * Version:	1.01

 * Following is the SQL code (DDL) for creating the tables for the 
 * "MBO EasyTeach" application. The tables correspond to the database
 * design previously made. 

 * Before creating the tables, please create a database called "easyTeach".
 * CREATE DATABASE easyTeach; */


/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
/* Data Definition Language */

DROP TABLE IF EXISTS ExerciseQuestionRelation;
DROP TABLE IF EXISTS QuestionTagRelation;
DROP TABLE IF EXISTS UserQuestionState;
DROP TABLE IF EXISTS ClassUserRelation;
DROP TABLE IF EXISTS UserTestResult;
DROP TABLE IF EXISTS ClassCourseRelation;
DROP TABLE IF EXISTS Exercise;
DROP TABLE IF EXISTS ExerciseParameter;
DROP TABLE IF EXISTS Answer;
DROP TABLE IF EXISTS Tag;
DROP TABLE IF EXISTS Question;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Course;
DROP TABLE IF EXISTS Class;


CREATE TABLE Class (
	classNo		INTEGER(6)	NOT NULL 	AUTO_INCREMENT,
	year		INTEGER(4)	NOT NULL,
	className	VARCHAR(50)	NOT NULL,
	CONSTRAINT	pk_class	PRIMARY KEY (classNo)
) 	ENGINE 		InnoDB;


CREATE TABLE Course (
	courseNo	INTEGER(6)	NOT NULL 	AUTO_INCREMENT,
	courseName	VARCHAR(50)	NOT NULL,
	CONSTRAINT	pk_course	PRIMARY KEY (courseNo)
) 	ENGINE 		InnoDB;


CREATE TABLE User (
	userNo		INTEGER(6)	 NOT NULL 	AUTO_INCREMENT,
	email		VARCHAR(40)	 NOT NULL,
	userType	VARCHAR(30)	 NOT NULL	DEFAULT 'Student',
	firstName	VARCHAR(50)  NOT NULL,
	lastName	VARCHAR(50)  NOT NULL,
	password	VARCHAR(256) NOT NULL,
	dateAdded	DATE 		 NOT NULL,
	UNIQUE (email),
	CONSTRAINT	pk_user		 PRIMARY KEY (userNo)
) 	ENGINE 		InnoDB;


CREATE TABLE Question (
	questionNo		INTEGER(6)		NOT NULL 	AUTO_INCREMENT,
	questionType 	VARCHAR(50)		NOT NULL,
	question 		TEXT(65535)		NOT NULL,
	points			INTEGER(5),
	CONSTRAINT		pk_question		PRIMARY KEY (questionNo)
) 	ENGINE 			InnoDB;


CREATE TABLE Tag (
	tagNo		INTEGER(6)	NOT NULL 	AUTO_INCREMENT,
	tag			VARCHAR(50),
	CONSTRAINT	pk_tag		PRIMARY KEY (tagNo)
) 	ENGINE 		InnoDB;


CREATE TABLE Answer (
	questionNo	INTEGER(6)		NOT NULL,
	answerNo	INTEGER(10)		NOT NULL,
	answer 		TEXT(65535)		NOT NULL,
	isCorrect 	BOOLEAN			NOT NULL,
	CONSTRAINT	pk_answer		PRIMARY KEY (questionNo, answerNo),
	CONSTRAINT	fk_aQuestionNo	FOREIGN KEY (questionNo) 	
		REFERENCES 	Question (questionNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE
) 	ENGINE 		InnoDB;


CREATE TABLE ExerciseParameter (
	exerciseParameterNo	INTEGER(10)		NOT NULL	AUTO_INCREMENT,
	isTest				BOOLEAN			NOT NULL,
	isLocked			BOOLEAN,
	accessBegin			DATETIME,
	accessEnd			DATETIME,
	timeLimit			INTEGER(5),
	CONSTRAINT			pk_exerciseParameter		PRIMARY KEY (exerciseParameterNo)
) 	ENGINE 				InnoDB;


CREATE TABLE Exercise (
	exerciseNo			INTEGER(10)		NOT NULL	AUTO_INCREMENT,
	courseNo			INTEGER(6),
	author 				INTEGER(6),		/* userNo from User */
	exerciseParameterNo INTEGER(10)		NOT NULL,
	exerciseName		VARCHAR(100)	NOT NULL,
	dateAdded			DATE			NOT NULL,
	password			VARCHAR(256),
	CONSTRAINT	pk_exercise				PRIMARY KEY (exerciseNo),
	CONSTRAINT	fk_eCourseNo			FOREIGN KEY (courseNo) 	
		REFERENCES 	Course (courseNo) 
			ON UPDATE CASCADE 	ON DELETE SET NULL,
	CONSTRAINT	fk_eAuthor				FOREIGN KEY (author) 	
		REFERENCES 	User (userNo) 
			ON UPDATE CASCADE 	ON DELETE SET NULL,
	CONSTRAINT	fk_eExerciseParameterNo	FOREIGN KEY (exerciseParameterNo) 	
		REFERENCES 	ExerciseParameter (exerciseParameterNo) 
			ON UPDATE CASCADE 	ON DELETE RESTRICT
) 	ENGINE 		InnoDB;


CREATE TABLE ClassCourseRelation (
	classNo		INTEGER(10)				NOT NULL,
	courseNo	INTEGER(6)				NOT NULL,
	CONSTRAINT	pk_classCourseRelation	PRIMARY KEY (classNo, courseNo),
	CONSTRAINT	fk_ccrClassNo			FOREIGN KEY (classNo) 	
		REFERENCES 	Class (classNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE,
	CONSTRAINT	fk_ccrCourseNo			FOREIGN KEY (courseNo) 	
		REFERENCES 	Course (courseNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE
) 	ENGINE 		InnoDB;


CREATE TABLE UserTestResult (
	userNo		INTEGER(6)				NOT NULL,
	exerciseNo	INTEGER(6)				NOT NULL,
	score		INTEGER(5)				NOT NULL,
	CONSTRAINT	pk_userTestResult		PRIMARY KEY (userNo, exerciseNo),
	CONSTRAINT	fk_utrUserNo			FOREIGN KEY (userNo) 	
		REFERENCES 	User (userNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE,
	CONSTRAINT	fk_utrExerciseNo		FOREIGN KEY (exerciseNo) 	
		REFERENCES 	Exercise (exerciseNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE
) 	ENGINE 		InnoDB;


CREATE TABLE ClassUserRelation (
	classNo		INTEGER(6)				NOT NULL,
	userNo		INTEGER(6)				NOT NULL,
	CONSTRAINT	pk_classUserRelation	PRIMARY KEY (classNo, userNo),
	CONSTRAINT	fk_curClassNo			FOREIGN KEY (classNo) 	
		REFERENCES 	Class (classNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE,
	CONSTRAINT	fk_curUserNo			FOREIGN KEY (userNo) 	
		REFERENCES 	User (userNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE
) 	ENGINE 		InnoDB;


CREATE TABLE UserQuestionState (
	userNo			INTEGER(6)			NOT NULL,
	questionNo		INTEGER(6)			NOT NULL,
	hasCompleted 	BOOLEAN				NOT NULL,
	CONSTRAINT	pk_userQuestionState	PRIMARY KEY (userNo, questionNo),
	CONSTRAINT	fk_uqsUserNo			FOREIGN KEY (userNo) 	
		REFERENCES 	User (userNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE,
	CONSTRAINT	fk_uqsQuestionNo		FOREIGN KEY (questionNo) 	
		REFERENCES 	Question (questionNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE
) 	ENGINE 		InnoDB;


CREATE TABLE QuestionTagRelation (
	questionNo	INTEGER(6)				NOT NULL,
	tagNo		INTEGER(6)				NOT NULL,
	CONSTRAINT	pk_questionTagRelation	PRIMARY KEY (questionNo, tagNo),
	CONSTRAINT	fk_qtrQuestionNo		FOREIGN KEY (questionNo) 	
		REFERENCES 	Question (questionNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE,
	CONSTRAINT	fk_qtrTagNo				FOREIGN KEY (tagNo) 	
		REFERENCES 	Tag (tagNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE
) 	ENGINE 		InnoDB;


CREATE TABLE ExerciseQuestionRelation (
	exerciseNo	INTEGER(6)				NOT NULL,
	questionNo	INTEGER(6)				NOT NULL,
	CONSTRAINT	pk_questionTagRelation	PRIMARY KEY (exerciseNo, questionNo),
	CONSTRAINT	fk_eqrExerciseNo		FOREIGN KEY (exerciseNo) 	
		REFERENCES 	Exercise (exerciseNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE,
	CONSTRAINT	fk_eqrQuestionNo		FOREIGN KEY (questionNo) 	
		REFERENCES 	Question (questionNo) 
			ON UPDATE CASCADE 	ON DELETE CASCADE
) 	ENGINE 		InnoDB;

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

DELIMITER //
CREATE PROCEDURE insertIntoClass(
        IN year                 		INTEGER(4),
        IN className                    VARCHAR(50))
BEGIN
        START TRANSACTION;
        INSERT INTO Class (year, className)
                VALUES (year, className);
        COMMIT;
END //
DELIMITER ;

SELECT * FROM Class;
