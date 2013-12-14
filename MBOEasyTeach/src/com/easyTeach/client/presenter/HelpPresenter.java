package com.easyTeach.client.presenter;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * <p>
 * The HelpPresenter class keeps all the different help messages for the
 * different User Interfaces in one place. This way different parts of help
 * messages can be reused and all help-messages are easily maintainable.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 28. November, 2013
 * @obvious Most methods are not commented as they are self explanatory (For
 *          example: getLoginTitle and getLoginHelp).
 */

public class HelpPresenter {

	// General Help
	public static Icon getHelpIcon() {
		return new ImageIcon("images/helpIcon.png");
	}

	// LoginUI Help
	public static String getLoginTitle() {
		return "EasyTeach - Login Help";
	}

	public static String getLoginHelp() {
		return "Please insert your username (your registered Email address) "
				+ "and corresponding password. \r\n If you have forgotten your "
				+ "username and password, please contact your system administrator.";
	}

	// AddExerciseUI Help
	public static String getManageExerciseTitle() {
		return "EasyTeach - Manage Exercise Help";
	}

	public static String getManageExerciseHelp() {
		return "Add info here!";
	}

	// ManageUserUI Help
	public static String getManageUserTitle() {
		return "EasyTeach - Manage User Help";
	}

	public static String getManageUserHelp() {
		return "Add info here!";
	}

	// ExerciseManagerUI Help
	public static String getExerciseManagerTitle() {
		return "EasyTeach - Exercise Manager Help";
	}

	public static String getExerciseManagerHelp() {
		return "Add info here!";
	}

	// QuestionManagerUI Help
	public static String getQuestionManagerTitle() {
		return "EasyTeach - Question Manager Help";
	}

	public static String getQuestionManagerHelp() {
		return "Add info here!";
	}

	// ManageUserUI Help
	public static String getManageClassTitle() {
		return "EasyTeach - Manage Class Help";
	}

	public static String getManageClassHelp() {
		return "Add info here!";
	}

	// AdminManagerUI Help
	public static String getAdminManagerTitle() {
		return "EasyTeach - Admin Manager Help";
	}

	public static String getAdminManagerHelp() {
		return "Welcome to the Admin Management Console!\r\n"
				+ "From here, you can manage Users, Classes and Courses "
				+ "from the corresponding tabs at the top of your console.\r\n"
				+ " From the User management tab, you may manage students, "
				+ "teachers, and other administrators from their respective"
				+ " tabs.";
	}

	// ManageCourseUI Help
	public static String getManageCourseTitle() {
		return "EasyTeach - Manage Course Help";
	}

	public static String getManageCourseHelp() {
		return "Add info here!";
	}

	// AdminManagerUI Help
	public static String getStudentManagerTitle() {
		return "EasyTeach - Student Manager Help";
	}

	public static String getStudentManagerHelp() {
		return "Add info here!";
	}

}
